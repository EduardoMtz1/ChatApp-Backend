package com.breakabletoy.chatapp.repository;

import com.breakabletoy.chatapp.model.Chatroom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends CrudRepository<Chatroom, String> {
    Chatroom findChatroomById(String id);

    Chatroom findChatroomByName(String name);
}
