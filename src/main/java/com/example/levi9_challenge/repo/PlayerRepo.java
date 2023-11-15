package com.example.levi9_challenge.repo;


import com.example.levi9_challenge.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepo extends JpaRepository<Player, Long> {
}
