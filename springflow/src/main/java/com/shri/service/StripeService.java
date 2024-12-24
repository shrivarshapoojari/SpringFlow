package com.shri.service;

import com.shri.response.StripeResponse;

public interface StripeService {
    public StripeResponse checkout(Long amount,String plan,String currency);


}
