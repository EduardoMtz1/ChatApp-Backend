package com.breakabletoy.chatapp.repository;

import com.breakabletoy.chatapp.model.Message;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, String> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO messages (messages.content,messages.from,messages.to,chatroom,date) VALUES (?1, ?2, ?3, ?4, ?5);", nativeQuery = true)
    void save(String content, String from, String to, String chatroom, LocalDateTime date);
    @Query(value = "SELECT * FROM messages WHERE messages.from =?1 AND messages.to=?2 AND chatroom=?3 OR messages.from=?2 AND messages.to=?1 AND chatroom=?3 ORDER BY date ASC", nativeQuery = true)
    List<Message> findAllByFromAndToAndChatroom_idOrderByDateDesc(String from, String to, String chatroom);
}
