package com.challenge.controller;

import com.challenge.entity.Company;
import com.challenge.exception.ResourceNotFoundException;
import com.challenge.service.impl.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Company> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.companyService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company")), HttpStatus.OK);
    }

    @GetMapping(params = "accelerationId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Company>> findByAccelerationId(@RequestParam Long accelerationId) {
        return new ResponseEntity<>(this.companyService.findByAccelerationId(accelerationId), HttpStatus.OK);
    }

    @GetMapping(params = "userId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Company>> findByUserId(@RequestParam Long userId) {
        return new ResponseEntity<>(this.companyService.findByUserId(userId), HttpStatus.OK);
    }
}
