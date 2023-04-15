grammar PropertyFile;
// 内嵌 代码 中
//file: {<<start file>>} prop+{<<finish file>>};
//prop: ID'='STRING '|'{<<process property>>};
//ID:[a-z]+;
//STRING:'"'.*?'"';
// 使用 子类 实现
@members{
    void startFile(){}
    void finishFile(){}
    void defineProperty(Token name,Token value){}

}
file:{startFile();}prop+{finishFile();};
prop:ID'='STRING '\n'{defineProperty($ID,$STRING)};
ID:[a-z]+;
STRING:'"'.*?'"';

