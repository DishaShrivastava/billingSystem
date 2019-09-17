/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package billingsys;

import com.sun.glass.events.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author sam
 */
public class NewEntry extends javax.swing.JFrame {

    
    Connection connn=null;
PreparedStatement pst=null;
ResultSet rs=null;
    public NewEntry() {
        initComponents();
        jTable0();
    }
    
       public void jTable0(){
         connn=DBConnect.ConnectDB();
        DefaultTableModel model=null;
        model =(DefaultTableModel)jTable1.getModel();
         String itm=jTextField1.getText(),mod=jTextField2.getText();
        String Sql="Select * from ItemInfo where Item like '%"+itm+"%' or Model like '%"+mod+"%'";
        try{
            pst=connn.prepareStatement(Sql);
            rs=pst.executeQuery();
              model.setRowCount(0);
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));
             connn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
public void addit(){
    if("".equals(jTextField1.getText()) ||
                "".equals(jTextField2.getText()) ||
                "".equals(jTextField3.getText()) ||
               
                "".equals(jTextField6.getText())){
            
        JOptionPane.showMessageDialog(null,"All Entry Must be Filled");
    }
        else{
             int in= JOptionPane.showConfirmDialog(null, "Are you sure ?");
        if(in==0){
        
                  connn=DBConnect.ConnectDB();int c=0;
                    String comp="",hsn="",gst="",rate="";
                    String Sql="Select * from ItemInfo where  Item=? and Model=? ";
                    try{
                        pst=connn.prepareStatement(Sql);
                        pst.setString(1,jTextField1.getText());
                        pst.setString(2,jTextField2.getText());
                        rs=pst.executeQuery();
                        while(rs.next()){

                            c++;
                            comp=rs.getString("Company");
                            hsn=rs.getString("HSN");
                            gst=rs.getString("GST");
                            rate=rs.getString("Rate");

                        }
                        connn.close();
                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null,e);
                    }
                    if(c>0){
                        JOptionPane.showMessageDialog(null,"Item Already Exists");
                        jTextField3.setText(comp);
                        jTextField4.setText(hsn);
                       
                        jTextField6.setText(rate);
                    
                         
                         
                    }
                    else{
                        
                      connn=DBConnect.ConnectDB();
                        Sql=" insert into ItemInfo (Item,Model,Company,HSN,GST,Rate)" +" values (?, ?,?,?,?,?) ";
                          try{

                           pst=connn.prepareStatement(Sql);
                           pst.setString(1,jTextField1.getText());
                           pst.setString(2,jTextField2.getText());
                           pst.setString(3,jTextField3.getText());
                           pst.setString(4,jTextField4.getText());
                           pst.setString(5,String.valueOf(jComboBox1.getSelectedItem()));
                           pst.setString(6,jTextField6.getText());



                           pst.execute();
                             JOptionPane.showMessageDialog(null, "Item Added");
                              connn.close();

                       }
                       catch(Exception e){
                           JOptionPane.showMessageDialog(null,e);
                       }  
                        
                          //stock
                        connn=DBConnect.ConnectDB();
                          Sql="insert into  ItemStock  (Item,Model,Quantity)" +" values (?, ?,?)   ";
                                      try{
                                          pst=connn.prepareStatement(Sql);

                                          pst.setString(1,jTextField1.getText());
                                          pst.setString(2,jTextField2.getText());
                                          pst.setString(3,String.valueOf(0));
                                          pst.executeUpdate();

                                            JOptionPane.showMessageDialog(null,"Stock Added");
                                             connn.close();
                                      }
                                      catch(Exception e){
                                          JOptionPane.showMessageDialog(null,e);
                                      }

                        jTextField3.setText("");
                        jTextField4.setText("");
                       
                        jTextField6.setText(""); 
                        jTextField1.setText("");
                        jTextField2.setText("");
                         
                    }
                    
              jTable0();
                    
            }
        }
}
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Item Entry", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Impact", 0, 18))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel2.setText("Item :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel3.setText("Model No:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel4.setText("Company Name :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel5.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel5.setText("HSN :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 202, -1, -1));

        jLabel6.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel6.setText("GST :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jLabel7.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel7.setText("%");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 10, 20));

        jButton1.setText("Add Item");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 95, 40));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 130, 30));

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 130, 30));

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 130, 30));

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 130, 30));

        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
        });
        jPanel1.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 130, 30));

        jButton2.setText("Update ");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 480, 100, 40));

        jButton3.setText("Remove");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 95, 40));

        jButton4.setText("Details");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 420, 100, 40));

        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 250, 20));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "18" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 60, 30));

        jLabel8.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel8.setText("INR");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 30, 20));

        jLabel9.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel9.setText("Sale Price :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
              // TODO add your handling code here:
   
        addit();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         JOptionPane.showMessageDialog(null, "Only HSN,GST,Rate can be updated...");
         int in= JOptionPane.showConfirmDialog(null, "Are you sure ?");
        if(in==0){
        
      connn=DBConnect.ConnectDB();
        String Sql="update ItemInfo set HSN=?,GST=?,Rate=? where Item=? and Model=? and Company=? ";
        try{
            pst=connn.prepareStatement(Sql);
            pst.setString(1,jTextField4.getText());
            pst.setString(2,String.valueOf(jComboBox1.getSelectedItem()));
            pst.setString(3,jTextField6.getText());
            pst.setString(4,jTextField1.getText());
            pst.setString(5,jTextField2.getText());
            pst.setString(6,jTextField3.getText());
           
            pst.executeUpdate();
          JOptionPane.showMessageDialog(null,"Updated");
          
           connn.close();
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int in= JOptionPane.showConfirmDialog(null, "Are you sure ?");
        if(in==0){
         
      connn=DBConnect.ConnectDB();
        String Sql="delete from ItemInfo  where Item=? and Model=? and Company=? ";
        try{
            pst=connn.prepareStatement(Sql);
            
            pst.setString(1,jTextField1.getText());
            pst.setString(2,jTextField2.getText());
            pst.setString(3,jTextField3.getText());
             
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Item Removed");
           connn.close();
           
          
            
            //stock
              connn=DBConnect.ConnectDB();
              Sql="delete from ItemStock   where Item=? and Model=? ";
                          try{
                              pst=connn.prepareStatement(Sql);
                              
                              pst.setString(1,jTextField1.getText());
                              pst.setString(2,jTextField2.getText());
                              pst.executeUpdate();
                            
                               connn.close();
                          }
                          catch(Exception e){
                              JOptionPane.showMessageDialog(null,e);
                          }
            JOptionPane.showMessageDialog(null,"Stock Removed");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
            jTextField3.setText("");
            jTextField4.setText("");
            
            jTextField6.setText(""); 
            jTextField1.setText("");
            jTextField2.setText("");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        ItemDetails x=new ItemDetails();
        x.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
 jTextField1.setText((String)jTable1.getValueAt(jTable1.getSelectedRow(), 0));
  jTextField2.setText((String)jTable1.getValueAt(jTable1.getSelectedRow(), 1));
 jTextField3.setText((String)jTable1.getValueAt(jTable1.getSelectedRow(), 2));
 jTextField4.setText((String)jTable1.getValueAt(jTable1.getSelectedRow(), 3));
 double r=(double)jTable1.getValueAt(jTable1.getSelectedRow(),5);
 float x=(float)r;
 jTextField6.setText(String.valueOf(x));
 // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           
            
            jTextField2.requestFocus();
        }         // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           
            
            jTextField3.requestFocus();
        }         // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           
            
            jTextField4.requestFocus();
        }         // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           
            
            jTextField6.requestFocus();
        }         // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           
            
            jButton1.requestFocus();
        }         // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6KeyPressed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           
            
            addit();
            jTextField1.requestFocus();
        }
 // TODO add your handling code here:
    }//GEN-LAST:event_jButton1KeyPressed

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
            java.util.logging.Logger.getLogger(NewEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewEntry().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables

}
