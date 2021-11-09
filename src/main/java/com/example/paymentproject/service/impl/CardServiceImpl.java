package com.example.paymentproject.service.impl;

import com.example.paymentproject.dao.impl.CardDaoImpl;
import com.example.paymentproject.entity.Card;
import com.example.paymentproject.service.interfaces.CardService;

import java.sql.SQLException;
import java.util.List;

public class CardServiceImpl implements CardService {
    CardDaoImpl cardDao = new CardDaoImpl();

    @Override
    public Card insertCard(Card card) throws SQLException {
        return cardDao.insertCard(card);
    }

    @Override
    public Card searchCardById(int cardId) {
        return cardDao.searchCardById(cardId);
    }

    @Override
    public void deleteCard(Card card) {
        cardDao.deleteCard(card);
    }

    @Override
    public boolean blockCard(Card card) {
        return false;
    }

    @Override
    public boolean unBlockCard(Card card) {
        return false;
    }

    @Override
    public List<Card> findAllUsersCards(int userid) {
        return cardDao.findAllUsersCards(userid);
    }
}
