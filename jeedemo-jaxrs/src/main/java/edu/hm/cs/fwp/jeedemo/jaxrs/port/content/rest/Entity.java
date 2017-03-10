/*
 * jeedemo-jaxrs:Entity.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.jaxrs.port.content.rest;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Simple {@code Entity} to demonstrate REST content negotiation.
 * 
 * @author theism
 * @version 1.0
 * @since 10.03.2017
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entity implements Serializable {

	private static final long serialVersionUID = 8825093323670037842L;

	private long id;

	private String name;

	private String description;

	public Entity() {
		// default constructor for JAXB and JPA only!
	}

	public Entity(long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
