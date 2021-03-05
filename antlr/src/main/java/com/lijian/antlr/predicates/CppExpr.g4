grammar CppExpr;

expr: {<<isfunc(ID)>>}?ID'('expr')' // 一个 参数 的函数 调用
    |{<<istype(ID)>>}?ID'('expr')'// 构造器风格的对expr 的转换
    |INT
    |ID ;