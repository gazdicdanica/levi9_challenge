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

    private float freeThrowMade;
    private float freeThrowAttempted;
    private float twoPointsMade;
    private float twoPointsAttempted;
    private float threePointsMade;
    private float threePointsAttempted;
    private float rebounds;
    private float blocks;
    private float assists;
    private float steals;
    private float turnovers;

}
