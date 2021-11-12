package com.example.paymentproject.entity;

public class Payment {
    private int userId;
    private int paymentSum;
    private int paymentId;
    private int cardId;
    private long billId;
    private String transactionType;
    private int status;


    public Payment() {
    }

    public Payment(int userId, int paymentId, long billId, int cardId, int paymentSum,
                   String transactionType, int status) {
        this.userId = userId;
        this.paymentId = paymentId;
        this.billId = billId;
        this.cardId = cardId;
        this.paymentSum = paymentSum;
        this.transactionType = transactionType;
        this.status = status;
    }


    public static Payment createPayment(Card card, String transactionType,
                                        int sum) {
        Payment payment = new Payment();
        payment.setUserId(card.getUserId());
        payment.setCardId(card.getCardId());
        payment.setPaymentSum(sum);
        payment.setTransactionType(transactionType);
        payment.setBillId(card.getBillId());
        payment.setStatus(0);
        return payment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
}
