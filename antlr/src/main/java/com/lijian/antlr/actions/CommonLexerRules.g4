lexer grammar CommonLexerRules;

/**
词法分析器 大写 字母 开头
*/
ID:[a-zA-Z]+; // 匹配 标识符
INT:[0-9]+;     // 匹配 整数
NEWLINE:'r'?'\n'; // 告诉 语法 分析器 一个 新行 的 开始 (即语句终止标志)
WS:[\t]+->skip; // 丢弃 空白 字符