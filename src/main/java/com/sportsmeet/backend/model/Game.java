package com.sportsmeet.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;


@Getter
@Setter
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;
    private Double latitude;
    private Double longitude;
    private LocalDateTime time;
    private int maxPlayers;
    private String skillLevel; // "Beginner", "Intermediate", etc.
    private String notes;

    @Enumerated(EnumType.STRING)
    private Sport sport;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private User host;

    @ManyToMany
    @JoinTable(
            name = "game_players",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> joinedPlayers = new ArrayList<>();

    // Getters and Setters
}
