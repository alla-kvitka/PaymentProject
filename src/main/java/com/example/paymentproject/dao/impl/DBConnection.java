package com.example.paymentproject.dao.impl;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConnection {
    static DBConnection instance;
    public DataSource dataSource;

    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    private DBConnection() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup(
                    "java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/Payments");
        } catch (Throwable e) {
            System.out.println("error");
        }
    }


    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


    public void commitAndClose(Connection connection) {
        try {
            connection.commit();
            connection.close();
        } catch (SQLException e) {
        }
    }


    public void rollbackAndClose(Connection connection) {
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException e) {
        }
    }


    public void close(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
            }
        }
    }


    public void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
            }
        }
    }
}
