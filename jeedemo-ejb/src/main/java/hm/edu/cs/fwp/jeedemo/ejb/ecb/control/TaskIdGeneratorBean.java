/*
 * TaskIdGeneratorBean.java
 * jeedemo-ejb
 */
package hm.edu.cs.fwp.jeedemo.ejb.ecb.control;

import javax.inject.Named;

/**
 * Generator für Task-IDs.
 * @author theism
 *
 */
@Named
public class TaskIdGeneratorBean {
	
	/**
	 * Liefert die nächste Task-ID für einen neuen Task zum angegebenen Projekt zurück.
	 *
	 * @param projectId eindeutiger Bezeichner des Projekt, für das eine neue Task-ID generiert werden soll.
	 * @return neue Task-ID
	 */
	public String nextTaskId(String projectId) {
		return projectId + "-" + System.currentTimeMillis() % 1000;
	}
}
