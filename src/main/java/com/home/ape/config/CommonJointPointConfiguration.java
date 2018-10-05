package com.home.ape.config;

import org.aspectj.lang.annotation.Pointcut;

/**
 * The Class CommonJointPointConfiguration. This class is related to AOP Best Practice to have all JointPoints in one
 * place
 */
public class CommonJointPointConfiguration {

	public static final String	PREFIX										= "com.home.ape.config.CommonJointPointConfiguration";
	public static final String	API_LAYER_EXECUTION							= PREFIX + ".apiLayerExecution()";
	public static final String	API_LAYER_WITH_VALID_ANNOTATION_EXECUTION	= PREFIX
			+ ".apiLayerWithValidAnnotationExecution()";
	public static final String	SERVICE_LAYER_EXECUTION						= PREFIX + ".serviceLayerExecution()";
	public static final String	DAO_LAYER_EXECUTION							= PREFIX + ".daoLayerExecution()";

	/**
	 * API layer execution.
	 */
	@Pointcut("execution(* com.home.ape.api.*.*(..))")
	public void apiLayerExecution() {
	}

	/**
	 * API layer execution with method params with @Valid annotation.
	 */
	@Pointcut("execution(* com.home.ape.api.*.*(@javax.validation.Valid (*), ..))")
	// @Pointcut("execution(* com.sap.consulting.tipservice.api.*.*(@com.sap.consulting.tipservice.aspect.IsValid
	// (*)))")
	public void apiLayerWithValidAnnotationExecution() {
	}

	/**
	 * Service layer execution.
	 */
	@Pointcut("execution(* com.home.ape.service.*.*(..))")
	public void serviceLayerExecution() {
	}

	/**
	 * Dao layer execution.
	 */
	@Pointcut("execution(* com.home.ape.dao.*.*(..))")
	public void daoLayerExecution() {
	}

}
