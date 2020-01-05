package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.other.PaytmDetails;
import com.quizcore.quizapp.service.PaymentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

@Controller
@RequestMapping("${base.endpoint}/payment")
public class PaymentUiController {


    @Autowired
    PaytmDetails paytmDetails;

    @Autowired
    PaymentOrderService paymentOrderService;

    @GetMapping("/")
    public String home(){
        return "checkout";
    }

    @PostMapping(value = "/pgredirect")
    public ModelAndView getRedirect(@RequestParam(name = "CUST_ID") String customerId,
                                    @RequestParam(name = "TXN_AMOUNT") String transactionAmount,
                                    @RequestParam(name = "ORDER_ID") String orderId) throws Exception {

        ModelAndView modelAndView = new ModelAndView("redirect:" + paytmDetails.getPaytmUrl());
        TreeMap<String, String> parameters = new TreeMap<>();
        paytmDetails.getDetails().forEach((k, v) -> parameters.put(k, v));
        parameters.put("MOBILE_NO", "8586969893");
        parameters.put("EMAIL", "lakshay148@gmail.com");
        parameters.put("ORDER_ID", orderId);
        parameters.put("TXN_AMOUNT", transactionAmount);
        parameters.put("CUST_ID", customerId);
        String checkSum = getCheckSum(parameters);
        parameters.put("CHECKSUMHASH", checkSum);
        modelAndView.addAllObjects(parameters);
        return modelAndView;
    }

    @GetMapping("/redirect/{orderId}")
    public ModelAndView redirect(@PathVariable("orderId") String orderId){
        ModelAndView modelAndView = new ModelAndView("redirect:" + paytmDetails.getPaytmUrl());
        Map<String, String> parameters = paymentOrderService.preparePaymentRequest(UUID.fromString(orderId));
        modelAndView.addAllObjects(parameters);
        return modelAndView;
    }

    @PostMapping("/callback")
    public String callback(HttpServletRequest request, Model model){
        Map<String, String[]> mapData = request.getParameterMap();
        TreeMap<String, String> parameters = new TreeMap<String, String>();
        mapData.forEach((key, val) -> parameters.put(key, val[0]));
        String paytmChecksum = "";
        if (mapData.containsKey("CHECKSUMHASH")) {
            paytmChecksum = mapData.get("CHECKSUMHASH")[0];
        }
        String result;

        boolean isValideChecksum = false;
        System.out.println("RESULT : "+parameters.toString());
        try {
            isValideChecksum = validateCheckSum(parameters, paytmChecksum);
            if (isValideChecksum && parameters.containsKey("RESPCODE")) {
                if (parameters.get("RESPCODE").equals("01")) {
                    result = "Payment Successful";
                } else {
                    result = "Payment Failed";
                }
            } else {
                result = "Checksum mismatched";
            }
        } catch (Exception e) {
            result = e.toString();
        }
        model.addAttribute("result",result);
        parameters.remove("CHECKSUMHASH");
        model.addAttribute("parameters",parameters);
        return "paymentcallback";

    }

    private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
//        return CheckSumServiceHelper.getCheckSumServiceHelper().verifycheckSum(paytmDetails.getMerchantKey(),
//                parameters, paytmChecksum);
        return false;
    }

    private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
//        return CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(paytmDetails.getMerchantKey(), parameters);
        return null;
    }


    public void createOrder(){

    }
}
