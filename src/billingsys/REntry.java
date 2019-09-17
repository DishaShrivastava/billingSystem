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

/**
 *
 * @author sam
 */
public class REntry {
Connection conn=null;
PreparedStatement pst=null;
ResultSet rs=null;


    
    public  void P(){
        conn=DBConnect.ConnectDB();
                
        String Sql=" DELETE FROM purchase";
           try{
            pst=conn.prepareStatement(Sql);
            
            
            pst.execute();
               JOptionPane.showMessageDialog(null, "Done ");
             conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e+"Unable to Delete Record");
        } 
    }
    public void S(){
           conn=DBConnect.ConnectDB();
           String Sql=" DELETE FROM Sale";
           try{
            pst=conn.prepareStatement(Sql);
            
            
            pst.execute();
               JOptionPane.showMessageDialog(null, "Done ");
             conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e+"Unable to Delete Record");
        } 
        
        
        
    }
    public  void Itemstock(){
        conn=DBConnect.ConnectDB();
        String Sql=" DELETE FROM ItemStock";
           try{
            pst=conn.prepareStatement(Sql);
            
            
            pst.execute();
               JOptionPane.showMessageDialog(null, "Done ");
             conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e+"Unable to Delete Record");
        }   
        
        
        
    }
    public  void Transaction(){
        conn=DBConnect.ConnectDB();
        String Sql=" DELETE FROM Excange";
           try{
            pst=conn.prepareStatement(Sql);
            
            
            pst.execute();
               JOptionPane.showMessageDialog(null, "Done ");
             conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e+"Unable to Delete Record");
        }   
        
        
        
    }
        public  void Collection(){
        conn=DBConnect.ConnectDB();
        String Sql=" DELETE FROM Payment";
           try{
            pst=conn.prepareStatement(Sql);
            
            
            pst.execute();
               JOptionPane.showMessageDialog(null, "Done ");
             conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e+"Unable to Delete Record");
        }   
        
        
        
    }
        public  void ItemInfo(){
        conn=DBConnect.ConnectDB();
        String Sql=" DELETE FROM ItemInfo";
           try{
            pst=conn.prepareStatement(Sql);
            
            
            pst.execute();
               JOptionPane.showMessageDialog(null, "Done ");
             conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e+"Unable to Delete Record");
        }   
        
        
        
    }
    
    public  void Alldb(){
        int x=0;
        conn=DBConnect.ConnectDB();
        String Sql=" DELETE FROM purchase";
           try{
            pst=conn.prepareStatement(Sql);
            
            
            pst.execute();
               //JOptionPane.showMessageDialog(null, "Purchase Record Deleted ");
             conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e+"Unable to Delete Record");
        } 
           conn=DBConnect.ConnectDB();
             Sql=" DELETE FROM ItemInfo";
           try{
            pst=conn.prepareStatement(Sql);
            
            
            pst.execute();
              // JOptionPane.showMessageDialog(null, "Item Details Record Deleted ");
             conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e+"Unable to Delete Record");
        }  
           conn=DBConnect.ConnectDB(); 
           Sql=" DELETE FROM Sale";
           try{
            pst=conn.prepareStatement(Sql);
            
            
            pst.execute();
            // JOptionPane.showMessageDialog(null, "Sales Record Deleted  ");
             conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e+"Unable to Delete Record");
        }  
            conn=DBConnect.ConnectDB();
           Sql=" DELETE FROM ItemStock";
           try{
            pst=conn.prepareStatement(Sql);
            
            
            pst.execute();
              // JOptionPane.showMessageDialog(null, "ItemStock Records Deleted ");
             conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e+"Unable to Delete Record");
        }  
        
        Transaction();
        Collection();
        ItemInfo();
    }
    
    
    
}
