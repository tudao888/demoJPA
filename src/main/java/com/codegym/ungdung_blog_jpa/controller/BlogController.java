package com.codegym.ungdung_blog_jpa.controller;

import com.codegym.ungdung_blog_jpa.model.Blog;
import com.codegym.ungdung_blog_jpa.model.Category;
import com.codegym.ungdung_blog_jpa.service.BlogService;
import com.codegym.ungdung_blog_jpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
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

    @ModelAttribute("categories")
    public List<Category> categoryList() {
        return (List<Category>) categoryService.findAll();
    }

    @GetMapping("/blogs")
    public ModelAndView getAll(@RequestParam (defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("blogs", blogService.findAll(PageRequest.of(page,2)));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView save(Blog blog, @RequestParam MultipartFile upImg) {
        ModelAndView modelAndView;
        String nameFile = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File("E:\\Module 4\\ungDung_Blog_JPA\\src\\main\\webapp\\WEB-INF\\image/" + nameFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        blog.setImg("/image/"+ nameFile);
        blogService.save(blog);
        modelAndView = new ModelAndView("redirect:/blogs") ;
        return  modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("redirect:/blogs");
        blogService.deleteById(id);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("blog", blogService.findById(id).get());
        return modelAndView;
    }

    @PostMapping("/edit")
    public String update(Blog blog, @RequestParam MultipartFile upImg){
        String nameFile = upImg.getOriginalFilename();
        if (!upImg.isEmpty()) {
            try {
                FileCopyUtils.copy(upImg.getBytes(), new File("E:\\Module 4\\ungDung_Blog_JPA\\src\\main\\webapp\\WEB-INF\\image/" + nameFile));
                blog.setImg("/image/"+ nameFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            blog.setImg(blog.getImg());
        }
        blogService.save(blog);
        String id = String.valueOf(blog.getId());
        return "redirect:/blog/" + id;
    }


    @GetMapping("/blog/{id}")
    public ModelAndView showBlog(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("showBlog");
        modelAndView.addObject("blog", blogService.findById(id).get());
        return modelAndView;
    }
}
