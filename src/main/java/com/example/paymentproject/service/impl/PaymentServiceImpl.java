package com.example.paymentproject.service.impl;

import com.example.paymentproject.dao.impl.PaymentDaoImpl;
import com.example.paymentproject.entity.Payment;
import com.example.paymentproject.entity.Transaction;
import com.example.paymentproject.service.interfaces.PaymentService;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    PaymentDaoImpl paymentDao = new PaymentDaoImpl();

    @Override
    public Payment insertPayment(Payment payment) {
        return paymentDao.insertPayment(payment);
    }

    @Override
    public void submitAllPaymentsForUser(int userId) {
        paymentDao.submitAllPaymentsForUser(userId);
    }

    @Override
    public void addToHistory(Payment payment) {
        paymentDao.addToHistory(payment);
    }

    @Override
    public void submitPayment(Payment payment) {
        paymentDao.submitPayment(payment);
    }

    @Override
    public List<Payment> searchAllCreatedPayments(int userId) {
        return paymentDao.searchAllCreatedPayments(userId);
    }

    @Override
    public List<Transaction> searchAllUserTransaction(int userId) {
        return paymentDao.searchAllUserTransaction(userId);
    }
}
