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
import com.itextpdf.text.PageSize;
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
import net.proteanit.sql.DbUtils;

/**
 *
 * @author sam
 */
public class Collection extends javax.swing.JFrame {

    /**
     * Creates new form Collection
     */
    Connection conn=null;
PreparedStatement pst=null;
ResultSet rs=null;
    public Collection() {
        initComponents();
        jTable3();
    }
    
    //printbill
    public void printbill(){
         String due=String.valueOf(Float.valueOf((String)jTable1.getValueAt(jTable1.getSelectedRow(), 6))-Float.valueOf(jTextField6.getText()));
        String collect=String.valueOf(Float.valueOf((String)jTable1.getValueAt(jTable1.getSelectedRow(), 5)) +Float.valueOf(jTextField6.getText()));
        
          String str="Invoices\\Collection_"+jTextField8.getText()+".pdf";
        Getinfo gi=new Getinfo();
        //str=getpath();
        try{
        Document document=new Document(PageSize.A4);
       // Rectangle rect = new Rectangle(210,297);
        //document.setPageSize(rect);
         PdfWriter.getInstance(document, new FileOutputStream(str));
        
        document.open();
        Paragraph title=new Paragraph("DELIVERY CHALLAN",FontFactory.getFont(FontFactory.COURIER,10,Font.UNDERLINE));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
         Paragraph company=new Paragraph("BALAJI TILES".toUpperCase(),FontFactory.getFont(FontFactory.COURIER,16,Font.BOLD));
        company.setAlignment(Element.ALIGN_CENTER);
        document.add(company);
        Paragraph add1=new Paragraph("HANUMAN NAGAR,KHANAPADA ROAD",FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD));
        add1.setAlignment(Element.ALIGN_CENTER);
        document.add(add1);
        Paragraph add2=new Paragraph("NEAR FIRE STATION,NAYAGARH",FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD));
        add2.setAlignment(Element.ALIGN_CENTER);
        document.add(add2);
         add2=new Paragraph("MOB :9658029600 , 9437359151 ,9439073220",FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD));
        add2.setAlignment(Element.ALIGN_CENTER);
        document.add(add2);
        Paragraph bill=new Paragraph("INVOICE NO. "+jTextField7.getText(),FontFactory.getFont(FontFactory.COURIER,12));
        bill.setAlignment(Element.ALIGN_RIGHT);
        document.add(bill);
        Paragraph date=new Paragraph("DATE "+Datetime.date()+" IST "+Datetime.time(),FontFactory.getFont(FontFactory.COURIER,10));
        date.setAlignment(Element.ALIGN_RIGHT);
        document.add(date);
        Paragraph bt=new Paragraph("CUSTOMER DETAILS",FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD));
        bt.setAlignment(Element.ALIGN_LEFT);
        document.add(bt);
       
        bt=new Paragraph("\n");
        bt.setAlignment(Element.ALIGN_LEFT);
        document.add(bt);
         PdfPTable tab =new PdfPTable(5);
         PdfPCell c ;
        //table.setWidths(new float[]{});
        //table.setWidthPercentage(100);
        tab.setWidths(new float[]{2,3,1,2,2});
        tab.setWidthPercentage(100);
        
            c=new PdfPCell(new Paragraph("NAME",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph((String)jTable1.getValueAt(jTable1.getSelectedRow(), 1),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph(" ",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
                c=new PdfPCell(new Paragraph("TOTAL CREDIT",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph((String)jTable1.getValueAt(jTable1.getSelectedRow(), 4),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph("ADDRESS",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph((String)jTable1.getValueAt(jTable1.getSelectedRow(), 3),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph(" ",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
                c=new PdfPCell(new Paragraph("PAID AMOUNT",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph(collect,FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph("CONTACT",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph((String)jTable1.getValueAt(jTable1.getSelectedRow(), 2),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
           
            c=new PdfPCell(new Paragraph(" ",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
                c=new PdfPCell(new Paragraph("DUE AMOUNT",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph(due,FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            
             document.add(tab);
       // cell.setBackgroundColor(BaseColor.DARK_GRAY);
        //cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT |Rectangle.TO
        
        String strsp="\n\n";
        int Maxv=5;
        for(int i=0;i<=Maxv-jTable1.getRowCount();i++){
            strsp=strsp+"\n";
        }
        
        bt=new Paragraph(strsp,FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD));
        bt.setAlignment(Element.ALIGN_LEFT);
        document.add(bt);
        PdfPTable footer =new PdfPTable(2);
                    c=new PdfPCell(new Paragraph("SELLER SIGNATURE",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            footer.addCell(c);
            c=new PdfPCell(new Paragraph("CUSTOMER SIGNATURE",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorder(PdfPCell.NO_BORDER );
            footer.addCell(c);
         document.add(footer);
         
         bt=new Paragraph("\n\n\n");
        bt.setAlignment(Element.ALIGN_CENTER);
        document.add(bt);
        
       /////////////
        title=new Paragraph("DELIVERY CHALLAN",FontFactory.getFont(FontFactory.COURIER,10,Font.UNDERLINE));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
          company=new Paragraph("BALAJI TILES".toUpperCase(),FontFactory.getFont(FontFactory.COURIER,16,Font.BOLD));
        company.setAlignment(Element.ALIGN_CENTER);
        document.add(company);
         add1=new Paragraph("HANUMAN NAGAR,KHANAPADA ROAD",FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD));
        add1.setAlignment(Element.ALIGN_CENTER);
        document.add(add1);
         add2=new Paragraph("NEAR FIRE STATION,NAYAGARH",FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD));
        add2.setAlignment(Element.ALIGN_CENTER);
        document.add(add2);
         add2=new Paragraph("MOB :9658029600 , 9437359151 ,9439073220",FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD));
        add2.setAlignment(Element.ALIGN_CENTER);
        document.add(add2);
       bill=new Paragraph("INVOICE NO. "+jTextField7.getText(),FontFactory.getFont(FontFactory.COURIER,12));
        bill.setAlignment(Element.ALIGN_RIGHT);
        document.add(bill);
         date=new Paragraph("DATE "+Datetime.date()+" IST "+Datetime.time(),FontFactory.getFont(FontFactory.COURIER,10));
        date.setAlignment(Element.ALIGN_RIGHT);
        document.add(date);
         bt=new Paragraph("CUSTOMER DETAILS",FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD));
        bt.setAlignment(Element.ALIGN_LEFT);
        document.add(bt);
       
        bt=new Paragraph("\n");
        bt.setAlignment(Element.ALIGN_LEFT);
        document.add(bt);
          tab =new PdfPTable(5);
         
        //table.setWidths(new float[]{});
        //table.setWidthPercentage(100);
        tab.setWidths(new float[]{2,3,1,2,2});
        tab.setWidthPercentage(100);
        
            c=new PdfPCell(new Paragraph("NAME",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph((String)jTable1.getValueAt(jTable1.getSelectedRow(), 1),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph(" ",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
                c=new PdfPCell(new Paragraph("TOTAL CREDIT",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph((String)jTable1.getValueAt(jTable1.getSelectedRow(), 4),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph("ADDRESS",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph((String)jTable1.getValueAt(jTable1.getSelectedRow(), 3),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph(" ",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
                c=new PdfPCell(new Paragraph("PAID AMOUNT",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph(collect,FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph("CONTACT",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph((String)jTable1.getValueAt(jTable1.getSelectedRow(), 2),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
           
            c=new PdfPCell(new Paragraph(" ",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
                c=new PdfPCell(new Paragraph("DUE AMOUNT",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph(due,FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            
             document.add(tab);
       // cell.setBackgroundColor(BaseColor.DARK_GRAY);
        //cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT |Rectangle.TO
        
         strsp="\n\n";
         Maxv=5;
        for(int i=0;i<=Maxv-jTable1.getRowCount();i++){
            strsp=strsp+"\n";
        }
        
        bt=new Paragraph(strsp,FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD));
        bt.setAlignment(Element.ALIGN_LEFT);
        document.add(bt);
         footer =new PdfPTable(2);
                    c=new PdfPCell(new Paragraph("SELLER SIGNATURE",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            footer.addCell(c);
            c=new PdfPCell(new Paragraph("CUSTOMER SIGNATURE",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorder(PdfPCell.NO_BORDER );
            footer.addCell(c);
         document.add(footer);
         


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
            JOptionPane.showMessageDialog(null,e);
        } 
        
        
        
        
    }
    //printbill
    
    
     public void jTable1(){
         conn=DBConnect.ConnectDB();
        DefaultTableModel model=null;
        model =(DefaultTableModel)jTable1.getModel();
         
                 
        String Sql="Select * from Payment ";
        try{
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
              model.setRowCount(0);
             while(rs.next()){  
                 if(Float.valueOf(rs.getString(8))>0){
                        model.addRow(new Object[]{rs.getString(1),rs.getString(3) ,rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8) });
                 }
             }
              conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
       public void cal(){
        Float cd=(float)0;
        Float cl=(float)0;
        Float du=(float)0;
        
         for(int i=0;i<jTable1.getRowCount();i++){
             Float x=(float)0;Float yy=(float)0;Float z=(float)0;
             if(jTable1.getValueAt(i,4).equals("")){
                 
             }else{
                 x=Float.valueOf(String.valueOf(jTable1.getValueAt(i,4)));
             }
             if(jTable1.getValueAt(i,5).equals("")){
                 
             }else{
                 yy=Float.valueOf(String.valueOf(jTable1.getValueAt(i,5)));
             }
             if(jTable1.getValueAt(i,6).equals("")){
                 
             }else{
                 z=Float.valueOf(String.valueOf(jTable1.getValueAt(i,6)));
             }
                   
             
             
             cd=cd+x;
             cl=cl+yy;
             du=du+z;
         }
         
         
         
         
         jTextField9.setText(String.valueOf(cd));
         jTextField10.setText(String.valueOf(cl));
         jTextField1.setText(String.valueOf(du));
         
    }
 public void jTable3(){
     jTextField8.setText(Datetime.date());
     jTextField2.setText(Datetime.time());
         conn=DBConnect.ConnectDB();
        DefaultTableModel model=null;
        model =(DefaultTableModel)jTable1.getModel();
         String b=jTextField5.getText();
         String c=jTextField3.getText();
         String d=jTextField4.getText();
                
        String Sql="Select * from  Payment where CName  like '%"+c+"%' and Bill like '%"+b+"%' and BDate like '%"+d+"%'";
        try{
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
               model.setRowCount(0);
             while(rs.next()){    
                 if(jCheckBox2.isSelected()==true){
                    if(Float.valueOf(rs.getString(8))>0){
                        
                        model.addRow(new Object[]{rs.getString(1) ,rs.getString(3) ,rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8) });
                        
                    }
                 }else{
                                             model.addRow(new Object[]{rs.getString(1) ,rs.getString(3) ,rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8) });

                 }
             }
              conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        cal();
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(820, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "COLLECTION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Impact", 0, 14))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BILL", "NAME", "CONTACT", "ADDRESS", "CREDIT", "COLLECTION", "DUE"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 560, 300));

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });
        jPanel2.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 130, 30));

        jLabel3.setFont(new java.awt.Font("Liberation Mono", 1, 14)); // NOI18N
        jLabel3.setText("Customer Name");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });
        jPanel2.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 130, 30));

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });
        jPanel2.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 80, 30));

        jLabel5.setFont(new java.awt.Font("Liberation Mono", 1, 14)); // NOI18N
        jLabel5.setText("Bill No.");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 70, 20));

        jCheckBox1.setText("Show All");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, -1, -1));

        jLabel8.setFont(new java.awt.Font("Liberation Mono", 1, 14)); // NOI18N
        jLabel8.setText("Billing Date");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, -1, 20));

        jCheckBox2.setText(" Due");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        jPanel2.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, -1, -1));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton1.setText("Collect");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Liberation Mono", 1, 14)); // NOI18N
        jLabel2.setText("Amount");

        jLabel6.setFont(new java.awt.Font("Liberation Mono", 1, 14)); // NOI18N
        jLabel6.setText("Bill No.");

        jLabel7.setFont(new java.awt.Font("Liberation Mono", 1, 14)); // NOI18N
        jLabel7.setText("Date");

        jTextField1.setText("0");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField9.setText("0");

        jTextField10.setText("0");

        jLabel4.setFont(new java.awt.Font("Liberation Mono", 1, 14)); // NOI18N
        jLabel4.setText("Credit");

        jLabel9.setFont(new java.awt.Font("Liberation Mono", 1, 14)); // NOI18N
        jLabel9.setText("Due");

        jLabel10.setFont(new java.awt.Font("Liberation Mono", 1, 14)); // NOI18N
        jLabel10.setText(" Collection");

        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Liberation Mono", 1, 14)); // NOI18N
        jLabel11.setText("IST");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))))
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(129, 129, 129)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(27, 27, 27)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(87, 87, 87))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        
        jTable1();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        // TODO add your handling code here:
        
        jTable3();
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
jTable3();        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
jTable3();        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        conn=DBConnect.ConnectDB();
        
        String Sql="Select * from Sale where CName=? and BillNo=? ";
        try{
            pst=conn.prepareStatement(Sql);
             pst.setString(1,(String)jTable1.getValueAt(jTable1.getSelectedRow(), 1));
              pst.setString(2,(String)jTable1.getValueAt(jTable1.getSelectedRow(), 0));
            rs=pst.executeQuery();
              
             if(rs.next()){  
         jTextField7.setText(rs.getString(8));
         jTextField5.setText(rs.getString(8));
         jTextField3.setText(rs.getString(4));
         jTextField4.setText(rs.getString(9));
             }
              conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        
        conn=DBConnect.ConnectDB();
             String Sql=" insert into Excange (Date,Time,Item,Model,Rate,Qty,Total,Type,Company,Bill)" +" values (?,?,?,?,?,?,?,?,?, ? ) ";
                  try{


                   pst=conn.prepareStatement(Sql);
                   pst.setString(1,jTextField8.getText());
                   pst.setString(2,jTextField2.getText());
                   pst.setString(3,"NA");
                   pst.setString(4,"NA");
                   pst.setString(5,"NA");
                   pst.setString(6,"NA");
                   pst.setString(7,jTextField6.getText());
                   pst.setString(8,"Collected");
                   pst.setString(9,jTextField3.getText());
                   pst.setString(10,jTextField5.getText());
                   pst.execute();
                   conn.close();
               }
               catch(Exception e){
                   JOptionPane.showMessageDialog(null,e);
               } 


        // TODO add your handling code here:
        
        String due=String.valueOf(Float.valueOf((String)jTable1.getValueAt(jTable1.getSelectedRow(), 6))-Float.valueOf(jTextField6.getText()));
        String collect=String.valueOf(Float.valueOf((String)jTable1.getValueAt(jTable1.getSelectedRow(), 5)) +Float.valueOf(jTextField6.getText()));
        conn=DBConnect.ConnectDB();
              Sql=" update  Payment set Collect=? ,Due=? where Bill=? and BDate=? ";
                  try{

                   System.out.println(due);
                   pst=conn.prepareStatement(Sql);
                  
                   pst.setString(1,collect);
                   pst.setString(2,due);
                   
                   pst.setString(3,jTextField5.getText());
                   pst.setString(4,jTextField4.getText());
                   
                   
                   pst.executeUpdate();
                   conn.close();
               }
               catch(Exception e){
                   JOptionPane.showMessageDialog(null,e);
               }
                  printbill();
                  jTable3();
                  
                   
             conn=DBConnect.ConnectDB();
             
             String Sq= "select No_Of_Installment from Installment Where BillNo = ? ";
             try
             {
                 pst=conn.prepareStatement(Sq);
               pst.setString(1,jTextField7.getText());
               
               
               
                 
             }
              catch(Exception e){
                   JOptionPane.showMessageDialog(null,e);
              }
                   
                  
                  
                  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
String str="Collection\\"+Datetime.date()+".pdf";
       
        try{
        Document document=new Document();
       // Rectangle rect = new Rectangle(210,297);
        //document.setPageSize(rect);
        PdfWriter.getInstance(document, new FileOutputStream(str));
        document.open();
            Paragraph title=new Paragraph("REPORT : COLLECTION",FontFactory.getFont(FontFactory.COURIER,10,Font.UNDERLINE));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
         Paragraph company=new Paragraph("BALAJI TILES",FontFactory.getFont(FontFactory.COURIER,16,Font.BOLD));
        company.setAlignment(Element.ALIGN_CENTER);
        
        Paragraph add2=new Paragraph("MOB :9658029600 , 9437359151 ,9439073220",FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD));
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
           // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
jTable3();        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

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
            java.util.logging.Logger.getLogger(Collection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Collection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Collection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Collection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Collection().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
