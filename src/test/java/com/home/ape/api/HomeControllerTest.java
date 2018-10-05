package com.home.ape.api;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.ape.helpers.ApeServiceTest;
import com.home.ape.model.Home;

@AutoConfigureMockMvc
@ApeServiceTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HomeControllerTest {

	private static final Logger	log	= LoggerFactory.getLogger(HomeControllerTest.class);

	@Autowired
	private MockMvc				mvc;

	@Autowired
	HomeController				homeController;

	@LocalServerPort
	static int					port;

	@BeforeAll
	public static void setUp() throws Exception {
		log.info("Setup for {}", HomeControllerTest.class.getCanonicalName());
		log.info("***** CURRENT SERVER PORT: {} *****", port);
		log.info("Setup for {} finished", HomeControllerTest.class.getCanonicalName());
	}

	@AfterAll
	public static void tearDown() throws Exception {
	}

	// @Test
	// public void testControllerInjection() {
	// assertThat(homeController).isNotNull();
	// }

	@Test
	public void testCreateHome() throws Exception {
		Home home = Home.builder().build();
		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(home);

		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post("/homes").accept(MediaType.APPLICATION_JSON).content(content))
				.andReturn();
		log.info("JUnit Test - 'post {} to /homes': {}", content, result.getResponse().getContentAsString());
	}

	@Test
	public void testGetHomes() throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/homes").accept(MediaType.APPLICATION_JSON))
				.andReturn();
		log.info("JUnit Test - '/homes': {}", result.getResponse().getContentAsString());
	}

}
