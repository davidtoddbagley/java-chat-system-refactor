package com.chat.service;

import java.util.List;

import com.chat.model.Chat;
import com.chat.model.Message;

public interface ChatService {

	Chat createChat(Chat chatInfo);

	Message addMessage(Message message, Long chatId);

	Chat endChat(Long chatId);

	List<Chat> getChats();

	Chat getChat(Long chatId);

	void deleteChat(Long chatId);

	Message updateChat(Long chatId, Message message);

	void deleteMessage(Long messageId);
	
}
