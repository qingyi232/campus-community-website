package com.campus.community.service;

import com.campus.community.entity.Notice;
import com.campus.community.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    public List<Notice> findAll() {
        return noticeRepository.findAllByOrderByTopDescCreateTimeDesc();
    }

    public List<Notice> search(String title) {
        return noticeRepository.findByTitleContaining(title);
    }

    public List<Notice> findLatest() {
        return noticeRepository.findTop5ByOrderByCreateTimeDesc();
    }

    public Notice findById(Long id) {
        return noticeRepository.findById(id).orElse(null);
    }

    public void save(Notice notice) {
        noticeRepository.save(notice);
    }

    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }

    public long count() {
        return noticeRepository.count();
    }
}
