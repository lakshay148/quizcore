package com.quizcore.quizapp.model.network.response;

public class SuccessResponse<T> extends BaseResponse<T> {

    int status = 0;
    String message;
    public T data;

    public SuccessResponse(String message) {
        this.message = message;
    }
}
