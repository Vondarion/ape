package com.home.ape.service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.home.ape.service.exception.ServiceException;

/**
 * Abstract base class for all services.
 *
 */
public abstract class BaseService {

	private static final Logger log = LoggerFactory.getLogger(BaseService.class);

	/**
	 * Creates an enclosing {@link ServiceException} for the specified {@link Exception} or propagates it directly if
	 * the specified {@link Exception} is an instance of {@link ServiceException}.
	 * 
	 * @param e
	 *            The {@link Exception} to handle
	 * @throws ServiceException
	 *             The {@link ServiceException}
	 */
	protected void handleException(Exception e) throws ServiceException {

		if ( e instanceof ServiceException ) {
			log.info("An service exception occured: " + e);
			throw (ServiceException) e;
		}
		if ( e instanceof ConstraintViolationException ) {
			log.info("An constraint violation exception occured: " + e);
			ConstraintViolationException constraintViolationException = (ConstraintViolationException) e;
			for ( ConstraintViolation<?> constraintViolation : constraintViolationException
					.getConstraintViolations() ) {
				Logger constraintViolationLog = LoggerFactory.getLogger(constraintViolation.getRootBeanClass());
				constraintViolationLog
						.warn(constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage());
				if ( constraintViolationLog.isDebugEnabled() ) {
					constraintViolationLog.warn(constraintViolation.getRootBean().toString());
				}
			}

			throw new ServiceException(e);
		} else {
			log.info("An exception occured and is transfered into service exception: " + e);
			throw new ServiceException(e);

		}
	}
}
