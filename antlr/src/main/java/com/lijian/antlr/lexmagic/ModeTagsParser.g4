parser grammar ModeTagsParser;
// 使用 ModeTagsLexer.g4中的词法符号
options{tokenVocab=ModeTagsLexer;}

file:(tag|TEXT)*;
tag:'<'ID'>'
    |'<' '/' ID '>'
    ;
