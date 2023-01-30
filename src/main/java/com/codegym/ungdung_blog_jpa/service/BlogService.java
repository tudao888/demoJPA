package com.codegym.ungdung_blog_jpa.service;

import com.codegym.ungdung_blog_jpa.model.Blog;
import com.codegym.ungdung_blog_jpa.repository.IBlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService{

    @Autowired
    IBlogRepo iBlogRepository;

    public Page<Blog> findAll(Pageable pageable){
        return iBlogRepository.findAll(pageable);
    }

    public void save(Blog blog) {
        iBlogRepository.save(blog);
    }

    public void deleteById(int id) {
        iBlogRepository.deleteById(id);
    }

    public Optional<Blog> findById(int id) {
        return iBlogRepository.findById(id);
    }
}
