package com.example.levi9_challenge.repo;

import com.example.levi9_challenge.model.GameStatistic;
import com.example.levi9_challenge.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameStatisticsRepo extends JpaRepository<GameStatistic, Long> {

    GameStatistic getByPlayer(Player player);
}
