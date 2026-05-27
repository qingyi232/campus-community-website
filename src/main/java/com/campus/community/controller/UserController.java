package com.campus.community.controller;

import com.campus.community.entity.User;
import com.campus.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(String username, String password,
                          @RequestParam(defaultValue = "0") Integer loginRole,
                          HttpSession session, RedirectAttributes ra) {
        User user = userService.login(username, password);
        if (user == null) {
            ra.addFlashAttribute("error", "用户名或密码错误");
            return "redirect:/login";
        }
        if (!user.getRole().equals(loginRole)) {
            String roleName = loginRole == 0 ? "普通用户" : "系统管理员";
            ra.addFlashAttribute("error", "该账号不是" + roleName + "身份，请选择正确的登录身份");
            return "redirect:/login";
        }
        session.setAttribute("loginUser", user);
        if (user.getRole() == 2) {
            return "redirect:/admin/users";
        }
        return "redirect:/index";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/doRegister")
    public String doRegister(User user, RedirectAttributes ra) {
        if (userService.register(user)) {
            ra.addFlashAttribute("success", "注册成功，请登录");
            return "redirect:/login";
        }
        ra.addFlashAttribute("error", "用户名已存在");
        return "redirect:/register";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/user/profile")
    public String profile(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        User user = userService.findById(loginUser.getId());
        model.addAttribute("user", user);
        return "user/profile";
    }

    @PostMapping("/user/update")
    public String updateProfile(User user, HttpSession session, RedirectAttributes ra) {
        User loginUser = (User) session.getAttribute("loginUser");
        User existing = userService.findById(loginUser.getId());
        existing.setRealName(user.getRealName());
        existing.setPhone(user.getPhone());
        existing.setEmail(user.getEmail());
        existing.setGender(user.getGender());
        existing.setDepartment(user.getDepartment());
        existing.setStudentNo(user.getStudentNo());
        userService.update(existing);
        session.setAttribute("loginUser", existing);
        ra.addFlashAttribute("success", "修改成功");
        return "redirect:/user/profile";
    }

    @GetMapping("/admin/users")
    public String userList(Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser.getRole() != 2) {
            return "redirect:/index";
        }
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/userList";
    }

    @PostMapping("/admin/user/role")
    public String changeRole(@RequestParam Long userId, @RequestParam Integer role,
                             HttpSession session, RedirectAttributes ra) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser.getRole() == 2) {
            User target = userService.findById(userId);
            if (target != null && target.getRole() != 2) {
                target.setRole(role);
                userService.update(target);
                ra.addFlashAttribute("success", "用户 " + target.getUsername() + " 角色已更新");
            }
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable Long id, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser.getRole() == 2) {
            userService.delete(id);
        }
        return "redirect:/admin/users";
    }
}
