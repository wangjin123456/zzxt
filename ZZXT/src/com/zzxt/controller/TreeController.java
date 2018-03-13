package com.zzxt.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zzxt.bean.OrgBean;
import com.zzxt.redis.RedisCacheUtil;
import com.zzxt.service.LicenseService;
import com.zzxt.service.LogService;
import com.zzxt.servicehttp.AdministrativeDivisionServiceHttp;
import com.zzxt.servicehttp.OrgTypeServiceHttp;
import com.zzxt.servicehttp.OrganizationServiceHttp;
import com.zzxt.util.Global;
import com.zzxt.util.JsonCast;





class UpdaetOrgBean {
	
	private String orgUuid;
	private String name;
	private String parentId;
	
	private int status;
	
	
	public UpdaetOrgBean() {
		super();
		// TODO Auto-generated constructor stub
		
		this.status = Global.ICE_ORG_INIT;
	}


	public UpdaetOrgBean(String orgUuid, String name, String parentId, int status) {
		super();
		
		this.status = Global.ICE_ORG_INIT;
		
		this.orgUuid = orgUuid;
		this.name = name;
		this.parentId = parentId;
		this.status = status;
	}


	public String getOrgUuid() {
		return orgUuid;
	}


	public void setOrgUuid(String orgUuid) {
		this.orgUuid = orgUuid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getParentId() {
		return parentId;
	}


	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}	
}




/**
 * 组织架构控制器
 * @ClassName: TreeController
 * @Description: 组织架构控制器
 * @author think
 */
