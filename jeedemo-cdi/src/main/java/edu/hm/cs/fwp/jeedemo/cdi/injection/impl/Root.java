/*
 * jeedemo-cdi:Root.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.cdi.injection.impl;

import javax.inject.Inject;

/**
 * TODO: add documentation!
 * 
 * @author mikeT92
 * @version 1.0
 * @since 23.03.2018
 */
public class Root {

	@Inject
	ConsumerBean consumer;

	public void useConsumer() {
		this.consumer.service = null;
	}

}
