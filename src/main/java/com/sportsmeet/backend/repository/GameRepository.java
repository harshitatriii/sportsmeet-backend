package com.sportsmeet.backend.repository;

import com.sportsmeet.backend.model.Game;
import com.sportsmeet.backend.model.Sport;
import com.sportsmeet.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    // Get upcoming games by sport and city
    List<Game> findBySportAndLocationContainingIgnoreCaseAndTimeAfter(
            Sport sport, String cityKeyword, LocalDateTime time
    );

    // Find all future games in a city
    List<Game> findByLocationContainingIgnoreCaseAndTimeAfter(
            String cityKeyword, LocalDateTime time
    );

    List<Game> findByTimeAfter(LocalDateTime time);

    @Query("SELECT g FROM Game g WHERE g.host = :user OR :user MEMBER OF g.joinedPlayers")
    List<Game> findAllByUserParticipation(@Param("user") User user);

}
