package com.quizcore.quizapp.model.entity;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class PartnerJob {

    public PartnerJob(UUID partnerId, UUID jobId) {
        this.partnerId = partnerId;
        this.jobId = jobId;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type="uuid-char")
    public UUID id;

    @Type(type="uuid-char")
    public UUID partnerId;

    @Type(type="uuid-char")
    public  UUID jobId;

}
