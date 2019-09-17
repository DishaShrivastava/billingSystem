/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billingsys;

import java.util.Calendar;

/**
 *
 * @author sam
 */
public class Datetime {
    public static String date(){
    String d="",m="",y="",h="",min="";
    Calendar cal= Calendar.getInstance();
    d=String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
    m=String.valueOf(cal.get(Calendar.MONTH)+1);
    y=String.valueOf(cal.get(Calendar.YEAR));
    h=String.valueOf(cal.get(Calendar.HOUR));
    min=String.valueOf(cal.get(Calendar.MINUTE));
    String date=d+"-"+m+"-"+y;
    return date;
    
}
public static String time(){
    String h="",min="",m="AM";
    Calendar cal= Calendar.getInstance();
    h=String.valueOf(cal.get(Calendar.HOUR));
    if(h.length()==1){
        h="0"+h;
    }
    min=String.valueOf(cal.get(Calendar.MINUTE));
    if(min.length()==1){
        min="0"+min;
    }
    if(cal.get(Calendar.AM_PM)==1){
        m="PM";
    }
   
    String time=h+":"+min+" "+m;
    return time;
    
}
}
