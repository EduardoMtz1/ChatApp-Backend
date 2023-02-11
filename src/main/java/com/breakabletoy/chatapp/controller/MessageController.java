package com.breakabletoy.chatapp.controller;

import com.breakabletoy.chatapp.model.Message;
import com.breakabletoy.chatapp.model.User;
import com.breakabletoy.chatapp.service.MessageService;
import com.breakabletoy.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class MessageController {
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @MessageMapping("/chat/{room}/{to}")
    public void sendMessage(@DestinationVariable String room, @DestinationVariable String to, @Payload Message message) {
        messageService.sendMessage(room, to, message);
    }

    @MessageMapping("/alert/{room}")
    public void sendAlert(@DestinationVariable String room, @Payload String content) {
        messageService.sendAlert(room, content);
    }

    @GetMapping("/api/history/{room}/{from}/{to}")
    public List<Message> getHistoryChat(@PathVariable String room, @PathVariable String from, @PathVariable String to) {
        return messageService.getMessageHistory(room, from, to);
    }

    @GetMapping("/api/userlist/{room}")
    public List<String> getUserList(@PathVariable String room, @AuthenticationPrincipal UserDetails details) {
        return userService.findAllByChatroom_id(room);
    }
}
