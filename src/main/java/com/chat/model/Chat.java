package com.chat.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Chat {
	private UUID chatUuid;
	private String name;
	private List<Message> messages;
	private Date createdDate;
	public UUID getUuid() {
		return chatUuid;
	}
	public void setUuid(UUID chatUuid) {
		this.chatUuid = chatUuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}	
}
