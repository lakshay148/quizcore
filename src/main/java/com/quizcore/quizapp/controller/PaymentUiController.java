package com.quizcore.quizapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${base.endpoint}/payment")
public class PaymentUiController {

    @GetMapping("/pay")
    public String home(){
        return "checkout";
    }

    @GetMapping("/redirect/{orderId}")
    public String redirect(@PathVariable("orderId") String orderId){
        return "checkout";
    }

    @PostMapping("/callback")
    public String callback(){
        return "paymentcallback";
    }
}
