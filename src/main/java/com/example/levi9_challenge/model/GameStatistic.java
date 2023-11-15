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
