package com.challenge.service.implementations;

import com.challenge.entity.Acceleration;
import com.challenge.repository.AccelerationRepository;
import com.challenge.service.interfaces.AccelerationServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccelerationService implements AccelerationServiceInterface {
    @Autowired(required = false)
    private AccelerationRepository accelerationRepository;

    @Override
    public Optional<Acceleration> findById(Long id) {
        return this.accelerationRepository.findById(id);
    }

    @Override
    public List<Acceleration> findByCompanyId(Long companyId) {
        return this.accelerationRepository.findByCandidatesIdCompanyId(companyId);
    }

    @Override
    public Optional<Acceleration> findByAccelerationName(String name) {
        return this.accelerationRepository.findByName(name);
    }

    @Override
    public Acceleration save(Acceleration object) {
        object.setCreatedAt(LocalDateTime.now());
        return this.accelerationRepository.save(object);
    }
}
