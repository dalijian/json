grammar Keywords;
@lexer::header{
    import java.util.*;
}
@lexer::members{
    Map<String,Integer> keywords = new HashMap<String,Integer>(){{
    put("begin",KeywordsParser.BEGIN);
    put("end",KeywordsParser.END);
    put("if",KeywordsParser.IF);
    put("then",KeywordsParser.THEN);
    put("while",KeywordsParser.WHILE);

    }
}
}
// 显示定义 关键 子的 词法符号类型，避免 隐式 定以 产生的 警告
tokens {BEGIN,END,IF,THEN,WHILE,INT,CHAR}
stat: BEGIN stat* END
    | IF expr THEN stat
    | WHILE expr stat
    | ID '=' expr ';';
expr: INT|CHAR;

ID: [a-zA-Z]+
    {
    if(keywords.containsKey(getText())){
        setType(keywords.get(getText())); // 重置 词法 符号 类型
        }
        };