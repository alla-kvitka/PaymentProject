package com.example.paymentproject.conroller;

import com.example.paymentproject.entity.Enums.Role;
import com.example.paymentproject.entity.User;
import com.example.paymentproject.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "login", value = "/login")
public class LoginController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserServiceImpl userService = new UserServiceImpl();
        try {
            if (userService.loginCheck(login, password)) {
                HttpSession oldSession = req.getSession(false);
                if (oldSession != null) {
                    oldSession.invalidate();
                }
                HttpSession newSession = req.getSession(true);
                newSession.setMaxInactiveInterval(5 * 60);
                Cookie message = new Cookie("currentUser", login);
                resp.addCookie(message);
                User user = userService.getUserInfo(login);
                if (user.getRole().equals(Role.USER)) {
                resp.sendRedirect(req.getContextPath() + "/homepage");}
                else
                    resp.sendRedirect(req.getContextPath() +"/adminHomepage");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }

}
