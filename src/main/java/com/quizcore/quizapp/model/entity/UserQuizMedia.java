package com.quizcore.quizapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuizMedia {

    public UserQuizMedia(UUID userId, UUID quizId, UUID mediaId, String mediaType) {
        this.userId = userId;
        this.quizId = quizId;
        this.mediaId = mediaId;
        this.mediaType = mediaType;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type="uuid-char")
    public UUID id;

    @Column
    @Type(type="uuid-char")
    UUID userId;

    @Column
    @Type(type="uuid-char")
    UUID quizId;

    @Column
    @Type(type="uuid-char")
    UUID mediaId;

    @Column
    String mediaType;
}
