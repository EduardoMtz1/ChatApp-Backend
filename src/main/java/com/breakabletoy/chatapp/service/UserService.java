package com.breakabletoy.chatapp.service;

import com.breakabletoy.chatapp.model.User;
import com.breakabletoy.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User toSave) {
        if (userRepository.findUserByUsername(toSave.getUsername()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User already exist!");
        }
        String encodedPassword = passwordEncoder.encode(toSave.getPassword());
        toSave.setPassword(encodedPassword);
        return userRepository.save(toSave);
    }

    public void updateChatroom(String username, String chatroomID) {
        userRepository.updateChatroomByUsername(username, chatroomID);
    }

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public List<String> findAllByChatroom_id(String chatroom_id) {
        return userRepository.findAllByChatroom(chatroom_id);
    }
}
