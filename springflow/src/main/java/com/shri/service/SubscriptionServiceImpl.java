package com.shri.service;

import com.shri.model.PlanType;
import com.shri.model.Subsription;
import com.shri.model.User;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements  SubscriptionService {
    @Override
    public Subsription createSubscription(User user) {
        return null;
    }

    @Override
    public Subsription getUsersSubscription(Long userId) throws Exception {
        return null;
    }

    @Override
    public Subsription upgradeSubscription(Long userId, PlanType planType) {
        return null;
    }

    @Override
    public boolean isValid(Subsription subsription) {
        return false;
    }
}
