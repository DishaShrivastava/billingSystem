/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billingsys;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sam
 */
public class Stockorder extends javax.swing.JFrame {

    /**
     * Creates new form Stockorder
     */
    Connection conn=null;
PreparedStatement pst=null;
ResultSet rs=null;
    public Stockorder() {
        initComponents();
    }
public void print(){
    
    String str="StockReport\\"+jTextField1.getText()+"_"+Datetime.date()+".pdf";
        Getinfo gi=new Getinfo();
        //str=getpath();
        try{
        Document document=new Document();
       // Rectangle rect = new Rectangle(210,297);
        //document.setPageSize(rect);
        PdfWriter.getInstance(document, new FileOutputStream(str));
        document.open();
            Paragraph title=new Paragraph("REPORT  ".toUpperCase(),FontFactory.getFont(FontFactory.COURIER,10,Font.UNDERLINE));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
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
    
    
    
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Stock Order Report", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Impact", 0, 12))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel4.setText("Company :");

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SlNo", "Item", "Model", "Company", "Stock"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 24, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
String comp=jTextField1.getText(); 
String item="",mod="";
 conn=DBConnect.ConnectDB();
      //  DefaultTableModel model=null;
        //model =(DefaultTableModel)jTable1.getModel();
        String Sql="Select * from ItemInfo where Company=? ";
        try{
            pst=conn.prepareStatement(Sql);
             pst.setString(1,jTextField1.getText());
            rs=pst.executeQuery();
             // model.setRowCount(0);
             while(rs.next()){
                
                 item=item+rs.getString(1)+"}";
                 mod=mod+rs.getString(2)+"}";
            
             }
             conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }


String[] dataitm=item.split("}");
String[] datamod=mod.split("}");

conn=DBConnect.ConnectDB();
      DefaultTableModel model=null;int y=0;
        model =(DefaultTableModel)jTable1.getModel();
         Sql="Select * from ItemStock ";
        try{
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
               model.setRowCount(0);
             while(rs.next()){
                
                 for(int i=0;i<datamod.length;i++){
                                     if(rs.getString(1).equals(dataitm[i]) && rs.getString(2).equals(datamod[i])){
                                         
                                         y++;
                               model.addRow(new Object[]{"-",rs.getString(1),
                                                     rs.getString(2),comp,rs.getString(3)
                                                     
                                                     });
                      
                                         
                                         
                                     }

                 }
            
             }
             conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

if(y==0){
    JOptionPane.showMessageDialog(null,"No Records Found");
}







// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
print();        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(Stockorder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Stockorder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Stockorder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Stockorder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Stockorder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
