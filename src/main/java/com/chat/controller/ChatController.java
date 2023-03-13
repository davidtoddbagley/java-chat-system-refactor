package com.chat.controller;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chat.model.Chat;
import com.chat.model.Message;
import com.chat.service.ChatService;

@RestController
public class ChatController {
	
	@Autowired
	private ChatService service;

	@DeleteMapping("/chats/{chatUuid}")
	public ResponseEntity<?> deleteChat(@NotNull @PathVariable UUID chatUuid) {
		try {
			service._requireUuid("Chat", chatUuid);
			service.deleteChat(chatUuid);
			return new ResponseEntity<>( HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/chats/{chatUuid}/messages/{messageUuid}")
	public ResponseEntity<?> deleteMessage(@NotNull @PathVariable UUID chatUuid, @NotNull @PathVariable UUID messageUuid) {
		try {
			service._requireUuid("Chat", chatUuid);
			service._requireUuid("Message", messageUuid);
			service.deleteMessage(chatUuid, messageUuid);
			return new ResponseEntity<>( HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/chats")
	public ResponseEntity<?> getChats() {
		try {
			return new ResponseEntity<>(service.getChats(), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/chats/{chatUuid}")
	public ResponseEntity<?> getChat(@NotNull @PathVariable UUID chatUuid) {
		try {
			service._requireUuid("Chat", chatUuid);
			return new ResponseEntity<>(service.getChat(chatUuid), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/chats/{chatUuid}/messages")
	public ResponseEntity<?> getChatMessages(@NotNull @PathVariable UUID chatUuid) {
		try {
			service._requireUuid("Chat", chatUuid);
			return new ResponseEntity<>(service.getChatMessages(chatUuid), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PatchMapping("/chats/{chatUuid}/messages")
	public ResponseEntity<?> endChat(@NotNull @PathVariable UUID chatUuid) {
		/**
		 * Is this a duplicate route?
		 * (see @DeleteMapping("/chats/{chatUuid}::deleteChat")
		 *
		 * Assuming this route exists for a reason...
		 *   
		 * ASSUMPTIONS:
		 *		(a) current chat CHANNEL [aka. "CHAT ROOM"] to persist 
		 *		(b) current exchange of MESSAGES has been concluded
		 *		(c) channel to be CLEARED of all MESSAGES
		 */
		try {
			service._requireUuid("Chat", chatUuid);
			return new ResponseEntity<>(service.endChat(chatUuid), HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/chats")
	public ResponseEntity<?> createChat(@NotNull @RequestBody Chat chatInfo) {
		try {
			return new ResponseEntity<>(service.createChat(chatInfo), HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/chats/{chatUuid}/messages")
	public ResponseEntity<?> addMessage(@NotNull @PathVariable UUID chatUuid, @NotNull @RequestBody Message message) {
		try {
			service._requireUuid("Chat", chatUuid);
			return new ResponseEntity<>(service.addMessage(message, chatUuid), HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/chats/{chatUuid}")
	public ResponseEntity<?> updateChat(@NotNull @PathVariable UUID chatUuid, @NotNull @RequestBody Message message) {
		try {
			service._requireUuid("Chat", chatUuid);
			return new ResponseEntity<>(service.updateChat(chatUuid, message), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
}
