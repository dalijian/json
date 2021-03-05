grammar Cymbol;

@lexer::members{
    public static final int WHITESPACE=1;
    public static final int COMMENTS=2;
}
WS:[\t\n\r]+->channel(WHITESPACE);

SL_COMMENT:'//'.*?'\n'->channel(COMMENTS);