package com.campus.community.service;

import com.campus.community.entity.Club;
import com.campus.community.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    public List<Club> findAll() {
        return clubRepository.findAll();
    }

    public List<Club> findByStatus(Integer status) {
        return clubRepository.findByStatus(status);
    }

    public List<Club> search(String name) {
        return clubRepository.findByNameContaining(name);
    }

    public List<Club> findByCategory(String category) {
        return clubRepository.findByCategory(category);
    }

    public Club findById(Long id) {
        return clubRepository.findById(id).orElse(null);
    }

    public void save(Club club) {
        clubRepository.save(club);
    }

    public void delete(Long id) {
        clubRepository.deleteById(id);
    }

    public List<Club> findByCreatorId(Long creatorId) {
        return clubRepository.findByCreatorId(creatorId);
    }

    public long count() {
        return clubRepository.count();
    }
}
