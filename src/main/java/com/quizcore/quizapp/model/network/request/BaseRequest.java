package com.quizcore.quizapp.model.network.request;

import com.quizcore.quizapp.model.other.Validity;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseRequest<T> {

    public abstract Validity validateRequest(T request);
    public abstract Validity validateHeaders(Map<String, String> headers);

}
