package com.sportsmeet.backend.controller;

import com.sportsmeet.backend.model.Game;
import com.sportsmeet.backend.model.Sport;
import com.sportsmeet.backend.model.User;
import com.sportsmeet.backend.repository.UserRepository;
import com.sportsmeet.backend.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
//@CrossOrigin(origins = "*") // allow frontend access
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            System.out.println("No authentication found!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Object principal = auth.getPrincipal();
        System.out.println("Principal: " + principal);

        if (!(principal instanceof User)) {
            System.out.println("Principal is not a User instance!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User currentUser = (User) principal;
        game.setHost(currentUser);
        Game savedGame = gameService.createGame(game);
        return ResponseEntity.ok(savedGame);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.getGameById(id));
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<Game>> getUpcomingGames() {
        return ResponseEntity.ok(gameService.getUpcomingGames());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Game>> getGamesBySportAndCity(
            @RequestParam Sport sport,
            @RequestParam String city
    ) {
        return ResponseEntity.ok(gameService.getGamesBySportAndCity(sport, city));
    }

    @PostMapping("/{gameId}/join")
    public ResponseEntity<Game> joinGame(
            @PathVariable Long gameId,
            @RequestParam Long userId
    ) {
        return ResponseEntity.ok(gameService.joinGame(gameId, userId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Game>> getGamesByUser(@PathVariable Long userId) {
        List<Game> games = gameService.getGamesForUser(userId);
        return ResponseEntity.ok(games);
    }

}
