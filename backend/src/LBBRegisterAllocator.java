import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LBBRegisterAllocator {

    private class BasicBlockComparator implements Comparator<BasicBlock>  {

        public int compare(BasicBlock a, BasicBlock b)
        {
            return a.blockNumber - b.blockNumber;
        }
    }

    private class IBBIRHandler extends IRHandler {

        public IBBIRHandler() {
            super();
        }

        @Override
        public String translate() {
            return "";
        }
    }

    public String path;

    public String output;

    public ArrayList<String> func_list = new ArrayList<>();
    public HashMap<String, String> data = new HashMap<>();
    public List<String> param_list = new ArrayList<String>();
    public HashMap<String, String> floats = new HashMap<>();
    boolean stop_adding_data = false;

    private IBBIRHandler irHandler;


    public LBBRegisterAllocator(String path) {
        this.path = path;
        this.irHandler = new IBBIRHandler();
        this.output = "";
    }


    public void addFloat(String f) {
        String tail = "fhvbakj"; // to avoid duplicate
        String name = "float" + Integer.toString(this.floats.size()) + tail;
        if (!floats.containsKey(f)) {
            this.floats.put(f, name);
        }
    }

    public void findFLoatWhileRead(String nl) {
        /* Extract floats in instructions */
        Pattern pattern = Pattern.compile("\\s([0-9]+\\.[0-9]+)");
        Matcher m = pattern.matcher(nl);
        while (m.find()) {
            addFloat(m.group(1));
        }
    }

    public HashMap<String, String> getData(String p) throws Exception {
        IRStream ir = new IRStream(p);
        String nl = ir.next();
        boolean start = false;
        while (nl != null) {
            if (nl.startsWith("float-list")) { // beginning of function
                start = true;
                stop_adding_data = false;
                nl = ir.next();
                continue;
            }
            if (nl.trim().startsWith("assign")) { // assign instruction
                if (!stop_adding_data && start) {
                    String[] ol = nl.trim().split(",");
                    String var = ol[1].trim();
                    String val = ol[2].trim();
                    this.data.put(var, val);
                } else {
                    findFLoatWhileRead(nl);
                }
                //print(data);
            } else {
                if (stop_adding_data || !start) {
                    findFLoatWhileRead(nl);
                }
            }
            if (nl.contains(":") && !nl.contains("(")) { // label
                String label = nl.substring(0, nl.indexOf(':'));
                start = false;
                if (label.equals("main")) {
                    stop_adding_data = true;
                } else {
                    data.clear();
                }
            }
            nl = ir.next();
        }
        data.putAll(floats);
        return data;
    }

    private boolean isLabel(String statement) {
        if (statement.endsWith(":")) {
            return true;
        } else {
            return false;
        }
    }

    public HashMap<String, String> getVarTypes(IRStream irStream) throws Exception {
        HashMap<String, String> types = new HashMap<>();
        String nl = irStream.next();
        while (nl != null) {
            if (nl.startsWith("int-list") || nl.startsWith("float-list")) {// var declaration
                VarDeclaration vd = new VarDeclaration(nl);
                // vd.clearMemory();
                vd.translate();
                types = vd.getVarType();
                output += nl + "\n";
            }

            if (isLabel(nl) && !nl.contains("(") && !nl.startsWith("int-list") && !nl.startsWith("float-list")) {
                output += nl + "\n";
                break;
            }
            nl = irStream.next();
        }
        irStream.reset();
        return types;
    }

    public void getFunctionNames(IRStream irStream) throws Exception {
        String nl = irStream.next();
        while (nl != null) {
            if (nl.contains(":") && nl.contains("(")) { // function declaration "int fact_st_1_0(int n_stf_fact_2_0):"
               String func_name = nl.substring(nl.indexOf(' ') + 1, nl.indexOf('('));
               func_list.add(func_name);
               if (nl.charAt(nl.indexOf('(') + 1) != ')') { // has parameters
                   param_list = Arrays.asList(nl.substring(nl.indexOf('(') + 1, nl.indexOf(')')).split(","));
                   for (int i = 0; i < param_list.size(); i++) {
                       param_list.set(i, param_list.get(i).substring(param_list.get(i).indexOf(' ') + 1, param_list.get(i).length()));
                   }
               }
           }
           nl = irStream.next();
        }

        irStream.reset();
    }

    public IRStream removeLinesBeforeMain(String p) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(path));
        boolean start = false;
        String modified = "";
        String nl = br.readLine();
        while (nl != null) {
            if (!start || (start && !nl.trim().startsWith("assign") && !nl.trim().startsWith("goto"))) {
                modified += nl + "\n";
            }
            if (nl.startsWith("float-list")) {
                start = true;
                nl = br.readLine();
                continue;
            }
            if (nl.contains(":") && !nl.contains("(")) {
                start = false;

            }
            nl = br.readLine();
        }

        System.out.println(modified);
        return new IRStream(modified, true);
    }

    public void generate() throws Exception {

        HashMap<String, String> data = this.getData(this.path);

        IRStream irStream = removeLinesBeforeMain(this.path);
        IRStream funcStream = irStream.nextFunction();
        while (funcStream != null) {
            HashMap<String, String> types = this.getVarTypes(funcStream.cloneStream());
            this.getFunctionNames(irStream.cloneStream());

            for (String param : param_list) {
                irHandler.addToMemory(param, 'p');
            }

            irHandler.param_list = param_list;
            irHandler.setData(data);
            irHandler.setVarType(types);
            irHandler.init(true);

            BasicBlocksGenerator bg = new BasicBlocksGenerator(funcStream, data);
            ArrayList<BasicBlock> blocks = bg.generate();

            CFGGenerator cfgGen = new CFGGenerator(blocks);

            CFGNode root = cfgGen.generate();

            for (int i = 0; i < 30; i++) {
                root.livenessAnalysis();

                root.resetTraversal();
            }

            root.collectBlocks();

            Collections.sort(root.blocks, new BasicBlockComparator());

            for (BasicBlock b : root.blocks) {
                b.print();
                b.allocateRegistersIBB();
                this.output += b.modifiedIR;
            }

            root.resetBlocks();
            funcStream = irStream.nextFunction();
        }
        System.out.println("===============");
        System.out.println(this.output);
    }
}
