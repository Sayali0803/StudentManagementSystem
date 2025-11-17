import java.sql.Connection;
import java.sql.DriverManager;

public class TestDBConnection {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/studentdb", "admin", "Pass@100123"
            );

            if (con != null) {
                System.out.println("✅ Database connected successfully!");
            } else {
                System.out.println("❌ Database connection failed!");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
