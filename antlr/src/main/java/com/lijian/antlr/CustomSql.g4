grammar CustomSql;

init : '{' value (',' value)* '}' ;
/**
通过 递归 的 方式 实现 无限匹配
*/
value :init
        | INT
        ;
INT : [0-9]+;
WS : [\t\r\n]+ -> skip;