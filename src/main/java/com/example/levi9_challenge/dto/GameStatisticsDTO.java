package com.example.levi9_challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameStatisticsDTO {
    private double freeThrowMade;
    private double freeThrowAttempted;
    private double twoPointsMade;
    private double twoPointsAttempted;
    private double threePointsMade;
    private double threePointsAttempted;
    private double rebounds;
    private double blocks;
    private double assists;
    private double steals;
    private double turnovers;
}
