package com.example.paymentproject.conroller.admin;

import com.example.paymentproject.entity.User;
import com.example.paymentproject.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "adminHomepage", value = "/adminHomepage")
public class AdminHomeController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String login = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("currentUser")) {
                login = cookie.getValue();
            }
        }
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.getUserInfo(login);
        req.setAttribute("login", user.getUserLogin());
        req.setAttribute("email", user.getUserEmail());
        getServletContext().getRequestDispatcher("/WEB-INF/views/admin/adminHomePage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
