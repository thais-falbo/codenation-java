package com.challenge.service.implementations;

import com.challenge.entity.Company;
import com.challenge.repository.CompanyRepository;
import com.challenge.service.interfaces.CompanyServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyService implements CompanyServiceInterface {
    @Autowired(required = false)
    private CompanyRepository companyRepository;

    @Override
    public Optional<Company> findById(Long id) {
        return this.companyRepository.findById(id);
    }

    @Override
    public List<Company> findByAccelerationId(Long accelerationId) {
        return this.companyRepository.findDistinctByCandidatesIdAccelerationId(accelerationId);
    }

    @Override
    public List<Company> findByUserId(Long userId) {
        return this.companyRepository.findByCandidatesIdUserId(userId);
    }

    @Override
    public Company save(Company object) {
        object.setCreatedAt(LocalDateTime.now());
        return this.companyRepository.save(object);
    }
}
