package com.shri.service;

import com.shri.model.Chat;
import com.shri.model.Message;
import com.shri.model.User;
import com.shri.repository.MessageRepository;
import com.shri.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl  implements  MessageService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectService projectService;

    @Autowired
    private MessageRepository messageRepository;
    @Override
    public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {



        Optional<User> sender=userRepository.findById(senderId);


        if(sender.isEmpty())
            throw new Exception("user not found in sending msg");
        Chat chat=projectService.getProjectById(projectId).getChat();

        Message message=new Message();
        message.setContent(content);
        message.setSender(sender.get());
        message.setCreatedAt(LocalDateTime.now());
        message.setChat(chat);

        Message savedmessage=messageRepository.save(message);
        chat.getMessage().add(savedmessage);
        return savedmessage;

    }



    public List<Message> getMessagesByProjectId(Long projectId) throws Exception
    {
        Chat chat=projectService.getChatByProjectId(projectId);
        List<Message> findByChatIdOrderByCreatedAtAsc=messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
        return  findByChatIdOrderByCreatedAtAsc;
    }
}
