package com.codegym.ungdung_blog_jpa.service;

import com.codegym.ungdung_blog_jpa.model.Blog;
import com.codegym.ungdung_blog_jpa.repository.IBlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService{

    @Autowired
    IBlogRepo iBlogRepository;

    public List<Blog> findAll(){
        return (List<Blog>) iBlogRepository.findAll();
    }

    public void save(Blog blog) {
        iBlogRepository.save(blog);
    }
}
