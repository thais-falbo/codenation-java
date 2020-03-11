package com.challenge.entity.submission;

import com.challenge.entity.challenge.Challenge;
import com.challenge.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SubmissionId implements Serializable {
    @ManyToOne
    private User user;

    @ManyToOne
    private Challenge challenge;
}
