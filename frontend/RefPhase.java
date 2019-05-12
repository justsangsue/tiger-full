import org.antlr.v4.runtime.tree.ParseTreeProperty;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class RefPhase extends tigerBaseListener {
    private ParseTreeProperty<Scope> scopes;
    private GlobalScope globals;
    private Scope currentScope; // resolve symbols starting in this scope

    // figs for debug and symbol table generation
    private boolean debug = false;
    protected boolean symbolTable = true;
    protected boolean IrCode = false;
    protected ArrayList<String> irBufferForFunc = new ArrayList<>();
    protected ArrayList<String> IrCodeResult = new ArrayList<>();
    protected int tempVarNum = 1;

    // number of indentation
    private int indent = 0;

    // improve output
    private Map<String, String> correctedTypeName = new HashMap<String, String>() {
        {
            put("tInt", "int");
            put("tFloat", "float");
            put("tVoid", "void");
        }
    };

    private ParseTreeProperty<Type> typeSys = new ParseTreeProperty<>();
    private Map<String, Type> availableIdsType = new HashMap<>(); // store available id in current scope

    private ArrayList<String> idsBufferFunc = new ArrayList<>();
    private ArrayList<String> idsBufferLet = new ArrayList<>();

    public RefPhase(GlobalScope globals, ParseTreeProperty<Scope> scopes) {
        this.scopes = scopes;
        this.globals = globals;
    }

    // first enter program set globals
    public void enterProgram(tigerParser.ProgramContext ctx){
        currentScope = globals;
        generateSymbolTable("scope " + Integer.toString(indent + 1) + ":", indent);
        //print("Enter program: " + ctx.getRuleContext());
    }

    // let, function, for stmt create new scope
    public void enterLET(tigerParser.LETContext ctx){
        //System.out.println("let text: " + ctx.getText());
        currentScope = scopes.get(ctx); // enter a new scope
        indent += 1;
        generateSymbolTable("scope " + Integer.toString(indent + 1) + ":", indent);
        //print("Enter let: " + typeSys.get(ctx));
    }
    /*
    public void exitLET(TigerParser.LETContext ctx){
    
        currentScope = currentScope.getEnclosingScope(); // exit a scope
    }
    */

    // check variable declaration
    public void exitVarDec(tigerParser.VarDecContext ctx) {
        ArrayList<String> ids = new ArrayList<>();
        Type idsType;
        if (ctx.type().type_id().getText().equals("int")) {
            idsType = Type.tInt;
        } else {
            idsType = Type.tFloat;
        }
        tigerParser.Id_listContext idlctx;
        idlctx = ctx.id_list();
        while (idlctx != null) {
            ids.add(idlctx.id().getText());
            if (indent != 0) {
                idsBufferLet.add(idlctx.id().getText());
            }
            idlctx = idlctx.id_list();
        }
        for (String idname : ids) {
            availableIdsType.put(idname, idsType);
            generateSymbolTable(idname + " ,var, " + correctedTypeName.get(idsType.toString()), indent);
        }
        print("Exit VarDec: " + availableIdsType);
    }

    public void enterFuncDec(tigerParser.FuncDecContext ctx){
        currentScope = scopes.get(ctx);
        indent += 1;
        generateSymbolTable("scope " + Integer.toString(indent + 1) + ":", indent);
        // check function parameters type in function scope
        if (ctx.param_list().param().id() != null) {
            // id : ('int' | 'float')
            // or
            // id : array[n] of ('int' | 'float')
            if (ctx.param_list().param().type().type_id() != null) {
                String typeName = ctx.param_list().param().type().type_id().getText();
                String idName = ctx.param_list().param().id().getText();
                if (typeName.equals("int")) {
                    availableIdsType.put(idName, Type.tInt);
                    idsBufferFunc.add(ctx.param_list().param().id().getText());
                    generateSymbolTable(idName + ", var, " + correctedTypeName.get("tInt"), indent);
                    irBufferForFunc.add(ctx.param_list().param().getText());
                } else {
                    availableIdsType.put(idName, Type.tFloat);
                    idsBufferFunc.add(ctx.param_list().param().id().getText());
                    generateSymbolTable(typeName + ", var, " + correctedTypeName.get("tFloat"), indent);
                    irBufferForFunc.add(ctx.param_list().param().getText());
                }
            }
        }

        // id : id2
        else if (ctx.param_list().param().type().id() != null) {
            String sourceId = ctx.param_list().param().type().id().getText();
            String targetId = ctx.param_list().param().id().getText();
            if (availableIdsType.containsKey(sourceId)) {
                Type assigningType = availableIdsType.get(sourceId);
                availableIdsType.put(targetId, assigningType);
                idsBufferFunc.add(targetId);
                generateSymbolTable(sourceId + ", var, " +
                        correctedTypeName.get(assigningType.toString()), indent);
                irBufferForFunc.add(ctx.param_list().param().getText());
            } else {
                print("Current id-type: " + availableIdsType);
                CheckSymbols.error(ctx.getStart(), sourceId + " is not defined");
                CheckSymbols.succcessfulCompile = false;
                availableIdsType.put(targetId, Type.tVoid);
                idsBufferFunc.add(targetId);
                generateSymbolTable(sourceId + ", var, " +
                        correctedTypeName.get("tVoid"), indent);
            }
        }
        //print("Enter FuncDec: " + typeSys.get(ctx));
    }

    public void exitFuncDec(tigerParser.FuncDecContext ctx) {
        currentScope = currentScope.getEnclosingScope();
        indent -= 1;
        // function type in enclosing scope
        switch (ctx.ret_type().getText()) {
            case "int":
                availableIdsType.put(ctx.id().getText(), Type.tInt);
                generateSymbolTable(ctx.id().getText() + ", func, " +
                        correctedTypeName.get("tInt"), indent);
                break;
            case "float":
                availableIdsType.put(ctx.id().getText(), Type.tFloat);
                generateSymbolTable(ctx.id().getText() + ", func, " +
                        correctedTypeName.get("tFloat"), indent);
                break;
            default:
                availableIdsType.put(ctx.id().getText(), Type.tVoid);
                generateSymbolTable(ctx.id().getText() + ", func, " +
                        correctedTypeName.get("tVoid"), indent);
                break;
        }
        for (String s : idsBufferFunc) {
            availableIdsType.remove(s);
        }
        idsBufferFunc.clear();
        //print("Exit FuncDec: " + typeSys.get(ctx));
    }

    public void exitCall(tigerParser.CallContext ctx) {

        if (availableIdsType.get(ctx.id().getText()) != Type.tVoid) {
            String nameX = ctx.opt_prefix().lvalue().getText();
            if (ctx.expr_list().expr() != null) {
                generateIRCode("callr, " + nameX + ", " + ctx.id().getText() + ", " + ctx.expr_list().expr().getText());
            } else {
                generateIRCode("callr, " + nameX + ", " + ctx.id().getText());
            }
        } else {
            if (ctx.expr_list().expr() != null) {
                generateIRCode("call, " + ctx.id().getText() + ", " + ctx.expr_list().expr().getText());
            } else {
                generateIRCode("call, " + ctx.id().getText());
            }
        }
    }

    public void enterForStmt(tigerParser.ForStmtContext ctx){
        currentScope = scopes.get(ctx);
        //print("In ForStmt: " + typeSys.get(ctx));
    }
/*
    // check integer
    public void exitInteger(tigerParser.IntegerContext ctx) {
        typeSys.put(ctx, Type.tInt);
        print("In Integer: " + typeSys.get(ctx));
    }

    // check float
    public void exitFloat(tigerParser.FloatContext ctx) {
        typeSys.put(ctx, Type.tFloat);
        print("In Float: " + typeSys.get(ctx));
    }
*/
    public void exitExpr(tigerParser.ExprContext ctx){
        // expr -> log
        Type childType = typeSys.get(ctx.log());
        typeSys.put(ctx, childType);
        print("In Expr " + typeSys.get(ctx));
    }

    // check var type around '|', '&'
    public void exitLog(tigerParser.LogContext ctx){
        // log -> comp
        if (ctx.log() == null) {
            Type childType = typeSys.get(ctx.comp());
            typeSys.put(ctx, childType);
        }
        // log ('|' | '&') comp
        else {
            Type t1 = typeSys.get(ctx.log());
            Type t2 = typeSys.get(ctx.comp());
            if ((t1 == Type.tInt || t2 == Type.tInt) && (t1 != t2)) {
                CheckSymbols.error(ctx.getStart(),
                        "Expected Int |/& Int got " + t1 + " |/& " + t2);
                CheckSymbols.succcessfulCompile = false;
                typeSys.put(ctx, Type.tVoid);
            } else if ((t1 == Type.tFloat || t2 == Type.tFloat) && (t1 != t2)) {
                CheckSymbols.error(ctx.getStart(),
                        "Expected Float |/& Float got " + t1 + " |/& " + t2);
                CheckSymbols.succcessfulCompile = false;
                typeSys.put(ctx, Type.tVoid);
            } else if (t1 == Type.tInt) {
                typeSys.put(ctx, Type.tInt);
            } else {
                typeSys.put(ctx, Type.tFloat);

            }
            print("In Logical " + typeSys.get(ctx));
        }
    }

    // check var type around '==', '!=', '<', '>', '<=', '>='
    public void exitComp(tigerParser.CompContext ctx){
        // comp -> add
        if (ctx.comp() == null) {
            Type childType = typeSys.get(ctx.add());
            typeSys.put(ctx, childType);
        }
        // comp -> comp ('==' | '!=' | '<' | '>' | '<=' | '>=') add
        else {
            Type t1 = typeSys.get(ctx.comp());
            Type t2 = typeSys.get(ctx.add());

            if ((t1 == Type.tInt || t2 == Type.tInt) && (t1 != t2)) {
                CheckSymbols.error(ctx.getStart(),
                        "Expected Int ==/!=/</>/<=/>= Int got " + t1 + " ==/!=/</>/<=/>= " + t2);
                CheckSymbols.succcessfulCompile = false;
                typeSys.put(ctx, Type.tVoid);
            } else if ((t1 == Type.tFloat || t2 == Type.tFloat) && (t1 != t2)) {
                CheckSymbols.error(ctx.getStart(),
                        "Expected Float ==/!=/</>/<=/>= Float got " + t1 + " ==/!=/</>/<=/>= " + t2);
                CheckSymbols.succcessfulCompile = false;
                typeSys.put(ctx, Type.tVoid);
            } else if (t1 == Type.tInt) {
                typeSys.put(ctx, Type.tInt);
            } else {
                typeSys.put(ctx, Type.tFloat);
            }
            print("In Comp : " + typeSys.get(ctx));
        }
    }

    // check var type around '+' and '-'
    public void exitAdd(tigerParser.AddContext ctx){
        // add -> mul
        if (ctx.add() == null) {
            Type childType = typeSys.get(ctx.mul());
            typeSys.put(ctx, childType);
        }
        // add -> add ('+' | '-') mul
        else {
            Type t1 = typeSys.get(ctx.add());
            Type t2 = typeSys.get(ctx.mul());

            if ((t1 == Type.tInt || t2 == Type.tInt) && (t1 != t2)) {
                CheckSymbols.error(ctx.getStart(), "Expected Int +/- Int got " + t1 + " +/- " + t2);
                CheckSymbols.succcessfulCompile = false;
                typeSys.put(ctx, Type.tVoid);
            } else if ((t1 == Type.tFloat || t2 == Type.tFloat) && (t1 != t2)) {
                CheckSymbols.error(ctx.getStart(), "Expected Float +/- Float got " + t1 + " +/- " + t2);
                CheckSymbols.succcessfulCompile = false;
                typeSys.put(ctx, Type.tVoid);
            } else if (t1 == Type.tInt) {
                typeSys.put(ctx, Type.tInt);
            } else {
                typeSys.put(ctx, Type.tFloat);
            }
            print("In Add..." + ctx.getText());
        }
    }

    // check var type around '*' and '/'
    public void exitMul(tigerParser.MulContext ctx){
        // mul -> exp
        if (ctx.mul() == null) {
            Type childType = typeSys.get(ctx.exp());
            typeSys.put(ctx, childType);
        }
        // mul -> mul ('*' | '/') exp
        else {
            Type t1 = typeSys.get(ctx.mul());
            Type t2 = typeSys.get(ctx.exp());

            if ((t1 == Type.tInt || t2 == Type.tInt) && (t1 != t2)) {
                CheckSymbols.error(ctx.getStart(), "Expected Int * / Int got " + t1 + " * / " + t2);
                CheckSymbols.succcessfulCompile = false;
                typeSys.put(ctx, Type.tVoid);
            } else if ((t1 == Type.tFloat || t2 == Type.tFloat) && (t1 != t2)) {
                CheckSymbols.error(ctx.getStart(), "Expected Float * / Float got " + t1 + " * / " + t2);
                CheckSymbols.succcessfulCompile = false;
                typeSys.put(ctx, Type.tVoid);
            } else if (t1 == Type.tInt) {
                typeSys.put(ctx, Type.tInt);
            } else {
                typeSys.put(ctx, Type.tFloat);
            }
            print("In Mul " + typeSys.get(ctx));
        }
    }

    // check var type around '**'
    public void exitExp(tigerParser.ExpContext ctx){
        // exp -> paren
        if (ctx.exp() == null) {
            Type childType = typeSys.get(ctx.paren());
            typeSys.put(ctx, childType);
        }
        // exp -> exp '**' paren
        else {
            Type t1 = typeSys.get(ctx.exp());
            Type t2 = typeSys.get(ctx.paren());

            print("Exp t1: " + t1);
            print("Exp t2: " + t2);
            if ((t1 == Type.tInt || t2 == Type.tInt) && (t1 != t2)) {
                CheckSymbols.error(ctx.getStart(),
                        "Expected Num ** Num got " + t1 + " ** " + t2);
                CheckSymbols.succcessfulCompile = false;
                typeSys.put(ctx, Type.tVoid);
            } else if ((t1 == Type.tFloat || t2 == Type.tFloat) && (t1 != t2)) {
                CheckSymbols.error(ctx.getStart(),
                        "Expected Num ** Num got " + t1 + " ** " + t2);
                CheckSymbols.succcessfulCompile = false;
                typeSys.put(ctx, Type.tVoid);
            } else if (t1 == Type.tInt) {
                typeSys.put(ctx, Type.tInt);
            } else {
                typeSys.put(ctx, Type.tFloat);
            }
            print("In Exp " + typeSys.get(ctx));
        }
    }

    // check type inside '()'
    public void exitParen(tigerParser.ParenContext ctx){
        if (typeSys.get(ctx.expr()) != null) {
            typeSys.put(ctx, typeSys.get(ctx.expr()));
            print("In Paren: " + typeSys.get(ctx));
        }
        if (typeSys.get(ctx.r_const()) != null) {
            typeSys.put(ctx, typeSys.get(ctx.r_const()));
            print("In Paren: " + typeSys.get(ctx));
        }
        if (typeSys.get(ctx.lvalue()) != null) {
            typeSys.put(ctx, typeSys.get(ctx.lvalue()));
            print("In Paren: " + typeSys.get(ctx));
        }
    }

    // check assignment type
    public void exitAssign(tigerParser.AssignContext ctx){
        Type t1 = typeSys.get(ctx.lvalue());
        Type t2 = typeSys.get(ctx.expr());
        if (t1 != t2){
            CheckSymbols.error( ctx.getStart(), "Assign " + t2 + " to " + t1);
            CheckSymbols.succcessfulCompile = false;
            typeSys.put(ctx, Type.tVoid);
        } else {
            typeSys.put(ctx, t1);
        }

        print("In Assign : " + typeSys.get(ctx));
    }

    // check lvalue
    public void exitLvalue(tigerParser.LvalueContext ctx){
        String idOfLvalue = ctx.id().getText();
        print("Id of the Lvalue: " + idOfLvalue);
        print("In current available ids: " + availableIdsType.keySet());
        // if current lvalue is an id and already defined
        // assign the lvalue node with the same type as existed id
        if (availableIdsType != null && availableIdsType.containsKey(idOfLvalue)) {
            typeSys.put(ctx, availableIdsType.get(idOfLvalue));
            print("exitLvalue ids map: " + availableIdsType);
            print("In Lvalue: " + typeSys.get(ctx));
            generateSymbolTable(ctx.id().getText() + ", var, " +
                    correctedTypeName.get(availableIdsType.get(idOfLvalue).toString()), indent);
        }
        // current lvalue is not defined
        // assign the node type as tVoid
        else {
            print("In Lvalue: " + availableIdsType);
            typeSys.put(ctx, Type.tVoid);
            CheckSymbols.error( ctx.getStart(),
                    idOfLvalue + " is not defined");
            CheckSymbols.succcessfulCompile = false;
            print("In Lvalue: " + typeSys.get(ctx));
            generateSymbolTable(ctx.id().getText() + ", var, " +
                    correctedTypeName.get("tVoid"), indent);
        }
    }

    // check r_const
    public void exitR_const(tigerParser.R_constContext ctx) {
        if (ctx.INTLIT() != null) {
            typeSys.put(ctx, Type.tInt);
            print("In r_const: " + typeSys.get(ctx));
            generateSymbolTable(ctx.INTLIT().getText() + ", INTLIT, " +
                    correctedTypeName.get("tInt"), indent);
        } else {
            typeSys.put(ctx, Type.tFloat);
            print("In r_const: " + typeSys.get(ctx));
            generateSymbolTable(ctx.FLOATLIT().getText() + ", FLOATLIT, " +
                    correctedTypeName.get("tFloat"), indent);
        }
    }

    // check if statement
    public void exitIfStmt(tigerParser.IfStmtContext ctx){
        Type t1 = typeSys.get(ctx.expr());
        Type t2 = typeSys.get(ctx.stat_seq(0));
        Type t3 = typeSys.get(ctx.stat_seq(1));
        if (t1 != Type.tInt){
            CheckSymbols.error(ctx.getStart(),
                    "boolean expression must be tInt ");
            CheckSymbols.succcessfulCompile = false;
            typeSys.put(ctx, Type.tVoid);
        } else {
            if (t2 != t3){
                CheckSymbols.error(ctx.getStart(),
                        "then statement and else statement need to same");
                CheckSymbols.succcessfulCompile = false;
                typeSys.put(ctx, Type.tVoid);
            }
            else {
                typeSys.put(ctx,t2);
            }
        }
       //print( "Exit IfStmt " + typeSys.get(ctx));
    }

    // check while statement
    public void exitWhileStmt(tigerParser.WhileStmtContext ctx){
        Type t1 = typeSys.get(ctx.expr());
        Type t2 = typeSys.get(ctx.stat_seq());

        if (t1 != Type.tInt){
            CheckSymbols.error(ctx.getStart(), "loop pd expression must be tInt" );
            CheckSymbols.succcessfulCompile = false;
            typeSys.put(ctx, Type.tVoid);
        } else {
            typeSys.put(ctx, t2);
        }
        //print("Exit WhileStmt : " + typeSys.get(ctx));
        currentScope = currentScope.getEnclosingScope(); // exit a scope
    }

    // check for statement
    public void exitForStmt(tigerParser.ForStmtContext ctx){
        Type t1 = typeSys.get(ctx.expr(0));
        Type t2 = typeSys.get(ctx.expr(1));
        Type t3 = typeSys.get(ctx.stat_seq());

        if ((t1 == Type.tInt || t2 == Type.tInt) && (t1 != t2)){
            CheckSymbols.error(ctx.getStart(),
                    "loop start and end must both be tInt" );
            CheckSymbols.succcessfulCompile = false;
            typeSys.put(ctx, Type.tVoid);
        } else {
            typeSys.put(ctx, t3);
        }
        //print("Exit ForStmt " + typeSys.get(ctx));
        currentScope = currentScope.getEnclosingScope(); // exit a scope
    }

    // check let statement
    public void exitLET(tigerParser.LETContext ctx) {
        currentScope = currentScope.getEnclosingScope(); // exit a scope
        indent -= 1;
        for (String s : idsBufferLet) {
            availableIdsType.remove(s);
        }
        idsBufferLet.clear();
        //print("Exit LET: " + typeSys.get(ctx));
    }

    private void print(Object o){
        if (debug) {
            System.out.println(o);
        }
    }

    private void generateSymbolTable(Object o, int indent) {
        if (symbolTable) {
            String indents = new String(new char[indent]).replace("\0", "\t");
            System.out.println(indents + o);
        }
    }

    private void generateIRCode(Object o) {
        if (IrCode) {
            IrCodeResult.add(o.toString());
        }
    }
}