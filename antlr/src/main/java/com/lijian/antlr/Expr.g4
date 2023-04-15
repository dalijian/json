grammar Expr;
import CommonLexerRules;

/**
起始规则， 语法 分析 的 起点  语法 规则 小写 字母 开头
*/
prog : stat+;

stat: expr NEWLINE
    | ID'='expr NEWLINE
    |NEWLINE
    ;
   /**
   使用 | 来 分隔 同一 语言 规则 的若干 备选 分支，
   （） 使用 圆括号 把 一些 符号 组合 成 子 规则  例如 子规则 （‘*’|‘/‘） 匹配 一个 乘法 符号 或者 一个 除法符号
   */
expr:expr('*'|'/') expr
    |expr('+'|'-')expr
    |INT
    |ID
    |'('expr')'
    ;

