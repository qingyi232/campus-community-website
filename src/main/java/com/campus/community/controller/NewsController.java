package com.campus.community.controller;

import com.campus.community.entity.Club;
import com.campus.community.entity.News;
import com.campus.community.entity.User;
import com.campus.community.service.ClubService;
import com.campus.community.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private ClubService clubService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String keyword, Model model) {
        List<News> newsList;
        if (keyword != null && !keyword.trim().isEmpty()) {
            newsList = newsService.search(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            newsList = newsService.findAll();
        }
        model.addAttribute("newsList", newsList);
        return "news/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        News news = newsService.findById(id);
        if (news != null) {
            news.setViewCount(news.getViewCount() + 1);
            newsService.save(news);
        }
        model.addAttribute("news", news);
        return "news/detail";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("clubs", clubService.findAll());
        return "news/add";
    }

    @PostMapping("/save")
    public String save(News news, HttpSession session, RedirectAttributes ra) {
        User loginUser = (User) session.getAttribute("loginUser");
        news.setPublisherId(loginUser.getId());
        news.setPublisherName(loginUser.getUsername());
        if (news.getClubId() != null) {
            Club club = clubService.findById(news.getClubId());
            if (club != null) news.setClubName(club.getName());
        }
        newsService.save(news);
        ra.addFlashAttribute("success", "资讯发布成功");
        return "redirect:/news/list";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        model.addAttribute("news", newsService.findById(id));
        model.addAttribute("clubs", clubService.findAll());
        return "news/edit";
    }

    @PostMapping("/update")
    public String update(News news, RedirectAttributes ra) {
        News existing = newsService.findById(news.getId());
        existing.setTitle(news.getTitle());
        existing.setContent(news.getContent());
        existing.setClubId(news.getClubId());
        if (news.getClubId() != null) {
            Club club = clubService.findById(news.getClubId());
            if (club != null) existing.setClubName(club.getName());
        }
        newsService.save(existing);
        ra.addFlashAttribute("success", "修改成功");
        return "redirect:/news/detail/" + news.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser.getRole() == 2) {
            newsService.delete(id);
        }
        return "redirect:/news/list";
    }
}
