import java.util.*;

/**
 * Different IR triggers different behavior:
 *     assign (single variable) -> op, x, y, _                                      ->
 *     assign (array)           -> op, x, size, value                               ->
 *     arithmetic               -> op, x, y, t                                      ->
 *     goto                     -> op, label, _, _                                  ->
 *     branch                   -> op, x, y, label                                  ->
 *     return                   -> op, x, _, _                                      ->
 *     call                     -> op, func_name, param1, param2, ..., paramn       ->
 *     callr                    -> op, x, func_name, param1, param2, ..., paramn    ->
 *     array_store              -> op, array_name, offset, x                        ->
 *     array_load               -> op, x, array_name, offset                        ->
 */
public class IRInstruction extends IRHandler {

    int mode = 0;

    public int number;

    public HashSet<String> liveIn; // Live variables coming into basic block.

    public HashSet<String> liveOut; // Outgoing live variables.

    public HashSet<String> prioritizedSet;

    public IRInstruction(String nl, int number) {
        super(nl);
        this.number = number;
    }

    public IRInstruction(String nl) {
        super(nl);
    }

    public void selectMode(int i) {
        /* 0: naive; 1: CFG; 2: global */
        this.mode = i;
    }

    public void loadToRegisterIfNeeded(String target, String src, char t, String l) {
        if (used_registers.get(target) == null) {
            loadToRegister(target, t);
            naive += "   " + l.trim() + " " + used_registers.get(target) + "," + src + "\n";
        }
    }

    public void assignToRegisterIfNeeded(String var, char t) {
        if (used_registers.get(var) == null) {
            loadToRegister(var, t);
        }
    }

    public void storeToMemoryIfNotPrioritized(String var, char t, String s) {
        if (!this.prioritizedSet.contains(var)) {
            naive += "   " + s.trim() + " " + used_registers.get(var) + "," + Integer.toString(getPosition(var) * 4) + "($sp)\n";
            storeToMemory(var, t);
        }
    }

    public void unassignFromRegister(String var, char t) {
        if (!this.prioritizedSet.contains(var)) {
            storeToMemory(var, t);
        }
    }
    public void clearUsedRegisters() {
        t_registers.clear();
        f_registers.clear();
        /* Initilize $t resigters */
        for (int i = 0; i < 10; i++) {
            t_registers.add("$t" + Integer.toString(i));
        }
        for (int i = 0; i < 32; i+=2) {
            f_registers.add("$f" + Integer.toString(i));
        }
        f_registers.remove("$f12");

        ArrayList<String> keys = new ArrayList<String>(used_registers.keySet());

        for (String key : keys) {
            if (used_registers.get(key).startsWith("$t") || used_registers.get(key).startsWith("$f")) {
                used_registers.remove(key);
            }
        }
    }

    public String storeAllRegistersToMemory() {
        Iterator it = used_registers.entrySet().iterator();
        String registerStores = "";
        while (it.hasNext()) {
            Map.Entry<String, String> register = (Map.Entry)it.next();
            String var = register.getKey();
            if (register.getValue().startsWith("$t")) {
                if (this.liveOut.contains(var) && !clean_registers.contains(register.getValue())) {
                    registerStores += "   sw " + used_registers.get(var) + "," + Integer.toString(getPosition(var) * 4) + "($sp)\n";
                }
            }
        }

        clean_registers.clear();
        return registerStores;
    }

