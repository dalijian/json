grammar LExprRETURN;
e returns [int value]
    :e '*' e #Mult
    |e '+'e #Add
    |INT #Int
    ;
INT:[0-9]+;