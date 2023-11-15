package com.example.levi9_challenge.repo;

import com.example.levi9_challenge.model.GameStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameStatisticsRepo extends JpaRepository<GameStatistic, Long> {
}
