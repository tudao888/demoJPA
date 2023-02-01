package com.codegym.ungdung_blog_jpa.controller;

import com.codegym.ungdung_blog_jpa.model.User;
import com.codegym.ungdung_blog_jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/register")
    public ModelAndView formRegister() {
        ModelAndView modelAndView = new ModelAndView("formRegister");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute User user, @RequestParam MultipartFile upAvatar) {
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        String nameFile = upAvatar.getOriginalFilename();
        try {
            FileCopyUtils.copy(upAvatar.getBytes(), new File("E:\\Module 4\\ungDung_Blog_JPA\\src\\main\\webapp\\WEB-INF\\image/" + nameFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setAvatar("/image/"+ nameFile);
        userService.save(user);
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView formLogin() {
        ModelAndView modelAndView = new ModelAndView("formLogin");
        return modelAndView;
    }

    @PostMapping("/login")
    public String login( @RequestParam String email, @RequestParam String password) {
        User user = userService.checkUser(email,password);
        if (user == null) {
            return "redirect:/login";
        }
        else {
            return "redirect:/blogs";
        }

    }

}
