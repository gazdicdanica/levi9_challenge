package com.example.levi9_challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvancedStatsDTO {
    private double valorization;
    private double effectiveFieldGoalPercentage;
    private double trueShootingPercentage;
    private double hollingerAssistRatio;
}
