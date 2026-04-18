package com.project.fitnesstracker.Configurations;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.fitnesstracker.Models.Users;
import com.project.fitnesstracker.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service

public class CustomUserDetailsService implements UserDetailsService {

    private  UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
public UserDetails loadUserByUsername(String username) {

    Users user = userRepository.findByFirstName(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    return User.builder()
            .username(user.getFirstName())   
            .roles("USER")
            .password(user.getPassword()).build();
}

}
