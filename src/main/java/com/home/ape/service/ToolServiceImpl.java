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

import com.home.ape.dao.ToolDAO;
import com.home.ape.model.Tool;
import com.home.ape.security.annotation.RequiresAdminRole;
import com.home.ape.service.exception.ServiceException;

/**
 * Default implementation of the {@link ToolService} interface.
 */
@Service
@Primary
public class ToolServiceImpl extends BaseService implements ToolService {
	/**
	 * The {@link Logger} to be used. Declared here so that it shows up in logging console early on.
	 */
	@SuppressWarnings("unused")
	private static final Logger	logger	= LoggerFactory.getLogger(ToolServiceImpl.class);

	/**
	 * The {@link ToolDAO} used for persistence operations.
	 */
	@Autowired
	ToolDAO						toolDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequiresAdminRole
	public Tool create(@Valid Tool tool) throws ServiceException {
		return this.save(tool);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequiresAdminRole
	public void delete(Tool tool) throws ServiceException {
		try {
			toolDAO.delete(tool);
		} catch ( Exception ex ) {
			this.handleException(ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Cacheable("all")
	public List<Tool> getAll() throws ServiceException {
		List<Tool> retVal = null;

		try {
			retVal = toolDAO.findAll();
		} catch ( Exception ex ) {
			this.handleException(ex);
		}

		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Tool> getById(Long id) throws ServiceException {
		Optional<Tool> retVal = null;

		try {
			retVal = toolDAO.findById(id);
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
	public Tool save(@Valid Tool tool) throws ServiceException {
		Tool retVal = null;

		try {
			retVal = toolDAO.save(tool);
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
	public Tool update(@Valid Tool tool) throws ServiceException {
		return this.save(tool);
	}
}