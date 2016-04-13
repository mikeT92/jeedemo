package edu.hm.cs.fwp.jeedemo.jpa.core.persistence;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.persistence.Query;

/**
 * Container für benannte SQL-Parameter.
 * @author theism
 * @version 1.0
 * @since R2016.1 16.04.2015
 */
public final class NamedQueryParameters implements QueryParameters {
    /**
     * Builder für {@code NamedQueryParameters}.
     */
    public static final class Builder {
        /**
         * Map mit allen benannten Parametern.
         */
        private final Map<String, Object> parametersByName = new LinkedHashMap<String, Object>();
        /**
         * Übernimmt den angegebenen SQL-Parameter.
         * 
         * @param name
         * @param value
         * @return {@code this} für Konkatenation von Methoden-Aufrufen (Fluid API)
         */
        public Builder withParameter(String name, Object value) {
            this.parametersByName.put(name, value);
            return this;
        }
        /**
         * Erstellt eine Instanz vom Typ {@code NamedQueryParameters} mit allen zuvor angegebenen SQL-Parametern.
         * @return {@code NamedQueryParameters}
         */
        public QueryParameters build() {
            return new NamedQueryParameters(parametersByName);
        }
    }
    /**
     * Alle zu übergebenden SQL-Parameter
     */
    private final Map<String, Object> parametersByName;
    /**
     * Erzeugt eine vollständig initialisierte Instanz.
     * 
     * @param parametersByName alle zu übergebenden SQL-Parameter
     */
    private NamedQueryParameters(Map<String, Object> parametersByName) {
        this.parametersByName = parametersByName;
    }
    /**
     * @see de.audi.lion.common.core.persistence.QueryParameters#applyParameters(javax.persistence.Query)
     */
    @Override
    public void applyParameters(Query query) {
        for (Map.Entry<String, Object> current : this.parametersByName.entrySet()) {
            query.setParameter(current.getKey(), current.getValue());
        }
    }
}
