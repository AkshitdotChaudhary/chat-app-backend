package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "session_token")
@Data
@NoArgsConstructor
public class SessionToken
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer       id;
    @Column(name = "token")
    private String        token;
    @ManyToOne
    @JoinColumn(name = "loginusr_id", referencedColumnName = "id")
    private User          loginUser;
    @Column(name = "status")
    private Byte          status;
    @Column(name = "login_date")
    private Long          loginDate;
    @Column(name = "login_ip")
    private String        loginIp;
    @Column(name = "logout_date")
    private Long          logoutDate;
    @Column(name = "logout_ip")
    private String        logoutIp;
    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;
}
