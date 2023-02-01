package com.codegym.ungdung_blog_jpa.filter;

import com.codegym.ungdung_blog_jpa.model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/blogs","/create"})
public class LoginFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession httpSession = req.getSession();
        User user = (User) httpSession.getAttribute("user");

        if (user == null){
            res.sendRedirect("/login");
        }
        chain.doFilter(req,res);
    }
}
