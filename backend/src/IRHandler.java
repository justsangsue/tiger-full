import java.lang.reflect.Array;
import java.util.*;


/**
* MIPS registers:
*          $zero        value 0
*          $v0 - $v1    (values) from expression evaluation and function results
*          $a0 - $a3    (arguments) First four parameters for subroutine
*          $t0 - $t9    temporary variables
*          $s0 - $s7    saved values representing final computed results
*          $ra          return address
*/

public abstract class IRHandler {
    public String newline;
    String naive;
    String ibb;
    static boolean is_init = false;
    HashMap<String, String> data = new HashMap<>();

    static HashMap<String, String> var_type = new HashMap<>(); // array or not
    static Map<String, String> memory = new LinkedHashMap<>();
    static ArrayList<String> t_registers = new ArrayList<>();
    static ArrayList<String> s_registers = new ArrayList<>();
    static ArrayList<String> a_registers = new ArrayList<>();
    static ArrayList<String> f_registers = new ArrayList<>();
    static HashMap<String, String> used_registers = new HashMap<>(); // <var, reg>
    static HashSet<String> clean_registers = new HashSet<>(); // Stores a set of registers.

    public static List<String> param_list = new ArrayList<String>();


    static boolean debug = false;

    public IRHandler() {
        this.naive = "";
        this.ibb = "";

        for (int i = 0; i < 10; i++) {
            t_registers.add("$t" + Integer.toString(i));
        }
        for (int i = 0; i < 8; i++) {
            s_registers.add("$s" + Integer.toString(i));
        }
        for (int i = 0; i < 4; i++) {
            a_registers.add("$a" + Integer.toString(i));
        }
        for (int i = 0; i < 32; i+=2) {
            f_registers.add("$f" + Integer.toString(i));
        }
        f_registers.remove("$f12"); // $f12 is a special register for result

        memory.put("start", "st");
        memory.put("ra", "rv");
        memory.put("tmp", "tp");
        memory.putAll(data);
    }

    public IRHandler(String nl) {
        this.newline = nl;
        this.naive = "";
        this.ibb = "";

        t_registers.clear();
        s_registers.clear();
        a_registers.clear();

        /* Initilize $t resigters */
        for (int i = 0; i < 10; i++) {
            t_registers.add("$t" + Integer.toString(i));
        }
        for (int i = 0; i < 8; i++) {
            s_registers.add("$s" + Integer.toString(i));
        }
        for (int i = 0; i < 4; i++) {
            a_registers.add("$a" + Integer.toString(i));
        }
        for (int i = 0; i < 32; i+=2) {
            f_registers.add("$f" + Integer.toString(i));
        }
        f_registers.remove("$f12");

        memory.put("start", "st");
        memory.put("ra", "rv");
        memory.put("tmp", "tp");
        memory.putAll(data);
    }

    static ArrayList<String> aRegValues = new ArrayList<>();
    public void useA (String val) {
        aRegValues.add(val);
    }
    public void freeA() {

    }

    public void init(boolean val) {
        this.is_init = val;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }

    public HashMap<String, String> getVarType() {
        return var_type;
    }

    public void setVarType(HashMap<String, String> var_type_map) {
        var_type = var_type_map;
    }

    public void addToMemory(String var, char t) {
        switch(t) {
            case 'v':
                memory.put(var, "val");
                break;
            case 'p':
                memory.put(var, "par");
                break;
            case 'a':
            default:
                break;
        }
    }

    public int getPosition(String var) {
        int index = 0;
        for (Map.Entry<String, String> entry : memory.entrySet()) {
            if (entry.getKey().equals(var)) {
                break;
            }
            index++;
        }
        return index;
    }

    public void clearMemory() {
        memory.clear();
        memory.put("start", "st");
        memory.put("ra", "rv");
        memory.putAll(data);
    }

    public Map<String, String> getMemory() {
        return memory;
    }

    protected static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void loadToRegister(String var, char type) {
        /* remove first available register and add a map <var, register> to used_registers */
        if (type == 't') {
            if (t_registers.size() >= 1) {
                used_registers.put(var, t_registers.remove(0));
            }
        } else if (type == 's') {
            if (s_registers.size() >= 1) {
                used_registers.put(var, s_registers.remove(0));
            }
        } else if (type =='a') {
            if (a_registers.size() >= 1) {
                used_registers.put(var, a_registers.remove(0));
            }
        } else if (type =='f') {
            if (f_registers.size() >= 1) {
                used_registers.put(var, f_registers.remove(0));
            }
        }
    }

    public void storeToMemory(String var, char type) {
        /* add register back to t_registers and update used_registers */
        if (type == 't') {
            t_registers.add(0, used_registers.get(var));
        } else if (type == 's') {
            s_registers.add(0, used_registers.get(var));
        } else if (type == 'a') {
            a_registers.add(0, used_registers.get(var));
        } else if (type == 'f') {
            f_registers.add(0, used_registers.get(var));
        }
        used_registers.remove(var);
    }

    public void printNewLine() { System.out.println(this.newline); }
    public void printMips() { System.out.println(this.naive); }
    abstract public String translate();


    protected static void print(Object o) {
        if (debug) {
            System.out.println(o);
        }
    }

}