//@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TreeController extends BaseController {
	private static Logger logger = Logger.getLogger(TreeController.class.getName());

	@Autowired
	OrganizationServiceHttp organizationServiceHttp;
	
	@Autowired
	AdministrativeDivisionServiceHttp administrativeDivisionServiceHttp;
	
	@Autowired
	OrgTypeServiceHttp orgTypeServiceHttp;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;
	
	@Autowired
	LicenseService licenseService;
	
	@Autowired
	LogService logService;
	

	

	/*
	 * 查询法人架构信息
	 */
	@RequestMapping("/getTree")
	public void getTree(HttpServletRequest request, String orgUuid,
			HttpServletResponse response) {
		
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
		
		
		if (orgUuid == null || "".equals(orgUuid)) {
			orgUuid = String.valueOf(0);
		}
		
		String jsonResult = organizationServiceHttp.getOrgTresJson(accessToken, orgUuid, "2");
		logger.info("---------证照树形列表--------" + jsonResult);
		
		if(jsonResult == null) {
			
			String message = "没有获取法人架构列表";
			
			// Global.myLogger.add("getTree", "false", message);
			
			Map<String, String> resultMap = new HashMap<String, String> ();
			resultMap.put(Global.PROTOCOL_CODE_KEY, String.valueOf(Global.ICE_UNKNOW));
			resultMap.put(Global.PROTOCOL_TYPE_KEY, Global.PROTOCOL_TYPE_ICE_VALUE);
			
			resultMap.put(Global.PROTOCOL_MESSAGE_KEY, message);
			resultMap.put(Global.PROTOCOL_CONTENT_KEY, "");

			jsonResult = JsonCast.collectToString(resultMap);
		}
//		else  {
//			Global.myLogger.add("getTree", "true");
//		}
		
		jsonToCallback(jsonpCallback, jsonResult, response);
	}
	
	
	
	/*
	 * 查询法人架构信息
	 */
	@RequestMapping("/getAllTree")
	public void getAllTree(HttpServletRequest request, String orgUuid,
			HttpServletResponse response) {
		
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
		
		if (orgUuid == null || "".equals(orgUuid)) {
			orgUuid = String.valueOf(0);
		}
		
		String jsonResult = organizationServiceHttp.getAllOrgTresJson(accessToken, orgUuid, "2");
		 
		if(jsonResult == null) {
			
			String message = "没有获取法人架构列表";
			
			// Global.myLogger.add("getTree", "false", message);
			
			Map<String, String> resultMap = new HashMap<String, String> ();
			resultMap.put(Global.PROTOCOL_CODE_KEY, String.valueOf(Global.ICE_UNKNOW));
			resultMap.put(Global.PROTOCOL_TYPE_KEY, Global.PROTOCOL_TYPE_ICE_VALUE);
			
			resultMap.put(Global.PROTOCOL_MESSAGE_KEY, message);
			resultMap.put(Global.PROTOCOL_CONTENT_KEY, "");

			jsonResult = JsonCast.collectToString(resultMap);
			
		} 
		
		logger.info("getAllTree->获取所有人法人架构列表:" +  jsonResult);
		
		jsonToCallback(jsonpCallback, jsonResult, response);
	}
	
	
	/*
	 * 查询组织架构信息-
	 */
	
	@RequestMapping("/getCorporationTree")
	public void getCorporationTree(HttpServletRequest request, String orgUuid, HttpServletResponse response) {
		
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
 		
		if (orgUuid == null || "".equals(orgUuid)) {
		
			orgUuid = String.valueOf(0);
		}
		
		//Global.myLogger.add("getCorporationTree", "true");
		
		String content = organizationServiceHttp.getOrgJson(accessToken, orgUuid, "0");
		
		logger.info("---------组织架构树形列表--------" + content);
		
		String jsonString = Global.getProtocol(content);
		
		// jsonToPage(jsonString, response);
		
//		if(Global.isICESuccessed(jsonString)) {
//			
//			Global.myLogger.add("getCorporationTree", "true");
//		
//		} else {
//			
//			String message = Global.getICEMessage(jsonString);
//			
//			Global.myLogger.add("getCorporationTree", "false", message);
//		}
		
		jsonToCallback(jsonpCallback, jsonString, response);
	}
	
	/**
	 * 创建架构信息——填充数据
	 */
	@RequestMapping("/addTreeView")
	public void addTreeView(HttpServletRequest request, 
		
			String pid,//区域等级最高为0
			String type,//类型(组织架构类型org_type,岗位类型job_type)
			String familyTypeId,//这里默认为2(法人架构)
			String orgUuId,//架构ID(主键)
			HttpServletResponse response){
		
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
		
		
		logger.info("---------开始获取类型值--------");
		
		Map<String,String>typeMap = new HashMap<String,String>();
		
		if(type!=null&&!"".equals(type)){
				typeMap.put("type", type);
		}
		
		typeMap.put("familyTypeId", familyTypeId);//默认法人架构
		
		typeMap.put("token", accessToken);
		
		String json = orgTypeServiceHttp.selectTypeSearch(typeMap);
		
		String jsonString = Global.getIceProtocolInfo(json);
		
		
		if(Global.isICESuccessed(jsonString)) {
			
			Global.myLogger.add("addTreeView", "true");
		} else {
			
			Global.myLogger.add("addTreeView", "false");
		}
		
		
		// jsonToPage(jsonString, response);
		
		jsonToCallback(jsonpCallback, jsonString, response);
		
		
//		Map<String,Object>typeResultValueMap=JsonCast.stringToCollect(typeResultValue);
//		
//		logger.info("---------返回类型值：" + typeResultValueMap);
//		
//		logger.info("---------封装返回页面数据---------");
//		
//		Map<String,Object>dataMap=new HashMap<String,Object>();
//		
//		dataMap.put("typeValues", typeResultValueMap);
//		
//		logger.info("---------返回页面的数据：" + dataMap);
//		
//		jsonToPage(JsonCast.collectToString(dataMap), response);
	}
	
	/**
	 * 添加组织结构信息
	 */
	@RequestMapping("/addTree")
	public void addTree(HttpServletRequest request, HttpServletResponse response, @ModelAttribute OrgBean orgBean) {
		
		logger.info("---------开始存入数据：" + orgBean.toString());

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
		
		
		Map<String,String>pamMap=new HashMap<String,String>();
		
		if(orgBean!=null){
			
			pamMap = JsonCast.stringToCollect(JsonCast.beanToJson(orgBean) + "");
			
			pamMap.put("token", accessToken);
			
			pamMap.put("corpId", corpId);
			
			pamMap.remove("orgBeanList");
		}
		
		logger.info("---------参数数据：" + orgBean.toString());
		logger.info("---------参数数据pamMap：" +pamMap.toString()+","+ pamMap.keySet()+","+pamMap.values());
		
		String json = organizationServiceHttp.insertOrg(pamMap);
		
		String jsonString = Global.getIceProtocolInfo(json);
		
		// jsonToPage(jsonString, response);
		
		if(Global.isICESuccessed(jsonString)) {
			
			String message = "增加法人架构：父结点:" + orgBean.getParentId() + "新增法人架构名称:" + orgBean.getName();
			
			Global.myLogger.add("addTree", "true", message);
		
		} else {
			
			String message = Global.getICEMessage(jsonString);
			
			Global.myLogger.add("addTree", "false", message);
		}
		
		
		jsonToCallback(jsonpCallback, jsonString, response);
	}

	
