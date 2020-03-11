package com.challenge.entity.user;

import com.challenge.entity.candidate.Candidate;
import com.challenge.entity.submission.Submission;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Size(min = 3, max = 100)
    private String fullName;

    @Column
    @NotNull
    @Size(min = 3, max = 100)
    @Email
    private String email;

    @Column
    @NotNull
    @Size(min = 3, max = 50)
    private String nickname;

    @Column
    @NotNull
    @Size(min = 3, max = 255)
    private String password;

    @CreatedDate
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "candidateId.user")
    private Set<Candidate> candidates;

    @OneToMany(mappedBy = "submissionId.user")
    private Set<Submission> submissions;
}
