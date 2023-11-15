package com.example.levi9_challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraditionalStatsDTO {
    private PointsDTO freeThrows;
    private PointsDTO twoPoints;
    private PointsDTO threePoints;
    private double points;
    private double rebounds;
    private double blocks;
    private double assists;
    private double steals;
    private double turnovers;
}
