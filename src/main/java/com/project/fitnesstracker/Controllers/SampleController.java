package com.project.fitnesstracker.Controllers;

import com.project.fitnesstracker.Models.DummyUsers;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fitnesstracker.Dto.LoginRequest;
import com.project.fitnesstracker.Security.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@RestController
public class SampleController {

    @Autowired
    AuthenticationManager authenticationManager;

    private final RestTemplate restTemplate;

    @Autowired
    JwtUtils jwtUtil;
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

     @PreAuthorize("hasRole('ADMIN')")
      @GetMapping("/admin/hello")
    public String sayAdminHello() {
        return "Hello Admin";
    }

     @PreAuthorize("hasAnyRole('USER','ADMIN')")
      @GetMapping("/user/hello")
    public String sayUserHello(){
        return "Hello User";
    }

        @PostMapping("/signIn")
        public String Login(@RequestBody LoginRequest loginRequest) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
           return  jwtUtil.generateToken(userDetails);
            
        }



 @GetMapping("/api/auth/getuser")
 public ResponseEntity<DummyUsers> callGetApi() {
 String url = "https://api.example.com/users";

 ResponseEntity<DummyUsers> response =
 restTemplate.getForEntity(url, DummyUsers.class);

 return response;
 }
    
}
