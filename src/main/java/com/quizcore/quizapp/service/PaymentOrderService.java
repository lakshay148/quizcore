package com.quizcore.quizapp.service;

import com.paytm.pg.merchant.CheckSumServiceHelper;
import com.quizcore.quizapp.model.entity.PaymentOrder;
import com.quizcore.quizapp.model.other.PaytmDetails;
import com.quizcore.quizapp.model.repository.PaymentOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;

@Service
public class PaymentOrderService {

    @Autowired
    PaytmDetails paytmDetails;

    @Autowired
    PaymentOrderRepository orderRepository;

    public UUID createOrder(PaymentOrder order){
        PaymentOrder savedOrder = orderRepository.save(order);
        return savedOrder.id;
    }

    public Map<String, String> preparePaymentRequest(UUID orderId){
        TreeMap<String, String> parameters = new TreeMap<>();
//        paytmDetails.getDetails().forEach((k, v) -> parameters.put(k, v));
        Optional<PaymentOrder> paymentOrderOptional = orderRepository.findById(orderId);
        if(paymentOrderOptional.isPresent()){
            PaymentOrder paymentOrder = paymentOrderOptional.get();

            parameters.put("MID", "vZrTzc24907027428309");
            parameters.put("WEBSITE", "WEBSTAGING");
            parameters.put("INDUSTRY_TYPE_ID", "Retail");
            parameters.put("CHANNEL_ID", "WEB");
            parameters.put("ORDER_ID", orderId.toString());
            parameters.put("CUST_ID", paymentOrder.getUserId().toString());
            parameters.put("MOBILE_NO", "9111111111");
            parameters.put("EMAIL", "testme@gmail.com");
            parameters.put("TXN_AMOUNT", "20.00");
            parameters.put("CALLBACK_URL", "http://localhost:8081/quizcore/payment/callback");
//            parameters.put("PAYMENT_MODE_ONLY","YES");
//            parameters.put("AUTH_MODE","USRPWD");
//            parameters.put("PAYMENT_TYPE_ID","PPI");
            /* for Staging */
            String url = "https://securegw-stage.paytm.in/order/process";

//            parameters.put("MOBILE_NO", "8586969893");
//            parameters.put("EMAIL", "lakshay148@gmail.com");
//            parameters.put("ORDER_ID", orderId.toString());
//            parameters.put("TXN_AMOUNT", paymentOrder.getAmount()+"");
//            parameters.put("CUST_ID", paymentOrder.getUserId().toString());
            try{
                String checkSum = getCheckSum(parameters);
                parameters.put("CHECKSUMHASH", checkSum);
            } catch (Exception e){
                //TODO throw exception here
                return null;
            }
            return parameters;
        } else {
            //TODO Throw order not found exception
            return null;
        }
    }

    private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
        return CheckSumServiceHelper.getCheckSumServiceHelper().verifycheckSum(paytmDetails.getMerchantKey(),
                parameters, paytmChecksum);
    }


    private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
        return CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(paytmDetails.getMerchantKey(), parameters);
    }
}
