import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionSelection {
    String ir;
    String mips_data;
    String mips_text;
    String mips;
    HashMap<String, String> data;

    public InstructionSelection(NaiveRegisterAllocator ng) {
        this.ir = ng.output;
        this.data = ng.data;
        this.mips_data = ".data\n";
        this.mips_text = ".text\n";
    }

	public InstructionSelection(LBBRegisterAllocator ibb) {
        this.ir = ibb.output;
        this.data = ibb.data;
        this.mips_data = ".data\n";
        this.mips_text = ".text\n";
    }


    public boolean isFloat(String f) {
        Pattern pattern = Pattern.compile("[0-9]+\\.[0-9]+");
        Matcher m = pattern.matcher(f);
        if (m.find()) { return true; }
        return false;
    }

    public void generate() {
        Iterator it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if (isFloat(pair.getKey().toString())) {
                mips_data += pair.getValue() + ": .double " + pair.getKey() + "\n";
            } else if (isFloat(pair.getValue().toString())) {
                mips_data += pair.getKey() + ": .double " + pair.getValue() + "\n";
            } else {
                mips_data += pair.getKey() + ": .word " + pair.getValue() + "\n";
            }
            it.remove();
        }

        for (String line : ir.split("\n")) {
            line = line.trim();
            if (line.startsWith("int-list") || line.startsWith("float-list")) {
                continue;
            }

            if (line.startsWith("brneq ")) {
                mips_text += line.replace("brneq", "bne") + "\n";
                continue;
            }

            if (line.startsWith("breq ")) {
                mips_text += line.replace("breq", "beq") + "\n";
                continue;
            }

            if (line.startsWith("brlt ")) {
                mips_text += line.replace("brlt", "blt") + "\n";
                continue;
            }

            if (line.startsWith("brgt ")) {
                mips_text += line.replace("brgt", "bgt") + "\n";
                continue;
            }

            if (line.startsWith("brgeq ")) {
                mips_text += line.replace("brgeq", "bge") + "\n";
                continue;
            }

            if (line.startsWith("brleq ")) {
                mips_text += line.replace("brleq", "ble") + "\n";
                continue;
            }

            if (line.startsWith("goto ")) {
                mips_text += line.replace("goto", "j") + "\n";
                continue;
            }

            if (line.startsWith("add ") ||
                    line.startsWith("addi ") ||
                    line.startsWith("sub ") ||
                    line.startsWith("subi ") ||
                    line.startsWith("mult ") ||
                    line.startsWith("div ") ||
                    line.startsWith("and ") ||
                    line.startsWith("andi ") ||
                    line.startsWith("or ") ||
                    line.startsWith("ori ")) {
                String op = line.split(" ")[0];
                String remain = line.split(" ")[1];
                String x = remain.split(",")[0];
                String y = remain.split(",")[1];
                String t = remain.split(",")[2];
                if (op.equals("mult")) {
                    mips_text += "mulo " + t + "," + x + "," + y + "\n";
                } else {
                    mips_text += op + " " + t + "," + x + "," + y + "\n";
                }
                continue;
            }
            mips_text += line + "\n";
        }
        mips = mips_data + mips_text;
    }
}
