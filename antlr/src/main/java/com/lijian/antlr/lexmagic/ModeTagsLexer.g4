lexer grammar ModeTagsLexer;

// 默认模式下的规则
// 切换到ISLAND模式
OPEN:'<'->mode(ISLAND);
// 收集所有的文本
TEXT:~'<'+;

mode ISLAND;
// 回到 SEA模式
CLOSE:'>'->mode(DEFAULT_MODE);
SLASH:'/';
// 匹配标签中的ID并将其输送给语法分析器
ID:[a-zA-Z]+;