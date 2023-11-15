package com.example.levi9_challenge.repo;


import com.example.levi9_challenge.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepo extends JpaRepository<Player, Long> {

    Optional<Player> findById(Long id);

    Optional<Player> findByFullName(String fullName);
}
