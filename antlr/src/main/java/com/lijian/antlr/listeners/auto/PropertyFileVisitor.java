// Generated from C:/Users/lijian/IdeaProjects/json/antlr/src/main/java/com.lijian.antlr/listeners\PropertyFile.g4 by ANTLR 4.9.1
package com.lijian.antlr.listeners.auto;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PropertyFileParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PropertyFileVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PropertyFileParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(PropertyFileParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link PropertyFileParser#prop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProp(PropertyFileParser.PropContext ctx);
}