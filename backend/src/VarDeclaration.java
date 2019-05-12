public class VarDeclaration extends IRHandler {
    /**
     * Initialize variables in memory
     */
    public VarDeclaration(String nl) {
        super(nl);
    }

    @Override
    public String translate() {
        String type = newline.substring(0, newline.indexOf(':'));
        String[] vars = newline.substring(newline.indexOf(':') + 1).split(",");
        for (String var : vars) {
            var = var.trim();
            if (var.contains("[")) {
                /* Array */
                String name = var.substring(0, var.indexOf('['));
                String space = var.substring(var.indexOf('[') + 1, var.indexOf(']'));
                naive += name + ": .space " + space + "\n";
                addToMemory(name, 't');

                /* Record variable type */
                var_type.put(name, type + "-array");
            } else if (!var.equals("") && type.equals("int-list")) {
                /* int-list */
                naive += var + ": .word \n";

                /* Record memory use */
                addToMemory(var, 'v');

                /* Record variable type */
                var_type.put(var, type);
            } else if (!var.equals("") && type.equals("float-list")) {
                naive += var + ": .float \n";
                addToMemory(var, 'v');
                var_type.put(var, type);
            }
            //print("in translate(): ");
            //print(var_type);
        }
        //print(naive);
        //print(Arrays.toString(memory.toArray()));
        //print(Arrays.asList(var_type));
        return naive;
    }
}
