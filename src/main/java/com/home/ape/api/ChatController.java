package com.home.ape.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.home.ape.model.ChatMessage;

@Controller
public class ChatController {

	private static final Logger log = LoggerFactory.getLogger(ChatController.class);

	@MessageMapping("/chat")
	@SendTo("/topic/all")
	public ChatMessage chat(ChatMessage message) throws Exception {
		log.debug("Incoming message: {}", message);
		System.out.println("Incoming message: " + message);
		Thread.sleep(2000);
		return ChatMessage.builder().sender("APE").content("UGUG").build();
	}
}
