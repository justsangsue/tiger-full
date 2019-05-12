import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;
import java.util.ArrayList;

public class IRCodeGeneration {

    public void run(ParseTree tree) throws Exception {
        ArrayList<String> result = new ArrayList<>();
        ParseTreeWalker walker = new ParseTreeWalker();
        DefPhase def = new DefPhase();
        walker.walk(def, tree);

        RefPhase ref = new RefPhase(def.globals, def.scopes);
        ref.symbolTable = false;
        ref.IrCode = true;
        walker.walk(ref, tree);
        result = ref.IrCodeResult;
        for (String line : result) {
            System.out.println(line);
        }
    }
}
