package com.challenge.controller;

import com.challenge.entity.Acceleration;
import com.challenge.exception.ResourceNotFoundException;
import com.challenge.service.impl.AccelerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acceleration")
public class AccelerationController {
    @Autowired
    private AccelerationService accelerationService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Acceleration> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.accelerationService.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Acceleration")), HttpStatus.OK);
    }

    @GetMapping(params = "companyId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Acceleration>> findByCompanyId(@RequestParam Long companyId) {
        return new ResponseEntity<>(this.accelerationService.findByCompanyId(companyId), HttpStatus.OK);
    }
}
