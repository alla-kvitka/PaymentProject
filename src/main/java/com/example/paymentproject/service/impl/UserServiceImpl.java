package com.example.paymentproject.service.impl;

import com.example.paymentproject.dao.impl.UserDaoImpl;
import com.example.paymentproject.entity.User;
import com.example.paymentproject.service.interfaces.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoImpl userDaoImpl = new UserDaoImpl();

    @Override
    public User createUser(User user) {
        return userDaoImpl.insertUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDaoImpl.deleteUser(user);
    }

    @Override
    public User getUserInfo(String login) {
        return userDaoImpl.getUser(login);
    }

    public boolean checkExistForUser(User user) {
        return userDaoImpl.checkExistForUser(user);
    }

    @Override
    public boolean loginCheck(String login, String password) throws SQLException {
        return userDaoImpl.checkPassLogin(login, password);
    }

    @Override
    public List<User> findUsers() {
        return userDaoImpl.findAllUsers();
    }

    @Override
    public User getUserById(int userID) {
        return  userDaoImpl.getUserById(userID);
    }
}
