package com.breakabletoy.chatapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    @Column(name = "id")
    private int id;
    @NotNull
    private String content;
    @NotNull
    private String from;
    @NotNull
    private String to;
    @NotNull
    private String chatroom;
    @LastModifiedDate
    private LocalDateTime date;
}
