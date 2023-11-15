package com.example.levi9_challenge.service;

import com.example.levi9_challenge.dto.GameStatisticsDTO;
import com.example.levi9_challenge.dto.StatsDTO;
import com.example.levi9_challenge.model.GameStatistic;
import com.example.levi9_challenge.model.Player;

import java.util.List;
import java.util.Map;

public interface IGameStatisticsService {
    void saveGameStatistics(Map<Player, List<GameStatisticsDTO>> map);
    StatsDTO getPlayerStats(Player player);

    GameStatistic getByPlayer(Player player);
}
