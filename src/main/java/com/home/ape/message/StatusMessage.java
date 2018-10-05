package com.home.ape.message;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

// TODO: Auto-generated Javadoc
/**
 * Object used to report (error) messages.
 *
 */
@XmlRootElement(name = "status")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatusMessage implements Serializable, Cloneable {
	/**
	 * The <code>serialVersionUID</code> of the class.
	 */
	private static final long	serialVersionUID	= 1L;

	/** The code. */
	int							code				= 500;

	/** The error. */
	String						error				= null;

	/** The description. */
	@XmlElement(name = "error_description")
	String						description			= null;

	/** The message. */
	String						message				= null;

	/** The errors. */
	List<ValidationError>		errors				= null;

	/**
	 * Instantiates a new status message.
	 */
	public StatusMessage() {
	}

	/**
	 * Instantiates a new status message.
	 *
	 * @param code
	 *            the code
	 * @param error
	 *            the error
	 * @param description
	 *            the description
	 * @param message
	 *            the message
	 * @param errors
	 *            the errors
	 */
	public StatusMessage(int code, String error, String description, String message, ValidationError... errors) {
		this.setCode(code);
		this.setError(error);
		this.setDescription(description);
		this.setMessage(message);

		if ( errors != null ) {
			this.setErrors(Arrays.asList(errors));
		}
	}

	/**
	 * Instantiates a new status message.
	 *
	 * @param error
	 *            the error
	 * @param description
	 *            the description
	 * @param message
	 *            the message
	 * @param errors
	 *            the errors
	 */
	public StatusMessage(String error, String description, String message, ValidationError... errors) {
		this.setError(error);
		this.setDescription(description);
		this.setMessage(message);

		if ( errors != null ) {
			this.setErrors(Arrays.asList(errors));
		}
	}

	/**
	 * Instantiates a new status message. The is the "Clone" constructor
	 *
	 * @param statusMessage
	 *            the status message
	 */
	public StatusMessage(StatusMessage statusMessage) {
		this.code = statusMessage.getCode();
		this.description = (statusMessage.getDescription() == null) ? null : new String(statusMessage.getDescription());
		this.error = (statusMessage.getError() == null) ? null : new String(statusMessage.getError());
		this.message = (statusMessage.getMessage() == null) ? null : new String(statusMessage.getMessage());
	}

	/**
	 * Equals.
	 *
	 * @param object
	 *            the object
	 * @return true, if successful
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if ( object == this ) {
			return true;
		}

		if ( !(object instanceof StatusMessage) ) {
			return false;
		}

		StatusMessage rhs = (StatusMessage) object;

		return new EqualsBuilder().appendSuper(super.equals(object)).append(this.message, rhs.message)
				.append(this.error, rhs.error).append(this.description, rhs.description).append(this.code, rhs.code)
				.isEquals();
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * Gets the errors.
	 *
	 * @return the errors
	 */
	public List<ValidationError> getErrors() {
		return errors;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(-950597061, 209594933).appendSuper(super.hashCode()).append(this.message)
				.append(this.error).append(this.description).append(this.code).toHashCode();
	}

	/**
	 * Sets the code.
	 *
	 * @param code
	 *            the new code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the error.
	 *
	 * @param error
	 *            the new error
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * Sets the errors.
	 *
	 * @param errors
	 *            the new errors
	 */
	public void setErrors(List<ValidationError> errors) {
		this.errors = errors;
	}

	/**
	 * Sets the message.
	 *
	 * @param message
	 *            the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).appendSuper(super.toString())
				.append("error", this.error).append("message", this.message).append("description", this.description)
				.append("code", this.code).toString();
	}

}
