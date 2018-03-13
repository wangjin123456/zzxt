package com.zzxt.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.util.Arrays;
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
import com.alibaba.fastjson.JSONObject;
import com.zzxt.bean.SignEntity;
import com.zzxt.entity.EContractEntity;
import com.zzxt.entity.LegalPersonEContractEntity;
import com.zzxt.service.ContractService;
import com.zzxt.servicehttp.FileUploadServiceHttp;
import com.zzxt.util.Global;
import com.zzxt.util.Tip;
import com.zzxt.util.UploadFileUtil;


/**
 * 合同控制器
 * @author think
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ContractController extends BaseController{
	
	private Logger logger = Logger.getLogger(ContractController.class);
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private FileUploadServiceHttp fileUploadServiceHttp;

	
//	/**
//	 * 查询合同
//	 */
//	@RequestMapping("/doContractSearch")
//	@ResponseBody
//	public List<EContractEntity> doContractSearch(HttpServletResponse response,String orgUuid, String skey){
//		List<EContractEntity> list = contractService.doSearch(orgUuid, skey);
//		
//		System.out.println(list);
// 
//		return list;
//	}
	
	
	/**
	 * 查询合同
	 */
	@RequestMapping("/doContractSearch")
	@ResponseBody
	public void doContractSearch(HttpServletRequest request, HttpServletResponse response,String orgUuid, String skey, String pageIndex){
		
		int index = 0;
		if(pageIndex == null || pageIndex.trim().equals("") || pageIndex.trim().equals("undefined")) {
			
			index = 0;
		}
		else 
		{
			index = Integer.valueOf(pageIndex);
		}
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数
		
		Map<String, Object>  map = contractService.doSearch(orgUuid, skey, index);
		
		String content = JSON.toJSONString(map);
		
		String message = "doContractSearch->content:" + content;
		
		logger.info(message);
		
		// Global.myLogger.add("doContractSearch", "true", message);
		
		// 做为成功不弹窗
		String jsonString = Global.getProtocol(content);

		// jsonToPage(jsonString, response);
		
		jsonToCallback(jsonpCallback, jsonString, response);
	}
	
	
//	
//	/**
//	 * 查询合同状态
//	 */
//	@RequestMapping("/doContractSearchState")
//	@ResponseBody
//	public List<EContractEntity> doContractSearchState(HttpServletResponse response,String orgUuid, Integer state){
//		
//		List<EContractEntity> list = null;
//		if(state == Global.CONTRACT_SERACH_ALL) {
//			
//			list = contractService.getContractList(orgUuid);
//		
//		} else {
//		
//			list = contractService.doSearchState(orgUuid, state);
//		}
//		 
//		System.out.println(list);
//		
//		return list;
//	}
//	
	
	
	/**
	 * 查询合同状态
	 */
	@RequestMapping("/doContractSearchState")
	@ResponseBody
	public void doContractSearchState(HttpServletRequest request, HttpServletResponse response,String orgUuid, Integer state, String pageIndex){
		
		int index = 0;
		if(pageIndex == null || pageIndex.trim().equals("") || pageIndex.trim().equals("undefined")) {
			
			index = 0;
		}
		else 
		{
			index = Integer.valueOf(pageIndex);
		}
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数

		
		Map<String, Object>  map = null;
		if(state == Global.CONTRACT_SERACH_ALL) {
			
			map = contractService.getContractList(orgUuid, index);
		
		} else {
		
			map = contractService.doSearchState(orgUuid, state, index);
		}
		 
		String content = JSON.toJSONString(map);
		
		// 做为成功不弹窗
		String jsonString = Global.getProtocol(content);

		// jsonToPage(jsonString, response);
		
//		 
//		if(Global.isICESuccessed(jsonString)) {
//			
//			Global.myLogger.add("doContractSearchState", "true");
//			
//		} else {
//			
//			String message = Global.getICEMessage(jsonString);
//			
//			Global.myLogger.add("doContractSearchState", "false", message);
//		}

		jsonToCallback(jsonpCallback, jsonString, response);
	}
	
	

//	/**
//	 * 获取所有合同
//	 */
//	@RequestMapping("/getContractList")
//	@ResponseBody
//	public List<EContractEntity> getContractList(HttpServletResponse response,String orgUuid){
//		List<EContractEntity> list = contractService.getContractList(orgUuid);
//		
//		System.out.println(list);
// 
//		return list;
//	}
//	

