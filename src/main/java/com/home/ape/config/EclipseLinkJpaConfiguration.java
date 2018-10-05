package com.home.ape.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * The Class EclipseLinkJpaConfiguration.
 */
@Configuration
public class EclipseLinkJpaConfiguration extends JpaBaseConfiguration {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(EclipseLinkJpaConfiguration.class);

	/**
	 * Instantiates a new eclipse link jpa configuration.
	 *
	 * @param dataSource
	 *            the data source
	 * @param properties
	 *            the properties
	 * @param jtaTransactionManager
	 *            the jta transaction manager
	 * @param transactionManagerCustomizers
	 *            the transaction manager customizers
	 */
	protected EclipseLinkJpaConfiguration(DataSource dataSource, JpaProperties properties,
			ObjectProvider<JtaTransactionManager> jtaTransactionManager,
			ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
		super(dataSource, properties, jtaTransactionManager, transactionManagerCustomizers);
		log.debug("EclipseLinkJpaConfiguration instantiated");

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration#createJpaVendorAdapter()
	 */
	@Override
	protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
		log.debug("New EclipseLinkJpaVendorAdapter creation");
		return new EclipseLinkJpaVendorAdapter();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration#getVendorProperties()
	 */
	@Override
	protected Map<String, Object> getVendorProperties() {
		HashMap<String, Object> map = new HashMap<>();
		map.put(PersistenceUnitProperties.WEAVING, "static");
		map.put(PersistenceUnitProperties.DDL_GENERATION, "drop-and-create-tables");
		map.put(PersistenceUnitProperties.LOGGING_LOGGER, "ServerLogger");
		map.put(PersistenceUnitProperties.LOGGING_LEVEL, "INFO");
		map.put(PersistenceUnitProperties.LOGGING_PARAMETERS, "true");
		map.put(PersistenceUnitProperties.LOGGING_TIMESTAMP, "true");
		map.put(PersistenceUnitProperties.LOGGING_SESSION, "true");
		// Create a log of key value pairs of the property map
		if ( log.isDebugEnabled() ) {
			map.keySet().forEach(key -> {
				log.debug("VendorProperty: {}:{}", key, map.get(key));
			});
		}

		return map;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("toString()", super.toString());
		return builder.toString();
	}

}