//	/**
//	 * 删除组织结构
//	 */
//	@RequestMapping("/delTree")
//	public void delTree(HttpServletRequest request, 
//			HttpServletResponse response,
//			String orgUuid,//主键uuid
//			String familyTypeId//这里默认为2(法人架构)
//			){
//		//删除对应组织架构的执照和股东信息
//		//int isSucc=licenseService.deleteLic_Shar(orgUuid);
//
//		int isSucc = 0;
//		Map<String,String>map= super.getCorpId_token(request, response, super.getSessionKey());
//		if(isSucc>0){
//			if(map!=null&&map.size()>0){
//				map.put("orgUuid", orgUuid);
//				map.put("familyTypeId",familyTypeId);
//			}
//			String jsonResult=organizationServiceHttp.deleteOrg(map);
//			Map<String,String>messageM=JsonCast.stringToCollect(jsonResult);
//			jsonToPage(jsonResult, response);
//		}else{
//			Map<String,Object> message=new HashMap<String,Object>();
//			message.put("code",1);
//			message.put("message","删除失败！");
//			jsonToPage(JsonCast.collectToString(message), response);
//		}
//	}
//	
	
	
//	
//	/**
//	 * 修改架构信息
//	 */
//	@RequestMapping("/updateTreeView")
//	public void updateTreeView(HttpServletRequest request,  HttpServletResponse response, @ModelAttribute OrgBean orgBean){
//		
//		Map<String,String>map= super.getCorpId_token(request, response, super.getSessionKey());
//		
//		logger.info("---------开始获取类型值--------");
//		
//		Map<String,String>typeMap=new HashMap<String,String>();
//		
//		if(orgBean.getOrgType()!=null&&!"".equals(orgBean.getOrgType())){
//		
//			typeMap.put("type", orgBean.getOrgType());
//			
//		}
//		
//		typeMap.put("familyTypeId", orgBean.getFamilyTypeId());//默认法人架构
//		
//		typeMap.put("token", map.get("token"));
//		
//		String typeResultValue= orgTypeServiceHttp.selectTypeSearch(typeMap);
//		
//		Map<String,Object>typeResultValueMap=JsonCast.stringToCollect(typeResultValue);
//		
//		logger.info("---------返回类型值：" + typeResultValueMap);
//		logger.info("---------开始获取原有数据--------");
//		
//		String oldInfo=null;
//		
//		if(orgBean.getOrgUuid()!=null&&!"".equals(orgBean.getOrgUuid())){
//		
//			map.put("orgUuId", orgBean.getOrgUuid());
//			
//			oldInfo=organizationServiceHttp.getOrg(map);
//			
//		}
//		logger.info("---------返回原有数据："+oldInfo);
//		logger.info("---------封装返回页面数据---------");
//	
//		Map<String,Object>dataMap=new HashMap<String,Object>();
//		
//		dataMap.put("typeValues", typeResultValueMap);
//		
//		dataMap.put("oldInfo", oldInfo);
//		
//		logger.info("---------返回页面的数据：" + dataMap);
//		
//		jsonToPage(JsonCast.collectToString(dataMap), response);
//	}
//	
	
	
	/**
	 * 修改架构信息 注释 2017/12/25 更新
	 */
	@RequestMapping("/updateTreeView")
	public void updateTreeView(HttpServletRequest request,  HttpServletResponse response, @ModelAttribute UpdaetOrgBean orgBean){
		

		String jsonpCallback = request.getParameter("callback");//客户端请求参数 
		
		Map<String,String> paramMap = new HashMap<String,String>();

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
//		
//		if (accessToken == null || accessToken.trim().equals("")) {
//			
//			String message = "修改法人架构错误:accessToken不能为空!";
//			
//			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message);
//			
//			// jsonToPage(protocol, response);
//			
//			Global.myLogger.add("updateTreeView", "false", message);
//			
//			jsonToCallback(jsonpCallback, protocol, response);
//		}
		
		String orgUuid = orgBean.getOrgUuid();
		
		if (orgUuid == null || orgUuid.trim().equals("")) {
			
			String message = "修改法人架构错误:orgUuid不能为空!";
			
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message);
			// jsonToPage(protocol, response);
			
			Global.myLogger.add("updateTreeView", "false", message);
			
			jsonToCallback(jsonpCallback, protocol, response);
		}
		
		paramMap.put("orgUuid", orgUuid);
		
		String message = "";
		
		// 如果有名称则修改名称
		String name = orgBean.getName();
		if(name != null && !name.trim().equals("")) {
			
			paramMap.put("name", name);
			
			message = "修改法人架构名称->orgUuid:" + orgUuid + "修改名称为:" + name;
			
		}

		// 如果有父结点，则移动到新的父结点上，当前父结点还存在？
		String parentId = orgBean.getParentId();
		if(parentId != null && !parentId.trim().equals("")) {
			
			paramMap.put("parentId", parentId);
			
			message += "\r\n";
			message += "修改法人架构名称->orgUuid:" + orgUuid + "修改名称为:" + name;
		}
			
		paramMap.put("familyTypeId", Global.STRUCTURE_TYPE_LEGALPERSON); //默认法人架构
		
		paramMap.put("token", accessToken);
		
		Integer status = orgBean.getStatus();
		if(status != Global.ICE_ORG_INIT) {
			
			paramMap.put("status", String.valueOf(status));
			
			String s = status == 0? "启用" : "禁用";
			
			message += "\r\n";
			message +=  "禁用启用法人架构->orgUuid:" + orgUuid + "状态:" + s;
		}
		
		
		String iceJson = organizationServiceHttp.updateOrg(paramMap);
		
		String protocol = Global.getIceProtocolInfo(iceJson);
		
		if(Global.isICESuccessed(protocol)) {
			
			Global.myLogger.add("updateTreeView", "true", message);
			
		} else {
			
			Global.myLogger.add("updateTreeView", "false", message);
		}
		
		// jsonToPage(protocol, response);
		
		jsonToCallback(jsonpCallback, protocol, response);
	}	
}
