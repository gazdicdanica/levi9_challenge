package com.example.levi9_challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsDTO {
    private String playerName;
    private int gamesPlayed;
    private TraditionalStatsDTO traditional;
    private AdvancedStatsDTO advanced;
}
