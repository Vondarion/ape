package com.home.ape.dao;

import java.util.List;
import java.util.Optional;

import com.home.ape.model.Tool;

/**
 * The Interface ToolDAO.
 */
public interface ToolDAO {

	/**
	 * Count.
	 *
	 * @return the long
	 */
	public long count();

	/**
	 * Delete.
	 *
	 * @param entity
	 *            the entity
	 */
	public void delete(Tool entity);

	/**
	 * Delete.
	 *
	 * @param entities
	 *            the entities
	 */
	public void deleteAll(Iterable<? extends Tool> entities);

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	public void deleteById(Long id);

	/**
	 * Delete all.
	 */
	public void deleteAll();

	/**
	 * Exists by id.
	 *
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	boolean existsById(Long id);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Tool> findAll();

	/**
	 * Find by id.
	 *
	 * @param id
	 *            the id
	 * @return the optional result (Team)
	 */
	Optional<Tool> findById(Long id);

	/**
	 * Save.
	 *
	 * @param entity
	 *            the entity
	 * @return the tool
	 */
	public Tool save(Tool entity);

	/**
	 * Save.
	 *
	 * @param entities
	 *            the entities
	 * @return the list
	 */
	public List<Tool> save(List<? extends Tool> entities);

}
