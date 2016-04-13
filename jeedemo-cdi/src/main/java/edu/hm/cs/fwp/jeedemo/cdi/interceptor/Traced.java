/*
 * Traced.java
 * jeedemo-cdi
 */
package edu.hm.cs.fwp.jeedemo.cdi.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/**
 * Interceptor-Binding-Annotation für den {@code TraceInterceptor}.
 * <p>
 * Die Methodenaufrufe aller Methoden einer Klasse und oder einer spezifischen Methode, 
 * die mit dieser Annotation markiert sind, werden protokolliert.
 * </p>
 * @author theism
 *
 */
@Inherited
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Traced {

}
