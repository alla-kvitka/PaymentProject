package com.example.paymentproject.service.interfaces;

import com.example.paymentproject.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    User createUser(User user);

    void deleteUser(User user);

    User getUserInfo(String login);

    boolean checkExistForUser(User user);

    boolean loginCheck(String login, String password) throws SQLException;

    List<User> findUsers();

    User getUserById(int userID);
}
