package com.home.ape.service;

import java.util.List;
import java.util.Optional;

import com.home.ape.model.Tool;
import com.home.ape.service.exception.ServiceException;

/**
 * Provides CRUD (Create, Read, Update, Delete) operations as well as other life-cycle related functions for
 * {@link Tool} objects.
 */
public interface ToolService {

	/**
	 * Creates the specified {@link Tool}.
	 * 
	 * @param tool
	 *            The {@link Tool} to create
	 * @return The {@link Tool}
	 * @throws ServiceException
	 *             In case of an error
	 */
	Tool create(Tool tool) throws ServiceException;

	/**
	 * Deletes the specified {@link Tool}.
	 * 
	 * @param tool
	 *            The {@link Tool} to delete
	 * @throws ServiceException
	 *             In case of an error
	 */
	void delete(Tool tool) throws ServiceException;

	/**
	 * Returns a {@link List} of all {@link Tool} objects.
	 * 
	 * @return {@link List} of all {@link Tool} objects
	 * @throws ServiceException
	 *             In case of an error
	 */
	List<Tool> getAll() throws ServiceException;

	/**
	 * Returns the {@link Tool} with the specified ID or <code>NULL</code> if no {@link Tool} object with the specified
	 * ID exists.
	 * 
	 * @param id
	 *            The id of the {@link Tool} to retrieve
	 * @return The {@link Optional<Team>} with the specified ID or <code>NULL</code> if no {@link Tool} object with the
	 *         specified ID exists
	 * @throws ServiceException
	 *             In case of an error
	 */
	Optional<Tool> getById(Long id) throws ServiceException;

	/**
	 * Saves the specified {@link Tool}.
	 * 
	 * @param tool
	 *            The {@link Tool} to save
	 * @return The {@link Tool}
	 * @throws ServiceException
	 *             In case of an error
	 */
	Tool save(Tool tool) throws ServiceException;

	/**
	 * Updates the specified {@link Tool}.
	 * 
	 * @param tool
	 *            The {@link Tool} to update
	 * @return The {@link Tool}
	 * @throws ServiceException
	 *             In case of an error
	 */
	Tool update(Tool tool) throws ServiceException;

}
