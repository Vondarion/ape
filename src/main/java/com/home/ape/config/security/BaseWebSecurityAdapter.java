package com.home.ape.config.security;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.home.ape.security.CsrfTokenResponseHeaderBindingFilter;
import com.home.ape.security.CustomAccessDeniedHandler;
import com.home.ape.security.Roles;

public abstract class BaseWebSecurityAdapter extends WebSecurityConfigurerAdapter {

	private static final Logger		log							= LoggerFactory.getLogger(BaseWebSecurityAdapter.class);

	protected static final boolean	DEBUG_SECURITY				= true;

	protected static final String[]	PATTERNS_REQUIRES_PLAYER	= { "/homes" };
	protected static final String[]	PATTERNS_REQUIRES_ADMIN		= { "/admin", "/system" };
	// TODO no reason for admin so far

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.
	 * springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("Init secureUrlPatterns");
		secureUrlPatterns(http, PATTERNS_REQUIRES_PLAYER, Roles.USER_ROLE);
		secureUrlPatterns(http, PATTERNS_REQUIRES_ADMIN, Roles.ADMIN_ROLE);

		http.headers().frameOptions().sameOrigin().httpStrictTransportSecurity().disable();// .and();//
		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler()); // .csrf().disable();
		http.cors();
		http.addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.
	 * springframework.security.config.annotation.web.builders.WebSecurity)
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(DEBUG_SECURITY);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:3000"));//
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("X-XSRF-TOKEN", "X-XSRF-HEADER", "*"));
		configuration.setExposedHeaders(Arrays.asList("X-XSRF-TOKEN", "X-XSRF-HEADER"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
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
	protected abstract void secureUrlPatterns(HttpSecurity http, String[] patterns, String role);

}
