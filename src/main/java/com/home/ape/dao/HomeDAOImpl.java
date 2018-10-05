package com.home.ape.dao;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.home.ape.model.Home;

/**
 * Default implementation of the {@link HomeDAO} interface based on Spring Data JPA.
 * 
 * @see http://static.springsource.org/spring-data/data-jpa/docs/current/reference/html/
 */
@Service
public class HomeDAOImpl implements HomeDAO {
	/**
	 * The spring-data-jpa repository to be used for persistence operations.
	 */
	@Autowired
	HomeRepository repository = null;

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
	public void delete(Home entity) {
		repository.delete(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sap.consulting.tipservice.dao.TeamDAO#deleteAll(java.lang.Iterable)
	 */
	@Override
	public void deleteAll(Iterable<? extends Home> entities) {
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
	public List<Home> findAll() {
		return repository.findAll(new Sort(Sort.Direction.ASC, "name"));
	}

	/*
	 * (non-Javadoc)
	 * @see com.sap.consulting.tipservice.dao.TeamDAO#findById(java.lang.Long)
	 */
	@Override
	public Optional<Home> findById(Long id) {
		return repository.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sap.consulting.tipservice.dao.TeamDAO#save(com.sap.consulting.tipservice.model.Team)
	 */
	@Override
	public Home save(@Valid Home entity) {
		return repository.save(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sap.consulting.tipservice.dao.TeamDAO#save(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Home> save(@Valid List<? extends Home> entities) {
		return (List<Home>) repository.saveAll(entities);
	}
}
