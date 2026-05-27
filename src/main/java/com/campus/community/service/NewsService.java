package com.campus.community.service;

import com.campus.community.entity.News;
import com.campus.community.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public List<News> findAll() {
        return newsRepository.findAllByOrderByCreateTimeDesc();
    }

    public List<News> findByClubId(Long clubId) {
        return newsRepository.findByClubId(clubId);
    }

    public List<News> search(String title) {
        return newsRepository.findByTitleContaining(title);
    }

    public List<News> findLatest() {
        return newsRepository.findTop5ByOrderByCreateTimeDesc();
    }

    public News findById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    public void save(News news) {
        newsRepository.save(news);
    }

    public void delete(Long id) {
        newsRepository.deleteById(id);
    }

    public long count() {
        return newsRepository.count();
    }
}
