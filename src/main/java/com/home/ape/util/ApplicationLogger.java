package com.home.ape.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationLogger {

	public static final Logger	API				= LoggerFactory.getLogger("API");
	public static final Logger	SERVICE			= LoggerFactory.getLogger("SERVICE");
	public static final Logger	SECURITY_LOG	= LoggerFactory.getLogger("SECURITY");

	private ApplicationLogger() {
	}

}
