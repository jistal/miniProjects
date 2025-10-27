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

        if (rs.next()) isUsernameValid = true;
        return isUsernameValid;

    }


    public boolean isPasswordValid(User user) throws SQLException {

        boolean isPasswordValid = false;
        String checkIfPasswordIsValid = "SELECT password FROM users WHERE username = ?";

        PreparedStatement ps = DBconnection.getConnection().prepareStatement(checkIfPasswordIsValid);
        ps.setString(1, user.getPassword());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) isPasswordValid = true;
        return isPasswordValid;
    }

    public boolean isUserAdmin(User user) throws SQLException{
        boolean isUserAdmin = false;

        String checkIfUserIsAdmin = "SELECT role FROM users WHERE username = ?";

        PreparedStatement ps = DBconnection.getConnection().prepareStatement(checkIfUserIsAdmin);

        ps.setString(1, user.getUsername());
        ResultSet rs = ps.executeQuery();

        String userRole = rs.getString("role");

        return userRole.matches("ADMIN");
    }






}
