package com.example.levi9_challenge.util;


import com.example.levi9_challenge.model.GameStatistic;
import com.example.levi9_challenge.model.Player;
import com.example.levi9_challenge.service.IGameStatisticsService;
import com.example.levi9_challenge.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

@Component
public class InitialLoader implements CommandLineRunner {

    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private IGameStatisticsService gameService;
    @Autowired
    private IPlayerService playerService;

    @Override
    public void run(String... args) throws Exception {
        Resource resource = resourceLoader.getResource("classpath:L9HomeworkChallengePlayersInput.csv");

        Scanner sc = new Scanner(resource.getFile());
        sc.useDelimiter("\r");
        String line = sc.next();
        while (sc.hasNext()){
            line = sc.next();
            Player player = parsePlayer(line);
            player = playerService.addPlayer(player);
            GameStatistic statistic = parseStatistics(line, player);
            gameService.saveGameStatistics(statistic);
        }

        sc.close();
    }

    private GameStatistic parseStatistics(String line, Player player){
        String[] tokens = line.split(",");
        return new GameStatistic(0L, player, Float.parseFloat(tokens[2]), Float.parseFloat(tokens[3]),
                Float.parseFloat(tokens[4]), Float.parseFloat(tokens[5]), Float.parseFloat(tokens[6]),
                Float.parseFloat(tokens[7]), Float.parseFloat(tokens[8]), Float.parseFloat(tokens[9]),
                Float.parseFloat(tokens[10]), Float.parseFloat(tokens[11]), Float.parseFloat(tokens[12]));
    }

    private Player parsePlayer(String line){
        String[] tokens = line.split(",");
        return new Player(0L, tokens[0], Player.Position.valueOf(tokens[1]));
    }
}