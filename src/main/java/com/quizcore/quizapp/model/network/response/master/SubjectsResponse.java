package com.quizcore.quizapp.model.network.response.master;

import com.quizcore.quizapp.model.entity.Subject;

import java.util.List;

public class SubjectsResponse {

    List<Subject> subjects;

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
