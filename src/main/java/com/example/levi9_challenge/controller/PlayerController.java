package com.example.levi9_challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.levi9_challenge.service.IPlayerService;

@RestController
@RequestMapping(value = "/api/player", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlayerController {

    @Autowired
    private IPlayerService playerService;
}
