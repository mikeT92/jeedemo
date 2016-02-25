/*
 * Product.java
 * jeedemo-cdi
 */
package edu.hm.cs.fwp.jeedemo.cdi.producer;

/**
 * Interface eines Produktes, das von einem Producer hergestellt wird.
 * <p>
 * Ein Interface für das Produkt ist an dieser Stelle eigentlich nicht zwingend
 * erforderlich, trägt aber zur losen Kopplung zwischen Consumer und Produkt
 * bei.
 * </p>
 * 
 * @author theism
 */
public interface Product {

	String doSomething(String what);
}
