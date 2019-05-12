// Generated from tiger.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class tigerParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		INTLIT=46, FLOATLIT=47, DIGIT=48, LETTER=49, COMMENT=50, LINE_COMMENT=51, 
		WHITESPACE=52;
	public static final int
		RULE_program = 0, RULE_declaration_segment = 1, RULE_type_declaration_list = 2, 
		RULE_var_declaration_list = 3, RULE_function_declaration_list = 4, RULE_type_declaration = 5, 
		RULE_type = 6, RULE_type_id = 7, RULE_var_declaration = 8, RULE_id_list = 9, 
		RULE_optional_init = 10, RULE_function_declaration = 11, RULE_param_list = 12, 
		RULE_param_list_tail = 13, RULE_ret_type = 14, RULE_param = 15, RULE_stat_seq = 16, 
		RULE_stat = 17, RULE_l_tail = 18, RULE_lvalue = 19, RULE_opt_prefix = 20, 
		RULE_expr = 21, RULE_log = 22, RULE_comp = 23, RULE_add = 24, RULE_mul = 25, 
		RULE_exp = 26, RULE_paren = 27, RULE_r_const = 28, RULE_expr_list = 29, 
		RULE_expr_list_tail = 30, RULE_id = 31;
	public static final String[] ruleNames = {
		"program", "declaration_segment", "type_declaration_list", "var_declaration_list", 
		"function_declaration_list", "type_declaration", "type", "type_id", "var_declaration", 
		"id_list", "optional_init", "function_declaration", "param_list", "param_list_tail", 
		"ret_type", "param", "stat_seq", "stat", "l_tail", "lvalue", "opt_prefix", 
		"expr", "log", "comp", "add", "mul", "exp", "paren", "r_const", "expr_list", 
		"expr_list_tail", "id"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'main'", "'let'", "'in'", "'begin'", "'end'", "'type'", "'='", 
		"';'", "'array'", "'['", "']'", "'of'", "'int'", "'float'", "'var'", "':'", 
		"','", "':='", "'function'", "'('", "')'", "'if'", "'then'", "'else'", 
		"'endif'", "'while'", "'do'", "'enddo'", "'for'", "'to'", "'break'", "'return'", 
		"'|'", "'&'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'+'", "'-'", 
		"'*'", "'/'", "'**'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, "INTLIT", 
		"FLOATLIT", "DIGIT", "LETTER", "COMMENT", "LINE_COMMENT", "WHITESPACE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "tiger.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public tigerParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public Declaration_segmentContext declaration_segment() {
			return getRuleContext(Declaration_segmentContext.class,0);
		}
		public Stat_seqContext stat_seq() {
			return getRuleContext(Stat_seqContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(T__0);
			setState(65);
			match(T__1);
			setState(66);
			declaration_segment();
			setState(67);
			match(T__2);
			setState(68);
			match(T__3);
			setState(69);
			stat_seq();
			setState(70);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Declaration_segmentContext extends ParserRuleContext {
		public Type_declaration_listContext type_declaration_list() {
			return getRuleContext(Type_declaration_listContext.class,0);
		}
		public Var_declaration_listContext var_declaration_list() {
			return getRuleContext(Var_declaration_listContext.class,0);
		}
		public Function_declaration_listContext function_declaration_list() {
			return getRuleContext(Function_declaration_listContext.class,0);
		}
		public Declaration_segmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration_segment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterDeclaration_segment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitDeclaration_segment(this);
		}
	}

	public final Declaration_segmentContext declaration_segment() throws RecognitionException {
		Declaration_segmentContext _localctx = new Declaration_segmentContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaration_segment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			type_declaration_list();
			setState(73);
			var_declaration_list();
			setState(74);
			function_declaration_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_declaration_listContext extends ParserRuleContext {
		public Type_declarationContext type_declaration() {
			return getRuleContext(Type_declarationContext.class,0);
		}
		public Type_declaration_listContext type_declaration_list() {
			return getRuleContext(Type_declaration_listContext.class,0);
		}
		public Type_declaration_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_declaration_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterType_declaration_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitType_declaration_list(this);
		}
	}

	public final Type_declaration_listContext type_declaration_list() throws RecognitionException {
		Type_declaration_listContext _localctx = new Type_declaration_listContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type_declaration_list);
		try {
			setState(80);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
			case T__14:
			case T__18:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				type_declaration();
				setState(78);
				type_declaration_list();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_declaration_listContext extends ParserRuleContext {
		public Var_declarationContext var_declaration() {
			return getRuleContext(Var_declarationContext.class,0);
		}
		public Var_declaration_listContext var_declaration_list() {
			return getRuleContext(Var_declaration_listContext.class,0);
		}
		public Var_declaration_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_declaration_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterVar_declaration_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitVar_declaration_list(this);
		}
	}

	public final Var_declaration_listContext var_declaration_list() throws RecognitionException {
		Var_declaration_listContext _localctx = new Var_declaration_listContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_var_declaration_list);
		try {
			setState(86);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
			case T__18:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				var_declaration();
				setState(84);
				var_declaration_list();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_declaration_listContext extends ParserRuleContext {
		public Function_declarationContext function_declaration() {
			return getRuleContext(Function_declarationContext.class,0);
		}
		public Function_declaration_listContext function_declaration_list() {
			return getRuleContext(Function_declaration_listContext.class,0);
		}
		public Function_declaration_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_declaration_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterFunction_declaration_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitFunction_declaration_list(this);
		}
	}

	public final Function_declaration_listContext function_declaration_list() throws RecognitionException {
		Function_declaration_listContext _localctx = new Function_declaration_listContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_function_declaration_list);
		try {
			setState(92);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				function_declaration();
				setState(90);
				function_declaration_list();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_declarationContext extends ParserRuleContext {
		public Type_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_declaration; }
	 
		public Type_declarationContext() { }
		public void copyFrom(Type_declarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeDecContext extends Type_declarationContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeDecContext(Type_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterTypeDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitTypeDec(this);
		}
	}

	public final Type_declarationContext type_declaration() throws RecognitionException {
		Type_declarationContext _localctx = new Type_declarationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type_declaration);
		try {
			_localctx = new TypeDecContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(T__5);
			setState(95);
			id();
			setState(96);
			match(T__6);
			setState(97);
			type();
			setState(98);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public Type_idContext type_id() {
			return getRuleContext(Type_idContext.class,0);
		}
		public TerminalNode INTLIT() { return getToken(tigerParser.INTLIT, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_type);
		try {
			setState(108);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__12:
			case T__13:
				enterOuterAlt(_localctx, 1);
				{
				setState(100);
				type_id();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(101);
				match(T__8);
				setState(102);
				match(T__9);
				setState(103);
				match(INTLIT);
				setState(104);
				match(T__10);
				setState(105);
				match(T__11);
				setState(106);
				type_id();
				}
				break;
			case LETTER:
				enterOuterAlt(_localctx, 3);
				{
				setState(107);
				id();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_idContext extends ParserRuleContext {
		public Type_idContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_id; }
	 
		public Type_idContext() { }
		public void copyFrom(Type_idContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IntegerContext extends Type_idContext {
		public IntegerContext(Type_idContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitInteger(this);
		}
	}
	public static class FloatContext extends Type_idContext {
		public FloatContext(Type_idContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterFloat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitFloat(this);
		}
	}

	public final Type_idContext type_id() throws RecognitionException {
		Type_idContext _localctx = new Type_idContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_type_id);
		try {
			setState(112);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__12:
				_localctx = new IntegerContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				match(T__12);
				}
				break;
			case T__13:
				_localctx = new FloatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
				match(T__13);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_declarationContext extends ParserRuleContext {
		public Var_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_declaration; }
	 
		public Var_declarationContext() { }
		public void copyFrom(Var_declarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VarDecContext extends Var_declarationContext {
		public Id_listContext id_list() {
			return getRuleContext(Id_listContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Optional_initContext optional_init() {
			return getRuleContext(Optional_initContext.class,0);
		}
		public VarDecContext(Var_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterVarDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitVarDec(this);
		}
	}

	public final Var_declarationContext var_declaration() throws RecognitionException {
		Var_declarationContext _localctx = new Var_declarationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_var_declaration);
		try {
			_localctx = new VarDecContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(T__14);
			setState(115);
			id_list();
			setState(116);
			match(T__15);
			setState(117);
			type();
			setState(118);
			optional_init();
			setState(119);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Id_listContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Id_listContext id_list() {
			return getRuleContext(Id_listContext.class,0);
		}
		public Id_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterId_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitId_list(this);
		}
	}

	public final Id_listContext id_list() throws RecognitionException {
		Id_listContext _localctx = new Id_listContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_id_list);
		try {
			setState(126);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				id();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				id();
				setState(123);
				match(T__16);
				setState(124);
				id_list();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Optional_initContext extends ParserRuleContext {
		public R_constContext r_const() {
			return getRuleContext(R_constContext.class,0);
		}
		public Optional_initContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optional_init; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterOptional_init(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitOptional_init(this);
		}
	}

	public final Optional_initContext optional_init() throws RecognitionException {
		Optional_initContext _localctx = new Optional_initContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_optional_init);
		try {
			setState(131);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__17:
				enterOuterAlt(_localctx, 1);
				{
				setState(128);
				match(T__17);
				setState(129);
				r_const();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_declarationContext extends ParserRuleContext {
		public Function_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_declaration; }
	 
		public Function_declarationContext() { }
		public void copyFrom(Function_declarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FuncDecContext extends Function_declarationContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Param_listContext param_list() {
			return getRuleContext(Param_listContext.class,0);
		}
		public Ret_typeContext ret_type() {
			return getRuleContext(Ret_typeContext.class,0);
		}
		public Stat_seqContext stat_seq() {
			return getRuleContext(Stat_seqContext.class,0);
		}
		public FuncDecContext(Function_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterFuncDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitFuncDec(this);
		}
	}

	public final Function_declarationContext function_declaration() throws RecognitionException {
		Function_declarationContext _localctx = new Function_declarationContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_function_declaration);
		try {
			_localctx = new FuncDecContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(T__18);
			setState(134);
			id();
			setState(135);
			match(T__19);
			setState(136);
			param_list();
			setState(137);
			match(T__20);
			setState(138);
			ret_type();
			setState(139);
			match(T__3);
			setState(140);
			stat_seq();
			setState(141);
			match(T__4);
			setState(142);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Param_listContext extends ParserRuleContext {
		public ParamContext param() {
			return getRuleContext(ParamContext.class,0);
		}
		public Param_list_tailContext param_list_tail() {
			return getRuleContext(Param_list_tailContext.class,0);
		}
		public Param_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterParam_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitParam_list(this);
		}
	}

	public final Param_listContext param_list() throws RecognitionException {
		Param_listContext _localctx = new Param_listContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_param_list);
		try {
			setState(148);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__20:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case LETTER:
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				param();
				setState(146);
				param_list_tail();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Param_list_tailContext extends ParserRuleContext {
		public ParamContext param() {
			return getRuleContext(ParamContext.class,0);
		}
		public Param_list_tailContext param_list_tail() {
			return getRuleContext(Param_list_tailContext.class,0);
		}
		public Param_list_tailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_list_tail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterParam_list_tail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitParam_list_tail(this);
		}
	}

	public final Param_list_tailContext param_list_tail() throws RecognitionException {
		Param_list_tailContext _localctx = new Param_list_tailContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_param_list_tail);
		try {
			setState(155);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__20:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 2);
				{
				setState(151);
				match(T__16);
				setState(152);
				param();
				setState(153);
				param_list_tail();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ret_typeContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Ret_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ret_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterRet_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitRet_type(this);
		}
	}

	public final Ret_typeContext ret_type() throws RecognitionException {
		Ret_typeContext _localctx = new Ret_typeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_ret_type);
		try {
			setState(160);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				match(T__15);
				setState(159);
				type();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitParam(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			id();
			setState(163);
			match(T__15);
			setState(164);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Stat_seqContext extends ParserRuleContext {
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public Stat_seqContext stat_seq() {
			return getRuleContext(Stat_seqContext.class,0);
		}
		public Stat_seqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat_seq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterStat_seq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitStat_seq(this);
		}
	}

	public final Stat_seqContext stat_seq() throws RecognitionException {
		Stat_seqContext _localctx = new Stat_seqContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_stat_seq);
		try {
			setState(170);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(166);
				stat();
				setState(167);
				stat_seq();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(169);
				stat();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	 
		public StatContext() { }
		public void copyFrom(StatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CallContext extends StatContext {
		public Opt_prefixContext opt_prefix() {
			return getRuleContext(Opt_prefixContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Expr_listContext expr_list() {
			return getRuleContext(Expr_listContext.class,0);
		}
		public CallContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitCall(this);
		}
	}
	public static class IfStmtContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<Stat_seqContext> stat_seq() {
			return getRuleContexts(Stat_seqContext.class);
		}
		public Stat_seqContext stat_seq(int i) {
			return getRuleContext(Stat_seqContext.class,i);
		}
		public IfStmtContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitIfStmt(this);
		}
	}
	public static class ReturnContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitReturn(this);
		}
	}
	public static class WhileStmtContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Stat_seqContext stat_seq() {
			return getRuleContext(Stat_seqContext.class,0);
		}
		public WhileStmtContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterWhileStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitWhileStmt(this);
		}
	}
	public static class BreakContext extends StatContext {
		public BreakContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterBreak(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitBreak(this);
		}
	}
	public static class AssignContext extends StatContext {
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public L_tailContext l_tail() {
			return getRuleContext(L_tailContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitAssign(this);
		}
	}
	public static class LETContext extends StatContext {
		public Declaration_segmentContext declaration_segment() {
			return getRuleContext(Declaration_segmentContext.class,0);
		}
		public Stat_seqContext stat_seq() {
			return getRuleContext(Stat_seqContext.class,0);
		}
		public LETContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterLET(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitLET(this);
		}
	}
	public static class ForStmtContext extends StatContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public Stat_seqContext stat_seq() {
			return getRuleContext(Stat_seqContext.class,0);
		}
		public ForStmtContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitForStmt(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_stat);
		int _la;
		try {
			setState(227);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new AssignContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(172);
				lvalue();
				setState(173);
				l_tail();
				setState(174);
				match(T__17);
				setState(175);
				expr();
				setState(176);
				match(T__7);
				}
				break;
			case 2:
				_localctx = new IfStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(178);
				match(T__21);
				setState(179);
				expr();
				setState(180);
				match(T__22);
				setState(181);
				stat_seq();
				setState(184);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__23) {
					{
					setState(182);
					match(T__23);
					setState(183);
					stat_seq();
					}
				}

				setState(186);
				match(T__24);
				setState(187);
				match(T__7);
				}
				break;
			case 3:
				_localctx = new WhileStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(189);
				match(T__25);
				setState(190);
				expr();
				setState(191);
				match(T__26);
				setState(192);
				stat_seq();
				setState(193);
				match(T__27);
				setState(194);
				match(T__7);
				}
				break;
			case 4:
				_localctx = new ForStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(196);
				match(T__28);
				setState(197);
				id();
				setState(198);
				match(T__17);
				setState(199);
				expr();
				setState(200);
				match(T__29);
				setState(201);
				expr();
				setState(202);
				match(T__26);
				setState(203);
				stat_seq();
				setState(204);
				match(T__27);
				setState(205);
				match(T__7);
				}
				break;
			case 5:
				_localctx = new CallContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(207);
				opt_prefix();
				setState(208);
				id();
				setState(209);
				match(T__19);
				setState(210);
				expr_list();
				setState(211);
				match(T__20);
				setState(212);
				match(T__7);
				}
				break;
			case 6:
				_localctx = new BreakContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(214);
				match(T__30);
				setState(215);
				match(T__7);
				}
				break;
			case 7:
				_localctx = new ReturnContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(216);
				match(T__31);
				setState(217);
				expr();
				setState(218);
				match(T__7);
				}
				break;
			case 8:
				_localctx = new LETContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(220);
				match(T__1);
				setState(221);
				declaration_segment();
				setState(222);
				match(T__2);
				setState(223);
				stat_seq();
				setState(224);
				match(T__4);
				setState(225);
				match(T__7);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class L_tailContext extends ParserRuleContext {
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public L_tailContext l_tail() {
			return getRuleContext(L_tailContext.class,0);
		}
		public L_tailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_l_tail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterL_tail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitL_tail(this);
		}
	}

	public final L_tailContext l_tail() throws RecognitionException {
		L_tailContext _localctx = new L_tailContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_l_tail);
		try {
			setState(234);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(229);
				match(T__17);
				setState(230);
				lvalue();
				setState(231);
				l_tail();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LvalueContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public LvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lvalue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterLvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitLvalue(this);
		}
	}

	public final LvalueContext lvalue() throws RecognitionException {
		LvalueContext _localctx = new LvalueContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_lvalue);
		try {
			setState(242);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(236);
				id();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(237);
				id();
				setState(238);
				match(T__9);
				setState(239);
				expr();
				setState(240);
				match(T__10);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Opt_prefixContext extends ParserRuleContext {
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public Opt_prefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_opt_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterOpt_prefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitOpt_prefix(this);
		}
	}

	public final Opt_prefixContext opt_prefix() throws RecognitionException {
		Opt_prefixContext _localctx = new Opt_prefixContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_opt_prefix);
		try {
			setState(248);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(244);
				lvalue();
				setState(245);
				match(T__17);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public LogContext log() {
			return getRuleContext(LogContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			log(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogContext extends ParserRuleContext {
		public CompContext comp() {
			return getRuleContext(CompContext.class,0);
		}
		public LogContext log() {
			return getRuleContext(LogContext.class,0);
		}
		public LogContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_log; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterLog(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitLog(this);
		}
	}

	public final LogContext log() throws RecognitionException {
		return log(0);
	}

	private LogContext log(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LogContext _localctx = new LogContext(_ctx, _parentState);
		LogContext _prevctx = _localctx;
		int _startState = 44;
		enterRecursionRule(_localctx, 44, RULE_log, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(253);
			comp(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(260);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LogContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_log);
					setState(255);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(256);
					_la = _input.LA(1);
					if ( !(_la==T__32 || _la==T__33) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(257);
					comp(0);
					}
					} 
				}
				setState(262);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class CompContext extends ParserRuleContext {
		public AddContext add() {
			return getRuleContext(AddContext.class,0);
		}
		public CompContext comp() {
			return getRuleContext(CompContext.class,0);
		}
		public CompContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterComp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitComp(this);
		}
	}

	public final CompContext comp() throws RecognitionException {
		return comp(0);
	}

	private CompContext comp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CompContext _localctx = new CompContext(_ctx, _parentState);
		CompContext _prevctx = _localctx;
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_comp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(264);
			add(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(271);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new CompContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_comp);
					setState(266);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(267);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(268);
					add(0);
					}
					} 
				}
				setState(273);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AddContext extends ParserRuleContext {
		public MulContext mul() {
			return getRuleContext(MulContext.class,0);
		}
		public AddContext add() {
			return getRuleContext(AddContext.class,0);
		}
		public AddContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_add; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterAdd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitAdd(this);
		}
	}

	public final AddContext add() throws RecognitionException {
		return add(0);
	}

	private AddContext add(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AddContext _localctx = new AddContext(_ctx, _parentState);
		AddContext _prevctx = _localctx;
		int _startState = 48;
		enterRecursionRule(_localctx, 48, RULE_add, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(275);
			mul(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(282);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AddContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_add);
					setState(277);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(278);
					_la = _input.LA(1);
					if ( !(_la==T__40 || _la==T__41) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(279);
					mul(0);
					}
					} 
				}
				setState(284);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class MulContext extends ParserRuleContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public MulContext mul() {
			return getRuleContext(MulContext.class,0);
		}
		public MulContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mul; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterMul(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitMul(this);
		}
	}

	public final MulContext mul() throws RecognitionException {
		return mul(0);
	}

	private MulContext mul(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MulContext _localctx = new MulContext(_ctx, _parentState);
		MulContext _prevctx = _localctx;
		int _startState = 50;
		enterRecursionRule(_localctx, 50, RULE_mul, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(286);
			exp(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(293);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new MulContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_mul);
					setState(288);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(289);
					_la = _input.LA(1);
					if ( !(_la==T__42 || _la==T__43) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(290);
					exp(0);
					}
					} 
				}
				setState(295);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public ParenContext paren() {
			return getRuleContext(ParenContext.class,0);
		}
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitExp(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		return exp(0);
	}

	private ExpContext exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpContext _localctx = new ExpContext(_ctx, _parentState);
		ExpContext _prevctx = _localctx;
		int _startState = 52;
		enterRecursionRule(_localctx, 52, RULE_exp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(297);
			paren();
			}
			_ctx.stop = _input.LT(-1);
			setState(304);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_exp);
					setState(299);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(300);
					match(T__44);
					setState(301);
					paren();
					}
					} 
				}
				setState(306);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ParenContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public R_constContext r_const() {
			return getRuleContext(R_constContext.class,0);
		}
		public ParenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paren; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterParen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitParen(this);
		}
	}

	public final ParenContext paren() throws RecognitionException {
		ParenContext _localctx = new ParenContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_paren);
		try {
			setState(314);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(307);
				match(T__19);
				setState(308);
				expr();
				setState(309);
				match(T__20);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(311);
				lvalue();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(312);
				r_const();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class R_constContext extends ParserRuleContext {
		public TerminalNode INTLIT() { return getToken(tigerParser.INTLIT, 0); }
		public TerminalNode FLOATLIT() { return getToken(tigerParser.FLOATLIT, 0); }
		public R_constContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_r_const; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterR_const(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitR_const(this);
		}
	}

	public final R_constContext r_const() throws RecognitionException {
		R_constContext _localctx = new R_constContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_r_const);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
			_la = _input.LA(1);
			if ( !(_la==INTLIT || _la==FLOATLIT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr_listContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_list_tailContext expr_list_tail() {
			return getRuleContext(Expr_list_tailContext.class,0);
		}
		public Expr_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterExpr_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitExpr_list(this);
		}
	}

	public final Expr_listContext expr_list() throws RecognitionException {
		Expr_listContext _localctx = new Expr_listContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_expr_list);
		try {
			setState(322);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(318);
				expr();
				setState(319);
				expr_list_tail();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr_list_tailContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_list_tailContext expr_list_tail() {
			return getRuleContext(Expr_list_tailContext.class,0);
		}
		public Expr_list_tailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_list_tail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterExpr_list_tail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitExpr_list_tail(this);
		}
	}

	public final Expr_list_tailContext expr_list_tail() throws RecognitionException {
		Expr_list_tailContext _localctx = new Expr_list_tailContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_expr_list_tail);
		try {
			setState(329);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__16:
				enterOuterAlt(_localctx, 1);
				{
				setState(324);
				match(T__16);
				setState(325);
				expr();
				setState(326);
				expr_list_tail();
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdContext extends ParserRuleContext {
		public List<TerminalNode> LETTER() { return getTokens(tigerParser.LETTER); }
		public TerminalNode LETTER(int i) {
			return getToken(tigerParser.LETTER, i);
		}
		public List<TerminalNode> DIGIT() { return getTokens(tigerParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(tigerParser.DIGIT, i);
		}
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof tigerListener ) ((tigerListener)listener).exitId(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_id);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(331);
			match(LETTER);
			setState(335);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(332);
					_la = _input.LA(1);
					if ( !(_la==DIGIT || _la==LETTER) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					} 
				}
				setState(337);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 22:
			return log_sempred((LogContext)_localctx, predIndex);
		case 23:
			return comp_sempred((CompContext)_localctx, predIndex);
		case 24:
			return add_sempred((AddContext)_localctx, predIndex);
		case 25:
			return mul_sempred((MulContext)_localctx, predIndex);
		case 26:
			return exp_sempred((ExpContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean log_sempred(LogContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean comp_sempred(CompContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean add_sempred(AddContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean mul_sempred(MulContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean exp_sempred(ExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\66\u0155\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\5"+
		"\4S\n\4\3\5\3\5\3\5\3\5\5\5Y\n\5\3\6\3\6\3\6\3\6\5\6_\n\6\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bo\n\b\3\t\3\t\5\ts\n\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\5\13\u0081\n\13"+
		"\3\f\3\f\3\f\5\f\u0086\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\5\16\u0097\n\16\3\17\3\17\3\17\3\17\3\17\5\17\u009e"+
		"\n\17\3\20\3\20\3\20\5\20\u00a3\n\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22"+
		"\3\22\5\22\u00ad\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\5\23\u00bb\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\5\23\u00e6\n\23\3\24\3\24\3\24\3\24\3\24\5\24\u00ed"+
		"\n\24\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u00f5\n\25\3\26\3\26\3\26\3\26"+
		"\5\26\u00fb\n\26\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\7\30\u0105\n"+
		"\30\f\30\16\30\u0108\13\30\3\31\3\31\3\31\3\31\3\31\3\31\7\31\u0110\n"+
		"\31\f\31\16\31\u0113\13\31\3\32\3\32\3\32\3\32\3\32\3\32\7\32\u011b\n"+
		"\32\f\32\16\32\u011e\13\32\3\33\3\33\3\33\3\33\3\33\3\33\7\33\u0126\n"+
		"\33\f\33\16\33\u0129\13\33\3\34\3\34\3\34\3\34\3\34\3\34\7\34\u0131\n"+
		"\34\f\34\16\34\u0134\13\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u013d"+
		"\n\35\3\36\3\36\3\37\3\37\3\37\3\37\5\37\u0145\n\37\3 \3 \3 \3 \3 \5 "+
		"\u014c\n \3!\3!\7!\u0150\n!\f!\16!\u0153\13!\3!\2\7.\60\62\64\66\"\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@\2\b\3\2"+
		"#$\3\2%*\3\2+,\3\2-.\3\2\60\61\3\2\62\63\2\u0156\2B\3\2\2\2\4J\3\2\2\2"+
		"\6R\3\2\2\2\bX\3\2\2\2\n^\3\2\2\2\f`\3\2\2\2\16n\3\2\2\2\20r\3\2\2\2\22"+
		"t\3\2\2\2\24\u0080\3\2\2\2\26\u0085\3\2\2\2\30\u0087\3\2\2\2\32\u0096"+
		"\3\2\2\2\34\u009d\3\2\2\2\36\u00a2\3\2\2\2 \u00a4\3\2\2\2\"\u00ac\3\2"+
		"\2\2$\u00e5\3\2\2\2&\u00ec\3\2\2\2(\u00f4\3\2\2\2*\u00fa\3\2\2\2,\u00fc"+
		"\3\2\2\2.\u00fe\3\2\2\2\60\u0109\3\2\2\2\62\u0114\3\2\2\2\64\u011f\3\2"+
		"\2\2\66\u012a\3\2\2\28\u013c\3\2\2\2:\u013e\3\2\2\2<\u0144\3\2\2\2>\u014b"+
		"\3\2\2\2@\u014d\3\2\2\2BC\7\3\2\2CD\7\4\2\2DE\5\4\3\2EF\7\5\2\2FG\7\6"+
		"\2\2GH\5\"\22\2HI\7\7\2\2I\3\3\2\2\2JK\5\6\4\2KL\5\b\5\2LM\5\n\6\2M\5"+
		"\3\2\2\2NS\3\2\2\2OP\5\f\7\2PQ\5\6\4\2QS\3\2\2\2RN\3\2\2\2RO\3\2\2\2S"+
		"\7\3\2\2\2TY\3\2\2\2UV\5\22\n\2VW\5\b\5\2WY\3\2\2\2XT\3\2\2\2XU\3\2\2"+
		"\2Y\t\3\2\2\2Z_\3\2\2\2[\\\5\30\r\2\\]\5\n\6\2]_\3\2\2\2^Z\3\2\2\2^[\3"+
		"\2\2\2_\13\3\2\2\2`a\7\b\2\2ab\5@!\2bc\7\t\2\2cd\5\16\b\2de\7\n\2\2e\r"+
		"\3\2\2\2fo\5\20\t\2gh\7\13\2\2hi\7\f\2\2ij\7\60\2\2jk\7\r\2\2kl\7\16\2"+
		"\2lo\5\20\t\2mo\5@!\2nf\3\2\2\2ng\3\2\2\2nm\3\2\2\2o\17\3\2\2\2ps\7\17"+
		"\2\2qs\7\20\2\2rp\3\2\2\2rq\3\2\2\2s\21\3\2\2\2tu\7\21\2\2uv\5\24\13\2"+
		"vw\7\22\2\2wx\5\16\b\2xy\5\26\f\2yz\7\n\2\2z\23\3\2\2\2{\u0081\5@!\2|"+
		"}\5@!\2}~\7\23\2\2~\177\5\24\13\2\177\u0081\3\2\2\2\u0080{\3\2\2\2\u0080"+
		"|\3\2\2\2\u0081\25\3\2\2\2\u0082\u0083\7\24\2\2\u0083\u0086\5:\36\2\u0084"+
		"\u0086\3\2\2\2\u0085\u0082\3\2\2\2\u0085\u0084\3\2\2\2\u0086\27\3\2\2"+
		"\2\u0087\u0088\7\25\2\2\u0088\u0089\5@!\2\u0089\u008a\7\26\2\2\u008a\u008b"+
		"\5\32\16\2\u008b\u008c\7\27\2\2\u008c\u008d\5\36\20\2\u008d\u008e\7\6"+
		"\2\2\u008e\u008f\5\"\22\2\u008f\u0090\7\7\2\2\u0090\u0091\7\n\2\2\u0091"+
		"\31\3\2\2\2\u0092\u0097\3\2\2\2\u0093\u0094\5 \21\2\u0094\u0095\5\34\17"+
		"\2\u0095\u0097\3\2\2\2\u0096\u0092\3\2\2\2\u0096\u0093\3\2\2\2\u0097\33"+
		"\3\2\2\2\u0098\u009e\3\2\2\2\u0099\u009a\7\23\2\2\u009a\u009b\5 \21\2"+
		"\u009b\u009c\5\34\17\2\u009c\u009e\3\2\2\2\u009d\u0098\3\2\2\2\u009d\u0099"+
		"\3\2\2\2\u009e\35\3\2\2\2\u009f\u00a3\3\2\2\2\u00a0\u00a1\7\22\2\2\u00a1"+
		"\u00a3\5\16\b\2\u00a2\u009f\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\37\3\2\2"+
		"\2\u00a4\u00a5\5@!\2\u00a5\u00a6\7\22\2\2\u00a6\u00a7\5\16\b\2\u00a7!"+
		"\3\2\2\2\u00a8\u00a9\5$\23\2\u00a9\u00aa\5\"\22\2\u00aa\u00ad\3\2\2\2"+
		"\u00ab\u00ad\5$\23\2\u00ac\u00a8\3\2\2\2\u00ac\u00ab\3\2\2\2\u00ad#\3"+
		"\2\2\2\u00ae\u00af\5(\25\2\u00af\u00b0\5&\24\2\u00b0\u00b1\7\24\2\2\u00b1"+
		"\u00b2\5,\27\2\u00b2\u00b3\7\n\2\2\u00b3\u00e6\3\2\2\2\u00b4\u00b5\7\30"+
		"\2\2\u00b5\u00b6\5,\27\2\u00b6\u00b7\7\31\2\2\u00b7\u00ba\5\"\22\2\u00b8"+
		"\u00b9\7\32\2\2\u00b9\u00bb\5\"\22\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3"+
		"\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\7\33\2\2\u00bd\u00be\7\n\2\2\u00be"+
		"\u00e6\3\2\2\2\u00bf\u00c0\7\34\2\2\u00c0\u00c1\5,\27\2\u00c1\u00c2\7"+
		"\35\2\2\u00c2\u00c3\5\"\22\2\u00c3\u00c4\7\36\2\2\u00c4\u00c5\7\n\2\2"+
		"\u00c5\u00e6\3\2\2\2\u00c6\u00c7\7\37\2\2\u00c7\u00c8\5@!\2\u00c8\u00c9"+
		"\7\24\2\2\u00c9\u00ca\5,\27\2\u00ca\u00cb\7 \2\2\u00cb\u00cc\5,\27\2\u00cc"+
		"\u00cd\7\35\2\2\u00cd\u00ce\5\"\22\2\u00ce\u00cf\7\36\2\2\u00cf\u00d0"+
		"\7\n\2\2\u00d0\u00e6\3\2\2\2\u00d1\u00d2\5*\26\2\u00d2\u00d3\5@!\2\u00d3"+
		"\u00d4\7\26\2\2\u00d4\u00d5\5<\37\2\u00d5\u00d6\7\27\2\2\u00d6\u00d7\7"+
		"\n\2\2\u00d7\u00e6\3\2\2\2\u00d8\u00d9\7!\2\2\u00d9\u00e6\7\n\2\2\u00da"+
		"\u00db\7\"\2\2\u00db\u00dc\5,\27\2\u00dc\u00dd\7\n\2\2\u00dd\u00e6\3\2"+
		"\2\2\u00de\u00df\7\4\2\2\u00df\u00e0\5\4\3\2\u00e0\u00e1\7\5\2\2\u00e1"+
		"\u00e2\5\"\22\2\u00e2\u00e3\7\7\2\2\u00e3\u00e4\7\n\2\2\u00e4\u00e6\3"+
		"\2\2\2\u00e5\u00ae\3\2\2\2\u00e5\u00b4\3\2\2\2\u00e5\u00bf\3\2\2\2\u00e5"+
		"\u00c6\3\2\2\2\u00e5\u00d1\3\2\2\2\u00e5\u00d8\3\2\2\2\u00e5\u00da\3\2"+
		"\2\2\u00e5\u00de\3\2\2\2\u00e6%\3\2\2\2\u00e7\u00e8\7\24\2\2\u00e8\u00e9"+
		"\5(\25\2\u00e9\u00ea\5&\24\2\u00ea\u00ed\3\2\2\2\u00eb\u00ed\3\2\2\2\u00ec"+
		"\u00e7\3\2\2\2\u00ec\u00eb\3\2\2\2\u00ed\'\3\2\2\2\u00ee\u00f5\5@!\2\u00ef"+
		"\u00f0\5@!\2\u00f0\u00f1\7\f\2\2\u00f1\u00f2\5,\27\2\u00f2\u00f3\7\r\2"+
		"\2\u00f3\u00f5\3\2\2\2\u00f4\u00ee\3\2\2\2\u00f4\u00ef\3\2\2\2\u00f5)"+
		"\3\2\2\2\u00f6\u00f7\5(\25\2\u00f7\u00f8\7\24\2\2\u00f8\u00fb\3\2\2\2"+
		"\u00f9\u00fb\3\2\2\2\u00fa\u00f6\3\2\2\2\u00fa\u00f9\3\2\2\2\u00fb+\3"+
		"\2\2\2\u00fc\u00fd\5.\30\2\u00fd-\3\2\2\2\u00fe\u00ff\b\30\1\2\u00ff\u0100"+
		"\5\60\31\2\u0100\u0106\3\2\2\2\u0101\u0102\f\4\2\2\u0102\u0103\t\2\2\2"+
		"\u0103\u0105\5\60\31\2\u0104\u0101\3\2\2\2\u0105\u0108\3\2\2\2\u0106\u0104"+
		"\3\2\2\2\u0106\u0107\3\2\2\2\u0107/\3\2\2\2\u0108\u0106\3\2\2\2\u0109"+
		"\u010a\b\31\1\2\u010a\u010b\5\62\32\2\u010b\u0111\3\2\2\2\u010c\u010d"+
		"\f\4\2\2\u010d\u010e\t\3\2\2\u010e\u0110\5\62\32\2\u010f\u010c\3\2\2\2"+
		"\u0110\u0113\3\2\2\2\u0111\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112\61"+
		"\3\2\2\2\u0113\u0111\3\2\2\2\u0114\u0115\b\32\1\2\u0115\u0116\5\64\33"+
		"\2\u0116\u011c\3\2\2\2\u0117\u0118\f\4\2\2\u0118\u0119\t\4\2\2\u0119\u011b"+
		"\5\64\33\2\u011a\u0117\3\2\2\2\u011b\u011e\3\2\2\2\u011c\u011a\3\2\2\2"+
		"\u011c\u011d\3\2\2\2\u011d\63\3\2\2\2\u011e\u011c\3\2\2\2\u011f\u0120"+
		"\b\33\1\2\u0120\u0121\5\66\34\2\u0121\u0127\3\2\2\2\u0122\u0123\f\4\2"+
		"\2\u0123\u0124\t\5\2\2\u0124\u0126\5\66\34\2\u0125\u0122\3\2\2\2\u0126"+
		"\u0129\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128\65\3\2\2"+
		"\2\u0129\u0127\3\2\2\2\u012a\u012b\b\34\1\2\u012b\u012c\58\35\2\u012c"+
		"\u0132\3\2\2\2\u012d\u012e\f\4\2\2\u012e\u012f\7/\2\2\u012f\u0131\58\35"+
		"\2\u0130\u012d\3\2\2\2\u0131\u0134\3\2\2\2\u0132\u0130\3\2\2\2\u0132\u0133"+
		"\3\2\2\2\u0133\67\3\2\2\2\u0134\u0132\3\2\2\2\u0135\u0136\7\26\2\2\u0136"+
		"\u0137\5,\27\2\u0137\u0138\7\27\2\2\u0138\u013d\3\2\2\2\u0139\u013d\5"+
		"(\25\2\u013a\u013d\5:\36\2\u013b\u013d\3\2\2\2\u013c\u0135\3\2\2\2\u013c"+
		"\u0139\3\2\2\2\u013c\u013a\3\2\2\2\u013c\u013b\3\2\2\2\u013d9\3\2\2\2"+
		"\u013e\u013f\t\6\2\2\u013f;\3\2\2\2\u0140\u0141\5,\27\2\u0141\u0142\5"+
		"> \2\u0142\u0145\3\2\2\2\u0143\u0145\3\2\2\2\u0144\u0140\3\2\2\2\u0144"+
		"\u0143\3\2\2\2\u0145=\3\2\2\2\u0146\u0147\7\23\2\2\u0147\u0148\5,\27\2"+
		"\u0148\u0149\5> \2\u0149\u014c\3\2\2\2\u014a\u014c\3\2\2\2\u014b\u0146"+
		"\3\2\2\2\u014b\u014a\3\2\2\2\u014c?\3\2\2\2\u014d\u0151\7\63\2\2\u014e"+
		"\u0150\t\7\2\2\u014f\u014e\3\2\2\2\u0150\u0153\3\2\2\2\u0151\u014f\3\2"+
		"\2\2\u0151\u0152\3\2\2\2\u0152A\3\2\2\2\u0153\u0151\3\2\2\2\33RX^nr\u0080"+
		"\u0085\u0096\u009d\u00a2\u00ac\u00ba\u00e5\u00ec\u00f4\u00fa\u0106\u0111"+
		"\u011c\u0127\u0132\u013c\u0144\u014b\u0151";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}