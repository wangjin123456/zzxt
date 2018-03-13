package com.zzxt.servicehttp.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zzxt.servicehttp.AdministrativeDivisionServiceHttp;
import com.zzxt.util.Global;
import com.zzxt.util.IOkHttpUtil;
import com.zzxt.util.TsSignAppid;
@Service("AdministrativeDivisionServiceHttp")
public class AdministrativeDivisionServiceHttpImpl implements  AdministrativeDivisionServiceHttp{
	
	@Override
	public String getAdministrativeDivision(String token,String pid) {
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		Map<String,String>pamMap=new HashMap<String,String>();
		TsSignAppid.get(pamMap);
		pamMap.put("token", token);
		pamMap.put("pid", pid);
		String url	=	apiURL+"regionms/administrativeDivision";
		
		String resultValue=IOkHttpUtil.sendGet(url, pamMap);
		
		
		return resultValue;
	}
}
