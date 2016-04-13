/* Role.java @(#)%PID%
 */
package edu.hm.cs.fwp.jeedemo.jpa.users.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Role that can be assigned to a JEETRAIN user.
 *  
 * @author Michael Theis
 */
@Entity
@Table(name = "T_ROLE")
@NamedQueries({@NamedQuery(name=Role.QUERY_ALL, query = "SELECT r FROM Role r")})
public class Role implements Serializable {

	private static final String QUERY_NAME_PREFIX = "edu.hm.cs.fwp.jeetrain.business.users.entity.Role.";
	
	public static final String QUERY_ALL = QUERY_NAME_PREFIX + "QUERY_ALL";
	
	@Id
	@Column(name = "ROLE_ID")
	private long id;
	
	@Column(name = "ROLE_NAME")
	private String name;

	public Role() {
	}

	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Role other = (Role) obj;
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}
}