//	/**
//	 * 获取所有合同
//	 */
//	@RequestMapping("/getContractList")
//	@ResponseBody
//	public void getContractList(HttpServletResponse response,String orgUuid){
//		List<EContractEntity> list = contractService.getContractList(orgUuid);
//		
//		
//		String content = JSON.toJSONString(list);
//		
//		// 做为成功不弹窗
//		String jsonString = Global.getProtocol(content);
//
//		jsonToPage(jsonString, response);
//	}
//	
	

	/**
	 * 分页获取合同列表
	 */
	@RequestMapping("/getContractList")
	@ResponseBody
	public void getContractList(HttpServletRequest request, HttpServletResponse response,String orgUuid, String pageIndex){
		
		int index = 0;
		if(pageIndex == null || pageIndex.trim().equals("") || pageIndex.trim().equals("undefined")) {
			
			index = 0;
		}
		else 
		{
			index = Integer.valueOf(pageIndex);
		}
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数
		
		Map<String, Object> map = contractService.getContractList(orgUuid, index);
		
		String content = JSON.toJSONString(map);
		
		// 做为成功不弹窗
		String jsonString = Global.getProtocol(content);

		// jsonToPage(jsonString, response);
		
//		if(Global.isICESuccessed(jsonString)) {
//			
//			Global.myLogger.add("getContractList", "true");
//			
//		} else {
//			
//			String message = Global.getICEMessage(jsonString);
//			
//			Global.myLogger.add("getContractList", "false", message);
//		}
		
		jsonToCallback(jsonpCallback, jsonString, response);
	}
	
//	/**
//	 * 根据id获取合同
//	 * @param response
//	 * @param sid
//	 */
//	@RequestMapping("/getContract")
//	@ResponseBody
//	public EContractEntity getContract(HttpServletResponse response, String contractId){
//		
//		logger.info("getContract -> contractId:" + contractId);
//		
//		return contractService.eContract(contractId);
//		
//	}

	/**
	 * 根据id获取合同
	 * @param response
	 * @param sid
	 */
	@RequestMapping("/getContract")
	@ResponseBody
	public void getContract(HttpServletRequest request, HttpServletResponse response, String contractId){
		
		logger.info("getContract -> contractId:" + contractId);
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数
		
		EContractEntity eContractEntity = contractService.eContract(contractId);
		
		String content = JSON.toJSONString(eContractEntity);
		
		// 做为成功不弹窗
		String jsonString = Global.getProtocol(content);

		// jsonToPage(jsonString, response);
		
//		if(Global.isICESuccessed(jsonString)) {
//			
//			Global.myLogger.add("getContract", "true");
//			
//		} else {
//			
//			String message = Global.getICEMessage(jsonString);
//			
//			Global.myLogger.add("getContract", "false", message);
//		}
	
		
		jsonToCallback(jsonpCallback, jsonString, response);
	}
	
	
	
/*	@RequestMapping("/getContractOne")
	public void getContractOne(HttpServletResponse response,String sid){
		ContractEntity Contract = ContractService.findContractOne(sid);
		//System.out.println(list);
		beanJsonToPage(Contract,response);
		
	}*/
