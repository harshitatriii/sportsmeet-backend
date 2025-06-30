package com.sportsmeet.backend.service;

import com.sportsmeet.backend.model.Game;
import com.sportsmeet.backend.model.Sport;

import java.util.List;

public interface GameService {
    Game createGame(Game game);
    Game getGameById(Long id);
    List<Game> getUpcomingGames();
    List<Game> getGamesBySportAndCity(Sport sport, String city);
    Game joinGame(Long gameId, Long userId);
    List<Game> getGamesForUser(Long userId);


}
