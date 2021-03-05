grammar Enum;
@parser::members{ public static boolean java5;}
prog:(stat|enumDecl)+;
stat:id '='expr';'{System.out.println($id.text+"="+$expr.text);};
expr:id|INT;
//enumDecl:'enum' name=id'{'id(','id)*'}'
//    {System.out.println("enum"+$name.text);};

enumDecl:{java5}?'enum'name=id '{'id(','id)*'}'
            {System.out.println("enum"+$name.text);};

// {!java5}? 判定允许enum 在非 java 5 模式 下 作出 普通 标识符 使用 ， 关闭 第二个 备选 分支
id:ID
    |{!java5}?'enum';
ID:[a-zA-Z]+;
INT:[0-9]+;


