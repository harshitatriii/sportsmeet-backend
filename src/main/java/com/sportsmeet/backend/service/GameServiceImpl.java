package com.sportsmeet.backend.service;

import com.sportsmeet.backend.model.Game;
import com.sportsmeet.backend.model.Sport;
import com.sportsmeet.backend.model.User;
import com.sportsmeet.backend.repository.GameRepository;
import com.sportsmeet.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    @Override
    public List<Game> getUpcomingGames() {
        return gameRepository.findByTimeAfter(LocalDateTime.now());
    }

    @Override
    public List<Game> getGamesBySportAndCity(Sport sport, String city) {
        return gameRepository.findBySportAndLocationContainingIgnoreCaseAndTimeAfter(
                sport, city, LocalDateTime.now()
        );
    }

    @Override
    public Game joinGame(Long gameId, Long userId) {
        Game game = gameRepository.findById(gameId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (game != null && user != null && !game.getJoinedPlayers().contains(user)) {
            game.getJoinedPlayers().add(user);
            return gameRepository.save(game);
        }
        return game;
    }


    public List<Game> getGamesForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return gameRepository.findAllByUserParticipation(user);
    }
}
