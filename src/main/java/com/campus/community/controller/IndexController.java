package com.campus.community.controller;

import com.campus.community.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private NoticeService noticeService;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("clubCount", clubService.count());
        model.addAttribute("activityCount", activityService.count());
        model.addAttribute("newsCount", newsService.count());
        model.addAttribute("latestActivities", activityService.findLatest());
        model.addAttribute("latestNews", newsService.findLatest());
        model.addAttribute("latestNotices", noticeService.findLatest());
        model.addAttribute("clubs", clubService.findByStatus(1));
        return "index";
    }
}
