/**   
* @Title: LicenseController.java 
* @Package com.zzxt.controller 
* @Description: 执照
* @author  wangdekun   
* @date 2017年9月5日 下午2:37:25 
* @version V1.0   
*/
package com.zzxt.controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzxt.bean.ManageOrgVo;
import com.zzxt.entity.LegalPersonEntity;
import com.zzxt.entity.LicenseEntity;
//import com.zzxt.service.LegalPersonService;
import com.zzxt.service.LicenseService;
import com.zzxt.service.LogService;
import com.zzxt.servicehttp.AuthServiceHttp;
import com.zzxt.servicehttp.FileUploadServiceHttp;
import com.zzxt.servicehttp.OrganizationServiceHttp;
import com.zzxt.util.Global;
import com.zzxt.util.Tip;
import com.zzxt.util.UploadFileUtil;

/**
 * @ClassName: LicenseController
 * @Description: 执照控制器
 * @author wangdekun
 * @date 2017年9月5日 下午2:37:25
 *  
 */
//@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LicenseController extends BaseController {

	private static Logger logger = Logger.getLogger(LicenseController.class.getName());

	@Autowired
	LicenseService licenseService;
	
	@Autowired
	AuthServiceHttp authServiceHttp;
	
	@Autowired
	OrganizationServiceHttp organizationServiceHttp;
	
//	@Autowired
//	LegalPersonService legalPersonService;
	
	@Autowired
	LogService logService;
	
	@Autowired
	FileUploadServiceHttp  fileUploadServiceHttp = null;
	
	

	
	/**
	 * 根据orgUuid获取相应的执照信息
	 * @param response
	 * @param orgUuid
	 */
	@RequestMapping("/getLicenseInfo")
	public void getLicenseInfo(HttpServletResponse response, HttpServletRequest request, String orgUuid) {
		
		if(orgUuid == null || orgUuid.trim().equals("") == true) {
					
				String message = "请求失败:请求参数OrgUuid 为空!";
				logger.info(message);
				
				// 做为成功不弹窗
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW, message);
	
				// Global.myLogger.add("getLic", "false", message);
				
				jsonToPage(jsonString, response);
			}
		
		logger.info("getLicenseInfo orgUuid:" + orgUuid);
		
		LicenseEntity licenseEntity = licenseService.getLicenseInfo(orgUuid);
		
		String jsonResult = "";
		
		if(licenseEntity != null) {
			
			jsonResult = JSONObject.toJSONString(licenseEntity);
		}
		
		logger.info("getLicenseInfo:" + jsonResult);
		
		jsonToPage(jsonResult, response);
	}
	
	
	
	/**
	 * 根据orgUuid获取相应的执照
	 * @param response
	 * @param orgUuid
	 */
	@RequestMapping("/getLic")
	public void getLic(HttpServletResponse response, HttpServletRequest request, String orgUuid) {
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数
		
		if(orgUuid == null || orgUuid.trim().equals("") == true) {
			
			String message = "请求失败:请求参数OrgUuid 为空!";
			logger.info(message);
			
			// 做为成功不弹窗
			String jsonString = Global.getProtocol(Global.ICE_UNKNOW, message);

			// Global.myLogger.add("getLic", "false", message);
			
			// jsonToPage(jsonString, response);
			
			jsonToCallback(jsonpCallback, jsonString, response);
		}
		
		logger.info("get Lic orgUuid:" + orgUuid);
		
		
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
		

		Map<String,Object> resultMap = licenseService.getLicenseMap(accessToken, orgUuid);
	
		if(resultMap != null) {
			
			String mangeOrgUuid = (String) resultMap.get("manageOrgUuid");
			if(mangeOrgUuid != null && mangeOrgUuid.trim().equals("") == false) {

				logger.info("get Lic mangeOrgUuid:" + mangeOrgUuid);
				
				List<ManageOrgVo> manageOrgList = organizationServiceHttp.getManageOrgList(accessToken, mangeOrgUuid);
				
				if (manageOrgList != null) {
					
					resultMap.put("manageOrg", manageOrgList);
					
				} else {
				
					resultMap.put("manageOrg", "");
				}
				
				logger.info("---------管理架构详情--------" + manageOrgList);
				
				logger.info("resultMap:"+resultMap);
			
			}  
		}
		
		
		String content = JSON.toJSONString(resultMap);
	   
		String jsonString = Global.getProtocol(content);
		
		// jsonToPage(jsonString, response);
		
//		if(Global.isICESuccessed(jsonString)) {
//			
//			Global.myLogger.add("getLic", "true");
//		} else {
//			
//			String message = Global.getICEMessage(jsonString);
//			
//			Global.myLogger.add("getLic", "false", message);
//		}
		
		jsonToCallback(jsonpCallback, jsonString, response);
	}
	
	
	
