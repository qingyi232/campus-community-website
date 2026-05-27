package com.campus.community.controller;

import com.campus.community.entity.Club;
import com.campus.community.entity.Member;
import com.campus.community.entity.User;
import com.campus.community.service.ClubService;
import com.campus.community.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private ClubService clubService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) Long clubId, Model model) {
        List<Member> members;
        if (clubId != null) {
            members = memberService.findByClubId(clubId);
            model.addAttribute("club", clubService.findById(clubId));
        } else {
            members = memberService.findAll();
        }
        model.addAttribute("members", members);
        model.addAttribute("clubs", clubService.findAll());
        return "member/list";
    }

    @GetMapping("/my")
    public String myClubs(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        List<Member> members = memberService.findByUserId(loginUser.getId());
        model.addAttribute("members", members);
        return "member/my";
    }

    @GetMapping("/approve/{id}")
    public String approve(@PathVariable Long id, RedirectAttributes ra) {
        Member member = memberService.findById(id);
        if (member != null) {
            member.setStatus(1);
            memberService.save(member);
            Club club = clubService.findById(member.getClubId());
            if (club != null) {
                club.setMemberCount((int) memberService.countByClubId(club.getId()));
                clubService.save(club);
            }
        }
        ra.addFlashAttribute("success", "审核通过");
        return "redirect:/member/list?clubId=" + (member != null ? member.getClubId() : "");
    }

    @GetMapping("/reject/{id}")
    public String reject(@PathVariable Long id, RedirectAttributes ra) {
        Member member = memberService.findById(id);
        Long clubId = member != null ? member.getClubId() : null;
        if (member != null) {
            member.setStatus(2);
            memberService.save(member);
        }
        ra.addFlashAttribute("success", "已拒绝");
        return "redirect:/member/list?clubId=" + clubId;
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id, RedirectAttributes ra) {
        Member member = memberService.findById(id);
        Long clubId = member != null ? member.getClubId() : null;
        if (member != null) {
            memberService.delete(id);
            Club club = clubService.findById(clubId);
            if (club != null) {
                club.setMemberCount((int) memberService.countByClubId(club.getId()));
                clubService.save(club);
            }
        }
        ra.addFlashAttribute("success", "已移除");
        return "redirect:/member/list?clubId=" + clubId;
    }

    @PostMapping("/updatePosition")
    public String updatePosition(@RequestParam Long id, @RequestParam String position, RedirectAttributes ra) {
        Member member = memberService.findById(id);
        if (member != null) {
            member.setPosition(position);
            memberService.save(member);
        }
        ra.addFlashAttribute("success", "职位已更新");
        return "redirect:/member/list?clubId=" + (member != null ? member.getClubId() : "");
    }
}
