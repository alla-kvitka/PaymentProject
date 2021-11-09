package com.example.paymentproject.dao.impl;

import com.example.paymentproject.dao.iterfaces.PaymentDao;
import com.example.paymentproject.entity.Payment;
import com.example.paymentproject.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {
    @Override
    public Payment insertPayment(Payment payment) {
        int random = Utils.randomInt();
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement
                     ("INSERT INTO PAYMENTS VALUES (?, ?, ?, ?,?,?,?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            payment.setPaymentId(random);
            pstmt.setInt(1, payment.getUserId());
            pstmt.setInt(2, payment.getPaymentId());
            pstmt.setLong(3, payment.getBillId());
            pstmt.setInt(4, payment.getCardId());
            pstmt.setInt(5, payment.getPaymentSum());
            pstmt.setString(6, payment.getTransactionType());
            pstmt.setInt(7, payment.getStatus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }

    public void submitAllPaymentsForUser(int userId) {
        List<Payment> createdPayments = searchAllCreatedPayments(userId);
        for (Payment payment : createdPayments) {
            submitPayment(payment);
        }
    }

    public void submitPayment(Payment payment) {
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement("UPDATE PAYMENTS SET payment_status=? WHERE payment_id=?")) {
            pstmt.setString(1, String.valueOf(1));
            pstmt.setLong(2, payment.getPaymentId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Payment> searchAllCreatedPayments(int userId) {
        List<Payment> createdPayments = new ArrayList<>();
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement
                     ("SELECT payment_sum,payment_id,card_id,bill_id,payment_type," +
                             "payment_status PAYMENTS " +
                             "WHERE user_id =? AND payment_status=0")) {
            rs = pstmt.executeQuery();
            while (rs.next()) {
                createdPayments.add(new Payment(rs.getInt(1), rs.getInt(2),
                        rs.getInt(3), rs.getLong(4), rs.getString(5),
                        rs.getInt(6)));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            close(rs);
        }
        return createdPayments;
    }

    private static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }
        }

    }
}

