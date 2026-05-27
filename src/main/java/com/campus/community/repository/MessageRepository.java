package com.campus.community.repository;

import com.campus.community.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByOrderByCreateTimeDesc();
    List<Message> findByUserId(Long userId);
    List<Message> findByStatus(Integer status);
}
