
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me_me.DBConnection;
/**
 *
 * @author jokip
 */
@WebServlet("/createaccount") 
public class InsertData extends HttpServlet {
    private static final long serialVersionUID = 1L; 
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
         try { 
  
            Connection con = DBConnection.DatabaseConnect(); 
  
            PreparedStatement st; 
             st = con.prepareStatement("insert into user(Name, Username, Email, Password) values(?, ?, ?, ?)");
 
            st.setString(1, request.getParameter("full_name"));
            st.setString(2, request.getParameter("user_name"));
            st.setString(3, request.getParameter("email_address"));
            st.setString(4, request.getParameter("pass_word"));
            st.execute(); 
  
            // Close all the connections 
            st.close(); 
            con.close(); 
  
            response.sendRedirect("login.html?ua=t");
            
    } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    }

}
