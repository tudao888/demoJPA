package com.codegym.ungdung_blog_jpa.repository;

import com.codegym.ungdung_blog_jpa.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICategoryRepo extends PagingAndSortingRepository<Category, Integer> {
}
