package com.chat.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	//TODO - @PostMapping(...)
	public ResponseEntity<?> createChat(@NotNull @RequestBody Chat chatInfo) {
		try {
			return new ResponseEntity<>(service.createChat(chatInfo), HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//TODO @PostMapping(...)
	public ResponseEntity<?> addMessage(@NotNull @PathVariable Long chatId, @NotNull @RequestBody Message message) {
		try {
			return new ResponseEntity<>(service.addMessage(message, chatId), HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//TODO @PostMapping(...)
	public ResponseEntity<?> endChat(@NotNull @PathVariable Long chatId) {
		try {
			return new ResponseEntity<>(service.endChat(chatId), HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	//TODO @GetMapping(...)
	public ResponseEntity<?> getChats() {
		try {
			return new ResponseEntity<>(service.getChats(), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	//TODO @GetMapping(...)
	public ResponseEntity<?> getChat(@NotNull @PathVariable Long chatId) {
		try {
			return new ResponseEntity<>(service.getChat(chatId), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	//TODO @PutMapping(...)
	public ResponseEntity<?> updateChat(@NotNull @PathVariable Long chatId, @NotNull @RequestBody Message message) {
		try {
			return new ResponseEntity<>(service.updateChat(chatId, message), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	//TODO @DeleteMapping(...)
	public ResponseEntity<?> deleteChat(@NotNull @PathVariable Long chatId) {
		try {
			service.deleteChat(chatId);
			return new ResponseEntity<>( HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//TODO @DeleteMapping(...)
	public ResponseEntity<?> deleteMessage(@NotNull @PathVariable Long messageId) {
		try {
			service.deleteMessage(messageId);
			return new ResponseEntity<>( HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
