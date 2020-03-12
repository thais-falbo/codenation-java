package com.challenge.repository;

import com.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    @Query(value = "SELECT DISTINCT * FROM challenge AS ch\n" +
            "INNER JOIN acceleration AS a ON a.challenge_id = ch.id\n" +
            "INNER JOIN candidate AS cd ON cd.acceleration_id = a.id\n" +
            "INNER JOIN users AS u ON u.id = cd.user_id\n" +
            "WHERE a.id = :accelerationId AND u.id = :userId", nativeQuery = true)
    List<Challenge> findByAccelerationIdAndUserId(@Param("accelerationId") Long accelerationId,
                                                  @Param("userId") Long userId);
}
