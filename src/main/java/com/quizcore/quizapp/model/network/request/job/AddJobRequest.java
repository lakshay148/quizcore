package com.quizcore.quizapp.model.network.request.job;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddJobRequest {
    String title;
    String description;
    double salaryPerMonth;
    String city;
    String startTime;
    String endTime;
    String partnerId;
}
