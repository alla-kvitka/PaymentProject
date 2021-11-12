package com.example.paymentproject.conroller.user;

import com.example.paymentproject.entity.Payment;
import com.example.paymentproject.entity.User;
import com.example.paymentproject.service.impl.PaymentServiceImpl;
import com.example.paymentproject.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "paymentSubmit", value = "/paymentSubmit")
public class PaymentSubmitController extends HttpServlet {
    List<Payment> paymentList;
    User user;
    UserServiceImpl userService = new UserServiceImpl();
    PaymentServiceImpl paymentService = new PaymentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String login = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("currentUser")) {
                login = cookie.getValue();
            }
        }
        user = userService.getUserInfo(login);
        paymentList = paymentService.searchAllCreatedPayments(user.getUserId());
        req.setAttribute("payments", paymentList);
        getServletContext().getRequestDispatcher("/WEB-INF/views/user/payments/submitPayment.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        paymentService.submitAllPaymentsForUser(user.getUserId());
        req.getRequestDispatcher("/WEB-INF/views/user/payments/doPayment.jsp").forward(req, resp);
    }
}
