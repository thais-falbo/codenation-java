package com.challenge.entity.company;

import com.challenge.entity.candidate.Candidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Company {
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

    @OneToMany(mappedBy = "candidateId.company")
    private Set<Candidate> candidates;

    @CreatedDate
    private Date createdAt;
}
