package com.example.paymentproject.dao.iterfaces;

import com.example.paymentproject.entity.Card;
import com.example.paymentproject.entity.Payment;

import java.sql.SQLException;
import java.util.List;

public interface CardDao {

    Card insertCard(Card card) throws SQLException;


    Card searchCardById(int cardId);

    void deleteCard(Card card);

    void blockCard(int cardId);

    void unBlockCard(int cardId);

    public List<Card> findAllUsersCards(int userid);

    Card searchCardByCardId(int cardId);

    List<Card> findAllCards();
    void updateBalAfterSubmit(Payment payment);

}
