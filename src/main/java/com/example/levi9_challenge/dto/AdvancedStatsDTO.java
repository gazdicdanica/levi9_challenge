package com.example.levi9_challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvancedStatsDTO {
    private float valorization;
    private float effectiveFieldGoalPercentage;
    private float trueShootingPercentage;
    private float hollingerAssistRatio;
}
