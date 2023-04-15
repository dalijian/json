// Generated from C:/Users/lijian/IdeaProjects/json/antlr/src/main/java/com.lijian.antlr/listeners/annotate\LExprRETURN.g4 by ANTLR 4.9.1
package com.lijian.antlr.listeners.annotate.auto;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LExprRETURNParser}.
 */
public interface LExprRETURNListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code Add}
	 * labeled alternative in {@link LExprRETURNParser#e}.
	 * @param ctx the parse tree
	 */
	void enterAdd(LExprRETURNParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Add}
	 * labeled alternative in {@link LExprRETURNParser#e}.
	 * @param ctx the parse tree
	 */
	void exitAdd(LExprRETURNParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Mult}
	 * labeled alternative in {@link LExprRETURNParser#e}.
	 * @param ctx the parse tree
	 */
	void enterMult(LExprRETURNParser.MultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Mult}
	 * labeled alternative in {@link LExprRETURNParser#e}.
	 * @param ctx the parse tree
	 */
	void exitMult(LExprRETURNParser.MultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Int}
	 * labeled alternative in {@link LExprRETURNParser#e}.
	 * @param ctx the parse tree
	 */
	void enterInt(LExprRETURNParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link LExprRETURNParser#e}.
	 * @param ctx the parse tree
	 */
	void exitInt(LExprRETURNParser.IntContext ctx);
}