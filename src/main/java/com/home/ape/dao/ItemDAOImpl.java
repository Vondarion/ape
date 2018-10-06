package com.home.ape.dao;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.home.ape.model.Item;

/**
 * Default implementation of the {@link ItemDAO} interface based on Spring Data JPA.
 * 
 * @see http://static.springsource.org/spring-data/data-jpa/docs/current/reference/html/
 */
@Service
public class ItemDAOImpl implements ItemDAO {
	/**
	 * The spring-data-jpa repository to be used for persistence operations.
	 */
	@Autowired
	ItemRepository repository = null;

	/*
	 * (non-Javadoc)
	 * @see com.sap.consulting.tipservice.dao.TeamDAO#count()
	 */
	@Override
	public long count() {
		return repository.count();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sap.consulting.tipservice.dao.TeamDAO#delete(com.sap.consulting.tipservice.model.Team)
	 */
	@Override
	public void delete(Item entity) {
		repository.delete(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sap.consulting.tipservice.dao.TeamDAO#deleteAll(java.lang.Iterable)
	 */
	@Override
	public void deleteAll(Iterable<? extends Item> entities) {
		repository.deleteAll(entities);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sap.consulting.tipservice.dao.TeamDAO#deleteById(java.lang.Long)
	 */
	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sap.consulting.tipservice.dao.TeamDAO#deleteAll()
	 */
	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sap.consulting.tipservice.dao.TeamDAO#existsById(java.lang.Long)
	 */
	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sap.consulting.tipservice.dao.TeamDAO#findAll()
	 */
	@Override
	public List<Item> findAll() {
		return repository.findAll(new Sort(Sort.Direction.ASC, "name"));
	}

	/*
	 * (non-Javadoc)
	 * @see com.sap.consulting.tipservice.dao.TeamDAO#findById(java.lang.Long)
	 */
	@Override
	public Optional<Item> findById(Long id) {
		return repository.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sap.consulting.tipservice.dao.TeamDAO#save(com.sap.consulting.tipservice.model.Team)
	 */
	@Override
	public Item save(@Valid Item entity) {
		return repository.save(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sap.consulting.tipservice.dao.TeamDAO#save(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Item> save(@Valid List<? extends Item> entities) {
		return (List<Item>) repository.saveAll(entities);
	}
}
