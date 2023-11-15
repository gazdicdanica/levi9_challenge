package com.example.levi9_challenge.service.impl;

import com.example.levi9_challenge.model.GameStatistic;
import com.example.levi9_challenge.repo.GameStatisticsRepo;
import com.example.levi9_challenge.service.IGameStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameStatisticsService implements IGameStatisticsService {

    @Autowired
    private GameStatisticsRepo gameStatisticsRepo;

    @Override
    public GameStatistic saveGameStatistics(GameStatistic gs) {
        return gameStatisticsRepo.save(gs);
    }
//    @Autowired
}
