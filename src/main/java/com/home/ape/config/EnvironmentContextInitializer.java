package com.home.ape.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Detects the current landscape the application is run on and initializes the Spring {@link ApplicationContext}
 * accordingly.
 *
 * @see ApplicationContextInitializer
 */
public class EnvironmentContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	private static final Logger	log		= LoggerFactory.getLogger(EnvironmentContextInitializer.class);

	final static String			vcap	= System.getenv("VCAP_APPLICATION");							// TODO CF

	public enum RuntimeEnvironment {
		DEV,
		DOCKER
	}

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		RuntimeEnvironment stage = getEnvironment();
		applicationContext.getEnvironment().setActiveProfiles(stage.name().toLowerCase());
	}

	/**
	 * Returns the {@link RuntimeEnvironment} the application runs in. Defaults to <code>DEV</code>.
	 *
	 * @return The {@link RuntimeEnvironment} the application runs in
	 */
	public static RuntimeEnvironment getEnvironment() {
		RuntimeEnvironment retVal = RuntimeEnvironment.DEV;

		// TODO When running in docker?
		if ( vcap != null ) // Docker
		{

			retVal = RuntimeEnvironment.DOCKER;
		}

		log.info("Application running in '{}' environment", retVal.name().toLowerCase());
		return retVal;
	}
}
