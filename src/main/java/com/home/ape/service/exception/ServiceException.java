package com.home.ape.service.exception;

import com.home.ape.message.StatusMessage;

/**
 * {@link RuntimeException} used by the service layer.
 */
public class ServiceException extends RuntimeException {

	/**
	 * The <code>serialVersionUID</code> of this class.
	 */
	private static final long	serialVersionUID	= 1L;

	/** The msg. */
	StatusMessage				statusMessage		= null;

	/**
	 * Instantiates a new service exception.
	 */
	public ServiceException() {
		super();
		statusMessage = new StatusMessage();
	}

	/**
	 * Instantiates a new service exception.
	 *
	 * @param msg
	 *            the msg
	 */
	public ServiceException(StatusMessage statusMessage) {
		super();
		this.statusMessage = statusMessage;
	}

	/**
	 * Instantiates a new service exception.
	 *
	 * @param messageText
	 *            the message text
	 */
	public ServiceException(String message) {
		super(message);
		statusMessage = new StatusMessage();
		statusMessage.setMessage(message);
	}

	/**
	 * Instantiates a new service exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		statusMessage = new StatusMessage();
		statusMessage.setMessage(message);
	}

	/**
	 * Instantiates a new service exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
		statusMessage = new StatusMessage();
		statusMessage.setDescription(cause.getMessage());
	}

	/**
	 * Gets the status message.
	 *
	 * @return the status message
	 */
	public StatusMessage getStatusMessage() {
		return this.statusMessage;
	}

}
