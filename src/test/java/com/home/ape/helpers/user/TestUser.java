package com.home.ape.helpers.user;

import java.util.Collection;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class TestUser extends User {

	/** The Constant serialVersionUID. */
	private static final long		serialVersionUID	= 2605773315646679628L;

	// ladies first ;)
	public static final TestUser	LEA					= new TestUserLea();
	public static final TestUser	LUKE				= new TestUserLuke();
	// public static final TestUser C3PO = new TestUserPlayerC3PO();
	// public static final TestUser R2D2 = new TestUserPlayerR2D2();
	public static final TestUser	ADMIN				= new TestUserAdmin();

	public static final TestUser[]	USERS				= { LEA, LUKE };

	public static final TestUser[]	ADMINS				= { ADMIN };

	public static final TestUser[]	ALL					= ArrayUtils.addAll(ADMINS, USERS);

	protected TestUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	protected TestUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public void login() {
		// List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(Roles.ADMIN_ROLE_WITH_PREFIX,
		// Roles.PLAYER_ROLE_WITH_PREFIX);

		UserDetails user = this;
		Authentication auth = new UsernamePasswordAuthenticationToken(user, this.getPassword(), this.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(auth);

	}

	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	// public static TestUser getTestUser(Predictor predictor) {
	// String name = predictor.getName();
	// Optional<TestUser> retVal = Arrays.stream(ALL).filter(user -> {
	// return StringUtils.equalsIgnoreCase(name, user.getUsername());
	// }).findFirst();
	//
	// return retVal.isPresent() ? retVal.get() : null;
	// }

}
