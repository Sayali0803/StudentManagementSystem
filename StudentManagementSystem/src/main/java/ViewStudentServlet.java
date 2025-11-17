
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewStudentServlet")
public class ViewStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Student Records</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; text-align: center; }");
        out.println("h2 { color: #333; margin-top: 20px; }");
        out.println("table { margin: 30px auto; border-collapse: collapse; width: 80%; background-color: white; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }");
        out.println("th, td { padding: 12px 15px; text-align: center; font-size: 16px; border: 1px solid #ddd; }");
        out.println("th { background-color: #ffb88c; color: #333; font-size: 18px; }"); // peach header
        out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
        out.println("tr:hover { background-color: #ffe0cc; transition: 0.3s; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>📋 Student Records</h2>");
        out.println("<table>");
        out.println("<tr><th>ID</th><th>Name</th><th>Email</th><th>Course</th></tr>");

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM student");

            while (rs.next()) {
                out.println("<tr>");
                out.print("<td>" + rs.getInt("id") + "</td>");
                out.print("<td>" + rs.getString("name") + "</td>");
                out.print("<td>" + rs.getString("email") + "</td>");
                out.print("<td>" + rs.getString("course") + "</td>");
                out.println("</tr>");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
