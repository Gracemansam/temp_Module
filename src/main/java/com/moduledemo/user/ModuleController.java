package com.moduledemo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ModuleController {

    @Autowired
    private RestTemplate restTemplate;

    private final String coreBaseUrl = "http://localhost:8080"; // Replace with actual core application URL

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        ResponseEntity<List<User>> response = restTemplate.exchange(coreBaseUrl + "/users", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        return response;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        ResponseEntity<User> response = restTemplate.getForEntity(coreBaseUrl + "/user/{id}", User.class, id);
        return response;
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        restTemplate.put(coreBaseUrl + "/user/{id}", user, id);
        return ResponseEntity.ok(user);
    }

    // Other methods for user management
}
