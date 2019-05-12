import java.util.*;

// class Range {
//     public int start;
//     public int end;
//
//     @Override
//     public String toString() {
//         return Integer.toString(start) + " - " + Integer.toString(end);
//     }
// }

// class LiveRange {
//     public ArrayList<Range> range;
//
//     public LiveRange() {
//         this.range = new ArrayList<Range>();
//     }
//
//     public void end(int end) {
//         Range r = new Range();
//         r.end = end;
//
//         this.range.add(0, r);
//     }
//
//     public void start(int start) {
//         this.range.get(0).start = start;
//     }
//
//     @Override
//     public String toString() {
//         return this.range.toString();
//     }
//
// }
//


public class BasicBlock {
    public ArrayList<IRInstruction> ir; // List of instructions in the basic block.

    public int blockNumber; // Number all of the blocks - mostly for printing and debugging.

    public String label; // Label for the block if the block is a target of a branch. Null otherwise.

    public String target; // Target of the branch statement at the end of the block if not return. Null otherwise.

    public boolean endsWithReturnOrGoto = false; // Set to true if ends with return or goto. Used for creating CFG.

    public HashSet<String> variables; // Names of variables in this basic block.

    public HashMap<String, Integer> usageCounts; // Number of occurrences for variables in this block.

    public HashSet<String> liveIn; // Live variables coming into basic block.

    public HashSet<String> liveOut; // Outgoing live variables.

    public String modifiedIR;

    public HashMap<String, String> data;

    public BasicBlock(int blockNumber) {
        this.ir = new ArrayList<IRInstruction>();
        this.blockNumber = blockNumber;

        this.liveOut = new HashSet<String>();
        this.liveIn = new HashSet<String>();

        this.modifiedIR = "";
    }

    public void getVariables() {
        this.variables = new HashSet<String>();

        for(IRInstruction ins: this.ir) {
            VariableSet vars = ins.getVariables();
            for (String var: vars.used) {
                this.variables.add(var);
            }
            if (vars.assigned != null) {
                this.variables.add(vars.assigned);
            }
        }

        // Can be used to print variables.
        // System.out.println("Block: " + Integer.toString(this.blockNumber));
        // System.out.println("Variables: ");
        // System.out.println(this.variables);
        // System.out.println("---------------");

    }

    public HashSet<String> livenessAnalysis(HashSet<String> outLive) {

        HashSet<String> unionSet = (HashSet<String>)outLive.clone();

        Iterator<String> it = this.liveOut.iterator();
        while (it.hasNext()) {
            unionSet.add(it.next());
        }

        this.liveOut = unionSet;

        HashSet<String> previousIn = (HashSet<String>)outLive.clone();

        for (int i = this.ir.size() - 1; i >= 0; i--) {
            EBBWeb.addToWeb(previousIn);
            this.ir.get(i).liveOut = previousIn;

            HashSet<String> in = (HashSet<String>)previousIn.clone();

            VariableSet vars = this.ir.get(i).getVariables();
            for (String var: vars.used) {
                in.add(var);
            }

            if (vars.assigned != null) {
                in.remove(vars.assigned);
            }


            this.ir.get(i).liveIn = (HashSet<String>)in.clone();
            previousIn = in;
        }

        this.liveIn = this.ir.get(0).liveIn;

        return this.liveIn;
    }

    public void computeUsageCounts() {

        ArrayList<String> usages = new ArrayList<String>();

        for (IRInstruction ins: this.ir) {
            VariableSet vars = ins.getVariables();
            for (String var: vars.used) {
                usages.add(var);
            }
        }

        Iterator<String> it = this.variables.iterator();

        usageCounts = new HashMap<String, Integer>();
        while(it.hasNext()){
            String var = it.next();

            Integer count = 0;
            for (String val: usages) {
                if (val.equals(var)) {
                    count ++;
                }
            }

            usageCounts.put(var, count);
        }
        // Can be used to print the usage counts.
        // System.out.println("Counts: ");
        // System.out.println(this.usageCounts);
        // System.out.println("---------------");
    }

    public void addStatement(String statement, int number) {
        this.ir.add(new IRInstruction(statement, number));
    }

    public void print() {
        // Print the block and its instructions.
        System.out.println("Block " + Integer.toString(this.blockNumber));
        System.out.println("--------------------");
        System.out.println("Label: " + this.label);
        for(IRInstruction ins: this.ir) {
            System.out.println("(" + Integer.toString(ins.number) + ") " + ins.newline);
        }
        System.out.println("Target: " + this.target);
        System.out.println("--------------------");
    }


    // Allocation

    // function to sort hashmap by values
   public ArrayList<String> sortVariablesByCount(HashMap<String, Integer> hm)
   {
       // Create a list from elements of HashMap
       List<Map.Entry<String, Integer> > list =
              new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

       // Sort the list
       Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
           public int compare(Map.Entry<String, Integer> o1,
                              Map.Entry<String, Integer> o2)
           {
               return (o2.getValue()).compareTo(o1.getValue());
           }
       });

       // put data from sorted list to hashmap
       ArrayList<String> temp = new ArrayList<String>();
       for (Map.Entry<String, Integer> aa : list) {
           temp.add(aa.getKey());
       }
       return temp;
   }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }

    private boolean isLabel(String statement) {
        if (statement.endsWith(":")) {
            return true;
        } else {
            return false;
        }
    }

    public void allocateRegistersIBB() {
        this.getVariables();
        this.computeUsageCounts();

        // Sort counts and assign the registers.
        ArrayList<String> sortedVars = this.sortVariablesByCount(this.usageCounts);

        ArrayList<String> prioritizedVars = new ArrayList();
        if (sortedVars.size() <= 7) {
            prioritizedVars = sortedVars;
        } else {
            prioritizedVars = new ArrayList<String>(sortedVars.subList(0, 7));
        }

        HashSet<String> prioritizedSet = new HashSet(prioritizedVars);

        if (this.label != null) {
            this.modifiedIR += this.label + ":\n";
        }
        // Loop through IR and translate.
        int i;
        IRInstruction ins = null;
        for (i = 0; i < this.ir.size(); i++) {
            ins = this.ir.get(i);
            ins.selectMode(1);
            ins.setData(this.data);
            ins.prioritizedSet = prioritizedSet;
            this.modifiedIR += ins.translate();
        }
        if (ins != null && this.target == null && !ins.newline.trim().startsWith("return")) {
            System.out.println("Test: " + ins.newline);
            this.modifiedIR += ins.storeAllRegistersToMemory();
            ins.clearUsedRegisters();
        }
        // Store live variable registers to memory.
    }
}
