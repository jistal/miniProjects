import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseHelper {


    public void test() throws SQLException {

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/contact_book",
                "root",
                "password");

        String createTable = "CREATE TABLE contacts (name VARCHAR(50), phone VARCHAR(15), email VARCHAR(50))";

        String sql = "INSERT INTO contacts (name, phone, email) VALUES ('John Doe', '123467890', 'joh@email.com')";

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);


    }
}
