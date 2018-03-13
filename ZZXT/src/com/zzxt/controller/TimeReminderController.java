package com.zzxt.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzxt.entity.LicenseEntity;
import com.zzxt.service.LicenseService;
import com.zzxt.servicehttp.TimeReminderServiceHttp;
import com.zzxt.util.Global;



/**
 * 设置时间节点
 * @author Administrator
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TimeReminderController extends BaseController{
    
	private static Logger logger = Logger.getLogger(TimeReminderController.class.getName());

	@Autowired
	private TimeReminderServiceHttp timeReminderServiceHttp;
	 
    @Autowired
	LicenseService licenseService;
	
	/**
	 * 时间提醒设置， 在时间提醒后发送邮件
	 * @param timeBean
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/setTimeReminder")
	@ResponseBody
	public void setTimeReminder(HttpServletRequest request,HttpServletResponse response, Integer lid, String oaList, String time, String days, String body){
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数 
		
		if(oaList == null || "".equals(oaList.trim()) || 
		   time == null || "".equals(time.trim()) ||
		   days == null || "".equals(days.trim()) || 
		   body == null || "".equals(body.trim())) {
			
		 	String message = "时间提醒所需要参数不能为空!";
		 	String jsonResult = Global.getProtocol(Global.ICE_UNKNOW, message);
    		
		 	jsonToCallback(jsonpCallback, jsonResult, response);
		}
		
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		
		logger.info("sessionId:" + sessionId);
		
		String accountUuid = Global.getUserInfoKey(request.getSession());
		if(accountUuid == null || accountUuid.trim().equals("")) {
			
			String message = Global.RELOGIN_HINT;
			
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message);
			
			jsonToCallback(jsonpCallback, protocol, response);
		}
		
		String corpId = Global.getUserCorpId(accountUuid);
		String user = Global.getUserName(accountUuid);
		String name = Global.getName(accountUuid); 
		
		
//		int len = time.length();
//		
//		String newTime = "";
//		
//		if(len >= 10) {
//			
//			newTime = time.substring(0, 10);
//		} else {
//		
//			String message = "时间提醒时间参数错误!" + time;
//		 	String jsonResult = Global.getProtocol(Global.ICE_UNKNOW, message);
//    		
//		 	jsonToCallback(jsonpCallback, jsonResult, response);
//		}
//		
//		newTime += Global.TIME_REMINDER_TIME;
//		
//		time = newTime;
		
		logger.info("提醒时间:" + time);
		
	    Date nowDate = new Date();
	    Date date = null;
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    
	    try {
	    	
	    		date = dateFormat.parse(time);
			
			logger.info(date);
	        //调用Date里面的before方法来做判断
	        boolean flag = date.before(nowDate);

	        if (flag) {
	            
	        		String message = "该日期不能早于现在!";
	    			
	        		String jsonResult = Global.getProtocol(Global.ICE_UNKNOW, message);
	    			
	    			jsonToCallback(jsonpCallback, jsonResult, response);
	        }
	        
	    } catch(Exception e) {
	    	
	    		e.printStackTrace();
	    }
	    
	    LicenseEntity licenseEntity = new LicenseEntity();
	    licenseEntity.setId(lid);
	    licenseEntity.setReminder_oa(oaList);
	    licenseEntity.setReminder_expire(date);
	    licenseEntity.setReminder_days(Integer.parseInt(days));
	    licenseEntity.setReminder_content(body);
	    
	    int r = licenseService.updateReminder(licenseEntity);
	    
	    if(r <= 0) {
	    	
	    		logger.info("时间提醒信息保存失败");
	    }
	    
	    
	    String jsonResult = timeReminderServiceHttp.setTimeReminder(oaList, time, days, body, corpId, user, name);
	    
        // String iceProtocol = Global.getIceProtocolInfo(jsonResult);
       
        logger.info(jsonResult);
        
        jsonToCallback(jsonpCallback, jsonResult, response);

    }
	
	
	@RequestMapping("/doTimeReminderCallback")
	@ResponseBody
	public void doTimeReminderCallback(HttpServletRequest request,HttpServletResponse response, String oaList, String body, String corpId, String user, String name) {
		 
		logger.info("=======================时间提醒 callback (doTimeReminderCallback) 被调用 看来时间点被成功触发==========================");
		
		logger.info("时间提醒 callback 被调用发邮件所需要参数信息");
		logger.info("oaList:" + oaList );
		logger.info("body:" + body);
		logger.info("corpId:" + corpId);
		logger.info("userName:" + user);
		logger.info("name:" + name);
		
		
		if(oaList == null || "".equals(oaList.trim())) {
			
			String message = "发时间提醒邮件所需要参数(OA列表)不能为空!";
		 	String jsonResult = Global.getProtocol(Global.ICE_UNKNOW, message);
    		
		 	 jsonToPage(jsonResult, response);
		}
		
		
		if(body == null || "".equals(body.trim())) {
			
			String message = "发时间提醒邮件正文不能为空!";
		 	String jsonResult = Global.getProtocol(Global.ICE_UNKNOW, message);
    		
		 	 jsonToPage(jsonResult, response);
		}
		
		if(corpId == null || "".equals(corpId.trim())) {
			
			String message = "发时间提醒邮件租户ID不能为空!";
		 	String jsonResult = Global.getProtocol(Global.ICE_UNKNOW, message);
    		
		 	 jsonToPage(jsonResult, response);
		}
		  
		String jsonResult = timeReminderServiceHttp.sendEmail(oaList, body, corpId, user, name);
		
		logger.info(jsonResult);
		 
		jsonToPage(jsonResult, response);
	}


	
	@RequestMapping("/selectEmployee")
	@ResponseBody
	public void selectEmployee(HttpServletRequest request,HttpServletResponse response, String keyword){
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数

		String accountUuid = Global.getUserInfoKey(request.getSession());
		
		String accessToken = Global.getAccessToken(accountUuid);
		logger.info("accessToken:" + accessToken);
		
		if(accessToken == null || accessToken.trim().equals("")) {
			
			String message = Global.RELOGIN_HINT;
			
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message);
			
			jsonToCallback(jsonpCallback, protocol, response);
		}
		
	    String jsonResult = timeReminderServiceHttp.selectEmployee(accessToken, keyword);
	    
	    if(!Global.isICESuccessed(jsonResult)) {
	    	
		    	String message = Global.getICEMessage(jsonResult);
		    	
		    	String jsonString = Global.getProtocol(Global.ICE_UNKNOW, message);
		    	
		    	jsonToCallback(jsonpCallback, jsonString, response);
	    }
	    
	    String content = Global.getICEContent(jsonResult);
	    
	    if(content == null || content.trim().equals("")) {
	    	
		    	String message = "协议包体不能为空!";
		    	
		    	String jsonString = Global.getProtocol(Global.ICE_UNKNOW, message);
		    	
		    	jsonToCallback(jsonpCallback, jsonString, response);
	    }
	    
	    
	    JSONArray a  = JSONArray.parseArray(content);
	    JSONArray b = new JSONArray();
	    
	    int len = a.size();
	    
	    for(int i = 0; i < len; i ++) {
	    	
		    	JSONObject o = a.getJSONObject(i);
		    	
		    	String username = o.getString("username");
		    	String name = o.getString("name");
		    	
		    	JSONObject temp = new JSONObject();
		    	temp.put("uid", username);
		    	temp.put("uname", name);
		    	temp.put("rType", "0");
		    	
		    	b.add(temp);
	    }
	    
	    String jsonString = b.toJSONString();
	    
	    String jsonProtocol = Global.getProtocol(jsonString);
	       
		jsonToCallback(jsonpCallback, jsonProtocol, response);
		
	}
	
}
