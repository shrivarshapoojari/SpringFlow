package com.shri.service;

import com.shri.model.Message;

import java.util.List;

public interface MessageService {

    Message sendMessage(Long senderId,Long projectId,String content);
    List<Message> getMessagesByProjectId(Long projectId) throws  Exception;
}
