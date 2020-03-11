package com.challenge.entity.challenge;

import com.challenge.entity.acceleration.Acceleration;
import com.challenge.entity.submission.Submission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @Column
    @NotNull
    @Size(min = 3, max = 50)
    private String slug;

    @OneToMany(mappedBy = "challenges")
    private Set<Acceleration> accelerations;

    @OneToMany(mappedBy = "submissionId.challenge")
    private Set<Submission> submissions;

    @CreatedDate
    private LocalDateTime createdAt;
}
