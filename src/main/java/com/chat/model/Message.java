package com.chat.model;

import java.util.Date;
import java.util.UUID;

public class Message {
	private UUID chatUuid;
	private UUID messageUuid;
	private String message;
	private Date createdDate;

	public UUID getChatUuid() {
		return chatUuid;
	}
	public void setChatUuid(UUID chatUuid) {
		this.chatUuid = chatUuid;
	}
	public UUID getUuid() {
		return messageUuid;
	}
	public void setUuid(UUID messageUuid) {
		this.messageUuid = messageUuid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