/*	@RequestMapping(value="/saveLic",method = RequestMethod.POST)
	public void saveLic(HttpServletResponse response,HttpServletRequest re,
			@RequestParam(value = "file", required = false) MultipartFile file
			){
		LicenseEntity licPam=null;
		try {
			licPam=new LicenseEntity().getPamValue(re);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.info("获取请求参数失败！");
			jsonToPage(JSON.toJSONString(new Tip("无法获取到请求参数！",Global.FAILD)), response);
			e.printStackTrace();
		}
		logger.info("获取请求参数LicenseEntity:"+licPam.toString());
		Tip tip=null;
		if(file!=null&&!file.isEmpty()){
			
			tip=UploadFileUtil.uploadM(file, "license", Timestamp.getNowDateStr("yyyyMMddHHmmss"), re, response);;
			logger.info("tip:"+tip.toString());
			if(tip.getStateCode()==Global.SUCCESS){
				logger.info("LicPath:"+tip.getPam()+"");
				licPam.setLicPath(tip.getPam()+"");
			}
		}
		logger.info("开始添加数据:"+licPam.toString());
		int id = licenseService.insertLic(licPam);
		if(id>0){
			id=licPam.getId();
			jsonToPage(JSON.toJSONString(new Tip("成功！",Global.SUCCESS,id)), response);
		}else{
			jsonToPage(JSON.toJSONString(new Tip("失败！",Global.FAILD)), response);
		}
	}*/
	
	
	 
	
		/**
		 * 下载合同 v2
		 * @param response
		 * @param re
		 * @param file
		 * 
		 * @date 2018年01月06日 下午4:16:00  
		 * 
		 * 
		 * @desc 通过文件id将从文件微服务下载文件
		 */
		@RequestMapping(value="/downloadFileFromFileId",method = RequestMethod.GET)
		public void getContractFile(HttpServletResponse response, HttpServletRequest request,  String fileId){
			
			if (fileId == null) {
			
				String message = "合同文件id为空不能继续从ice文件微服务下载！";
				
				logger.info(message);
				
				Global.myLogger.add("getContractFile", "false", message);
				
				String failProtocol = Global.getProtocol(Global.ICE_UNKNOW, message);
					
				jsonToPage(failProtocol, response);
			}
			
			String jsonpCallback = request.getParameter("callback");//客户端请求参数
			
			HttpSession session = request.getSession();
			String sessionId = session.getId();
			
			logger.info("sessionId:" + sessionId);
			
			String accountUuid = Global.getUserInfoKey(request.getSession());
			
			String accessToken = Global.getAccessTokenV1(accountUuid);
			
//			String account = Global.getUserName(accountUuid);
//			
//			String appName = "证照系统";
			
			if(accessToken == null || accessToken.trim().equals("")) {
				
				String message = Global.RELOGIN_HINT;
				
				String protocol = Global.getProtocol(Global.ICE_UNKNOW, message);
				
				Global.myLogger.add("getContractFile", "false", message);
				
				jsonToCallback(jsonpCallback, protocol, response);
			}
			
		   String jsonResult = fileUploadServiceHttp.downloadFile(accessToken, fileId);
		    if(Global.isICESuccessed(jsonResult)) {
		    		
			    	 String fileURL = Global.getICEContent(jsonResult);
			    	 
			    	 logger.info("文件在线访问地址:" + fileURL);
		    }
		    
		    Global.myLogger.add("getContractFile", "true", jsonResult);
		    
		    jsonToCallback(jsonpCallback, jsonResult, response);
		}
		
	
	/**
	 * 保存合同 v2  可以做修改操作，需要前端配合
	 * @param response
	 * @param re
	 * @param file
	 * 
	 * @date 2017年11月15日 上午3:17:35  
	 */
	@RequestMapping(value="/saveContract",method = RequestMethod.POST)
	public void saveContract(HttpServletResponse response, HttpServletRequest request, EContractEntity eContractEntity
			,@RequestParam(value = "file", required = false) MultipartFile file){
		
		if (eContractEntity == null) {
		
			String message = "无法获取到增加或编辑合同请求参数！";
			
			logger.info(message);
			
			Global.myLogger.add("saveContract", "false", message);
			
			String failProtocol = Global.getProtocol(Global.ICE_UNKNOW, message);
				
			jsonToPage(failProtocol, response);
		}
		
		String title = request.getParameter("title");
		String fileName = request.getParameter("fileName");
		String name = request.getParameter("name");
		
		Integer contractId = eContractEntity.getId();
		
		if((file == null || file.isEmpty()) && 
		   (fileName == null || fileName.trim().equals("")) && 
		   (name == null || name.trim().equals(""))) {
		
			String message = "合同文件信息获取失败！";
			
			logger.info(message);
			
			Global.myLogger.add("saveContract", "false", message);
			
			String failProtocol = Global.getProtocol(Global.ICE_UNKNOW, message);
			
			jsonToPage(failProtocol, response);
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
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>通过ice文件微服务上传合同返回信息:" + jsonResult);
		

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
		} else {
			
			String message = jsonResult;

			Global.myLogger.add("saveContract", "false", message);
			
			jsonToPage(message, response);
		}
		
//		Tip tip = null;
//		
//		if(file != null && !file.isEmpty()){
//			
//			tip=UploadFileUtil.uploadM(file, Global.CONTRACT_FILE_PATH, request, response);
//			
//			logger.info("tip:"+tip.toString());
//			
//			if(tip.getStateCode()==Global.SUCCESS){
//				
//				String message = "fileTimeName:"+tip.getPam()+"";
//				
//				logger.info(message);
//			
//				Global.myLogger.add("saveContract", "true", message);
//				
//				fileName = file.getOriginalFilename();
//				
//				name = tip.getPam() + "";
//			} 
//		}
		
		eContractEntity.setTitle(title);
		eContractEntity.setFileName(fileName);
		eContractEntity.setName(name);
		eContractEntity.setState(0);
		
		logger.info("开始添加数据:"+eContractEntity.toString());
		
		int ecId = 0;
		
		int ok = 0;
		
		if(contractId == null) {
			ok = contractService.add(eContractEntity);
			
			if(ok > 0) {
				
				ecId = eContractEntity.getId();
				
				logger.info("新增加合同ID:"+ ecId);
				
				//  法人架构id
				String lpId = eContractEntity.getOrgUuid();
				
				// 保存在关联法人表
				if(ecId > 0){
					
					LegalPersonEContractEntity legalPersonEContractEntity = new LegalPersonEContractEntity();
					
					legalPersonEContractEntity.setLpId(lpId);
					legalPersonEContractEntity.setEcId(ecId);
					legalPersonEContractEntity.setHolder(1);
					contractService.addRelate(legalPersonEContractEntity);
					
					String message = "合同关联添加成功！";
					String jsonString = Global.getProtocol(Global.ICE_OK, message, false);
					
					Global.myLogger.add("saveContract", "true", message);
					
					jsonToPage(jsonString, response);
					
				}
				
			} else {
				
				String message = "合同添加失败！"; 
				String failProtocol = Global.getProtocol(Global.ICE_UNKNOW, message);
				
				Global.myLogger.add("saveContract", "false", message);
				
				jsonToPage(failProtocol, response);
				
			}
		} else {
			
			ok = contractService.edit(eContractEntity);
			if(ok > 0) {
				
				String message = "编辑合同成功！";
				
				logger.info(message);
				
				Global.myLogger.add("editContract", "true", message);
				
				String jsonString = Global.getProtocol(Global.ICE_OK, message, false);
				
				jsonToPage(jsonString, response);

				
				
			} else {
								
				String message = "合同编辑失败！";
				
				logger.info(message);
				
				Global.myLogger.add("editContract", "false", message);
				
				String failProtocol = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				jsonToPage(failProtocol, response);
			}
			
		}
	}
	
	
	
	
	
	
	/**
	 * 添加签署者
	 */
