package com.quizcore.quizapp.model.network.response;

public class ErrorResponse<T> extends BaseResponse<T> {
    int status = 1;
    String message;
    T data;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
