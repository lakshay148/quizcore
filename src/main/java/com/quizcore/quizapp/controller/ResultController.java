package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.network.request.ComputeResultRequest;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import com.quizcore.quizapp.model.network.response.result.ComputeResultResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("quizcore/api/v1/result")
public class ResultController {

    @GetMapping("/healthcheck")
    public BaseResponse<Object> checkHealth() {
        BaseResponse<Object> response = new BaseResponse<>();
        response.message = "It works awesone";
        response.status = 200;
        return response;
    }

    @PostMapping("/add")
    public BaseResponse<ComputeResultResponse> addQuestion(@RequestBody ComputeResultRequest request){

        BaseResponse<ComputeResultResponse> response = new BaseResponse<>();
        response.message = "result calculated";
        response.status = 200;
        ComputeResultResponse result = new ComputeResultResponse();
        response.data = result;
        return response;
    }
}
