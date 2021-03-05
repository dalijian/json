grammar Enum2;
tokens{ENUM}
stat:ID'='expr';'{System.out.println($ID.text+"="$expr.text);};

expr:ID|INT;
// 判定 出现 在 词法 规则的右侧，而非像文法规则一样的左侧
// 词法分析器不进行备选分支的预测，它们仅仅寻找最长的匹配文本，然后在发现整个词法符号后做出抉择
// 当 java5 为假时，该判定关闭了ENUM规则。当它为真时，ENUM和ID同时匹配了字符序列e-n-u-m，ANTLR总是通过选择位置靠前的规则来解决词法歧义问题。
ENUM:'enum'{java5}?; // 必须放置在ID规则之前
ID:[a-zA-Z]+;
// 这里无需判定，因为 ‘enum' 词法符号 在 ！java5的情况 下并未定义
enumDecl:'enum' name=ID'{'ID(','ID)*'}'{System.out.println("enum "+$name.text);};
ID:[a-zA-Z]+{ if (java5&&getText().equals("enum"))setType(Enum2Parser.ENUM);};

