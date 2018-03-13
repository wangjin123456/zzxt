package com.zzxt.controller;
/**
 * 
 * @ClassName: MainController
 * @Description: 组织机构类型
 * @author think
 */
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zzxt.bean.TypeBean;
import com.zzxt.service.LogService;
import com.zzxt.servicehttp.OrgTypeServiceHttp;
import com.zzxt.util.Global;

//@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class OrgTypeController extends BaseController{
	private static Logger logger = Logger.getLogger(OrgTypeController.class.getName());
	
	
	@Autowired
	OrgTypeServiceHttp orgTypeServiceHttp;
	
	@Autowired
	LogService logService;
	
	
	
	/**
	 * 获取组织机构类型
	 */
	@RequestMapping("/orgType")
	public void getOrgType(HttpServletRequest request, HttpServletResponse response,
			String type,//类型(组织架构类型org_type(默认),岗位类型job_type)
			String familyTypeId //这里默认为2
			){
		
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
		
		Map<String,String>typeMap=new HashMap<String,String>();
		if(type!=null&&!"".equals(type)){
			typeMap.put("type", type);
		}
		
		typeMap.put("familyTypeId", familyTypeId);//默认 法人架构
		typeMap.put("token", accessToken);
		
		String jsonResult=orgTypeServiceHttp.selectTypeSearch(typeMap);
		logger.info("-----------orgTypeJson------" + jsonResult);
		
		JSONObject jsonObject = JSONObject.parseObject(jsonResult);
		
		Integer totalPage = jsonObject.getInteger("totalPage");
		Integer totalRecord = jsonObject.getInteger("totalRecord");
		
		//如果页面大于一，全部查询出来
		if (totalPage > 1) {
			typeMap.put("pageSize", String.valueOf(totalRecord));
			jsonResult=orgTypeServiceHttp.selectTypeSearch(typeMap);
		
		}

		// jsonToPage(jsonResult, response);
		
		jsonToCallback(jsonpCallback, jsonResult, response);
	}
	
	
	/**
	 * 添加组织机构类型
	 */
	@RequestMapping("/orgTypeSave")
	public void saveOrgType(TypeBean typeBean,HttpServletRequest request,HttpServletResponse response){
		
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
		
		String corpId = Global.getUserCorpId(accountUuid);
		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", accessToken);
		map.put("corpId", corpId);
		
		String jsonResult = orgTypeServiceHttp.saveOrgType(typeBean,map);
		
		if(Global.isICESuccessed(jsonResult)) {
			
			Global.myLogger.add("orgTypeSave", "true");
		
		} else {
			
			String message = Global.getICEMessage(jsonResult);
			
			Global.myLogger.add("orgTypeSave", "false", message);
		}
		
		jsonToCallback(jsonpCallback, jsonResult, response);
		
	}
	
	/**
	 * 删除架构类型
	 * @param typeUuid
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delOrgType")
	@ResponseBody
	public void deleteOrgType(String typeUuid, HttpServletRequest request, HttpServletResponse response){
		
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
		
		String corpId = Global.getUserCorpId(accountUuid);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", accessToken);
		map.put("corpId", corpId);
		
		String jsonResult = orgTypeServiceHttp.delOrgType(typeUuid,map);

		
		if(Global.isICESuccessed(jsonResult)) {
			
			Global.myLogger.add("delOrgType", "true");
		
		} else {
			
			String message = Global.getICEMessage(jsonResult);
			
			Global.myLogger.add("delOrgType", "false", message);
		}
		
		
		jsonToCallback(jsonpCallback, jsonResult, response);
		
	}

	
	
	@RequestMapping("/updateOrgType")
	public void updateOrgType(TypeBean typeBean,HttpServletRequest request,HttpServletResponse response){

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
		
		String corpId = Global.getUserCorpId(accountUuid);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", accessToken);
		map.put("corpId", corpId);
		
		String jsonResult = orgTypeServiceHttp.updateOrgType(typeBean,map);

		
		if(Global.isICESuccessed(jsonResult)) {
			
			Global.myLogger.add("updateOrgType", "true");
		
		} else {
			
			String message = Global.getICEMessage(jsonResult);
			
			Global.myLogger.add("updateOrgType", "false", message);
		}
		
		
		jsonToCallback(jsonpCallback, jsonResult, response);

	}


//	
//	/*@RequestMapping("/orgTypeSave")
//	public void saveOrgType(HttpServletRequest request,HttpServletResponse response,
//			int typeNum,String familyTypeId,String org_type//类型(组织架构类型org_type（在此默认）,岗位类型job_type)
//			){
//		Map<String,String>map=super.getCorpId_token(request, response, super.getSessionKey());
//		String username = map.get("username");
//		//获取缓存数据 -all类型
//		//String orgTypes=redisCacheUtil.get(Md5Util.md5(super.getSessionID(request, response,super.getSessionKey()) + "orgTypes"));
//		String orgTypes=redisCacheUtil.get(Md5Util.md5(Global.ORGTYPES.replace("sessionId", super.getSessionID(request, response,super.getSessionKey()))));
//		//获取用户编辑的-all类型
//		List<TypeContent>newOrgTypes=new ArrayList<TypeContent>();
//		String typeUuids=null;
//		if(typeNum>0){
//			for(int i=1;i<=typeNum;i++){
//				typeUuids +=request.getParameter("TtypeUuid_"+i)+",";
//				TypeContent typeContent=new TypeContent(request.getParameter("TtypeUuid_"+i),map.get("corpId"), "org_type",request.getParameter("typeName_"+i),"",new Date(),
//						new Date(),familyTypeId);
//				newOrgTypes.add(typeContent);
//			}
//		}
//		List<TypeContent>typeContent=null;
//		if(orgTypes!=null&&!"".equals(orgTypes)){
//			Map<String,Object>orgTypeMap=JsonCast.stringToCollect(orgTypes);
//			if(orgTypeMap!=null&&orgTypeMap.size()>0){//原有数据
//				typeContent=JsonCast.jsonToTypeContentList(orgTypeMap.get("content")+"", TypeContent.class) ;
//				logger.info("-----------content------" + orgTypeMap.get("content"));
//			}
//		}
//		orgTypeServiceHttp.saveTypeList(typeContent, typeNum, newOrgTypes,map.get("token"),typeUuids);
//		Map<String,String>reMap=new HashMap<String,String>();
//		// 保存日志信息
//		if(reMap!=null){
//		Log log = new Log(); 
//        log.setHandle("orgTypeSave");    
//        log.setUserName(username);    
//        log.setState("true");  
//        log.setComment("");  
//        log.setDate(new Date());
//        logService.addLog(log);
//		logger.info(log);
//		}
//		reMap.put("message", "设置成功！");
//		reMap.put("code", "200");
//		
//		Log log = new Log(); 
//        log.setHandle("orgTypeSave");    
//        log.setUserName(username);    
//        log.setState("false");  
//        log.setComment("");  
//        log.setDate(new Date());
//        logService.addLog(log);
//		logger.info(log);
//		jsonToPage(JsonCast.collectToString(reMap), response);
//	 }*/
}
