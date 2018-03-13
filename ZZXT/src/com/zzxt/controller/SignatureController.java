package com.zzxt.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.zzxt.entity.ESignatureEntity;
import com.zzxt.entity.LegalPersonESignatureEntity;
import com.zzxt.service.LogService;
import com.zzxt.service.SignatureService;
import com.zzxt.servicehttp.FileUploadServiceHttp;
import com.zzxt.util.Global;
import com.zzxt.util.Tip;
import com.zzxt.util.UploadFileUtil;


/**
 * 电子签章控制器
 * @author think
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SignatureController extends BaseController{
	
	private Logger logger = Logger.getLogger(SignatureController.class);
	@Autowired
	private SignatureService signatureService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private FileUploadServiceHttp fileUploadServiceHttp;
	
	
	/**
	 * 查询签章
	 */
	@RequestMapping("/doSignatureSearch")
	@ResponseBody
	public void doSignatureSearch(HttpServletRequest request, HttpServletResponse response,String orgUuid, String skey, String pageIndex){
		
		int index = 0;
		if(pageIndex == null || pageIndex.trim().equals("") || pageIndex.trim().equals("undefined")) {
			
			index = 0;
		}
		else 
		{
			index = Integer.valueOf(pageIndex);
		}
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数
		
		Map<String, Object> map = signatureService.doSearch(orgUuid, skey, index);
		
		String content = JSON.toJSONString(map);
		
		String jsonString = Global.getProtocol(content);
		
		// jsonToPage(jsonString, response);
//		
//		if(Global.isICESuccessed(jsonString)) {
//			
//			Global.myLogger.add("doSignatureSearch", "true");
//		} else {
//			
//			String	message = Global.getICEMessage(jsonString);
//			
//			Global.myLogger.add("doSignatureSearch", "false", message);
//		}
		
		jsonToCallback(jsonpCallback, jsonString, response);
	}
	
	
	
//	/**
//	 * 查询签章状态
//	 */
//	@RequestMapping("/doSignatureSearchState")
//	@ResponseBody
//	public List<ESignatureEntity> doSignatureSearchState(HttpServletResponse response,String orgUuid, Integer state){
//		List<ESignatureEntity> list = signatureService.doSearchState(orgUuid, state);
//		
//		System.out.println(list);
// 
//		return list;
//	}
//	
	
	
//	/**
//	 * 获取所有电子签章
//	 */
//	@RequestMapping("/getSignatureList")
//	public void getSignatureList(HttpServletRequest request, HttpServletResponse response,String orgUuid){
//		
//		String jsonpCallback = request.getParameter("callback");//客户端请求参数
//
//		List<ESignatureEntity> list = signatureService.getSignatureList(orgUuid);
//
//		String content = JSON.toJSONString(list);
//		
//		String jsonString = Global.getProtocol(content);
//		
//		// jsonToPage(jsonString, response);
//		
////		if(Global.isICESuccessed(jsonString)) {
////			
////			Global.myLogger.add("getSignatureList", "true");
////		} else {
////			
////			String	message = Global.getICEMessage(jsonString);
////			
////			Global.myLogger.add("getSignatureList", "false", message);
////		}
//		
//		
//		jsonToCallback(jsonpCallback, jsonString, response);
//	}
	
	
	/**
	 * 获取所有电子签章
	 */
	@RequestMapping("/getSignatureList")
	public void getSignatureList(HttpServletRequest request, HttpServletResponse response,String orgUuid, String pageIndex){
		
		int index = 0;
		if(pageIndex == null || pageIndex.trim().equals("") || pageIndex.trim().equals("undefined")) {
			
			index = 0;
		}
		else 
		{
			index = Integer.valueOf(pageIndex);
		}
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数

		Map<String, Object> map = signatureService.getSignatureList(orgUuid, index);

		String content = JSON.toJSONString(map);
		
		// 做为成功不弹窗
		String jsonString = Global.getProtocol(content);
		
		
		// jsonToPage(jsonString, response);
		
//		if(Global.isICESuccessed(jsonString)) {
//			
//			Global.myLogger.add("getSignatureList", "true");
//		} else {
//			
//			String	message = Global.getICEMessage(jsonString);
//			
//			Global.myLogger.add("getSignatureList", "false", message);
//		}
		
		
		jsonToCallback(jsonpCallback, jsonString, response);
	}
	
