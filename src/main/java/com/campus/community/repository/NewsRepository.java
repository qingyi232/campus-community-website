package com.campus.community.repository;

import com.campus.community.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByClubId(Long clubId);
    List<News> findByTitleContaining(String title);
    List<News> findAllByOrderByCreateTimeDesc();
    List<News> findTop5ByOrderByCreateTimeDesc();
}
