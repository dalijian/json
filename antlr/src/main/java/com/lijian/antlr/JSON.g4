grammar JSON;

json: object
    |array
    ;
// 对象 {}
object: '{' pair(',' pair)*'}'
    | '{' '}' // 空对象
    ;
pair : STRING ':' value;

// 数组 []
array
    : '[' value (',' value)* ']'
    | '[' ']' // 空数组
    ;

value:
      STRING
      |NUMBER
      |object // 递归调用
      |array  //递归调用
      |'true' //关键字
      |'false'
      |'null'
      ;

STRING: '"'(ESC|~["\\])*'"';

fragment ESC: '\\'(["\\/bfnrt] | UNICODE );
fragment UNICODE: 'u' HEX HEX HEX HEX;
fragment HEX: [0-9a-fA-F];

NUMBER : '-'?INT '.' INT EXP? // 1.23
        | '-'?INT EXP //1e10
        | '-'?INT //-3
        ;
fragment INT: '0'|[1-9][0-9]*;
fragment EXP:[Ee][+\-]?INT;
WS:[\t\n\r]+ ->skip;