//	/**
//	 * 获取所有电子签章
//	 */
//	@RequestMapping("/getSignatureList")
//	public void getSignatureList(HttpServletResponse response,String orgUuid, Integer pageIndex){
//		
//		Map<String, Object> map = signatureService.getSignatureList(orgUuid, pageIndex);
//
//		String content = JSON.toJSONString(map);
//		
//		String jsonString = Global.getProtocol(content);
//		
//		jsonToPage(jsonString, response);
//	}
	
	
	/**
	 * 根据id获取电子签章
	 * @param response
	 * @param sid
	 */
	@RequestMapping("/getSignature")
	public void getSignatureOne(HttpServletRequest request, HttpServletResponse response,String sid){
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数

		
		ESignatureEntity signature = signatureService.eSignature(sid);
		
		String content = JSON.toJSONString(signature);
		
		String jsonString = Global.getProtocol(content);
		
		// jsonToPage(jsonString, response);
		
//		
//		if(Global.isICESuccessed(jsonString)) {
//			
//			Global.myLogger.add("getSignature", "true");
//		} else {
//			
//			String	message = Global.getICEMessage(jsonString);
//			
//			Global.myLogger.add("getSignature", "false", message);
//		}
		
		jsonToCallback(jsonpCallback, jsonString, response);
	}
	
	
	

	/**
	 * 保存合同 v2  可以做修改操作，需要前端配合
	 * @param response
	 * @param re
	 * @param file
	 * 
	 * @date 2017年11月15日 上午3:17:35  
	 */
	@RequestMapping(value="/saveSignature",method = RequestMethod.POST)
	public void saveSignature(HttpServletResponse response, HttpServletRequest request, ESignatureEntity eSignatureEntity
			,@RequestParam(value = "file", required = false) MultipartFile file){
		
		if (eSignatureEntity == null) {
			
			String message = "无法获取到增加或编辑电子印章请求参数！";
			
			logger.info(message);
			
			Global.myLogger.add("saveSignature", "false", message);
			
			jsonToPage(Global.getProtocol(Global.ICE_UNKNOW, message), response);
		}

		
		String title = request.getParameter("title");
		String fileName = request.getParameter("fileName");
		String name = request.getParameter("name");
		
		Integer signatureId = eSignatureEntity.getId();
		
		if((file == null || file.isEmpty()) && 
		   (fileName == null || fileName.trim().equals("")) && 
		   (name == null || name.trim().equals(""))) {
		
			String message = "获取电子印章文件信息获取失败！";
			logger.info(message);
			
			Global.myLogger.add("saveSignature", "false", message);
			
			jsonToPage(Global.getProtocol(Global.ICE_UNKNOW, message), response);
		}

		HttpSession session = request.getSession();
		String sessionId = session.getId();
		
		logger.info("sessionId:" + sessionId);
		
		String accountUuid = Global.getUserInfoKey(request.getSession());
		
		String accessToken = Global.getAccessTokenV1(accountUuid);
		
		String account = Global.getUserName(accountUuid);
		
		String appName = "证照系统";
		
		String jsonResult = ""; 
		
		if(file != null && !file.isEmpty()) {
			
			jsonResult = fileUploadServiceHttp.uploadFile(accessToken, (CommonsMultipartFile) file, account, appName);
		}
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>通过ice文件微服务上传印章返回信息:" + jsonResult);
		

		if(jsonResult != null && !jsonResult.trim().equals("") && Global.isICESuccessed(jsonResult)) {
			
			fileName = file.getOriginalFilename();
			
		    name = Global.getICEContent(jsonResult);
		    
//		    jsonResult = fileUploadServiceHttp.downloadFile(accessToken, name);
//		    
//		    if(Global.isICESuccessed(jsonResult)) {
//		    		
//		    	 String fileURL = Global.getICEContent(jsonResult);
//		    	 
//		    	 logger.info("文件在线访问地址:" + fileURL);
//		    }
		}  else {
			
			String message = jsonResult;

			Global.myLogger.add("saveSignature", "false", message);
			
			jsonToPage(message, response);
		}
		
//		Tip tip = null;
//		
//		if(file != null && !file.isEmpty()){
//			
//			tip=UploadFileUtil.uploadM(file, Global.SIGNATURE_FILE_PATH, request, response);
//			
//			logger.info("tip:"+tip.toString());
//			
//			if(tip.getStateCode()==Global.SUCCESS){
//				
//				String message = "fileTimeName:"+tip.getPam();
//						
//				Global.myLogger.add("saveSignature", "true", message);
//				
//				logger.info(message);
//				
//				fileName = file.getOriginalFilename();
//				name = tip.getPam() + "";
// 
//			} 
//		}
		
		eSignatureEntity.setTitle(title);
		eSignatureEntity.setFileName(fileName);
		eSignatureEntity.setName(name);
		
		logger.info("开始添加数据:"+eSignatureEntity.toString());
		
		int esId = 0;
		
		int ok = 0;
		
		if(signatureId == null) {
			
			ok = signatureService.add(eSignatureEntity);
			
			if(ok > 0) {
				
				esId = eSignatureEntity.getId();
				
				logger.info("新增加电子印章ID:"+ esId);
				
				//  法人架构id
				String lpId = eSignatureEntity.getOrgUuid();
				
				// 保存在关联法人表
				if(esId > 0){
					
					LegalPersonESignatureEntity legalPersonESignatureEntity = new LegalPersonESignatureEntity();
					
					legalPersonESignatureEntity.setLpId(lpId);
					legalPersonESignatureEntity.setEcId(esId);
					
					signatureService.addRelate(legalPersonESignatureEntity);
					
					String message = "电子印章关联添加成功！";
					
					Global.myLogger.add("saveSignature", "true", message);
					
					jsonToPage(Global.getProtocol(Global.ICE_OK, message, false), response);
					
				}
				
			} else {
				
				String message = "电子印章添加失败！";
				
				Global.myLogger.add("saveSignature", "true", message);
				
				jsonToPage(Global.getProtocol(Global.ICE_UNKNOW, message), response);
				
				
			}
		} else {
			
			ok = signatureService.edit(eSignatureEntity);
			
			String message = "";
			
			if(ok > 0) {
				
				message = "电子印章编辑成功！";
				
				Global.myLogger.add("editSignature", "true", message);
				
				jsonToPage(Global.getProtocol(Global.ICE_OK, message, false), response);
				
			} else {
				
				message = "电子印章编辑失败！";
				
				Global.myLogger.add("editSignature", "true", message);
				
				jsonToPage(Global.getProtocol(Global.ICE_UNKNOW, message), response);
			}
			
		}
	}
	
	
	
	
	
	
