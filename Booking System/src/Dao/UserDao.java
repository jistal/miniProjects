package Dao;

import model.User;
import utils.DBconnection;

import java.sql.*;

public class UserDao {

    public void insertNewUser(User user) throws SQLException {

       String insertNewUser = "INSERT INTO users(username, password, role)" +
               "VALUES(?, ?, ?)";

      PreparedStatement ps = DBconnection.getConnection().prepareStatement(insertNewUser);

       ps.setString(1, user.getUsername());
       ps.setString(2, user.getPassword());
       ps.setString(3, user.getRole());
       ps.executeUpdate();
       ps.close();
    }
}
