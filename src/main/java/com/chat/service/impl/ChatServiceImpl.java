package com.chat.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.chat.model.Chat;
import com.chat.model.ChatPublicFacing;
import com.chat.model.Message;
import com.chat.service.ChatService;

@Service 
public class ChatServiceImpl implements ChatService {

	private List<Chat> chats;

	public int _getChatIndexByUuid(UUID uuid) {
		return IntStream.range(0, this.chats.size())
			.filter(i -> this.chats.get(i).getUuid().equals(uuid))
			.findFirst()
			.orElse(-1);
	}

	public int _getMessageIndexByUuid(List<Message> messages, UUID uuid) {
		return IntStream.range(0, messages.size())
			.filter(i -> messages.get(i).getUuid().equals(uuid))
			.findFirst()
			.orElse(-1);
	}

	private static long _get64LeastSignificantBitsForVersion1() {
		Random random = new Random();
		long random63BitUUID = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
		long variant3BitFlag = 0x8000000000000000L;
		return random63BitUUID | variant3BitFlag;
	}
	
	private static long _get64MostSignificantBitsForVersion1() {
		final long currentTimeMillis = System.currentTimeMillis();
		final long time_low = (currentTimeMillis & 0x0000_0000_FFFF_FFFFL) << 32; 
		final long time_mid = ((currentTimeMillis >> 32) & 0xFFFF) << 16;
		final long version = 1 << 12; 
		final long time_hi = ((currentTimeMillis >> 48) & 0x0FFF);
		return time_low | time_mid | version | time_hi;
	}
	
	public static UUID _generateType1UUID() {
		long most64SigBits = _get64MostSignificantBitsForVersion1();
		long least64SigBits = _get64LeastSignificantBitsForVersion1();
		return new UUID(most64SigBits, least64SigBits);
	}	

	@Override
	public void _requireUuid(String type, UUID uuid) {
		if (uuid == null) {
			throw new IllegalArgumentException( type + " UUID is missing");
		}
	}

	@Override
	public Message addMessage(Message message, UUID chatUuid) {
		Chat chat = this.chats.get(this._getChatIndexByUuid(chatUuid));
		message.setUuid(chatUuid);
		List<Message> chatMessages = chat.getMessages();
		chatMessages.add(message);
		chat.setMessages(chatMessages);
		return null;
	}

	@Override
	public Chat createChat(Chat chat) {
		chat.setUuid(ChatServiceImpl._generateType1UUID());
		this.chats.add(chat);
		return chat;
	}

	@Override
	public void deleteChat(UUID chatUuid) {
		this.chats.remove(this._getChatIndexByUuid(chatUuid));
	}

	@Override
	public void deleteMessage(UUID chatUuid, UUID messageUuid) {
		Chat chat = this.chats.get(this._getChatIndexByUuid(chatUuid));
		List<Message> chatMessages = chat.getMessages();
		chatMessages.remove(this._getMessageIndexByUuid(chatMessages, messageUuid));
		chat.setMessages(chatMessages);
	}

	@Override
	public Chat endChat(UUID chatUuid) {
		Chat chat = this.chats.get(this._getChatIndexByUuid(chatUuid));
		chat.setMessages(null);
		return chat;
	}

	@Override
	public Chat getChat(UUID chatUuid) {
		return this.chats.get(this._getChatIndexByUuid(chatUuid));
	}

	@Override
	public List<Message> getChatMessages(UUID chatUuid) {
		Chat chat = this.chats.get(this._getChatIndexByUuid(chatUuid));
		return chat.getMessages();
	}
	
	@Override
	public List<ChatPublicFacing> getChats() {
		List<ChatPublicFacing> publicFacingChats = new ArrayList<ChatPublicFacing>();
		for(Chat chat : this.chats) {
			ChatPublicFacing publicFacingChat = new ChatPublicFacing();
			publicFacingChat.setUuid(chat.getUuid());
			publicFacingChat.setName(chat.getName());
			publicFacingChat.setCreatedDate(chat.getCreatedDate());
			publicFacingChats.add(publicFacingChat);
		}
		return publicFacingChats;
	}

	@Override
	public Message updateChat(UUID chatUuid, Message message) {
		Chat chat = this.chats.get(this._getChatIndexByUuid(chatUuid));
		List<Message> chatMessages = chat.getMessages();
		Message chatMessage = chatMessages.get(this._getMessageIndexByUuid(chatMessages, message.getUuid()));
		chatMessage.setMessage(message.getMessage());
		return chatMessage;
	}

}
