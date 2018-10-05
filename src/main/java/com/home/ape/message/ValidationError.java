package com.home.ape.message;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Object used to report information about validation errors.
 *
 */
@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidationError implements Serializable {
	/**
	 * The <code>serialVersionUID</code> of the class.
	 */
	private static final long	serialVersionUID	= 1L;

	/** The message key. */
	String						messageKey			= null;

	/** The message. */
	String						message				= null;

	/** The message template. */
	String						messageTemplate		= null;

	/** The path. */
	String						path				= null;

	/** The invalid value. */
	String						invalidValue		= null;

	/** The message parameter. */
	Map<String, String>			messageParameter	= null;

	/**
	 * Instantiates a new validation error.
	 */
	public ValidationError() {
	}

	/**
	 * Instantiates a new validation error.
	 *
	 * @param messageKey
	 *            the message key
	 * @param message
	 *            the message
	 * @param messageTemplate
	 *            the message template
	 * @param path
	 *            the path
	 * @param invalidValue
	 *            the invalid value
	 * @param parameter
	 *            the parameter
	 */
	public ValidationError(String messageKey, String message, String messageTemplate, String path, String invalidValue,
			Map<String, String> parameter) {
		this.setMessageKey(messageKey);
		this.setMessage(message);
		this.setMessageTemplate(messageTemplate);
		this.setPath(path);
		this.setInvalidValue(invalidValue);
		this.setMessageParameter(parameter);

	}

	/**
	 * Gets the invalid value.
	 *
	 * @return the invalid value
	 */
	public String getInvalidValue() {
		return invalidValue;
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
	 * Gets the message key.
	 *
	 * @return the message key
	 */
	public String getMessageKey() {
		return messageKey;
	}

	/**
	 * Gets the message parameter.
	 *
	 * @return the message parameter
	 */
	public Map<String, String> getMessageParameter() {
		return messageParameter;
	}

	/**
	 * Gets the message template.
	 *
	 * @return the message template
	 */
	public String getMessageTemplate() {
		return messageTemplate;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the invalid value.
	 *
	 * @param invalidValue
	 *            the new invalid value
	 */
	public void setInvalidValue(String invalidValue) {
		this.invalidValue = invalidValue;
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
	 * Sets the message key.
	 *
	 * @param messageKey
	 *            the new message key
	 */
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	/**
	 * Sets the message parameter.
	 *
	 * @param messageParameter
	 *            the message parameter
	 */
	public void setMessageParameter(Map<String, String> messageParameter) {
		this.messageParameter = messageParameter;
	}

	/**
	 * Sets the message template.
	 *
	 * @param messageTemplate
	 *            the new message template
	 */
	public void setMessageTemplate(String messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

	/**
	 * Sets the path.
	 *
	 * @param path
	 *            the new path
	 */
	public void setPath(String path) {
		this.path = path;
	}
}