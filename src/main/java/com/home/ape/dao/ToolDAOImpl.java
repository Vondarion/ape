package com.home.ape.dao;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.home.ape.model.Tool;

// TODO: Auto-generated Javadoc
/**
 * Default implementation of the {@link ToolDAO} interface based on Spring Data JPA.
 * 
 * @see http://static.springsource.org/spring-data/data-jpa/docs/current/reference/html/
 */
@Service
public class ToolDAOImpl implements ToolDAO {
	/**
	 * The spring-data-jpa repository to be used for persistence operations.
	 */
	@Autowired
	ToolRepository repository = null;

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
	 * @see com.home.ape.dao.ToolDAO#delete(com.home.ape.model.Tool)
	 */
	@Override
	public void delete(Tool entity) {
		repository.delete(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.home.ape.dao.ToolDAO#deleteAll(java.lang.Iterable)
	 */
	@Override
	public void deleteAll(Iterable<? extends Tool> entities) {
		repository.deleteAll(entities);
	}

	/*
	 * (non-Javadoc)
	 * @see com.home.ape.dao.ToolDAO#deleteById(java.lang.Long)
	 */
	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.home.ape.dao.ToolDAO#deleteAll()
	 */
	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	/*
	 * (non-Javadoc)
	 * @see com.home.ape.dao.ToolDAO#existsById(java.lang.Long)
	 */
	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.home.ape.dao.ToolDAO#findAll()
	 */
	@Override
	public List<Tool> findAll() {
		return repository.findAll(new Sort(Sort.Direction.ASC, "name"));
	}

	/*
	 * (non-Javadoc)
	 * @see com.home.ape.dao.ToolDAO#findById(java.lang.Long)
	 */
	@Override
	public Optional<Tool> findById(Long id) {
		return repository.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.home.ape.dao.ToolDAO#save(com.home.ape.model.Tool)
	 */
	@Override
	public Tool save(@Valid Tool entity) {
		return repository.save(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.home.ape.dao.ToolDAO#save(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Tool> save(@Valid List<? extends Tool> entities) {
		return (List<Tool>) repository.saveAll(entities);
	}
}
