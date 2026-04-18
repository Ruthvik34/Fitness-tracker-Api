package com.project.fitnesstracker.Service;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.fitnesstracker.Dto.RecommendationRequest;
import com.project.fitnesstracker.Dto.RecommendationResponse;
import com.project.fitnesstracker.Exceptions.CustomExceptions.CustomException;
import com.project.fitnesstracker.Models.Activity;
import com.project.fitnesstracker.Models.Recommendations;
import com.project.fitnesstracker.Models.Users;
import com.project.fitnesstracker.Repository.ActivityRepository;
import com.project.fitnesstracker.Repository.RecommendationsRepository;
import com.project.fitnesstracker.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendationsService {

    private final ModelMapper modelMapper;
    private final RecommendationsRepository recommendationsRepository;
    private final UserRepository usersRepository; 
       private final ActivityRepository activityRepository; 

    public RecommendationResponse generateRecommendations(RecommendationRequest recommendationRequest) {

        Users user = usersRepository.findById(recommendationRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Activity activity = activityRepository.findById(recommendationRequest.getActivityId()).orElseThrow(() -> new RuntimeException("Activity not found"));

        Recommendations recommendation = modelMapper.map(recommendationRequest, Recommendations.class);

        recommendation.setUser(user);
        recommendation.setActivity(activity);
        Recommendations savedRecommendation = recommendationsRepository.save(recommendation);
     
         return modelMapper.map(savedRecommendation, RecommendationResponse.class);
    }




    public List<Recommendations> getUserRecommendationsById() {
      
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users username = (Users) authentication.getPrincipal();
        Users user = usersRepository.findByFirstName(username.getFirstName()).orElseThrow(() -> new CustomException.UserNotFoundException("User not found"));
 
      return  recommendationsRepository.findByUserId(user.getId());
    
}

  public List<Recommendations> getUserRecommendationsByActivityId() {

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users user = usersRepository.findByFirstName(username).orElseThrow(() -> new CustomException.UserNotFoundException("User not found"));
 
        Activity activity = activityRepository.findByUserId(user.getId()).orElseThrow(() -> new CustomException.ActivityNotFoundException("Activity not found"));
      
       return recommendationsRepository.findByActivityId(activity.getId());

    
}

}
