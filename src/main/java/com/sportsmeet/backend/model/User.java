package com.sportsmeet.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true)
    private String email;
    private String city;
    private String profileImageUrl;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = Sport.class)
    @Enumerated(EnumType.STRING)
    private Set<Sport> preferredSports = new HashSet<>();


    @ManyToMany(mappedBy = "joinedPlayers",fetch = FetchType.EAGER)
    @JsonIgnore // prevent circular loop
    private List<Game> joinedGames = new ArrayList<>();

    @OneToMany(mappedBy = "host",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Game> hostedGames = new ArrayList<>();

    // Getters and Setters
}
