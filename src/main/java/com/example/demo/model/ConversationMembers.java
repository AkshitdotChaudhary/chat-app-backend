package com.example.demo.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "conversation_members")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ConversationMembers
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer       id;
    @ManyToOne
    @JoinColumn(name = "conversation_id", referencedColumnName = "id")
    private Conversation  conversation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User          user;
    @Column(name = "status")
    private Byte          status;
    @Column(name = "joined_at")
    private LocalDateTime joinedAt;
    @Column(name = "left_at")
    private LocalDateTime leftAt;
    public ConversationMembers( Conversation conversation, User user, Byte status )
    {
        this.conversation = conversation;
        this.user = user;
        this.status = status;
        this.joinedAt = LocalDateTime.now();
    }
}
