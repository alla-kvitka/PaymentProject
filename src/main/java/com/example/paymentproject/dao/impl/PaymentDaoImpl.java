package com.example.paymentproject.dao.impl;

import com.example.paymentproject.dao.iterfaces.PaymentDao;
import com.example.paymentproject.entity.Payment;
import com.example.paymentproject.entity.Transaction;
import com.example.paymentproject.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public void submitAllPaymentsForUser(int userId) {
        CardDaoImpl cardDao = new CardDaoImpl();
        List<Payment> createdPayments = searchAllCreatedPayments(userId);
        for (Payment payment : createdPayments) {
            submitPayment(payment);
            cardDao.updateBalAfterSubmit(payment);
            addToHistory(payment);

        }
    }

    @Override
    public void addToHistory(Payment payment) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        int random = Utils.randomInt();
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement
                     ("INSERT INTO TRANSACTION_HISTORY VALUES (?,?, ?, ?, ?,?,?,?,?)")) {
            pstmt.setInt(1, payment.getUserId());
            pstmt.setInt(2, random);
            pstmt.setLong(3, payment.getBillId());
            pstmt.setInt(4, payment.getCardId());
            pstmt.setTimestamp(5, timestamp);
            pstmt.setInt(6, payment.getPaymentSum());
            pstmt.setString(7, payment.getTransactionType());
            pstmt.setInt(8, 1);
            pstmt.setLong(9, payment.getPaymentId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaction> searchAllUserTransaction(int userId) {
        List<Transaction> transactionList = new ArrayList<>();
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt =
                     connection.prepareStatement("SELECT tr_date, card_id, payment_sum, payment_type " +
                             " FROM TRANSACTION_HISTORY WHERE `user_id` = ?")) {
            pstmt.setLong(1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setDate(rs.getString(1));
                transaction.setCardId(rs.getInt(2));
                transaction.setPaymentSum(rs.getInt(3));
                transaction.setTransactionType(rs.getString(4));
                transactionList.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            close(rs);
        }
        return transactionList;
    }

    @Override
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

    @Override
    public List<Payment> searchAllCreatedPayments(int userId) {
        List<Payment> createdPayments = new ArrayList<>();
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt =
                     connection.prepareStatement("SELECT * FROM PAYMENTS WHERE `user_id` = ? AND payment_status=0")) {
            pstmt.setLong(1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                createdPayments.add(new Payment(rs.getInt(1), rs.getInt(2), rs.getLong(3),
                        rs.getInt(4), rs.getInt(5), rs.getString(6),
                        rs.getInt(7)));
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

