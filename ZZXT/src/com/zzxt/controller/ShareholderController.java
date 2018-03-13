/**   
* @Title: LicenseController.java 
* @Package com.zzxt.controller 
* @Description: 执照
* @author  wangdekun   
* @date 2017年9月5日 下午2:37:25 
* @version V1.0   
*/
package com.zzxt.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.zzxt.entity.ShareholderEntity;
import com.zzxt.service.LicenseService;
import com.zzxt.service.LogService;
// import com.zzxt.service.ShareholderService;
import com.zzxt.servicehttp.AdministrativeDivisionServiceHttp;
import com.zzxt.util.Global;


/**
 * 股东信息控制器
 * @ClassName: ShareholderController
 * @Description: 股东信息控制器
 * @author think
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ShareholderController extends BaseController {

	private static Logger logger = Logger.getLogger(ShareholderController.class.getName());
	
//	@Autowired
//	ShareholderService shareholderService;
	
	@Autowired
	LogService logService;
	
	@Autowired
	LicenseService licenseService;
	
	@Autowired
	AdministrativeDivisionServiceHttp administrativeDivisionServiceHttp;
	
	
	/**
	 * 导入Excel股东信息
	 */
	@RequestMapping("importSharInfo")
	public void importSharInfo(HttpServletRequest re,HttpServletResponse res){
	}
	
	
	
	
	/**
	 * 股东信息列表
	 */
	@RequestMapping("sharehoderList")
	public void getSharehoderList(HttpServletRequest request, HttpServletResponse response, String lid){
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数 
		
		List<ShareholderEntity> shareolderList = licenseService.shareHolderList(lid);
 
		String content = JSON.toJSONString(shareolderList);
		
		String jsonString = Global.getProtocol(content);
		
		// jsonToPage(jsonString, response);
		
//		if(Global.isICESuccessed(jsonString)) {
//			
//			Global.myLogger.add("sharehoderList", "true");
//		
//		} else {
//			
//			String message = Global.getICEMessage(jsonString);
//			
//			Global.myLogger.add("sharehoderList", "false", message);
//		}
		
		
		jsonToCallback(jsonpCallback, jsonString, response);
	}
	

	
	/**
	 * 添加股东信息
	 */
	@RequestMapping("addShareInfo")
	public void addSharInfo(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ShareholderEntity shareholderEntity){
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数 
		
	    String lid = request.getParameter("licenseId");
		int ok = licenseService.updateShareHolderInfo(shareholderEntity);
		if(ok > 0){
		
			Integer sid = shareholderEntity.getId();
			
			if(lid != null || lid.trim().equals("") == false) {
				
				Integer t = Integer.valueOf(lid);

				ok = licenseService.saveLicenseShareHolder(t, sid);
				if(ok < 0) {
					
					String message = "关联法人、股东信息失败";
					
					String jsonString = Global.getProtocol(Global.ICE_UNKNOW, message);
					
					// jsonToPage(jsonString, response);
					
					Global.myLogger.add("addShareInfo", "false", message);
					
					jsonToCallback(jsonpCallback, jsonString, response);
					
				} else {
					
					 String message = "关联法人、股东信息成功";

					String jsonString = Global.getProtocol(Global.ICE_OK, message, false);
					 
					// jsonToPage(jsonString, response);
					
					Global.myLogger.add("addShareInfo", "false", message);
					
					jsonToCallback(jsonpCallback, jsonString, response);
				}
			}
		}
		
		String message = "添加股东信息失败";
		
	    String jsonString = Global.getProtocol(Global.ICE_UNKNOW, message);
		
	    // jsonToPage(jsonString, response);
	    
		if(Global.isICESuccessed(jsonString)) {
			
			Global.myLogger.add("addShareInfo", "true");
		
		} else {
			
			 message = Global.getICEMessage(jsonString);
			
			Global.myLogger.add("addShareInfo", "false", message);
		}
		
	    
	    jsonToCallback(jsonpCallback, jsonString, response);
		
	}
	
	
	
	/**
	 * 删除
	 */
	@RequestMapping("delSharInfo")
	public void delSharInfo(HttpServletRequest request, HttpServletResponse response,int id){

		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数 
		
		String message = "";
		int ok = licenseService.delSharInfo(id);
		if(ok>0){
			
			message = "删除股东信息成功!";
			
		    String jsonString = Global.getProtocol(Global.ICE_OK, message, false);
			
		    // jsonToPage(jsonString, response);
		    
		    jsonToCallback(jsonpCallback, jsonString, response);
		}
		
		message = "删除股东信息失败!";
		
	  String jsonString = Global.getProtocol(Global.ICE_UNKNOW, message);
		
	  // jsonToPage(jsonString, response);
	  
		if(Global.isICESuccessed(jsonString)) {
			
			Global.myLogger.add("delSharInfo", "true");
		
		} else {
			
			 message = Global.getICEMessage(jsonString);
			
			Global.myLogger.add("delSharInfo", "false", message);
		}
	  
	  jsonToCallback(jsonpCallback, jsonString, response);
	}
	
	
	
	/**
	 * 查询
	 */
	@RequestMapping("selectSharInfo")
	public void selectSharInfo(HttpServletRequest request, HttpServletResponse response,Integer id){
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数 
		
		ShareholderEntity shareholderEntity = licenseService.shareholderInfo(id);
		
		String content = JSON.toJSONString(shareholderEntity);
		
		String jsonString = Global.getProtocol(content);
		
		// jsonToPage(jsonString, response);
		
	  
//		if(Global.isICESuccessed(jsonString)) {
//			
//			Global.myLogger.add("selectSharInfo", "true");
//		
//		} else {
//			
//			String message = Global.getICEMessage(jsonString);
//			
//			Global.myLogger.add("selectSharInfo", "false", message);
//		}
			
		
		jsonToCallback(jsonpCallback, jsonString, response);
	}
	
	
	/**
	 * 修改
	 */
	@RequestMapping("updateSharInfo")
	public void updateSharInfo(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ShareholderEntity shareholderEntity){
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数 
		
		Integer sid = shareholderEntity.getId();
		
		if(sid != null && sid > 0) {
			
			int ok = licenseService.updateShareHolderInfo(shareholderEntity);
			if(ok > 0) {
				
				String message = "修改股东信息成功!";
				
				String jsonString = Global.getProtocol(Global.ICE_OK, message, false);
				
				// jsonToPage(jsonString, response);
				
				Global.myLogger.add("updateSharInfo", "true", message);
				
				jsonToCallback(jsonpCallback, jsonString, response);
			} else {
				
				String message = "修改股东信息失败!";
				
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW, message);
				
				// jsonToPage(jsonString, response);
				
				Global.myLogger.add("updateSharInfo", "false", message);
				
				jsonToCallback(jsonpCallback, jsonString, response);
			}
		}
	}
	
	
	/**
	 * 区域查询
	 */
	@RequestMapping("selectRegion")
	public void selectRegion(HttpServletRequest request, HttpServletResponse response,String pid){

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
		
		
		if(pid==null){
			pid=String.valueOf("0");
		}
		
		String jsonString = administrativeDivisionServiceHttp.getAdministrativeDivision(accessToken, pid);
		
		jsonString = Global.getIceProtocolInfo(jsonString);
		
//		  
//		if(Global.isICESuccessed(jsonString)) {
//			
//			Global.myLogger.add("selectRegion", "true");
//		
//		} else {
//			
//			String message = Global.getICEMessage(jsonString);
//			
//			Global.myLogger.add("selectRegion", "false", message);
//		}
		
		//jsonToPage(json,res);
		
		jsonToCallback(jsonpCallback, jsonString, response);
	}
}
