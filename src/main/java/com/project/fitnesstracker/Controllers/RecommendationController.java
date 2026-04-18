package com.project.fitnesstracker.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fitnesstracker.Dto.RecommendationRequest;
import com.project.fitnesstracker.Dto.RecommendationResponse;
import com.project.fitnesstracker.Models.Recommendations;
import com.project.fitnesstracker.Service.RecommendationsService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationsService recommendationsService;


    @PostMapping("/generate")
    public ResponseEntity<RecommendationResponse> generateRecommendations(@RequestBody RecommendationRequest recommendation) {

        return new ResponseEntity<>(recommendationsService.generateRecommendations(recommendation), HttpStatus.OK);
           
    }

    @GetMapping("/user")
    public ResponseEntity<List<Recommendations>> getRecommendationsByUserId() {

        return new ResponseEntity<>(recommendationsService.getUserRecommendationsById(), HttpStatus.OK);
           
    }

    @GetMapping("/activity")
    public ResponseEntity<List<Recommendations>> getRecommendationsByActivityId() {

        return new ResponseEntity<>(recommendationsService.getUserRecommendationsByActivityId(), HttpStatus.OK);
           
    }
    

    
}
