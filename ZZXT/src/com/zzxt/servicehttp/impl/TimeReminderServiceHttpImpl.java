package com.zzxt.servicehttp.impl;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzxt.servicehttp.TimeReminderServiceHttp;
import com.zzxt.util.Global;
import com.zzxt.util.IOkHttpUtil;
import com.zzxt.util.Md5Util;
import com.zzxt.util.TimestampUtil;
import com.zzxt.util.TsSignAppid;


/**
 * 时间提醒设置
 * @author Administrator
 *
 */
@Service("TimeReminderServiceHttp")
public class TimeReminderServiceHttpImpl implements TimeReminderServiceHttp{
	private static Logger logger = Logger.getLogger(TimeReminderServiceHttpImpl.class.getName());
    
	@Override
	public String setTimeReminder(String oaList, String time, String days, String body, String corpId, String user, String name) {
	    
		String api = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		String url = api + "tr/timeReminder/addTimeReminder";
		
		logger.info("TimeReminder URL : " + url);
		
		String serverURL = Global.getConfig(Global.SERVER_PATH_KEY);
		
		logger.info("setTimeReminder 服务器IP地址:" + serverURL);
		
		logger.info("setTimeReminder ICE接口地址:" + url);
		
		try {
			
			oaList = URLEncoder.encode(oaList, "utf-8");
			body = URLEncoder.encode(body, "utf-8");
			corpId = URLEncoder.encode(corpId, "utf-8");
			user = URLEncoder.encode(user, "utf-8");
			name = URLEncoder.encode(name, "utf-8");
			
		} catch(Exception e) {
			
			e.printStackTrace();
		
			String message = "回调地址参数编码产生异常，详细信息：" + e.getLocalizedMessage();
			
			String jsonResult = Global.getProtocol(Global.ICE_UNKNOW, message);
			
			return jsonResult;
		}
		
		String params = "oaList="+ oaList + "&body=" + body + "&corpId=" + corpId + "&user=" + user + "&name=" + name;
		
		logger.info("setTimeReminder 回调param:" + params);
		
		try {
			
			params = URLEncoder.encode(params, "utf-8");
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
				
		logger.info("setTimeReminder 回调encoe->param:" + params);
		
		String callbackURL = serverURL+ "/doTimeReminderCallback?" + params;
		
		logger.info("setTimeReminder 回调地址:" + callbackURL);

		
//		String enCallbackURL = "";
//		
//		try {
//			
//			enCallbackURL = URLEncoder.encode(callbackURL, "utf-8");
//			
//			logger.info("setTimeReminder 回调地址(编码):" + enCallbackURL);
//			
//		} catch(Exception e) {
//			
//			e.printStackTrace();
//		}
		
		
		// 封装请求参数
		Map<String,String> param = new HashMap<>(); 
		// 时间点(支持到"分") 格式 : yyyy-MM-dd HH:mm:ss
		param.put("timepoint", time);
		// 提醒方式[+之后，-之前]
		param.put("remindway", "-");
		param.put("quantity", days);
		param.put("unit", "d");
		param.put("callbackurl", callbackURL);
		param.put("circletimes", "0");
        param.put("circleinterval", "0");
        param.put("circleunit", "m");
        
		logger.info(param);
		
		TsSignAppid.get(param);
		
		// 发起get请求 
        String jsonResult = IOkHttpUtil.sendGet(url, param);
        
        logger.info("TimeReminderServiceHttp-TimeReminderServiceHttpImpl->setTimeReminder-jsonResult:" + jsonResult);
        
        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
        if(jsonObject != null) {
        	
	        	int code = jsonObject.getIntValue(Global.ICE_ZZXT_CODE_KEY);
	        	
	        	String message = jsonObject.getString("codeValue");
	        	
	        	String id = jsonObject.getString("id");
	        	
	        	String remindTime = jsonObject.getString("remindtime");
	        	
	        	String appId = jsonObject.getString("appid");
	        	
	        	// 成功，封包
	        	if(code == 0) {
	        
	        		JSONObject object = new JSONObject();
	        		
	        		object.put("id", id);
	        		object.put("remindTime", remindTime);
	        		object.put("appId", appId);
	        		
	        		String content = object.toJSONString();
	        		
	        		String info = "时间提醒设置成功";
	        		
	        		jsonResult = Global.getProtocol(Global.ICE_OK, info, true, content);
	        		
	        		logger.info("TimeReminderServiceHttp-TimeReminderServiceHttpImpl->setTimeReminder-new part->jsonResult:" + jsonResult);
	        		
	        	} else {
	        		
	        		jsonResult = Global.getProtocol(Global.ICE_UNKNOW, message);
	        	}
        }
        
        return jsonResult;
    }
	
	
	
	@Override
	public String sendEmail(String recipients, String body, String corpId, String username, String name) {
		
		String api = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		String url = api +"newmail/mail";
		
		String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);
        String ts = TimestampUtil.getTs();
		
 		logger.info("sendEmail ICE接口地址:" + url);
        
		// 封装请求参数
		Map<String,String> param = new HashMap<>();
		param.put("title", "提醒邮件");
		param.put("body", body);
		// 是否存为草稿0否1是.
		param.put("isdraft", "0");
		// 附件数量
		param.put("accessorySum", "0");
		// 附件信息json格式
		param.put("accessory", "");
		param.put("recipients", recipients);
		param.put("corpid", corpId);
		param.put("founder", username);
		param.put("foundername", name);
		param.put("ts", ts);
		param.put("sign", Md5Util.signMd5(ts));
		param.put("appID", appId);
		
		logger.info("封装参数:" + param);
		
		// 地址栏附加参数
		Map<String, String> addressEmail = new HashMap<>();
		TsSignAppid.get(addressEmail);
		
		// 发起post请求
        String jsonResult = IOkHttpUtil.sendPost(url, addressEmail, param);
        
        logger.info("sendEmail ICE接口请求返回:" + jsonResult);
        
        return jsonResult;
	}


	@Override
	public String selectEmployee(String accessToken, String keyword) {
		String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);
		
		String api = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		String url = api +"orgms/account/searchKey";
		
		String token = accessToken; 
		String ts = TimestampUtil.getTs();
		String sign = Md5Util.signMd5(ts);
		// 封装请求参数
		Map<String,String> param = new HashMap<>();
		param.put("keyword", keyword);
		param.put("ts", ts);
		param.put("sign", sign);
		param.put("appID", appId);
		param.put("token", token);
		param.put("pageSize", "30");
		param.put("pageIndex", "1");
		
		// 发起get请求
        String jsonResult = IOkHttpUtil.sendGet(url, param);
        return jsonResult;
	  
	}
}
	