/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billingsys;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author sam
 */
public class DBtoCSV {
     Connection conn=null;
PreparedStatement pst=null;
ResultSet rs=null;

    public void export(){
        String str=Datetime.date();
        String file="C:\\Users\\sam\\Desktop\\Project2018\\DataBase\\SALE_REPORT_"+str+".csv";
        conn=DBConnect.ConnectDB();
        String Sql="Select * from Sale ";
        try{
            FileWriter fw = new  FileWriter(file); 
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
            
            while(rs.next()){
                 fw.append(rs.getString(1));
                 fw.append(',');
                 fw.append(rs.getString(2));
                 fw.append(',');
                 fw.append(rs.getString(3));
                 fw.append(',');
                 fw.append(rs.getString(4));
                 fw.append(',');
                 fw.append(rs.getString(5));
                 fw.append(',');
                 fw.append(rs.getString(6));
                 fw.append(',');
                 fw.append(rs.getString(7));
                 fw.append(',');
                 fw.append(rs.getString(8));
                 fw.append(',');
                 fw.append(rs.getString(9));
                 fw.append(',');
                 fw.append(rs.getString(10));
                 fw.append('\n');
                
            
            }
            fw.flush();
            fw.close();
            JOptionPane.showMessageDialog(null, "Successfully Exported");
            conn.close();
        }
        catch(Exception e){
       JOptionPane.showMessageDialog(null, e);
       
   }   
    }
}
