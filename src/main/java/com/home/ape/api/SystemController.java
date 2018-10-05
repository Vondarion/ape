package com.home.ape.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.home.ape.config.EnvironmentContextInitializer;
import com.home.ape.security.Roles;
import com.home.ape.util.DBInformation;

// TODO: Auto-generated Javadoc
/**
 * The Class SystemController.
 */
@RestController
@RequestMapping("/system")
public class SystemController {

	/** The Constant log. */
	private static final Logger	log		= LoggerFactory.getLogger(SystemController.class);

	/** The Constant OUTPUT. */
	public static final Logger	OUTPUT	= LoggerFactory.getLogger("OUTPUT");

	/** The environment. */
	@Autowired
	Environment					environment;

	/** The data source. */
	@Autowired(required = false)
	DataSource					dataSource;

	/**
	 * Gets the system info.
	 *
	 * @param request
	 *            the request
	 * @return the system info
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getSystemInfo(HttpServletRequest request) {
		log.info("Reading systemInfo...");
		SystemInfo retVal = new SystemInfo();
		retVal.setCurrentUser(request.getRemoteUser());
		retVal.setEnvironment(EnvironmentContextInitializer.getEnvironment().toString());
		retVal.setActiveProfile(String.join(",", environment.getActiveProfiles()));
		retVal.setDb(toString(dataSource));
		retVal.setLogConfig(LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME).toString());
		retVal.setText(LocalDateTime.now().toString());
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	/**
	 * To string.
	 *
	 * @param dataSource
	 *            the data source
	 * @return the string
	 */
	private String toString(DataSource dataSource) {
		if ( dataSource == null ) {
			return "<none>";
		} else {
			try {
				DBInformation dbInfo = new DBInformation(dataSource);
				return stripCredentials(dbInfo.getUrl());
			} catch ( Exception ex ) {
				log.error("An error occured while trying to acquire DB information", ex);
				return "<error>";
			}
		}
	}

	/**
	 * Strip credentials.
	 *
	 * @param urlString
	 *            the url string
	 * @return the string
	 */
	private String stripCredentials(String urlString) {
		try {
			if ( urlString.startsWith("jdbc:") ) {
				urlString = urlString.substring("jdbc:".length());
			}
			URI url = new URI(urlString);
			return new URI(url.getScheme(), null, url.getHost(), url.getPort(), url.getPath(), null, null).toString();
		} catch ( URISyntaxException e ) {
			// Expected error in DEV/Derby so shorten the log message
			log.error("An error occured while trying to acquire DB information", e.getMessage());
			return "<bad url> " + urlString;
		}
	}

	/**
	 * The Class SystemInfo.
	 */
	protected class SystemInfo {

		/** The active profile. */
		private String	activeProfile;

		/** The db. */
		private String	db;

		/** The log config. */
		private String	logConfig;

		/** The environment. */
		private String	environment;

		/** The text. */
		private String	text;

		/** The current user. */
		private String	currentUser;

		/**
		 * Instantiates a new system info.
		 */
		protected SystemInfo() {

		}

		/**
		 * Gets the active profile.
		 *
		 * @return the active profile
		 */
		public String getActiveProfile() {
			return activeProfile;
		}

		/**
		 * Sets the active profile.
		 *
		 * @param activeProfile
		 *            the new active profile
		 */
		public void setActiveProfile(String activeProfile) {
			this.activeProfile = activeProfile;
		}

		/**
		 * Gets the db.
		 *
		 * @return the db
		 */
		public String getDb() {
			return db;
		}

		/**
		 * Sets the db.
		 *
		 * @param db
		 *            the new db
		 */
		public void setDb(String db) {
			this.db = db;
		}

		/**
		 * Gets the log config.
		 *
		 * @return the log config
		 */
		public String getLogConfig() {
			return logConfig;
		}

		/**
		 * Sets the log config.
		 *
		 * @param logConfig
		 *            the new log config
		 */
		public void setLogConfig(String logConfig) {
			this.logConfig = logConfig;
		}

		/**
		 * Gets the environment.
		 *
		 * @return the environment
		 */
		public String getEnvironment() {
			return environment;
		}

		/**
		 * Sets the environment.
		 *
		 * @param environment
		 *            the new environment
		 */
		public void setEnvironment(String environment) {
			this.environment = environment;
		}

		/**
		 * Gets the text.
		 *
		 * @return the text
		 */
		public String getText() {
			return text;
		}

		/**
		 * Sets the text.
		 *
		 * @param text
		 *            the new text
		 */
		public void setText(String text) {
			this.text = text;
		}

		/**
		 * Gets the current user.
		 *
		 * @return the current user
		 */
		public String getCurrentUser() {
			return currentUser;
		}

		/**
		 * Sets the current user.
		 *
		 * @param currentUser
		 *            the new current user
		 */
		public void setCurrentUser(String currentUser) {
			this.currentUser = currentUser;
		}

	}

	public final SystemController.TestUser	PLAYER1	= createPlayer1();
	public final SystemController.TestUser	PLAYER2	= createPlayer2();
	public final SystemController.TestUser	PLAYER3	= createPlayer3();

	public final TestUser[]					PLAYERS	= { PLAYER1, PLAYER2, PLAYER3 };

	public class TestUser extends User {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 4146810138132544042L;

		protected TestUser(String username, String password, String[] roles) {
			super(username, password, true, true, true, true, buildAuthorities(roles));
		}

		public void login() {
			UserDetails user = this;
			Authentication auth = new UsernamePasswordAuthenticationToken(user, this.getPassword(),
					this.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(auth);

		}

		public void logout() {
			SecurityContextHolder.getContext().setAuthentication(null);
		}
	}

	private TestUser createPlayer1() {
		/** The Constant ROLES. */
		final String[] roles = { Roles.USER_ROLE };
		return new TestUser("player1", "secret1", roles);
	}

	private TestUser createPlayer2() {
		/** The Constant ROLES. */
		final String[] roles = { Roles.USER_ROLE };
		return new TestUser("player2", "secret2", roles);
	}

	private TestUser createPlayer3() {
		/** The Constant ROLES. */
		final String[] roles = { Roles.USER_ROLE, Roles.ADMIN_ROLE };
		return new TestUser("player3", "secret3", roles);
	}

	/**
	 * Builds the authorities.
	 *
	 * @return the collection
	 */
	private static Collection<GrantedAuthority> buildAuthorities(String[] roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();

		for ( String role : roles ) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
		}

		return authorities;
	}
}
