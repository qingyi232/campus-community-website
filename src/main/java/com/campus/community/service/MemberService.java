package com.campus.community.service;

import com.campus.community.entity.Member;
import com.campus.community.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> findByClubId(Long clubId) {
        return memberRepository.findByClubId(clubId);
    }

    public List<Member> findByUserId(Long userId) {
        return memberRepository.findByUserId(userId);
    }

    public List<Member> findByClubIdAndStatus(Long clubId, Integer status) {
        return memberRepository.findByClubIdAndStatus(clubId, status);
    }

    public boolean existsByUserIdAndClubId(Long userId, Long clubId) {
        return memberRepository.existsByUserIdAndClubId(userId, clubId);
    }

    public Member findByUserIdAndClubId(Long userId, Long clubId) {
        return memberRepository.findByUserIdAndClubId(userId, clubId).orElse(null);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public void save(Member member) {
        memberRepository.save(member);
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    public long countByClubId(Long clubId) {
        return memberRepository.countByClubIdAndStatus(clubId, 1);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
