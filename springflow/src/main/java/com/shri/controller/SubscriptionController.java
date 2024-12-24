package com.shri.controller;


import com.shri.model.PlanType;
import com.shri.model.Subscription;
import com.shri.model.User;
import com.shri.service.SubscriptionService;
import com.shri.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscribe")
public class SubscriptionController {

    @Autowired
    private UserService userService;

    @Autowired
    private SubscriptionService subscriptionService;


    @GetMapping("/user")
    public ResponseEntity<Subscription> getUserSubscription(
            @RequestHeader("Authorization") String jwt

    )throws  Exception
    {
        User user=userService.findUserProfileByJwt(jwt);

        Subscription subscription=subscriptionService.getUsersSubscription(user.getId());

        return new ResponseEntity<>(subscription, HttpStatus.OK);


    }

    @PatchMapping("/upgrade")
    public ResponseEntity<Subscription> updateSubscription(
            @RequestHeader("Authorization") String jwt,
            @RequestParam PlanType planType
            ) throws  Exception
    {
        User user=userService.findUserProfileByJwt(jwt);
        Subscription subscription=subscriptionService.upgradeSubscription(user.getId(), planType);
        return new ResponseEntity<>(subscription,HttpStatus.OK);
    }
}


