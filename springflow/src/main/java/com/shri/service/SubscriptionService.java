package com.shri.service;


import com.shri.model.PlanType;
import com.shri.model.Subsription;
import com.shri.model.User;

public interface SubscriptionService {

    Subsription createSubscription(User user);

    Subsription getUsersSubscription(Long userId) throws  Exception;

    Subsription upgradeSubscription(Long userId, PlanType planType);

    boolean isValid(Subsription subsription);
}
