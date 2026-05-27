package com.campus.community.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Long clubId;

    @Column(length = 100)
    private String clubName;

    private Long publisherId;

    @Column(length = 50)
    private String publisherName;

    private Integer viewCount;

    private String coverImage;

    private LocalDateTime createTime;

    @PrePersist
    public void prePersist() {
        if (createTime == null) createTime = LocalDateTime.now();
        if (viewCount == null) viewCount = 0;
    }
}
