package com.quizcore.quizapp.model.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Subject {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type="uuid-char")
    public UUID id;

    @Column
    @Type(type="uuid-char")
    UUID subjectId;

    @Column
    @Type(type="uuid-char")
    UUID productId;

    @Column
    String subjectName;

    public Subject(UUID productId, String subjectName) {
        this.productId = productId;
        this.subjectName = subjectName;
    }

    public Subject() { super(); }


    public Subject(String subjectId) {
    }



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(String partnerId) {
        this.productId = productId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public UUID getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(UUID subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
}
