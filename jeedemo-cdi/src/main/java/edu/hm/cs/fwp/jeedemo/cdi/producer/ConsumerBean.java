/*
 * ConsumerBean.java
 * jeedemo-cdi
 */
package edu.hm.cs.fwp.jeedemo.cdi.producer;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * CDI-mananged Bean, das ein Produkt eines {@code Producer}s injiziert bekommt.
 * <p>
 * Der Consumer kennt weder die Interna des injizierten Produktes noch weiß er,
 * wie es hergestellt wird.
 * </p>
 * 
 * @author theism
 */
@Named
public class ConsumerBean {
	
	@Inject
	private Product product;
	
	public String useProduct(String what) {
		return this.product.doSomething(what);
	}
}
