package com.zzxt.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzxt.entity.EContractEntity;
import com.zzxt.entity.LegalPersonEContractEntity;
import com.zzxt.service.ContractService;
import com.zzxt.servicehttp.OrganizationServiceHttp;
import com.zzxt.util.Global;
import com.zzxt.util.JsonCast;
import com.zzxt.util.Tip;
import com.zzxt.util.UploadFileUtil;






class ContractBean {
	
	String orgUuid;
	
	String title;
	
	String fileName;
	
	String fileUrl;

	String returnUrl;
	
	String dateTime;
	
	String comment;
		

	public ContractBean() {
		super();
		// TODO Auto-generated constructor stub
	}

 
	
	public ContractBean(String orgUuid, String title, String fileName, String fileUrl, String returnUrl,
			String dateTime, String comment) {
		super();
		this.orgUuid = orgUuid;
		this.title = title;
		this.fileName = fileName;
		this.fileUrl = fileUrl;
		this.returnUrl = returnUrl;
		this.dateTime = dateTime;
		this.comment = comment;
	}



	public String getOrgUuid() {
		return orgUuid;
	}

	public void setOrgUuid(String orgUuid) {
		this.orgUuid = orgUuid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getReturnUrl() {
		return returnUrl;
	}


	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}


	public String getDateTime() {
		return dateTime;
	}


	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	
	
	
}



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ReviewController extends BaseController {

	private static Logger logger = Logger.getLogger(TreeController.class.getName());

	
	@Autowired
	OrganizationServiceHttp organizationServiceHttp;
	
	@Autowired
	private ContractService contractService;
	
	
	/*
	 * 给审批使用接口，获取法人架构列表
	 * 
	 * @author 赵玺翔
	 * 
	 * @param orgUuid 默认为 0 familyTypeId 为 2
	 * 
	 * @Time 2017-12-13 01:12
	 * 
	 * @return orgList
	 * 
	 */
	@RequestMapping("/getLegalPersonTree")
	public void getTree(HttpServletRequest request, HttpServletResponse response, String oaUser, String token, String orgUuid) {
		

		if(orgUuid == null || orgUuid.trim().equals("")) {
			
			orgUuid = "0";
		}
		
//		Map<String,String>map= new HashMap<String, String>();
//		map.put("token", token);
//		
//		logger.info(map.keySet()+">>>>>>>>>>>>>>>>>"+map.values());
		
		String orgTreeJson = organizationServiceHttp.getOrgTresJson(token, orgUuid, Global.STRUCTURE_TYPE_LEGALPERSON);
		logger.info("---------证照树形列表--------" + orgTreeJson);
		
		if(orgTreeJson == null) {
			orgTreeJson = "没有获取法人架构列表";
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("LegalPersonTree", orgTreeJson);
		
		String resultJson = JsonCast.collectToString(resultMap);
		
		logger.info("result info:"+ resultJson);
		
		jsonToPage(resultJson, response);
	}
	
//	@RequestMapping("/doOALogin")
//	public void doOALogin(HttpServletRequest request, HttpServletResponse response, UserAccountBean user) {
//		
//		// 账户匹配结果信息
//		String loginJson = loginServiceHttp.matchAccount(user);
//		
//		Boolean success = ParseJsonResult.success(loginJson);
//		
//		Map<String, Object>resutlMap = new HashMap<String, Object>();
//		
//		String jsonResult = "";
//		
//		logger.info("JSON USER INFO :" + loginJson);
//		
//		resutlMap.put("LoginInfo", loginJson);
//		
//		if (success == true) {			 
//			 // 前后台分离联调时运用
//			 redisCacheUtil.set(Md5Util.md5(Global.ACCOUNT.replace("sessionId", super.getSessionID(request, response, super.getSessionKey()))), loginJson);
//			 redisCacheUtil.expire(Md5Util.md5(Global.ACCOUNT.replace("sessionId", super.getSessionID(request, response, super.getSessionKey()))), Global.TWO_HOUR_SECOND);
//		}
//		else {
//			
//			jsonResult = JsonCast.collectToString(resutlMap);
//			
//			jsonToPage(jsonResult, response);
//		}
//		 
//		// 登录成功 继续完成鉴权
//		Map<String,String> map = super.getCorpId_token(request, response, super.getSessionKey());
//		
//		String token1 = authServiceHttp.getToken(map.get("corpId"));
//		map.put("token", token1);
//			
//		logger.info("account:"+map.get("account")+",token:"+map.get("token"));
//		if (map.get("token") != null) {
//			 
//			String token=Md5Util.md5(Global.TOKEN.replace("sessionId", super.getSessionID(request, response,super.getSessionKey())));
//		
//			logger.info("Login token:" + token);
//			redisCacheUtil.set(token, map.get("token"));
//			redisCacheUtil.expire(token, Global.TWO_HOUR_SECOND);
//			
//			resutlMap.put("AuthInfo","登录成功并已鉴权!");
//			
//		} else {
//			
//			resutlMap.put("AuthInfo","登录成功但鉴权失败!");
//			
//		}
//		
//		jsonResult = JsonCast.collectToString(resutlMap);
//		
//		jsonToPage(jsonResult, response);
//}
	
	/*
	 * 给审批使用接口，增加一条等签署合同信息
	 * 
	 * @author 赵玺翔
	 * 
	 * @param String orgUuid fileName fileName
	 * 
	 * @Time 2017-12-13 01:12
	 * 
	 * @return
	 */
	@RequestMapping("/addContractInfo")
	public void addContractInfo(HttpServletRequest request, HttpServletResponse response, String contractInfo){
		
		if(contractInfo == null || contractInfo.trim().equals("") == true) {
			
			String message = "无法获取到增加合同请求参数！";
			logger.info(message);
			
			Global.myLogger.add("addContractInfo", "false", message);
			
			jsonToPage(JSON.toJSONString(new Tip(message, Global.FAILD)), response);
		}
		
		
		//JSONObject jsonObject = JSONObject.parseObject(contractInfo);
		JSONArray contentList = JSONArray.parseArray(contractInfo);
		if(contentList == null) {
			
			String message = "无法解析增加合同请求参数，可能是协议包格式错误！";
			logger.info(message);
			
			Global.myLogger.add("addContractInfo", "false", message);
			
			jsonToPage(JSON.toJSONString(new Tip(message,Global.FAILD)), response);
		}
		

		int len = contentList.size();
		
		for(int index = 0; index < len; index ++) {
			
			JSONObject object = contentList.getJSONObject(index);
			if(object != null) {
				
				ContractBean contractBean = (ContractBean)JSONObject.toJavaObject(object, ContractBean.class);
				
				// 获取法人架构id
				String orgUuid = contractBean.getOrgUuid();
				String title = contractBean.getTitle();
				String name = "";
				String fileName = contractBean.getFileName();
				String fileURL = contractBean.getFileUrl();
				String returnURL = contractBean.getReturnUrl();
				String dateTime = contractBean.getDateTime();
				
				if(orgUuid == null || orgUuid.trim().equals("") ||
				   title == null || title.trim().equals("") ||
				   fileName == null || fileName.trim().equals("") ||
				   fileURL == null || fileURL.trim().equals("") ||
				   returnURL == null || returnURL.trim().equals("") ||
				   dateTime == null || dateTime.trim().equals("")) {
					
					String message = "无法获取到合同文件和文件地址信息！";
					logger.info(message);
					
					Global.myLogger.add("addContractInfo", "false", message);
					
					jsonToPage(JSON.toJSONString(new Tip(message, Global.FAILD)), response);
				}
				
				
				Tip tip = null;
				
				if(fileURL != null && !fileURL.isEmpty()){
					
					tip=UploadFileUtil.uploadM(fileURL, fileName, Global.CONTRACT_FILE_PATH, request, response);
					
					logger.info("tip:"+tip.toString());
					
					if(tip.getStateCode() == Global.SUCCESS){
						
						logger.info("fileTimeName:"+tip.getPam()+"");
						
						name = tip.getPam() + "";
						
						String message = "文件上传成功，生成日期时间文件名为:" + name;
						
						Global.myLogger.add("addContractInfo", "true", message);
						
						logger.info(message);
									
		 
					} else {
						
						String message = "上传文件失败，程序不能继续运行！";
						
						logger.info(message);
						
						
						Global.myLogger.add("addContractInfo", "false", message);
						
						jsonToPage(JSON.toJSONString(new Tip(message, Global.FAILD)), response);
					}
				}
				
				EContractEntity eContractEntity = new EContractEntity();
				eContractEntity.setTitle(title);
				eContractEntity.setFileName(fileName);
				eContractEntity.setName(name);
				eContractEntity.setState(0);
				eContractEntity.setReturnURL(returnURL);
				
				 SimpleDateFormat dateFormat =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				 Date date = null; 
				 
				 try {
					 date = dateFormat.parse(dateTime);
				 }catch(Exception e) {
					 
					e.printStackTrace();
				 }
				 
				 if(date == null) {
					 
					 String message = "合同有效期("+ dateTime+")传入错误!";
					 
					 logger.info(message);
					 
					 Global.myLogger.add("addContractInfo", "false", message);
					 
					 jsonToPage(JSON.toJSONString(new Tip(message, Global.FAILD)), response);
				 }
				 
				 eContractEntity.setDeadline(date);
				
				logger.info("开始添加数据:"+eContractEntity.toString());
				
				int ecId = 0;
				
				int ok = 0;
				
				ok = contractService.add(eContractEntity);
				
				if(ok > 0) {
					
					ecId = eContractEntity.getId();
					
					logger.info("新增加合同ID:"+ ecId);
					
					//  法人架构id
					String lpId = orgUuid;
					
					// 保存在关联法人表
					if(ecId > 0){
						
						LegalPersonEContractEntity legalPersonEContractEntity = new LegalPersonEContractEntity();
						
						legalPersonEContractEntity.setLpId(lpId);
						legalPersonEContractEntity.setEcId(ecId);
						legalPersonEContractEntity.setHolder(1);
						
						contractService.addRelate(legalPersonEContractEntity);
						
						String message = "审批合同添加成功！";
						
						Global.myLogger.add("addContractInfo", "true", message);
						
						jsonToPage(JSON.toJSONString(new Tip(message, Global.SUCCESS,lpId)), response);
					}
					
				} else {
					
					String message = "审批合同添加失败！";
					
					Global.myLogger.add("addContractInfo", "false", message);
					
					jsonToPage(JSON.toJSONString(new Tip(message, Global.FAILD)), response);
				}
			}
			}
		}
	
}
