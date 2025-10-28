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


    public boolean isUsernameValid(User user) throws SQLException {
        Boolean isUsernameValid = false;

        String checkIfUserExits = "SELECT username FROM users WHERE username = ?";
        PreparedStatement ps = DBconnection.getConnection().prepareStatement(checkIfUserExits);
        ps.setString(1, user.getUsername());
        ResultSet rs = ps.executeQuery();

        // check if username is found
        if (rs.next()) isUsernameValid = true;
        return isUsernameValid;
    }


    public boolean isPasswordValid(User user) throws SQLException {
        boolean isPasswordCorrect = false;

        // check password by username
        String checkIfPasswordIsValid = "SELECT password FROM users WHERE username = ?";
        PreparedStatement ps = DBconnection.getConnection().prepareStatement(checkIfPasswordIsValid);
        ps.setString(1, user.getUsername());
        ResultSet rs = ps.executeQuery();

        // check if password user entered matches password stored
        if (rs.next()){
            if (rs.getString("password").matches(user.getPassword()))
            isPasswordCorrect = true;
        }

        return isPasswordCorrect;
    }


    public boolean isUserAdmin(User user) throws SQLException{
        String userRole = null;

        // get role by checking their username
        String checkIfUserIsAdmin = "SELECT role FROM users WHERE username = ?";
        PreparedStatement ps = DBconnection.getConnection().prepareStatement(checkIfUserIsAdmin);
        ps.setString(1, user.getUsername());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
           userRole  = rs.getString("role");
        }

        // returns true if role is admin
        return userRole.matches("ADMIN");
    }






}
