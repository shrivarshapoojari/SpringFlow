package com.shri.service;


import com.shri.model.PlanType;
import com.shri.model.Subscription;
import com.shri.model.User;

public interface SubscriptionService {

    Subscription createSubscription(User user);

    Subscription getUsersSubscription(Long userId) throws  Exception;

    Subscription upgradeSubscription(Long userId, PlanType planType) throws Exception;

    boolean isValid(Subscription subsription);
}