//	@RequestMapping("/addSigner")
//	@ResponseBody
//	public Tip addSigner(String orgUuid, String cuuid) {
//		int i = contractService.addSigner(orgUuid, cuuid);
//		if (i > 0) {
//			return new Tip("添加签署者成功", Global.SUCCESS);
//		} else if (i == -1) {
//			return new Tip("不能重复添加！", Global.FAILD);
//		} else {
//			return new Tip("添加签署者失败", Global.FAILD);
//
//		}
//
//	}
	
	
	/**
	 * @author zhao
	 * @param
	 * 
	 * 添加签置者，并关联签署者
	 * 
	 * @date  2017年11月15日 上午5:53:00
	 */
	
	@RequestMapping(value="/addSigner",method = RequestMethod.GET)
	public void addSigner(HttpServletRequest request, HttpServletResponse response, Integer ecId, String lpId){
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数
		
		if(ecId != null && lpId != null && (lpId.trim().equals("") == false)) {
			
			String jsonString = contractService.addSigner(ecId, lpId);
			
			JSONObject jsonObject = JSONObject.parseObject(jsonString);
			if(jsonObject != null) {
				
				Integer code = jsonObject.getInteger(Global.PROTOCOL_CODE_KEY);
				if(code == Global.ICE_UNKNOW) {
					
					// jsonToPage(jsonString, response);
					
					//String message = Global.getICEMessage(jsonString);
					
					jsonToCallback(jsonpCallback, jsonString, response);
					
				} else {
					
					// 1.查询签署者
					List<Map<String, String>> signers = contractService.getSignerList(String.valueOf(ecId));
					 
					 Map<String, Object> resultMap = new HashMap<>();
					 
					 resultMap.put("signers", signers);
					 
					 String content = JSON.toJSONString(resultMap);
					 
					 jsonString = Global.getProtocol(content);
					 
					 // jsonToPage(jsonString, response);
					 
					 jsonToCallback(jsonpCallback, jsonString, response);
				}
			}
		}
		
	}
	

	
	
	/**
	 * @author zhao
	 * @param
	 * 
	 * 移除签置者
	 * 
	 * @date  2017年11月15日 上午5:53:00
	 */
	
	@RequestMapping(value="/removeSigner",method = RequestMethod.GET)
	public void removeSigner(HttpServletRequest request, HttpServletResponse response,HttpServletRequest re, String id){
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数
		
		if(id != null && (id.trim().equals("") == false)) {
			
			contractService.removeSigner(id);
			
			// jsonToPage(Global.getProtocol(Global.ICE_OK, "移除签署者成功！", false), response);
			
			String message = "移除签署者成功！";
			
			Global.myLogger.add("removeSigner", "true", message);
			
			jsonToCallback(jsonpCallback, Global.getProtocol(Global.ICE_OK, message, false), response);
		}
		else 
		{
			
			String message = "移除签署者失败！";
			
			Global.myLogger.add("removeSigner", "false", message);
			// jsonToPage(Global.getProtocol(Global.ICE_UNKNOW, "移除签署者失败！"), response);
			
			jsonToCallback(jsonpCallback, Global.getProtocol(Global.ICE_UNKNOW, message, false), response);
		}
	}
	
	
	
	
	@RequestMapping(value="/reSigning",method = RequestMethod.GET)
	public void reSigning(HttpServletRequest request, HttpServletResponse response,HttpServletRequest re, String id, String ecId, String orgUuid){
			
		String jsonpCallback = request.getParameter("callback");//客户端请求参数
		
		if (ecId == null || ecId.trim().equals("")) {
				logger.info("获取请求参数失败！");
				jsonToPage(JSON.toJSONString(new Tip("无法获取到请求参数！",Global.FAILD)), response);
			}
		
		int r = contractService.reSigning(id, ecId, orgUuid);
		
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("state", Integer.valueOf(r));
		
		// jsonToPage(JSON.toJSONString(new Tip("添加成功！",Global.SUCCESS, resultMap)), response);
		
		jsonToCallback(jsonpCallback, JSON.toJSONString(new Tip("添加成功！",Global.SUCCESS, resultMap)), response);
	}
	
	
	
	
