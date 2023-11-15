package com.example.levi9_challenge.service.impl;

import com.example.levi9_challenge.dto.AdvancedStatsDTO;
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

        TraditionalStatsDTO traditional = getTraditionalStats(allGames);
        ret.setTraditional(traditional);
        ret.setAdvanced(getAdvancedStats(traditional));
        return ret;
    }

    private AdvancedStatsDTO getAdvancedStats(TraditionalStatsDTO tradStats){
        AdvancedStatsDTO dto = new AdvancedStatsDTO();
        double ftm = tradStats.getFreeThrows().getMade();
        double fta = tradStats.getFreeThrows().getAttempts();
        double twopm = tradStats.getTwoPoints().getMade();
        double twopa = tradStats.getTwoPoints().getAttempts();
        double threepm = tradStats.getThreePoints().getMade();
        double threepa = tradStats.getThreePoints().getAttempts();

        //(FTM + 2x2PM + 3x3PM + REB + BLK + AST + STL) - (FTA-FTM + 2PA-2PM + 3PA-3PM + TOV)
        dto.setValorization(roundValue(( ftm + twopm * 2 + threepm * 3
                + tradStats.getRebounds() + tradStats.getBlocks() + tradStats.getAssists() + tradStats.getSteals())
                - (fta - ftm + twopa - twopm + threepa - threepm + tradStats.getTurnovers())));

        //(2PM + 3PM + 0,5 * 3PM) / (2PA + 3PA) * 100
        dto.setEffectiveFieldGoalPercentage(roundValue((twopm + threepm + 0.5 * threepm) / (twopa + threepa) * 100.0));

        //PTS / (2 * (2PA + 3PA +0,475 * FTA)) * 100
        dto.setTrueShootingPercentage(roundValue(tradStats.getPoints() / (2 * (twopa + threepa + 0.475 * fta)) * 100.0));

        //AST / (2PA + 3PA + 0,475 * FTA + AST + TOV) * 100
        dto.setHollingerAssistRatio(roundValue(tradStats.getAssists() / (twopa + threepa + 0.475 * fta + tradStats.getAssists() + tradStats.getTurnovers())*100.0));

        return dto;
    }

    private TraditionalStatsDTO getTraditionalStats(List<GameStatistic> allGames){

        TraditionalStatsDTO dto = new TraditionalStatsDTO();
        dto.setFreeThrows(calculatePointsStats(allGames, GameStatistic::getFreeThrowMade, GameStatistic::getFreeThrowAttempted));
        dto.setTwoPoints(calculatePointsStats(allGames, GameStatistic::getTwoPointsMade, GameStatistic::getTwoPointsAttempted));
        dto.setThreePoints(calculatePointsStats(allGames, GameStatistic::getThreePointsMade, GameStatistic::getThreePointsAttempted));

        dto.setRebounds(getMeanValue(allGames, GameStatistic::getRebounds));
        dto.setBlocks(getMeanValue(allGames,GameStatistic::getBlocks));
        dto.setAssists(getMeanValue(allGames,GameStatistic::getAssists));
        dto.setSteals(getMeanValue(allGames,GameStatistic::getSteals));
        dto.setTurnovers(getMeanValue(allGames, GameStatistic::getTurnovers));
        dto.setPoints(dto.getFreeThrows().getMade() + dto.getTwoPoints().getMade() * 2 + dto.getThreePoints().getMade() * 3);

        return dto;
    }

    private double getMeanValue(List<GameStatistic> list, ToDoubleFunction<GameStatistic> getter){
        return roundValue(list.stream().mapToDouble(getter).sum() / list.size());
    }

    private double roundValue(double value){
        return Math.round(value * 10.0) / 10.0;
    }

    private PointsDTO calculatePointsStats(List<GameStatistic> allGames,
                                           ToDoubleFunction<GameStatistic> madeGetter,
                                           ToDoubleFunction<GameStatistic> attemptedGetter) {
        PointsDTO points = new PointsDTO();
        int gamesNum = allGames.size();

        double madeMean = allGames.stream()
                .mapToDouble(madeGetter)
                .sum() / gamesNum;

        double attemptedMean = allGames.stream()
                .mapToDouble(attemptedGetter)
                .sum() / gamesNum;

        points.setMade(roundValue(madeMean));
        points.setAttempts(roundValue(attemptedMean));
        points.setShootingPercentage(roundValue(madeMean / attemptedMean * 100));

        return points;
    }



}
