package com.campus.community.controller;

import com.campus.community.entity.Message;
import com.campus.community.entity.User;
import com.campus.community.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Message> messages = messageService.findAll();
        model.addAttribute("messages", messages);
        return "message/list";
    }

    @PostMapping("/save")
    public String save(String content, HttpSession session, RedirectAttributes ra) {
        User loginUser = (User) session.getAttribute("loginUser");
        Message message = new Message();
        message.setContent(content);
        message.setUserId(loginUser.getId());
        message.setUsername(loginUser.getUsername());
        messageService.save(message);
        ra.addFlashAttribute("success", "留言成功");
        return "redirect:/message/list";
    }

    @PostMapping("/reply/{id}")
    public String reply(@PathVariable Long id, String reply, HttpSession session, RedirectAttributes ra) {
        User loginUser = (User) session.getAttribute("loginUser");
        Message message = messageService.findById(id);
        if (message != null) {
            message.setReply(reply);
            message.setReplyBy(loginUser.getUsername());
            message.setReplyTime(LocalDateTime.now());
            message.setStatus(1);
            messageService.save(message);
        }
        ra.addFlashAttribute("success", "回复成功");
        return "redirect:/message/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser.getRole() == 2) {
            messageService.delete(id);
        }
        return "redirect:/message/list";
    }
}