//	
//	/**
//	 * 保存合同 v2
//	 * @param response
//	 * @param re
//	 * @param file
//	 * 
//	 * @date 2017年11月15日 上午3:17:35
//	 */
//	@RequestMapping(value="/saveSignature",method = RequestMethod.POST)
//	public void saveSignature(HttpServletResponse response,HttpServletRequest re,ESignatureEntity eSignatureEntity
//			,@RequestParam(value = "file", required = false) MultipartFile file){
//		if (eSignatureEntity == null) {
//				logger.info("获取请求参数失败！");
//				jsonToPage(JSON.toJSONString(new Tip("无法获取到请求参数！",Global.FAILD)), response);
//			}
//		
//		logger.info("获取请求参数ESignatureEntity:"+eSignatureEntity.toString());
//		
//		Tip tip=null;
//		
//		String title = re.getParameter("title");
//		String name = "";
//		String fileName = "";
//		
//		
//		if(file!=null&&!file.isEmpty()){
//			
//			tip=UploadFileUtil.uploadM(file, Global.SIGNATURE_FILE_PATH ,re, response);
//			
//			logger.info("tip:"+tip.toString());
//			
//			if(tip.getStateCode()==Global.SUCCESS){
//				
//				logger.info("fileTimeName:"+tip.getPam()+"");
//				
//				fileName = file.getOriginalFilename();
//				name = tip.getPam() + "";
// 
//			}else{
//				jsonToPage(JSON.toJSONString(new Tip("签章文件上传出错！",Global.FAILD)), response);
//				
//				return;
//			}
//		}
//	
//		eSignatureEntity.setTitle(title);
//		eSignatureEntity.setName(name);
//		eSignatureEntity.setFileName(fileName);
//		
//		logger.info("开始添加数据:"+eSignatureEntity.toString());
//		
//		int ecId = 0;
//		// 保存合同返回合同表ID
//		int ok = signatureService.add(eSignatureEntity);
//		if(ok > 0) {
//			ecId = eSignatureEntity.getId();
//		}
//		
//		logger.info("新增加签章ID:"+ ecId);
//		
//		//  法人架构id
//		String lpId = eSignatureEntity.getOrgUuid();
//		
//		// 保存在关联法人表
//		if(ecId>0){
//			LegalPersonESignatureEntity legalPersonESignatureEntity = new LegalPersonESignatureEntity();
//			
//			legalPersonESignatureEntity.setLpId(lpId);
//			legalPersonESignatureEntity.setEcId(ecId);
//			
//			signatureService.addRelate(legalPersonESignatureEntity);
//			
//			jsonToPage(JSON.toJSONString(new Tip("添加成功！",Global.SUCCESS,lpId)), response);
//		}else{
//			jsonToPage(JSON.toJSONString(new Tip("添加失败！",Global.FAILD)), response);
//		}
//	}
//	
//	/**
//	 * 保存电子签章
//	 * @param response
//	 * @param re
//	 * @param file
//	 */
//	@RequestMapping(value="/saveSignature",method = RequestMethod.POST)
//	public void saveLic(HttpServletResponse response,HttpServletRequest re,
//			@RequestParam(value = "file", required = false) MultipartFile file,SignatureVo sv
//			){
//			if (sv == null) {
//				logger.info("获取请求参数失败！");
//				jsonToPage(JSON.toJSONString(new Tip("无法获取到请求参数！",Global.FAILD)), response);
//			}
//		logger.info("获取请求参数LicenseEntity:"+sv.toString());
//		Tip tip=null;
//		if(file!=null&&!file.isEmpty()){
//			
//			tip=UploadFileUtil.uploadM(file, "signature", re, response);
//			logger.info("tip:"+tip.toString());
//			if(tip.getStateCode()==Global.SUCCESS){
//				logger.info("LicPath:"+tip.getPam()+"");
//				sv.setPicUrl(tip.getPam()+"");
//			}
//		}
//		sv.setDate(new Date());
//		logger.info("开始添加数据:"+sv.toString());
//		int id = signatureService.insertSignature(sv);
//		if(id>0){
//			String idStr=sv.getSid();
//			jsonToPage(JSON.toJSONString(new Tip("成功！",Global.SUCCESS,idStr)), response);
//		}else{
//			jsonToPage(JSON.toJSONString(new Tip("失败！",Global.FAILD)), response);
//		}
//	}
	 
	
	@RequestMapping("/delSignature")
	public void delSignature(HttpServletRequest request, HttpServletResponse response, String id) {
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数
		
		Integer x = signatureService.del(id);
		if (x == 1) {
			
			String message = "成功删除：" + x + "条数据";
			
			logger.info(message);
			
			Global.myLogger.add("delSignature", "true", message);
			
			//jsonToPage(Global.getProtocol(Global.ICE_OK, "成功删除：" + x + "条数据", false), response);
			
			jsonToCallback(jsonpCallback, Global.getProtocol(Global.ICE_OK, message, false), response);

		} else {
		
			String message = "删除数据失败";
			
			logger.info(message);
			
			Global.myLogger.add("delSignature", "true", message);
			
			jsonToCallback(jsonpCallback, Global.getProtocol(Global.ICE_UNKNOW, message), response);
		
		}
		
		// jsonToPage(JSON.toJSONString(id), response);
	}

}
