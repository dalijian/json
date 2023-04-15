// Generated from C:/Users/lijian/IdeaProjects/json/antlr/src/main/java/com.lijian.antlr/listeners/annotate\LExprRETURN.g4 by ANTLR 4.9.1
package com.lijian.antlr.listeners.annotate.auto;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link LExprRETURNVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class LExprRETURNBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements LExprRETURNVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitAdd(LExprRETURNParser.AddContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitMult(LExprRETURNParser.MultContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitInt(LExprRETURNParser.IntContext ctx) { return visitChildren(ctx); }
}