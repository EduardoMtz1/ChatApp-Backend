package com.breakabletoy.chatapp.service;

import com.breakabletoy.chatapp.model.Chatroom;
import com.breakabletoy.chatapp.repository.ChatroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatroomService {
    private ChatroomRepository chatroomRepository;

    @Autowired
    public ChatroomService(ChatroomRepository chatroomRepository) {
        this.chatroomRepository = chatroomRepository;
    }

    public Chatroom save(Chatroom toSave) {
        return chatroomRepository.save(toSave);
    }
    public Chatroom findChatroomById(String id) {
        return chatroomRepository.findChatroomById(id);
    }

    public Chatroom findChatroomByName(String name) {
        return  chatroomRepository.findChatroomByName(name);
    }
}
