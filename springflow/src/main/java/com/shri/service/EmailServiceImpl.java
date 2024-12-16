package com.shri.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements  EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendEmail(String email, String link) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String subject = "SpringFlow- Project Invitation";
        String text = "Hello, You are invited to join the project on SpringFlow. Press the link to accept the invite" + link;

        helper.setSubject(subject);
        helper.setText(text);
        helper.setTo(email);


        try {
            javaMailSender.send(mimeMessage);
        }
        catch (MailSendException e) {
            throw  new MailSendException("Failed to send mail");


        }
    }
    }
}
