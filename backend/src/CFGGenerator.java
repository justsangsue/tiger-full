import java.util.*;

public class CFGGenerator {

    private ArrayList<BasicBlock> basicBlocks;

    public CFGGenerator(ArrayList<BasicBlock> basicBlocks) {
        this.basicBlocks = basicBlocks;
    }

    private CFGNode findTarget(String target, ArrayList<CFGNode> nodes) {
        // Finds the target of a branch based on its label.
        for (CFGNode n: nodes) {
            if (n.block.label != null && n.block.label.trim().equals(target.trim())) {
                return n;
            }
        }

        return null;
    }

    public CFGNode generate() {

        ArrayList<CFGNode> nodes = new ArrayList<CFGNode>();

        // Transform list of basic blocks into lists of CFGNodes.
        for (BasicBlock b: this.basicBlocks) {
            CFGNode node = new CFGNode();
            node.block = b;
            nodes.add(node);
        }

        // Loop through nodes and connect them together based on targets and labels and fall throughs.
        int i = 0;
        for (CFGNode n: nodes) {
            if (!n.block.endsWithReturnOrGoto) {
                n.left = nodes.get(i+1);
            }

            if (n.block.target != null) {
                n.right = this.findTarget(n.block.target, nodes);
            }

            i++;
        }

        // Returnt he root which can be traversed.
        return nodes.get(0);

    }
}
