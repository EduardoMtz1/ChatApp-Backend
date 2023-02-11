package com.breakabletoy.chatapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "chatrooms")
public class Chatroom {
    @Id
    @NotNull
    private String id;
    @NotNull
    private String name;
}
