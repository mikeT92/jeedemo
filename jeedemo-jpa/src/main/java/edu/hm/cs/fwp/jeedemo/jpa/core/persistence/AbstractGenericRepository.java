package edu.hm.cs.fwp.jeedemo.jpa.core.persistence;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

/**
 * Generisches {@code Repository} als dünne Schicht über einem
 * {@link EntityManager}, um gemeinsame JPA-Zugriffe immer auf die gleiche Art
 * und Weise zu lösen.
 * 
 * @author theism
 * @version 1.0
 * @since R2016.1 24.03.2015
 */
public abstract class AbstractGenericRepository {

	/**
	 * Fügt die angegebene Entität in den Datastore ein und synchronisiert diese
	 * mit datastore-seitig generierten beziehungsweise veränderten Feldern.
	 * 
	 * @param entity
	 *            hinzuzufügende Entität (erforderlich)
	 */
	public void addEntity(@NotNull Object entity) {
		getEntityManager().persist(entity);
		getEntityManager().flush();
		getEntityManager().refresh(entity);
	}

	/**
	 * Liefert die Entität vom angebenen Typ mit dem angebenen eindeutigen
	 * technischen Bezeichner zurück.
	 * 
	 * @param entityType
	 *            erwartete Entitätentyp
	 * @param entityId
	 *            eindeutiger technischer Bezeichner
	 * @return gefundene Entität, falls eine Entität mit dem angebenen
	 *         Bezeichner exisitert; sonst {@code null}
	 */
	public <T> T getEntityById(@NotNull Class<T> entityType, @NotNull Object entityId) {
		return getEntityManager().find(entityType, entityId);
	}

	/**
	 * Liefert die Entität vom angebenen Typ mit dem angebenen eindeutigen
	 * technischen Bezeichner zurück, wobei erwartet wird, dass eine Entität mit
	 * dem angegeben Beeichner existiert.
	 * 
	 * @param entityType
	 *            erwartete Entitätentyp
	 * @param entityId
	 *            eindeutiger technischer Bezeichner
	 * @return gefundene Entität, falls eine Entität mit dem angebenen
	 *         Bezeichner exisitert; niemals {@code null}
	 * @throws NoSuchElementException
	 *             - falls keine Entität mit dem angegebenen Bezeichner gefunden
	 *             werden kann.
	 */
	public @NotNull <T> T getRequiredEntityById(@NotNull Class<T> entityType, @NotNull Object entityId) {
		T result = getEntityById(entityType, entityId);
		if (result == null) {
			throw new NoSuchElementException("Missing required entity of type [" + entityType.getName()
					+ "] with unique identifier [" + entityId + "]!");
		}
		return result;
	}

	/**
	 * Aktualisiert den Zustand der Entität im Datastore mit der angegebenen
	 * Entität.
	 * <p>
	 * Die angegebene Entität muss zuvor mit {{@link #addEntity(Object)} dem
	 * Datastore hinzugefügt worden sein.
	 * </p>
	 * 
	 * @param entity
	 *            zu aktualisierende Entität
	 */
	public void setEntity(@NotNull Object entity) {
		getEntityManager().merge(entity);
	}

	/**
	 * Entfernt die angegebene Entität aus dem Datastore.
	 * <p>
	 * Die angegebene Entität muss zuvor mit {{@link #addEntity(Object)} dem
	 * Datastore hinzugefügt worden sein.
	 * </p>
	 * 
	 * @param entity
	 *            zu löschende Entität
	 */
	public void removeEntity(@NotNull Object entity) {
		Object mergedEntity = getEntityManager().merge(entity);
		getEntityManager().remove(mergedEntity);
	}

	/**
	 * Entfernt die Entität mit dem angegebenen eindeutigen Bezeichner aus dem
	 * Datastore.
	 * <p>
	 * Achtung: Beim Löschen über ID ist zu beachten, dass optimistische Sperren
	 * nicht geprüft werden können.
	 * </p>
	 * 
	 * @param entityType
	 *            erwarteter Entitätentyp
	 * @param entityId
	 *            eindeutiger technischer Bezeichner der zu löschende Entität
	 */
	public <T> void removeEntityById(@NotNull Class<T> entityType, @NotNull Object entityId) {
		T entity = getEntityManager().find(entityType, entityId);
		if (entity != null) {
			getEntityManager().remove(entity);
		}
	}

