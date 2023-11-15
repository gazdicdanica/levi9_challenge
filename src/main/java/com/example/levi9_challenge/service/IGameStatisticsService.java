package com.example.levi9_challenge.service;

import com.example.levi9_challenge.dto.StatsDTO;
import com.example.levi9_challenge.model.GameStatistic;
import com.example.levi9_challenge.model.Player;

import java.util.List;

public interface IGameStatisticsService {
    GameStatistic saveGameStatistics(GameStatistic gs);
    StatsDTO getPlayerStats(Player player);

    List<GameStatistic> getAllByPlayer(Player player);
}
