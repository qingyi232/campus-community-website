package com.campus.community.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(length = 200)
    private String location;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Long clubId;

    @Column(length = 100)
    private String clubName;

    private Long publisherId;

    @Column(length = 50)
    private String publisherName;

    private Integer maxParticipants;

    private Integer currentParticipants;

    private Integer status; // 0-报名中 1-进行中 2-已结束

    private String coverImage;

    private LocalDateTime createTime;

    @PrePersist
    public void prePersist() {
        if (createTime == null) createTime = LocalDateTime.now();
        if (status == null) status = 0;
        if (currentParticipants == null) currentParticipants = 0;
    }
}
