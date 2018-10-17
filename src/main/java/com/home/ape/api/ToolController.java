package com.home.ape.api;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.home.ape.model.Tool;
import com.home.ape.service.ToolService;

@RestController
@RequestMapping("/tools")
public class ToolController {

	private static final Logger	log	= LoggerFactory.getLogger(ToolController.class);

	@Autowired
	ToolService					toolService;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getItem(@PathVariable Long id) {
		log.info("Get tool: {}", id);
		return new ResponseEntity<>(toolService.getById(id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getItemes() {
		log.info("Get tools");
		return new ResponseEntity<>(toolService.getAll(), HttpStatus.OK);
	}

	/**
	 * Updates the {@link Tool} object with the specified ID with the properties of the specified {@link Tool}.
	 * 
	 * @param id
	 *            The ID of the {@link Tool} to return
	 * @param form
	 *            The {@link Tool} object with the new property values to be used
	 * @return {@link ResponseEntity} representation of the updated {@link Tool}
	 * 
	 * @name Update Item
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> update(@PathParam("id") Long id, @Valid Tool tool) {
		tool = toolService.update(tool);
		return new ResponseEntity<>(tool, HttpStatus.OK);
	}

	/**
	 * Creates a new {@link Tool} object.
	 * 
	 * @param attribute
	 *            The {@link Tool} to be created
	 * @return {@link ResponseEntiy} representation of the created {@link Tool}
	 * 
	 * @name Create Item
	 */
	@PostMapping() // method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public ResponseEntity<?> create(@Valid @RequestBody Tool tool, BindingResult bindingResult) {
		tool = toolService.create(tool);
		return new ResponseEntity<>(tool, HttpStatus.OK);
	}
}
