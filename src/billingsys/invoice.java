/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billingsys;

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
import com.sun.glass.events.KeyEvent;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
/**
 *
 * @author sam
 */
public class invoice extends javax.swing.JFrame {

    /**
     * Creates new form invoice
     */
Connection conn=null;
PreparedStatement pst=null;
ResultSet rs=null;
int sl=0;


    public invoice() {
        initComponents();
        setLocationRelativeTo(null);
        fill();
    }
    public void setsl(){
        DefaultTableModel model=null;int sl=1;
        model =(DefaultTableModel)jTable1.getModel();
         for(int i=0;i<jTable1.getRowCount();i++){
             jTable1.setValueAt(String.valueOf(sl), i, 0);
            //model.setValueAt(String.valueOf(sl), jTable1.getSelectedRow(), 0);
            sl++;
        }
         
         
        
    }
    public void fill(){
    String date=Datetime.date();
    String time=Datetime.time();
    jTextField10.setText(date);
    jTextField8.setText(time);
    conn=DBConnect.ConnectDB();
String Sql="Select * from Sale";int c=0;
        try{
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 
                c=Integer.valueOf(rs.getString(8));;
            
            }
            conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        c++;
       jTextField9.setText(String.valueOf(c));
       
       TableColumn column=null;
       column = jTable1.getColumnModel().getColumn(0);
        column.setPreferredWidth(2);
       
      
       
}
    public void jTable(){
        conn=DBConnect.ConnectDB();
        DefaultTableModel model=null;
        model =(DefaultTableModel)jTable2.getModel();
        String itm=jTextField2.getText();
        String mod=jTextField3.getText().trim();
        String Sql="Select * from ItemInfo where  Item =? ";
        try{
            pst=conn.prepareStatement(Sql);
            pst.setString(1,mod);
            rs=pst.executeQuery();
              model.setRowCount(0);
            if(rs.next()){    
                        //model.addRow(new Object[]{rs.getString(1),rs.getString(2) ,rs.getString(6),rs.getString(3) });
                    
        jTextField2.setText(rs.getString(1));
        jTextField3.setText(rs.getString(2));
        jTextField6.setText(rs.getString(6));
        jTextField4.setText(rs.getString(3));
        cal();
                        
             }
              conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
public void jTable1(){
         conn=DBConnect.ConnectDB();
        DefaultTableModel model=null;
        model =(DefaultTableModel)jTable2.getModel();
        String itm=jTextField2.getText();
        String mod=jTextField3.getText();
        String Sql="Select * from ItemInfo where Item  like '%"+itm+"%' and Model like '%"+mod+"%' ";
        try{
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
              model.setRowCount(0);
             while(rs.next()){    
                        model.addRow(new Object[]{rs.getString(1),rs.getString(2) ,rs.getString(6),rs.getString(3) });
                    
             }
              conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

public boolean scheck(){
    float avl =(float)0;
    
                    String qty="";int c=0;
                        conn=DBConnect.ConnectDB();

                           String  Sql="Select * from ItemStock where  Item=? and Model=? ";
                  try{
                      pst=conn.prepareStatement(Sql);
                      pst=conn.prepareStatement(Sql);
                      pst.setString(1,jTextField2.getText());
                      pst.setString(2,jTextField3.getText());
                      rs=pst.executeQuery();
                      while(rs.next()){

                          qty = rs.getString("Quantity");c++;
                          

                      }
                       conn.close();
                        
                  }
                  catch(Exception e){
                      JOptionPane.showMessageDialog(null,e);
                  }
                    if(c>0){ 
                          float f = Float.valueOf(qty);
                         avl=f;
                          f=f-Float.valueOf(jTextField7.getText());
                          
                          if(f>=0){
                               // JOptionPane.showMessageDialog(null,"Stock After Decreased : "+f); 

                                   conn=DBConnect.ConnectDB();

                                Sql="update ItemStock set Quantity=? where Item=? and Model=? ";
                                try{
                                    pst=conn.prepareStatement(Sql);
                                    pst.setString(1,String.valueOf(f));
                                    pst.setString(2,jTextField2.getText());
                                    pst.setString(3,jTextField3.getText());
                                    pst.executeUpdate();

                                    conn.close();
                                }
                                catch(Exception e){
                                    JOptionPane.showMessageDialog(null,e);
                                }
                                return true;
                          }
                          else{
                              JOptionPane.showMessageDialog(null, "RequestedStock Not Available\nAvailble Stock = "+String.valueOf(avl));
                              
                              return false;
                              
                          }
                          
                    }
                    else{  
                                JOptionPane.showMessageDialog(null, "Item Doesn't Exists");
                               
                               return false; 
                    }
                    
                  
        
    
}
public String getdis(){
        float sum = 0;
        for(int i=0;i<jTable1.getRowCount();i++){
            sum=sum+( Float.valueOf((String)jTable1.getValueAt(i, 4))*Float.valueOf((String)jTable1.getValueAt(i, 6)));
        }
        sum=sum+(Float.valueOf(jTextField19.getText()));
        return String.valueOf(sum);
    }
 public String gettotal(){
        float sum = 0;
         
        for(int i=0;i<jTable1.getRowCount();i++){
            sum=sum+Float.valueOf((String)jTable1.getValueAt(i, 7));
        }
        return String.valueOf(sum);
    }
public void cal(){
    if("".equals(jTextField7.getText())){
       jTextField7.setText("0"); 
        
    }
    if("".equals(jTextField1.getText())){
       jTextField1.setText("0"); 
        
    }
    if("".equals(jTextField6.getText())){
       jTextField6.setText("0"); 
        
    }
       jTextField5.setText(String.valueOf( (Float.valueOf(jTextField6.getText()) - Float.valueOf(jTextField1.getText()))*Float.valueOf(jTextField7.getText())));
       jTextField17.setText(String.valueOf((Float.valueOf(jTextField14.getText())-(Float.valueOf(jTextField19.getText()))+(Float.valueOf(jTextField16.getText())))));


}
public void additm(){
    if(scheck()){
    cal();
           sl=jTable1.getRowCount();
           sl++;

DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
model.addRow(new Object[]{String.valueOf(sl),jTextField2.getText(),jTextField3.getText(),jTextField4.getText(),jTextField7.getText(),jTextField6.getText(),jTextField1.getText(),jTextField5.getText()});


             jTextField14.setText(gettotal());
             if(jCheckBox1.isSelected()==true){
                jTextField16.setText(String.valueOf( (Float.valueOf(jTextField14.getText()) *0.18)));
            }else{
                jTextField16.setText("0");
            }
             jTextField17.setText(String.valueOf((Float.valueOf(jTextField14.getText())-(Float.valueOf(jTextField19.getText()))+(Float.valueOf(jTextField16.getText())))));
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField1.setText("0");
            jTextField5.setText("0");
            jTextField6.setText("0");
            jTextField7.setText("0");
            
    }       /////
            
             
}




//print bill
 public void printbill(){
          String str="Invoices\\"+jTextField10.getText()+"_"+jTextField9.getText()+".pdf";
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
        Paragraph bill=new Paragraph("INVOICE NO. "+jTextField9.getText(),FontFactory.getFont(FontFactory.COURIER,12));
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
         PdfPTable tab =new PdfPTable(2);
         PdfPCell c ;
        //table.setWidths(new float[]{});
        //table.setWidthPercentage(100);
        tab.setWidths(new float[]{1,7});
        tab.setWidthPercentage(100);
        
            c=new PdfPCell(new Paragraph("NAME",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph(jTextField12.getText(),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph("ADDRESS",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph(jTextField13.getText(),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph("CONTACT",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            c=new PdfPCell(new Paragraph(jTextField11.getText(),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            tab.addCell(c);
            document.add(tab);
       // cell.setBackgroundColor(BaseColor.DARK_GRAY);
        //cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT |Rectangle.TOP);
         bt=new Paragraph("\n");
        bt.setAlignment(Element.ALIGN_LEFT);
        document.add(bt);
            
        
        
        PdfPTable table =new PdfPTable(jTable1.getColumnCount());
        //table.setWidths(new float[]{});
        
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1,2,4,3,1,2,2,2});
        for(int i=0;i<jTable1.getColumnCount();i++){
            
            PdfPCell cell =new PdfPCell(new Paragraph(jTable1.getColumnName(i),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
      
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       // cell.setBackgroundColor(BaseColor.DARK_GRAY);
        //cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT |Rectangle.TOP);
         
        table.addCell(cell);
        }
        
        for(int i=0;i<jTable1.getRowCount();i++){
            for(int k=0;k<jTable1.getColumnCount();k++){
                
               
        if( i<jTable1.getRowCount()){
                        PdfPCell cell =new PdfPCell(new Paragraph(jTable1.getValueAt(i, k).toString(),FontFactory.getFont(FontFactory.COURIER,12)));

                   cell.setHorizontalAlignment(Element.ALIGN_CENTER);

                   cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT  );
                   if(i==jTable1.getRowCount()-1){
                       cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT |Rectangle.BOTTOM );
                   }
                   table.addCell(cell);
              
                }
            }
           
        }
        document.add(table);
               
       
String strsp="\n\n";
        int Maxv=12;
        for(int i=0;i<=Maxv-jTable1.getRowCount();i++){
            strsp=strsp+"\n";
        }
        bt=new Paragraph(strsp,FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD));
        bt.setAlignment(Element.ALIGN_LEFT);
        document.add(bt);
        
        
        PdfPTable billing =new PdfPTable(3);
        billing.setWidthPercentage(100);
        billing.setWidths(new float[]{12,3,3});
                    c=new PdfPCell(new Paragraph(" ",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            billing.addCell(c);
                c=new PdfPCell(new Paragraph("SUB TOTAL",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorder(PdfPCell.NO_BORDER );
            billing.addCell(c);
            c=new PdfPCell(new Paragraph(jTextField14.getText(),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorder(PdfPCell.NO_BORDER );
            billing.addCell(c);
            
            c=new PdfPCell(new Paragraph(" ",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            billing.addCell(c);
                c=new PdfPCell(new Paragraph("TAX",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorder(PdfPCell.NO_BORDER );
            billing.addCell(c);
            c=new PdfPCell(new Paragraph(jTextField16.getText(),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorder(PdfPCell.NO_BORDER );
            billing.addCell(c);
            
            c=new PdfPCell(new Paragraph(" ",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            billing.addCell(c);
                c=new PdfPCell(new Paragraph("DISCOUNT",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorder(PdfPCell.NO_BORDER );
            billing.addCell(c);
            c=new PdfPCell(new Paragraph(getdis(),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorder(PdfPCell.NO_BORDER );
            billing.addCell(c);
            
            
            c=new PdfPCell(new Paragraph(" ",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            billing.addCell(c);
                c=new PdfPCell(new Paragraph("NET AMOUNT",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorder(PdfPCell.NO_BORDER );
            billing.addCell(c);
            c=new PdfPCell(new Paragraph(jTextField17.getText(),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorder(PdfPCell.NO_BORDER );
            billing.addCell(c);
            
             c=new PdfPCell(new Paragraph(" ",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            billing.addCell(c);
                c=new PdfPCell(new Paragraph("COLLECTED",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorder(PdfPCell.NO_BORDER );
            billing.addCell(c);
            c=new PdfPCell(new Paragraph(String.valueOf(Float.valueOf(jTextField15.getText())),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorder(PdfPCell.NO_BORDER );
            billing.addCell(c);
            c=new PdfPCell(new Paragraph(" ",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(PdfPCell.NO_BORDER );
            billing.addCell(c);
                c=new PdfPCell(new Paragraph("DUE",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorder(PdfPCell.NO_BORDER );
            billing.addCell(c);
            c=new PdfPCell(new Paragraph(String.valueOf(Float.valueOf(jTextField17.getText())-Float.valueOf(jTextField15.getText())),FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorder(PdfPCell.NO_BORDER );
            billing.addCell(c);

            
         document.add(billing);
        
        
        
        bt=new Paragraph("\n\n",FontFactory.getFont(FontFactory.COURIER,12,Font.BOLD));
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
        bt=new Paragraph("DETAILS",FontFactory.getFont(FontFactory.COURIER,12 ));
        bt.setAlignment(Element.ALIGN_CENTER);
        document.add(bt); 
         bt=new Paragraph("ALL TYPES OF MARBALS , VITRIFIED , WALL TILES ETC. AVAILABLE",FontFactory.getFont(FontFactory.COURIER,10,Font.BOLD));
        bt.setAlignment(Element.ALIGN_CENTER);
        document.add(bt);
        bt=new Paragraph("GOODS ONCE SOLD, IS NOT RETURNABLE . WE ARE NOT RESPONSIBLE FOR ANY TYPE OF BREAKING OF TILES .",FontFactory.getFont(FontFactory.COURIER,8,Font.BOLD));
        bt.setAlignment(Element.ALIGN_CENTER);
        document.add(bt);
          
            //PdfWriter writer=PdfWriter.getInstance(document, os);
          // PdfContentByte cb=document.ge;
          
         
            
            
            
            tab.addCell(c);
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
        
        
        
        this.dispose();
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
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel16 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1312, 807));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "BILL REPORT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Impact", 0, 18))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setText("0");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 290, 60, 30));

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 80, 30));

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 130, 30));
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 110, 30));

        jTextField5.setText("0");
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 290, 100, 30));

        jTextField6.setText("0");
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 290, 60, 30));

        jLabel1.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel1.setText("IST");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, -1, 20));

        jLabel2.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel2.setText("Item");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        jLabel3.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel3.setText("Model No");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, -1, -1));

        jLabel4.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel4.setText("Company");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 270, -1, -1));

        jLabel5.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel5.setText("Rate");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 270, -1, -1));

        jLabel6.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel6.setText("Discount");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SL", "ITEM", "MODEL", "COMPANY", "QTY", "RATE", "DISCOUNT", "TOTAL"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 720, 240));

        jLabel8.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel8.setText("Customer Name");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, 20));

        jLabel9.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel9.setText("Company");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, -1, 20));

        jLabel10.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel10.setText("Contact No");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, 20));

        jLabel11.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel11.setText(" No.");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, -1, 20));

        jLabel12.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel12.setText("Date");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, -1, 20));

        jTextField8.setEditable(false);
        jPanel1.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 100, 30));

        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField9KeyPressed(evt);
            }
        });
        jPanel1.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 50, 30));

        jTextField10.setEditable(false);
        jPanel1.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 100, 30));

        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField11KeyPressed(evt);
            }
        });
        jPanel1.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 180, 30));

        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField12KeyPressed(evt);
            }
        });
        jPanel1.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 180, 30));

        jTextField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField13KeyPressed(evt);
            }
        });
        jPanel1.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, 180, 30));

        jButton1.setText("Done");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 650, 100, 30));

        jLabel13.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel13.setText("Total");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 270, -1, 20));

        jLabel14.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel14.setText("Discount ");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 620, -1, 20));

        jLabel15.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel15.setText("Collection");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 640, -1, 20));

        jTextField14.setEditable(false);
        jTextField14.setText("0");
        jPanel1.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, 90, 30));

        jTextField15.setEditable(false);
        jTextField15.setText("0");
        jPanel1.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 630, 90, 30));

        jTextField7.setText("0");
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField7KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 290, 60, 30));

        jLabel7.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel7.setText("Qty");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 270, -1, -1));

        jCheckBox1.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jCheckBox1.setText("Taxable");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 660, -1, -1));

        jLabel16.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel16.setText("Net Amount ");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 590, -1, 20));

        jTextField16.setText("0");
        jPanel1.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 580, 100, 30));

        jLabel17.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel17.setText("Subtotal");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 630, -1, 20));

        jTextField17.setEditable(false);
        jTextField17.setText("0");
        jPanel1.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 580, 90, 30));

        jLabel18.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel18.setText("Address");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, -1, 20));

        jTextField18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField18KeyPressed(evt);
            }
        });
        jPanel1.add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, 180, 30));

        jLabel19.setFont(new java.awt.Font("Liberation Mono", 1, 12)); // NOI18N
        jLabel19.setText("Tax amount");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 630, -1, 20));

        jTextField20.setColumns(1);
        jTextField20.setText("1");
        jTextField20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField20ActionPerformed(evt);
            }
        });
        jTextField20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField20KeyPressed(evt);
            }
        });
        jPanel1.add(jTextField20, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 580, 100, 30));

        jTextField19.setText("0");
        jTextField19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField19KeyPressed(evt);
            }
        });
        jPanel1.add(jTextField19, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 580, 100, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 750, 770));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ITEM", "MODEL", "M.R.P", "COMPANY"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 480, 230));

        jButton2.setText("Add");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 100, 30));

        jButton3.setText("Update");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 100, 30));

        jButton4.setText("Remove");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 100, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 510, 760));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
      
      //end
      if(Float.valueOf(jTextField17.getText())>0){
         
       String coll=JOptionPane.showInputDialog("Collect Amount");
       if(Float.valueOf(coll)<=0){
            jTextField15.setText("0");
       }else{
            jTextField15.setText(coll);
       }
      
      ////
      for(int i=0;i<jTable1.getRowCount();i++){
             conn=DBConnect.ConnectDB();

             String Sql=" insert into Sale (Item,Model,Total,CName,CCompany,CContact,Info,BillNo,Date,Time,SL,Collect,Due)" +" values (?,?,?,?,?, ?,?,?,?,?,?,?,?) ";
           try{
              
            
            String time=Datetime.time();
            pst=conn.prepareStatement(Sql);
            pst.setString(1,(String)jTable1.getValueAt(i, 1));
            pst.setString(2,(String)jTable1.getValueAt(i, 2));
            pst.setString(3,(String)jTable1.getValueAt(i, 7));
            pst.setString(4,jTextField12.getText());
            pst.setString(5,jTextField18.getText());
            pst.setString(6,jTextField11.getText());
            pst.setString(7,jTextField13.getText());
            pst.setString(8,jTextField9.getText());
            pst.setString(9,jTextField10.getText());
            pst.setString(10,time);
            pst.setString(11,(String)jTable1.getValueAt(i, 0));
            pst.setString(12,jTextField17.getText());
            
            String s=String.valueOf(Float.valueOf(jTextField17.getText())-Float.valueOf(jTextField15.getText()) );
            pst.setString(13,s);
            
            
            pst.execute();
                conn.close();
                
                
               
                
                
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }   
      }
      //Installment
       for(int i=0;i<jTable1.getRowCount();i++){
             conn=DBConnect.ConnectDB();
             int no=1;
             
             
              String Sql=" insert into Installment (BillNo,No_Of_Installment,AmountPaid)" +" values (?,?,?) ";
           try{
             
               pst=conn.prepareStatement(Sql);
               pst.setString(1,jTextField9.getText());
               pst.setString(2,jTextField20.getText());
                pst.setString(3,jTextField15.getText());
                  
                   pst.execute();
                     conn.close();
           }
            catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
               
           }
       
      } 
      
      
      
         for(int i=0;i<jTable1.getRowCount();i++){
             conn=DBConnect.ConnectDB();
             String Sql=" insert into Excange (Date,Time,Item,Model,Rate,Qty,Total,Type,Company,Bill)" +" values (?,?,?,?,?,?,?,?,?, ? ) ";
                  try{


                   pst=conn.prepareStatement(Sql);
                   pst.setString(1,jTextField10.getText());
                   pst.setString(2,jTextField8.getText());
                   pst.setString(3,(String)jTable1.getValueAt(i, 1));
                   pst.setString(4,(String)jTable1.getValueAt(i, 2));
                   pst.setString(5,(String)jTable1.getValueAt(i, 5));
                   pst.setString(6,(String)jTable1.getValueAt(i, 4));
                   pst.setString(7,(String)jTable1.getValueAt(i, 7));
                   pst.setString(8,"Sold");
                   pst.setString(9,jTextField12.getText());
                   pst.setString(10,jTextField9.getText());
                   pst.execute();
                   conn.close();
               }
               catch(Exception e){
                   JOptionPane.showMessageDialog(null,e);
               } 
         }
          conn=DBConnect.ConnectDB();
             String Sql=" insert into Payment (Bill,BDate,Cname,CContact,CAddress,Total,Collect,Due)" +" values (?,?,?,?,?,?,?,? ) ";
                  try{

                   String due=String.valueOf(Float.valueOf(jTextField17.getText())-Float.valueOf(jTextField15.getText()));
                   System.out.println(due);
                   pst=conn.prepareStatement(Sql);
                   pst.setString(1,jTextField9.getText());
                   pst.setString(2,jTextField10.getText());
                   pst.setString(3,jTextField12.getText());
                   pst.setString(4,jTextField11.getText());
                   pst.setString(5,jTextField13.getText());
                   pst.setString(6,jTextField17.getText());
                   pst.setString(7,jTextField15.getText());
                   pst.setString(8,due);
                 
                   pst.execute();
                   conn.close();
               }
               catch(Exception e){
                   JOptionPane.showMessageDialog(null,e);
               } 
                  conn=DBConnect.ConnectDB();
               Sql=" insert into Excange (Date,Time,Item,Model,Rate,Qty,Total,Type,Company,Bill)" +" values (?,?,?,?,?,?,?,?,?, ? ) ";
                  try{


                   pst=conn.prepareStatement(Sql);
                   pst.setString(1,jTextField10.getText());
                   pst.setString(2,jTextField8.getText());
                   pst.setString(3,"NA");
                   pst.setString(4,"NA");
                   pst.setString(5,"NA");
                   pst.setString(6,"NA");
                   pst.setString(7,jTextField15.getText());
                   pst.setString(8,"Collected");
                   pst.setString(9,jTextField12.getText());
                   pst.setString(10,jTextField9.getText());
                   pst.execute();
                   conn.close();
               }
               catch(Exception e){
                   JOptionPane.showMessageDialog(null,e);
               } 
          

                 
         printbill();
      }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased




        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        // TODO add your handling code here:
        jTable1();
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        // TODO add your handling code here:
        jTable1();
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        // TODO add your handling code here:
        jTable1();
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jTable();
            jTextField7.requestFocus();
            
        }
         
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        jTable1();        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        jTextField2.setText((String)jTable2.getValueAt(jTable2.getSelectedRow(), 0));
        jTextField3.setText((String)jTable2.getValueAt(jTable2.getSelectedRow(), 1));
        jTextField6.setText((String)jTable2.getValueAt(jTable2.getSelectedRow(), 2));
        jTextField4.setText((String)jTable2.getValueAt(jTable2.getSelectedRow(), 3));
        cal();
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
cal(); 
// TODO add your handling code here:
    }//GEN-LAST:event_jTextField7KeyReleased

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
cal()  ;      // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
cal(); 
// TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        if(scheck()){
            cal();
           sl=jTable1.getRowCount();
           sl++;

DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
model.addRow(new Object[]{String.valueOf(sl),jTextField2.getText(),jTextField3.getText(),jTextField4.getText(),jTextField7.getText(),jTextField6.getText(),jTextField1.getText(),jTextField5.getText()});


             jTextField14.setText(gettotal());
             if(jCheckBox1.isSelected()==true){
                jTextField16.setText(String.valueOf( (Float.valueOf(jTextField14.getText()) *0.18)));
            }else{
                jTextField16.setText("0");
            }
             jTextField17.setText(String.valueOf((Float.valueOf(jTextField14.getText())+(Float.valueOf(jTextField16.getText())))));
             jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField1.setText("0");
            jTextField5.setText("0");
            jTextField6.setText("0");
            jTextField7.setText("0");
        }  
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
            if(jCheckBox1.isSelected()==true){
                jTextField16.setText(String.valueOf( (Float.valueOf(jTextField14.getText()) *0.18)));
            }else{
                jTextField16.setText("0");
            }
                         jTextField17.setText(String.valueOf((Float.valueOf(jTextField14.getText())+(Float.valueOf(jTextField16.getText())))));

        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed


if(jTable1.getSelectedRow()==-1){
            if(jTable1.getRowCount()>0){
                JOptionPane.showMessageDialog(null,"Selct a Row First ");
            }
        }
        else{
        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
        model.setValueAt(jTextField2.getText(), jTable1.getSelectedRow(), 1);
        model.setValueAt(jTextField3.getText(), jTable1.getSelectedRow(), 2);
        model.setValueAt(jTextField4.getText(), jTable1.getSelectedRow(), 3);
        model.setValueAt(jTextField7.getText(), jTable1.getSelectedRow(), 4);
        model.setValueAt(jTextField6.getText(), jTable1.getSelectedRow(), 5);
        model.setValueAt(jTextField1.getText(), jTable1.getSelectedRow(), 6);
        model.setValueAt(jTextField5.getText(), jTable1.getSelectedRow(), 7);
        
        jTextField14.setText(gettotal());
        cal();
        jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField1.setText("0");
            jTextField5.setText("0");
            jTextField6.setText("0");
            jTextField7.setText("0");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        
        jTextField2.setText((String)jTable1.getValueAt(jTable1.getSelectedRow(), 1));
        jTextField3.setText((String)jTable1.getValueAt(jTable1.getSelectedRow(), 2));
        jTextField4.setText((String)jTable1.getValueAt(jTable1.getSelectedRow(), 3));
        jTextField7.setText((String)jTable1.getValueAt(jTable1.getSelectedRow(), 4));
        jTextField6.setText((String)jTable1.getValueAt(jTable1.getSelectedRow(), 5));
        jTextField1.setText((String)jTable1.getValueAt(jTable1.getSelectedRow(), 6));
        jTextField5.setText((String)jTable1.getValueAt(jTable1.getSelectedRow(), 7));
        
        cal();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
        model.removeRow(jTable1.getSelectedRow());
        setsl();
jTextField14.setText(gettotal());
        cal();  
        jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField1.setText("0");
            jTextField5.setText("0");
            jTextField6.setText("0");
            jTextField7.setText("0");// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           
            jTextField13.requestFocus();
        }


// TODO add your handling code here:
    }//GEN-LAST:event_jTextField12KeyPressed

    private void jTextField9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           
            jTextField12.requestFocus();
        }
    }//GEN-LAST:event_jTextField9KeyPressed

    private void jTextField13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           
            jTextField11.requestFocus();
        } // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13KeyPressed

    private void jTextField11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           
            jTextField18.requestFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11KeyPressed

    private void jTextField18KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField18KeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           
            jTextField3.requestFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField18KeyPressed

    private void jTextField7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           
            jTextField1.requestFocus();
            
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7KeyPressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
      if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           
            additm();
            jTextField3.requestFocus();
        } 
      // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField20KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField20KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField20KeyPressed

    private void jTextField19KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField19KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19KeyPressed

    private void jTextField20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField20ActionPerformed

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
            java.util.logging.Logger.getLogger(invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new invoice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
