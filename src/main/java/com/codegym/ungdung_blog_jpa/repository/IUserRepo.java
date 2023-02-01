package com.codegym.ungdung_blog_jpa.repository;

import com.codegym.ungdung_blog_jpa.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends PagingAndSortingRepository<User, Integer> {
    @Query(nativeQuery = true, value = "select * from user where email=:email and password=:password")
    User checkUser(@Param("email") String email, @Param("password") String password);
}
