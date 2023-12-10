package com.example.pathfinder.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name= "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
 
    @Column(name= "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(columnDefinition = "TEXT")
    private String text;

    @ManyToOne
    private User author;

    @ManyToOne
    private User recipient;
}
