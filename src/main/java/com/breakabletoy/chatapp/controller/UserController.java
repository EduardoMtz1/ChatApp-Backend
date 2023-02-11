package com.breakabletoy.chatapp.controller;

import com.breakabletoy.chatapp.model.User;
import com.breakabletoy.chatapp.security.UserDetailsImpl;
import com.breakabletoy.chatapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Validated
@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User body) {
        userService.save(body);
        return new ResponseEntity<>(Map.of("Ok", "User created"), HttpStatus.OK);
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody User body) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword())
        );
        if(authentication == null){
            return new ResponseEntity<>(Map.of("Error","Not authenticated"), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(Map.of("Ok", "Authenticated"), HttpStatus.OK);
    }

    @PutMapping("/api/user/chatroom/")
    public ResponseEntity<?> setChatroom(@Valid @RequestBody User user,  @AuthenticationPrincipal UserDetails details) {
        userService.updateChatroom(details.getUsername(), user.getChatroom());
        return new ResponseEntity<>(Map.of("Ok", "Chatroom set"), HttpStatus.OK);
    }
}
