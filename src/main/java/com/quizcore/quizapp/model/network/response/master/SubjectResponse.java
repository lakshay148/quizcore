package com.quizcore.quizapp.model.network.response.master;

import java.util.UUID;

public class SubjectResponse {



    private UUID productkey;
    private String subjectname;
    private UUID subjectId;

    public UUID getProductkey() { return productkey; }

    public void setProductkey(UUID productkey) {
        this.productkey = productkey;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public UUID getSubjectId() { return subjectId; }

    public void setSubjectId(UUID subjectId) { this.subjectId = subjectId; }

}
