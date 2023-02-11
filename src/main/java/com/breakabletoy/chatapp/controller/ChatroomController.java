package com.breakabletoy.chatapp.controller;

import com.breakabletoy.chatapp.model.Chatroom;
import com.breakabletoy.chatapp.service.ChatroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@Validated
@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class ChatroomController {
    @Autowired
    ChatroomService chatroomService;


    @PostMapping("/api/chatroom/")
    public ResponseEntity<?> newChatroom(@RequestBody String name) {
        String finalName = name.split("=")[0];
        Chatroom saved = chatroomService.findChatroomByName(finalName);
        if(saved != null) {
            return new ResponseEntity<>(Map.of("Error", "Chatroom already exist"), HttpStatus.BAD_REQUEST);
        }
        Chatroom room = new Chatroom();
        room.setName(finalName);
        room.setId(UUID.randomUUID().toString());
        chatroomService.save(room);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @GetMapping("/api/chatroom/{id}")
    public ResponseEntity<?> checkExistentChatroom(@PathVariable String id) {
        Chatroom saved = chatroomService.findChatroomById(id);
        if(saved == null) {
            return new ResponseEntity<>(Map.of("Error", "Chatroom doesn't exist"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

}
