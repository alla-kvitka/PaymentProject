package com.example.paymentproject.dao.impl;

import com.example.paymentproject.dao.iterfaces.CardDao;
import com.example.paymentproject.entity.Card;
import com.example.paymentproject.entity.Enums.CardStatus;
import com.example.paymentproject.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDaoImpl implements CardDao {
    @Override
    public Card insertCard(Card card) {
        int random = Utils.randomInt();
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement
                     ("INSERT INTO CARDS VALUES (?, ?, ?, ?,?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            card.setCardId(random);
            pstmt.setInt(1, card.getCardId());
            pstmt.setInt(2, card.getUserId());
            pstmt.setLong(3, 4000);
            pstmt.setLong(4, card.getBillId());
            pstmt.setString(5, String.valueOf(card.isCardStatus()));
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return card;
    }


    @Override
    public Card searchCardById(int cardId) {
        Card card = null;
        ResultSet result = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement("SELECT * FROM CARDS WHERE card_id=?")) {
            pstm.setInt(1, cardId);
            result = pstm.executeQuery();
            if (result.next()) {
                card = new Card();
                card.setBillId(result.getLong("bill_id"));
                card.setCardId(result.getInt("card_id"));
                card.setUserId(result.getInt("user_id"));
                card.setCardSum(result.getLong("card_sum"));
                card.setCardStatus(CardStatus.valueOf(result.getString("card_status")));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            close(result);
        }
        return card;
    }

    @Override
    public void deleteCard(Card card) {

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
        List<Card> cards = new ArrayList<>();
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM cards where user_id = ?")) {
            pstmt.setLong(1, userid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                cards.add(new Card(rs.getInt(1), rs.getInt(2),
                        rs.getLong(3), rs.getLong(4), CardStatus.valueOf(rs.getString(5))));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            close(rs);
        }
        return cards;
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
