package com.example.paymentproject.dao.impl;


import com.example.paymentproject.dao.iterfaces.UserDao;
import com.example.paymentproject.entity.Enums.Role;
import com.example.paymentproject.entity.User;
import com.example.paymentproject.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements UserDao {

    @Override
    public User insertUser(User user) {
        long randomBill = Utils.randomLong();
        int userIdRandom = Utils.randomInt();
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement
                     ("INSERT INTO USERS VALUES (?, ?, ?, ?,?,?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            if (checkExistForUser(user)) {
                user.setUserId(userIdRandom);
                user.setUserBill(randomBill);

                pstmt.setLong(1, user.getUserId());
                pstmt.setString(2, user.getUserLogin());
                pstmt.setString(3, user.getUserPassword());
                pstmt.setString(4, user.getUserEmail());
                pstmt.setString(5, user.getRole().toString());
                pstmt.setLong(6, user.getUserBill());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteUser(User user) {


    }

    @Override
    public boolean checkExistForUser(User user) {
        boolean isUserExists = false;
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from `Users` where `Login` = ?")) {
            ps.setString(1, user.getUserLogin());
            rs = ps.executeQuery();
            if (rs.next()) {
                isUserExists = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            close(rs);
        }
        return !isUserExists;
    }

    @Override
    public boolean checkPassLogin(String login, String password) throws SQLException {
        ResultSet rs;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from `Users` " +
                     "where `user_login` = ? and `user_password` = ?")) {
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUser(String login) {
        User user = null;
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM `users` WHERE `user_login`= ?")) {
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUserPassword(rs.getString("user_password"));
                user.setUserLogin(rs.getString("user_login"));
                user.setUserEmail(rs.getString("user_email"));
                user.setRole(Role.valueOf(rs.getString("user_role")));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            close(rs);
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        ResultSet rs = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users")) {
            rs = pstmt.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        Role.valueOf(rs.getString(5)),
                        rs.getInt(6)));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            close(rs);
        }
        return users;
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
