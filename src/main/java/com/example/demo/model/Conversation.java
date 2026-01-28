package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "conversations")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Conversation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                   id;
    @Column(name = "type")
    private String                    type;
    @Column(name = "title")
    private String                    title;
    @Column(name = "status")
    private Byte                      status;
    @OneToMany(mappedBy = "conversation")
    private List<ConversationMembers> conversationMembers;
    public Conversation( String type, String title, Byte status )
    {
        this.type = type;
        this.title = title;
        this.status = status;
    }
}