//	@RequestMapping(value="/saveContract",method = RequestMethod.POST)
//	public void saveLic(HttpServletResponse response,HttpServletRequest re,ContractVo cv
//			,@RequestParam(value = "file", required = false) MultipartFile file){
//			
//		if (cv == null) {
//				logger.info("获取请求参数失败！");
//				jsonToPage(JSON.toJSONString(new Tip("无法获取到请求参数！",Global.FAILD)), response);
//			}
//		
//		logger.info("获取请求参数ContractVo:"+cv.toString());
//		
//		Tip tip=null;
//		
//		if(file!=null&&!file.isEmpty()){
//			
//			tip=UploadFileUtil.uploadM(file, "contract",re, response);
//			
//			logger.info("tip:"+tip.toString());
//			
//			if(tip.getStateCode()==Global.SUCCESS){
//				logger.info("fileUrl:"+tip.getPam()+"");
//				cv.setFileUrl(tip.getPam()+"");
//				cv.setPicUrl(ListToStringUtil.listToString(tip.getUrls()));
//			}else{
//				jsonToPage(JSON.toJSONString(new Tip("合同文件上传出错！",Global.FAILD)), response);
//				return;
//			}
//		}
//		cv.setStatus(0);
//		cv.setDate(new Date());
//		logger.info("开始添加数据:"+cv.toString());
//		int id = contractService.insertContract(cv);
//		if(id>0){
//			String idStr=cv.getCuuid();
//			LicenseEntity licinfo = licenseService.getLicinfo(cv.getOrgUuid());
//			if (licinfo != null) {
//				Integer licId = licinfo.getId();
//				LicenseContractEntity lce = new LicenseContractEntity();
//				lce.setContract_uuid(idStr);
//				lce.setLicense_id(licId);
//				licenseContractService.insertLicenseContract(lce);
//				
//			}
//			jsonToPage(JSON.toJSONString(new Tip("添加成功！",Global.SUCCESS,idStr)), response);
//		}else{
//			jsonToPage(JSON.toJSONString(new Tip("添加失败！",Global.FAILD)), response);
//		}
//	}
	
	@RequestMapping("/delContract")
	public void delContract(HttpServletRequest request, HttpServletRequest re, HttpServletResponse response, String cuuid) {
		
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数
		
		Integer x = contractService.delContract(cuuid);
		if (x == 1) {
			
			String message = "成功删除：" + x + "条数据";
			
			logger.info(message);
		
			Global.myLogger.add("delContract", "true", message);
			
			// jsonToPage(Global.getProtocol(Global.ICE_OK, "成功删除：" + x + "条数据", false), response);
			
			jsonToCallback(jsonpCallback, Global.getProtocol(Global.ICE_OK, message, false), response);
		
			
		} else {
			
			String message = "删除数据失败";
			
			logger.info(message);
			
			// jsonToPage(Global.getProtocol(Global.ICE_UNKNOW, "删除数据失败"), response);
			
			Global.myLogger.add("delContract", "true", message);
			
			jsonToCallback(jsonpCallback, Global.getProtocol(Global.ICE_UNKNOW, message, false), response);
		}
		
		// jsonToPage(JSON.toJSONString(cuuid), response);
	}

	
	/**
	 * @author zhao
	 * @param
	 * 
	 * 查看合同 打开pdf 转换成jpg传回
	 * 
	 * @date  2017年11月15日 上午5:53:00
	 */
	
	@RequestMapping("/showConDetail")
	public void showContract(HttpServletRequest request, HttpServletResponse response, String cuuid, String orgUuid) {
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数
		
		Map<String, Object> contractMap = contractService.showContract(cuuid, orgUuid);
	
		String message = "";
		String jsonString = "";
		
		int code = Global.ICE_OK;
		
		if(contractMap != null) {
			
			Object o = contractMap.get("PdfToImageInfo");
			
			if(o != null) {
				
				String info = o.toString();
				
				String[] list = info.split(":");
				
				int len = list.length;
				if(len == 2) {
					
					String c= list[0];
					String m = list[1];
					
					if(c != null && !c.trim().equals("")) {
						code = Integer.valueOf(c);
					}
					
					message = m;
				}
			}
			
			contractMap.remove("PdfToImageInfo");
			
			if(code == Global.ICE_UNKNOW) {
				
				jsonString = Global.getProtocol(code, message);
			}
			else {
				
				o = contractMap.get("initCon");
				if(o != null) {
						
					String info = o.toString();
					
					String[] list = info.split(":");
					
					int len = list.length;
					if(len == 2) {
						
						String c= list[0];
						String m = list[1];
						
						if(c != null && !c.trim().equals("")) {
							code = Integer.valueOf(c);
						}
						
						message = m;
					}
					}
					
					contractMap.remove("initCon");
					
					String content = JSON.toJSONString(contractMap);
					
					if(code == Global.ICE_UNKNOW) {
						
						jsonString = Global.getProtocol(code, message);
					}
					else  {
						
						jsonString =  Global.getProtocol(content);
					}
					
			}
			
		} else {
			
			message = "合同文件信息异常，文件没有找到，不能显示";
			
			jsonString =  Global.getProtocol(Global.ICE_UNKNOW, message);
		}
			
		//jsonToPage(jsonString, response);
		
		
//		if(Global.isICESuccessed(jsonString)) {
//			
//			Global.myLogger.add("showConDetail", "true");
//		} else {
//			
//			 message = Global.getICEMessage(jsonString);
//			
//			Global.myLogger.add("showConDetail", "false", message);
//		}
		
		
		jsonToCallback(jsonpCallback, jsonString, response);
	}
	
	
	



	/**
	 * 申请ca证书
	 * 
	 * @param orgUuid
	 * @return
	 */
	@RequestMapping("/applyCert")
	@ResponseBody
	public void userAsyncApplyCertSubmit(HttpServletRequest request, HttpServletResponse response, String orgUuid) {
		
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数
		
		if(orgUuid == null || orgUuid.trim().equals("") == true || orgUuid.length() < 10) {

			String message = "Ca认证 法人架构Id获取失败(orgUuid:" + orgUuid + ")";

			Global.myLogger.add("applyCert", "false", message);
			
			String jsonString = Global.getProtocol(Global.ICE_UNKNOW, message);
			
			// jsonToPage(jsonString, response);
			
			jsonToCallback(jsonpCallback, jsonString, response);
		}
		
		String jsonString =  contractService.userAsyncApplyCertSubmit(orgUuid, Global.SHANG_SHANG_SIGN);
	
		// jsonToPage(jsonString, response);
				
		jsonToCallback(jsonpCallback, jsonString, response);
	}
	
	
	
	
	/**
	 * 申请ca证书
	 * 
	 * @param orgUuid
	 * @return
	 */
	@RequestMapping("/doZhongShuiCert")
	@ResponseBody
	public void doZhongShuiCert(HttpServletRequest request, HttpServletResponse response, String orgUuid) {
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数
		
		if(orgUuid == null || orgUuid.trim().equals("") == true || orgUuid.length() < 10) {

			String message = "Ca认证 法人架构Id获取失败(orgUuid:" + orgUuid + ")";

			Global.myLogger.add("applyCert", "false", message);
			
			String jsonString = Global.getProtocol(Global.ICE_UNKNOW, message);
			
			// jsonToPage(jsonString, response);
			
			jsonToCallback(jsonpCallback, jsonString, response);
		}
		
		String jsonString =  contractService.userAsyncApplyCertSubmit(orgUuid, Global.ZHONG_SHUI_SIGN);
	
		// jsonToPage(jsonString, response);
				
		jsonToCallback(jsonpCallback, jsonString, response);
	}
	
	
	

	/**
	 * 查询证书申请情况
	 * 
	 * @param orgUuid
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/certStatus", produces = "application/json; charset=utf-8")
	@ResponseBody
	public void  userAsyncApplyCertStatus(HttpServletRequest request, HttpServletResponse response, String orgUuid, String taskId) {
        
		String jsonpCallback = request.getParameter("callback");//客户端请求参数
		
		String jsonString = contractService.userAsyncApplyCertStatus(orgUuid, taskId);
	
         // jsonToPage(jsonString, response);
		
//		
//		if(Global.isICESuccessed(jsonString)) {
//			
//			Global.myLogger.add("certStatus", "true");
//		} else {
//			
//			String message = Global.getICEMessage(jsonString);
//			
//			Global.myLogger.add("certStatus", "false", message);
//		}
         
         jsonToCallback(jsonpCallback, jsonString, response);
	}

	/**
	 * 查询证书编号：
	 * 
	 * @param account
	 * @param certType
	 * @return
	 */
	@RequestMapping(value = "/userGetCert", produces = "application/json; charset=utf-8")
	@ResponseBody
	public void userGetCert(HttpServletRequest request, HttpServletResponse response, String orgUuid) {

		String jsonpCallback = request.getParameter("callback");//客户端请求参数
		
		String content = contractService.userGetCert(orgUuid);
		String jsonString = Global.getProtocol(content);
				
		// jsonToPage(jsonString, response);
//		
//		if(Global.isICESuccessed(jsonString)) {
//			
//			Global.myLogger.add("userGetCert", "true");
//		} else {
//			
//			String message = Global.getICEMessage(jsonString);
//			
//			Global.myLogger.add("userGetCert", "false", message);
//		}
		
		jsonToCallback(jsonpCallback, jsonString, response);
	}

