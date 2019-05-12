/**
 * Excerpted and modified from "The Definitive ANTLR 4 Reference",
 **/

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;

public class CheckSymbols {
    public static boolean succcessfulCompile = true;
    public static void error(Token t, String msg) {
        System.err.printf("line %d:%d %s\n", t.getLine(), t.getCharPositionInLine(),
                msg);
    }

    public ParseTree process(String[] args) throws Exception {
        String inputFile = null;
        if (args.length > 0) inputFile = args[0];
        InputStream is = System.in;
        if (inputFile != null) {
            is = new FileInputStream(inputFile);
        }
        CharStream input = CharStreams.fromStream(is);
        tigerLexer lexer = new tigerLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tigerParser parser = new tigerParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.program();

        // show tree in text form
        System.out.println("Sequence of tokens: ");
        System.out.println(tree.toStringTree(parser));
        System.out.println("====================================");
        //if (parser.errorHappens) {
        //    System.err.println("Syntactic error.");
        //    succcessfulCompile = false;
        //}
        System.out.println("Syntactic analysis finished");
        // Semantic analysis
        System.out.println("Semantic Checking...");
        ParseTreeWalker walker = new ParseTreeWalker();

        // 1 st traversal: build map<scope, node>
        DefPhase def = new DefPhase();
        walker.walk(def, tree);

        // 2 nd traversal:
        // create next phase and feed symbol table info from def to ref phase
        RefPhase ref = new RefPhase(def.globals, def.scopes);
        ref.symbolTable = true;
        walker.walk(ref, tree);
        return tree;
    }

    public static void main(String[] args) throws Exception {

        ParseTree tree = new CheckSymbols().process(args);
        System.out.println();
        //if (succcessfulCompile) {
        //    System.out.println("Successful compile, generating IR code...");
        //    new IRCodeGeneration().run(tree);
        //} else {
        //    System.out.println("Successful compile, generating IR code...");
        //}
    }
}