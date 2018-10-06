package com.home.ape.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.ape.model.Item;

/**
 * The Interface ItemRepository.
 */
public interface ItemRepository extends JpaRepository<Item, Long> {

}
