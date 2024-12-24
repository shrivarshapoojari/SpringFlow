package com.shri.controller;

import com.shri.model.PlanType;
import com.shri.model.User;
import com.shri.response.StripeResponse;
import com.shri.service.StripeService;
import com.shri.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.shri.model.PlanType.ANNUAL;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private UserService userService;

    @Autowired
    private StripeService stripeService;

    @PostMapping("/checkout/{planType}")
    public ResponseEntity<StripeResponse> checkout(
            @PathVariable PlanType planType,
            @RequestHeader("Authorization") String jwt
            ) throws  Exception
    {

        User user=userService.findUserProfileByJwt(jwt);
        Long userId=user.getId();
        Long amount =500L;
        if(planType.equals(ANNUAL))
        {
                  amount =amount*12;
        }


        StripeResponse response= stripeService.checkout(amount,planType,"INR",userId);

        return  new ResponseEntity<>(response, HttpStatus.OK);

    }

}
