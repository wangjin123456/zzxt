package com.zzxt.servicehttp.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zzxt.servicehttp.AuthServiceHttp;
import com.zzxt.util.Global;
import com.zzxt.util.IOkHttpUtil;
import com.zzxt.util.Md5Util;
import com.zzxt.util.ParseJsonResult;
import com.zzxt.util.TimestampUtil;
import com.zzxt.util.TsSignAppid;

/**
 * 
 * @ClassName: AuthServiceHttpImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wangdekun
 * @date 2017年8月29日 下午5:40:21
 *
 */
@Service("AuthServiceHttp")
public class AuthServiceHttpImpl implements AuthServiceHttp {
	private static Logger logger = Logger.getLogger(AuthServiceHttpImpl.class.getName());

	
	@Override
	public String getToken(String corp_uuid) {

		String appUuid = Global.getConfig(Global.ICE_ZZXT_APP_UUID_KEY);
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		String timestamp = TimestampUtil.getTs();
		String url = apiURL + "authms/auth/app";
		// form表单参数
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("timestamp", timestamp);
		paramMap.put("app_uuid", appUuid);
		paramMap.put("signature", Md5Util.signatureMd5(timestamp));
		paramMap.put("corp_uuid", corp_uuid);
		// 地址栏附加参数
		
		HashMap<String, String> paramAddress = new HashMap<>();
		TsSignAppid.get(paramAddress);
		logger.info("Appid&sign&ts:"+paramAddress);
		
		logger.info("Auth ICE_POST Request->Appid&sign&ts:"+paramAddress);
		
		String jsonResult = IOkHttpUtil.sendPost(url, paramAddress, paramMap);
		
		logger.info("Auth Recv Message:"+ jsonResult);
		
		// 判断返回的信息是否成功
		Boolean success = ParseJsonResult.success(jsonResult);
		if (success) {
			// 从 jsonResult 中获取 accessToken字符串
			return JSONObject.parseObject(JSONObject.parseObject(jsonResult).getString("content"))
					.getString("accessToken");
		} else {
		
			logger.warn("未能获取到accessToken数据");
			return null;
		}
	}

	@Override
	public String doAuth(String corpUuid) {
		
		String appUuid = Global.getConfig(Global.ICE_ZZXT_APP_UUID_KEY);
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		String timestamp = TimestampUtil.getTs();
		String url = apiURL + "authms/auth/app";
		// form表单参数
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("timestamp", timestamp);
		paramMap.put("app_uuid", appUuid);
		paramMap.put("signature", Md5Util.signatureMd5(timestamp));
		paramMap.put("corp_uuid", corpUuid);
		// 地址栏附加参数
		
		HashMap<String, String> paramAddress = new HashMap<>();
		TsSignAppid.get(paramAddress);
		logger.info("Appid&sign&ts:"+paramAddress);
		
		logger.info("Auth ICE_POST Request->Appid&sign&ts:"+paramAddress);
		
		String jsonResult = IOkHttpUtil.sendPost(url, paramAddress, paramMap);
		
		return jsonResult;
	}
	
	@Override
	public String doAuthV1() {
		
		String appKey = Global.getConfig(Global.ICE_ZZXT_V1_APPKEY_KEY);
		String appSecretKey = Global.getConfig(Global.ICE_ZZXT_V1_APPSECRET_KEY);
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		String timestamp = TimestampUtil.getTs();
		String url = apiURL + "jqfw/app/auth";
		// form表单参数
		HashMap<String, String> paramMap = new HashMap<>();

		paramMap.put("appkey", appKey);
		paramMap.put("timestamp", timestamp);
		
		String signatrue = Md5Util.signatureMd5(appKey, timestamp, appSecretKey);
		paramMap.put("signature", signatrue);
		// 地址栏附加参数
		
		HashMap<String, String> paramAddress = new HashMap<>();
		
		
		String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);
		String token = Global.getConfig(Global.ICE_ZZXT_TOKEN_KEY);
		
		paramAddress.put("ts", timestamp);
		
		String sign = Md5Util.signMd5(appId, timestamp, token);
		
		paramAddress.put("sign", sign);
		
		paramAddress.put("appID", appId);
		
		String jsonResult = IOkHttpUtil.sendPost(url, paramAddress, paramMap);
		
		return jsonResult;
	}
	
	
	@Override
	public String getApp(String token) {
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);

		Map<String,String>dataMap=new HashMap<String,String>();
		dataMap.put("access_token", token);
		String url = apiURL + "authms/app/search";
		getTsSignAppid(dataMap);
		return IOkHttpUtil.sendGet(url, dataMap);
	}
	/**
	 * @Description 封装数据
	 * 
	 *              sign(签名)、ts(时间戳)、appID(调用者app id)
	 *
	 * @param param
	 */
	private void getTsSignAppid(Map<String, String> param) {
		
		String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);

		String ts = TimestampUtil.getTs();
		param.put("ts", ts);
		param.put("sign", Md5Util.signMd5(ts));
		param.put("appID", appId);
	}
	
	
	@Override
	public String getZhongShuiCaCert(Map<String, String> paramMap) {
		
		String  appId = Global.getConfig(Global.ICE_ZZXT_ZHONG_SHUI_APPID_KEY);
		
		String ts = TimestampUtil.getTs13();
		
		String appSecret = Global.getConfig(Global.ICE_ZZXT_ZHONG_SHUI_CLIENT_SECRET_KEY);
		
		String sign = Md5Util.getZhongShuiSign(appId, appSecret, ts);
		
		HashMap<String, String> baseParamMap = new HashMap<>();
		baseParamMap.put("appId", appId);
		baseParamMap.put("sign", sign);
		baseParamMap.put("timeStamp", ts);
				
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_ZHONG_SHUI_API_URL_KEY);
		
		String url = apiURL + "/cert/ca/apply";
		
		String jsonResult = IOkHttpUtil.sendPost(url, baseParamMap, paramMap);
		
		return jsonResult;
	}
	
}
