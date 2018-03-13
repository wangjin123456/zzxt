package com.zzxt.servicehttp.impl;
import java.util.HashMap;
import java.util.Map;

/**
 * 岗位成员相关
 */
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zzxt.servicehttp.JobMemberServiceHttp;
import com.zzxt.util.Global;
import com.zzxt.util.IOkHttpUtil;
import com.zzxt.util.Md5Util;
import com.zzxt.util.TimestampUtil;


@Service("JobMemberServiceHttp")
public class JobMemberServiceHttpImpl implements JobMemberServiceHttp {
	/**
	 * 获取岗位成员
	 *//**
	 * http://icetest.colourlife.net:8081/v1/orgms/job/search?
	 * ts=1508205333&
	 * sign=bd2b180eb0e015a565af6f47cbceb2f2&
	 * appID=ICEZZXT0-C725-4567-83E2-A839C785A808&
	 * token=f9d98e17ace2421782c84fc41a50f3d0
	 */
 
	
	@Override
	public String getJobMemberJson(Map<String, String> map) {
		
		String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.putAll(map);
		String ts = TimestampUtil.getTs();
		String sign = Md5Util.signMd5(ts);
		paramMap.put("ts", ts);
		paramMap.put("sign", sign);
		paramMap.put("appID", appId);
		paramMap.put("status", "0");
		String url = apiURL + "orgms/job/search";
		String json = IOkHttpUtil.sendGet(url, paramMap);
		//Logger.info(json);
		JSONObject jsonObject = JSONObject.parseObject(json);
	    /*"pageSize": 10, 
	    "totalPage": 4278, 
	    "currentPage": 1, 
	    "totalRecord": 42775*/
	
		Integer totalPage = jsonObject.getInteger("totalPage");
		Integer totalRecord = jsonObject.getInteger("totalRecord");
		
		return jsonObject.getString("content");
	}

}
