import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * General elements of IR (taken from Appendix B):
 *          function declaration => type func_name(parameters...)
 *          variable declaration => int-list, float-list
 *          labels
 *          instructions
 *              assign                                  (op, x, y, _)
 *                                                      (op, x, size, value) # array assignment
 *              add, sub, mult, div, and, or            (op, x, y, t)
 *              goto                                    (op, label, _, _)
 *              breq, brneq, brlt, brgt, brgeq, brleq   (op, x, y, label)
 *              return                                  (op, x, _, _)
 *              call                                    (op, func_name, param1, param2, ..., paramn)
 *              callr                                   (op, x, func_name, param1, param2, ..., paramn)
 *              array_store                             (op, array_name, offset, x)
 *              array_load                              (op, x, array_name, offset)
 *
 *
 * MIPS registers:
 *          $zero        value 0
 *          $v0 - $v1    (values) from expression evaluation and function results
 *          $a0 - $a3    (arguments) First four parameters for subroutine
 *          $t0 - $t9    temporary variables
 *          $s0 - $s7    saved values representing final computed results
 *          $ra          return address
 */
public class NaiveRegisterAllocator {
    IRStream ir;
    String path;
    String output;
    boolean has_return_value = false;
    boolean skip = false;
    List<String> param_list;
    int line_passed = 0;
    boolean enter_new_function = false;
    ArrayList<String> func_list = new ArrayList<>();
    HashMap<String, String> data = new HashMap<>();
    HashMap<String, String> floats = new HashMap<>();

    static boolean debug = false;

    public NaiveRegisterAllocator(String path) throws FileNotFoundException {
        this.ir = new IRStream(path);
        this.path = path;
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

    public HashMap<String, String> getData(String path) throws Exception {
        IRStream ir = new IRStream(path);
        String nl = ir.next();
        boolean enter_main = false;
        boolean stop_adding_data = false;

        while (nl != null) {
            if (nl.contains("main(")) { enter_main = true; }
            if (nl.startsWith("float-list") && enter_main) { // beginning of function
                stop_adding_data = false;
                nl = ir.next();
                continue;
            }
            if (nl.trim().startsWith("assign")) { // assign instruction
                if (!stop_adding_data && enter_main) {
                    String[] ol = nl.trim().split(",");
                    if (ol.length == 3) { // assign, x, 10
                        String var = ol[1].trim();
                        String val = ol[2].trim();
                        this.data.put(var, val);
                    } else if (ol.length == 4) { // assign, X, 100, 10
                        String var = ol[1].trim();
                        String size = ol[2].trim();
                        String val = ol[3].trim();
                        this.data.put(var, val + ":" + size);
                    }
                } else {
                    findFLoatWhileRead(nl);
                }
                //print(data);
            } else {
                if (stop_adding_data || !enter_main) {
                    findFLoatWhileRead(nl);
                }
            }
            if (nl.contains(":") && !nl.contains("(")) { // label
                String label = nl.substring(0, nl.indexOf(':'));
                if (label.equals("main")) { // stop looking for .data when meet "main:"
                    stop_adding_data = true;
                    enter_main = false;
                }
            }
            nl = ir.next();
        }
        print("floats: " + floats);
        data.putAll(floats);
        return data;
    }

    public void generate() throws Exception {
        HashMap<String, String> data = this.getData(this.path);
        HashMap<String, String> types = new HashMap<>();
        String nl = ir.next();
        while (nl != null) {
            //print(nl);
            if (nl.startsWith("int-list") || nl.startsWith("float-list")) {// var declaration
                VarDeclaration vd = new VarDeclaration(nl);
                //print("vd: " + nl);
                //output += vd.translate();
                if (enter_new_function) { vd.clearMemory(); }
                vd.translate();
                types = vd.getVarType();
                output += nl + "\n";
                enter_new_function = false;
            } else if (nl.contains(":") && nl.contains("(")) { // function declaration "int fact_st_1_0(int n_stf_fact_2_0):"
                //print("fd: " + nl);
                String func_name = nl.substring(nl.indexOf(' ') + 1, nl.indexOf('('));
                if (func_name.equals("main")) { skip = true; }
                enter_new_function = true;
                func_list.add(func_name);
                if (nl.charAt(nl.indexOf('(') + 1) != ')') { // has parameters
                    param_list = Arrays.asList(nl.substring(nl.indexOf('(') + 1, nl.indexOf(')')).split(","));
                    for (int i = 0; i < param_list.size(); i++) {
                        param_list.set(i, param_list.get(i).substring(param_list.get(i).indexOf(' ') + 1, param_list.get(i).length()));
                    }
                    //print("param-list: " + param_list);
                }
                if (nl.startsWith("void")) { // no return value
                    has_return_value = false;
                } else {
                    has_return_value = true;
                }
                print("function name: " + func_name);
            } else if (nl.contains(":")) { // labels
                //print("lb: " + nl);
                String label_name = nl.substring(0, nl.length() - 1);
                if (label_name.equals("main")) { skip = false; }
                output += nl + "\n"; // labels
                //print("func_list: " + func_list);
                if (func_list.contains(label_name)) {
                    //print(nl.substring(0, nl.length() - 1));
                    line_passed = 0;
                }
            } else { // instructions
                //print("ins: " + nl);
                if (skip) {
                    nl = ir.next();
                    continue;
                }
                if (has_return_value) {
                    IRInstruction ins = new IRInstruction(nl);
                    ins.setData(data);
                    ins.setVarType(types);
                    if (line_passed == 1) { ins.init(true); }
                    //print("param list of has_return_value: " + param_list);
                    //print("line: " + nl);
                    for (String param : param_list) {
                        ins.addToMemory(param, 'p');
                    }
                    ins.selectMode(0);
                    String o = ins.translate();
                    output += o;
                    print("data: " + ins.data);
                    print("memory: " + ins.getMemory());
                    print("var type: " + ins.getVarType());
                    print(nl);
                    print(o);
                    ins.init(false);
                } else {
                    IRInstruction ins = new IRInstruction(nl);
                    ins.setData(data);
                    ins.setVarType(types);
                    if (line_passed == 1) { ins.init(true); }
                    //print("param list of not_has_return_value: " + param_list);
                    //print("line: " + nl);
                    if (param_list != null) {
                        for (String param : param_list) {
                            ins.addToMemory(param, 'p');
                        }
                    }
                    ins.selectMode(0);
                    String o = ins.translate();
                    output += o;
                    print("data: " + ins.data);
                    print("memory: " + ins.getMemory());
                    print("var type: " + ins.getVarType());
                    print(nl);
                    print(o);
                }
                enter_new_function = false;
            }
            nl = ir.next();
            line_passed += 1;
        }
        types.clear();
    }

    private static void print(Object o) {
        if (debug) {
            System.out.println(o);
        }
    }
}
