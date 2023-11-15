package com.example.levi9_challenge.controller;

import com.example.levi9_challenge.dto.StatsDTO;
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


    @GetMapping(value = "/stats/player/{playerFullName}")
    public ResponseEntity<StatsDTO> getPlayerStats(@PathVariable String playerFullName){

        return new ResponseEntity<>(new StatsDTO(), HttpStatus.OK);
    }
}
