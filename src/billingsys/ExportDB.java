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
public class ExportDB extends javax.swing.JFrame {
    Connection conn=null;
PreparedStatement pst=null;
ResultSet rs=null;
        String str=Datetime.date();
        
    /**
     * Creates new form ExportDB
     */
    public ExportDB() {
        initComponents();
    }
    public void S(){
        String file="Reports\\SALE_REPORT_"+str+".csv";
        conn=DBConnect.ConnectDB();
        String Sql="Select * from Sale ";
        try{
            FileWriter fw = new  FileWriter(file); 
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
            String Header="SLNo,BillNo,Date,Time,Item,Model,Total,Customer,Company,Contact,Info\n";
            fw.append(Header);
            int c=0;
            while(rs.next()){
                c++;
                 fw.append(String.valueOf(c));
                 fw.append(',');
                 fw.append(rs.getString(8));
                 fw.append(',');
                 fw.append(rs.getString(9));
                 fw.append(',');
                 fw.append(rs.getString(10));
                 fw.append(',');
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
                 fw.append('\n');
                
            
            }
            fw.flush();
            fw.close();
            JOptionPane.showMessageDialog(null, "Sale Report Exported");conn.close();
        }
        catch(Exception e){
       JOptionPane.showMessageDialog(null, e);
       
   }   
    
        
        
    }
    public void P(){
        String file="Reports\\PURCHASE_REPORT_"+str+".csv";
        
        conn=DBConnect.ConnectDB();
        String Sql="Select * from Purchase ";
        try{
            FileWriter fw = new  FileWriter(file); 
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
            String Header="SLNo,BillNo,Date,Time,Item,Model,Rate,Quantity,Total,Company\n";
            fw.append(Header);
            int c=0;
            while(rs.next()){
                c++;
                 fw.append(String.valueOf(c));
                 fw.append(',');
                 fw.append(rs.getString(7));
                 fw.append(',');
                 fw.append(rs.getString(6));
                 fw.append(',');
                 fw.append(rs.getString(8));
                 fw.append(',');
                 fw.append(rs.getString(1));
                 fw.append(',');
                 fw.append(rs.getString(2));
                 fw.append(',');
                 fw.append(rs.getString(4));
                 fw.append(',');
                 fw.append(rs.getString(5));
                 fw.append(',');
                 fw.append(String.valueOf(Float.valueOf(rs.getString(4))*Float.valueOf(rs.getString(5))));
                 fw.append(',');
                
                 fw.append(rs.getString(3));
                 fw.append('\n');
                
            
            }
            fw.flush();
            fw.close();
            JOptionPane.showMessageDialog(null, "Purchase Report Exported");conn.close();
        }
        catch(Exception e){
       JOptionPane.showMessageDialog(null, e);
        
    }
    }
    public void T(){
        
        String file="Reports\\TRANSACTION_REPORT_"+str+".csv";
        
        conn=DBConnect.ConnectDB();
        String Sql="Select * from Excange ";
        try{
            FileWriter fw = new  FileWriter(file); 
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
            String Header="SLNo,BillNo,Date,Time,Item,Model,Rate,Quantity,Total,Company,Type\n";
            fw.append(Header);
            int c=0;
            while(rs.next()){
                c++;
                 fw.append(String.valueOf(c));
                 fw.append(',');
                 fw.append(rs.getString(10));
                 fw.append(',');
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
                 fw.append(rs.getString(9));
                 fw.append(',');
                 fw.append(rs.getString(8));
                 fw.append('\n');
                
            
            }
            fw.flush();
            fw.close();
            JOptionPane.showMessageDialog(null, "Transaction Report Exported");conn.close();
        }
        catch(Exception e){
       JOptionPane.showMessageDialog(null, e);
        
    }
        
    }
     public void C(){
        
        String file="Reports\\PAYMENT_REPORT_"+str+".csv";
        
        conn=DBConnect.ConnectDB();
        String Sql="Select * from Payment ";
        try{
            FileWriter fw = new  FileWriter(file); 
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
            String Header="SLNo,BillNo,BillDate,Customer,Contact,Address,Total,Collect,Due\n";
            fw.append(Header);
            int c=0;
            while(rs.next()){
                c++;
                 fw.append(String.valueOf(c));
                 fw.append(',');
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
                 fw.append('\n');
                
            
            }
            fw.flush();
            fw.close();
            JOptionPane.showMessageDialog(null, "Payment Report Exported");conn.close();
        }
        catch(Exception e){
       JOptionPane.showMessageDialog(null, e);
        
    }
        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Export Databse", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Impact", 0, 18))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sale Report", "Purchase Report", "Transaction Report", "Payment", "All Reports" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 160, 28));

        jLabel2.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel2.setText("Select Type :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 36, -1, -1));

        jButton1.setText("Export");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 100, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        if(jComboBox1.getSelectedItem()=="Sale Report" ){
                    S();
                }
       if(jComboBox1.getSelectedItem()=="Purchase Report" ){
                   P();
                }
       if(jComboBox1.getSelectedItem()== "Transaction Report" ){
                    T();
                }
       if(jComboBox1.getSelectedItem()== "Payment" ){
                   C();
                }
       if(jComboBox1.getSelectedItem()== "All Reports" ){
                   S();P(); T();C();
                }
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ExportDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExportDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExportDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExportDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExportDB().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
