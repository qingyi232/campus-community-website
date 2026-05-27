package com.campus.community.repository;

import com.campus.community.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByClubId(Long clubId);
    List<Activity> findByStatus(Integer status);
    List<Activity> findByTitleContaining(String title);
    List<Activity> findAllByOrderByCreateTimeDesc();
    List<Activity> findTop5ByOrderByCreateTimeDesc();
}
