package com.campus.community.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Long userId;

    @Column(length = 50)
    private String username;

    @Column(columnDefinition = "TEXT")
    private String reply;

    @Column(length = 50)
    private String replyBy;

    private LocalDateTime replyTime;

    private Integer status; // 0-未回复 1-已回复

    private LocalDateTime createTime;

    @PrePersist
    public void prePersist() {
        if (createTime == null) createTime = LocalDateTime.now();
        if (status == null) status = 0;
    }
}
