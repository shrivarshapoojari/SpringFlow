package com.shri.controller;

import com.shri.model.User;
import com.shri.repository.UserRepository;
import com.shri.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

@GetMapping("/me")
    public ResponseEntity<User>  getUserProfile(
            @RequestHeader ("Authorization") String jwt
    ) throws  Exception
    {
        User user=userService.findUserProfileByJwt(jwt);
        return  new ResponseEntity<>(user, HttpStatus.OK
        );


    }

}
