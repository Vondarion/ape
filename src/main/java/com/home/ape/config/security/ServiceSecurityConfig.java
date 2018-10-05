package com.home.ape.config.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.home.ape.security.Roles;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ServiceSecurityConfig extends GlobalMethodSecurityConfiguration {

	private static final Logger log = LoggerFactory.getLogger(ServiceSecurityConfig.class);

	protected ServiceSecurityConfig() {
		super();
		log.info("ServiceSecurityConfig is activated!");
	}

	public static void loginSystemUser() {
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(Roles.ADMIN_ROLE_WITH_PREFIX,
				Roles.USER_ROLE_WITH_PREFIX);

		UserDetails systemUser = User.withUsername("SYSTEM").password("secret1").authorities(authorities).build();
		Authentication auth = new UsernamePasswordAuthenticationToken(systemUser, "secret1",
				systemUser.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	public static void logoutSystemUser() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

}
