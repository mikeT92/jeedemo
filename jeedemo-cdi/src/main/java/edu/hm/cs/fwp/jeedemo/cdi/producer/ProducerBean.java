/*
 * ProducerBean.java
 * jeedemo-cdi
 */
package edu.hm.cs.fwp.jeedemo.cdi.producer;

import javax.enterprise.inject.Produces;

/**
 * {@code Factory} für {@link Product}s, deren Instanziierung zu komplex für den
 * CDI-Container ist.
 * <p>
 * Demonstriert die Anwendung der Annotation {@code @Produces}, mit der
 * Factory-Methoden für komplexe Objekte beim CDI-Container registriert werden
 * können.
 * </p>
 * 
 * @author theism
 *
 */
public class ProducerBean {

	/**
	 * Factory-Methode, die vom CDI-Container aufgerufen wird, sobald ein Bean
	 * ein {@code Product} injiziert bekommen will.
	 *
	 * @return konkretes Produkt
	 */
	@Produces
	public Product createProduct() {
		return new ConcreteProduct.Builder().withComplexConfigParameter("complexConfigParameter").build();
	}
}
