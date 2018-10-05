package com.home.ape.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Base class for all domain model objects.
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseObject implements Serializable {

	/**
	 * The unique ID of the object.
	 */
	@Id
	@GeneratedValue
	private Long			id;

	/**
	 * The {@link LocalDateTime} the object was created at.
	 */
	private LocalDateTime	createdAt		= null;

	/**
	 * The {@link LocalDateTime} the object was last modified at.
	 */
	private LocalDateTime	lastModifiedAt	= null;

	/**
	 * ID of the user who created the object.
	 */
	@Size(max = 30, message = "{api.data_validation.max_length.error}")
	private String			createdBy		= null;

	/**
	 * ID of the user who was the last to modify the object.
	 */
	@Size(max = 30, message = "{api.data_validation.max_length.error}")
	private String			lastModifiedBy	= null;

	/**
	 * The version number used for optimistic locking.
	 * 
	 * @see http://en.wikibooks.org/wiki/Java_Persistence/Locking
	 * @see http://eclipse.org/eclipselink/documentation/2.4/jpa/extensions/a_optimisticlocking.htm
	 */
	@Version
	private Long			version			= 0L;

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public final boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}

		if ( obj == null || !(obj instanceof BaseObject) ) {
			return false;
		}

		BaseObject other = (BaseObject) obj;

		if ( id == null ) {
			return false;
		}

		return id.equals(other.getId());
	}

	/**
	 * Gets the created at.
	 *
	 * @return the created at
	 */
	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Gets the last modified at.
	 *
	 * @return the last modified at
	 */
	public LocalDateTime getLastModifiedAt() {
		return this.lastModifiedAt;
	}

	/**
	 * Gets the last modified by.
	 *
	 * @return the last modified by
	 */
	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode() {
		if ( id != null ) {
			String hashBase = this.getClass().getName() + "_" + id;
			return hashBase.hashCode();
		} else {
			return super.hashCode();
		}
	}

	/**
	 * Sets the created at.
	 *
	 * @param createdAt
	 *            the new created at
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy
	 *            the new created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the last modified at.
	 *
	 * @param lastModifiedAt
	 *            the new last modified at
	 */
	public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	/**
	 * Sets the last modified by.
	 *
	 * @param lastModifiedBy
	 *            the new last modified by
	 */
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	/**
	 * Sets the version.
	 *
	 * @param version
	 *            the new version
	 */
	public void setVersion(Long version) {
		this.version = version;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("id", id);
		builder.append("createdAt", createdAt);
		builder.append("lastModifiedAt", lastModifiedAt);
		builder.append("createdBy", createdBy);
		builder.append("lastModifiedBy", lastModifiedBy);
		builder.append("version", version);
		return builder.toString();
	}

	/**
	 * Generate audit information.
	 */
	@PrePersist
	protected void generateAuditInformation() {
		final LocalDateTime now = LocalDateTime.now();

		createdAt = now;
		lastModifiedAt = now;

		try {
			createdBy = SecurityContextHolder.getContext().getAuthentication().getName();
			lastModifiedBy = SecurityContextHolder.getContext().getAuthentication().getName();
		} catch ( Exception e ) {
			throw new SecurityException("Model persist approach with security issue", e);
		}
	}

	/**
	 * Life-cycle event callback, which automatically sets the last modification date.
	 */
	@PreUpdate
	protected void updateAuditInformation() {
		lastModifiedAt = LocalDateTime.now();

		try {
			lastModifiedBy = SecurityContextHolder.getContext().getAuthentication().getName();
		} catch ( Exception e ) {
			throw new SecurityException("Model persist approach with security issue", e);
		}
	}

}
