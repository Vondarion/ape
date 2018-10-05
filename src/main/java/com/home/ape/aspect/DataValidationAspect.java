package com.home.ape.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.home.ape.config.CommonJointPointConfiguration;
import com.home.ape.message.ValidationError;
import com.home.ape.service.exception.DataValidationException;
import com.home.ape.util.ConstraintViolationMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Ensures data validation based on JSR-303.
 * 
 * @see http://static.springsource.org/spring/docs/3.2.x/spring-framework-reference/html/aop.html
 */
@Aspect
@Configuration
@Slf4j
public class DataValidationAspect {

	/** The validator. */
	@Inject
	LocalValidatorFactoryBean	validator					= null;

	/** The message resource bundle name. */
	@Value("${messageResourceBundleName}")
	String						messageResourceBundleName	= null;

	/**
	 * Gets the message resource bundle name.
	 *
	 * @return the message resource bundle name
	 */
	public String getMessageResourceBundleName() {
		return messageResourceBundleName;
	}

	/**
	 * Needs validation.
	 *
	 * @param annotations
	 *            the annotations
	 * @return true, if successful
	 */
	private boolean needsValidation(Annotation[] annotations) {
		// log.debug("needValidations {}", annotations);
		for ( Annotation annotation : annotations ) {
			if ( Valid.class.isInstance(annotation) ) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Sets the message resource bundle name.
	 *
	 * @param messageResourceBundleName
	 *            the new message resource bundle name
	 */
	public void setMessageResourceBundleName(String messageResourceBundleName) {
		log.debug("setMessageResourceBundleName {}", messageResourceBundleName);
		this.messageResourceBundleName = messageResourceBundleName;
	}

	/**
	 * Validates the data of any method parameter annotated with {@link Valid}.
	 * 
	 * @param joinPoint
	 *            The intercepted {@link JoinPoint}
	 * @throws DataValidationException
	 *             In case of an validation error
	 */
	@Before(CommonJointPointConfiguration.API_LAYER_WITH_VALID_ANNOTATION_EXECUTION)
	public void validateIncomingData(JoinPoint joinPoint) throws DataValidationException {
		log.debug("validate incoming data: {}", joinPoint);
		// get information about the intercepted method
		final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		final Method method = methodSignature.getMethod();

		// get the annotations and names of the method parameters
		final Annotation[][] paramAnnotations = method.getParameterAnnotations();
		final String[] paramNames = methodSignature.getParameterNames();

		// get the message parameter themselves
		final Object[] param = joinPoint.getArgs();

		if ( param == null || param.length < 1 ) {
			return; // nothing to validate
		}

		// check whether or not they have been annotated with @Valid
		for ( int i = 0; i < param.length; i++ ) {
			validateParam(paramAnnotations[i], param[i], paramNames[i]);
		}
	}

	/**
	 * Validate param.
	 *
	 * @param annotations
	 *            the annotations
	 * @param param
	 *            the param
	 * @param paramName
	 *            the param name
	 * @throws DataValidationException
	 *             the data validation exception
	 */
	void validateParam(Annotation[] annotations, Object param, String paramName) throws DataValidationException {
		// check if parameter needs to be validated
		if ( !needsValidation(annotations) ) {
			return;
		}

		try // to validate the parameter
		{

			Set<ConstraintViolation<Object>> constraints = this.validator.getValidator().validate(param);

			// get locale
			final Locale locale = LocaleContextHolder.getLocale();

			ValidationError[] errors = ConstraintViolationMapper
					.convertConstraintViolationsToValidationErrors(constraints, locale, this.messageResourceBundleName);

			// only throw data validation exception in case we found errors ;)
			if ( errors != null && errors.length > 0 ) {
				throw new DataValidationException(errors);
			}
		} catch ( ConstraintViolationException ex ) {
			throw new DataValidationException(ex);
		}
	}

}
