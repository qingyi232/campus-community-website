package com.campus.community.service;

import com.campus.community.entity.Activity;
import com.campus.community.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> findAll() {
        return activityRepository.findAllByOrderByCreateTimeDesc();
    }

    public List<Activity> findByClubId(Long clubId) {
        return activityRepository.findByClubId(clubId);
    }

    public List<Activity> findByStatus(Integer status) {
        return activityRepository.findByStatus(status);
    }

    public List<Activity> search(String title) {
        return activityRepository.findByTitleContaining(title);
    }

    public List<Activity> findLatest() {
        return activityRepository.findTop5ByOrderByCreateTimeDesc();
    }

    public Activity findById(Long id) {
        return activityRepository.findById(id).orElse(null);
    }

    public void save(Activity activity) {
        activityRepository.save(activity);
    }

    public void delete(Long id) {
        activityRepository.deleteById(id);
    }

    public long count() {
        return activityRepository.count();
    }
}
