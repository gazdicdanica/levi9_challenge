package com.example.levi9_challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.levi9_challenge.repo.PlayerRepo;

@Service
public class PlayerService implements IPlayerService{

    @Autowired
    private PlayerRepo playerRepo;
}
