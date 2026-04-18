package com.project.fitnesstracker.Service;

import com.project.fitnesstracker.Dto.RegisterRequest;
import com.project.fitnesstracker.Dto.UserResponseDTO;
import com.project.fitnesstracker.Models.Users;
import com.project.fitnesstracker.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    

    public UserResponseDTO register(RegisterRequest registerRequest) {
       Users dummyUser = Users.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password( passwordEncoder.encode(registerRequest.getPassword()))
                .build();

userRepository.save(dummyUser);

 UserResponseDTO userResponseDTO =UserResponseDTO.builder()
        .id(dummyUser.getId())
        .firstName(dummyUser.getFirstName())
        .lastName(dummyUser.getLastName())
        .email(dummyUser.getEmail())
        .createdAt(dummyUser.getCreatedAt())
        .updatedAt(dummyUser.getUpdatedAt()).build();


        return userResponseDTO;
    }

    public List<UserResponseDTO> getAllUsers() {
        List<Users> users = userRepository.findAll();
        return users.stream().map(user -> UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build()).collect(Collectors.toList());
    }

    public UserResponseDTO updateUser(String id, RegisterRequest registerRequest){
        Users user=userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        Users userUpdated=userRepository.save(user);

        return UserResponseDTO.builder()
        .firstName(userUpdated.getFirstName())
        .lastName(userUpdated.getLastName())
        .email(userUpdated.getEmail())
        .build();

    }
}
