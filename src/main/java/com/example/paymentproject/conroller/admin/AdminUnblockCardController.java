package com.example.paymentproject.conroller.admin;


import com.example.paymentproject.service.impl.CardServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "unblock", value = "/unblock")
public class AdminUnblockCardController extends HttpServlet {
    CardServiceImpl cardService = new CardServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/admin/adminUnblockCard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cardId = Integer.parseInt(req.getParameter("cardUnBlock"));
        cardService.unBlockCard(cardId);

        req.getRequestDispatcher("/WEB-INF/views/admin/adminUnblockCard.jsp").forward(req, resp);

    }
}
