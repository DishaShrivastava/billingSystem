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
import com.sun.glass.events.KeyEvent;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author sam
 */
public class Transaction extends javax.swing.JFrame {

    Connection conn=null;
PreparedStatement pst=null;
ResultSet rs=null;

    public Transaction() {
        initComponents(); Datetime d= new Datetime();
        jTable(""); jTextField8.setText(d.date()); jTable2();
       // cal();
    }
    
     public void printreport(){
        jTable("");
        String cash="",collect="",purchase="";
        
         for(int j=0;j<jTable1.getRowCount();j++){
                     
                      if( jTable1.getValueAt(j,7).toString().equals("Purchased") && jTable1.getValueAt(j,0).toString().equals(jTextField8.getText().trim())){
                         
                          purchase=purchase+jTable1.getValueAt(j,8)+"}"+jTable1.getValueAt(j,2)+"}"+jTable1.getValueAt(j,3)+"}"+jTable1.getValueAt(j,4)
                          +"}"+jTable1.getValueAt(j,5)+"}"+jTable1.getValueAt(j,6)+"}";
                          
                      }
         }
        for(int i=0;i<jTable2.getRowCount();i++){
            
            if(Float.valueOf(jTable2.getValueAt(i,7).toString())==0){
                int index =Integer.valueOf(jTable2.getValueAt(i,0).toString());
                 for(int j=0;j<jTable1.getRowCount();j++){
                     
                      if(Float.valueOf(jTable1.getValueAt(j,8).toString())==index && jTable1.getValueAt(j,7).toString().equals("Sold") && jTable1.getValueAt(j,0).toString().equals(jTextField8.getText().trim())){
                          System.out.println(jTable1.getValueAt(j,2)+" "+jTable1.getValueAt(j,3)+" "+jTable1.getValueAt(j,4)
                          +" "+jTable1.getValueAt(j,5)+" "+jTable1.getValueAt(j,6));
                          
                          
                          cash=cash+jTable1.getValueAt(j,8)+"}"+jTable1.getValueAt(j,2)+"}"+jTable1.getValueAt(j,3)+"}"+jTable1.getValueAt(j,4)
                          +"}"+jTable1.getValueAt(j,5)+"}"+jTable1.getValueAt(j,6)+"}";
                          
                      }
                 }
            
            }
            else{
                int index =Integer.valueOf(jTable2.getValueAt(i,0).toString());
                String a,b,c,d,e;a=""+jTable2.getValueAt(i,6);
                b=""+jTable2.getValueAt(i,7);
                c=""+jTable2.getValueAt(i,2);
                d=""+jTable2.getValueAt(i,3);
                e=""+jTable2.getValueAt(i,4);
                        
                a=a.equals("")?"NA":a;b=b.equals("")?"NA":b;c=c.equals("")?"NA":c;d=d.equals("")?"NA":d;e=e.equals("")?"NA":e;
                       
                 for(int j=0;j<jTable1.getRowCount();j++){
                     
                      if(Float.valueOf(jTable1.getValueAt(j,8).toString())==index && jTable1.getValueAt(j,7).toString().equals("Sold") && jTable1.getValueAt(j,0).toString().equals(jTextField8.getText().trim())){
                          System.out.println(jTable1.getValueAt(j,2)+" "+jTable1.getValueAt(j,3)+" "+jTable1.getValueAt(j,4)
                          +" "+jTable1.getValueAt(j,5)+" "+jTable1.getValueAt(j,6));
                          
                          
                          collect=collect+jTable1.getValueAt(j,8)+"}"+jTable1.getValueAt(j,2)+"}"+jTable1.getValueAt(j,3)+"}"+jTable1.getValueAt(j,4)
                          +"}"+jTable1.getValueAt(j,5)+"}"+jTable1.getValueAt(j,6)+"}"+
                                  a+"}"
                                  +b+"}"
                                  +c+"}"
                                  +d+"}"
                                  +e+"}";
                          
                      }
                 }
            }
        }
        
        
        /////
        String[] datacash=cash.split("}");
        String[] datacollect=collect.split("}");
         String[] datapur=purchase.split("}");
        /////
        
         String str="Records\\"+"DailyReport".toUpperCase()+"_"+jTextField8.getText()+".pdf";
        Getinfo gi=new Getinfo();
        //str=getpath();
        try{
        Document document=new Document();
       // Rectangle rect = new Rectangle(210,297);
        //document.setPageSize(rect);
        PdfWriter.getInstance(document, new FileOutputStream(str));
        document.open();
            Paragraph title=new Paragraph("REPORT : "+"DailyReport".toUpperCase(),FontFactory.getFont(FontFactory.COURIER,10,Font.UNDERLINE));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
         
        Paragraph date=new Paragraph("\n");
        date.setAlignment(Element.ALIGN_RIGHT);
        document.add(date); 
       date=new Paragraph("Date "+Datetime.date()+" IST "+Datetime.time(),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD));
        date.setAlignment(Element.ALIGN_RIGHT);
        document.add(date);
        date=new Paragraph("\n");
        date.setAlignment(Element.ALIGN_RIGHT);
        document.add(date); 
        date=new Paragraph("Net Balance Report :",FontFactory.getFont(FontFactory.COURIER,10,Font.UNDERLINE));
        date.setAlignment(Element.ALIGN_LEFT);
        document.add(date); 
 date=new Paragraph("\n",FontFactory.getFont(FontFactory.COURIER,10,Font.UNDERLINE));
        date.setAlignment(Element.ALIGN_LEFT);
        document.add(date);
        PdfPTable table =new PdfPTable(6);
         table.setWidthPercentage(100);
         String heading[]={"BILLNO","ITEM","MODEL","RATE","QUANTITY","NET BLALANCE"};
         
                for(int i=0;i<6;i++){
            
            PdfPCell cell =new PdfPCell(new Paragraph(heading[i],FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD)));
       
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.DARK_GRAY);
        cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT |Rectangle.TOP);
        table.addCell(cell);
        }
        
         
            for(int k=0;k<datacash.length;k++){
              
             PdfPCell cell =new PdfPCell(new Paragraph(""+datacash[k],FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
       
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT  );
        if(k>=datacash.length-6){
                       cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT |Rectangle.BOTTOM );
                   }
        table.addCell(cell);
            }
        if(datacash.length>0){
        document.add(table);
        }
        //////
         
        date=new Paragraph("Net Collection Report :",FontFactory.getFont(FontFactory.COURIER,10,Font.UNDERLINE));
        date.setAlignment(Element.ALIGN_LEFT);
        document.add(date); 
         date=new Paragraph("\n",FontFactory.getFont(FontFactory.COURIER,10,Font.UNDERLINE));
        date.setAlignment(Element.ALIGN_LEFT);
        document.add(date);

          table =new PdfPTable(11);
         table.setWidthPercentage(100);
          
         String h[]={"BILLNO","ITEM","MODEL","RATE","QUANTITY","NET BLALANCE","PAID","DUE","CUSTUMER","MOB","ADDRESS"};
                for(int i=0;i<11;i++){
            
            PdfPCell cell =new PdfPCell(new Paragraph(h[i],FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
       
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.DARK_GRAY);
        cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT |Rectangle.TOP);
        table.addCell(cell);
        }
        
         System.out.println(datacollect.length);
            for(int k=0;k<datacollect.length;k++){
              
             PdfPCell cell =new PdfPCell(new Paragraph(""+datacollect[k],FontFactory.getFont(FontFactory.COURIER,9,Font.BOLD)));
       
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT  );
        if(k>=datacollect.length-11){
                       cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT |Rectangle.BOTTOM );
                   }
        table.addCell(cell);
            }
        if(datacollect.length>0){
        document.add(table);
        }
        
        
        ///////
        date=new Paragraph("Net Purchase Report :",FontFactory.getFont(FontFactory.COURIER,10,Font.UNDERLINE));
        date.setAlignment(Element.ALIGN_LEFT);
        document.add(date); 
