package com.campus.community.controller;

import com.campus.community.entity.Activity;
import com.campus.community.entity.Club;
import com.campus.community.entity.User;
import com.campus.community.service.ActivityService;
import com.campus.community.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ClubService clubService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String keyword, Model model) {
        List<Activity> activities;
        if (keyword != null && !keyword.trim().isEmpty()) {
            activities = activityService.search(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            activities = activityService.findAll();
        }
        model.addAttribute("activities", activities);
        return "activity/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Activity activity = activityService.findById(id);
        model.addAttribute("activity", activity);
        return "activity/detail";
    }

    @GetMapping("/add")
    public String addPage(Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        List<Club> clubs = clubService.findAll();
        model.addAttribute("clubs", clubs);
        return "activity/add";
    }

    @PostMapping("/save")
    public String save(Activity activity, HttpSession session, RedirectAttributes ra) {
        User loginUser = (User) session.getAttribute("loginUser");
        activity.setPublisherId(loginUser.getId());
        activity.setPublisherName(loginUser.getUsername());
        if (activity.getClubId() != null) {
            Club club = clubService.findById(activity.getClubId());
            if (club != null) {
                activity.setClubName(club.getName());
            }
        }
        activityService.save(activity);
        ra.addFlashAttribute("success", "活动发布成功");
        return "redirect:/activity/list";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        model.addAttribute("activity", activityService.findById(id));
        model.addAttribute("clubs", clubService.findAll());
        return "activity/edit";
    }

    @PostMapping("/update")
    public String update(Activity activity, RedirectAttributes ra) {
        Activity existing = activityService.findById(activity.getId());
        existing.setTitle(activity.getTitle());
        existing.setContent(activity.getContent());
        existing.setLocation(activity.getLocation());
        existing.setStartTime(activity.getStartTime());
        existing.setEndTime(activity.getEndTime());
        existing.setMaxParticipants(activity.getMaxParticipants());
        existing.setStatus(activity.getStatus());
        if (activity.getClubId() != null) {
            existing.setClubId(activity.getClubId());
            Club club = clubService.findById(activity.getClubId());
            if (club != null) existing.setClubName(club.getName());
        }
        activityService.save(existing);
        ra.addFlashAttribute("success", "修改成功");
        return "redirect:/activity/detail/" + activity.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser.getRole() == 2) {
            activityService.delete(id);
        }
        return "redirect:/activity/list";
    }
}
