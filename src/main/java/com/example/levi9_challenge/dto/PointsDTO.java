package com.example.levi9_challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointsDTO {
    private double attempts;
    private double made;
    private double shootingPercentage;
}
