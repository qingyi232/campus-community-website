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
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String keyword, Model model) {
        List<Club> clubs;
        if (keyword != null && !keyword.trim().isEmpty()) {
            clubs = clubService.search(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            clubs = clubService.findByStatus(1);
        }
        model.addAttribute("clubs", clubs);
        return "club/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model, HttpSession session) {
        Club club = clubService.findById(id);
        long actualCount = memberService.countByClubId(id);
        if (actualCount > 0) {
            club.setMemberCount((int) actualCount);
        }
        model.addAttribute("club", club);
        model.addAttribute("memberCount", club.getMemberCount());
        List<Member> members = memberService.findByClubIdAndStatus(id, 1);
        model.addAttribute("members", members);

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null) {
            boolean isMember = memberService.existsByUserIdAndClubId(loginUser.getId(), id);
            model.addAttribute("isMember", isMember);
        }
        return "club/detail";
    }

    @GetMapping("/add")
    public String addPage() {
        return "club/add";
    }

    @PostMapping("/save")
    public String save(Club club, HttpSession session, RedirectAttributes ra) {
        User loginUser = (User) session.getAttribute("loginUser");
        club.setCreatorId(loginUser.getId());
        clubService.save(club);
        ra.addFlashAttribute("success", "社团创建成功");
        return "redirect:/club/list";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        model.addAttribute("club", clubService.findById(id));
        return "club/edit";
    }

    @PostMapping("/update")
    public String update(Club club, RedirectAttributes ra) {
        Club existing = clubService.findById(club.getId());
        existing.setName(club.getName());
        existing.setCategory(club.getCategory());
        existing.setDescription(club.getDescription());
        existing.setPresident(club.getPresident());
        existing.setContactPhone(club.getContactPhone());
        clubService.save(existing);
        ra.addFlashAttribute("success", "修改成功");
        return "redirect:/club/detail/" + club.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser.getRole() == 2) {
            clubService.delete(id);
        }
        return "redirect:/club/list";
    }

    @PostMapping("/join/{id}")
    public String join(@PathVariable Long id, HttpSession session, RedirectAttributes ra) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (memberService.existsByUserIdAndClubId(loginUser.getId(), id)) {
            ra.addFlashAttribute("error", "您已经是该社团成员");
            return "redirect:/club/detail/" + id;
        }
        Club club = clubService.findById(id);
        Member member = new Member();
        member.setUserId(loginUser.getId());
        member.setUsername(loginUser.getUsername());
        member.setRealName(loginUser.getRealName());
        member.setClubId(id);
        member.setClubName(club.getName());
        memberService.save(member);
        ra.addFlashAttribute("success", "申请已提交，等待审核");
        return "redirect:/club/detail/" + id;
    }
}
