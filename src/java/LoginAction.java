import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import me_me.DBConnection;
/**
 *
 * @author jokip
 */
@WebServlet("/login") 
public class LoginAction extends HttpServlet {
    public String emaili;
    private static final long serialVersionUID = 1L; 
    index indexx = new index();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
         try { 
            emaili = request.getParameter("email_address");
            Connection con = DBConnection.DatabaseConnect(); 
            PreparedStatement st;
            ResultSet rs;
             st = con.prepareStatement("SELECT * FROM user WHERE Email = ? AND Password = ?");
 
            st.setString(1, request.getParameter("email_address"));
            st.setString(2, request.getParameter("pass_word"));
            rs = st.executeQuery();
            
              if(rs.next()) { 
                  indexx.processRequest(request, response);
               } else {
                  response.sendRedirect("login.html?e=creds");
               //JOptionPane.showMessageDialog(null, "Login Failed", "Please try again", JOptionPane.ERROR_MESSAGE);
               }
            // Close all the connections 
            st.close(); 
            con.close(); 
  
            
    } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    }

}
