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
    private float points;
    private float rebounds;
    private float blocks;
    private float assists;
    private float steals;
    private float turnovers;
}
