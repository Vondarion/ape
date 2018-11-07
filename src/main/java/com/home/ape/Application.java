package com.home.ape;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.home.ape.config.EnvironmentContextInitializer;

/**
 * The Class Application.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.boot.web.servlet.support.SpringBootServletInitializer#configure(org.springframework.boot.
	 * builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		// We start the Spring Application as simple as we use the public static void main method in java
		// System.out.println("args:" + ArrayUtils.toString(args));
		log.info("Awakening the Ape");
		SpringApplication app = new SpringApplication(Application.class);
		app.addInitializers(new EnvironmentContextInitializer());
		app.run(args);
	}

}
