package com.campus.community.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(length = 50)
    private String publisher;

    private Integer top; // 0-普通 1-置顶

    private Integer viewCount;

    private LocalDateTime createTime;

    @PrePersist
    public void prePersist() {
        if (createTime == null) createTime = LocalDateTime.now();
        if (top == null) top = 0;
        if (viewCount == null) viewCount = 0;
    }
}
