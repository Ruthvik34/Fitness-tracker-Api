package com.project.fitnesstracker.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.fitnesstracker.Models.Recommendations;
import com.project.fitnesstracker.Models.Users;

@Repository
public interface RecommendationsRepository extends JpaRepository<Recommendations, String> {

    List<Recommendations> findByUserId(String id);

List<Recommendations>findByActivityId(String activityId);
    
}
