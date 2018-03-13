package com.zzxt.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzxt.bean.UserPowerBean;
import com.zzxt.service.UserPowerService;
import com.zzxt.servicehttp.AuthServiceHttp;
import com.zzxt.servicehttp.OrganizationServiceHttp;
import com.zzxt.util.Global;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

public class UserPowerController extends BaseController {

	
	private static Logger logger = Logger.getLogger(UserPowerController.class.getName());

	
	@Autowired
	UserPowerService userPowerService;
	

	@Autowired
	OrganizationServiceHttp organizationServiceHttp;
	
	@Autowired
	private AuthServiceHttp authServiceHttp;
	
	
	/*
	 * @Auth zhao
	 * @Date 2017-11-26 18:00
	 * @Param orgUuid
	 * @Desc 权限模块使用，获取管理组织架构
	 */
	
	@RequestMapping("/getOrgStructure")
	public void getOrgStructure(HttpServletResponse response, HttpServletRequest request, String orgUuid) {

		if(orgUuid == null || orgUuid.trim().equals("") ==  true) {
			orgUuid = "0";
		}
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数 
		
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		
		logger.info("sessionId:" + sessionId);
		
		String accountUuid = Global.getUserInfoKey(session);
		
		String accessToken = Global.getAccessToken(accountUuid);
		if(accessToken == null || accessToken.trim().equals("")) {
			
			String message = Global.RELOGIN_HINT;
			
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message);
			
			jsonToCallback(jsonpCallback, protocol, response);
		}
		
		String jsonResult = organizationServiceHttp.getOrgJson(accessToken, orgUuid, Global.STRUCTURE_TYPE_ORG);
		
		// jsonResult = Global.getProtocol(jsonResult);
		
		logger.info("---------组织架构树形列表--------" + jsonResult);
		
//		if(jsonResult == null || jsonResult.trim().equals("")) {
//			
//			String message = "管理组织架构列表为空!";
//			
//			Global.myLogger.add("getOrgStructure", "false", message);
//		} else {
//			
//			Global.myLogger.add("getOrgStructure", "true");
//		}
		 
		jsonToCallback(jsonpCallback, jsonResult, response);
	}

	
	
	
	/*
	 * @Auth zhao
	 * @Date 2017-11-26 18:00
	 * @Param orgUuid
	 * @Desc 获取管理员组织架构员工列表
	 */
	@RequestMapping("/getOrgStructureUsers")
	public void getOrgStructureUsers(HttpServletResponse response,HttpServletRequest request, String orgUuid) {
	
		if(orgUuid == null || orgUuid.trim().equals("") ==  true) {
			
			return;
		}
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数 
		
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		
		logger.info("sessionId:" + sessionId);
		
		String accountUuid = Global.getUserInfoKey(request.getSession());
		
		String accessToken = Global.getAccessToken(accountUuid);
		if(accessToken == null || accessToken.trim().equals("")) {
			
			String message = Global.RELOGIN_HINT;
			
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message);
			
			jsonToCallback(jsonpCallback, protocol, response);
		}
		
		String content = organizationServiceHttp.getOrgAgents(accessToken, orgUuid);
		
		String jsonResult = Global.getProtocol(content);
		
		logger.info("---------组织架员工列表--------" + jsonResult);
		
		// jsonToPage(jsonResult, response);
		
