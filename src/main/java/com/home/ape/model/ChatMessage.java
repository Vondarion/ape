package com.home.ape.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage extends BaseObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5376278778327064901L;

	private enum MessageType {
		CHAT,
		JOIN,
		LEAVE
	}

	MessageType	type;
	String		content;
	String		sender;
}
