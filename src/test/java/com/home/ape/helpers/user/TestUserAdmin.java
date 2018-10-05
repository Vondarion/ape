package com.home.ape.helpers.user;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;

import com.home.ape.security.Roles;

/**
 * The Class TestUserAdmin.
 */
public class TestUserAdmin extends TestUser {

	/** The Constant serialVersionUID. */
	private static final long		serialVersionUID			= -3278951286110145464L;

	/** The Constant log. */
	private static final Logger		log							= LoggerFactory.getLogger(TestUserAdmin.class);

	// settings, careful when changing - can affect lots of test cases!
	/** The Constant USERNAME. */
	public static final String		USERNAME					= "Test-Admin";

	/** The Constant PASSWORD. */
	public static final String		PASSWORD					= "secret1";

	/** The Constant IS_ENABLED. */
	public static final Boolean		IS_ENABLED					= true;

	/** The Constant IS_ACCOUNT_NON_EXPIRED. */
	public static final Boolean		IS_ACCOUNT_NON_EXPIRED		= true;

	/** The Constant IS_ACCOUNT_NON_LOCKED. */
	public static final Boolean		IS_ACCOUNT_NON_LOCKED		= true;

	/** The Constant IS_CREDENTIALS_NON_EXPIRED. */
	public static final Boolean		IS_CREDENTIALS_NON_EXPIRED	= true;

	/** The Constant ROLES. */
	public static final String[]	ROLES						= { Roles.ADMIN_ROLE, Roles.USER_ROLE };

	/**
	 * Instantiates a new test user admin.
	 */
	public TestUserAdmin() {
		super(USERNAME, PASSWORD, IS_ENABLED, IS_ACCOUNT_NON_EXPIRED, IS_CREDENTIALS_NON_EXPIRED, IS_ACCOUNT_NON_LOCKED,
				TestUserAdmin.buildAuthorities());
	}

	/**
	 * Builds the authorities.
	 *
	 * @return the collection
	 */
	private static Collection<GrantedAuthority> buildAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();

		log.info("TestUser {} has the following roles: {}", USERNAME, String.join(",", ROLES));
		for ( String role : ROLES ) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
		}

		return authorities;
	}

	/**
	 * The Interface ExecuteAsTestUserAdmin.
	 */
	@Retention(RetentionPolicy.RUNTIME)
	// can't set annotation values from String[] because of arrays have no runtime immutability
	@WithMockUser(username = USERNAME, roles = { Roles.USER_ROLE, Roles.ADMIN_ROLE })
	public @interface ExecuteAsTestUserAdmin {
	}

}
