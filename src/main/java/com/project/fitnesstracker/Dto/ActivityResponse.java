
package com.project.fitnesstracker.Dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.project.fitnesstracker.Models.ActivityType;


import lombok.Data;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityResponse {


    private String id;
    private ActivityType type;
    private Map<String,Object> additionalMetrics;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String userId;



    
}