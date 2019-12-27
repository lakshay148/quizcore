package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.entity.PaymentOrder;
import com.quizcore.quizapp.model.network.request.payment.OrderRequest;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import com.quizcore.quizapp.model.network.response.ErrorResponse;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import com.quizcore.quizapp.model.network.response.payment.OrderResponse;
import com.quizcore.quizapp.model.other.Validity;
import com.quizcore.quizapp.service.PaymentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("${base.endpoint}/api/v1/payment")
public class PaymentController {

    @Autowired
    PaymentOrderService orderService;

    @Value("${pg.orderRedirectionUrl}")
    String paymentUrl;

    @GetMapping("/healthcheck")
    public SuccessResponse<Object> checkHealth() {
        SuccessResponse<Object> response = new SuccessResponse<>("It works awesome");
        return response;
    }

    @PostMapping("/order")
    public BaseResponse<OrderResponse> createOrder(@RequestHeader Map<String, String> headers, @RequestBody OrderRequest request){
        Validity headerValidity = request.validateHeaders(headers);
        if (!headerValidity.isValid()) {
            ErrorResponse<OrderResponse> response = new ErrorResponse<>(headerValidity.getMessage(), null);
            return response;
        }

        PaymentOrder paymentOrder = new PaymentOrder(UUID.fromString(headers.get("authorization")), request.getProductId(), request.getPartnerId(), request.getAmount());
        UUID createdOrderId = orderService.createOrder(paymentOrder);
        String url = paymentUrl+createdOrderId;
        SuccessResponse<OrderResponse> response = new SuccessResponse<>("order created successfully");
        response.data = new OrderResponse(url);
        return response;
    }

    @PostMapping("/order/{orderId}")
    public OrderResponse processOrder(@PathVariable("orderId") String orderId){

        return null;
    }
}
