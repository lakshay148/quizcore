package com.quizcore.quizapp.model.network.request.payment;

import com.quizcore.quizapp.model.network.request.BaseRequest;
import com.quizcore.quizapp.model.other.Validity;
import lombok.Data;
import lombok.ToString;

import java.util.Map;
import java.util.UUID;

@Data
@ToString
public class OrderRequest extends BaseRequest<OrderRequest> {

    UUID customerId;
    double amount;
    UUID productId;
    UUID partnerId;

    @Override
    public Validity validateRequest(OrderRequest request) {
        return null;
    }

    @Override
    public Validity validateHeaders(Map<String, String> headers) {
        String authorization = headers.get("authorization");
        if(authorization == null || authorization.length() == 0){
            return new Validity(false, "unauthorized user");
        }
        return new Validity(true, null);
    }
}
