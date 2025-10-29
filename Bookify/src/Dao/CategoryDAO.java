package Dao;

import model.Category;
import utils.DBconnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO {



    public void addNewCategory(Category category) throws SQLException {
        String sql = "INSERT INTO categories (name) VALUES (?)" ;

        PreparedStatement ps = DBconnection.getConnection().prepareStatement(sql);
        ps.setString(1, category.getName());
        ps.executeUpdate();
        ps.close();


    }


    public ArrayList<String> displayCategory() throws SQLException{
        ArrayList<String> categories = new ArrayList<>();

        String sql = "SELECT name FROM categories";
        PreparedStatement ps = DBconnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
          categories.add( rs.getString("name"));
        }
        return categories;
    }

    public void deleteCategory(Category category) throws SQLException {

        String sql = "DELETE FROM categories WHERE name = ?";
        PreparedStatement ps = DBconnection.getConnection().prepareStatement(sql);
        ps.setString(1, category.getName());
        ps.executeUpdate();
        ps.close();

    }


    // needed to assign to slots
    public int getCategoryID(Category category) throws SQLException{

        String sql = "SELECT id FROM categories WHERE name = ?";
        PreparedStatement ps = DBconnection.getConnection().prepareStatement(sql);
        ps.setString(1, category.getName());
        ResultSet rs = ps.executeQuery();

        int id = -1;
        if (rs.next()) id = rs.getInt("id");

        ps.close();
        rs.close();

        return id;
    }




}
