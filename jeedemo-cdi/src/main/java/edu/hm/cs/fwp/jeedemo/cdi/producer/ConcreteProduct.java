/*
 * ConcreteProduct.java
 * jeedemo-cdi
 */
package edu.hm.cs.fwp.jeedemo.cdi.producer;

/**
 * Konkrete Implementierung eines {@link Product}s.
 * <p>
 * Zur Erzeugung dieses Produktes wird zum Beispiel das {@code Builder} Pattern
 * verwendet.
 * </p>
 * 
 * @author theism
 *
 */
public class ConcreteProduct implements Product {

	/**
	 * {@code Builder} für ein {@code ConcreteProduct}.
	 */
	public static final class Builder {
		private String complexConfigParameter;

		public Builder withComplexConfigParameter(String configParameter) {
			this.complexConfigParameter = configParameter;
			return this;
		}

		public ConcreteProduct build() {
			if (this.complexConfigParameter == null) {
				throw new IllegalStateException("Erforderlicher Wert [complexConfigParameter] fehlt!");
			}
			return new ConcreteProduct(this.complexConfigParameter);
		}
	}

	private final String complexConfigParameter;

	/**
	 * Privater Konstruktor, damit Instanzen dieser Klasse nur über den Builder
	 * erzeugt werden können.
	 *
	 * @param configParameter
	 */
	private ConcreteProduct(String configParameter) {
		this.complexConfigParameter = configParameter;
	}

	/**
	 * @see edu.hm.cs.fwp.jeedemo.cdi.producer.Product#doSomething(java.lang.String)
	 */
	@Override
	public String doSomething(String what) {
		return "done: " + what;
	}

}
