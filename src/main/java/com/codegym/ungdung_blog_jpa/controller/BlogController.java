package com.codegym.ungdung_blog_jpa.controller;

import com.codegym.ungdung_blog_jpa.model.Blog;
import com.codegym.ungdung_blog_jpa.model.Category;
import com.codegym.ungdung_blog_jpa.service.BlogService;
import com.codegym.ungdung_blog_jpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("category")
    public List<Category> categoryList() {
        return (List<Category>) categoryService.findAll();
    }

    @GetMapping("/blogs")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("blogs", blogService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(@ModelAttribute("category") Category category) {
        ModelAndView modelAndView = new ModelAndView("/create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView save(Blog blog, @Param("idCategory") Integer idCategory, @RequestParam MultipartFile upImg) {
        ModelAndView modelAndView;
        blog.setCategory(categoryService.findById(idCategory).get());
        String nameFile = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File("E:\\Module 4\\ungDung_Blog_JPA\\src\\main\\webapp\\WEB-INF\\image/" + nameFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        blog.setImg("/image/"+ nameFile);
        blogService.save(blog);
        modelAndView = new ModelAndView("redirect: /blogs") ;
        return  modelAndView;
    }
}
