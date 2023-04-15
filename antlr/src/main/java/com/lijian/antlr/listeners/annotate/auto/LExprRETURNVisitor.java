// Generated from C:/Users/lijian/IdeaProjects/json/antlr/src/main/java/com.lijian.antlr/listeners/annotate\LExprRETURN.g4 by ANTLR 4.9.1
package com.lijian.antlr.listeners.annotate.auto;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LExprRETURNParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LExprRETURNVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code Add}
	 * labeled alternative in {@link LExprRETURNParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(LExprRETURNParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Mult}
	 * labeled alternative in {@link LExprRETURNParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMult(LExprRETURNParser.MultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link LExprRETURNParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(LExprRETURNParser.IntContext ctx);
}