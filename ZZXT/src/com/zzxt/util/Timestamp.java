package com.zzxt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 
 * @ClassName: Timestamp
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wangdekun
 * @date 2017年8月29日 下午5:13:51
 *
 */
public class Timestamp {

	public static String get() {
		Long timestamp = System.currentTimeMillis();
		return timestamp.toString().substring(0, 10);
	}
	
	
	public static String getNowDateStr(String format)
    {
    	Date now = new Date();
    	
    	SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    	
    	String date = dateFormat.format(now);
    	
    	return date;
    }
	
	/* 
     * 将时间转换为时间戳
     */    
	 public static String Date2TimeStamp(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    /* 
     * 将时间戳转换为时间
     */
	 public static String TimeStamp2Date(String timestampString) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date(timestamp));
        return date;
	    }
	
	public static void main(String[] args) {
			System.out.println(Date2TimeStamp("2017-06-10 12:13:14"));
		
	}
}
