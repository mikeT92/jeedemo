package edu.hm.cs.fwp.jeedemo.jpa.common.persistence.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Query;

/**
 * Konkrete Implementierung von {@link QueryParameters}, die benannte Parameter
 * unterstützt.
 *
 * @author Michael Theis (msg)
 * @version 1.0
 * @since release 18.2
 */
public final class NamedQueryParameters implements QueryParameters {
	public static final class Builder {
		private final Map<String, Object> parametersByName = new LinkedHashMap<String, Object>();

		public Builder withParameter(String name, Object value) {
			this.parametersByName.put(name, value);
			return this;
		}

		public QueryParameters build() {
			return new NamedQueryParameters(this.parametersByName);
		}
	}

	private final Map<String, Object> parametersByName;

	private NamedQueryParameters(Map<String, Object> parametersByName) {
		this.parametersByName = parametersByName;
	}

	@Override
	public void applyParameters(Query query) {
		for (final Map.Entry<String, Object> current : this.parametersByName.entrySet()) {
			query.setParameter(current.getKey(), current.getValue());
		}
	}
}
