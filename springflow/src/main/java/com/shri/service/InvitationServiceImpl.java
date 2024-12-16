package com.shri.service;

import com.shri.model.Invitation;
import com.shri.repository.InvitationRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvitationServiceImpl  implements  InvitationService{

    @Autowired
    private InvitationRepository invitationRepository;
    @Autowired
    private EmailService emailService;
    @Override
    public void sendInvitation(String email, Long projectId) throws MessagingException {


        String inviteToken= UUID.randomUUID().toString();
        Invitation invitation=new Invitation();
        invitation.setEmail(email);
        invitation.setProjectId(projectId);
        invitation.setToken(inviteToken);
        invitationRepository.save(invitation);
        String inviteLink="http://localhost:5173/accept_invite?token="+inviteToken;
        emailService.sendEmail(email,inviteLink);
    }

    @Override
    public Invitation acceptInvitation(String token, Long userId) throws Exception {
        Invitation invitation =invitationRepository.findByToken(token);

        if(invitation==null)
        {
            throw  new Exception("Invitation Expired");
        }
        return invitation;
    }

    @Override
    public String getTokenByUserMail(String userEmail) {

        Invitation invitation=invitationRepository.findByEmail(userEmail);
        return invitation.getToken();

    }

    @Override
    public void deleteToken(String token) {

        Invitation invitation=invitationRepository.findByToken(token);
              invitationRepository.delete(invitation);
    }
}
