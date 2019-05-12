import java.util.*;

public class IGNode {
    ArrayList<IGNode> children;

    String var;

    IGNode(String var) {
        this.var = var;
        this.children = new ArrayList<IGNode>();
    }
}
