package com.project.fitnesstracker.Dto;

import java.util.List;

import lombok.*;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationRequest {

    private String userId;

    private String activityId;

    private String type;

    private List<String> improvements;

    private List<String> suggestions;

    private List<String> safety;

    
}
