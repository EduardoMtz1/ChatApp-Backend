package com.breakabletoy.chatapp.repository;

import com.breakabletoy.chatapp.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findUserByUsername(String username);
    @Query(value = "SELECT username FROM users WHERE chatroom =?1",nativeQuery = true)
    List<String> findAllByChatroom(String chatroom_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET chatroom =?2 WHERE username=?1",nativeQuery = true)
    void updateChatroomByUsername(String username, String chatroomId);
}
