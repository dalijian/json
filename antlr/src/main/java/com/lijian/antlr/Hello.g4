grammar Hello;
@header {package com.lijian.antlr;}
r : 'hello' ID;
ID : [a-z]+;
WS : [\t\r\n]+ -> skip;
