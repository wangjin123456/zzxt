package com.zzxt.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zzxt.bean.UserAccountBean;
import com.zzxt.service.UserPowerService;
import com.zzxt.servicehttp.AuthServiceHttp;
import com.zzxt.servicehttp.LoginServiceHttp;
import com.zzxt.util.Global;
import com.zzxt.util.ParseJsonResult;

/**
 * 
 * @ClassName: LoginController
 * @Description: 登录 Controller
 * @author wangdekun
 * @date 2017年8月21日 下午3:15:06
 *
 */
//@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoginController extends BaseController {
	private static Logger logger = Logger.getLogger(LoginController.class.getName());

	@Autowired
	private LoginServiceHttp loginServiceHttp;
	

	@Autowired
	private AuthServiceHttp authServiceHttp;
	
	@Autowired 
	private UserPowerService userPowerService;
	


    @RequestMapping("/Login")
	public void  login(HttpServletRequest request, UserAccountBean user,
			HttpServletResponse response) throws Exception {
    	
		// 账户匹配结果信息
		String loginJson = loginServiceHttp.matchAccount(user);
		
		logger.info("1.Login->INFO :" + loginJson);

		String jsonpCallback = request.getParameter("callback");//客户端请求参数 
		
		String userName = user.getUsername();
		
		Global.myLogger.setUserName(userName);
	     
		Boolean success = ParseJsonResult.success(loginJson);

		String iceProtocol = "";
		
		
		String accountUuid = "";
		
		Map<String, Object> loginInfoMap = null;
		
		String accessToken = "";
		
		// 如果用户登录成功, 取到用户登录信息map 
		if (success == true) {			 
	
			Global.myLogger.add("Login", "true");
			
			// 如果是首次登录成功，则取用户信息生成map
			loginInfoMap = Global.getContentObject(loginJson);
			
			// 从用户登录信息中取唯一身份标识
			accountUuid = loginInfoMap.get(Global.USER_INFO_ACCOUNT_UUID).toString();

			logger.info(loginInfoMap.toString());
			
		} else {

			iceProtocol = Global.getIceProtocolInfo(loginJson);
			
			logger.info("2.Login->INFO :" + iceProtocol);
			
			//jsonToPage(iceProtocol, response);

			
			jsonToCallback(jsonpCallback, iceProtocol, response);
			
			Global.myLogger.add("Login", "false");
			
			return;
		}
		 
		
		// 从用户信息中取多租户id
		String corpId = loginInfoMap.get(Global.USER_INFO_CORP_ID).toString();
		
		
		//  使用多租户id进行鉴权
		String authJson = authServiceHttp.doAuth(corpId);
		
		// 鉴权成功，取出accessToken
		success = ParseJsonResult.success(authJson);

		// 鉴权验证失败
		if(!success) {
			
			 iceProtocol = Global.getIceProtocolInfo(authJson);
			
			 logger.info("3.Login->INFO :" + iceProtocol);
			 
			 // 原信息返回
			 // jsonToPage(iceProtocol, response);
			 
			 Global.myLogger.add("Login", "false", "鉴权失败");
			 
			 jsonToCallback(jsonpCallback, iceProtocol, response);
		}
		
		// 获取accesToken
		JSONObject contentObject = Global.getContentObject(authJson);
		
		accessToken =  contentObject.getString(Global.AUTH_INFO_ACCESS_TOKEN);
		if(accessToken != null && !accessToken.trim().equals("")) {
			
			 // 保存accessToken
			 loginInfoMap.put(Global.AUTH_INFO_ACCESS_TOKEN, accessToken);
		
		} else {
			
			String message = "用户鉴权成功，但accessToken返回失败!";
			
			iceProtocol = Global.updateIceMessage(iceProtocol, message);
			
			logger.info("4.Login->INFO :" + iceProtocol);
			
			Global.myLogger.add("Login", "false", message);
			
			// jsonToPage(iceProtocol, response);
			
			jsonToCallback(jsonpCallback, iceProtocol, response);
		}
		
		
		String authV1SJson = authServiceHttp.doAuthV1();
		
		if(Global.isICESuccessed(authV1SJson)) {
			
			JSONObject object = Global.getContentObject(authV1SJson);
			if(object != null) {
				
				String v1AccessToken = object.getString("access_token");
				
				if(v1AccessToken != null && !v1AccessToken.trim().equals("")) {
					
					 loginInfoMap.put(Global.AUTH_INFO_ACCESS_TOKEN_V1, v1AccessToken);
				}
			}
			
		}
		
		
		
		HttpSession session = request.getSession();
		
		String sessionId = session.getId();
		
		logger.info("sessionId:" + sessionId);
		
		Global.setAccountUuid(session, accountUuid);
		
		Global.setLoginInfo(accountUuid, loginInfoMap);
		
		
		Map<String, Object> powerInfo = null;
		
		// 登录成功完成鉴权
		JSONObject jsonObject = JSONObject.parseObject(loginJson);
				
		
		boolean isSuperUser= loginServiceHttp.checkZZXTSupperUser(accessToken, userName);
		
		if(isSuperUser == true) { 
			
			// 获取超级用户信息
			powerInfo = userPowerService.superUserPowerInfo(accessToken, "0");
		
		}else {
			
			if(jsonObject != null) {
				
				JSONObject content = jsonObject.getJSONObject("content");
				
				accountUuid	= content.getString("accountUuid");
				if (accountUuid != null) {
					
					// 获取一般用户信息
					powerInfo = userPowerService.userPowerInfo(accessToken, accountUuid);
				}
			}
		
		}
		
		loginJson = Global.getCustomerProtocolInfo(loginJson);
		
		String jsonString = Global.addCutmostContent(loginJson, "powerinfo", powerInfo);		
		
		logger.info("5.Login->INFO :" + loginJson);
		
		// jsonToPage(jsonString, response);
		
		if(Global.isICESuccessed(jsonString)) {
			
			Global.myLogger.add("Login", "true");
		
		} else {
			
			String message = Global.getICEMessage(jsonString);
			
			Global.myLogger.add("Login", "false", message);
		}
					
		jsonToCallback(jsonpCallback, jsonString, response);
    }



	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest request, HttpServletResponse response) {
		
	    Global.myLogger.add("loginOut", "true");
		
		HttpSession httpSession = request.getSession();
		if(httpSession != null) {
			httpSession.invalidate();
		}
		
		return redirectPage("login");
	}
}
