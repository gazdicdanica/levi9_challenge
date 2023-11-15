package com.example.levi9_challenge.util;


import com.example.levi9_challenge.dto.GameStatisticsDTO;
import com.example.levi9_challenge.model.GameStatistic;
import com.example.levi9_challenge.model.Player;
import com.example.levi9_challenge.service.IGameStatisticsService;
import com.example.levi9_challenge.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class InitialLoader implements CommandLineRunner {

    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private IGameStatisticsService gameService;
    @Autowired
    private IPlayerService playerService;

    @Value("classpath:L9HomeworkChallengePlayersInput.csv")
    Resource resource;

    @Override
    public void run(String... args) throws Exception {
        Map<Player, List<GameStatisticsDTO>> statistics = new HashMap<>();
        try (InputStream in = getClass().getResourceAsStream("/L9HomeworkChallengePlayersInput.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                Player player = parsePlayer(line);
                player = playerService.addPlayer(player);
                GameStatisticsDTO dto = parseStatistics(line, player);
                if(statistics.containsKey(player)){
                    statistics.get(player).add(dto);
                }else{
                    statistics.put(player, new ArrayList<>(List.of(dto)));
                }
            }
        }
        gameService.saveGameStatistics(statistics);

    }

    private GameStatisticsDTO parseStatistics(String line, Player player){
        String[] tokens = line.split(",");
        return new GameStatisticsDTO(Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]),
                Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6]),
                Double.parseDouble(tokens[7]), Double.parseDouble(tokens[8]), Double.parseDouble(tokens[9]),
                Double.parseDouble(tokens[10]), Double.parseDouble(tokens[11]), Double.parseDouble(tokens[12]));
    }

    private Player parsePlayer(String line){
        String[] tokens = line.split(",");
        return new Player(0L, tokens[0], Player.Position.valueOf(tokens[1]));
    }
}
