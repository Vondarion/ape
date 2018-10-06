package com.home.ape.service;

import java.util.List;
import java.util.Optional;

import com.home.ape.model.Item;
import com.home.ape.service.exception.ServiceException;

/**
 * Provides CRUD (Create, Read, Update, Delete) operations as well as other life-cycle related functions for
 * {@link Item} objects.
 */
public interface ItemService {

	/**
	 * Creates the specified {@link Item}.
	 * 
	 * @param team
	 *            The {@link Item} to create
	 * @return The {@link Item}
	 * @throws ServiceException
	 *             In case of an error
	 */
	Item create(Item team) throws ServiceException;

	/**
	 * Deletes the specified {@link Item}.
	 * 
	 * @param team
	 *            The {@link Item} to delete
	 * @throws ServiceException
	 *             In case of an error
	 */
	void delete(Item team) throws ServiceException;

	/**
	 * Returns a {@link List} of all {@link Item} objects.
	 * 
	 * @return {@link List} of all {@link Item} objects
	 * @throws ServiceException
	 *             In case of an error
	 */
	List<Item> getAll() throws ServiceException;

	/**
	 * Returns the {@link Item} with the specified ID or <code>NULL</code> if no {@link Item} object with the specified
	 * ID exists.
	 * 
	 * @param id
	 *            The id of the {@link Item} to retrieve
	 * @return The {@link Optional<Team>} with the specified ID or <code>NULL</code> if no {@link Item} object with the
	 *         specified ID exists
	 * @throws ServiceException
	 *             In case of an error
	 */
	Optional<Item> getById(Long id) throws ServiceException;

	/**
	 * Saves the specified {@link Item}.
	 * 
	 * @param team
	 *            The {@link Item} to save
	 * @return The {@link Item}
	 * @throws ServiceException
	 *             In case of an error
	 */
	Item save(Item team) throws ServiceException;

	/**
	 * Updates the specified {@link Item}.
	 * 
	 * @param team
	 *            The {@link Item} to update
	 * @return The {@link Item}
	 * @throws ServiceException
	 *             In case of an error
	 */
	Item update(Item team) throws ServiceException;

}
