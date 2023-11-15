package com.example.levi9_challenge.service.impl;

import com.example.levi9_challenge.dto.*;
import com.example.levi9_challenge.model.GameStatistic;
import com.example.levi9_challenge.model.Player;
import com.example.levi9_challenge.repo.GameStatisticsRepo;
import com.example.levi9_challenge.service.IGameStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.ToDoubleFunction;

@Service
public class GameStatisticsService implements IGameStatisticsService {

    @Autowired
    private GameStatisticsRepo gameStatisticsRepo;

    @Override
    public void saveGameStatistics(Map<Player, List<GameStatisticsDTO>> map) {
        for (Map.Entry<Player, List<GameStatisticsDTO>> entry : map.entrySet()) {
            GameStatistic gameStatistic = new GameStatistic();
            gameStatistic.setPlayer(entry.getKey());
            gameStatistic.setGamesPlayed(entry.getValue().size());
            gameStatistic.setFreeThrowMade(getMeanValue(entry.getValue(), GameStatisticsDTO::getFreeThrowMade));
            gameStatistic.setFreeThrowAttempted(getMeanValue(entry.getValue(), GameStatisticsDTO::getFreeThrowAttempted));
            gameStatistic.setTwoPointsMade(getMeanValue(entry.getValue(), GameStatisticsDTO::getTwoPointsMade));
            gameStatistic.setTwoPointsAttempted(getMeanValue(entry.getValue(), GameStatisticsDTO::getTwoPointsAttempted));
            gameStatistic.setThreePointsMade(getMeanValue(entry.getValue(), GameStatisticsDTO::getThreePointsMade));
            gameStatistic.setThreePointsAttempted(getMeanValue(entry.getValue(), GameStatisticsDTO::getThreePointsAttempted));
            gameStatistic.setRebounds(getMeanValue(entry.getValue(), GameStatisticsDTO::getRebounds));
            gameStatistic.setBlocks(getMeanValue(entry.getValue(), GameStatisticsDTO::getBlocks));
            gameStatistic.setAssists(getMeanValue(entry.getValue(), GameStatisticsDTO::getAssists));
            gameStatistic.setSteals(getMeanValue(entry.getValue(), GameStatisticsDTO::getSteals));
            gameStatistic.setTurnovers(getMeanValue(entry.getValue(), GameStatisticsDTO::getTurnovers));
            gameStatistic.calculateAdvanced();

            gameStatisticsRepo.save(gameStatistic);

        }
    }

    @Override
    public GameStatistic getByPlayer(Player player) {
        return gameStatisticsRepo.getByPlayer(player);
    }

    @Override
    public StatsDTO getPlayerStats(Player player) {
        StatsDTO ret = new StatsDTO();
        ret.setPlayerName(player.getFullName());

        GameStatistic gameStatistic = getByPlayer(player);
        ret.setGamesPlayed(gameStatistic.getGamesPlayed());

        TraditionalStatsDTO traditional = getTraditionalStats(gameStatistic);
        ret.setTraditional(traditional);
        ret.setAdvanced(getAdvancedStats(gameStatistic));
        return ret;
    }

    private TraditionalStatsDTO getTraditionalStats(GameStatistic gameStatistic){

        TraditionalStatsDTO dto = new TraditionalStatsDTO();
        dto.setFreeThrows(calculatePointStats(gameStatistic.getFreeThrowMade(), gameStatistic.getFreeThrowAttempted()));
        dto.setTwoPoints(calculatePointStats(gameStatistic.getTwoPointsMade(), gameStatistic.getTwoPointsAttempted()));
        dto.setThreePoints(calculatePointStats(gameStatistic.getThreePointsMade(), gameStatistic.getThreePointsAttempted()));

        dto.setRebounds(roundValue(gameStatistic.getRebounds()));
        dto.setBlocks(roundValue(gameStatistic.getBlocks()));
        dto.setAssists(roundValue(gameStatistic.getAssists()));
        dto.setSteals(roundValue(gameStatistic.getSteals()));
        dto.setTurnovers(roundValue(gameStatistic.getTurnovers()));
        dto.setPoints(roundValue(gameStatistic.getPoints()));

        return dto;
    }


    private AdvancedStatsDTO getAdvancedStats(GameStatistic gameStatistic){
        AdvancedStatsDTO dto = new AdvancedStatsDTO();
        dto.setValorization(roundValue(gameStatistic.getValorization()));
        dto.setEffectiveFieldGoalPercentage(roundValue(gameStatistic.getEffectiveFieldGoalPercentage()));
        dto.setTrueShootingPercentage(roundValue(gameStatistic.getTrueShootingPercentage()));
        dto.setHollingerAssistRatio(roundValue(gameStatistic.getHollingerAssistRatio()));
        return dto;
    }


    private double getMeanValue(List<GameStatisticsDTO> list, ToDoubleFunction<GameStatisticsDTO> getter){
        return list.stream().mapToDouble(getter).sum() / list.size();
    }

    private double roundValue(double value){
        return Math.round(value * 10.0) / 10.0;
    }

    private PointsDTO calculatePointStats(double made, double attempted) {
        PointsDTO points = new PointsDTO();

        points.setMade(roundValue(made));
        points.setAttempts(roundValue(attempted));

        points.setShootingPercentage(roundValue((made / attempted) * 100));

        return points;
    }



}
