package Dao;

import model.Slot;
import utils.DBconnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SlotDao {

public void addSlot(Slot slot) throws SQLException {
    String sql = "INSERT INTO slots (time, category_id) VALUES (?, ?)";

    PreparedStatement ps = DBconnection.getConnection().prepareStatement(sql);
    ps.setString(1, slot.getTime());
    ps.setInt(2, slot.getCategoryID());
    ps.executeUpdate();
    ps.close();
}




}
