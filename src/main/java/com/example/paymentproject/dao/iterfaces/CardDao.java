package com.example.paymentproject.dao.iterfaces;

import com.example.paymentproject.entity.Card;

import java.sql.SQLException;
import java.util.List;

public interface CardDao {

    Card insertCard(Card card) throws SQLException;


    Card searchCardById (int cardId);

    void deleteCard(Card card);

    boolean blockCard(Card card);

    boolean unBlockCard(Card card);

    public List<Card> findAllUsersCards(int userid);

}
