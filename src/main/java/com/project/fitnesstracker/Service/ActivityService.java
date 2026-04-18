package com.project.fitnesstracker.Service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.fitnesstracker.Dto.ActivityRequest;
import com.project.fitnesstracker.Dto.ActivityResponse;
import com.project.fitnesstracker.Exceptions.CustomExceptions.CustomException;
import com.project.fitnesstracker.Models.Activity;
import com.project.fitnesstracker.Models.Users;
import com.project.fitnesstracker.Repository.ActivityRepository;
import com.project.fitnesstracker.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityService {
    
    @Autowired
    private final ActivityRepository activityRepository;

    @Autowired
    private final UserRepository userRepository;

    public ActivityResponse trackActivity(ActivityRequest activityRequest) {
        
Users user = userRepository.findById(activityRequest.getUserId())
        .orElseThrow(() -> new RuntimeException("User not found"));

        Activity userActivity = Activity.builder()
                .type(activityRequest.getType())
                .duration(activityRequest.getDuration())
                .caloriesBurned(activityRequest.getCaloriesBurned())
                .startTime(activityRequest.getStartTime())
                .additionalMetrics(activityRequest.getAdditionalMetrics())
                .user(user)
                .build();

      Activity savedActivity = activityRepository.save(userActivity);

        return ActivityResponse.builder()
                .id(savedActivity.getId())
                .type(savedActivity.getType())
                .duration(savedActivity.getDuration())
                .caloriesBurned(savedActivity.getCaloriesBurned())
                .startTime(savedActivity.getStartTime())
                .additionalMetrics(savedActivity.getAdditionalMetrics())
                .userId(savedActivity.getUser().getId())
                .createdAt(savedActivity.getCreatedAt())
                .updatedAt(savedActivity.getUpdatedAt())
                .build();
    
    }

    

 public List<ActivityResponse> getAllActivities() {

    List<Activity> activities = activityRepository.findAll();

    return  activities.stream().map(activity -> ActivityResponse.builder()
            .id(activity.getId())
            .type(activity.getType())
            .duration(activity.getDuration())
            .caloriesBurned(activity.getCaloriesBurned())
            .startTime(activity.getStartTime())
            .additionalMetrics(activity.getAdditionalMetrics())
            .userId(activity.getUser().getId())
            .createdAt(activity.getCreatedAt())
            .updatedAt(activity.getUpdatedAt())
            .build()).collect(Collectors.toList());

}

 public ActivityResponse getUserActivities() {

     Authentication authentication = SecurityContextHolder
            .getContext()
            .getAuthentication();

    String username = authentication.getName();

    Users user = userRepository.findByFirstName(username)
            .orElseThrow(() -> new CustomException.UserNotFoundException("User not found"));

              Activity activity = activityRepository.findByUserId(user.getId())
            .orElseThrow(() -> new RuntimeException("Activity not found"));

    return   ActivityResponse.builder()
            .id(activity.getId())
            .type(activity.getType())
            .duration(activity.getDuration())
            .caloriesBurned(activity.getCaloriesBurned())
            .startTime(activity.getStartTime())
            .additionalMetrics(activity.getAdditionalMetrics())
            .userId(activity.getUser().getId())
            .createdAt(activity.getCreatedAt())
            .updatedAt(activity.getUpdatedAt())
            .build();

}


}