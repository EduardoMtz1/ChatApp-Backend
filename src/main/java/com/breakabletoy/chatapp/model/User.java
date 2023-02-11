package com.breakabletoy.chatapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @NotNull
    private String username;
    @NotNull
    @Size(min = 8)
    private String password;
    @NotNull
    private String chatroom;
}
