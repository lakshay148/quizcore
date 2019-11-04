package com.quizcore.quizapp.model.network.response.partner;

import com.quizcore.quizapp.model.entity.Partner;

import java.util.List;

public class GetProductPartnersResponse {

    List<Partner> partners;

    public List<Partner> getPartners() {
        return partners;
    }

    public void setPartners(List<Partner> partners) {
        this.partners = partners;
    }
}
