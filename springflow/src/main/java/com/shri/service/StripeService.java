package com.shri.service;

import com.shri.model.PlanType;
import com.shri.response.StripeResponse;

public interface StripeService {
    public StripeResponse checkout(Long amount, PlanType plan, String currency,Long userId);


}
