package com.example.levi9_challenge.service.impl;

import com.example.levi9_challenge.dto.PointsDTO;
import com.example.levi9_challenge.dto.StatsDTO;
import com.example.levi9_challenge.dto.TraditionalStatsDTO;
import com.example.levi9_challenge.model.GameStatistic;
import com.example.levi9_challenge.model.Player;
import com.example.levi9_challenge.repo.GameStatisticsRepo;
import com.example.levi9_challenge.service.IGameStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ToDoubleFunction;

@Service
public class GameStatisticsService implements IGameStatisticsService {

    @Autowired
    private GameStatisticsRepo gameStatisticsRepo;

    @Override
    public GameStatistic saveGameStatistics(GameStatistic gs) {
        return gameStatisticsRepo.save(gs);
    }

    @Override
    public List<GameStatistic> getAllByPlayer(Player player) {
        return gameStatisticsRepo.getAllByPlayer(player);
    }

    @Override
    public StatsDTO getPlayerStats(Player player) {
        StatsDTO ret = new StatsDTO();
        ret.setPlayerName(player.getFullName());

        List<GameStatistic> allGames = getAllByPlayer(player);
        ret.setGamesPlayed(allGames.size());

        return null;
    }

    private TraditionalStatsDTO getTraditionalStats(List<GameStatistic> allGames){

        int gameNum = allGames.size();

        TraditionalStatsDTO dto = new TraditionalStatsDTO();
        dto.setFreeThrows(calculatePoints(allGames, GameStatistic::getFreeThrowMade, GameStatistic::getFreeThrowAttempted));
        dto.setTwoPoints(calculatePoints(allGames, GameStatistic::getTwoPointsMade, GameStatistic::getTwoPointsAttempted));
        dto.setThreePoints(calculatePoints(allGames, GameStatistic::getThreePointsMade, GameStatistic::getThreePointsAttempted));

//        dto.setRebounds(allGames.stream().mapToDouble(GameStatistic::getRebounds).sum() / gameNum);
        return dto;
    }

    private PointsDTO calculatePoints(List<GameStatistic> allGames,
                                      ToDoubleFunction<GameStatistic> madeGetter,
                                      ToDoubleFunction<GameStatistic> attemptedGetter) {
        PointsDTO points = new PointsDTO();
        int gamesNum = allGames.size();

        double madeSum = allGames.stream()
                .mapToDouble(madeGetter)
                .sum();

        double attemptedSum = allGames.stream()
                .mapToDouble(attemptedGetter)
                .sum();

        points.setMade(madeSum / gamesNum);
        points.setAttempts(attemptedSum / gamesNum);
        points.setShootingPercentage(points.getMade() / points.getAttempts() * 100);

        return points;
    }



}
