package com.quizcore.quizapp.model.network.response;

public class ErrorResponse<T> extends BaseResponse<T> {

    public int status = 1;
    public String message;
    public T data;

    public ErrorResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
