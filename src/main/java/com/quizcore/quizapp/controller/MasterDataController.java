package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.network.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quizcore/api/v1/masterdata")
public class MasterDataController {

    @GetMapping("/healthcheck")
    public BaseResponse<Object> checkHealth() {
        BaseResponse<Object> response = new BaseResponse<>();
        response.message = "It works awesone";
        response.status = 200;
        return response;
    }
}
