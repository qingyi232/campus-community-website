package com.campus.community.repository;

import com.campus.community.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByClubId(Long clubId);
    List<Member> findByUserId(Long userId);
    List<Member> findByClubIdAndStatus(Long clubId, Integer status);
    Optional<Member> findByUserIdAndClubId(Long userId, Long clubId);
    boolean existsByUserIdAndClubId(Long userId, Long clubId);
    long countByClubIdAndStatus(Long clubId, Integer status);
}
