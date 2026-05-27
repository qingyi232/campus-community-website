package com.campus.community.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_club")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 50)
    private String category;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 50)
    private String president;

    @Column(length = 20)
    private String contactPhone;

    private Integer memberCount;

    private String logo;

    private Integer status; // 0-待审核 1-正常 2-已解散

    private Long creatorId;

    private LocalDateTime createTime;

    @PrePersist
    public void prePersist() {
        if (createTime == null) createTime = LocalDateTime.now();
        if (status == null) status = 1;
        if (memberCount == null) memberCount = 0;
    }
}
