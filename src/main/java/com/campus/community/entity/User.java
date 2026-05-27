package com.campus.community.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(length = 50)
    private String realName;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String email;

    @Column(length = 10)
    private String gender;

    @Column(length = 50)
    private String department;

    @Column(length = 20)
    private String studentNo;

    private Integer role; // 0-普通用户 2-系统管理员

    private String avatar;

    private LocalDateTime createTime;

    @PrePersist
    public void prePersist() {
        if (createTime == null) {
            createTime = LocalDateTime.now();
        }
        if (role == null) {
            role = 0;
        }
    }
}
