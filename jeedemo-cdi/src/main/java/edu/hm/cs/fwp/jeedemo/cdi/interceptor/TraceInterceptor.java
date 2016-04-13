/*
 * TraceInterceptor.java
 * jeedemo-cdi
 */
package edu.hm.cs.fwp.jeedemo.cdi.interceptor;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * {@code Interceptor}, der Methodenaufrufe von Klassen oder Methoden, die mit
 * {@code @Traced} annotiert sind, protokolliert.
 * 
 * @author theism
 * @version 1.0
 * @since Release 2016.1
 */
@Traced
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class TraceInterceptor {
	@AroundInvoke
	public Object traceMethodInvocation(InvocationContext context) throws Exception {
		Object result = null;
		System.out.println(buildBeforeInvocationEntry(context));
		try {
			result = context.proceed();
			System.out.println(buildAfterInvocationEntry(context, result));
		} catch (Exception ex) {
			System.err.println(buildOnExceptionEntry(context, ex));
		}
		return result;
	}

	private String buildBeforeInvocationEntry(InvocationContext context) {
		return String.format(">>> Rufe Methode [%s.%s]", context.getMethod().getDeclaringClass().getSimpleName(),
				context.getMethod().getName());
	}

	private String buildAfterInvocationEntry(InvocationContext context, Object returnValue) {
		return String.format("<<< Kehre erfolgreich aus Methode [%s.%s] mit Rückgabewert [%s] zurück",
				context.getMethod().getDeclaringClass().getSimpleName(), context.getMethod().getName(), returnValue);
	}

	private String buildOnExceptionEntry(InvocationContext context, Exception caught) {
		return String.format("<<< Kehre nach Exception [%s] aus Methode [%s.%s] zurück", caught,
				context.getMethod().getDeclaringClass().getSimpleName(), context.getMethod().getName());
	}
}
