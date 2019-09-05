/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import me_me.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jokip
 */
@WebServlet("/editprofile")
public class Profile extends HttpServlet {

    LoginAction login = new LoginAction();
    String emaile;
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int status = 0;
        System.out.println("Got Details");
        try {
            System.out.println("Got Details");
            emaile = login.emaili;
            Connection con = DBConnection.DatabaseConnect();

            PreparedStatement st;
            st = con.prepareStatement("UPDATE `user` SET `Name` =?, `Username` =?, `Password` =? WHERE `user`.`Email` =?");

            st.setString(1, request.getParameter("full_name"));
            st.setString(2, request.getParameter("user_name"));
            st.setString(3, request.getParameter("pass_word"));
            st.setString(4, request.getParameter("email_address"));

            st.execute();

                response.sendRedirect("editprofile.html?e=creds");
            
            st.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

}
