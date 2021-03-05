grammar Tags;
file:(TAG|ENTITY|TEXT|CDATA)*;
COMMENT:'<!--'.*?'-->'->skip;
CDATA:'<![CDATA['.*?']]>';
// 必须放置在其他类似标签的结构之后
TAG:'<'.*?'>';
ENTITY:'&'.*?';';
// 除<和&之外的任意字符序列
TEXT:~[<&]+;
