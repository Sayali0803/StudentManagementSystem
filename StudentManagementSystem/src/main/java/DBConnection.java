import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/studentdb", 
                "admin",         // MySQL DB user
                "Pass@100123"    // MySQL DB password
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
