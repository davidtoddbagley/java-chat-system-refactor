package com.chat.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chat.model.Chat;
import com.chat.model.Message;
import com.chat.service.ChatService;

@Service 
public class ChatServiceImpl implements ChatService {

	@Override
	public Chat createChat(Chat chatInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message addMessage(Message message, Long chatId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chat endChat(Long chatId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Chat> getChats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chat getChat(Long chatId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteChat(Long chatId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Message updateChat(Long chatId, Message message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMessage(Long messageId) {
		// TODO Auto-generated method stub
		
	}
	
}
