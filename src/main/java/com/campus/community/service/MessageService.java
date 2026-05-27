package com.campus.community.service;

import com.campus.community.entity.Message;
import com.campus.community.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> findAll() {
        return messageRepository.findAllByOrderByCreateTimeDesc();
    }

    public List<Message> findByUserId(Long userId) {
        return messageRepository.findByUserId(userId);
    }

    public List<Message> findByStatus(Integer status) {
        return messageRepository.findByStatus(status);
    }

    public Message findById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    public void save(Message message) {
        messageRepository.save(message);
    }

    public void delete(Long id) {
        messageRepository.deleteById(id);
    }
}