//		
//		if(Global.isICESuccessed(jsonResult)) {
//			
//			Global.myLogger.add("getOrgStructureUsers", "true");
//		} else {
//			
//			String message = Global.getICEMessage(jsonResult);
//			
//			Global.myLogger.add("getOrgStructureUsers", "false", message);
//		}
		
		jsonToCallback(jsonpCallback, jsonResult, response);
	}
	
	
	@RequestMapping("/showUsers")
	public void showUsers(HttpServletResponse response,HttpServletRequest request, String orgUuid) {
	
		String jsonpCallback = request.getParameter("callback");//客户端请求参数 
		
		List<UserPowerBean> userList = userPowerService.showUsers(orgUuid);
		if(userList != null) {
			
			String content = JSON.toJSONString(userList);
			
			String jsonResult = Global.getProtocol(content);
			
			// jsonToPage(jsonResult, response);
			
			// Global.myLogger.add("showUsers", "true");
			
			jsonToCallback(jsonpCallback, jsonResult, response);
		}
		
		String message = "获取用户列表为空!";
		
		String jsonResult = Global.getProtocol(Global.ICE_UNKNOW, message);
		
		// jsonToPage(jsonResult, response);
		
		// Global.myLogger.add("showUsers", "false", message);
		
		jsonToCallback(jsonpCallback, jsonResult, response);
	}
	
	
	@RequestMapping("/addUsers")
	public void addUsers(HttpServletResponse response,HttpServletRequest request, String orgUuid, String users) {
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数 
		
		int result = userPowerService.addUsers(orgUuid, users);
		
		String message = "";
		String jsonResult = "";
		
		if(result == Global.FAILD) {
			 
			message = "添加用户失败";
			jsonResult = Global.getProtocol(Global.ICE_UNKNOW, message);
		}
		else {
			 
			message = "添加用户成功";
		    jsonResult = Global.getProtocol(Global.ICE_OK, message);
		}
		
		// jsonToPage(jsonResult, response);
		
		if(Global.isICESuccessed(jsonResult)) {
			
			Global.myLogger.add("addUsers", "true");
		} else {
			
			message = Global.getICEMessage(jsonResult);
			
			Global.myLogger.add("addUsers", "false", message);
		}
		
		
		jsonToCallback(jsonpCallback, jsonResult, response);
	}
	
	
	@RequestMapping("/removeUsers")
	public void removeUsers(HttpServletResponse response,HttpServletRequest request, String orgUuid, List<String> ids) {
	
		String jsonpCallback = request.getParameter("callback");//客户端请求参数 
		
		boolean ok = userPowerService.removeUsers(orgUuid, ids);
		
		String message = "";
		String jsonResult = "";
		if(ok == false) {
			
			message = "删除用户失败";
			jsonResult = Global.getProtocol(Global.ICE_UNKNOW, message);
		}
		else {
			 
			message = "删除用户成功";
			jsonResult = Global.getProtocol(Global.ICE_OK, message);
			
		}
		
		// jsonToPage(jsonResult, response);
		
		
		if(Global.isICESuccessed(jsonResult)) {
			
			Global.myLogger.add("removeUsers", "true");
		} else {
			
			message = Global.getICEMessage(jsonResult);
			
			Global.myLogger.add("removeUsers", "false", message);
		}
		
		jsonToCallback(jsonpCallback, jsonResult, response);
	}
	
	
	@RequestMapping("/doSearchUser")
	public void doSearchUser(HttpServletResponse response,HttpServletRequest request, String orgUuid, String user) {
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数 
		
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		
		logger.info("sessionId:" + sessionId);
		
		String accountUuid = Global.getUserInfoKey(request.getSession());
		
		String accessToken = Global.getAccessToken(accountUuid);
		if(accessToken == null || accessToken.trim().equals("")) {
			
			String message = Global.RELOGIN_HINT;
			
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message);
			
			jsonToCallback(jsonpCallback, protocol, response);
		}
		
	
		String userInfo = userPowerService.doSearchUser(accessToken, orgUuid, user);
		
		String jsonResult = Global.getProtocol(Global.ICE_OK, "success", false, userInfo);
		
		jsonToCallback(jsonpCallback, jsonResult, response);
	}
	
	
	
	@RequestMapping("/setPower")  // 向用户授权 1证照 2电子签章 3证照和电子签章
	public void setPower(HttpServletResponse response,HttpServletRequest request, String orgUuid, Integer uid, Integer power) {
	
		String jsonpCallback = request.getParameter("callback");//客户端请求参数 
		
		boolean ok = userPowerService.updatePower(orgUuid, uid, power);
		
		String message = "";
		String jsonResult = "";
		if(ok == false) {
			
			message = "设置权限失败";
			jsonResult = Global.getProtocol(Global.ICE_UNKNOW, message);
		}
		else {
			 
			message = "设置权限成功";
			jsonResult = Global.getProtocol(Global.ICE_OK, message);
			
		}
		
		// jsonToPage(jsonResult, response);
		
		if(Global.isICESuccessed(jsonResult)) {
			
			Global.myLogger.add("setPower", "true");
		} else {
			
			message = Global.getICEMessage(jsonResult);
			
			Global.myLogger.add("setPower", "false", message);
		}
		
		jsonToCallback(jsonpCallback, jsonResult, response);
	}


	@RequestMapping("/setSubLevel") // 向用户授权 true 子级允许 false 子级禁止
	public void setSubLevel(HttpServletResponse response,HttpServletRequest request, String orgUuid, Integer uid, Integer sub) {
		
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数 
		
		boolean ok = userPowerService.updateSubPower(orgUuid, uid, sub);
		
		String message = "";
		String jsonResult = "";
		if(ok == false) {
			
			message = "设置子级权限失败";
			jsonResult = Global.getProtocol(Global.ICE_UNKNOW, message);
		}
		else {
			 
			message = "设置子级权限成功";
			jsonResult = Global.getProtocol(Global.ICE_OK, message);
		}
		
		// jsonToPage(jsonResult, response);
		
		
		if(Global.isICESuccessed(jsonResult)) {
			
			Global.myLogger.add("setSubLevel", "true");
		} else {
			
			message = Global.getICEMessage(jsonResult);
			
			Global.myLogger.add("setSubLevel", "false", message);
		}
		
		jsonToCallback(jsonpCallback, jsonResult, response);
	}

}