//	/**
//	 * 删除执照信息
//	 * @param id
//	 * @param response
//	 */
//	@RequestMapping("/delLic")
//	public void getLic(Integer id, HttpServletResponse response) {
//		Integer x = licenseService.deleteLic(id);
//		if (x == 1) {
//			logger.info("成功删除：" + x + "条数据");
//			jsonToPage(JSON.toJSONString(new Tip("成功删除：" + x + "条数据",Global.SUCCESS)), response);
//		} else {
//			logger.info("删除数据失败");
//			jsonToPage(JSON.toJSONString(new Tip("删除数据失败",Global.FAILD)), response);
//		}
//		jsonToPage(JSON.toJSONString(id), response);
//	}
//	
	
	private boolean isUpload(String[] picturebackfill, String fileName) {
		
		boolean isOk = true;
		
		for(String file : picturebackfill) {
			
			if(file.equals(fileName)) {
				
				isOk = false;
				
				break;
			}
		}
		
		return isOk;
	}
	
	
	/**
	 * 保存执照信息
	 * @param response
	 * @param re
	 * @param file
	 */
	@RequestMapping(value="/saveLic",method = RequestMethod.POST)
	public void saveLic(HttpServletResponse response,HttpServletRequest request, String[] picturebackfill, @RequestParam(value = "file", required = false) MultipartFile[] files){
	
		for(String fill : picturebackfill) {
			
			logger.info("<<<<<<<<<<<<<<<<<<<<<picturebackfill 内容:" + fill + ">>>>>>>>>>>>>>>>>>>>");
		}
		
		for(MultipartFile file : files) {
			
			logger.info("<<<<<<<<<<<<<<<<<<<<<CommonsMultipartFile 内容:" + file.getOriginalFilename() + ">>>>>>>>>>>>>>>>>>>>");

		}
		
		// String jsonpCallback = request.getParameter("callback");//客户端请求参数 
		
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		logger.info("sessionId:" + sessionId);
		
		String accountUuid = Global.getUserInfoKey(request.getSession());
		String accessToken = Global.getAccessToken(accountUuid);
		String accessTokeV1= Global.getAccessTokenV1(accountUuid);
		
		if(accessToken == null || accessToken.trim().equals("") || accessTokeV1 == null || accessTokeV1.trim().equals("")) {
			
			String message = Global.RELOGIN_HINT;
			
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message);

			jsonToPage(protocol, response);
		}

		LicenseEntity licenseEntity=null;
		
		LegalPersonEntity legalPersonEntity = null;
		
		try {
			licenseEntity = new LicenseEntity().getPamValue(request);
		
			legalPersonEntity = new LegalPersonEntity().getPamValue(request);
		
		} catch (ParseException e) {
			
			
			String message = "获取请求参数失败！";
			logger.info(message);
			
			String jsonString = Global.getProtocol(Global.ICE_UNKNOW, message);
			
			e.printStackTrace();

			Global.myLogger.add("saveLic", "false", message);

			
			jsonToPage(jsonString, response);
			
			//jsonToCallback(jsonpCallback, jsonString, response);
		}
		
		logger.info("获取请求参数LicenseEntity:" + licenseEntity.toString());
		
