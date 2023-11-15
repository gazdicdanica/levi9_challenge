package com.example.levi9_challenge.service;

import com.example.levi9_challenge.model.Player;

import java.util.Optional;

public interface IPlayerService {
    Player savePlayer(Player player);

    Player addPlayer(Player player);

    Player findByFullName(String fullName);
    Player findById(Long id);


}
