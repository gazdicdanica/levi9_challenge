package com.example.levi9_challenge;

import com.example.levi9_challenge.service.impl.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = PlayerService.class)
public class PlayerServiceTests {
    @Autowired
    private PlayerService playerService;

}