//		Tip tip=null;
//		
//		String licPath ="";
//		for (MultipartFile file : files) {
//			if(file != null && !file.isEmpty()){
//				
//				tip = UploadFileUtil.uploadM(file, "license", request, response);
//				
//				logger.info("tip:"+tip.toString());
//				
//				if(tip.getStateCode()==Global.SUCCESS){
//					
//					logger.info("LicPath:"+tip.getPam()+"");
//					
//					licPath += tip.getPam();
//				
//					licPath += ",";
//				}
//			}
//		}
		
		String licPath ="";
		for (MultipartFile file : files) {
			if(file != null && !file.isEmpty()){
				
				String user = Global.getUserName(accountUuid);
						
				String jsonResult = fileUploadServiceHttp.uploadFile(accessTokeV1, (CommonsMultipartFile)file, user, "证照系统");

				logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>通过ice文件微服务上传文件返回信息:" + jsonResult);
				if(jsonResult != null && !jsonResult.trim().equals("") && Global.isICESuccessed(jsonResult)) {
					
					String	fileName = file.getOriginalFilename();
					
				    String fileId = Global.getICEContent(jsonResult);
				    
					logger.info("fileId:" + fileId);
					
					if(fileId != null && !fileId.trim().equals("")){
						
						licPath += fileId;
					
						licPath += ",";
					}
				} else {
					
					String message = jsonResult;

					Global.myLogger.add("saveLic", "false", message);
					
					jsonToPage(message, response);
				}
			}
		}
		
		logger.info("通过文件上传生成的文件保存路径串:" + licPath);
		
		String hasPath = "";
		for(String fill : picturebackfill) {
			
			hasPath += fill;
			
			hasPath += ",";
		}
		
		logger.info("通过文件数组生成的文件保存路径串:" + hasPath);
		
		licPath = licPath + hasPath;
		
		logger.info("1. 通过文件上传和文件数组生成的文件保存路径串:" + licPath);

		if(licPath.trim().equals("") == false) {
			
			licPath = licPath.substring(0, licPath.length() - 1);
		}
		
		logger.info("2. 通过文件上传和文件数组生成的文件保存路径串:" + licPath);
		
		licenseEntity.setLicPath(licPath);
		
		int result = licenseService.saveLicenseLegalperson(licenseEntity, legalPersonEntity);
		
		if(result > 0) {
			
			String orgUuid = licenseEntity.getOrgUuid();
			if(orgUuid == null || orgUuid.trim().equals("") == true) {
				
				String message = "失败, 法人架构orgUuid 为空！";
				
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW, message);

				Global.myLogger.add("saveLic", "false", message);
				
				// jsonToCallback(jsonpCallback, jsonString, response);
				
				jsonToPage(jsonString, response);
				
				return;
			}
			
			Map<String,Object> resultMap = licenseService.getLicenseMap(accessToken, orgUuid);
			if(resultMap == null) {
				
				String message = "失败, 没有查询到工商信息！";
				
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW, message);

				Global.myLogger.add("saveLic", "false", message);
				
			    jsonToPage(jsonString, response);
				
			    //jsonToCallback(jsonString, jsonString, response);
			}
			
			//查询法人架构详细信息
 			String org = organizationServiceHttp.getOrg(accessToken, orgUuid);
			
			JSONObject jsonObject = JSONObject.parseObject(org);
			
			resultMap.put("legalOrg", jsonObject);
			
			String mangeOrgUuid = (String) resultMap.get("manageOrgUuid");
			if(mangeOrgUuid == null || mangeOrgUuid.trim().equals("") == true) {
				
				String message = "失败, 没有查询到管理组织构架信息！";
				logger.info(message);
				
				Global.myLogger.add("saveLic", "false", message);
				
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);

			    jsonToPage(jsonString, response);
				
			    // jsonToCallback(jsonpCallback, jsonString, response);
			}
			
			//查询管理组织架构
			//Map<String,String>map= new HashMap<>();
			//map.put("orgUuid", mangeOrgUuid);
			//获取当前管理组织架构以及他的的所有父级发回给页面展示
			List<ManageOrgVo> manageOrgList = organizationServiceHttp.getManageOrgList(accessToken, mangeOrgUuid);
			
			if (manageOrgList != null && manageOrgList.size()>0) {
				resultMap.put("manageOrg", manageOrgList);
			}
			
			String content = JSON.toJSONString(resultMap);
			
			String jsonString = Global.getProtocol(content);
			
			Global.myLogger.add("saveLic", "true");
			
			jsonToPage(jsonString, response);
			
		    // jsonToCallback(jsonpCallback, jsonString, response);
		}
		else {
			
			String message = "失败！工商信息 法人信息 保存或更新失败！";
			
			logger.info(message);
			
			Global.myLogger.add("saveLic", "false", message);
			
			String jsonString = Global.getProtocol(Global.ICE_UNKNOW, message);

		    jsonToPage(jsonString, response);
			
		     // jsonToCallback(jsonpCallback, jsonString, response);
		}
	}
	
	
