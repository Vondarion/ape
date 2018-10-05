package com.home.ape.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;

import com.home.ape.util.ApplicationLogger;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Bean
	AccessDeniedHandler defaultAccessDeniedHandler() {
		return new AccessDeniedHandlerImpl();
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// the final response should not provide details that helps possible attackers
		// but we want to get some valid log message
		ApplicationLogger.SECURITY_LOG.warn("Access denied: {}", accessDeniedException.getMessage());
		defaultAccessDeniedHandler().handle(request, response, accessDeniedException);

	}

}
