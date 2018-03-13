package com.zzxt.servicehttp.impl;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzxt.bean.UserAccountBean;
import com.zzxt.controller.LoginController;
import com.zzxt.redis.RedisCacheUtil;
import com.zzxt.servicehttp.LoginServiceHttp;
import com.zzxt.servicehttp.OrganizationServiceHttp;
import com.zzxt.util.Global;
import com.zzxt.util.IOkHttpUtil;
import com.zzxt.util.Md5Util;
import com.zzxt.util.TimestampUtil;
import com.zzxt.util.TsSignAppid;

/**
 * 
 * @ClassName: LoginServiceHttpImpl
 * @Description: LoginServiceHttp 接口的实现类
 * @author wangdekun
 * @date 2017年8月23日 上午11:11:35
 *
 */
@Service("LoginServiceHttp")
public class LoginServiceHttpImpl implements LoginServiceHttp {
	private static Logger logger = Logger.getLogger(LoginController.class.getName());

	@Autowired
	private RedisCacheUtil redisCacheUtil;

	
	@Autowired
	private OrganizationServiceHttp organizationServiceHttp;
	
	
	/**
	 * 
	 * @param uer
	 *            账户信息
	 * @return 返回处理结果信息
	 */
	@Override
	public String matchAccount(UserAccountBean user) {
	
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		String url = apiURL + "orgms/loginAccount";
		// 封装 请求参数
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("username", user.getUsername());// 用户名
		paramMap.put("password", user.getPassword());// 密码
		logger.info("密码："+ user.getPassword());
		if (user.getCorpId() != null && !"".equals(user.getCorpId())) {
			paramMap.put("corpId", user.getCorpId());// 租户id
		} else {
			logger.info("没有输入 corpId 参数");
		}
		// 地址栏附加参数
		HashMap<String, String> paramAddress = new HashMap<>();
		
		TsSignAppid.get(paramAddress);
		logger.info("Login ICE_POST Request->Appid&sign&ts:"+paramAddress);
		
		// 发起post 获得数据数据
		String json = IOkHttpUtil.sendPost(url, paramAddress, paramMap);
		
		logger.info("Login Recv Message:"+ json);
		
		
		return json;

	}
	
	
	public String pageQuerySuperUser(String accessToken, String user, Integer pageIndex) {
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		String url = apiURL + "groupTag/tag/memberTags";
		
		String token = accessToken;

		// 封装 请求参数
		HashMap<String, String> paramMap = new HashMap<>();
		
		String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);

		String ts = TimestampUtil.getTs();
		paramMap.put("ts", ts);
		paramMap.put("sign", Md5Util.signMd5(ts));
		paramMap.put("appID", appId);
		paramMap.put("member", user);
		paramMap.put("token", token);
		
		paramMap.put("pageSize", "100");
		
		paramMap.put("currentPage", String.valueOf(pageIndex));
		
		String jsonResult = IOkHttpUtil.sendGet(url, paramMap);
		
		return jsonResult;
	}
	
	
	// 递归查询
	public boolean bySelfCheckZZXTSupperUser(String accessToken, String user, Integer index) {
		
		String jsonResult =  this.pageQuerySuperUser(accessToken, user, index);
		
		if(jsonResult == null || jsonResult.trim().equals("") == true) {
			
			return false;
		}
		 
		logger.info("ZZXT SupperUser INFO :" + jsonResult);
		
		JSONObject jsonObject = JSONObject.parseObject(jsonResult);
		if(jsonObject == null) {
			
			return false;
		}
				
		int success = Integer.valueOf(jsonObject.getString("code").toString());
		
		if(success != Global.SUCCESS0) {
			
			return false;
		}
		
		JSONArray contentList = jsonObject.getJSONArray("content");

		int len = contentList.size();
		
		for(int c = 0; c < len; c ++) {
			
			JSONObject contentObject = contentList.getJSONObject(c);
			if(contentObject != null) {
				
				int id = contentObject.getIntValue("id");
				
				String number = Global.getConfig(Global.ICE_ZZXT_NUMBER_KEY);
				 
				if(id == Integer.valueOf(number).intValue()) {
					
					return true;
				}
			}
		}
		
		
		String  psize = jsonObject.getString("pageSize").toString();
		
		logger.info("checkZZXTSupperUser pageInfo-> pageSize:" + psize);

		String  tpage = jsonObject.getString("totalPage").toString();

		logger.info("checkZZXTSupperUser pageInfo-> totalPage:" + tpage);

		logger.info("checkZZXTSupperUser pageInfo-> currentPage(pageIndex):" + index);

		
		Integer totalPage = Integer.valueOf(tpage);
		
		for(int i = index + 1; i < totalPage; i ++) {
		
			return this.bySelfCheckZZXTSupperUser(accessToken, user, i);
			
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param uer
	 *            账户信息
	 * @return 返回处理结果信息
	 */
	@Override
	public boolean checkZZXTSupperUser(String accessToken, String user) {
	
		return this.bySelfCheckZZXTSupperUser(accessToken, user, 1);
	}
	
	

}
