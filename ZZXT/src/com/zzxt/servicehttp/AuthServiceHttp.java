package com.zzxt.servicehttp;

import java.util.Map;

/**
 * 
 * @ClassName: AuthServiceHttp
 * @Description: 访问鉴权问服务
 * @author wangdekun
 * @date 2017年8月29日 下午5:30:45
 *
 */
public interface AuthServiceHttp {
	/*
	 * { "code": 0, "message": "", "content": { "expireTime": 1504085545559,
	 * "accessToken": "b9d032717f424ca7a68b2c1850bb87a0", "corpUuid":
	 * "a8c58297436f433787725a94f780a3c9", "appUuid":
	 * "38517b585ac74ec9868243ca49dea823", "serviceUuid": "" } }
	 */

	
	public String doAuth(String corpUuid);
	
	public String doAuthV1();
	
	String getToken(String corp_uuid);
	
	//获取对应应用
	String getApp(String token);
	
	
	public String getZhongShuiCaCert(Map<String, String> paramMap);
	
}
