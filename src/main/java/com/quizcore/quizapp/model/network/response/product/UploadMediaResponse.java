package com.quizcore.quizapp.model.network.response.product;

import java.util.UUID;

public class UploadMediaResponse {

    String id;
    String path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
