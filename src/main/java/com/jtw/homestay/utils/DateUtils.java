/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtw.homestay.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author  jml
 * @date    2020-3-20 10:24:23
 * @version V1.0
 * @desc    
 */
public class DateUtils {
    
    public static Integer getTimeDif(String startTime,String overTime){
        
        System.out.println(startTime + "-----------"+ overTime);
        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
        Date startdate = null;
        Date overdate = null;
        try {
            startdate = dateFormat.parse(startTime);
            overdate = dateFormat.parse(overTime);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        long overSecond = overdate.getTime();
        long startSecond = startdate.getTime();
        long dif = overSecond - startSecond;
        Integer day = Integer.parseInt((dif/(1000*60*60*24)+""));
        return day;
        
    }

}
