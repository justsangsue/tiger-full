// Generated from tiger.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link tigerParser}.
 */
public interface tigerListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link tigerParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(tigerParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(tigerParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#declaration_segment}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration_segment(tigerParser.Declaration_segmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#declaration_segment}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration_segment(tigerParser.Declaration_segmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#type_declaration_list}.
	 * @param ctx the parse tree
	 */
	void enterType_declaration_list(tigerParser.Type_declaration_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#type_declaration_list}.
	 * @param ctx the parse tree
	 */
	void exitType_declaration_list(tigerParser.Type_declaration_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#var_declaration_list}.
	 * @param ctx the parse tree
	 */
	void enterVar_declaration_list(tigerParser.Var_declaration_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#var_declaration_list}.
	 * @param ctx the parse tree
	 */
	void exitVar_declaration_list(tigerParser.Var_declaration_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#function_declaration_list}.
	 * @param ctx the parse tree
	 */
	void enterFunction_declaration_list(tigerParser.Function_declaration_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#function_declaration_list}.
	 * @param ctx the parse tree
	 */
	void exitFunction_declaration_list(tigerParser.Function_declaration_listContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeDec}
	 * labeled alternative in {@link tigerParser#type_declaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeDec(tigerParser.TypeDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeDec}
	 * labeled alternative in {@link tigerParser#type_declaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeDec(tigerParser.TypeDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(tigerParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(tigerParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Integer}
	 * labeled alternative in {@link tigerParser#type_id}.
	 * @param ctx the parse tree
	 */
	void enterInteger(tigerParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Integer}
	 * labeled alternative in {@link tigerParser#type_id}.
	 * @param ctx the parse tree
	 */
	void exitInteger(tigerParser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Float}
	 * labeled alternative in {@link tigerParser#type_id}.
	 * @param ctx the parse tree
	 */
	void enterFloat(tigerParser.FloatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Float}
	 * labeled alternative in {@link tigerParser#type_id}.
	 * @param ctx the parse tree
	 */
	void exitFloat(tigerParser.FloatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarDec}
	 * labeled alternative in {@link tigerParser#var_declaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDec(tigerParser.VarDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarDec}
	 * labeled alternative in {@link tigerParser#var_declaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDec(tigerParser.VarDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#id_list}.
	 * @param ctx the parse tree
	 */
	void enterId_list(tigerParser.Id_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#id_list}.
	 * @param ctx the parse tree
	 */
	void exitId_list(tigerParser.Id_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#optional_init}.
	 * @param ctx the parse tree
	 */
	void enterOptional_init(tigerParser.Optional_initContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#optional_init}.
	 * @param ctx the parse tree
	 */
	void exitOptional_init(tigerParser.Optional_initContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FuncDec}
	 * labeled alternative in {@link tigerParser#function_declaration}.
	 * @param ctx the parse tree
	 */
	void enterFuncDec(tigerParser.FuncDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FuncDec}
	 * labeled alternative in {@link tigerParser#function_declaration}.
	 * @param ctx the parse tree
	 */
	void exitFuncDec(tigerParser.FuncDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#param_list}.
	 * @param ctx the parse tree
	 */
	void enterParam_list(tigerParser.Param_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#param_list}.
	 * @param ctx the parse tree
	 */
	void exitParam_list(tigerParser.Param_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#param_list_tail}.
	 * @param ctx the parse tree
	 */
	void enterParam_list_tail(tigerParser.Param_list_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#param_list_tail}.
	 * @param ctx the parse tree
	 */
	void exitParam_list_tail(tigerParser.Param_list_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#ret_type}.
	 * @param ctx the parse tree
	 */
	void enterRet_type(tigerParser.Ret_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#ret_type}.
	 * @param ctx the parse tree
	 */
	void exitRet_type(tigerParser.Ret_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(tigerParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(tigerParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#stat_seq}.
	 * @param ctx the parse tree
	 */
	void enterStat_seq(tigerParser.Stat_seqContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#stat_seq}.
	 * @param ctx the parse tree
	 */
	void exitStat_seq(tigerParser.Stat_seqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link tigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterAssign(tigerParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link tigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitAssign(tigerParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link tigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(tigerParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link tigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(tigerParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link tigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(tigerParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link tigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(tigerParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForStmt}
	 * labeled alternative in {@link tigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(tigerParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForStmt}
	 * labeled alternative in {@link tigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(tigerParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Call}
	 * labeled alternative in {@link tigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterCall(tigerParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Call}
	 * labeled alternative in {@link tigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitCall(tigerParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Break}
	 * labeled alternative in {@link tigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterBreak(tigerParser.BreakContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Break}
	 * labeled alternative in {@link tigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitBreak(tigerParser.BreakContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Return}
	 * labeled alternative in {@link tigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterReturn(tigerParser.ReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Return}
	 * labeled alternative in {@link tigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitReturn(tigerParser.ReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LET}
	 * labeled alternative in {@link tigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterLET(tigerParser.LETContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LET}
	 * labeled alternative in {@link tigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitLET(tigerParser.LETContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#l_tail}.
	 * @param ctx the parse tree
	 */
	void enterL_tail(tigerParser.L_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#l_tail}.
	 * @param ctx the parse tree
	 */
	void exitL_tail(tigerParser.L_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void enterLvalue(tigerParser.LvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void exitLvalue(tigerParser.LvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#opt_prefix}.
	 * @param ctx the parse tree
	 */
	void enterOpt_prefix(tigerParser.Opt_prefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#opt_prefix}.
	 * @param ctx the parse tree
	 */
	void exitOpt_prefix(tigerParser.Opt_prefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(tigerParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(tigerParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#log}.
	 * @param ctx the parse tree
	 */
	void enterLog(tigerParser.LogContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#log}.
	 * @param ctx the parse tree
	 */
	void exitLog(tigerParser.LogContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#comp}.
	 * @param ctx the parse tree
	 */
	void enterComp(tigerParser.CompContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#comp}.
	 * @param ctx the parse tree
	 */
	void exitComp(tigerParser.CompContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#add}.
	 * @param ctx the parse tree
	 */
	void enterAdd(tigerParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#add}.
	 * @param ctx the parse tree
	 */
	void exitAdd(tigerParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#mul}.
	 * @param ctx the parse tree
	 */
	void enterMul(tigerParser.MulContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#mul}.
	 * @param ctx the parse tree
	 */
	void exitMul(tigerParser.MulContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(tigerParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(tigerParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#paren}.
	 * @param ctx the parse tree
	 */
	void enterParen(tigerParser.ParenContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#paren}.
	 * @param ctx the parse tree
	 */
	void exitParen(tigerParser.ParenContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#r_const}.
	 * @param ctx the parse tree
	 */
	void enterR_const(tigerParser.R_constContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#r_const}.
	 * @param ctx the parse tree
	 */
	void exitR_const(tigerParser.R_constContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list(tigerParser.Expr_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list(tigerParser.Expr_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#expr_list_tail}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list_tail(tigerParser.Expr_list_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#expr_list_tail}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list_tail(tigerParser.Expr_list_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link tigerParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(tigerParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link tigerParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(tigerParser.IdContext ctx);
}