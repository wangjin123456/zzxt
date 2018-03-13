package com.zzxt.servicehttp;


/**
 * 
 * @Description: 时间提醒设置
 */
public interface TimeReminderServiceHttp {
	
	
	public String setTimeReminder(String oaList, String time, String days, String body, String corpId, String user, String name);
	
	public String sendEmail(String recipients, String body, String corpId, String username, String name);
	
	public String selectEmployee(String accessToken, String keyword);
	
}
 