date=new Paragraph("\n",FontFactory.getFont(FontFactory.COURIER,10,Font.UNDERLINE));
        date.setAlignment(Element.ALIGN_LEFT);
        document.add(date); 
          table =new PdfPTable(6);
         table.setWidthPercentage(100);
         
        // String heading[]={"ITEM","MODEL","RATE","QUANTITY","NET BLALANCE"};
         
                for(int i=0;i<6;i++){
            
            PdfPCell cell =new PdfPCell(new Paragraph(heading[i],FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
       
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.DARK_GRAY);
        cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT |Rectangle.TOP);
        table.addCell(cell);
        }
        
         
            for(int k=0;k<datapur.length;k++){
              
             PdfPCell cell =new PdfPCell(new Paragraph(""+datapur[k],FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
       
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT  );
        if(k>=datapur.length-6){
                       cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT |Rectangle.BOTTOM );
                   }
        table.addCell(cell);
            }
        if(datapur.length>0){
        document.add(table);
        }
         
        
        
        ////////
        document.close();
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
        }        // TODO add your handling
        
        
        
    }
    public void cal(){
        Float in=(float)0;
        Float out=(float)0;
        Float pro=(float)0;
        
         for(int i=0;i<jTable1.getRowCount();i++){
             Float x=(float)0;
             if(jTable1.getValueAt(i,6).equals("")){
                 
             }else{
                 x=Float.valueOf(String.valueOf(jTable1.getValueAt(i,6)));
             }
                   
             
             if(null != jTable1.getValueAt(i,7).toString())switch (jTable1.getValueAt(i,7).toString()) {
                case "Purchased":
                    
                   // System.out.println(jTable1.getValueAt(i,7).toString());
                    out=out+x;
                    break;
                case "Collected":
                   // System.out.println(jTable1.getValueAt(i,7).toString());
                    in=in+Float.valueOf(String.valueOf(jTable1.getValueAt(i,6)));
                    break;
                
                default:
                    break;
            }
         }
         pro=in-out;
         jTextField5.setText(String.valueOf(in));
          jTextField6.setText(String.valueOf(out));
           jTextField4.setText(String.valueOf(pro));
         
    }
 public  void jTable(String str){
         conn=DBConnect.ConnectDB();
         DefaultTableModel model=null;
 model =(DefaultTableModel)jTable1.getModel();
    model.setRowCount(0);
        String Sql="Select  * from Excange where Model like '%"+str+"%'";
        try{
            
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
              while(rs.next()){
              model.addRow(new Object[]{rs.getString(1),rs.getString(2),
                                                     rs.getString(3),rs.getString(4),rs.getString(5),
                                                     rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(10)});
            }       
              
              
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
       cal();
    }
 public DefaultTableModel jTable1(){
         conn=DBConnect.ConnectDB();
        DefaultTableModel model=null;
         model =(DefaultTableModel)jTable1.getModel();
    model.setRowCount(0);
        String Sql="Select  * from Excange";
        try{
            
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
             while(rs.next()){
              model.addRow(new Object[]{rs.getString(1),rs.getString(2),
                                                     rs.getString(3),rs.getString(4),rs.getString(5),
                                                     rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(10)});
            }   
              
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return model;
    }
      public DefaultTableModel jTable2(){
        
         conn=DBConnect.ConnectDB();
        DefaultTableModel model=null;
        model =(DefaultTableModel)jTable2.getModel();
         model.setRowCount(0);
        String Sql="Select  * from Payment where BDate=?";
        try{
            
            pst=conn.prepareStatement(Sql);
            pst.setString(1,jTextField8.getText());
            rs=pst.executeQuery();
            while(rs.next()){
              model.addRow(new Object[]{rs.getString(1),rs.getString(2),
                                                     rs.getString(3),rs.getString(4),rs.getString(5),
                                                     rs.getString(6),rs.getString(7),rs.getString(8)});
            }
              
              
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return model;
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
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField8 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Transaction Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Impact", 0, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel1.setText("From");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Today", "This Month", "This Year" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox1KeyReleased(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 130, 30));

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 2));
        jTable1.setFont(new java.awt.Font("Liberation Mono", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Date", "Time", "Item", "Model", "Rate", "Quantity", "Total", "Type", "Bill"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 630, 350));

        jLabel2.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel2.setText("Model no.");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));

        jTextField1.setText("1-1-2018");
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 130, 30));

        jButton1.setText("Show");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 70, 40));

        jCheckBox1.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jCheckBox1.setText("Sold");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, -1));

        jCheckBox2.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jCheckBox2.setText("Purchased");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));

        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 80, 40));

        jTextField2.setText("1-1-2018");
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 130, 30));

        jTextField3.setText("1-1-2018");
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 130, 30));

        jButton3.setText("Show");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, 70, 40));

        jLabel3.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel3.setText("Profit");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 580, 50, -1));

        jLabel4.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel4.setText("To");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 20, -1));

        jTextField4.setText("0");
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 570, 120, 30));

        jTextField5.setText("0");
        jPanel1.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 570, 120, 30));

        jTextField6.setText("0");
        jPanel1.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 570, 120, 30));

        jLabel5.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel5.setText("Enter Date :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel6.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel6.setText("InFlow");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, 50, -1));

        jLabel7.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel7.setText("OutFlow");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 580, 50, -1));

        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 150, 30));

        jLabel8.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel8.setText("Filter :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "BillNo", "Date", "Customer", "Contact", "Address", "Total", "Collect", "Due"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, 520, 350));

        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField8KeyPressed(evt);
            }
        });
        jPanel1.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 140, 140, 40));

        jButton4.setText("Report");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 570, 90, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
