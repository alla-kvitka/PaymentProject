package com.example.paymentproject.dao.impl;

import com.example.paymentproject.dao.iterfaces.CardDao;
import com.example.paymentproject.entity.Card;
import com.example.paymentproject.entity.Enums.CardStatus;
import com.example.paymentproject.entity.Payment;
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
    public Card searchCardById(int userId) {
        Card card = null;
        ResultSet result = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement("SELECT * FROM CARDS WHERE user_id=?")) {
            pstm.setInt(1, userId);
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
    public Card searchCardByCardId(int cardId) {
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
    public void blockCard(int cardId) {
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement
                     ("UPDATE CARDS SET card_status=? WHERE card_id=?")) {
            pstmt.setString(1, "BLOCKED");
            pstmt.setLong(2, cardId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unBlockCard(int cardId) {
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement
                     ("UPDATE CARDS SET card_status=? WHERE card_id=?")) {
            pstmt.setString(1, "ACTIVE");
            pstmt.setLong(2, cardId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

@Override
    public List<Card> findAllCards() {
        List<Card> cards = new ArrayList<>();
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM cards")) {
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
@Override
public  void updateBalAfterSubmit (Payment payment){
    Card card = searchCardByCardId(payment.getCardId());
    try (Connection connection = DBConnection.getInstance().getConnection();
         PreparedStatement pstmt = connection.prepareStatement("UPDATE CARDS SET card_sum=? WHERE card_id=?")){
        pstmt.setLong(2, payment.getCardId());
        if (payment.getTransactionType().equalsIgnoreCase("positive"))
        pstmt.setInt(1, (int) (payment.getPaymentSum()+card.getCardSum()));
        else
            pstmt.setInt(1, (int) (card.getCardSum()-payment.getPaymentSum()));
        pstmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
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
