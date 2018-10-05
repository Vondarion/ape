package com.home.ape.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.ape.model.Home;

/**
 * The Interface HomeRepository.
 */
public interface HomeRepository extends JpaRepository<Home, Long> {

}
