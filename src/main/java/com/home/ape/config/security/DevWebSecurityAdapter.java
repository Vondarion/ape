package com.home.ape.config.security;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.home.ape.security.Roles;

/**
 * The Class DevWebSecurityAdapter. This class manages the security configuration, e.g. how users are authenticated.
 * This is the adapter to use the user authentication and authorization on the dev and test environment
 */
@Configuration
@EnableWebSecurity
@Profile("dev")
public class DevWebSecurityAdapter extends BaseWebSecurityAdapter {

	private static final Logger	log				= LoggerFactory.getLogger(DevWebSecurityAdapter.class);

	// from BCRYPT: the log rounds to use, between 4 and 31, for DEV and TST we keep it low
	protected static final int	BCRYPT_STRENGTH	= 4;

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.
	 * springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("Configure development web security authentication");
		auth.inMemoryAuthentication().withUser("player1").password(passwordEncoder().encode("secret1"))
				.roles(Roles.USER_ROLE);
		auth.inMemoryAuthentication().withUser("player2").password(passwordEncoder().encode("secret2"))
				.roles(Roles.USER_ROLE);
		auth.inMemoryAuthentication().withUser("player3").password(passwordEncoder().encode("secret3"))
				.roles(Roles.USER_ROLE, Roles.ADMIN_ROLE);
	}

	@Override
	protected UserDetailsService userDetailsService() {
		log.info("Building development userDetailService");
		UserDetails player1 = User.withUsername("player1").password(passwordEncoder().encode("secret1"))
				.roles(Roles.USER_ROLE).build();
		UserDetails player2 = User.withUsername("player2").password(passwordEncoder().encode("secret2"))
				.roles(Roles.USER_ROLE).build();
		UserDetails player3 = User.withUsername("player3").password(passwordEncoder().encode("secret3"))
				.roles(Roles.USER_ROLE, Roles.ADMIN_ROLE).build();

		UserDetailsService userDetailsService = new InMemoryUserDetailsManager(player1, player2, player3);
		return userDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.csrf().disable();
	}

	/**
	 * Secure url patterns.
	 *
	 * @param http
	 *            the http
	 * @param patterns
	 *            the patterns
	 * @param role
	 *            the role
	 */
	@Override
	protected void secureUrlPatterns(HttpSecurity http, String[] patterns, String role) {
		Arrays.stream(patterns).forEach(pattern -> {
			try {
				http.formLogin().and().authorizeRequests().mvcMatchers(pattern).hasRole(role);
			} catch ( Exception e ) {
				log.error("Can't secure url pattern {}: {}", pattern, e);
				throw new SecurityException(e);
			}

		});
	}

	/**
	 * Password encoder.
	 *
	 * @return the password encoder
	 */
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(BCRYPT_STRENGTH);
	}

}
