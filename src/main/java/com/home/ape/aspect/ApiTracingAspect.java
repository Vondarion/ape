package com.home.ape.aspect;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.home.ape.config.CommonJointPointConfiguration;

@Aspect
@Configuration
public class ApiTracingAspect {

	private static final Logger apiTraceLog = LoggerFactory.getLogger("API");

	/**
	 * Traces method invocation.
	 * 
	 * @param joinPoint
	 *            The intercepted {@link ProceedingJoinPoint}
	 * @return The original result of the intercepted operation
	 * @throws Throwable
	 *             In case of an error
	 */
	@Around(CommonJointPointConfiguration.API_LAYER_EXECUTION)
	public Object traceMethodInvocation(ProceedingJoinPoint joinPoint) throws Throwable {
		Object retVal = null;

		long now = System.currentTimeMillis();
		if ( apiTraceLog.isDebugEnabled() ) {
			apiTraceLog.trace("entering: {}", getSignature(joinPoint));
		}

		// proceed with intercepted call
		retVal = joinPoint.proceed();
		if ( apiTraceLog.isDebugEnabled() ) {
			apiTraceLog.trace("exiting ({}ms): {}", System.currentTimeMillis() - now, getSignature(joinPoint));
		}
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
	@AfterThrowing(pointcut = CommonJointPointConfiguration.API_LAYER_EXECUTION, throwing = "ex")
	public void traceException(JoinPoint joinPoint, Exception ex) {
		apiTraceLog.trace(ex.getMessage(), ex);
	}

	private String getSignature(JoinPoint joinPoint) {
		StringBuilder sb = new StringBuilder();
		sb.append(joinPoint.getSignature().getDeclaringTypeName()).append(".")
				.append(joinPoint.getSignature().getName());
		sb.append("(");
		if ( joinPoint.getArgs() != null ) {
			if ( joinPoint.getArgs().length > 0 ) {
				sb.append(" ");
				sb.append(ArrayUtils.toString(joinPoint.getArgs()));
				sb.append(" ");
				// Arrays.stream(joinPoint.getArgs()).forEach(arg -> {
				// sb.append(", ").append(arg);
				// });

			}
		}
		sb.append(")");

		// entering: public org.springframework.http.ResponseEntity
		// com.sap.consulting.tipservice.api.TeamController.create(com.sap.consulting.tipservice.model.Team,org.springframework.validation.BindingResult)
		return sb.toString();
	}
}
