package com.zzxt.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;


/**
 * 
 * @ClassName: TimestampUtil
 * @Description: 用于向微服务获取 时间戳数据
 * @author wangdekun
 * @date 2017年8月21日 下午5:09:15
 *
 */
public class TimestampUtil {
	private static Logger logger = Logger.getLogger(TimestampUtil.class.getName());

	public static String getTs() {
 
		Date date = new Date();
		
		long unixTimestamp = date.getTime() / 1000;
		
		logger.info(unixTimestamp);
		
		String timestamp = String.valueOf(unixTimestamp);
		
		return timestamp;
	}
	
	
	public static String getTs13() {
		 
		Date date = new Date();
		 
		long timestamp1 = System.currentTimeMillis();
		
		long timestamp2 = date.getTime();
		
		long timestamp3 = Calendar.getInstance().getTimeInMillis();  
		
		logger.info("timestamp1:" + timestamp1);
		logger.info("timestamp2:" + timestamp2);
		logger.info("timestamp3:" + timestamp3);
		
		
		String timestamp = String.valueOf(timestamp1);
		
		return timestamp;
	}
}
