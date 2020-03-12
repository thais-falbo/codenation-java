package com.challenge.service.implementations;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import com.challenge.repository.CandidateRepository;
import com.challenge.service.interfaces.CandidateServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CandidateService implements CandidateServiceInterface {
    @Autowired(required = false)
    private CandidateRepository candidateRepository;

    @Override
    public Optional<Candidate> findById(CandidateId id) {
        return this.candidateRepository.findById(id);
    }

    @Override
    public Optional<Candidate> findById(Long userId, Long companyId, Long accelerationId) {
        return this.candidateRepository.findById(userId, companyId, accelerationId);
    }

    @Override
    public List<Candidate> findByCompanyId(Long companyId) {
        return this.candidateRepository.findByCompanyId(companyId);
    }

    @Override
    public List<Candidate> findByAccelerationId(Long accelerationId) {
        return this.candidateRepository.findByAccelerationId(accelerationId);
    }

    @Override
    public Candidate save(Candidate object) {
        object.setCreatedAt(LocalDateTime.now());
        return this.candidateRepository.save(object);
    }
}
