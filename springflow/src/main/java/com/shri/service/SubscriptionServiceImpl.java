package com.shri.service;

import com.shri.model.PlanType;
import com.shri.model.Subscription;
import com.shri.model.User;
import com.shri.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SubscriptionServiceImpl implements  SubscriptionService {

    @Autowired
    private UserService userService;

    @Autowired
    private SubscriptionRepository subscriptionRepository;


    @Override
    public Subscription createSubscription(User user) {

         Subscription subscription=new Subscription();
         subscription.setUser(user);
        subscription.setSubscriptionStartDate(LocalDate.now());
         subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
         subscription.setValid(true);
         subscription.setPlantype(PlanType.FREE);

         return  subscriptionRepository.save(subscription);


    }

    @Override
    public Subscription getUsersSubscription(Long userId) throws Exception {

        Subscription subscription= subscriptionRepository.findByUserId(userId);
        if(!isValid(subscription))
        {
            subscription.setPlantype(PlanType.FREE);
            subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
            subscription.setSubscriptionStartDate(LocalDate.now());
        }
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription upgradeSubscription(Long userId, PlanType planType) throws Exception {

        Subscription subscription=subscriptionRepository.findByUserId(userId);
        subscription.setPlantype(planType);
        subscription.setSubscriptionStartDate(LocalDate.now());
        if(planType.equals(PlanType.ANNUAL))
            subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
        else
            subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(1));
        return  subscriptionRepository.save(subscription);
    }

    @Override
    public boolean isValid(Subscription subsription) {

        if(subsription.getPlantype().equals(PlanType.FREE))
            return  true;

        LocalDate endDate=subsription.getSubscriptionEndDate();
        LocalDate currDate=LocalDate.now();

        return endDate.isAfter(currDate) || endDate.equals(currDate);
    }
}
