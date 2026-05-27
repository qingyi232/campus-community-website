package com.campus.community.repository;

import com.campus.community.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {
    List<Club> findByStatus(Integer status);
    List<Club> findByNameContaining(String name);
    List<Club> findByCategory(String category);
    List<Club> findByCreatorId(Long creatorId);
}
