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

import com.home.ape.dao.ItemDAO;
import com.home.ape.model.Item;
import com.home.ape.security.annotation.RequiresAdminRole;
import com.home.ape.service.exception.ServiceException;

/**
 * Default implementation of the {@link ItemService} interface.
 */
@Service
@Primary
public class ItemServiceImpl extends BaseService implements ItemService {
	/**
	 * The {@link Logger} to be used. Declared here so that it shows up in logging console early on.
	 */
	@SuppressWarnings("unused")
	private static final Logger	logger	= LoggerFactory.getLogger(ItemServiceImpl.class);

	/**
	 * The {@link ItemDAO} used for persistence operations.
	 */
	@Autowired
	ItemDAO						itemDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequiresAdminRole
	public Item create(@Valid Item item) throws ServiceException {
		return this.save(item);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequiresAdminRole
	public void delete(Item item) throws ServiceException {
		try {
			itemDAO.delete(item);
		} catch ( Exception ex ) {
			this.handleException(ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Cacheable("all")
	public List<Item> getAll() throws ServiceException {
		List<Item> retVal = null;

		try {
			retVal = itemDAO.findAll();
		} catch ( Exception ex ) {
			this.handleException(ex);
		}

		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Item> getById(Long id) throws ServiceException {
		Optional<Item> retVal = null;

		try {
			retVal = itemDAO.findById(id);
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
	public Item save(@Valid Item item) throws ServiceException {
		Item retVal = null;

		try {
			retVal = itemDAO.save(item);
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
	public Item update(@Valid Item item) throws ServiceException {
		return this.save(item);
	}
}