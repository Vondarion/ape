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

import com.home.ape.model.Item;
import com.home.ape.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

	private static final Logger	log	= LoggerFactory.getLogger(ItemController.class);

	@Autowired
	ItemService					itemService;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getItem(@PathVariable Long id) {
		log.info("Get item: {}", id);
		return new ResponseEntity<>(itemService.getById(id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getItemes() {
		log.info("Get items");
		return new ResponseEntity<>(itemService.getAll(), HttpStatus.OK);
	}

	/**
	 * Updates the {@link Item} object with the specified ID with the properties of the specified {@link Item}.
	 * 
	 * @param id
	 *            The ID of the {@link Item} to return
	 * @param form
	 *            The {@link Item} object with the new property values to be used
	 * @return {@link ResponseEntity} representation of the updated {@link Item}
	 * 
	 * @name Update Item
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> update(@PathParam("id") Long id, @Valid Item item) {
		item = itemService.update(item);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}

	/**
	 * Creates a new {@link Item} object.
	 * 
	 * @param attribute
	 *            The {@link Item} to be created
	 * @return {@link ResponseEntiy} representation of the created {@link Item}
	 * 
	 * @name Create Item
	 */
	@PostMapping() // method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public ResponseEntity<?> create(@Valid @RequestBody Item item, BindingResult bindingResult) {
		item = itemService.create(item);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
}
