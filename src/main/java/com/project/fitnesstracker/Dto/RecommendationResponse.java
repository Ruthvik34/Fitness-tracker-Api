package com.project.fitnesstracker.Dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationResponse {


    private String id;

    private String type;

    private String recommendation;

    private List<String> improvements;

    private List<String> suggestions;

    private List<String> safety;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;
    
}
