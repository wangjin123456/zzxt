package com.zzxt.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.zzxt.servicehttp.OrganizationServiceHttp;
import com.zzxt.servicehttp.impl.OrganizationServiceHttpImpl;

/**
 * 管理组织架构查询工具
 * @author think
 *
 */

public class MangerOrgUtil {
	private static Logger logger = Logger.getLogger(MangerOrgUtil.class);
	private static OrganizationServiceHttp  organizationServiceHttp = new OrganizationServiceHttpImpl();
 	
	
	String appUuid = Global.getConfig(Global.ICE_ZZXT_APP_UUID_KEY);
	String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);
	
	String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
	

	public static String getManageOrgVo(String manageOrgUuid, HttpServletResponse response,
			HttpServletRequest request) {
 
		HttpSession session = request.getSession();
		
		String sessionId = session.getId();
		
		logger.info("sessionId:" + sessionId);
		
		String accountUuid = Global.getUserInfoKey(request.getSession());
		
		String accessToken = Global.getAccessToken(accountUuid);
		
		
		
		//orgUuid="a78820c4-d6db-491b-bcfd-1e476a21a9c5";
		if (manageOrgUuid == null || "".equals(manageOrgUuid)) {
			return null;
		}
	
		String jsonResult = organizationServiceHttp.getOrgJson(accessToken, manageOrgUuid, "0");

		return jsonResult;
	}
	
	
}
