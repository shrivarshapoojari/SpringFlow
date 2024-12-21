package com.shri.repository;

import com.shri.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findByChatIdOrderByCreatedAtAsc(Long chatId);
}
