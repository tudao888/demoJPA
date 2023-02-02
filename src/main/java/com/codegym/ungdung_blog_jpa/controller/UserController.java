package com.codegym.ungdung_blog_jpa.controller;

import com.codegym.ungdung_blog_jpa.model.User;
import com.codegym.ungdung_blog_jpa.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import jdk.internal.icu.text.NormalizerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    HttpSession httpSession;
    @GetMapping("/register")
    public ModelAndView formRegister() {
        ModelAndView modelAndView = new ModelAndView("formRegister");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute User user, @RequestParam MultipartFile upAvatar) {
        ModelAndView modelAndView1 = new ModelAndView("formRegister");
        ModelAndView modelAndView2 = new ModelAndView("redirect: /blogs");
        String message;
        if (userService.checkEmail(user.getEmail())) {
            message = "Tài khoản email đã tồn tại";
            modelAndView1.addObject("message", message);
           return modelAndView1;
        } else {
            String nameFile = upAvatar.getOriginalFilename();
            try {
                FileCopyUtils.copy(upAvatar.getBytes(), new File("E:\\Module 4\\ungDung_Blog_JPA\\src\\main\\webapp\\WEB-INF\\image/" + nameFile));
            } catch (Exception e) {
                e.printStackTrace();
            }
            user.setAvatar("/image/"+ nameFile);
            userService.save(user);
            return modelAndView2;
        }
    }

    @GetMapping("/login")
    public ModelAndView formLogin() {
        ModelAndView modelAndView = new ModelAndView("formLogin");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String email, @RequestParam String password) {
        ModelAndView modelAndView1 = new ModelAndView("redirect:/login");
        ModelAndView modelAndView2 = new ModelAndView("redirect:/blogs");
        User user = userService.checkUser(email,password);
        if (user == null) {
            return modelAndView1;
        }
        else {
            httpSession.setAttribute("user", user);
            modelAndView2.addObject("user", user);
            return modelAndView2;
        }
    }

    @GetMapping("/showInformation/{id}")
    public ModelAndView showInformation(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("showInformation");
//        modelAndView.addObject("user", userService.findById(id));
        return modelAndView;
    }

}
