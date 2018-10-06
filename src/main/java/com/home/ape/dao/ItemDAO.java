package com.home.ape.dao;

import java.util.List;
import java.util.Optional;

import com.home.ape.model.Item;

/**
 * The Interface ItemDAO.
 */
public interface ItemDAO {

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
	public void delete(Item entity);

	/**
	 * Delete.
	 *
	 * @param entities
	 *            the entities
	 */
	public void deleteAll(Iterable<? extends Item> entities);

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
	public List<Item> findAll();

	/**
	 * Find by id.
	 *
	 * @param id
	 *            the id
	 * @return the optional result (Team)
	 */
	Optional<Item> findById(Long id);

	/**
	 * Save.
	 *
	 * @param entity
	 *            the entity
	 * @return the team
	 */
	public Item save(Item entity);

	/**
	 * Save.
	 *
	 * @param entities
	 *            the entities
	 * @return the list
	 */
	public List<Item> save(List<? extends Item> entities);

}
