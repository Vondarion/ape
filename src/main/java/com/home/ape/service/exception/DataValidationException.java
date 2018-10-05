package com.home.ape.service.exception;

import java.util.Arrays;

import javax.validation.ConstraintViolationException;

import com.home.ape.message.StatusMessage;
import com.home.ape.message.ValidationError;
import com.home.ape.util.ConstraintViolationMapper;

/**
 * {@link RuntimeException} used by the service layer.
 */
public class DataValidationException extends ServiceException {

	/**
	 * The <code>serialVersionUID</code> of this class.
	 */
	private static final long serialVersionUID = 1L;

	public DataValidationException(ConstraintViolationException arg0) {
		super(arg0);
		this.statusMessage = ConstraintViolationMapper.getDefaultStatusMessage();
	}

	public DataValidationException(ValidationError... errors) {
		super();
		this.statusMessage = ConstraintViolationMapper.getDefaultStatusMessage();
		this.statusMessage.setErrors(Arrays.asList(errors));
	}

	@Override
	public StatusMessage getStatusMessage() {
		return this.statusMessage;
	}

}
