package com.campus.community.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(length = 50)
    private String username;

    @Column(length = 50)
    private String realName;

    private Long clubId;

    @Column(length = 100)
    private String clubName;

    @Column(length = 30)
    private String position; // 社长、副社长、部长、成员

    private Integer status; // 0-待审核 1-正常 2-已退出

    private LocalDateTime joinTime;

    @PrePersist
    public void prePersist() {
        if (joinTime == null) joinTime = LocalDateTime.now();
        if (status == null) status = 0;
        if (position == null) position = "成员";
    }
}
