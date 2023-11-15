package com.example.levi9_challenge.service;

import com.example.levi9_challenge.model.Player;

public interface IPlayerService {
    Player savePlayer(Player player);

    Player addPlayer(Player player);
}
