package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
public class Message
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer       id;
    @ManyToOne
    @JoinColumn(name = "conversation_id", referencedColumnName = "id")
    private Conversation  conversation;
    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private User          sender;
    @Column(name = "content")
    private String        content;
    @Column(name = "message_type")
    private String        messageType;
    @Column(name = "status")
    private Byte          status         = (byte) 1;
    @Column(name = "created_date")
    private LocalDateTime createdAt      = LocalDateTime.now();
    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate = LocalDateTime.now();
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
