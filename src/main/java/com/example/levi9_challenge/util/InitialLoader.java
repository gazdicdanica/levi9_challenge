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
        return new GameStatistic(0L, player, Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]),
                Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6]),
                Double.parseDouble(tokens[7]), Double.parseDouble(tokens[8]), Double.parseDouble(tokens[9]),
                Double.parseDouble(tokens[10]), Double.parseDouble(tokens[11]), Double.parseDouble(tokens[12]));
    }

    private Player parsePlayer(String line){
        String[] tokens = line.split(",");
        return new Player(0L, tokens[0], Player.Position.valueOf(tokens[1]));
    }
}
