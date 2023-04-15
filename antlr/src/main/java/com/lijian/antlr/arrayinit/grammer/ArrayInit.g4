// 语法文件 已 grammar 关键字 开头
grammar ArrayInit;
//定义语法规则 init
init : '{' value (',' value)* '}' ;
/**
通过 递归 的 方式 实现 无限匹配
*/
value :init
        | INT   // |  表示 备选 分支
        ;
// 定义词法规则
INT : [0-9]+;
WS : [\t\r\n]+ -> skip;
