package com.codegym.ungdung_blog_jpa.service;

import com.codegym.ungdung_blog_jpa.model.User;
import com.codegym.ungdung_blog_jpa.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepo iUserRepo;

    public List<User> findAll() {
        return (List<User>) iUserRepo.findAll();
    }

    public void save(User user) {
        iUserRepo.save(user);
    }

    public void delete(int id) {
        iUserRepo.deleteById(id);
    }

    public User findById(int id) {
       return iUserRepo.findById(id).get();
    }

    public User checkUser(String email, String password) {
    return iUserRepo.checkUser(email, password);
    }

    public boolean checkEmail(String email) {
        List<User> userList = findAll();
        for (User u : userList) {
            if (email.equals(u.getEmail())) {
                return true;
            }
        }
        return false;
    }

}
