package com.example.levi9_challenge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game_statistics")
public class GameStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;
    private int gamesPlayed;

    private double freeThrowMade;
    private double freeThrowAttempted;
    private double twoPointsMade;
    private double twoPointsAttempted;
    private double threePointsMade;
    private double threePointsAttempted;
    private double points;
    private double rebounds;
    private double blocks;
    private double assists;
    private double steals;
    private double turnovers;
    private double valorization;
    private double effectiveFieldGoalPercentage;
    private double trueShootingPercentage;
    private double hollingerAssistRatio;

    public void calculateAdvanced(){
        calculatePoints();
        calculateValorization();
        calculateEffectiveFieldGoalPercentage();
        calculateHollingerAssistRatio();
        calculateTrueShootingPercentage();
    }
    private void calculatePoints(){
        this.points = this.freeThrowMade + this.twoPointsMade * 2 + this.threePointsMade * 3;
    }

    private void calculateValorization(){
        //(FTM + 2x2PM + 3x3PM + REB + BLK + AST + STL) - (FTA-FTM + 2PA-2PM + 3PA-3PM + TOV)
        this.valorization = (freeThrowMade + 2 * twoPointsMade + 3 * threePointsMade + rebounds + blocks + assists + steals)
                - (freeThrowAttempted - freeThrowMade + twoPointsAttempted - twoPointsMade + threePointsAttempted - threePointsMade + turnovers);
    }

    private void calculateEffectiveFieldGoalPercentage(){
        //(2PM + 3PM + 0,5 * 3PM) / (2PA + 3PA) * 100
        this.effectiveFieldGoalPercentage = (twoPointsMade + threePointsMade + 0.5 * threePointsMade)
                / (twoPointsAttempted + threePointsAttempted) * 100;
    }

    private void calculateTrueShootingPercentage(){
        //PTS / (2 * (2PA + 3PA +0,475 * FTA)) * 100
        this.trueShootingPercentage = points / (2 * (twoPointsAttempted + threePointsAttempted + 0.475 * freeThrowAttempted))* 100;
    }

    private void calculateHollingerAssistRatio(){
        //AST / (2PA + 3PA + 0,475 * FTA + AST + TOV) * 100
        this.hollingerAssistRatio = assists / (twoPointsAttempted + threePointsAttempted + 0.475 * freeThrowAttempted + assists + turnovers) * 100;
    }

}
