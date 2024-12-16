package com.shri.service;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;


public interface EmailService {

    void sendEmail(String email,String link) throws MessagingException;
}
