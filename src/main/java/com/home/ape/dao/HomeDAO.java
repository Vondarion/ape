package com.home.ape.dao;

import java.util.List;
import java.util.Optional;

import com.home.ape.model.Home;

/**
 * The Interface HomeDAO.
 */
public interface HomeDAO {

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
	public void delete(Home entity);

	/**
	 * Delete.
	 *
	 * @param entities
	 *            the entities
	 */
	public void deleteAll(Iterable<? extends Home> entities);

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
	public List<Home> findAll();

	/**
	 * Find by id.
	 *
	 * @param id
	 *            the id
	 * @return the optional result (Team)
	 */
	Optional<Home> findById(Long id);

	/**
	 * Save.
	 *
	 * @param entity
	 *            the entity
	 * @return the team
	 */
	public Home save(Home entity);

	/**
	 * Save.
	 *
	 * @param entities
	 *            the entities
	 * @return the list
	 */
	public List<Home> save(List<? extends Home> entities);

}