    public String translateIBB() {
        if (is_init) {
            //print("init: " + newline);
            naive += "   addiu $sp,$sp,-" + Integer.toString(memory.size() * 4) + "\n";
            naive += "   sw $ra," + Integer.toString(getPosition("ra") * 4) + "($sp)\n";

            this.init(false);
        }
        //print(memory);
        String[] ol = newline.split(","); // operands list
        // System.out.println(ol);
        /* assign */
        if (ol[0].trim().equals("assign")) {
            String var = ol[1].trim();
            String type = var_type.get(var);

            if (type != null && type.equals("float-list") || (data.containsKey(var) && data.get(var).contains("."))) {
                /* op, var, src, _ */
                String src = ol[2].trim();
                if (param_list.contains(src)) {
                    if (!data.containsKey(var)) { // assign, var, 1/input
                        assignToRegisterIfNeeded(var, 'f');
                        naive += "   move " + used_registers.get(var) + "," + "$a0" + "\n";
                        storeToMemoryIfNotPrioritized(var, 'f', "sdc1");
                    } else { // assign, input, 1/input
                        assignToRegisterIfNeeded(var, 'f');
                        naive += "   ldc1 " + used_registers.get(var) + "," + "$a0" + "\n";
                        storeToMemoryIfNotPrioritized(var, 'f', "sdc1");
                    }
                } else if (isNumeric(src) || data.containsKey(src)) { // src -> 1.0 or src -> input
                    if (!data.containsKey(var)) { // assign, var, 1.0/input
                        if (data.containsKey(src)) { src = data.get(src); }
                        loadToRegisterIfNeeded(var, src, 'f', "ldc1");
                        storeToMemoryIfNotPrioritized(var, 'f', "sdc1");
                    } else { // assign, input, 1.0/input
                        if (data.containsKey(src)) { src = data.get(src); }
                        loadToRegisterIfNeeded(var, src, 'f', "ldc1");
                        storeToMemoryIfNotPrioritized(var, 'f', "sdc1");
                    }
                } else { // src -> $reg
                    if (data.containsKey(var)) {
                        char st = memory.get(src).equals("val") ? 'f' : 'a';
                        if (st == 'a') { // assign, input, param
                            assignToRegisterIfNeeded(src, st);
                            naive += "   sdc1 " + used_registers.get(src) + "," + var + "\n";
                            unassignFromRegister(src, st);
                        } else { // assign, input, src
                            loadToRegisterIfNeeded(src, Integer.toString(getPosition(src) * 4) + "($sp)", st, "ldc1");
                            naive += "   sdc1 " + used_registers.get(src) + "," + var + "\n";
                            unassignFromRegister(src, st);
                        }
                    } else {
                        char st = memory.get(src).equals("val") ? 'f' : 'a';
                        if (st == 'a') { // assign, var, param
                            assignToRegisterIfNeeded(src, st);
                            naive += "   sdc1 " + used_registers.get(src) + "," + Integer.toString(getPosition(var) * 4) + "($sp)\n";
                            unassignFromRegister(src, st);
                        } else { // assign, var, src
                            loadToRegisterIfNeeded(src, Integer.toString(getPosition(src) * 4) + "($sp)", st, "ldc1");
                            assignToRegisterIfNeeded(var, 'f');
                            naive += "   move " + used_registers.get(var) + "," + used_registers.get(src) + "\n";
                            storeToMemoryIfNotPrioritized(var, 'f', "sdc1");
                            unassignFromRegister(src, st);
                        }
                    }
                }
                //naive += "   " + newline.trim() + "\n";
                //print(memory);
                //print(newline);
                return naive;
            }
            if (type != null && type.equals("int-list") || data.containsKey(var)) { // values
                /* op, var, src, _ */
                String src = ol[2].trim();
                if (param_list.contains(src)) {
                    if (!data.containsKey(var)) { // assign, var, 1/input
                        assignToRegisterIfNeeded(var, 't');
                        naive += "   move " + used_registers.get(var) + "," + "$a0" + "\n";
                        storeToMemoryIfNotPrioritized(var, 't', "sw");
                    } else { // assign, input, 1/input
                        String l = isNumeric(src)? "   li " : "   lw ";
                        assignToRegisterIfNeeded(var, 't');
                        naive += l + used_registers.get(var) + "," + "$a0" + "\n";
                        storeToMemoryIfNotPrioritized(var, 't', "sw");
                    }
                } else if (isNumeric(src) || data.containsKey(src)) { // src -> 1 or src -> input
                    String l = isNumeric(src)? "   li " : "   lw ";
                    if (!data.containsKey(var)) { // assign, var, 1/input
                        assignToRegisterIfNeeded(var, 't');
                        naive += l + used_registers.get(var) + "," + src + "\n";
                        storeToMemoryIfNotPrioritized(var, 't', "sw");
                    } else { // assign, input, 1/input
                        assignToRegisterIfNeeded(var, 't');
                        naive += l + used_registers.get(var) + "," + src + "\n";
                        storeToMemoryIfNotPrioritized(var, 't', "sw");
                    }
                } else { // src -> $reg
                    if (data.containsKey(var)) {
                        char st = memory.get(src).equals("val") ? 't' : 'a';
                        if (st == 'a') { // assign, input, param
                            assignToRegisterIfNeeded(src, st);
                            naive += "   sw " + used_registers.get(src) + "," + var + "\n";
                            unassignFromRegister(src, st);
                        } else { // assign, input, src
                            loadToRegisterIfNeeded(src, Integer.toString(getPosition(src) * 4) + "($sp)", st, "lw");
                            naive += "   sw " + used_registers.get(src) + "," + var + "\n";
                            unassignFromRegister(src, st);
                        }
                    } else {
                        char st = memory.get(src).equals("val") ? 't' : 'a';
                        if (st == 'a') { // assign, var, param
                            assignToRegisterIfNeeded(src, st);
                            naive += "   sw " + used_registers.get(src) + "," + Integer.toString(getPosition(var) * 4) + "($sp)\n";
                            unassignFromRegister(src, st);
                        } else { // assign, var, src
                            loadToRegisterIfNeeded(src, Integer.toString(getPosition(src) * 4) + "($sp)", st, "lw");
                            assignToRegisterIfNeeded(var, 't');
                            naive += "   move " + used_registers.get(var) + "," + used_registers.get(src) + "\n";
                            storeToMemoryIfNotPrioritized(var, 't', "sw");
                            unassignFromRegister(src, st);
                        }
                    }
                }
                //naive += "   " + newline.trim() + "\n";
                //print(memory);
                //print(newline);
                return naive;
            } else { // arrays
                /* op, x, size, value */
                print(newline);
                String op = ol[0].trim();
                String x = ol[1].trim();
                String size = ol[2].trim();
                String value = ol[3].trim();
                //naive += "   ";
                return naive;
            }
        }

        /* add, sub, mult, div, and, or */
        if (ol[0].trim().equals("add") ||
                ol[0].trim().equals("sub") ||
                ol[0].trim().equals("mult") ||
                ol[0].trim().equals("div") ||
                ol[0].trim().equals("and") ||
                ol[0].trim().equals("or")) {
            /* op, x, y, t */
            String op = ol[0].trim();
            String x = ol[1].trim();
            String y = ol[2].trim();
            String t = ol[3].trim();

            print("x: " + x);
            print("y: " + y);
            print("t: " + t);

            String type = var_type.get(x);
            if (type != null && type.equals("float-list")) {
                //char tt = memory.get(t).equals("val")? 't' : 'a';
                assignToRegisterIfNeeded(t, 't');
                //naive += "   lw " + used_registers.get(t) + "," + Integer.toString(getPosition(t) * 4) + "($sp)\n";
                if (data.containsKey(x)) {
                    if (isNumeric(y) || data.containsKey(y)) { // add, input, 3.0, t
                        if (data.containsKey(y)) { y = data.get(y); }
                        if (op.equals("add") || op.equals("sub") || op.equals("mult") || op.equals("div")) {
                            op += ".d";
                        }
                        loadToRegisterIfNeeded(x, Integer.toString(getPosition(x) * 4) + "($sp)", 'f', "ldc1");

                        naive += "   " + op + used_registers.get(x) + "," + y + "," + used_registers.get(t) + "\n";
                        unassignFromRegister(x, 'f');
                    } else { // add, input, y, t
                        char yt = memory.get(y).equals("val") ? 'f' : 'a';
                        loadToRegister(y, yt);
                        loadToRegister(x, 'f');

                        loadToRegisterIfNeeded(y, Integer.toString(getPosition(y) * 4) + "($sp)", yt, "ldc1");
                        loadToRegisterIfNeeded(x, Integer.toString(getPosition(x) * 4) + "($sp)", 'f', "ldc1");

                        naive += "   " + op + " " + used_registers.get(x) + "," + used_registers.get(y) + "," + used_registers.get(t) + "\n";
                        unassignFromRegister(y, yt);
                        unassignFromRegister(x, 'f');
                    }
                } else {
                    char xt = memory.get(x).equals("val") ? 'f' : 'a';

                    if (isNumeric(y) || data.containsKey(y)) { // add, x, 2.0, t
                        if (data.containsKey(y)) {
                            y = data.get(y);
                        }
                        loadToRegister(x, xt);
                        loadToRegisterIfNeeded(x, Integer.toString(getPosition(x) * 4) + "($sp)", xt, "ldc1");

                        naive += "   " + op + " " + used_registers.get(x) + "," + y + "," + used_registers.get(t) + "\n";
                        unassignFromRegister(x, xt);
                    } else { // add, x, y, t
                        char yt = memory.get(y).equals("val") ? 'f' : 'a';

                        loadToRegisterIfNeeded(x, Integer.toString(getPosition(x) * 4) + "($sp)", xt, "ldc1");
                        loadToRegisterIfNeeded(y, Integer.toString(getPosition(y) * 4) + "($sp)", yt, "ldc1");

                        naive += "   " + op + " " + used_registers.get(x) + "," + used_registers.get(y) + "," + used_registers.get(t) + "\n";
                        unassignFromRegister(x, xt);
                        unassignFromRegister(y, yt);
                    }
                }
                naive += "   sdc1 " + used_registers.get(t) + "," + Integer.toString(getPosition(t) * 4) + "($sp)\n";
                storeToMemory(t, 't');
                //naive += "   " + newline.trim() + "\n";
                return naive;
            }
            if (type != null && type.equals("int-list")) {
            //char tt = memory.get(t).equals("val")? 't' : 'a';
                assignToRegisterIfNeeded(t, 't');
                //naive += "   lw " + used_registers.get(t) + "," + Integer.toString(getPosition(t) * 4) + "($sp)\n";
                if (data.containsKey(x)) {
                    if (isNumeric(y) || data.containsKey(y)) { // add, input, 3, t
                        if (data.containsKey(y)) { y = data.get(y); }
                        if (op.equals("add") || op.equals("and") || op.equals("or") || op.equals("sub")) {
                            loadToRegisterIfNeeded(x, Integer.toString(getPosition(x) * 4) + "($sp)", 't', "lw");
                            naive += "   " + op + "i " + used_registers.get(x) + "," + y + "," + used_registers.get(t) + "\n";
                            unassignFromRegister(x, 't');
                        } else {
                            loadToRegisterIfNeeded(x, Integer.toString(getPosition(x) * 4) + "($sp)", 't', "lw");
                            naive += "   " + op + used_registers.get(x) + "," + y + "," + used_registers.get(t) + "\n";
                            unassignFromRegister(x, 't');
                        }
                    } else { // add, input, y, t
                        char yt = memory.get(y).equals("val")? 't' : 'a';
                        loadToRegisterIfNeeded(y, Integer.toString(getPosition(y) * 4) + "($sp)", yt, "lw");
                        loadToRegisterIfNeeded(x, Integer.toString(getPosition(x) * 4) + "($sp)", 't', "lw");

                        naive += "   " + op + " " + used_registers.get(x) + "," + used_registers.get(y) + "," + used_registers.get(t) + "\n";
                        unassignFromRegister(y, yt);
                        unassignFromRegister(x, 't');
                    }
                } else {
                    char xt = memory.get(x).equals("val")? 't' : 'a';

                    if (isNumeric(y) || data.containsKey(y)) { // add, x, 2, t
                        if (data.containsKey(y)) { y = data.get(y); }
                        loadToRegisterIfNeeded(x, Integer.toString(getPosition(x) * 4) + "($sp)", xt, "lw");

                        naive += "   " + op + " " + used_registers.get(x) + "," + y + "," + used_registers.get(t) + "\n";
                        unassignFromRegister(x, xt);
                    } else { // add, x, y, t
                        char yt = memory.get(y).equals("val")? 't' : 'a';
                        loadToRegisterIfNeeded(x, Integer.toString(getPosition(x) * 4) + "($sp)", xt, "lw");
                        loadToRegisterIfNeeded(y, Integer.toString(getPosition(y) * 4) + "($sp)", xt, "lw");

                        naive += "   " + op + " " + used_registers.get(x) + "," + used_registers.get(y) + "," + used_registers.get(t) + "\n";
                        unassignFromRegister(x, xt);
                        unassignFromRegister(y, yt);
                    }
                }
                storeToMemoryIfNotPrioritized(t, 't', "sw");
                //naive += "   " + newline.trim() + "\n";
                return naive;
            }
        }

        /* breq, brneq, brlt, brgt, brgeq, brleq */
        if (ol[0].trim().equals("breq") ||
                ol[0].trim().equals("brneq") ||
                ol[0].trim().equals("brlt") ||
                ol[0].trim().equals("brgt") ||
                ol[0].trim().equals("brgeq") ||
                ol[0].trim().equals("brleq")) {
            /* op, x, y, label */
            String op = ol[0].trim();
            String x = ol[1].trim();
            String y = ol[2].trim();
            String label = ol[3].trim();

            String type = var_type.get(x);
            if (type != null && type.equals("float-list")) {
                if (data.containsKey(x)) {
                    if (op.equals("breq")) { op = "c.eq.d"; }
                    if (op.equals("brlt")) { op = "c.lt.d"; }
                    if (op.equals("brleq")) { op = "c.le.d"; }

                    if (isNumeric(y) || data.containsKey(y)) { // c.eq.d input, 3.0, label
                        if (data.containsKey(y)) { y = data.get(y); }

                        loadToRegisterIfNeeded(x, Integer.toString(getPosition(x) * 4) + "($sp)", 'f', "ldc1");

                        if (op.equals("brgt")) {
                            op = "c.le.d";
                            naive += "   " + op + " " + y + "," + used_registers.get(x) + "," + label + "\n";
                        } else if (op.equals("brgeq")) {
                            op = "c.lt.d";
                            naive += "   " + op + " " + y + "," + used_registers.get(x) + "," + label + "\n";
                        } else {
                            naive += "   " + op + " " + used_registers.get(x) + "," + y + "," + label + "\n";
                        }
                        unassignFromRegister(x, 'f');
                    }
                } else {
                    if (isNumeric(y) || data.containsKey(y)) { // c.eq.d x, 2.0, label
                        if (data.containsKey(y)) {
                            y = data.get(y);
                        }
                        char xt = memory.get(x).equals("val") ? 'f' : 'a';

                        loadToRegisterIfNeeded(x, Integer.toString(getPosition(x) * 4) + "($sp)", xt, "ldc1");

                        naive += "   " + op + " " + used_registers.get(x) + "," + y + "," + label + "\n";
                        unassignFromRegister(x, xt);
                    } else { // bne, x, y, label
                        char xt = memory.get(x).equals("val") ? 'f' : 'a';
                        char yt = memory.get(y).equals("val") ? 'f' : 'a';

                        loadToRegisterIfNeeded(x, Integer.toString(getPosition(x) * 4) + "($sp)", xt, "ldc1");
                        loadToRegisterIfNeeded(y, Integer.toString(getPosition(y) * 4) + "($sp)", yt, "ldc1");

                        naive += "   " + op + " " + used_registers.get(x) + "," + used_registers.get(y) + "," + label + "\n";
                        unassignFromRegister(x, xt);
                        unassignFromRegister(y, yt);
                    }
                }
                //naive += "   " + newline.trim() + "\n";
                return naive;
            }

            if (type != null && type.equals("int-list")) {

                if (data.containsKey(x)) {
                    if (isNumeric(y) || data.containsKey(y)) { // bne, input, 3, label
                        if (data.containsKey(y)) { y = data.get(y); }
                        loadToRegisterIfNeeded(x, Integer.toString(getPosition(x) * 4) + "($sp)", 't', "lw");

                        naive += storeAllRegistersToMemory();
                        naive += "   " + op + " " + used_registers.get(x) + "," + y + "," + label + "\n";
                        clearUsedRegisters();
                    }
                } else {
                    if (isNumeric(y) || data.containsKey(y)) { // bne, x, 2, label
                        if (data.containsKey(y)) { y = data.get(y); }
                        char xt = memory.get(x).equals("val")? 't' : 'a';
                        loadToRegisterIfNeeded(x, Integer.toString(getPosition(x) * 4) + "($sp)", xt, "lw");

                        naive += storeAllRegistersToMemory();
                        naive += "   " + op + " " + used_registers.get(x) + "," + y + "," + label + "\n";
                        clearUsedRegisters();
                    } else { // bne, x, y, label
                        char xt = memory.get(x).equals("val")? 't' : 'a';
                        char yt = memory.get(y).equals("val")? 't' : 'a';
                        loadToRegisterIfNeeded(x, Integer.toString(getPosition(x) * 4) + "($sp)", xt, "lw");
                        loadToRegisterIfNeeded(y, Integer.toString(getPosition(y) * 4) + "($sp)", yt, "lw");

                        naive += storeAllRegistersToMemory();
                        naive += "   " + op + " " + used_registers.get(x) + "," + used_registers.get(y) + "," + label + "\n";
                        clearUsedRegisters();
                    }
                }
                //naive += "   " + newline.trim() + "\n";
                return naive;
            }
        }

        /* goto */
        if (ol[0].trim().equals("goto")) {
            /* op, label, _, _ */
            naive += storeAllRegistersToMemory();
            clearUsedRegisters();

            String op = ol[0].trim();
            String label = ol[1].trim();
            naive += "   " + op + " " + label + "\n";
            return naive;
        }

        /* return */
        if (ol[0].trim().equals("return")) {
            naive += storeAllRegistersToMemory();

            /* op, x, _, _ */
            String x = "";
            if (ol.length > 1) {
                x = ol[1].trim();
            }
            naive += "   lw $ra," + Integer.toString(getPosition("ra") * 4) + "($sp)\n";
            if (!x.equals("")) {
                if (used_registers.get(x) != null) {
                    naive += "   move $v0," + used_registers.get(x) + "\n";
                } else {
                    naive += "   lw $v0," + Integer.toString(getPosition(x) * 4) + "($sp)\n";
                }
            }
            naive += "   addiu $sp,$sp," + Integer.toString(memory.size() * 4) + "\n";
            naive += "   jr $ra\n";
            //naive += "   " + newline.trim() + "\n";
            clearUsedRegisters();

            return naive;
        }

        /* call */
        if (ol[0].trim().equals("call")) {
            /* op, func_name, param1, param2, ..., paramn */
            this.naive += storeAllRegistersToMemory();
            String func_name = ol[1].trim();
            if (func_name.equals("printi")) {
                String param = ol[2].trim();
                if (isNumeric(param)) { // call, printi, 5
                    if (param.contains(".")) { // call, printi, 5.0
                        naive += "   ldc1 $f12," + data.get(param) + "\n";
                        naive += "   li $v0,3\n";
                        naive += "   syscall\n";
                    } else { // call, printi, 5
                        naive += "   li $a0," + param + "\n";
                        naive += "   li $v0,1\n";
                        naive += "   syscall\n";
                    }
                } else { // call, printi, param
                    if (var_type.get(param).equals("float-list")) { // call, printi, float_param
                        if (memory.get(param).equals("par")) {
                            naive += "   mtc1 $a0,$f12\n";
                        } else {
                            if (!data.containsKey(param)) {
                                naive += "   ld $a0,$f12\n";
                            } else { // call, printi, float_input
                                naive += "   ld $a0," + param + "\n";
                            }
                            naive += "   mtc1.d $a0,$f12\n";
                        }
                        naive += "   li $v0,3\n";
                        naive += "   syscall\n";
                    } else {
                        if (memory.get(param).equals("par")) {
                            naive += "   sw $a0," + Integer.toString(getPosition(param) * 4) + "($sp)\n";
                        }
                        if (!data.containsKey(param)) {
                            if (used_registers.get(param) != null) {
                                naive += "   move $a0," + used_registers.get(param) + "\n";
                            } else {
                                naive += "   lw $a0," + Integer.toString(getPosition(param) * 4) + "($sp)\n";
                            }

                        } else { // call, printi, input
                            if (used_registers.get(param) != null) {
                                naive += "   move $a0," + used_registers.get(param) + "\n";
                            } else {
                                naive += "   lw $a0," + param + "\n";
                            }
                        }
                        naive += "   li $v0,1\n";
                        naive += "   syscall\n";
                    }
                }
            } else {
                String param = ol[2].trim();
                if (param.contains(".")) {
                    if (isNumeric(param)) { // call, print, 5.5
                        naive += "   ldc1 $f12," + data.get(param) + "\n";
                        naive += "   mfc1 $a0,$f12\n";
                        naive += "   jal " + func_name + "\n";
                    } else { // call, func, param
                        //loadToRegister(param, 'a');
                        if (memory.get(param).equals("par")) {
                            naive += "   sw $a0," + Integer.toString(getPosition(param) * 4) + "($sp)\n";
                        }
                        if (!data.containsKey(param)) {
                            if (used_registers.get(param) != null) {
                                naive += "   move $a0," + used_registers.get(param) + "\n";
                            } else {
                                naive += "   ld $a0," + Integer.toString(getPosition(param) * 4) + "($sp)\n";
                            }
                        } else {
                            if (used_registers.get(param) != null) {
                                naive += "   move $a0," + used_registers.get(param) + "\n";
                            } else {
                                naive += "   ld $a0," + param + "\n";
                            }
                        }
                        naive += "   jal " + func_name + "\n";
                    }
                } else {
                    if (isNumeric(param)) { // call, print, 5
                        naive += "   li $a0," + param + "\n";
                        naive += "   jal " + func_name + "\n";
                    } else { // call, func, param
                        //loadToRegister(param, 'a');
                        if (memory.get(param).equals("par")) {
                            naive += "   sw $a0," + Integer.toString(getPosition(param) * 4) + "($sp)\n";
                        }
                        if (!data.containsKey(param)) {
                            if (used_registers.get(param) != null) {
                                naive += "   move $a0," + used_registers.get(param) + "\n";
                            } else {
                                naive += "   lw $a0," + Integer.toString(getPosition(param) * 4) + "($sp)\n";
                            }
                        } else {
                            if (used_registers.get(param) != null) {
                                naive += "   move $a0," + used_registers.get(param) + "\n";
                            } else {
                                naive += "   lw $a0," + param + "\n";
                            }
                        }
                        naive += "   jal " + func_name + "\n";
                    }
                }
            }
            return naive;
        }

        /* callr */
        if (ol[0].trim().equals("callr")) {
            /* op, x, func_name, param1, param2, ..., paramn */
            this.naive += storeAllRegistersToMemory();
            //naive += "   " + newline.trim() + "\n";
            String op = ol[0].trim();
            String x = ol[1].trim();
            String f = ol[2].trim();
            String p = ol[3].trim();

            if (p.contains(".")) {
                naive += "   sw $a0," + Integer.toString(getPosition("tmp") * 4) + "($sp)\n";
                if (used_registers.get(p) != null) {
                    naive += "   move $a0," + used_registers.get(p) + "\n";
                } else {
                    naive += "   lw $a0," + Integer.toString(getPosition(p) * 4) + "($sp)\n";
                }
                naive += "   jal " + f + "\n";
                naive += "   sw $v0," + Integer.toString(getPosition(x) * 4) + "($sp)\n";
                naive += "   lw $a0," + Integer.toString(getPosition("tmp") * 4) + "($sp)\n";
                return naive;
            } else {
                naive += "   sw $a0," + Integer.toString(getPosition("tmp") * 4) + "($sp)\n";
                if (used_registers.get(p) != null) {
                    naive += "   move $a0," + used_registers.get(p) + "\n";
                } else {
                    naive += "   lw $a0," + Integer.toString(getPosition(p) * 4) + "($sp)\n";
                }
                naive += "   jal " + f + "\n";
                naive += "   sw $v0," + Integer.toString(getPosition(x) * 4) + "($sp)\n";
                naive += "   lw $a0," + Integer.toString(getPosition("tmp") * 4) + "($sp)\n";
                return naive;
            }
        }

        /* array_store */
        if (ol[0].trim().equals("array_store")) {
            /* op, array_name, offset, x */

            naive += "   " + newline.trim() + "\n";
            return naive;
        }

        /* array_load */
        if (ol[0].trim().equals("array_load")) {
            /* op, x, array_name, offset */

            naive += "   " + newline.trim() + "\n";
            return naive;
        }

        return naive;
    }

