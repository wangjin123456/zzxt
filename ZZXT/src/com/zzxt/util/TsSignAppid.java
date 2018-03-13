package com.zzxt.util;

import java.util.Map;


/**
 * 
 * @ClassName: TsSignAppid
 * @Description: 用于封装 timestamp sign appid
 * @author wangdekun
 * @date 2017年9月11日 下午3:08:01
 *
 */
public class TsSignAppid {

	
	public static void get(Map<String, String> param) {
		
		String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);
		
		String ts = TimestampUtil.getTs();
		param.put("ts", ts);
		param.put("sign", Md5Util.signMd5(ts));
		
		param.put("appID", appId);
	}
}
