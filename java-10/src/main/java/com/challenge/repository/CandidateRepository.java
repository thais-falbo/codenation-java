package com.challenge.repository;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, CandidateId> {
    @Query(value = "SELECT * FROM candidate\n" +
            "WHERE user_id = :userId\n" +
            "AND company_id = :companyId\n" +
            "AND acceleration_id = :accelerationId", nativeQuery = true)
    Optional<Candidate> findById(@Param("userId") Long userId,
                                 @Param("companyId") Long companyId,
                                 @Param("accelerationId") Long accelerationId);


    @Query(value = "SELECT * FROM candidate WHERE company_id = :companyId ", nativeQuery = true)
    List<Candidate> findByCompanyId(@Param("companyId") Long companyId);

    @Query(value = "SELECT * FROM candidate WHERE acceleration_id = :accelerationId ", nativeQuery = true)
    List<Candidate> findByAccelerationId(@Param("accelerationId") Long accelerationId);
}
