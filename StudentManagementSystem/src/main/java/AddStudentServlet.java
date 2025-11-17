
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String course = req.getParameter("course");

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO student (id, name, email, course) VALUES (?, ?, ?, ?)");
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, course);
            int rows = ps.executeUpdate();

            // 🌸 Stylish success message
            out.println("<!DOCTYPE html>");
            out.println("<html><head><meta charset='UTF-8'><title>Success</title>");
            out.println("<style>");
            out.println("body { background-color: #ffe5b4; font-family: Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
            out.println(".box { background-color: white; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.2); text-align: center; }");
            out.println("a { text-decoration: none; color: #333; margin-top: 10px; display: inline-block; }");
            out.println("</style></head><body>");
            out.println("<div class='box'>");
            out.println("<h2>✅ Student Inserted Successfully</h2>");
            out.println("<a href='dashboard.html'>Back to Dashboard</a>");
            out.println("</div></body></html>");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();

            // ❌ Error message
            out.println("<!DOCTYPE html>");
            out.println("<html><head><meta charset='UTF-8'><title>Error</title>");
            out.println("<style>");
            out.println("body { background-color: #ffd6d6; font-family: Arial; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
            out.println(".box { background-color: white; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.2); text-align: center; color: red; }");
            out.println("</style></head><body>");
            out.println("<div class='box'>");
            out.println("<h2>⚠️ Failed to Add Student</h2>");
            out.println("</div></body></html>");
        }

        out.close();
    }
}
