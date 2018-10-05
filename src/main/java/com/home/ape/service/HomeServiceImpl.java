package com.home.ape.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.home.ape.dao.HomeDAO;
import com.home.ape.model.Home;
import com.home.ape.security.annotation.RequiresAdminRole;
import com.home.ape.service.exception.ServiceException;

/**
 * Default implementation of the {@link FixtureService} interface.
 */
@Service
@Primary
public class HomeServiceImpl extends BaseService implements HomeService {
	/**
	 * The {@link Logger} to be used. Declared here so that it shows up in logging console early on.
	 */
	@SuppressWarnings("unused")
	private static final Logger	logger	= LoggerFactory.getLogger(HomeServiceImpl.class);

	/**
	 * The {@link HomeDAO} used for persistence operations.
	 */
	@Autowired
	HomeDAO						homeDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequiresAdminRole
	public Home create(@Valid Home home) throws ServiceException {
		return this.save(home);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequiresAdminRole
	public void delete(Home home) throws ServiceException {
		try {
			homeDAO.delete(home);
		} catch ( Exception ex ) {
			this.handleException(ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Cacheable("all")
	public List<Home> getAll() throws ServiceException {
		List<Home> retVal = null;

		try {
			retVal = homeDAO.findAll();
		} catch ( Exception ex ) {
			this.handleException(ex);
		}

		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Home> getById(Long id) throws ServiceException {
		Optional<Home> retVal = null;

		try {
			retVal = homeDAO.findById(id);
		} catch ( Exception ex ) {
			this.handleException(ex);
		}

		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequiresAdminRole
	public Home save(@Valid Home home) throws ServiceException {
		Home retVal = null;

		try {
			retVal = homeDAO.save(home);
		} catch ( Exception ex ) {
			this.handleException(ex);
		}

		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequiresAdminRole
	public Home update(@Valid Home home) throws ServiceException {
		return this.save(home);
	}
}