    @Override
    public String translate() {
        switch(mode) {
            case 0: // naive
                if (is_init) {
                    //print("init: " + newline);
                    naive += "   addiu $sp,$sp,-" + Integer.toString(memory.size() * 4) + "\n";
                    naive += "   sw $ra," + Integer.toString(getPosition("ra") * 4) + "($sp)\n";

                    this.init(false);
                }
                //print(memory);
                String[] ol = newline.split(","); // operands list
                // System.out.println(ol);
                /* assign */
                if (ol[0].trim().equals("assign")) {
                    String var = ol[1].trim();
                    String type = var_type.get(var);
                    //print("data: " + data);
                    //print("used registers:" + used_registers);
                    //print("t registers: " + t_registers);
                    if (type != null && type.equals("float-list") || (data.containsKey(var) && data.get(var).contains("."))) {
                        /* op, var, src, _ */
                        String src = ol[2].trim();
                        if (isNumeric(src) || data.containsKey(src)) { // src -> 1.0 or src -> input
                            if (!data.containsKey(var)) { // assign, var, 1.0/input
                                loadToRegister(var, 'f');
                                if (data.containsKey(src)) { src = data.get(src); }
                                naive += "   ldc1 " + used_registers.get(var) + "," + src + "\n";
                                naive += "   sdc1 " + used_registers.get(var) + "," + Integer.toString(getPosition(var) * 4) + "($sp)\n";
                                storeToMemory(var, 'f');
                            } else { // assign, input, 1.0/input
                                loadToRegister(var, 'f');
                                if (data.containsKey(src)) { src = data.get(src); }
                                naive += "   ldc1 " + used_registers.get(var) + "," + src + "\n";
                                naive += "   sdc1 " + used_registers.get(var) + "," + var + "\n";
                                storeToMemory(var, 'f');
                            }
                        } else { // src -> $reg
                            if (data.containsKey(var)) {
                                char st = memory.get(src).equals("val") ? 'f' : 'a';
                                loadToRegister(src, st);
                                if (st == 'a') { // assign, input, param
                                    naive += "   sdc1 " + used_registers.get(src) + "," + var + "\n";
                                    storeToMemory(src, st);
                                } else { // assign, input, src
                                    naive += "   ldc1 " + used_registers.get(src) + "," + Integer.toString(getPosition(src) * 4) + "($sp)\n";
                                    naive += "   sdc1 " + used_registers.get(src) + "," + var + "\n";
                                    storeToMemory(src, st);
                                }
                            } else {
                                char st = memory.get(src).equals("val") ? 'f' : 'a';
                                loadToRegister(src, st);
                                if (st == 'a') { // assign, var, param
                                    naive += "   sdc1 " + used_registers.get(src) + "," + Integer.toString(getPosition(var) * 4) + "($sp)\n";
                                    storeToMemory(src, st);
                                } else { // assign, var, src
                                    loadToRegister(var, 'f');
                                    naive += "   ldc1 " + used_registers.get(var) + "," + Integer.toString(getPosition(src) * 4) + "($sp)\n";
                                    naive += "   sdc1 " + used_registers.get(var) + "," + Integer.toString(getPosition(var) * 4) + "($sp)\n";
                                    storeToMemory(var, 'f');
                                    storeToMemory(src, st);
                                }
                            }
                        }
                        //naive += "   " + newline.trim() + "\n";
                        //print(memory);
                        //print(newline);
                        return naive;
                    }
                    if (type != null && type.equals("int-list") || data.containsKey(var)) { // values
                        /* op, var, src, _ */
                        String src = ol[2].trim();
                        if (isNumeric(src) || data.containsKey(src)) { // src -> 1 or src -> input
                            if (!data.containsKey(var)) { // assign, var, 1/input
                                String l = isNumeric(src)? "   li " : "   lw ";
                                loadToRegister(var, 't');
                                naive += l + used_registers.get(var) + "," + src + "\n";
                                naive += "   sw " + used_registers.get(var) + "," + Integer.toString(getPosition(var) * 4) + "($sp)\n";
                                storeToMemory(var, 't');
                            } else { // assign, input, 1/input
                                String l = isNumeric(src)? "   li " : "   lw ";
                                loadToRegister(var, 't');
                                naive += l + used_registers.get(var) + "," + src + "\n";
                                naive += "   sw " + used_registers.get(var) + "," + var + "\n";
                                storeToMemory(var, 't');
                            }
                        } else { // src -> $reg
                            if (data.containsKey(var)) {
                                char st = memory.get(src).equals("val") ? 't' : 'a';
                                loadToRegister(src, st);
                                if (st == 'a') { // assign, input, param
                                    naive += "   sw " + used_registers.get(src) + "," + var + "\n";
                                    storeToMemory(src, st);
                                } else { // assign, input, src
                                    naive += "   lw " + used_registers.get(src) + "," + Integer.toString(getPosition(src) * 4) + "($sp)\n";
                                    naive += "   sw " + used_registers.get(src) + "," + var + "\n";
                                    storeToMemory(src, st);
                                }
                            } else {
                                char st = memory.get(src).equals("val") ? 't' : 'a';
                                loadToRegister(src, st);
                                if (st == 'a') { // assign, var, param
                                    naive += "   sw " + used_registers.get(src) + "," + Integer.toString(getPosition(var) * 4) + "($sp)\n";
                                    storeToMemory(src, st);
                                } else { // assign, var, src
                                    loadToRegister(var, 't');
                                    naive += "   lw " + used_registers.get(var) + "," + Integer.toString(getPosition(src) * 4) + "($sp)\n";
                                    naive += "   sw " + used_registers.get(var) + "," + Integer.toString(getPosition(var) * 4) + "($sp)\n";
                                    storeToMemory(var, 't');
                                    storeToMemory(src, st);
                                }
                            }
                        }
                        //naive += "   " + newline.trim() + "\n";
                        //print(memory);
                        //print(newline);
                        return naive;
                    } else { // arrays
                        // Assume that this part will be handled at var declaration
                        /* op, x, size, value */
                        print(newline);
                        String op = ol[0].trim();
                        String x = ol[1].trim();
                        String size = ol[2].trim();
                        String value = ol[3].trim();
                        //naive += "   ";
                        return naive;
                    }
                }

                /* add, sub, mult, div, and, or */
                if (ol[0].trim().equals("add") ||
                        ol[0].trim().equals("sub") ||
                        ol[0].trim().equals("mult") ||
                        ol[0].trim().equals("div") ||
                        ol[0].trim().equals("and") ||
                        ol[0].trim().equals("or")) {
                    /* op, x, y, t */
                    String op = ol[0].trim();
                    String x = ol[1].trim();
                    String y = ol[2].trim();
                    String t = ol[3].trim();

                    print("x: " + x);
                    print("y: " + y);
                    print("t: " + t);
                    String type = var_type.get(x);
                    if (type != null && type.equals("float-list")) {
                        //char tt = memory.get(t).equals("val")? 't' : 'a';
                        loadToRegister(t, 'f');
                        //naive += "   lw " + used_registers.get(t) + "," + Integer.toString(getPosition(t) * 4) + "($sp)\n";
                        if (data.containsKey(x)) {
                            if (isNumeric(y) || data.containsKey(y)) { // add, input, 3.0, t
                                if (data.containsKey(y)) {
                                    y = data.get(y);
                                }
                                if (op.equals("add") || op.equals("sub") || op.equals("mult") || op.equals("div")) {
                                    op += ".d";
                                }
                                loadToRegister(x, 'f');
                                naive += "   ldc1 " + used_registers.get(x) + "," + Integer.toString(getPosition(x) * 4) + "($sp)\n";
                                naive += "   " + op + used_registers.get(x) + "," + y + "," + used_registers.get(t) + "\n";
                                storeToMemory(x, 'f');
                            } else { // add, input, y, t
                                char yt = memory.get(y).equals("val") ? 'f' : 'a';
                                loadToRegister(y, yt);
                                loadToRegister(x, 'f');
                                naive += "   ldc1 " + used_registers.get(y) + "," + Integer.toString(getPosition(y) * 4) + "($sp)\n";
                                naive += "   ldc1 " + used_registers.get(x) + "," + Integer.toString(getPosition(x) * 4) + "($sp)\n";
                                naive += "   " + op + " " + used_registers.get(x) + "," + used_registers.get(y) + "," + used_registers.get(t) + "\n";
                                storeToMemory(y, yt);
                                storeToMemory(x, 'f');
                            }
                        } else {
                            char xt = memory.get(x).equals("val") ? 'f' : 'a';

                            if (isNumeric(y) || data.containsKey(y)) { // add, x, 2.0, t
                                if (data.containsKey(y)) {
                                    y = data.get(y);
                                }
                                loadToRegister(x, xt);
                                naive += "   ldc1 " + used_registers.get(x) + "," + Integer.toString(getPosition(x) * 4) + "($sp)\n";
                                naive += "   " + op + " " + used_registers.get(x) + "," + y + "," + used_registers.get(t) + "\n";
                                storeToMemory(x, xt);
                            } else { // add, x, y, t
                                char yt = memory.get(y).equals("val") ? 'f' : 'a';
                                loadToRegister(x, xt);
                                loadToRegister(y, yt);
                                naive += "   ldc1 " + used_registers.get(x) + "," + Integer.toString(getPosition(x) * 4) + "($sp)\n";
                                naive += "   ldc1 " + used_registers.get(y) + "," + Integer.toString(getPosition(y) * 4) + "($sp)\n";
                                naive += "   " + op + " " + used_registers.get(x) + "," + used_registers.get(y) + "," + used_registers.get(t) + "\n";
                                storeToMemory(x, xt);
                                storeToMemory(y, yt);
                            }
                        }
                        naive += "   sdc1 " + used_registers.get(t) + "," + Integer.toString(getPosition(t) * 4) + "($sp)\n";
                        storeToMemory(t, 't');
                        //naive += "   " + newline.trim() + "\n";
                        return naive;
                    }
                    if (type != null && type.equals("int-list")) {
                        //char tt = memory.get(t).equals("val")? 't' : 'a';
                        loadToRegister(t, 't');
                        //naive += "   lw " + used_registers.get(t) + "," + Integer.toString(getPosition(t) * 4) + "($sp)\n";
                        if (data.containsKey(x)) {
                            if (isNumeric(y) || data.containsKey(y)) { // add, input, 3, t
                                if (data.containsKey(y)) {
                                    y = data.get(y);
                                }
                                if (op.equals("add") || op.equals("and") || op.equals("or") || op.equals("sub")) {
                                    loadToRegister(x, 't');
                                    naive += "   lw " + used_registers.get(x) + "," + Integer.toString(getPosition(x) * 4) + "($sp)\n";
                                    naive += "   " + op + "i " + used_registers.get(x) + "," + y + "," + used_registers.get(t) + "\n";
                                    if (!x.equals(t)) { storeToMemory(x, 't'); }
                                } else {
                                    loadToRegister(x, 't');
                                    naive += "   lw " + used_registers.get(x) + "," + Integer.toString(getPosition(x) * 4) + "($sp)\n";
                                    naive += "   " + op + used_registers.get(x) + "," + y + "," + used_registers.get(t) + "\n";
                                    if (!x.equals(t)) { storeToMemory(x, 't'); }
                                }
                            } else { // add, input, y, t
                                char yt = memory.get(y).equals("val") ? 't' : 'a';
                                loadToRegister(y, yt);
                                loadToRegister(x, 't');
                                naive += "   lw " + used_registers.get(y) + "," + Integer.toString(getPosition(y) * 4) + "($sp)\n";
                                naive += "   lw " + used_registers.get(x) + "," + x + "\n";
                                naive += "   " + op + " " + used_registers.get(x) + "," + used_registers.get(y) + "," + used_registers.get(t) + "\n";
                                storeToMemory(y, yt);
                                storeToMemory(x, 't');
                            }
                        } else {
                            char xt = memory.get(x).equals("val") ? 't' : 'a';

                            if (isNumeric(y) || data.containsKey(y)) { // add, x, 2, t
                                if (data.containsKey(y)) {
                                    y = data.get(y);
                                }
                                loadToRegister(x, xt);
                                naive += "   lw " + used_registers.get(x) + "," + Integer.toString(getPosition(x) * 4) + "($sp)\n";
                                naive += "   " + op + " " + used_registers.get(x) + "," + y + "," + used_registers.get(t) + "\n";
                                storeToMemory(x, xt);
                            } else { // add, x, y, t
                                char yt = memory.get(y).equals("val") ? 't' : 'a';
                                loadToRegister(x, xt);
                                loadToRegister(y, xt);
                                naive += "   lw " + used_registers.get(x) + "," + Integer.toString(getPosition(x) * 4) + "($sp)\n";
                                naive += "   lw " + used_registers.get(y) + "," + Integer.toString(getPosition(y) * 4) + "($sp)\n";
                                naive += "   " + op + " " + used_registers.get(x) + "," + used_registers.get(y) + "," + used_registers.get(t) + "\n";
                                storeToMemory(x, xt);
                                storeToMemory(y, yt);
                            }
                        }
                        naive += "   sw " + used_registers.get(t) + "," + Integer.toString(getPosition(t) * 4) + "($sp)\n";
                        storeToMemory(t, 't');
                        return naive;
                    }
                }

                /* breq, brneq, brlt, brgt, brgeq, brleq */
                if (ol[0].trim().equals("breq") ||
                        ol[0].trim().equals("brneq") ||
                        ol[0].trim().equals("brlt") ||
                        ol[0].trim().equals("brgt") ||
                        ol[0].trim().equals("brgeq") ||
                        ol[0].trim().equals("brleq")) {
                    /* op, x, y, label */
                    String op = ol[0].trim();
                    String x = ol[1].trim();
                    String y = ol[2].trim();
                    String label = ol[3].trim();

                    String type = var_type.get(x);
                    if (type != null && type.equals("float-list")) {
                        if (data.containsKey(x)) {
                            /**
                             * breq, x, y, label -> c.eq.d x, y, label
                             * brneq, x, y, label ->
                             * brlt, x, y, label -> c.lt.d x, y, label
                             * brleq, x, y, label -> c.le.d x, y, label
                             * brgt, x, y, label -> c.le.d y, x, label
                             * brgeq, x, y, label -> c.lt y, x, label
                             */
                            if (op.equals("breq")) { op = "c.eq.d"; }
                            if (op.equals("brlt")) { op = "c.lt.d"; }
                            if (op.equals("brleq")) { op = "c.le.d"; }

                            if (isNumeric(y) || data.containsKey(y)) { // c.eq.d input, 3.0, label
                                if (data.containsKey(y)) {
                                    y = data.get(y);
                                }
                                loadToRegister(x, 'f');
                                naive += "   ldc1 " + used_registers.get(x) + "," + Integer.toString(getPosition(x) * 4) + "($sp)\n";

                                if (op.equals("brgt")) {
                                    op = "c.le.d";
                                    naive += "   " + op + " " + y + "," + used_registers.get(x) + "," + label + "\n";
                                } else if (op.equals("brgeq")) {
                                    op = "c.lt.d";
                                    naive += "   " + op + " " + y + "," + used_registers.get(x) + "," + label + "\n";
                                } else {
                                    naive += "   " + op + " " + used_registers.get(x) + "," + y + "," + label + "\n";
                                }
                                storeToMemory(x, 'f');
                            }
                        } else {
                            if (isNumeric(y) || data.containsKey(y)) { // c.eq.d x, 2.0, label
                                if (data.containsKey(y)) {
                                    y = data.get(y);
                                }
                                char xt = memory.get(x).equals("val") ? 'f' : 'a';
                                loadToRegister(x, xt);
                                naive += "   ldc1 " + used_registers.get(x) + "," + Integer.toString(getPosition(x) * 4) + "($sp)\n";
                                naive += "   " + op + " " + used_registers.get(x) + "," + y + "," + label + "\n";
                                storeToMemory(x, xt);
                            } else { // bne, x, y, label
                                char xt = memory.get(x).equals("val") ? 'f' : 'a';
                                char yt = memory.get(y).equals("val") ? 'f' : 'a';
                                loadToRegister(x, xt);
                                loadToRegister(y, yt);
                                naive += "   ldc1 " + used_registers.get(x) + "," + Integer.toString(getPosition(x) * 4) + "($sp)\n";
                                naive += "   ldc1 " + used_registers.get(y) + "," + Integer.toString(getPosition(y) * 4) + "($sp)\n";
                                naive += "   " + op + " " + used_registers.get(x) + "," + used_registers.get(y) + "," + label + "\n";
                                storeToMemory(x, xt);
                                storeToMemory(y, yt);
                            }
                        }
                        //naive += "   " + newline.trim() + "\n";
                        return naive;
                    }
                    if (type != null && type.equals("int-list")) {
                        if (data.containsKey(x)) {
                            if (isNumeric(y) || data.containsKey(y)) { // bne, input, 3, label
                                if (data.containsKey(y)) {
                                    y = data.get(y);
                                }
                                loadToRegister(x, 't');
                                naive += "   lw " + used_registers.get(x) + "," + Integer.toString(getPosition(x) * 4) + "($sp)\n";
                                naive += "   " + op + " " + used_registers.get(x) + "," + y + "," + label + "\n";
                            }
                        } else {
                            if (isNumeric(y) || data.containsKey(y)) { // bne, x, 2, label
                                if (data.containsKey(y)) {
                                    y = data.get(y);
                                }
                                char xt = memory.get(x).equals("val") ? 't' : 'a';
                                loadToRegister(x, xt);
                                naive += "   lw " + used_registers.get(x) + "," + Integer.toString(getPosition(x) * 4) + "($sp)\n";
                                naive += "   " + op + " " + used_registers.get(x) + "," + y + "," + label + "\n";
                                storeToMemory(x, xt);
                            } else { // bne, x, y, label
                                char xt = memory.get(x).equals("val") ? 't' : 'a';
                                char yt = memory.get(y).equals("val") ? 't' : 'a';
                                loadToRegister(x, xt);
                                loadToRegister(y, yt);
                                naive += "   lw " + used_registers.get(x) + "," + Integer.toString(getPosition(x) * 4) + "($sp)\n";
                                naive += "   lw " + used_registers.get(y) + "," + Integer.toString(getPosition(y) * 4) + "($sp)\n";
                                naive += "   " + op + " " + used_registers.get(x) + "," + used_registers.get(y) + "," + label + "\n";
                                storeToMemory(x, xt);
                                storeToMemory(y, yt);
                            }
                        }
                        //naive += "   " + newline.trim() + "\n";
                        return naive;
                    }
                }

                /* goto */
                if (ol[0].trim().equals("goto")) {
                    /* op, label, _, _ */
                    String op = ol[0].trim();
                    String label = ol[1].trim();
                    naive += "   " + op + " " + label + "\n";
                    return naive;
                }

                /* return */
                if (ol[0].trim().equals("return")) {
                    /* op, x, _, _ */
                    String x = "";
                    if (ol.length > 1) {
                        x = ol[1].trim();
                    }
                    naive += "   lw $ra," + Integer.toString(getPosition("ra") * 4) + "($sp)\n";
                    if (!x.equals("")) {
                        naive += "   lw $v0," + Integer.toString(getPosition(x) * 4) + "($sp)\n";
                    }
                    naive += "   addiu $sp,$sp," + Integer.toString(memory.size() * 4) + "\n";
                    naive += "   jr $ra\n";
                    //naive += "   " + newline.trim() + "\n";
                    return naive;
                }

                /* call */
                if (ol[0].trim().equals("call")) {
                    //print("data" + data);
                    //print("memory" + memory);
                    //print("used_registers: " + used_registers);
                    /* op, func_name, param1, param2, ..., paramn */
                    String func_name = ol[1].trim();
                    if (func_name.equals("printi")) {
                        String param = ol[2].trim();
                        if (isNumeric(param)) {
                            if (param.contains(".")) { // call, printi, 5.0
                                naive += "   ldc1 $f12," + data.get(param) + "\n";
                                naive += "   li $v0,3\n";
                                naive += "   syscall\n";
                            } else { // call, printi, 5
                                naive += "   li $a0," + param + "\n";
                                naive += "   li $v0,1\n";
                                naive += "   syscall\n";
                            }
                        } else {
                            if (var_type.get(param).equals("float-list")) { // call, printi, float_param
                                if (memory.get(param).equals("par")) {
                                    naive += "   mtc1 $a0,$f12\n";
                                } else {
                                    if (!data.containsKey(param)) {
                                        naive += "   ld $a0,$f12\n";
                                    } else { // call, printi, float_input
                                        naive += "   ld $a0," + param + "\n";
                                    }
                                    naive += "   mtc1.d $a0,$f12\n";
                                }
                                naive += "   li $v0,3\n";
                                naive += "   syscall\n";
                            } else {
                                if (memory.get(param).equals("par")) {
                                    naive += "   sw $a0," + Integer.toString(getPosition(param) * 4) + "($sp)\n";
                                }
                                if (!data.containsKey(param)) {
                                    naive += "   lw $a0," + Integer.toString(getPosition(param) * 4) + "($sp)\n";
                                } else { // call, printi, input
                                    naive += "   lw $a0," + param + "\n";
                                }
                                naive += "   li $v0,1\n";
                                naive += "   syscall\n";
                            }
                        }
                    } else {
                        String param = ol[2].trim();
                        if (param.contains(".")) {
                            if (isNumeric(param)) { // call, print, 5.5
                                naive += "   ldc1 $f12," + data.get(param) + "\n";
                                naive += "   mfc1 $a0,$f12\n";
                                naive += "   jal " + func_name + "\n";
                            } else { // call, func, param
                                //loadToRegister(param, 'a');
                                if (memory.get(param).equals("par")) {
                                    naive += "   sw $a0," + Integer.toString(getPosition(param) * 4) + "($sp)\n";
                                }
                                if (!data.containsKey(param)) {
                                    naive += "   ld $a0," + Integer.toString(getPosition(param) * 4) + "($sp)\n";
                                } else {
                                    naive += "   ld $a0," + param + "\n";
                                }
                                naive += "   jal " + func_name + "\n";
                            }
                        }
                        else {
                            if (isNumeric(param)) { // call, print, 5
                                naive += "   li $a0," + param + "\n";
                                naive += "   jal " + func_name + "\n";
                            } else { // call, func, param
                                //loadToRegister(param, 'a');
                                if (memory.get(param).equals("par")) {
                                    naive += "   sw $a0," + Integer.toString(getPosition(param) * 4) + "($sp)\n";
                                }
                                if (!data.containsKey(param)) {
                                    naive += "   lw $a0," + Integer.toString(getPosition(param) * 4) + "($sp)\n";
                                } else {
                                    naive += "   lw $a0," + param + "\n";
                                }
                                naive += "   jal " + func_name + "\n";
                            }
                        }
                    }
                    return naive;
                }

                /* callr */
                if (ol[0].trim().equals("callr")) {
                    /* op, x, func_name, param1, param2, ..., paramn */

                    //naive += "   " + newline.trim() + "\n";
                    String op = ol[0].trim();
                    String x = ol[1].trim();
                    String f = ol[2].trim();
                    String p = ol[3].trim();
                    if (p.contains(".")) {
                        naive += "   sw $a0," + Integer.toString(getPosition("tmp") * 4) + "($sp)\n";
                        naive += "   ld $a0," + Integer.toString(getPosition(p) * 4) + "($sp)\n";
                        naive += "   jal " + f + "\n";
                        naive += "   sw $v0," + Integer.toString(getPosition(x) * 4) + "($sp)\n";
                        naive += "   ld $a0," + Integer.toString(getPosition("tmp") * 4) + "($sp)\n";
                        return naive;
                    } else {
                        naive += "   sw $a0," + Integer.toString(getPosition("tmp") * 4) + "($sp)\n";
                        naive += "   lw $a0," + Integer.toString(getPosition(p) * 4) + "($sp)\n";
                        naive += "   jal " + f + "\n";
                        naive += "   sw $v0," + Integer.toString(getPosition(x) * 4) + "($sp)\n";
                        naive += "   lw $a0," + Integer.toString(getPosition("tmp") * 4) + "($sp)\n";
                        return naive;
                    }
                }

                /* array_store */
                if (ol[0].trim().equals("array_store")) { // array_name[offset] = x
                    /* op, array_name, offset, x */
                    String array_name = ol[1].trim();
                    String offset = ol[2].trim();
                    if (data.containsKey(offset)) { offset = data.get(offset); }
                    String x = ol[3].trim();
                    print("array_name: " + array_name);
                    print("x: " + x);
                    print("offset: " + offset);
                    print("data: " + data);
                    print("memory: " + memory);
                    print("nl: " + newline);
                    loadToRegister(array_name, 't');
                    loadToRegister(x, 't');
                    if (isNumeric(offset)) {
                        naive += "   la " + used_registers.get(array_name) + "," + array_name + "\n";
                        naive += "   addi " + used_registers.get(array_name) + "," + Integer.toString(Integer.parseInt(offset) * 4) + "," + used_registers.get(array_name) + "\n";
                        naive += "   sw " + used_registers.get(x) + ",0(" + used_registers.get(array_name) + ")\n";
                    } else {
                        loadToRegister(offset, 's');
                        naive += "   lw " + used_registers.get(offset) + "," + Integer.toString(getPosition(offset) * 4) + "($sp)\n";
                        naive += "   la " + used_registers.get(array_name) + "," + array_name + "\n";
                        naive += "   add " + used_registers.get(offset) + "," + used_registers.get(offset) + "," + used_registers.get(offset) + "\n";
                        naive += "   add " + used_registers.get(offset) + "," + used_registers.get(offset) + "," + used_registers.get(offset) + "\n";
                        naive += "   add " + used_registers.get(array_name) + "," + used_registers.get(offset) + "," + used_registers.get(array_name) + "\n";
                        naive += "   sw " + used_registers.get(x) + ",0(" + used_registers.get(array_name) + ")\n";
                        storeToMemory(offset, 's');
                    }
                    storeToMemory(array_name, 't');
                    storeToMemory(x, 't');
                    return naive;
                }

                /* array_load */
                if (ol[0].trim().equals("array_load")) { // x = array_name[offset]
                    /* op, x, array_name, offset */
                    String array_name = ol[2].trim();
                    String offset = ol[3].trim();
                    String x = ol[1].trim();
                    print("array_name: " + array_name);
                    print("x: " + x);
                    print("offset: " + offset);
                    print("data: " + data);
                    print("memory: " + memory);
                    print("nl: " + newline);
                    loadToRegister(array_name, 't');
                    loadToRegister(x, 't');
                    if (isNumeric(offset)) {
                        naive += "   la " + used_registers.get(array_name) + "," + array_name + "\n";
                        naive += "   addi " + used_registers.get(array_name) + "," + Integer.toString(Integer.parseInt(offset) * 4) + "," + used_registers.get(array_name) + "\n";
                        naive += "   lw " + used_registers.get(x) + ",0(" + used_registers.get(array_name) + ")\n";
                    } else {
                        loadToRegister(offset, 's');
                        naive += "   lw " + used_registers.get(offset) + "," + Integer.toString(getPosition(offset) * 4) + "($sp)\n";
                        naive += "   la " + used_registers.get(array_name) + "," + array_name + "\n";
                        naive += "   add " + used_registers.get(offset) + "," + used_registers.get(offset) + "," + used_registers.get(offset) + "\n";
                        naive += "   add " + used_registers.get(offset) + "," + used_registers.get(offset) + "," + used_registers.get(offset) + "\n";
                        naive += "   add " + used_registers.get(array_name) + "," + used_registers.get(offset) + "," + used_registers.get(array_name) + "\n";
                        naive += "   lw " + used_registers.get(x) + ",0(" + used_registers.get(array_name) + ")\n";
                        naive += "   sw " + used_registers.get(x) + "," + Integer.toString(getPosition(x) * 4) + "($sp)\n";
                        storeToMemory(offset, 's');
                    }
                    storeToMemory(array_name, 't');
                    storeToMemory(x, 't');
                    return naive;
                }
                break;
            case 1:
                return translateIBB();
            default:
                /* global */
                return naive;
        }
        return naive;
    }

