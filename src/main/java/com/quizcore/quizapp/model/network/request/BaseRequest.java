package com.quizcore.quizapp.model.network.request;

import com.quizcore.quizapp.model.other.Validity;

public abstract class BaseRequest<T> {
    public abstract Validity validateRequest(T request);
}
