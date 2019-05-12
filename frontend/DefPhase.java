/**
 * Excerpted and modified from "The Definitive ANTLR 4 Reference",
 **/

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.*;
import java.util.*;

public class DefPhase extends tigerBaseListener {

    ParseTreeProperty<Scope> scopes = new ParseTreeProperty<>();   // variable table
    GlobalScope globals;
    Scope currentScope; // define symbols in this scope

    boolean debug = false;

    // enter Program
    public void enterProgram(tigerParser.ProgramContext ctx){
        globals = new GlobalScope(null);
        currentScope = globals;
    }

    // enter scope: let
    public void enterLET(tigerParser.LETContext ctx){
        // push new local scope
        // make the parameter in the decs
        currentScope = new LocalScope(currentScope);
        saveScope(ctx, currentScope);
    }

    public void exitLET(tigerParser.LETContext ctx){
        currentScope = currentScope.getEnclosingScope();
    }

    // enter scope: function declaration
    public void enterFuncDec(tigerParser.FuncDecContext ctx){
        // formal parameter
        print("Entering FuncDec " + currentScope);
        currentScope = new LocalScope(currentScope);
        saveScope(ctx, currentScope);
    }
    public void exitFuncDec(tigerParser.FuncDecContext ctx){
        print("Exiting FuncDec " + currentScope);
        currentScope = currentScope.getEnclosingScope();
    }

    // enter scope: for statement
    public void enterForStmt(tigerParser.ForStmtContext ctx){
        print("Entering ForStmt" + currentScope);
        currentScope = new LocalScope(currentScope);
        saveScope(ctx, currentScope);
    }

    public void exitForStmt(tigerParser.ForStmtContext ctx){
        print("Exiting ForStmt" + currentScope);
        currentScope = currentScope.getEnclosingScope();
    }

    // enter scope: while statement
    public void enterWhileStmt(tigerParser.WhileStmtContext ctx) {
        print("Entering WhileStmt" + currentScope);
        currentScope = new LocalScope(currentScope);
    }

    public void exitWhileStmt(tigerParser.WhileStmtContext ctx){
        print("Exiting ForStmt" + currentScope);
        currentScope = currentScope.getEnclosingScope();
    }

    void saveScope(ParserRuleContext ctx, Scope s) {
        scopes.put(ctx, s);
    }

    private void print(Object o){
        if (debug) {
            System.out.println(o);
        }
    }
}
