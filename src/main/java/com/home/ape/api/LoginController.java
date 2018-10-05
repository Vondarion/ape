package com.home.ape.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.home.ape.util.ApplicationLogger;

@RestController
@RequestMapping("/login")
public class LoginController {

	@RequestMapping(path = "/user", method = RequestMethod.GET)
	public ResponseEntity<?> login(HttpServletRequest request, Authentication authentication) {
		if ( authentication != null ) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			ApplicationLogger.SECURITY_LOG.info("User login {} with authorities: " + userDetails.getAuthorities());
			return new ResponseEntity<>(userDetails, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
		}

	}

}
