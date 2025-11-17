
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/UpdateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String course = req.getParameter("course");
        
      
        out.println("<html><head><title>Update Student</title>");
  
        out.println("<style>");
        out.println("body { background-color: #FFDAB9; font-family: Arial, sans-serif; margin: 0; height: 100vh; display: flex; justify-content: center; align-items: center; flex-direction: column; }");
        out.println("h2 { color: green; background-color: white; padding: 15px 30px; border-radius: 10px; box-shadow: 0 4px 8px rgba(0,0,0,0.2); }");
        out.println("a { text-decoration: none; color: white; background-color: #FF8C42; padding: 10px 20px; border-radius: 5px; margin-top: 20px; display: inline-block; }");
        out.println("a:hover { background-color: #e67a35; }");
        out.println("</style>");

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "UPDATE student SET name=?, email=?, course=? WHERE id=?"
            );
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, course);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                out.println("<h2>✅ Student Updated Successfully</h2>");
            } else {
                out.println("<h2 style='color:red;'>⚠ Student Not Found</h2>");
            }

            out.println("<br><a href='viewstudent.html'>View All Students</a>");
            con.close();
        } catch (Exception e) {
            out.println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
        }

        out.println("</body></html>");
        out.close();
    }
}
