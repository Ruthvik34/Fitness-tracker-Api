package com.project.fitnesstracker.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.fitnesstracker.Dto.ActivityResponse;
import com.project.fitnesstracker.Models.Activity;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {

    Optional<Activity> findByUserId(String userId);
}
    

    

