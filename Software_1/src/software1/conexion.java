///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package software1;
//
///**
// *
// * @author Angelina
// */
//import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class conexion{
    public Connection getconection() throws Exception{
        try{
            String connectionUrl = "jdbc:sqlserver://localhost:1433;"
                    + "databasename=proceso_tratamiento;"
                    + "user=angelina;"
                    + "password= chatbot;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            Class.forName("com.microsoft.sqlserver.jdbc.");

            return (DriverManager.getConnection(connectionUrl));
            
            
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    public ResultSet listar(String sql){
        Statement st = null;
        ResultSet rs = null;
        
        try{
            Connection conn = this.getconection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return rs;
    }
}
//
//public class conexion {
//    // Connect to your database.
//    // Replace server name, username, and password with your credentials
//    public static void main(String[] args) {
//        try{
//            String connectionUrl = "jdbc:sqlserver://DESKTOP-HM82LV0:1433;"
//                    + "databasename=proceso_tratamiento;"
//                    + "user=DESKTOP-HM82LV0'\'Angelina;";
////                    + "password=;";
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
////            Class.forName('');
//            
//            return (DriverManager.getConnection(connectionUrl));
//            
//
//        } 
//        catch (SQLException e) {
//            JOptionpane.showMessageDialog(null, e);
//        }
//        return null;
//    }
//}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

