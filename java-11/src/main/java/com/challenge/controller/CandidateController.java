package com.challenge.controller;

import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.exception.ResourceNotFoundException;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidateMapper candidateMapper;

    @GetMapping(value = "/{userId}/{accelerationId}/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CandidateDTO> findById(@PathVariable("userId") Long userId,
                                                 @PathVariable("companyId") Long companyId,
                                                 @PathVariable("accelerationId") Long accelerationId) {
        return new ResponseEntity<>(this.candidateMapper.map(this.candidateService.findById(userId, companyId, accelerationId)
                        .orElseThrow(() -> new ResourceNotFoundException("Candidate"))), HttpStatus.OK);
    }

    @GetMapping(params = "companyId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CandidateDTO>> findByCompanyId(@RequestParam Long companyId) {
        return new ResponseEntity<>(this.candidateMapper.map(this.candidateService.findByCompanyId(companyId)), HttpStatus.OK);
    }

    @GetMapping(params = "accelerationId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CandidateDTO>> findByAccelerationId(@RequestParam Long accelerationId) {
        return new ResponseEntity<>(this.candidateMapper.map(this.candidateService.findByAccelerationId(accelerationId)), HttpStatus.OK);
    }
}
