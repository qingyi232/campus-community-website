package com.campus.community.controller;

import com.campus.community.entity.Notice;
import com.campus.community.entity.User;
import com.campus.community.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String keyword, Model model) {
        List<Notice> notices;
        if (keyword != null && !keyword.trim().isEmpty()) {
            notices = noticeService.search(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            notices = noticeService.findAll();
        }
        model.addAttribute("notices", notices);
        return "notice/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Notice notice = noticeService.findById(id);
        if (notice != null) {
            notice.setViewCount(notice.getViewCount() + 1);
            noticeService.save(notice);
        }
        model.addAttribute("notice", notice);
        return "notice/detail";
    }

    @GetMapping("/add")
    public String addPage() {
        return "notice/add";
    }

    @PostMapping("/save")
    public String save(Notice notice, HttpSession session, RedirectAttributes ra) {
        User loginUser = (User) session.getAttribute("loginUser");
        notice.setPublisher(loginUser.getUsername());
        noticeService.save(notice);
        ra.addFlashAttribute("success", "公告发布成功");
        return "redirect:/notice/list";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        model.addAttribute("notice", noticeService.findById(id));
        return "notice/edit";
    }

    @PostMapping("/update")
    public String update(Notice notice, RedirectAttributes ra) {
        Notice existing = noticeService.findById(notice.getId());
        existing.setTitle(notice.getTitle());
        existing.setContent(notice.getContent());
        existing.setTop(notice.getTop());
        noticeService.save(existing);
        ra.addFlashAttribute("success", "修改成功");
        return "redirect:/notice/detail/" + notice.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser.getRole() == 2) {
            noticeService.delete(id);
        }
        return "redirect:/notice/list";
    }
}