//	// 展示合同、公司章、签署者
//	@RequestMapping("/showConDetail")
//	@ResponseBody
//	public Map<String, Object> showContractDetai(String cuuid, String orgUuid) {
//
//		return bestSignService.getSealAndSigner(cuuid, orgUuid);
//	}

	/**
	 * 设置签名/印章图片
	 * 
	 * @param orgUuid
	 * @return
	 */
	@RequestMapping(value = "/setSignImage")
	@ResponseBody
	public void setUserSignatureImage(HttpServletRequest request, HttpServletResponse response, String sid) {

		String jsonpCallback = request.getParameter("callback");//客户端请求参数
		
		String jsonString = contractService.setUserSignatureImage(sid);

		// jsonToPage(jsonString, response);
		
		jsonToCallback(jsonpCallback, jsonString, response);
	}


	
	/**
	 * 合同初始化
	 */
	@RequestMapping("/initContract")
	@ResponseBody
	public void initContract(HttpServletRequest request, HttpServletResponse response, String cuuid){
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数
		
		String jsonString = contractService.initContract(cuuid);

		// jsonToPage(jsonString, response);
		
//		
//		if(Global.isICESuccessed(jsonString)) {
//			
//			Global.myLogger.add("initContract", "true");
//		} else {
//			
//			String message = Global.getICEMessage(jsonString);
//			
//			Global.myLogger.add("initContract", "false", message);
//		}
		
		
		jsonToCallback(jsonpCallback, jsonString, response);
	}

	/**
	 * 签署合同
	 * 
	 * @param cuuid
	 *            合同uuid
	 * @param signEntity
	 *            签署数据实体
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/signContract")
	@ResponseBody
	public void signContract(HttpServletRequest request, HttpServletResponse response, String cuuid, SignEntity signEntity,String orgUuid) throws ParseException {


		String jsonpCallback = request.getParameter("callback");//客户端请求参数

		
		String jsonString = contractService.signContract(cuuid, signEntity,orgUuid);

		// jsonToPage(jsonStrig, response);
		
		
//		if(Global.isICESuccessed(jsonString)) {
//			
//			Global.myLogger.add("signContract", "true");
//		} else {
//			
//			String message = Global.getICEMessage(jsonString);
//			
//			Global.myLogger.add("signContract", "false", message);
//		}
		
		jsonToCallback(jsonpCallback, jsonString, response);
	}

	/**
	 * 获取合同详细信息
	 * 
	 * @param cuuid
	 * @return
	 */
	@RequestMapping("/contractInfo")
	public JSONObject contractGetInfo(HttpServletRequest request, HttpServletResponse response, String cuuid) {
		return contractService.contractGetInfo(cuuid);
	}
	
	
	
