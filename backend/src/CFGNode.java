import java.util.*;

public class CFGNode {
    public CFGNode left; // Points to the next basic block. Null when basic block ends in goto or return.
    public CFGNode right; // Points to the target of a branch. Null if the basic block ends with return or does not end with a branch.

    public BasicBlock block;

    public Boolean visited = false;

    public Boolean visitedLeft = false;

    public Boolean visitedRight = false;

    public static ArrayList<BasicBlock> blocks = new ArrayList<BasicBlock>();

    // public Boolean same = true;

    public HashSet<String> livenessAnalysis() {

        HashSet<String> leftSet = new HashSet<String>();
        HashSet<String> rightSet = new HashSet<String>();

        if (this.left != null && !this.visitedLeft) {
            this.visitedLeft = true;
            leftSet = this.left.livenessAnalysis();
        } else if (this.left == null) {
            this.visitedLeft = true;
        } else if (this.visitedLeft) {
            leftSet = this.left.block.liveIn;
        }

        if (this.right != null && !this.visitedRight) {
            this.visitedRight = true;
            rightSet = this.right.livenessAnalysis();
        } else if (this.right == null) {
            this.visitedRight = true;
        } else if (this.visitedRight) {
            rightSet = this.right.block.liveIn;
        }

        HashSet<String> unionSet = (HashSet<String>)leftSet.clone();

        Iterator<String> it = rightSet.iterator();
        while (it.hasNext()) {
            unionSet.add(it.next());
        }

        return this.block.livenessAnalysis(unionSet);

    }

    public void printGraph() {

        if (!this.visited) {
            this.visited = true;
            // Print the graph recursively.
            System.out.println("Block: " + block.blockNumber);
            if (left != null) {
                System.out.println("Left: " + left.block.blockNumber);
            }

            if (right != null) {
                System.out.println("Right: " + right.block.blockNumber);
            }

            System.out.println("====================");

            if (left != null && !left.visited) {
                left.printGraph();
            }

            if (right != null && !right.visited) {
                right.printGraph();
            }

        }
    }

    public void printLiveAnalysisResults() {
        if (!this.visited) {
            this.visited = true;
            System.out.println("Block: " + Integer.toString(this.block.blockNumber));
            System.out.println("Live In:");
            System.out.println(this.block.liveIn);
            System.out.println("Live Out:");
            System.out.println(this.block.liveOut);

            if (left != null) {
                this.left.printLiveAnalysisResults();
            }
            if (right != null) {
                this.right.printLiveAnalysisResults();
            }
        }
    }

    public void resetTraversal() {

        this.visited = false;
        this.visitedLeft = false;
        this.visitedRight = false;
        // this.same = true;
        if (left != null && left.visited) {
            left.resetTraversal();
        }

        if (right != null && right.visited) {
            right.resetTraversal();
        }

    }


    public void resetBlocks() {
        blocks = new ArrayList<BasicBlock>();
    }

    public void collectBlocks() {
        if (!this.visited) {
            this.visited = true;
            blocks.add(this.block);

            if (this.left != null) {
                this.left.collectBlocks();
            }

            if (this.right != null) {
                this.right.collectBlocks();
            }
        }
    }
}
