/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jokip
 */
public class index extends HttpServlet {
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher view = request.getRequestDispatcher("indexx.jsp");
        // don't add your web-app name to the path

        view.forward(request, response); 
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
     /*    PrintWriter out = response.getWriter();  
       try{    
           String[] dirs={};
            Connection con = DBConnection.DatabaseConnect(); 
            PreparedStatement st;
            ResultSet rs;
             st = con.prepareStatement("SELECT * FROM meme");
 
            rs = st.executeQuery();
            String todoo="";
      
            int nm=0;
              while (rs.next()){
              String dir  = rs.getString("Pic_Vid");
              todoo="<script>$(\".gal\").append(\""+dir+"\"); </script>";
              out.println(todoo);
              
              dirs[nm]=dir;
              nm+=1;
              }
              
            st.close(); 
            con.close(); 
  
            
    } catch (Exception e) { 
            e.printStackTrace(); 
        } */
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }



}
