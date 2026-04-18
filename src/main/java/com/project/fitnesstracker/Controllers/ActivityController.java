package com.project.fitnesstracker.Controllers;

import com.project.fitnesstracker.Dto.ActivityRequest;
import com.project.fitnesstracker.Dto.ActivityResponse;
import com.project.fitnesstracker.Service.ActivityService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/activities")

public class ActivityController {

    private  ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/track")
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest activityRequest) {
       
        return ResponseEntity.ok(activityService.trackActivity(activityRequest));
    }

     @GetMapping("/allActivities")
    public ResponseEntity<List<ActivityResponse>> getAllActivities() {

        return ResponseEntity.ok(activityService.getAllActivities());
 
    }

     @GetMapping("/user")
    public ResponseEntity<ActivityResponse> getUserActivities() {

        return ResponseEntity.ok(activityService.getUserActivities());
 
    }
    
    
}
