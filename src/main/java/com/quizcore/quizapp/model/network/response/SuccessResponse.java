package com.quizcore.quizapp.model.network.response;

public class SuccessResponse<T> extends BaseResponse<T> {

    public int status = 0;
    public String message;
    public T data;

    public SuccessResponse(String message) {
        this.message = message;
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