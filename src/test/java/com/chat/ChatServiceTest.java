package com.chat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.isNull;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chat.model.Chat;
import com.chat.service.impl.ChatServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChatApplication.class)
public class ChatServiceTest {

	ChatServiceImpl chatService = new ChatServiceImpl();

	UUID uuidRandom1 = UUID.randomUUID();
	UUID uuidRandom2 = UUID.randomUUID();

	@Test
	void givenEmptyChats_whenGetChatIndexByUuid_thenReturnsNull() throws InterruptedException {
		assertThat(chatService._getChatIndexByUuid(uuidRandom1), isNull());
	}

	@Test
	void whenGenerateType1UUID_thenReturnsNotNullValue() throws InterruptedException {
		assertThat(ChatServiceImpl._generateType1UUID(), isNotNull());
	}

	@Test
	void givenChatUuid_whenGetChatIndexByUuid_thenReturnsTargetChat() throws InterruptedException {
		Chat chat = new Chat();
		chat.setName("Test Chat Channel");
		Chat createdChat = chatService.createChat(chat);
		assertEquals(chatService.getChat(createdChat.getUuid()), createdChat);
	}

}
