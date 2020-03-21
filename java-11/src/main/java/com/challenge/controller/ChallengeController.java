package com.challenge.controller;

import com.challenge.entity.Challenge;
import com.challenge.service.impl.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {
    @Autowired
    private ChallengeService challengeService;

    @GetMapping(params = {"accelerationId", "userId"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Challenge>> findByAccelerationIdAndUserId(@RequestParam Long accelerationId,
                                                                         @RequestParam Long userId) {
        return new ResponseEntity<>(
                this.challengeService.findByAccelerationIdAndUserId(accelerationId, userId), HttpStatus.OK);
    }
}
