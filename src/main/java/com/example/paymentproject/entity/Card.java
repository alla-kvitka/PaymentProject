package com.example.paymentproject.entity;

import com.example.paymentproject.entity.Enums.CardStatus;

public class Card {
    private int cardId;
    private int userId;
    private long cardSum;
    private long billId;
    private CardStatus cardStatus;

    public Card(int cardId, int userId, long cardSum, long billId, CardStatus cardStatus) {
        this.cardId = cardId;
        this.userId = userId;
        this.cardSum = cardSum;
        this.billId = billId;
        this.cardStatus = cardStatus;
    }

    public Card() {
    }

    public static Card createCard(User user) {
        Card card = new Card();
        card.setUserId(user.getUserId());
        card.setCardSum(4000);
        card.setCardStatus(CardStatus.ACTIVE);
        card.setBillId(user.getUserBill());
        return card;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getCardSum() {
        return cardSum;
    }

    public void setCardSum(long cardSum) {
        this.cardSum = cardSum;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public CardStatus isCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }
}
