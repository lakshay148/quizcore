package com.quizcore.quizapp.model.entity;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class UserActivityLog {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type="uuid-char")
    public UUID id;

    @Column(name = "userId", updatable = false, nullable = false)
    @Type(type="uuid-char")
    public UUID userId;

    @Column(name = "quizId")
    @Type(type="uuid-char")
    public UUID quizId;

    @Column
    public String action;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserActivityLog that = (UserActivityLog) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(quizId, that.quizId) &&
                Objects.equals(action, that.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, quizId, action);
    }

    public UserActivityLog(UUID userId, String action) {
        this.userId = userId;
        this.action = action;
    }

    public UserActivityLog(UUID userId, UUID quizId, String action) {
        this.userId = userId;
        this.quizId = quizId;
        this.action = action;
    }

    @CreationTimestamp
    public LocalDateTime createdTime;

    public UUID getQuizId() {
        return quizId;
    }

    public void setQuizId(UUID quizId) {
        this.quizId = quizId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
