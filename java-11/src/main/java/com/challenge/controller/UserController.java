package com.challenge.controller;

import com.challenge.entity.User;
import com.challenge.exception.ResourceNotFoundException;
import com.challenge.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> findById(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(this.userService.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User")), HttpStatus.OK);
    }

    @RequestMapping(params = "accelerationName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> findByAccelerationName(@RequestParam String accelerationName) {
        return new ResponseEntity<>(this.userService.findByAccelerationName(accelerationName), HttpStatus.OK);
    }

    @RequestMapping(params = "companyId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> findByCompanyId(@RequestParam Long companyId) {
        return new ResponseEntity<>(this.userService.findByCompanyId(companyId), HttpStatus.OK);
    }
}