//	/**
//	 * 下载合同
//	 * 
//	 * @param cuuid
//	 * @param response
//	 */
//
//	@RequestMapping(value = "/downloadCon")
//	public void downPhotoByStudentId(HttpServletRequest re, HttpServletResponse response, String cuuid) {
//
//		Map<String, Object> contract = contractService.contractDownload(cuuid);
//		byte[] data = (byte[]) contract.get("contractData");
//		String contractName = (String) contract.get("contractName");
//		String fileName = contractName == null ? "合同.pdf" : contractName + ".pdf";
//		try {
//			fileName = URLEncoder.encode(fileName, "UTF-8");
//			response.reset();
//			response.setHeader("Access-Control-Allow-Origin", "*");
//			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//			response.addHeader("Content-Length", "" + data.length);
//			response.setContentType("application/octet-stream;charset=UTF-8");
//			OutputStream outputStream = null;
//
//			outputStream = new BufferedOutputStream(response.getOutputStream());
//
//			outputStream.write(data);
//			outputStream.flush();
//			outputStream.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	  
	 
	/**
	 * 下载合同
	 * 
	 * @param cuuid
	 * @param response
	 */

	@RequestMapping(value = "/downloadCon")
	public void downloadContract(HttpServletRequest request, HttpServletResponse response, String cuuid) {
		
		Map<String, Object> map = contractService.contractDownload(cuuid);
		
		Integer code = Integer.parseInt(map.get("code").toString());
		
		if(code == Global.ICE_UNKNOW) {
			
			String message = "下载签好合同操作产生错误, 下载能不被继续执行,详细信息:" + map.get("message").toString();
			
			logger.info(message);
			
			Global.myLogger.add("downloadCon", "false", message);
			
			return;
		}
	 
		
		byte[] data = (byte[]) map.get("contract");
		
		logger.info("下载前生成的签好合同二进制文件:" + Arrays.toString(data));
		 
		String contractFile = map.get("contractFile").toString();
		
		String fileName = map.get("fileName").toString();
 		
		OutputStream outputStream = null;
		InputStream inputStream = null;
		
		try {
			
			response.reset();
			
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.reset();
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			// response.addHeader("Content-Length", "" + data.length);
			response.setContentType("application/octet-stream;charset=UTF-8");
			  
			
			String type = Global.getConfig(Global.CONTRACT_DOWNLOAD_TYPE_KEY);
			
			if(type.equals("byte")) {
			
				response.setHeader("Content-Length", "" + data.length);
				
				outputStream = new BufferedOutputStream(response.getOutputStream());

				outputStream.write(data);
				
				outputStream.flush();
				
			} else {
				
				String path = Global.getBasePath() + Global.CONTRACT_FILE_PATH + File.separator + contractFile;
				
				logger.info("正在下载签好合同文件:" + path);
				
				long len = 0;
				
				File file = new File(path);  
				len = file.length();
				
				response.setHeader("Content-Length", String.valueOf(len));

				inputStream  = new FileInputStream(path);
				
				outputStream = new BufferedOutputStream(response.getOutputStream());
				
				byte[] buffer = new byte[1024];  
				
				int bytesRead = 0;  
	
				while((bytesRead = inputStream.read(buffer, 0, 1024)) != -1){  
					
					outputStream.write(buffer, 0, bytesRead);  
			    }
				
				outputStream.flush();
			}
			 
		} catch(IOException e) {
			
			e.printStackTrace();
			
			String message = "下载签好合同操作产生异常, 详细信息:" + e.getLocalizedMessage();
			
			logger.info(message);
			
		} finally {
			
		    try {
		        
		    		if(inputStream != null){
		        	
		        		inputStream.close();
		        }
		    		
		        if(outputStream != null){
		        		
		        		outputStream.close();
		        }
		        
		    } catch (IOException e) {
		       
		    		e.printStackTrace(); 
		    }
		}
	}
	
	
	
//
//	/**
//	 * 锁定合同
//	 * 
//	 * @param cuuid
//	 * @return
//	 */
//	@RequestMapping("/lockCon")
//	@ResponseBody
//	public Tip lockContract(String cuuid) {
//		return contractService.lockContract(cuuid);
//	}
//
//	/**
//	 * 结束合同
//	 * 
//	 * @param cuuid
//	 * @return
//	 */
//	@RequestMapping("/finishCon")
//	@ResponseBody
//	public Tip finishContract(String cuuid) {
//		return contractService.finishContract(cuuid);
//	}
//	
}
