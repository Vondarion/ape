package com.home.ape.config;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * The listener interface for receiving startupApplication events. The class that is interested in processing a
 * startupApplication event implements this interface, and the object created with that class is registered with a
 * component using the component's <code>addStartupApplicationListener<code> method. When the startupApplication event
 * occurs, that object's appropriate method is invoked.
 *
 * @see StartupApplicationEvent
 */
@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(StartupApplicationListener.class);

	/** The environment. */
	@Autowired
	Environment					environment;

	/// ** The competiton service. */
	// @Autowired
	// CompetitionService competitonService;

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("StartEvent fired!");
		log.info("* init with profiles: " + ArrayUtils.toString(environment.getActiveProfiles()));

		if ( environment.acceptsProfiles("dev") || environment.acceptsProfiles("cf")
				|| environment.acceptsProfiles("neo") ) {
			log.info("Init after Application started...!");
			// TODO ServiceSecurityConfig.loginSystemUser();
			// Competition competition = CompetitionUtil.initSoccerWorldCup2018();
			// competitonService.createCompetition(competition);
			// ServiceSecurityConfig.logoutSystemUser();
			log.info("Init after application started...done");
		}

	}

}
