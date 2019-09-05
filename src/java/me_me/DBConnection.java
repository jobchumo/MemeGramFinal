

package me_me;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author jokip
 */
public class DBConnection {
    public static Connection DatabaseConnect() throws SQLException, ClassNotFoundException {
        try{
             Class.forName("com.mysql.jdbc.Driver");
            String databaseURL = "jdbc:mysql://localhost:3306/memes"; 
            Connection con = DriverManager.getConnection(databaseURL, "root", "");
            
            return con;
        
        } catch(Exception ex){
        JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }
    
}
