package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.network.response.BaseResponse;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quizcore/api/v1/masterdata")
public class MasterDataController {

    @GetMapping("/healthcheck")
    public BaseResponse<Object> checkHealth() {
        SuccessResponse<Object> response = new SuccessResponse<>("It works awesone");
        return response;
    }
}