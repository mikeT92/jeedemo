/*
 * jeedemo-jaxrs:TaskCategory.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.jaxrs.core.tasks.entity;

/**
 * Enumeration representing the various categories of a {@link Task}.
 * 
 * @author theism
 * @version 1.0
 * @since 09.03.2017
 */
public enum TaskCategory {

	UNDEFINED, //
	BUGFIX, //
	REFACTORING, //
	NEW_FEATURE, //
	PERFORMANCE_IMPROVEMENT, //
	RELEASE_MANAGEMENT, //
	QUALITY_ASSURANCE, //
	BUILD_FAILURE, //
	COMMUNICATION
}
