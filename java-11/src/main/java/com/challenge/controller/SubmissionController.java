package com.challenge.controller;

import com.challenge.dto.SubmissionDTO;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.impl.SubmissionService;
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
@RequestMapping("/submission")
public class SubmissionController {
    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private SubmissionMapper submissionMapper;

    @GetMapping(params = {"challengeId", "accelerationId"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubmissionDTO>> findByChallengeIdAndAccelerationId(@RequestParam Long challengeId,
                                                                                  @RequestParam Long accelerationId) {
        return new ResponseEntity<>(this.submissionMapper.map(
                this.submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId)
        ), HttpStatus.OK);
    }
}
