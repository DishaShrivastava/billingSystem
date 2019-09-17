/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billingsys;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Getinfo {
   Connection conn=null;
PreparedStatement pst=null;
ResultSet rs=null;
public String contName(){
        conn=DBConnect.ConnectDB();
        String Sql="Select * from Admin ";
        try{
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 
       
                 return (rs.getString(9));
            
            }
            conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            return "";
        }
        
         return "";
    }
public String compName(){
        conn=DBConnect.ConnectDB();
        String Sql="Select * from Admin ";
        try{
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 
       
                 return (rs.getString(2));
            
            }
            conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            return "";
        }
        
         return "";
    }
public String add1Name(){
        conn=DBConnect.ConnectDB();
        String Sql="Select * from Admin ";
        try{
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 
       
                 return (rs.getString(3));
            
            }
            conn.close();
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            return "";
        }
        
         return "";
    }
public String add2Name(){
        conn=DBConnect.ConnectDB();
        String Sql="Select * from Admin ";
        try{
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 
       
                 return (rs.getString(6));
            
            }
            conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            return "";
        }
        
         return "";
    }
public String pinName(){
        conn=DBConnect.ConnectDB();
        String Sql="Select * from Admin ";
        try{
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 
       
                 return (rs.getString(7));
            
            }
            conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            return "";
        }
        
         return "";
    }
public String AdvName(){
        conn=DBConnect.ConnectDB();
        String Sql="Select * from Admin ";
        try{
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 
       
                 return (rs.getString(8));
            
            }
           conn.close(); 
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            return "";
        }
        
         return "";
    }
}
