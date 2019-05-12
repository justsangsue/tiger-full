import java.util.*;

public class BasicBlocksGenerator {

    private IRStream ir;

    private HashMap<String, String> data;

    public BasicBlocksGenerator(IRStream ir, HashMap<String, String> data) {
        this.ir = ir;
        this.data = data;
    }

    private String getTarget(String statement) {
        String instruction = statement.split(",")[0].trim();
        if (instruction.equals("goto")) {
            return statement.split(",")[1];
        } else {
            return statement.split(",")[3];
        }
    }

    private boolean isBranchStatement(String statement) {
        String instruction = statement.split(",")[0].trim();
        return instruction.equals("breq")
            || instruction.equals("brneq")
            || instruction.equals("brlt")
            || instruction.equals("brgt")
            || instruction.equals("brgeq")
            || instruction.equals("brleq")
            || instruction.equals("goto");
    }

    private HashSet<String> findUsedLabels() throws Exception {

        HashSet<String> usedLabels = new HashSet<String>();
        String line;
        while((line = ir.next()) != null) {
            line = line.trim();
            if (isBranchStatement(line)) {
                usedLabels.add(getTarget(line).trim());
            }
        }

        ir.reset();
        return usedLabels;
    }

    private boolean skip(String statement) {
        if (statement.startsWith("int-list:") || statement.startsWith("float-list:")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isLabel(String statement) {
        if (statement.endsWith(":")) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<BasicBlock> generate() throws Exception {
        // Find the leaders.

        HashSet<String> usedLabels = findUsedLabels();
        ArrayList<BasicBlock> blocks = new ArrayList<BasicBlock>();

        boolean first = true;

        BasicBlock current = null;
        String line;
        int iNum = 0, bNum = 0;
        while((line = ir.next()) != null) {
            line = line.trim();

            if (skip(line) == true) {
                continue;
            }

            if (first == true ) {
                first = false;
                current = new BasicBlock(bNum);
                bNum++;
            }

            if (!isLabel(line)) {
                current.addStatement(line, iNum);
                iNum++;
            }

            if ( (isLabel(line) && usedLabels.contains(line.substring(0, line.length() - 1)))
                 || isBranchStatement(line)
                 || line.startsWith("return")) {


                if (current != null && current.ir.size() > 0) {
                    if (isBranchStatement(line)) {
                        current.target = getTarget(line);
                        if(line.startsWith("goto")) {
                            current.endsWithReturnOrGoto = true;
                        }
                    } else if (line.startsWith("return")) {
                        current.endsWithReturnOrGoto = true;
                    }

                    current.setData(this.data);
                    blocks.add(current);

                    current = new BasicBlock(bNum);

                    if (isLabel(line) && usedLabels.contains(line.substring(0, line.length() - 1))) {
                        current.label = line.substring(0, line.length() - 1);
                    }

                    bNum++;
                } else {
                    if (isLabel(line) && usedLabels.contains(line.substring(0, line.length() - 1))) {
                        current.label = line.substring(0, line.length() - 1);
                    }
                }
            }
        }

        return blocks;
    }
}
