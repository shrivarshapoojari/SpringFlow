package com.shri.controller;

import com.shri.model.Chat;
import com.shri.model.Message;
import com.shri.model.User;
import com.shri.request.CreateMessageRequest;
import com.shri.service.MessageService;
import com.shri.service.ProjectService;
import com.shri.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController
{


    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;



    @PostMapping("/send")
    public ResponseEntity<Message> send(
            @RequestBody CreateMessageRequest req

    )  throws  Exception
    {
        User user=userService.findUserById(req.getSenderId());
        if(user==null)
            throw  new Exception("user not found with id" + req.getSenderId());
        Chat chats=projectService.getProjectById(req.getProjectId()).getChat();

        if(chats==null)
            throw new Exception("No chats found");

        Message sentMessage=messageService.sendMessage(req.getSenderId(),req.getProjectId(),req.getContent());
        return   new ResponseEntity<>(sentMessage, HttpStatus.OK);
    }

    @GetMapping("/chat/{projectId}")
    public  ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long projectId) throws  Exception
    {
         List<Message> messages=messageService.getMessagesByProjectId(projectId);
         return new ResponseEntity<>(messages,HttpStatus.OK);
    }
}

