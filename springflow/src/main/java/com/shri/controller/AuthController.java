package com.shri.controller;


import com.shri.config.JwtProvider;
import com.shri.model.User;
import com.shri.repository.UserRepository;
import com.shri.request.LoginRequest;
import com.shri.response.AuthResponse;
import com.shri.service.SubscriptionService;
import com.shri.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
   private    PasswordEncoder passwordEncoder;

    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private UserDetailsImpl userDetails;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createuserHandler (@RequestBody User user) throws Exception {
        User isEmailExist=userRepository.findByEmail(user.getEmail());
        if(isEmailExist!=null)
        {
            throw new Exception("Email already exists");
        }

        User newUser=new User();
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setFullName(user.getFullName());

        User savedUser=userRepository.save(newUser);
        Authentication authentication=new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt= JwtProvider.generateToken(authentication);
        AuthResponse res=new AuthResponse();
        res.setMessage("SignUp success");
        subscriptionService.createSubscription(savedUser);
        res.setJwt(jwt);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest)
    {
        String username=loginRequest.getEmail();
        String password=loginRequest.getPassword();
        Authentication authentication=authenticate(username,password);


        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt=JwtProvider.generateToken(authentication);
        AuthResponse res=new AuthResponse();
        res.setMessage("login success");
        res.setJwt(jwt);

       return  new ResponseEntity<>(res,HttpStatus.ACCEPTED);

    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetail=userDetails.loadUserByUsername(username);
        if(userDetail==null)
        {
            throw  new BadCredentialsException("No user found");
        }
        if(!passwordEncoder.matches(password,userDetail.getPassword()))
        {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetail,null,userDetail.getAuthorities());
    }


}
