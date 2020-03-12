package com.challenge.service.implementations;

import com.challenge.entity.Challenge;
import com.challenge.repository.ChallengeRepository;
import com.challenge.service.interfaces.ChallengeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChallengeService implements ChallengeServiceInterface {
    @Autowired
    private ChallengeRepository challengeRepository;

    @Override
    public List<Challenge> findByAccelerationIdAndUserId(Long accelerationId, Long userId) {
        return this.challengeRepository.findByAccelerationIdAndUserId(accelerationId, userId);
    }

    @Override
    public Challenge save(Challenge object) {
        object.setCreatedAt(LocalDateTime.now());
        return this.challengeRepository.save(object);
    }
}
