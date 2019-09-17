/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billingsys;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import javafx.scene.layout.Border;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

/**
 *
 * @author sam
 */
public class ViewRecord extends javax.swing.JFrame {

    Connection conn=null;
PreparedStatement pst=null;
ResultSet rs=null;
    public ViewRecord() {
        initComponents();
        jTable0("");
    }
   
   public void jTable0(String str){
         conn=DBConnect.ConnectDB();
        DefaultTableModel model=null;
        model =(DefaultTableModel)jTable1.getModel();
         
        String Sql="Select * from ItemInfo where Company like '%"+str+"%'";
        try{
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
              model.setRowCount(0);
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));
             conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void jTable1(String str){
         conn=DBConnect.ConnectDB();
        DefaultTableModel model=null;
        model =(DefaultTableModel)jTable1.getModel();
         
        String Sql="Select * from ItemStock where Item like '%"+str+"%' or Model like '%"+str+"%' or Quantity like '%"+str+"%'";
        try{
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
              model.setRowCount(0);
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));
             conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void jTable2(String str){
         conn=DBConnect.ConnectDB();
        DefaultTableModel model=null;
        model =(DefaultTableModel)jTable1.getModel();
         
        String Sql="Select * from Purchase where Date like '%"+str+"%'";
        try{
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
              model.setRowCount(0);
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));
             conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void jTable3(String str){
         conn=DBConnect.ConnectDB();
        DefaultTableModel model=null;
        model =(DefaultTableModel)jTable1.getModel();
         
        String Sql="Select * from Sale where Date like '%"+str+"%'";
        try{
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
              model.setRowCount(0);
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));
             conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "View Record", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Impact", 0, 18))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jTable1.setSelectionBackground(new java.awt.Color(0, 255, 51));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 690, 440));

        jLabel1.setFont(new java.awt.Font("Liberation Mono", 1, 14)); // NOI18N
        jLabel1.setText("Select Date : ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item Details", "Stock Details", "Purchase Orders", "Sale Orders" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 170, 30));

        jButton1.setText("Show");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 70, 40));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 170, 30));

        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, 70, 40));

        jLabel2.setFont(new java.awt.Font("Liberation Mono", 1, 14)); // NOI18N
        jLabel2.setText("Select Record : ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String Choice[]={"Item Details","Stock Details","Purchase Orders","Sale Orders"};
        if(jComboBox1.getSelectedItem()==Choice[0] ){
                  jTable0("");  
                }
       if(jComboBox1.getSelectedItem()==Choice[1] ){
           jTable1("");  
                         }
      if(jComboBox1.getSelectedItem()==Choice[2] ){
                   jTable2("");   
                }
     if(jComboBox1.getSelectedItem()==Choice[3] ){
                  jTable3("");    
                }
      
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        String Choice[]={"Item Details","Stock Details","Purchase Orders","Sale Orders"};
        if(jComboBox1.getSelectedItem()==Choice[0] ){
                  jTable0(jTextField1.getText());  
                }
       if(jComboBox1.getSelectedItem()==Choice[1] ){
           jTable1(jTextField1.getText());
                         }
      if(jComboBox1.getSelectedItem()==Choice[2] ){
          jTable2(jTextField1.getText());
                    
                }
     if(jComboBox1.getSelectedItem()==Choice[3] ){
                    jTable3(jTextField1.getText());
                }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String str="Records\\"+jTextField1.getText()+"_"+jComboBox1.getSelectedItem().toString()+"_"+Datetime.date()+".pdf";
        Getinfo gi=new Getinfo();
        //str=getpath();
        try{
        Document document=new Document();
       // Rectangle rect = new Rectangle(210,297);
        //document.setPageSize(rect);
        PdfWriter.getInstance(document, new FileOutputStream(str));
        document.open();
            Paragraph title=new Paragraph("REPORT : "+jComboBox1.getSelectedItem().toString().toUpperCase(),FontFactory.getFont(FontFactory.COURIER,10,Font.UNDERLINE));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
         Paragraph company=new Paragraph(gi.compName(),FontFactory.getFont(FontFactory.COURIER,16,Font.BOLD));
        company.setAlignment(Element.ALIGN_CENTER);
        document.add(company);
        Paragraph add1=new Paragraph(gi.add1Name(),FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD));
        add1.setAlignment(Element.ALIGN_CENTER);
        document.add(add1);
        Paragraph add2=new Paragraph(gi.add2Name(),FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD));
        add2.setAlignment(Element.ALIGN_CENTER);
        document.add(add2);
        Paragraph date=new Paragraph("\n\n");
        date.setAlignment(Element.ALIGN_RIGHT);
        document.add(date); 
       date=new Paragraph("Date "+Datetime.date()+" IST "+Datetime.time(),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD));
        date.setAlignment(Element.ALIGN_RIGHT);
        document.add(date);
        date=new Paragraph("\n\n");
        date.setAlignment(Element.ALIGN_RIGHT);
        document.add(date);         

        PdfPTable table =new PdfPTable(jTable1.getColumnCount());
         table.setWidthPercentage(100);
         
                for(int i=0;i<jTable1.getColumnCount();i++){
            
            PdfPCell cell =new PdfPCell(new Paragraph(""+jTable1.getColumnName(i),FontFactory.getFont(FontFactory.COURIER,16,Font.BOLD)));
       
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.DARK_GRAY);
        cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT |Rectangle.TOP);
        table.addCell(cell);
        }
        
        for(int i=0;i<jTable1.getRowCount();i++){
            for(int k=0;k<jTable1.getColumnCount();k++){
              
             PdfPCell cell =new PdfPCell(new Paragraph(""+jTable1.getValueAt(i, k),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
       
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT  );
        if(i==jTable1.getRowCount()-1){
                       cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT |Rectangle.BOTTOM );
                   }
        table.addCell(cell);
            }
        }
        document.add(table);
        document.close();
        JOptionPane.showMessageDialog(null,"Saved");
        if(Desktop.isDesktopSupported()){
                try{
                File f =new File(str);
                Desktop.getDesktop().open(f);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
            }
        }
         catch(Exception e){
            JOptionPane.showMessageDialog(null,e+"\nProblem Occured During Saving ");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(ViewRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewRecord().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
