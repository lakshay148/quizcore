package com.quizcore.quizapp.model.entity;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class Job {

    public Job(String title, String description, String city, String startTime, String endTime, double salary) {
        this.title = title;
        this.description = description;
        this.city = city;
        this.startTime = startTime;
        this.endTime = endTime;
        this.salary = salary;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type="uuid-char")
    public UUID id;

    @Column
    String title;

    @Column
    String description;

    @Column
    String city;

    @Column
    String startTime;

    @Column
    String endTime;

    @Column
    double salary;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

}
