grammar Expr;
import CommonLexerRules;
@header { package tools;
import java.util.*;
}
@parser::members{
/**
  memory 字段 用于 存储变量 / 变量值对
*/
Map<String,Integer>memory = new HashMap();
int eval(int left,int op, int right){
    switch(op){
        case MUL: return left * right;
        case DIV: return left / right;
        case ADD: return left+right;
        case SUB: return left-right;
        }
        return 0;
    }
}
stat: e NEWLINE {System.out.println($e.v);}
    | ID '='e NEWLINE {memory.put($ID.text,$e.v);}
    | NEWLINE
    ;
/**
新建 规则 e , 指定 返回值 名称 v
*/
e returns [int v]
    : a=e op=('*'|'/')b=e{$v=eval($a.v,$op.type,$b.v);}
    | a=e op=('+'|'=')b=e{$v=eval($a.v,$op.type,$b.v);}
    | INT {$v=$INT.int;}
    |ID
    {String id =$ID.text;
    $v=memory.containsKey(id)?memory.get(id):0;}
    |'(' e')'{$v=$e.v;}
    ;