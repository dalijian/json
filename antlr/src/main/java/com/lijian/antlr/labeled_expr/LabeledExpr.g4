grammar LabeledExpr;
import CommonLexerRules;

prog : stat+;
// # 给 备选 分支 添加 标签 用于 生成 方法
stat: expr NEWLINE  # printExpr
    | ID '='expr NEWLINE #assign
    | NEWLINE       # blank
    ;

//  op 是 定义 子表达式 名()   ,
expr: expr op=('*'|'/') expr #MulDiv
    | expr op=('+'|'-') expr #AddSub
    | INT                    # int
    | ID                     # id
    | '(' expr ')'           # parens
    ;

