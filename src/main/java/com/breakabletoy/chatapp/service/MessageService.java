package com.breakabletoy.chatapp.service;

import com.breakabletoy.chatapp.model.Message;
import com.breakabletoy.chatapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public void sendMessage(String room, String to, Message message) {
        messageRepository.save(message.getContent(), message.getFrom(), message.getTo(), message.getChatroom(), message.getDate());
        String content = "{\"Username\":\"" + message.getFrom() + "\", \"content\":\""+message.getContent()+"\", \"date\":\"" + message.getDate()+ "\"}";
        simpMessagingTemplate.convertAndSend("/queue/"+room+"/"+to, content);
    }

    public void sendAlert (String room, String content) {
        simpMessagingTemplate.convertAndSend("/topic/" + room, content);
    }

    public List<Message> getMessageHistory(@PathVariable("chatroom_id") String chatroom_id, @PathVariable("from") String from, @PathVariable("to") String to) {
        return messageRepository.findAllByFromAndToAndChatroom_idOrderByDateDesc(from, to, chatroom_id);
    }
}