	/**
	 * Sucht eine einzelne Entität des angegebenen Typs mit der angegebenen
	 * Named Query und den angegebenen Query-Parametern und liefert das Ergebnis
	 * der Query zurück.
	 * 
	 * @param entityType
	 *            erwarteter Entitätentyp
	 * @param queryName
	 *            eindeutiger Name einer Named Query
	 * @param queryParameters
	 *            anzuwendende Query-Parameter (optional)
	 * @return gefundene Entität, falls die Query ein Ergebnis geliefert hat;
	 *         sonst {@code null}.
	 * @throws NonUniqueResultException
	 *             - falls mehr als eine Entität gefunden werden kann
	 */
	public <T> T queryEntityWithNamedQuery(@NotNull Class<T> entityType, @NotNull String queryName,
			QueryParameters queryParameters) {
		TypedQuery<T> query = getEntityManager().createNamedQuery(queryName, entityType);
		if (queryParameters != null) {
			queryParameters.applyParameters(query);
		}
		List<T> resultList = query.getResultList();
		T result = null;
		if (resultList.size() == 1) {
			result = resultList.get(0);
		} else if (resultList.size() > 1) {
			throw new NonUniqueResultException("Expected query [" + queryName + "] with query parameters ["
					+ queryParameters + "] to find exactly one entity of type [" + entityType
					+ "] but actually found many entities!");
		}
		return result;
	}

	/**
	 * Sucht eine einzelne Entität des angegebenen Typs mit der angegebenen
	 * Named Query und den angegebenen Query-Parametern und liefert das Ergebnis
	 * der Query zurück.
	 * <p>
	 * Im Gegensatz zu
	 * {@link #queryEntityWithNamedQuery(Class, String, QueryParameters)} wird
	 * allerdings davon ausgegangen, dass die gewünschte Entität existiert.
	 * </p>
	 * 
	 * @param entityType
	 *            erwarteter Entitätentyp
	 * @param queryName
	 *            eindeutiger Name einer Named Query
	 * @param queryParameters
	 *            anzuwendende Query-Parameter (optional)
	 * @return gefundene Entität, niemals {@code null}.
	 * @throws NoSuchElementException
	 *             - falls keine Entität gefunden werden kann
	 */
	public @NotNull <T> T queryRequiredEntityWithNamedQuery(@NotNull Class<T> entityType, @NotNull String queryName,
			QueryParameters queryParameters) {
		T result = queryEntityWithNamedQuery(entityType, queryName, queryParameters);
		if (result == null) {
			throw new NoSuchElementException(
					"Expected query [" + queryName + "] with query parameters [" + queryParameters
							+ "] to find exactly one entity of type [" + entityType + "] but actually found none!");
		}
		return result;
	}

	/**
	 * Sucht eine Menge von Entität des angegebenen Typs mit dem angegebenen
	 * Query-Statement und den angegebenen Query-Parametern und liefert das
	 * Ergebnis der Query zurück.
	 * 
	 * @param entityType
	 *            erwarteter Entitätentyp
	 * @param queryStatement
	 *            JPQL-Statement
	 * @param queryParameters
	 *            anzuwendende Query-Parameter (optional)
	 * @return Liste der gefundenen Entitäten, niemals {@code null}
	 */
	public @NotNull <T> List<T> queryEntities(@NotNull Class<T> entityType, @NotNull String queryStatement,
			QueryParameters queryParameters) {
		return queryForList(entityType, getEntityManager().createQuery(queryStatement, entityType), queryParameters, -1, -1);
	}

	/**
	 * Sucht eine Menge von Entität des angegebenen Typs mit dem angegebenen
	 * Query-Statement und den angegebenen Query-Parametern und liefert das
	 * Ergebnis der Query zurück.
	 * 
	 * @param entityType
	 *            erwarteter Entitätentyp
	 * @param queryStatement
	 *            JPQL-Statement
	 * @param queryParameters
	 *            anzuwendende Query-Parameter (optional)
	 * @param pageStartingAt
	 *            Index des ersten Elements der Seite im Resultset
	 * @param pageSize
	 *            Anzahl an Elementen in einer Seite
	 * @return Liste der gefundenen Entitäten, niemals {@code null}
	 */
	public @NotNull <T> List<T> queryEntities(@NotNull Class<T> entityType, @NotNull String queryStatement,
			QueryParameters queryParameters, int pageStartingAt, int pageSize) {
		return queryForList(entityType, getEntityManager().createQuery(queryStatement, entityType), queryParameters, pageStartingAt,
				pageSize);
	}

