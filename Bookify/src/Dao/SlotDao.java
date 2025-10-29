package Dao;

import Model.Slot;
import utils.DBconnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SlotDao {

    public void addSlot(Slot slot) throws SQLException {
        String sql = "INSERT INTO slots (slot, category_id, username) VALUES (?, ?, ?)";

        try (PreparedStatement ps = DBconnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, slot.getSlot());
            ps.setInt(2, slot.getCategoryID());
            ps.setString(3, slot.getUsername());
            ps.executeUpdate();
        }
    }


    public ArrayList<String> viewAllSlots(int categoryID) throws SQLException {
        ArrayList<String> slots = new ArrayList<>();
        String sql = "SELECT slot FROM slots WHERE category_id = ?";

        try(PreparedStatement ps = DBconnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, categoryID);
            try (ResultSet rs = ps.executeQuery();) {

                while (rs.next()) {
                    slots.add(rs.getString("slot"));
                }
            }
        }
        return slots;
    }

    public void deleteSlot(Slot slot) throws SQLException{
        String sql = "DELETE FROM slots WHERE category_id = ? AND slot = ?";

        try(PreparedStatement ps = DBconnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, slot.getCategoryID());
            ps.setString(2, slot.getSlot());
            ps.executeUpdate();
        }
    }


    // CHECKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK
    public Boolean checkSlotAvailability(int categoryID) throws SQLException {
        Boolean isBooked = false;
        String sql = "SELECT is_booked FROM slots WHERE category_id = ?";

        try(PreparedStatement ps = DBconnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, categoryID);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) isBooked = rs.getBoolean("is_booked");
            }
        }
        return isBooked;
    }


    public void bookSlot(String username, String slot, int categoryID) throws SQLException{
        String sql = "UPDATE slots SET is_booked = true, username = ? WHERE slot = ? AND category_id = ?";

        try(PreparedStatement ps = DBconnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, slot);
            ps.setInt(3, categoryID);
            ps.executeUpdate();
        }
    }
}
