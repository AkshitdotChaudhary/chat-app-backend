package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                   id;
    @Column(name = "username")
    private String                    username;
    @JsonIgnore
    @Column(name = "password")
    private String                    password;
    @Column(name = "status")
    private Byte                      status;
    @OneToMany(mappedBy = "user")
    private List<ConversationMembers> conversationMembers;
    public User( String username, String password )
    {
        this.username = username;
        this.password = password;
    }
}