	/**
	 * Sucht eine Menge von Entität des angegebenen Typs mit der angegebenen
	 * Named Query und den angegebenen Query-Parametern und liefert das Ergebnis
	 * der Query zurück.
	 * 
	 * @param entityType
	 *            erwarteter Entitätentyp
	 * @param queryName
	 *            eindeutiger Name einer Named Query
	 * @param queryParameters
	 *            anzuwendende Query-Parameter (optional)
	 * @return Liste der gefundenen Entitäten, niemals {@code null}
	 */
	public @NotNull <T> List<T> queryEntitiesWithNamedQuery(@NotNull Class<T> entityType, @NotNull String queryName,
			QueryParameters queryParameters) {
		return queryForList(entityType, getEntityManager().createNamedQuery(queryName, entityType), queryParameters, -1, -1);
	}

	/**
	 * Sucht eine Menge von Entität des angegebenen Typs mit der angegebenen
	 * Named Query und den angegebenen Query-Parametern und liefert das Ergebnis
	 * der Query zurück.
	 * 
	 * @param entityType
	 *            erwarteter Entitätentyp
	 * @param queryName
	 *            eindeutiger Name einer Named Query
	 * @param queryParameters
	 *            anzuwendende Query-Parameter (optional)
	 * @param pageStartingAt
	 *            Index des ersten Elements der Seite im Resultset
	 * @param pageSize
	 *            Anzahl an Elementen in einer Seite
	 * @return Liste der gefundenen Entitäten, niemals {@code null}
	 */
	public @NotNull <T> List<T> queryEntitiesWithNamedQuery(@NotNull Class<T> entityType, @NotNull String queryName,
			QueryParameters queryParameters, int pageStartingAt, int pageSize) {
		return queryForList(entityType, getEntityManager().createNamedQuery(queryName, entityType), queryParameters, pageStartingAt,
				pageSize);
	}

	/**
	 * Ermittelt die Anzahl der Entitäten des angegebenen Typs mit dem
	 * angegebenen Query-Statement und den angegebenen Query-Parametern.
	 * 
	 * @param queryStatement
	 *            JPQL-Statement
	 * @param queryParameters
	 *            anzuwendende Query-Parameter (optional)
	 * @return Anzahl der gefundenen Entitäten, die zu den Query-Parametern
	 *         passen
	 */
	public long countEntities(String queryStatement, QueryParameters queryParameters) {
		TypedQuery<Long> query = getEntityManager().createNamedQuery(queryStatement, Long.class);
		if (queryParameters != null) {
			queryParameters.applyParameters(query);
		}
		return query.getSingleResult();
	}

	/**
	 * Ermittelt die Anzahl der Entitäten des angegebenen Typs mit der
	 * angegebenen Named Query und den angegebenen Query-Parametern.
	 * 
	 * @param queryName
	 *            eindeutiger Name einer Named Query
	 * @param queryParameters
	 *            anzuwendende Query-Parameter (optional)
	 * @return Anzahl der gefundenen Entitäten, die zu den Query-Parametern
	 *         passen
	 */
	public long countEntitiesWithNamedQuery(String queryName, QueryParameters queryParameters) {
		TypedQuery<Long> query = getEntityManager().createNamedQuery(queryName, Long.class);
		if (queryParameters != null) {
			queryParameters.applyParameters(query);
		}
		return query.getSingleResult();
	}

	protected abstract EntityManager getEntityManager();
	
	/**
	 * Führt die angegebene Query mit den angegebenen Query-Parametern aus und
	 * liefert das Ergebnis als List zurück.
	 * <p>
	 * Kapselt die Logik der Parametriesierung und Ausführung von Query für
	 * beliebige Querytypen (Einfache Query, Named Query, Native Query).
	 * </p>
	 * 
	 * @param entityType
	 * @param query
	 * @param queryParameters
	 * @param pageStartingAt
	 * @param pageSize
	 * @return Liste der gefundenen Entitäten; kann leer sein aber niemals
	 *         {@code null}
	 */
	private <T> List<T> queryForList(Class<T> entityType, TypedQuery<T> query, QueryParameters queryParameters,
			int pageStartingAt, int pageSize) {
		if (queryParameters != null) {
			queryParameters.applyParameters(query);
		}
		if (pageStartingAt != -1) {
			query.setFirstResult(pageStartingAt);
		}
		if (pageSize != -1) {
			query.setMaxResults(pageSize);
		}
		return query.getResultList();
	}
}
