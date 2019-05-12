import java.util.*;

public class EBBWeb {
    public static HashMap<String, HashSet<String>> web = new HashMap<String, HashSet<String>>();

    public static void addToWeb(HashSet<String> vars) {
        for (String var : vars) {
            if (!web.keySet().contains(var)) {
                web.put(var, new HashSet<String>());
            }

            HashSet<String> cloned = (HashSet<String>)vars.clone();
            cloned.remove(var);
            for (String other : cloned) {
                web.get(var).add(other);
            }
        }
    }

    public static void reset() {
        web = new HashMap<String, HashSet<String>>();
    }

}
