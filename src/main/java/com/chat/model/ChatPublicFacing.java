package com.chat.model;

import java.util.Date;
import java.util.UUID;

public class ChatPublicFacing {
	private UUID chatUuid;
	private String name;
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}	
}
