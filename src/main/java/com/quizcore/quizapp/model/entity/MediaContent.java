package com.quizcore.quizapp.model.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class MediaContent {

    public MediaContent() { }

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
    String mediaType;

    @Column
    String path;

    public MediaContent(String mediaType) {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MediaContent(UUID id, UUID userId, String quizId, String partnerId, String mediaType, String path) {
        this.id = id;
        this.userId = userId;
        this.mediaType = mediaType;
        this.path = path;
    }

    @Override
    public String toString() {
        return "MediaContent{" +
                "id=" + id +
                ", userId=" + userId +
                ", mediaType='" + mediaType + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}

