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

import com.home.ape.model.Home;
import com.home.ape.service.HomeService;

@RestController
@RequestMapping("/homes")
public class HomeController {

	private static final Logger	log	= LoggerFactory.getLogger(HomeController.class);

	@Autowired
	HomeService					homeService;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getHome(@PathVariable Long id) {
		log.info("Get home: {}", id);
		return new ResponseEntity<>(homeService.getById(id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getHomees() {
		log.info("Get homes");
		return new ResponseEntity<>(homeService.getAll(), HttpStatus.OK);
	}

	/**
	 * Updates the {@link Home} object with the specified ID with the properties of the specified {@link Home}.
	 * 
	 * @param id
	 *            The ID of the {@link Home} to return
	 * @param form
	 *            The {@link Home} object with the new property values to be used
	 * @return {@link ResponseEntity} representation of the updated {@link Home}
	 * 
	 * @name Update Home
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> update(@PathParam("id") Long id, @Valid Home home) {
		home = homeService.update(home);
		return new ResponseEntity<>(home, HttpStatus.OK);
	}

	/**
	 * Creates a new {@link Home} object.
	 * 
	 * @param attribute
	 *            The {@link Home} to be created
	 * @return {@link ResponseEntiy} representation of the created {@link Home}
	 * 
	 * @name Create Home
	 */
	@PostMapping() // method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public ResponseEntity<?> create(@Valid @RequestBody Home home, BindingResult bindingResult) {
		home = homeService.create(home);
		return new ResponseEntity<>(home, HttpStatus.OK);
	}
}