//	
//	/**
//	 * 根据执照Id获取执照信息和股东信息
//	 */
//	@RequestMapping("/getLic_Sha")
//	public void getShareHolderList(HttpServletResponse response,Integer licenseId) {
//		if(licenseId!=null){
//			String jsonResult = licenseService.getShareHolderList(licenseId);
//			jsonToPage(jsonResult, response);
//		}else{
//			jsonToPage(JSON.toJSONString(new Tip("参数出错！",Global.FAILD)), response);
//		}
//	}
//	
	
	
	/*
	 * 
	 * 
	 * 	// 保存工商信息，返回工商信息id
		int ok = licenseService.updateLicense(licenseEntity);
		if(ok > 0) {
			
			// 获取工商信息表id
			int lid = licenseEntity.getId();
			
			// 添加或更新法人信息表
			ok = licenseService.updateLegalPerson(legalPersonEntity);
			if(ok > 0) {
				
				int lpId = legalPersonEntity.getId();
				
				ok = licenseService.saveLicenseLegalPerson(lid, lpId);
				if(ok > 0){
					
					String orgUuid = licenseEntity.getOrgUuid();
					if(orgUuid == null || orgUuid.trim().equals("") == true) {
						
						jsonToPage(JSON.toJSONString(new Tip("失败, 法人架构orgUuid 为空！",Global.FAILD)), response);
						
						return;
					}
					
					Map<String,Object> resultMap = licenseService.getLicenseMap(orgUuid);
					if(resultMap == null) {
						
						jsonToPage(JSON.toJSONString(new Tip("失败, 没有查询到工商信息！",Global.FAILD)), response);
						
						return;
					}
					
					//查询法人架构详细信息
					Map<String, String> param = new HashMap<>();
					
					param.put("orgUuid", orgUuid);
					
					String org = organizationServiceHttp.getOrg(param);
					
					JSONObject jsonObject = JSONObject.parseObject(org);
					
					resultMap.put("legalOrg", jsonObject);
					
					String mangeOrgUuid = (String) resultMap.get("manageOrgUuid");
					if(mangeOrgUuid == null || mangeOrgUuid.trim().equals("") == true) {
						
						jsonToPage(JSON.toJSONString(new Tip("失败, 没有查询到管理组织构架信息！",Global.FAILD)), response);
						
					}
					
					//查询管理组织架构
					//Map<String,String>map= new HashMap<>();
					//map.put("orgUuid", mangeOrgUuid);
					//获取当前管理组织架构以及他的的所有父级发回给页面展示
					List<ManageOrgVo> manageOrgList = organizationServiceHttp.getManageOrgList(mangeOrgUuid);
					
					if (manageOrgList != null && manageOrgList.size()>0) {
						resultMap.put("manageOrg", manageOrgList);
					}
					
					jsonToPage(JSON.toJSONString(new Tip("成功！",Global.SUCCESS,resultMap)), response);
					
				}else{
					
					logger.info("失败！工商信息保成功，但保存工商和法人信息时失败，工商信息id" + lid + " 法人信息 :" + "->id" + lpId + " 详细信息:"+ legalPersonEntity.toString());
					
					jsonToPage(JSON.toJSONString(new Tip("失败！工商信息保成功，但保存工商和法人信息关联失败",Global.FAILD)), response);
				}
				
			} else {
				
				logger.info("失败！工商信息保成功，但保存工商和法人信息时失败，工商信息id" + lid + " 法人信息 详细信息:"+ legalPersonEntity.toString());

				jsonToPage(JSON.toJSONString(new Tip("失败！工商信息保成功，但保存法人信息时失败",Global.FAILD)), response);
			}
		} else {
			logger.info("失败！工商信息保成功，但保存工商和法人信息时失败，工商信息 详细信息:"+ licenseEntity.toString());

			jsonToPage(JSON.toJSONString(new Tip("失败！工商信息保失败！",Global.FAILD)), response);
		}
	 * 
	 * 
	 */
}