String Choice[]={"Today","This Month","This Year"};String  p="";boolean b=false;
                String d="",m="",y="",h="",min="";
                Calendar cal= Calendar.getInstance();
                d=String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
                m=String.valueOf(cal.get(Calendar.MONTH)+1);
                y=String.valueOf(cal.get(Calendar.YEAR));
                
                if(jCheckBox2.isSelected()==true){
                    p="Purchased";
                }
                if(jCheckBox1.isSelected()==true){
                    p="Sold";
                }
                if((jCheckBox1.isSelected()==true && jCheckBox2.isSelected()==true)||(jCheckBox1.isSelected()==false && jCheckBox2.isSelected()==false)){
                   b=true; 
                }
                 
                if(jComboBox1.getSelectedItem()==Choice[0] ){
                    conn=DBConnect.ConnectDB();
        
        String Sql="Select  Date,Time,Item,Model,Rate,Qty,Total,Type from Excange";
        try{
           DefaultTableModel model=jTable1();
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
            model.setRowCount(0);
             while(rs.next()){
                 String[] date=rs.getString(1).split("-");
                 if(d.equals(date[0]) && (b || p.equals(rs.getString(8)))){
                        model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)});
                    }
             }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
                }
                 if(jComboBox1.getSelectedItem()==Choice[1]){
                    String Sql="Select  Date,Time,Item,Model,Rate,Qty,Total,Type from Excange";
        try{
           DefaultTableModel model=jTable1();
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
            model.setRowCount(0);
             while(rs.next()){
                 String[] date=rs.getString(1).split("-");
                 if(m.equals(date[1]) && (b || p.equals(rs.getString(8)))){
                        model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)});
                    }
             }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
                }
                  if(jComboBox1.getSelectedItem()==Choice[2]){
                    String Sql="Select  Date,Time,Item,Model,Rate,Qty,Total,Type from Excange";
        try{
           DefaultTableModel model=jTable1();
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
            model.setRowCount(0);
             while(rs.next()){
                 String[] date=rs.getString(1).split("-");
                 if(y.equals(date[2]) && (b || p.equals(rs.getString(8)))){
                        model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)});
                    }
             }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
                }
             cal();
                          // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyReleased
       
                   
    }//GEN-LAST:event_jComboBox1KeyReleased

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String Choice[]={"Today","This Month","This Year"};
                String d="",m="",y="",h="";
                String[] date1=jTextField1.getText().split("-");
                
                d=date1[0];
                m=date1[1];
                y=date1[2];
             
                 
                
                    conn=DBConnect.ConnectDB();
        String str=jTextField1.getText();
        String Sql="Select  Date,Time,Item,Model,Rate,Qty,Total,Type from Excange where date like '%"+str+"%'";
        try{
           DefaultTableModel model=jTable1();
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
            model.setRowCount(0);
             while(rs.next()){
                // String[] date=rs.getString(1).split("-");
                // if(d.equals(date[0]) && m.equals(date[1]) && y.equals(date[2]) ){
                        
                        model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)});
                  //  }
             }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
                
        cal();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            String str="Records\\"+"Transaction Details".toUpperCase()+"_"+Datetime.date()+".pdf";
        Getinfo gi=new Getinfo();
        //str=getpath();
        try{
        Document document=new Document();
       // Rectangle rect = new Rectangle(210,297);
        //document.setPageSize(rect);
        PdfWriter.getInstance(document, new FileOutputStream(str));
        document.open();
            Paragraph title=new Paragraph("REPORT : "+"Transaction Details".toUpperCase(),FontFactory.getFont(FontFactory.COURIER,10,Font.UNDERLINE));
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
        }
         catch(Exception e){
            JOptionPane.showMessageDialog(null,e+"\nProblem Occured During Saving ");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 int d,m,y,dd,mm,yy,a,b,c;
                String[] date1=jTextField3.getText().split("-");
                String[] date2=jTextField2.getText().split("-");
                
                d=Integer.valueOf(date1[0]);
                m=Integer.valueOf(date1[1]);
                y=Integer.valueOf(date1[2]);
                d=d+m*100+y*10000;
                dd=Integer.valueOf(date2[0]);
                mm=Integer.valueOf(date2[1]);
                yy=Integer.valueOf(date2[2]);
                dd=dd+mm*100+yy*10000;
                 
                
                    conn=DBConnect.ConnectDB();
        
        String Sql="Select  Date,Time,Item,Model,Rate,Qty,Total,Type from Excange ";
        try{
           DefaultTableModel model=jTable1();
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
            model.setRowCount(0);
             while(rs.next()){
                 String[] date=rs.getString(1).split("-");
                 a=Integer.valueOf(date[0]);
                b=Integer.valueOf(date[1]);
                c=Integer.valueOf(date[2]);
                a=a+b*100+c*10000;
                 if(a>=d &&a <=dd ){
                        
                        model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)});
                    }
             }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
cal();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
jTable(jTextField7.getText().trim());        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7KeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
printreport();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jTable2();
            
        }         // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8KeyPressed

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
            java.util.logging.Logger.getLogger(Transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaction().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
