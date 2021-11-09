package com.example.paymentproject.dao.iterfaces;

import com.example.paymentproject.entity.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDao {

    Payment insertPayment(Payment payment) throws SQLException;

    void submitAllPaymentsForUser(int userId);

    void addToHistory(Payment payment);

    void submitPayment(Payment payment);

    List<Payment> searchAllCreatedPayments(int userId);
}
