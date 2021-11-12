package com.example.paymentproject.service.impl;

import com.example.paymentproject.dao.impl.CardDaoImpl;
import com.example.paymentproject.entity.Card;
import com.example.paymentproject.entity.Payment;
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
    public Card searchCardById(int userId) {
        return cardDao.searchCardById(userId);
    }
    public Card searchCardByCardId(int cardId) {
        return cardDao.searchCardByCardId(cardId);
    }


    @Override
    public void deleteCard(Card card) {
        cardDao.deleteCard(card);
    }

    @Override
    public void blockCard(int cardId) {
        cardDao.blockCard(cardId);
    }

    @Override
    public void unBlockCard(int cardId) {
        cardDao.unBlockCard(cardId);
    }

    @Override
    public List<Card> findAllUsersCards(int userid) {
        return cardDao.findAllUsersCards(userid);
    }

    @Override
    public List<Card> findAllCards() {
        return cardDao.findAllCards();
    }
    @Override
    public void updateBalAfterSubmit(Payment payment) {
        cardDao.updateBalAfterSubmit(payment);
    }
}
