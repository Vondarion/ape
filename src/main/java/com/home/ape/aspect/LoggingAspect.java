package com.home.ape.aspect;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.home.ape.config.CommonJointPointConfiguration;
import com.home.ape.util.ApplicationLogger;

/**
 * Provides logging capabilities via aspects.
 */
@Aspect
@Configuration
public class LoggingAspect {
	/**
	 * {@link ConcurrentMap} of {@link Logger} instances used. "Cached" for better performance.
	 */
	private static ConcurrentMap<Class<?>, Logger> LOGGERS = new ConcurrentHashMap<Class<?>, Logger>();

	/**
	 * Traces method invocation.
	 * 
	 * @param joinPoint
	 *            The intercepted {@link ProceedingJoinPoint}
	 * @return The original result of the intercepted operation
	 * @throws Throwable
	 *             In case of an error
	 */
	@Around(CommonJointPointConfiguration.SERVICE_LAYER_EXECUTION)
	public Object traceMethodInvocation(ProceedingJoinPoint joinPoint) throws Throwable {
		Object retVal = null;

		Class<?> clazz = joinPoint.getSignature().getDeclaringType();
		Logger logger = getLogger(clazz);

		String message = joinPoint.getSignature().toLongString();
		logger.trace("entering: {}", message);
		ApplicationLogger.SERVICE.trace("entering: {}", message);

		// proceed with intercepted call
		retVal = joinPoint.proceed();

		// update message
		message = joinPoint.getSignature().toLongString();
		ApplicationLogger.SERVICE.trace("exeting: {}", message);
		logger.trace("exiting {}", message);

		return retVal;
	}

	/**
	 * Traces a (service) {@link Exception}.
	 * 
	 * @param joinPoint
	 *            The intercepted {@link JoinPoint}
	 * @param ex
	 *            The {@link Exception} to trace
	 */
	@AfterThrowing(pointcut = CommonJointPointConfiguration.SERVICE_LAYER_EXECUTION, throwing = "ex")
	public void traceException(JoinPoint joinPoint, Exception ex) {
		Class<?> clazz = joinPoint.getSignature().getDeclaringType();
		Logger logger = getLogger(clazz);

		logger.trace(ex.getMessage(), ex);
	}

	/**
	 * Provides the corresponding {@link Logger} for the specified {@link Class}.
	 * 
	 * This is encapsulated here for better performance via caching the {@link Logger} instances in a {@link Map}. May
	 * not be needed anymore with optimizations done in slf4j version 1.7.5
	 * 
	 * @see http://bugzilla.slf4j.org/show_bug.cgi?id=298
	 * 
	 * @param clazz
	 *            The {@link Class} for which to return a corresponding {@link Logger}
	 * @return The corresponding {@link Logger} for the specified {@link Class}
	 */
	protected static Logger getLogger(Class<?> clazz) {
		Logger retVal = LOGGERS.get(clazz);

		if ( retVal == null ) {
			retVal = LoggerFactory.getLogger(clazz);
			LOGGERS.putIfAbsent(clazz, retVal);
		}

		return retVal;
	}
}
