import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet  extends HttpServlet {
	protected void doPost(HttpServletRequest req,HttpServletResponse res ) throws IOException{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		int id=Integer.parseInt(req.getParameter("id"));
		out.println("<style>");
        out.println("body { background-color: #FFDAB9; font-family: Arial, sans-serif; margin: 0; height: 100vh; display: flex; justify-content: center; align-items: center; flex-direction: column; }");
        out.println("h3 { color: white; background-color: #e67a35; padding: 15px 30px; border-radius: 10px; box-shadow: 0 4px 8px rgba(0,0,0,0.2); }");
        out.println("a { text-decoration: none; color: white; background-color: #333; padding: 10px 20px; border-radius: 5px; margin-top: 20px; display: inline-block; }");
        out.println("a:hover { background-color: #555; }");
        out.println("</style>");
		try {
			Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("DELETE FROM student WHERE id=?");
			ps.setInt(1,id);
			int rows=ps.executeUpdate();
			out.println("<h3>Student with ID " + id + " Deleted.</h3> ");
			con.close();
		}
		catch(Exception e) {
			out.println("<h3>NO Student found with ID " + id + ".</h3>");
		}
		out.close();
	}

}
