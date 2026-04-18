package com.project.fitnesstracker.Dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.project.fitnesstracker.Models.ActivityType;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequest {

    private ActivityType type;
    private Map<String,Object> additionalMetrics;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private String userId;
    
}