    private ArrayList<String> removeNumericsAndBlanks(ArrayList<String> l) {
        ArrayList<String> newList = new ArrayList<String>();
        for (String s: l) {
            if (!isNumeric(s) && !s.trim().equals("")) {
                newList.add(s.trim());
            }
        }

        return newList;
    }

    public VariableSet getVariables() {
        ArrayList<String> ol = new ArrayList<String>(Arrays.asList(newline.split(",")));

        VariableSet varSet = new VariableSet();
        switch(ol.get(0).trim()) {
            case "assign":
                if (ol.size() > 3 && !ol.get(3).trim().equals("")) {
                    varSet.used = new ArrayList<String>();
                    return varSet;
                } else {
                    varSet.used = removeNumericsAndBlanks(new ArrayList<String>(ol.subList(2,3)));
                    varSet.assigned = ol.get(1).trim();

                    return varSet;
                }
            case "breq":
            case "brneq":
            case "brlt":
            case "brgt":
            case "brgeq":
            case "brleq":
                varSet.used = removeNumericsAndBlanks(new ArrayList<String>(ol.subList(1,3)));
                return varSet;
            case "add":
            case "sub":
            case "mult":
            case "and":
            case "or":
                varSet.used = removeNumericsAndBlanks(new ArrayList<String>(ol.subList(1, 3)));
                varSet.assigned = ol.get(3).trim();
                return varSet;

            case "return":
                if (ol.size() > 1) {
                    varSet.used = removeNumericsAndBlanks(new ArrayList<String>(ol.subList(1, 2)));
                } else {
                    varSet.used = new ArrayList<String>();
                }
                return varSet;
            case "call":
                varSet.used = removeNumericsAndBlanks(new ArrayList<String>(ol.subList(2, ol.size())));
                return varSet;
            case "callr":
                varSet.used = removeNumericsAndBlanks(new ArrayList<String>(ol.subList(3, ol.size())));
                varSet.assigned = ol.get(1).trim();
                return varSet;
            case "array_store":
                varSet.used = removeNumericsAndBlanks(new ArrayList<String>(ol.subList(2, ol.size())));
                return varSet;
            case "array_load":
                varSet.used = removeNumericsAndBlanks(new ArrayList<String>(ol.subList(3, ol.size())));
                varSet.assigned = ol.get(1).trim();
                return varSet;
            default:
                varSet.used = new ArrayList<String>();
                return varSet;
        }
    }
}
