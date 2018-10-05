package com.home.ape.security;

// TODO: Auto-generated Javadoc
/**
 * The Class Roles.
 */
// To use e.g. in spring security annotation we avoided to use an enumeration for the roles
public class Roles {

	/** The Constant ROLE_PREFIX. */
	private static final String	ROLE_PREFIX				= "ROLE_";				// Required for service layer
																				// security - issues
																				// without ROLE_
																				// prefix (spring security default)

	/** The Constant USER_ROLE. */
	public static final String	USER_ROLE				= "USER";

	/** The Constant ADMIN_ROLE. */
	public static final String	ADMIN_ROLE				= "ADMIN";

	/** The Constant USER_ROLE_WITH_PREFIX. */
	public static final String	USER_ROLE_WITH_PREFIX	= ROLE_PREFIX + "USER";

	/** The Constant ADMIN_ROLE_WITH_PREFIX. */
	public static final String	ADMIN_ROLE_WITH_PREFIX	= ROLE_PREFIX + "ADMIN";

	/**
	 * Instantiates a new roles.
	 */
	private Roles() {
	} // hidden contructor

}
