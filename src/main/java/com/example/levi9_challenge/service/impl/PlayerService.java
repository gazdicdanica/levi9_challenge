package com.example.levi9_challenge.service.impl;

import com.example.levi9_challenge.exception.EntityNotFoundException;
import com.example.levi9_challenge.model.Player;
import com.example.levi9_challenge.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.levi9_challenge.repo.PlayerRepo;

import java.util.Optional;

@Service
public class PlayerService implements IPlayerService {

    @Autowired
    private PlayerRepo playerRepo;

    @Override
    public Player savePlayer(Player player) {
        return playerRepo.save(player);
    }

    @Override
    public Player addPlayer(Player player) {
        Optional<Player> optional = playerRepo.findByFullName(player.getFullName());
        if(optional.isEmpty()){
            return savePlayer(player);
        }
        return optional.get();
    }

    @Override
    public Player findByFullName(String fullName) {
        return playerRepo.findByFullName(fullName).orElseThrow(() -> new EntityNotFoundException("Player not found"));
    }

    @Override
    public Player findById(Long id) {
        return playerRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Player not found"));
    }


}
