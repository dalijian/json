grammar CSV;

file :hdr row+;
/**
标题行
*/
hdr :row;
row :field(',' field)* '\r'?'\n';
field: TEXT #text
    |STRING # string
    |       # empty
    ;
    // TEXT 类型 是 下一个 逗号或者 换行符 之前 的任意 字符 序列
TEXT:~[,\n\r"]+;
// 两个双引号是对双引号的转义
STRING:'"'('""'|~'"')*'"';
