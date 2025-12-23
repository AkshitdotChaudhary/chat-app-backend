package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "messages")
@Data
public class Message
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sender")
    private String sender;

    @Column(name = "text")
    private String text;

    public Message(String sender, String text){
        this.sender = sender;
        this.text = text;
    }
}
