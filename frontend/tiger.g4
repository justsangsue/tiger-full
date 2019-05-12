grammar tiger;

program
	: 'main' 'let' declaration_segment 'in' 'begin' stat_seq 'end'				
	;

declaration_segment
	: type_declaration_list var_declaration_list function_declaration_list
	;

type_declaration_list
	: 
	| type_declaration type_declaration_list
	;

var_declaration_list
	: 
    | var_declaration var_declaration_list
    ;

function_declaration_list					
	: 
    | function_declaration function_declaration_list
    ;

type_declaration
	: 'type' id '=' type ';'													#TypeDec
	;

type
	: type_id 																	
	| 'array' '[' INTLIT ']' 'of' type_id 										
	| id 																		
	;

type_id
	: 'int' 																	#Integer
	| 'float'																	#Float
	; 

var_declaration
	: 'var' id_list ':' type optional_init ';'									#VarDec
	;

id_list
	: id
	| id ',' id_list
	; 

optional_init
	: ':=' r_const
	| 
	;

function_declaration
	: 'function' id '(' param_list ')' ret_type 'begin' stat_seq 'end' ';'		#FuncDec
	;

param_list
	: 
	| param param_list_tail
	;

param_list_tail
	: 
	| ',' param param_list_tail
	;

ret_type
	: 
	| ':' type
	;

param
	: id ':' type
	;

stat_seq
	: stat stat_seq
	| stat 
	;

stat
	: lvalue l_tail ':=' expr ';'												#Assign
	| 'if' expr 'then' stat_seq ('else' stat_seq)? 'endif' ';'   				#IfStmt	
	| 'while' expr 'do' stat_seq 'enddo' ';'									#WhileStmt
	| 'for' id ':=' expr 'to' expr 'do' stat_seq 'enddo' ';'					#ForStmt
	| opt_prefix id '(' expr_list ')' ';'										#Call
	| 'break' ';'																#Break
	| 'return' expr ';'															#Return
	| 'let' declaration_segment 'in' stat_seq 'end' ';'							#LET
	;

l_tail
	: ':=' lvalue l_tail
	| 
	;

lvalue
	: id
	| id '[' expr ']'
	;

opt_prefix
	: lvalue ':='
	| 
	;

expr
	: log										 			   	
	;

log
	: log ('|' | '&') comp				
	| comp															 			   	
	;

comp
	: comp ('==' | '!=' | '<' | '>' | '<=' | '>=') add
    | add 																		
	;

add
	: add ('+' | '-') mul 														
	| mul 									
	;

mul
	: mul ('*' | '/') exp 														
	| exp 												
	;

exp
	: exp '**' paren  															
	| paren																
	;

paren
	: '(' expr ')' 																
	| lvalue 																	
	| r_const 	
	|
	; 

r_const
	: INTLIT
	| FLOATLIT
	;

expr_list
	: expr expr_list_tail
	| 
	;

expr_list_tail
	: ',' expr expr_list_tail
	| 
	;

id
	: LETTER (LETTER | DIGIT)*
	;

INTLIT
	: [0-9]+
	;

FLOATLIT
	: DIGIT+ '.' DIGIT+
	;

DIGIT
	: [0-9]
	;

LETTER
	: [a-zA-Z]
	| '_'
	;

COMMENT
	: '/*' .*? '*/' -> skip
	;

LINE_COMMENT
	: '//' .*? '\n' -> skip
	;

WHITESPACE
	: [ \n\t\r]+ -> skip
	;
