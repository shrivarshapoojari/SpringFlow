package com.shri.service;

import com.shri.model.Chat;
import com.shri.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
   @Autowired
    private ChatRepository chatRepository;
    @Override
    public Chat createChat(Chat chat) {

        return  chatRepository.save(chat);


    }
}
