package com.home.ape.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.ape.model.Tool;

/**
 * The Interface ToolRepository.
 */
public interface ToolRepository extends JpaRepository<Tool, Long> {

}
