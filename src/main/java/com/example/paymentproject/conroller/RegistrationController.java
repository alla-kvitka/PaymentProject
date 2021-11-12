package com.example.paymentproject.conroller;

import com.example.paymentproject.entity.Card;
import com.example.paymentproject.entity.User;
import com.example.paymentproject.service.impl.CardServiceImpl;
import com.example.paymentproject.service.impl.UserServiceImpl;
import com.example.paymentproject.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "registration", value = "/registration")
public class RegistrationController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("password-repeat");
        String email = req.getParameter("email");
        UserServiceImpl userService = new UserServiceImpl();
        CardServiceImpl cardService = new CardServiceImpl();

        if (login.equals("") || password.equals("") || passwordRepeat.equals("")
                || email.equals("") || !Utils.checkEmail(email))
            req.getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
        else if (userService.checkExistForUser(User.createUser(login, password, email))
                && password.equals(passwordRepeat)) {
            User user = userService.createUser(User.createUser(login, password, email));
            try {
                cardService.insertCard(Card.createCard(user));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
        }
    }


    public void destroy() {
    }
}