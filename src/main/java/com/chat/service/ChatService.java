package com.chat.service;

import java.util.List;
import java.util.UUID;

import com.chat.model.Chat;
import com.chat.model.ChatPublicFacing;
import com.chat.model.Message;

public interface ChatService {

	/**
	 * Add message to an active chat channel
	 * @param message
	 * @param chatUuid
	 * @return
	 */
	Message addMessage(Message message, UUID chatUuid);

	/**
	 * Create a new chat channel
	 * @param chatInfo
	 * @return
	 */
	Chat createChat(Chat chatInfo);

	/**
	 * 
	 * Remove chat channel from chats array
	 * @param chatUuid
	 */
	void deleteChat(UUID chatUuid);

	/**
	 * Remove a specific message from the array of messages for a given chat channel
	 * @param chatUuid
	 * @param messageUuid
	 */
	void deleteMessage(UUID chatUuid, UUID messageUuid);

	/**
	 * Reset the array of messasges for a given chat channel
	 * @param chatUuid
	 * @return
	 */
	Chat endChat(UUID chatUuid);

	/**
	 * Returns a chat channel object
	 * @param chatUuid
	 * @return
	 */
	Chat getChat(UUID chatUuid);

	/**
	 * Returns an array of messages
	 * @param chatUuid
	 * @return
	 */
	List<Message> getChatMessages(UUID chatUuid);

	/**
	 * Returns an array of chat channel uuids
	 * @return
	 */
	List<ChatPublicFacing> getChats();

	/**
	 * Updates the content of a specific message
	 * @param chatUuid
	 * @param message
	 * @return
	 */
	Message updateChat(UUID chatUuid, Message message);

}
