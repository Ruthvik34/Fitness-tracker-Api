package com.project.fitnesstracker.Repository;

import com.project.fitnesstracker.Models.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,String> {

Optional<Users> findByFirstName(String firstName);}
