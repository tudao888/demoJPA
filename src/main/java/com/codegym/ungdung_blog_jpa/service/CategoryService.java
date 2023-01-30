package com.codegym.ungdung_blog_jpa.service;

import com.codegym.ungdung_blog_jpa.model.Category;
import com.codegym.ungdung_blog_jpa.repository.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    ICategoryRepo iCategoryRepo;

    public List<Category> findAll(){
       return (List<Category>) iCategoryRepo.findAll();
    }

    public Optional<Category> findById(int id) {
        return iCategoryRepo.findById(id);
    }
}
