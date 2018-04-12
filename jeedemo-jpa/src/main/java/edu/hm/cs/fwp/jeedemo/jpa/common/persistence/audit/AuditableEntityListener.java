package edu.hm.cs.fwp.jeedemo.jpa.common.persistence.audit;

import java.security.Principal;
import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * {@code JPA lifecycle listener} that tracks creation and modification of
 * {@link AuditableEntity}s.
 *
 * @author Michael Theis (msg)
 * @version 1.0
 * @since release 18.2
 */
public class AuditableEntityListener {

	@Inject
	private Principal principal;

	@PrePersist
	public void onPrePersist(AbstractAuditableEntity entity) {
		entity.trackCreation(getAuthenticatedUserId(), LocalDateTime.now());
	}

	@PreUpdate
	public void onPreUpdate(AbstractAuditableEntity entity) {
		entity.trackModification(getAuthenticatedUserId(), LocalDateTime.now());
	}

	private String getAuthenticatedUserId() {
		return this.principal != null ? this.principal.getName() : "anonymous";
	}

}
