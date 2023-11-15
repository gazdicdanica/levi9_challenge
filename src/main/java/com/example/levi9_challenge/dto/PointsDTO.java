package com.example.levi9_challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointsDTO {
    private float attempts;
    private float made;
    private float shootingPercentage;
}
