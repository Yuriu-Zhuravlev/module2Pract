package com.module.dao;

import com.module.entry.Question;
import com.module.entry.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoConnection {
    private Connection connection;
    private void connect(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection= DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","admin");
            connection.setAutoCommit(true);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void disconnect(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public User getUser(String name){
        connect();
        User result = null;
        try {
            PreparedStatement getUserStatement = connection.prepareStatement("SELECT MAX_TOTAL_POINTS FROM USERS WHERE NAME = ?");
            getUserStatement.setString(1,name);
            ResultSet resultSet = getUserStatement.executeQuery();
            if (resultSet.next()){
                result = new User(name,resultSet.getInt(1));
            } else {
                PreparedStatement insertUser = connection.prepareStatement("INSERT INTO USERS(NAME, MAX_TOTAL_POINTS) " +
                        "values (?,0)");
                insertUser.setString(1,name);
                insertUser.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disconnect();
        return result;
    }

    public List<Question> getQuestions(){
        connect();
        List<Question> questions = new ArrayList<>(3);
        int first = (int) (Math.random() * 9) + 1;
        int second;
        do {
            second = (int) (Math.random() * 9) + 1;
        }while (second != first);
        int third;
        do {
            third = (int) (Math.random() * 9) + 1;
        }while (third != first && third != second);
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT QUESTION, ANSWER, POINTS FROM QUESTIONS WHERE ID in (?,?,?)");
            statement.setInt(1,first);
            statement.setInt(2,second);
            statement.setInt(3,third);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                questions.add(new Question(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disconnect();
        return questions;
    }
}
