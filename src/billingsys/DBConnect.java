package billingsys;


import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.*;
/**
 *
 * @author sam
 */
public class DBConnect {
    Connection conn=null;
    public static Connection ConnectDB(){
         try{           
       Class.forName("org.sqlite.JDBC"); // MySQL database connection
       Connection conn = DriverManager.getConnection("jdbc:sqlite:db\\NewDB.db" ,"sam","123");     
      // JOptionPane.showMessageDialog(null, "Database connected");
       return conn;
                  
   }
   catch(Exception e){
       JOptionPane.showMessageDialog(null, e);
       return null;
   }   
    }
}