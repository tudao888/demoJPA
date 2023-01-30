package com.codegym.ungdung_blog_jpa.repository;

import com.codegym.ungdung_blog_jpa.model.Blog;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBlogRepo extends PagingAndSortingRepository<Blog, Integer> {
}
