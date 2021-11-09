package com.example.paymentproject.entity;

public class Transaction {
    private int trId;
    private int userId;
    private int paymentSum;
    private int paymentId;
    private int cardId;
    private long billId;
    private String transactionType;
    private int status;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Transaction(int trId, int userId, int paymentSum,
                       int paymentId, int cardId,
                       long billId, String transactionType, int status) {
        this.trId = trId;
        this.userId = userId;
        this.paymentSum = paymentSum;
        this.paymentId = paymentId;
        this.cardId = cardId;
        this.billId = billId;
        this.transactionType = transactionType;
        this.status = status;
    }

    public Transaction() {

    }

    public static Transaction createTransaction(Payment payment) {
        Transaction transaction = new Transaction();
        transaction.setUserId(payment.getUserId());
        transaction.setPaymentSum(payment.getPaymentSum());
        transaction.setPaymentId(payment.getPaymentId());
        transaction.setCardId(payment.getCardId());
        transaction.setBillId(payment.getBillId());
        transaction.setTransactionType(payment.getTransactionType());
        transaction.setStatus(payment.getStatus());
        return transaction;
    }


    public int getTrId() {
        return trId;
    }

    public void setTrId(int trId) {
        this.trId = trId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(int paymentSum) {
        this.paymentSum = paymentSum;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
