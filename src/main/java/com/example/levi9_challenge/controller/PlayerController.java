package com.example.levi9_challenge.controller;

import com.example.levi9_challenge.dto.StatsDTO;
import com.example.levi9_challenge.model.Player;
import com.example.levi9_challenge.service.IGameStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.levi9_challenge.service.IPlayerService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PlayerController {

    @Autowired
    private IPlayerService playerService;
    @Autowired
    private IGameStatisticsService gameStatisticsService;


    @GetMapping(value = "/stats/player/{playerFullName}")
    public ResponseEntity<StatsDTO> getPlayerStats(@PathVariable String playerFullName){
        Player player = playerService.findByFullName(playerFullName);
        StatsDTO stats = gameStatisticsService.getPlayerStats(player);

        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}
