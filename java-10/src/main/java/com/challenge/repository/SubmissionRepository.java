package com.challenge.repository;

import com.challenge.entity.Submission;
import com.challenge.entity.SubmissionId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, SubmissionId> {
    @Query(value = "SELECT ISNULL(MAX(score), 0) FROM submission AS s\n" +
            "INNER JOIN challenge AS ch ON ch.id = s.challenge_id\n" +
            "WHERE ch.id = :challengeId", nativeQuery = true)
    BigDecimal findHigherScoreByChallengeId(@Param("challengeId") Long challengeId);

    @Query(value = "SELECT * FROM submission AS s\n" +
            "INNER JOIN challenge AS ch ON s.challenge_id = ch.id\n" +
            "INNER JOIN acceleration AS a ON a.challenge_id = ch.id\n" +
            "WHERE ch.id = :challengeId AND a.id = :accelerationId"
            , nativeQuery = true)
    List<Submission> findByChallengeIdAndAccelerationId(@Param("challengeId") Long challengeId,
                                                        @Param("accelerationId") Long accelerationId);
}
