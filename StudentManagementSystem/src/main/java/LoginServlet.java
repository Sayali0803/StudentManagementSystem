import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();

        try {
            Connection con = DBConnection.getConnection(); // your DB connection

            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Login successful → redirect to dashboard.html
                HttpSession session = req.getSession();
                session.setAttribute("username", username); // store username if needed
                res.sendRedirect("dashboard.html"); // next page
            } else {
                // Login failed → show error on the same page
                out.println("<html><body>");
                out.println("<h3 style='color:red; text-align:center;'>Invalid Username or Password</h3>");
                out.println("<div style='text-align:center;'><a href='login.html'>Go Back to Login</a></div>");
                out.println("</body></html>");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<html><body>");
            out.println("<h3 style='color:red; text-align:center;'>Server Error! Try again later.</h3>");
            out.println("</body></html>");
        }

        out.close();
    }
}
