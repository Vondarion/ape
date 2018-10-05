package com.home.ape.service;

import java.util.List;
import java.util.Optional;

import com.home.ape.model.Home;
import com.home.ape.service.exception.ServiceException;

/**
 * Provides CRUD (Create, Read, Update, Delete) operations as well as other life-cycle related functions for
 * {@link Home} objects.
 */
public interface HomeService {

	/**
	 * Creates the specified {@link Home}.
	 * 
	 * @param team
	 *            The {@link Home} to create
	 * @return The {@link Home}
	 * @throws ServiceException
	 *             In case of an error
	 */
	Home create(Home team) throws ServiceException;

	/**
	 * Deletes the specified {@link Home}.
	 * 
	 * @param team
	 *            The {@link Home} to delete
	 * @throws ServiceException
	 *             In case of an error
	 */
	void delete(Home team) throws ServiceException;

	/**
	 * Returns a {@link List} of all {@link Home} objects.
	 * 
	 * @return {@link List} of all {@link Home} objects
	 * @throws ServiceException
	 *             In case of an error
	 */
	List<Home> getAll() throws ServiceException;

	/**
	 * Returns the {@link Home} with the specified ID or <code>NULL</code> if no {@link Home} object with the specified
	 * ID exists.
	 * 
	 * @param id
	 *            The id of the {@link Home} to retrieve
	 * @return The {@link Optional<Team>} with the specified ID or <code>NULL</code> if no {@link Home} object with the
	 *         specified ID exists
	 * @throws ServiceException
	 *             In case of an error
	 */
	Optional<Home> getById(Long id) throws ServiceException;

	/**
	 * Saves the specified {@link Home}.
	 * 
	 * @param team
	 *            The {@link Home} to save
	 * @return The {@link Home}
	 * @throws ServiceException
	 *             In case of an error
	 */
	Home save(Home team) throws ServiceException;

	/**
	 * Updates the specified {@link Home}.
	 * 
	 * @param team
	 *            The {@link Home} to update
	 * @return The {@link Home}
	 * @throws ServiceException
	 *             In case of an error
	 */
	Home update(Home team) throws ServiceException;

}
