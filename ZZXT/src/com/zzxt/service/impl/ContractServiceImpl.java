package com.zzxt.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.Arrays;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzxt.bean.BestSignVo;
import com.zzxt.bean.SignEntity;
import com.zzxt.dao.EContractDao;
import com.zzxt.dao.ESignatureDao;
import com.zzxt.dao.LegalPersonDao;
import com.zzxt.dao.LegalPersonEContractDao;
import com.zzxt.dao.LegalPersonESignatureDao;
//import com.zzxt.dao.Lic_Sha_MiddleDao;
//import com.zzxt.dao.LicenseContractDao;
import com.zzxt.dao.LicenseDao;
import com.zzxt.dao.ShareholderDao;
import com.zzxt.entity.EContractEntity;
import com.zzxt.entity.ESignatureEntity;
import com.zzxt.entity.LegalPersonEntity;
import com.zzxt.entity.LegalPersonEContractEntity;
import com.zzxt.entity.LegalPersonESignatureEntity;
//import com.zzxt.entity.LicenseContractEntity;
import com.zzxt.entity.LicenseEntity;
import com.zzxt.mixedSDK.SignatureManager;
import com.zzxt.service.ContractService;
import com.zzxt.service.LicenseService;
import com.zzxt.servicehttp.AuthServiceHttp;
import com.zzxt.servicehttp.OrganizationServiceHttp;
import com.zzxt.util.FileConvert;
import com.zzxt.util.Global;
import com.zzxt.util.Https;
import com.zzxt.util.IOkHttpUtil;
import com.zzxt.util.ImageConvert;
import com.zzxt.util.PDF2PNGUtil;
import com.zzxt.util.Timestamp;
import com.zzxt.util.Tip;
import com.zzxt.util.UploadFileUtil;

/**
 * 合同业务
 * @author think
 *
 */
@Service
public class ContractServiceImpl implements ContractService {
 
	private Logger logger = Logger.getLogger(ContractServiceImpl.class);

	private SignatureManager signatureManager = SignatureManager.Instance(Global.SHANG_SHANG_SIGN);

	
	@Autowired
	private EContractDao eContractDao;
	
	@Autowired
	private ESignatureDao eSignatureDao;
	
	
	@Autowired
	private LegalPersonEContractDao legalPersonEContractDao;
	
	@Autowired
	private LegalPersonESignatureDao legalPersonESignatureDao;
	
	@Autowired
	LicenseDao licenseDao;
	@Autowired
	ShareholderDao shareholderDao;
	
	//@Autowired
	//Lic_Sha_MiddleDao lic_Sha_MiddleDao;
	
	@Autowired
	private LegalPersonDao legalPersonDao;
	
	@Autowired
	private LicenseService licenseService;
	
	@Autowired
	private OrganizationServiceHttp organizationServiceHttp;
	
	@Autowired
	private  AuthServiceHttp authServiceHttp;
	
	@Override
	public Map<String, Object> doSearch(String orgUuid, String skey, Integer pageIndex) {
		
		// 总记录数
		int max = eContractDao.count(orgUuid);
		
		String a = Global.getConfig(Global.MAX_PAGE_KEY);
		int pageSize = Integer.valueOf(a);
		
		int  allPage = (max + pageSize - 1) / pageSize;
		
		if(pageIndex > allPage) {
			pageIndex = allPage;
		}
		
		if(pageIndex < 1) {
			
			pageIndex = 1;
		}
		
		int startIndex = (pageIndex - 1) * pageSize;
		
		int endIndex = pageIndex * pageSize;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("allPage", allPage);
		resultMap.put("pageSize", pageSize);
				
				
		List<EContractEntity> contractList = eContractDao.search(orgUuid, skey, startIndex, endIndex);
		 
		resultMap.put("dataList", contractList);
		
		return resultMap;
		
	}
	
	@Override
	public Map<String, Object> doSearchState(String orgUuid, int state, Integer pageIndex) {
		
		// 总记录数
		int max = eContractDao.count(orgUuid);
		
		String a = Global.getConfig(Global.MAX_PAGE_KEY);
		int pageSize = Integer.valueOf(a);
		
		int  allPage = (max + pageSize - 1) / pageSize;
		
		if(pageIndex > allPage) {
			pageIndex = allPage;
		}
		
		if(pageIndex < 1) {
			
			pageIndex = 1;
		}
		
		int startIndex = (pageIndex - 1) * pageSize;
		
		int endIndex = pageIndex * pageSize;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("allPage", allPage);
		resultMap.put("pageSize", pageSize);
						
		List<EContractEntity> contractList = eContractDao.searchState(orgUuid, state, startIndex, endIndex);
		
		int len = contractList.size();
		
		logger.info("Search Contract List Size:" + len);
		
		resultMap.put("dataList", contractList);
		
		return resultMap;
		
	}

	
//	@Override
//	public List<EContractEntity> getContractList(String orgUuid) {
//		
//		// 总记录数
//		int max = eContractDao.count(orgUuid);
//		
//		int pageSize = Global.PAGE_SIZE;
//		
//		int  allPage = (max + pageSize - 1) / pageSize;
//		
//		
//		
//		List<EContractEntity> contractList = eContractDao.eContractList(orgUuid);
//		 
//		return contractList;
//	}
//	
	
	@Override
	public Map<String, Object> getContractList(String orgUuid, Integer pageIndex) {
		
		// 总记录数
		int max = eContractDao.count(orgUuid);
		
		String a = Global.getConfig(Global.MAX_PAGE_KEY);
		int pageSize = Integer.valueOf(a);
		
		int  allPage = (max + pageSize - 1) / pageSize;
		
		if(pageIndex > allPage) {
			pageIndex = allPage;
		}
		
		if(pageIndex < 1) {
			
			pageIndex = 1;
		}
		
		int startIndex = (pageIndex - 1) * pageSize;
		
		int endIndex = pageIndex * pageSize;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("allPage", allPage);
		resultMap.put("pageSize", pageSize);
		
		
		List<EContractEntity> contractList = eContractDao.eContractList(orgUuid, startIndex, endIndex);
		
		resultMap.put("dataList", contractList);
		
		return resultMap;
	}

	@Override
	public EContractEntity eContract(String ecId) {
		
		EContractEntity eContractEntity = eContractDao.eContract(ecId);
		
		return eContractEntity;
	}
 
	
	@Override
	public Integer add(EContractEntity contractEntity) {
		return eContractDao.add(contractEntity);

	}
	
	@Override
	public Integer edit(EContractEntity contractEntity) {
		return eContractDao.edit(contractEntity);
	}
	
	
	
	
	@Override
	public Integer addRelate(LegalPersonEContractEntity legalPersonEContractEntity) {

		return legalPersonEContractDao.add(legalPersonEContractEntity);
	}
	@Override 
	public void delRelate(String id)
	{
		legalPersonEContractDao.del(id);
	}
	

	@Override
	public Integer delContract(String cuuid) {
		// TODO Auto-generated method stub
		
		legalPersonEContractDao.del(cuuid);
		
		return eContractDao.del(cuuid);
		

	}
	
	@Override
	public LegalPersonEContractEntity getLegalPersonEContractEntity(String id) {
		
		return legalPersonEContractDao.getLegalPersonEContractEntity(id);
	}
	

	
	@Override
	public List<Map<String, String>> getSignerList(String ecId){
		
		// 1.查询签署者
		List<Map<String, String>> signers = new ArrayList<>();
		
		// 获取签署者列表
		List<LegalPersonEContractEntity> orgUuids = legalPersonEContractDao.getSignerList(ecId);
		
		logger.info("ecId:" + ecId);
				
		for(LegalPersonEContractEntity oid : orgUuids) {
			
			String id = String.valueOf(oid.getId());
			String cId = String.valueOf(oid.getEcId());
			String lpId = oid.getLpId();
			
			String state = String.valueOf(oid.getState());
			
			
			String cName = "";
			
			// LicenseEntity licenseInfo = licenseDao.selectLicInfo(oid.getLpId());
			
			LicenseEntity licenseInfo = licenseDao.licenseInfo(lpId);
			if(licenseInfo != null) {
			   cName = licenseInfo.getcName(); if(cName == null) cName = "";
			}
			
			logger.info("LegalPersonEContractEntity->Info(id):"+ id);
			logger.info("LegalPersonEContractEntity->Info(ecId):"+ cId);
			logger.info("LegalPersonEContractEntity->Info(lpId):"+ lpId);
			logger.info("LegalPersonEContractEntity->Info(state):"+ state);
			logger.info("licenseInfo->Info(cName):"+ cName);
			
			Map<String, String> signer = new HashMap<String, String>();
			
			signer.put("id", id);
			signer.put("ecId", cId);
			signer.put("orgUuid",lpId);
			signer.put("state",state);
			signer.put("cName",cName);
			
			signers.add(signer);			 
		}
			
		return signers;
	}

	
	
	
	@Override
	public List<Map<String, String>> getSignerList(String ecId, String orgUuid){
		
		// 1.查询签署者
		List<Map<String, String>> signers = new ArrayList<>();
		
		// 获取签署者列表
		List<LegalPersonEContractEntity> orgUuids = legalPersonEContractDao.orgIdList(ecId, orgUuid);
		
		logger.info("ecId:" + ecId + "\n orgUuid:" + orgUuid);
				
		for(LegalPersonEContractEntity oid : orgUuids) {
			
			String id = String.valueOf(oid.getId());
			String cId = String.valueOf(oid.getEcId());
			String lpId = oid.getLpId();
			
			String state = String.valueOf(oid.getState());
			
			
			String cName = "";
			LicenseEntity licenseInfo = licenseDao.licenseInfo(lpId);
			if(licenseInfo != null) {
			   cName = licenseInfo.getcName(); if(cName == null) cName = "";
			}
				
			
			logger.info("LegalPersonEContractEntity->Info(id):"+ id);
			logger.info("LegalPersonEContractEntity->Info(ecId):"+ cId);
			logger.info("LegalPersonEContractEntity->Info(lpId):"+ lpId);
			logger.info("LegalPersonEContractEntity->Info(state):"+ state);
			logger.info("licenseInfo->Info(cName):"+ cName);
			
			Map<String, String> signer = new HashMap<String, String>();
			
			signer.put("id", id);
			signer.put("ecId", cId);
			signer.put("orgUuid",lpId);
			signer.put("state",state);
			signer.put("cName",cName);
			
			signers.add(signer);			 
		}
			
		return signers;
	}

	
	@Override
	public Map<String, Object> showContract(String ecId, String orgUuid) {
		
		EContractEntity eContractEntity=  this.eContract(ecId);
		if(eContractEntity == null) {
			
			logger.info("showContract EContractEntity 为空");
			
			return null;
		}
		
		// pdf文件名
		String name = eContractEntity.getName();
		
		String fileName = eContractEntity.getFileName();
		
		logger.info("showContract->eContractEntity.name: " + name);

		if(name == null || name.trim().equals("")) {
			
			return null;
		}
		
		
		// 获取已经签好的合同文件
		String contract = eContractEntity.getContract();
		
		logger.info("showContract->eContractEntity.contract: " + contract);

	
		// 获取合同总签署状态，需要处理“拒绝/重签”情况，合同有五种状态：未签署、签署中、已完成 
		int state = eContractEntity.getState();
		
		String dir = Global.CONTRACT_FILE_PATH;
	    String path = Global.getBasePath() + dir + "/";
	    
	    if(contract != null && contract.trim().equals("") == false && state != Global.UNSIGN) {
	    	
	    		name = contract;
	    }
	    
	    logger.info("showContract->eContractEntity.name: " + name);
	    
	    // 整体路径
	    String filePath = path + name;
	    
		logger.info("showContract->filePath: " + filePath);

//	    if(name != null && name.trim().equals("") == false) {
//	    	
//	    }
//	    // 文件名称
//		String frontName = name.substring(0, name.lastIndexOf("."));
	
		logger.info("整体路径："+filePath);
	
		String imagePath = path;
		logger.info("图片路径："+ imagePath);
		
		Map<String, Object> map = PDF2PNGUtil.pdf2Image(new File(filePath), imagePath, 72);
		
		logger.info("PDF2PNG RESULT："+ map);
		
		int code = Integer.parseInt(map.get("code").toString());
		
		String message = map.get("message").toString();
		
		String imageInfo = "";
		
		List<String> imageList = null;
		if(code == Global.ICE_OK) {
			
			imageList = (List<String>)map.get("imgPath");
			
			imageInfo = this.getReturnInfo(Global.ICE_OK, "合同PDF文件转换为图片成功!");
			
			logger.info("图片文件名："+imageList);
		} else {
			
			imageInfo = this.getReturnInfo(Global.ICE_UNKNOW, "合同文件(" + fileName + ")已经丢失, 请重新上传!");
		}
		
		// 1.查询签署者
		List<Map<String, String>> signers = this.getSignerList(ecId, orgUuid);
		
		// 获取签章列表
		List<ESignatureEntity> signatureList = eSignatureDao.eSignatureAllList(orgUuid);
		
		Map<String, Object> resultMap = new HashMap<>();
		
		if(imageList == null) {
			
			imageList = new ArrayList<String>();
		}
		
		resultMap.put("imgList", imageList);
		resultMap.put("seal", signatureList);
		resultMap.put("signers", signers);
		
		if(eContractEntity.getState() != Global.FINISH)
		{
			String resultInfo  = this.initContract(ecId, orgUuid);
			
			resultMap.put("initCon", resultInfo);
		}
		
		resultMap.put("PdfToImageInfo", imageInfo);
		
		return resultMap;
	}
	

	@Override
	public String userAsyncApplyCertSubmit(String orgUuid, int type) {

		String resultString = "";
		
		switch(type) {
		
			case Global.SHANG_SHANG_SIGN:
			{
				resultString = this.shangshangSignCertSubmit(orgUuid);
			}
			break;
			
			case Global.ZHONG_SHUI_SIGN:
			{
				resultString = this.zhongShuiCertSubmit(orgUuid);
			}
			break;
		}
			
		return resultString;
	}
	
	
	
		/**
		 * 上上签申请ca证书
		 */
		public String userAsyncApplyCertSubmit(String orgUuid) {

			return this.shangshangSignCertSubmit(orgUuid);
		}
		
		
		
		/**
		 * 上上签申请ca证书
		 */
		public String shangshangSignCertSubmit(String orgUuid) {
			Map<String, Object> map = new HashMap<>();
			// 获取证照信息
			
			LicenseEntity licenseInfo = licenseDao.licenseInfo(orgUuid);
			if (licenseInfo == null) {

				String message = "CA 认证失败, 没有找到工商信息(orgUuid:" + orgUuid + ")";
				
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				Global.myLogger.add("applyCert", "false", message);

				return jsonString;
			}

			// int id = licenseEntity.getLegalPerson_id();
			
			int lid = licenseInfo.getId();
			
			// 获取法人代表
			// LegalPersonEntity legalPerson = legalPersonDao.getLegalPersonById(id);
			
			LegalPersonEntity legalPersonEntity  = licenseService.getLegalPersonInfo(lid);
			if(legalPersonEntity == null) {
				
				String message = "CA 认证失败, 没有找到法人信息(orgUuid:" + orgUuid + ")";
				
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				Global.myLogger.add("applyCert", "false", message);

				return jsonString;
			}
			
			// 获取用户
			String name = legalPersonEntity.getLegalPerson();
			
			// 获取身份证
			String identity = legalPersonEntity.getIdentity();
			
			// 获取电话 
			String phone = legalPersonEntity.getPhone();
			

			JSONObject userInfo = signatureManager.userBaseInfo(orgUuid);
			
			String info = "CA认证过程用户详细信息：" + userInfo.toJSONString();
			
			String message = "";
			
			// 获取到了不用注册
			if (userInfo.getString("account") != null) {
				
				logger.info("userInfo:" + userInfo);
				
				map.put("userInfo", userInfo);
				
				message = info + "=>已经注册过，不需要再注册";
				
				Global.myLogger.add("applyCert", "true", message);
				
			} else {
				Boolean flag = signatureManager.userReg(orgUuid, null, phone, name, Global.SIGN_TYPE_COMPANY);
				if (flag) {
					// 注册成功
					userInfo = signatureManager.userBaseInfo(orgUuid);
					logger.info("userInfo:" + userInfo);
					
					map.put("userInfo", userInfo);
					
					String content = JSON.toJSONString(map);
					
					String jsonString = Global.getProtocol(content);
					
					message = info + "=>注册成功!";
					
					Global.myLogger.add("applyCert", "true", message);
					
					return jsonString;
					
				} else {
					// 注册失败
					
					 message = info + "=>用户注册失败";
					 
					 Global.myLogger.add("applyCert", "false", message);
					
					String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
					
					return jsonString;
				}
			}
			
			 message = info + "=>用户注册成功，进行设置企业法人证件信息!";
			
			Global.myLogger.add("applyCert", "true", message);
			
			String account = orgUuid;
			String regNum = licenseInfo.getRegNum();
			String cName = licenseInfo.getcName(); if(cName == null || cName.trim().equals("") == true) {cName = "未知公司";}
			String lPerson = legalPersonEntity.getLegalPerson();
			String iId = legalPersonEntity.getIdentity();
			String pIdType = legalPersonEntity.getIdentityType();
			String pMobile = legalPersonEntity.getPhone();
			
			String logs = "Account:" + account + "\n regNum:" + regNum + "\n cName:" + cName + "\n lPerson:" + lPerson + "\n iId:" + iId + "\n pIdType:" + pIdType + "\n pMobile:" + pMobile;
			
			logger.info(logs);
			
			// 2.设置证件信息
			boolean flag = signatureManager.userSetEnterpriseCredential(account, regNum, regNum, regNum, cName, lPerson, iId, pIdType, null, null, null, pMobile, null, null, null);
			
			if (flag) {
			
				map.put("Credential", "企业法人证件信息设置成功");
				
				message = info + "=>企业法人证件信息设置成功";
				
				Global.myLogger.add("applyCert", "true", message);

			} else {
				
				message = info + "=>企业法人证件信息设置失败";
				
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				Global.myLogger.add("applyCert", "false", message);
				
				return jsonString;
			}

			// 3异步申请数ca证书
			JSONObject jsonObject = signatureManager.userAsyncApplyCertSubmit(orgUuid, "ZJCA");

			if (jsonObject != null) {
				
				String taskId = jsonObject.getString("taskId");
				
				message = info + "=>CA证书申请成功,获取到 taskId:" + taskId;
				
				Global.myLogger.add("applyCert", "true", message);
				
				map.put("taskId", taskId);
			
			} else {
				
				 message = info + "=>没有获取到taskId！";
				
				Global.myLogger.add("applyCert", "false", message);
				
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				return jsonString;
			}
			
			String content = JSON.toJSONString(map);
			
			String jsonString = Global.getProtocol(content);
			
			return jsonString;
		}
		
		
		/**
		 * 中税签申请ca证书
		 */
		public String zhongShuiCertSubmit(String orgUuid) {
			
			LicenseEntity licenseInfo = licenseDao.licenseInfo(orgUuid);
			if (licenseInfo == null) {

				String message = "CA 认证失败, 没有找到工商信息(orgUuid:" + orgUuid + ")";
				
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				Global.myLogger.add("applyCert", "false", message);

				return jsonString;
			}

			// int id = licenseEntity.getLegalPerson_id();
			
			int lid = licenseInfo.getId();
			
			// 获取法人代表
			// LegalPersonEntity legalPerson = legalPersonDao.getLegalPersonById(id);
			
			LegalPersonEntity legalPersonEntity  = licenseService.getLegalPersonInfo(lid);
			if(legalPersonEntity == null) {
				
				String message = "CA 认证失败, 没有找到法人信息(orgUuid:" + orgUuid + ")";
				
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				Global.myLogger.add("applyCert", "false", message);

				return jsonString;
			}
			
			// 获取用户
			String name = legalPersonEntity.getLegalPerson();
			
			// 获取身份证
			String identity = legalPersonEntity.getIdentity();
			
			// 获取电话 
			String phone = legalPersonEntity.getPhone();
			
			String province = licenseInfo.getProvince();
			
			String city = licenseInfo.getCity();
			
			String address = licenseInfo.getAddress();
			
			
			
			String account = orgUuid;
			String regNum = licenseInfo.getRegNum();
			String cName = licenseInfo.getcName(); if(cName == null || cName.trim().equals("") == true) {cName = "未知公司";}
			String lPerson = legalPersonEntity.getLegalPerson();
			String iId = legalPersonEntity.getIdentity();
			String pIdType = legalPersonEntity.getIdentityType();
			
			String logs = "Account:" + account + "\n regNum:" + regNum + "\n cName:" + cName + "\n lPerson:" + lPerson + "\n iId:" + iId + "\n pIdType:" + pIdType + "\n phone:" + phone;
			
			logger.info(logs);
			
			
			Map<String, String> paramMap = new HashMap<String, String> ();
			
			paramMap.put("etpName", cName);
			paramMap.put("efpLegalName", name);
			paramMap.put("etpAddr", address);
			paramMap.put("etpPhone", phone);
			paramMap.put("etpProvince", province);
			paramMap.put("etpCity", city);
			paramMap.put("etpTaxId", regNum);
			paramMap.put("agentName", name);
			paramMap.put("agentPhone", phone);
			paramMap.put("agentIdCard", identity);

			String content = this.authServiceHttp.getZhongShuiCaCert(paramMap);
			
			String jsonString = Global.getProtocol(content);
			
			return jsonString;
		}
		
		
		

		/**
		 * 查询证书申请情况
		 */
		public String userAsyncApplyCertStatus(String account, String taskId) {
			
			JSONObject jsonObject = signatureManager.userAsyncApplyCertStatus(account, taskId);
			
			if (jsonObject == null) {
			
				String message = "查询数字证书失败！";
				
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				return jsonString;
				
			} else {
				String status = jsonObject.getString("status");
				if ("5".equals(status)) {
				
					String message = "数字证书申请成功！";
					String jsonString = Global.getProtocol(Global.ICE_OK, message, false);
					
					return jsonString;
					
				} else {
					
					String message = "数字证书申请失败！";
					String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
					
					return jsonString;
				}
			}

		}

		/**
		 * 设置签名
		 * 
		 * @param bestSignVo
		 * @return
		 */
		public Map<String, Object> setUserSignatureImage(BestSignVo bestSignVo, HttpServletRequest httpRequest,
				HttpServletResponse response) {
			
			Map<String, Object> map = new HashMap<>();
			
			// 3.设置签名/印章
			byte[] signatureImageData = ImageConvert.httpImage2byte(bestSignVo.getLicPath());
			
			boolean flag1 = false;
			
			if (signatureImageData != null) {
			
				flag1 = signatureManager.setUserSignatureImage(bestSignVo.getOrgUuid(), 2, signatureImageData);
			
			} else {
			
				map.put("sealPicture", "没有获取到签章图片");
			}
			
			if (flag1) {
			
				// 设置起签名成功
				// 3.查询印章
				
				byte[] userSignatureImageData = signatureManager.getUserSignatureImageData(bestSignVo.getOrgUuid(), 2);
				
				map.put("userSignatureImageData", userSignatureImageData);
				
				Map<String, String> map2 = UploadFileUtil.getFilePath(httpRequest, response);
				
				int i = ImageConvert.byte2image(userSignatureImageData, map2.get("filePath"));
				
				if (i == 0) {
				
					String url = map2.get("url");
					
					map.put("sealPicture", url);
					
					map.put("签名图片保存成功！", Global.SUCCESS);
				
				} else {
				
					map.put("获取签名图片保存失败!", Global.FAILD);
				
				}
			} else {
				
				map.put("设置印章失败!", Global.FAILD);
			}
			
			return map;
		}
		
		
		/**
		 * 设置签名
		 * 
		 * @param sid
		 * @return Tip
		 */
		@Override
		public String setUserSignatureImage(String sid, String ecId) {
			
			ESignatureEntity signatureEntity = eSignatureDao.eSignature(sid);
			
			LegalPersonESignatureEntity  legalPersonESignatureEntity = legalPersonESignatureDao.getLegalPersonESignatureEntity(sid);
			if(signatureEntity == null || legalPersonESignatureEntity == null) {
			
				String message = "设置印章操作失败，没有获取印章数据！";
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				return jsonString;
			}
			
			String name = signatureEntity.getName();
			String file = Global.SIGNATURE_FILE_PATH + "/" +  name;
			final String filePath = Global.getBasePath() + file;
			
			logger.info("印章图片路径:" + filePath);
			
			String account = legalPersonESignatureEntity.getLpId();
			
			if(account == null || account.trim().equals("") == true) {
				
				String message = "置印章操作失败：没有找到帐号信息!";
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				return jsonString;
				
			}
			
			byte[] signatureImageData = ImageConvert.readFile(filePath);		
			if(signatureImageData == null) {
				
				String message = "置印章操作失败：印章图片二进制数据获取失败，可能是没有找到印章图片文件!";
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				return jsonString;
			}
			
			boolean flag = signatureManager.setUserSignatureImage(account, Global.SIGN_TYPE_COMPANY, signatureImageData);
			if (flag) {
				
				String orgUuid = "";
				//设置印章成功，将合同状态设置为签署中，签署人同
				EContractEntity eContractEntity = eContractDao.eContract(ecId);
				if(eContractEntity != null) {
					
					orgUuid = eContractEntity.getOrgUuid();
					// 只要出现设置电子签章操作，就记为签署中操作
					eContractEntity.setState(Global.SIGNING);
					eContractDao.edit(eContractEntity);
					
					logger.info("当前合同id:" + ecId + " 名称:" + eContractEntity.getTitle() + " 签署者：" + orgUuid + " 合同状态设置为签署中操作 state="+ Global.SIGNING);
				}
				
				LegalPersonEContractEntity legalPersonEContractEntity = new LegalPersonEContractEntity();
				if(legalPersonEContractEntity != null) {
			
					legalPersonEContractEntity.setEcId(Integer.valueOf(ecId));
					legalPersonEContractEntity.setLpId(orgUuid);
					legalPersonEContractEntity.setState(Global.SIGNER_SIGNING);
			
					legalPersonEContractDao.editState(legalPersonEContractEntity);
					
					logger.info("当前合同id:" + ecId + " 名称:" + eContractEntity.getTitle() + " 签署者：" + orgUuid + " 签署者状态设置为签署中操作 state="+ Global.SIGNING);

				}

				String message = "设置印章成功!";
				String jsonString = Global.getProtocol(Global.ICE_OK, message, false);
				
				return jsonString;

			} else {
				
				String message = "设置印章失败!";
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				return jsonString;
			}
		}

		
		
		

		/**
		 * 设置签名
		 * 
		 * @param sid
		 * @return Tip
		 */
		@Override
		public String setUserSignatureImage(String sid) {
			
			ESignatureEntity signatureEntity = eSignatureDao.eSignature(sid);
			
			LegalPersonESignatureEntity  legalPersonESignatureEntity = legalPersonESignatureDao.getLegalPersonESignatureEntity(sid);
			
			if(signatureEntity == null || legalPersonESignatureEntity == null) {
			
				String message = "设置印章操作失败，没有获取印章数据！";
				
				Global.myLogger.add("setSignImage", "false", message);
				
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				return jsonString;
			}
			
			String name = signatureEntity.getName();
			
			String file = Global.SIGNATURE_FILE_PATH + "/" +  name;
			
			final String filePath = Global.getBasePath() + file;
			
			logger.info("印章图片路径:" + filePath);
			
			String account = legalPersonESignatureEntity.getLpId();
			
			String title = signatureEntity.getTitle();
			
			String fileName = signatureEntity.getFileName();
			
			String orgUuid = signatureEntity.getOrgUuid();
			
			String info = "印章详细信息\r\norgUuid:" + orgUuid + "\r\ntitle:" + title + "\r\nfileName:" + fileName + "\r\nfilePath:" + filePath + "\r\naccount:" + account;
			
			String message = "";
			if(account == null || account.trim().equals("") == true) {
				
			     message = info +"\r\n置印章操作失败：没有找到帐号信息!";
				
				Global.myLogger.add("setSignImage", "false", message);
				
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				return jsonString;
				
			}
			
			byte[] signatureImageData = ImageConvert.readFile(filePath);
			if(signatureImageData == null) {
				
			    message = info + "\r\n置印章操作失败：印章图片二进制数据获取失败，可能是没有找到印章图片文件!";
				
			    Global.myLogger.add("setSignImage", "false", message);
			    
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				return jsonString;
			}
			
			boolean flag = signatureManager.setUserSignatureImage(account, 2, signatureImageData);
			if (flag) {
				
				 message = info + "\r\n设置印章成功！";
				
				 Global.myLogger.add("setSignImage", "true", message);
				 
				String jsonString = Global.getProtocol(Global.ICE_OK, message, false);
				
				return jsonString;
				
			} else {
				
			    message = info + "\r\n设置印章失败！";
				
			    Global.myLogger.add("setSignImage", "false", message);
			    
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				return jsonString;
			}
		}

		
		
		
		
		/**
		 * 获取签名图片
		 * 
		 * @param account
		 * @param userType
		 * @return
		 */
		public byte[] getUserSignatureImageData(String account, int userType) {
			return signatureManager.getUserSignatureImageData(account, userType);
		}

		/**
		 * 设置企业证件信息
		 * 
		 * @param bestSignVo
		 * @return
		 */
		public boolean userSetEnterpriseCredential(BestSignVo bestSignVo) {
			return signatureManager.userSetEnterpriseCredential(bestSignVo.getOrgUuid(), bestSignVo.getRegNum(),
					bestSignVo.getRegNum(), bestSignVo.getOrgCode(), bestSignVo.getcName(), bestSignVo.getLegalPerson(),
					bestSignVo.getLegalPersonIdentity(), bestSignVo.getLegalPersonIdentityType(), null, null, null,
					bestSignVo.getContactMobile(), null, null, null);
		}

		/**
		 * 查询证书编号
		 * 
		 */
		@Override
		public String userGetCert(String account) {

			return signatureManager.userGetCert(account, "ZJCA");
		}

		// 合同相关接口

//		/**
//		 * 根据合同uuid查询所有的章和签署者
//		 */
//		public Map<String, Object> getSealAndSigner(String cuuid, String orgUuid) {
//			
//			List<LicenseContractEntity> lcList = licenseContractDao.getLiceseIdsByCuuid(cuuid);
//			// 1.查询签署者
//			List<OrgBean> signers = new ArrayList<>();
//			for (LicenseContractEntity lcEntity : lcList) {
//				// 得到证照信息
//				LicenseEntity licenseEntity = licenseDao.getLic_ShaByLicenseId(lcEntity.getLicense_id());
//
//				// 根据orgUuid获取签署者
//				// 查询法人架构详细信息
//				Map<String, String> param = new HashMap<>();
//				param.put("orgUuid", licenseEntity.getOrgUuid());
//				String org = organizationServiceHttp.getOrg(param);
//				if (org != null && !"".equals(org)) {
//					OrgBean orgBean = JSONObject.parseObject(org, OrgBean.class);
//					signers.add(orgBean);
//
//				}
//			}
//			// 2.获取所有的章
//
//			//List<ESignatureEntity> signatureList = signatureDao.findSignatureList(orgUuid);
//			
//			List<ESignatureEntity> signatureList = eSignatureDao.eSignatureList(orgUuid);
//			Map<String, Object> map = new HashMap<>();
//			map.put("seal", signatureList);
//			map.put("signers", signers);
//			
//			Tip tip = initContract(cuuid);
//			map.put("initCon", tip);
//			
//			// 根据cuuid
//			return map;
//		}

		/**
		 * 创建合同
		 * 
		 * @param cuuid
		 *            合同id
		 * @return
		 */
		public String contractCreate(String ecId) {
			// 获取合同实体
			EContractEntity contractEntity = eContractDao.eContract(ecId);

			String account = contractEntity.getOrgUuid();
			
			String name = contractEntity.getName();
			String file = Global.CONTRACT_FILE_PATH + "/" +  name;
			
			final String filePath = Global.getBasePath() + file;
			
			logger.info("文件保存路径："+filePath);
			
			byte[] pdfData = ImageConvert.readFile(filePath);		
			// 1.创建合同节点,返回合同文件编号
			String fid = signatureManager.contractCreateFileNode(account, pdfData);
			
			// 2.创建合同
			signatureManager.contractCreate(account, fid, null, contractEntity.getName(), contractEntity.getComment());
			
			return ecId;

		}
		
		
		private String getReturnInfo (Integer code, String message) {
			
			String info = code + ":" + message;
			
			logger.info(info);
			
			return info;
		}
		
		
		@Override
		public String initContract(String ecid, String orgUuid) {
			
			//获取合同实体
			EContractEntity eContractEntity = eContractDao.eContract(ecid);
			if(eContractEntity == null) {
				
				return this.getReturnInfo(Global.ICE_UNKNOW, "合同ID为 " + ecid + " 信息没有找到, 不能执行初始合同操作!");
			}
			
			String title = eContractEntity.getFileName();
			
			String contractId = eContractEntity.getContractId();
			String account = orgUuid;
			
			//如果签署的合同不存在则创建合同
			if (contractId == null || signatureManager.contractDownload(contractId) == null) {
				
			
				String name = eContractEntity.getName();
				String file = Global.CONTRACT_FILE_PATH + "/" +  name;
				
				final String path = Global.getBasePath() + file;
				
				logger.info("文件保存路径："+path);
	 			
				byte[] pdfData = ImageConvert.readFile(path);
				if(pdfData == null) {
					
					return this.getReturnInfo(Global.ICE_UNKNOW, "合同名称为 " + title + " 文件可能已经丢失，不能继续同操作!");
				}
				
				//1.创建合同节点,返回合同文件编号
				String info = signatureManager.contractCreateFileNode(account , pdfData);
				
				String list[] = info.split(":"); 
				
				int code = Integer.parseInt(list[0]);
				if(code == Global.ICE_UNKNOW) {
					
					String error = list[1];
					return this.getReturnInfo(Global.ICE_UNKNOW, "合同名称为 " + title + " 执行初始化操作失败, 云签平台返回（"+error+")!");
				}
				
				String fid = list[1];
				
				
				eContractEntity.setfId(fid);
				
				eContractEntity.setState(1);
				
				//2.创建合同
				JSONObject jsonObject = signatureManager.contractCreate(account, fid, Long.toString(eContractEntity.getDeadline().getTime()/1000), eContractEntity.getTitle(), eContractEntity.getComment());
				
				contractId = jsonObject.getString("contractId");
				
				if (contractId == null) {

					return this.getReturnInfo(Global.ICE_UNKNOW, "合同名称为 " + title + " 创建合同失败：合同签署ID为空");

				}
				eContractEntity.setContractId(contractId);
				eContractDao.edit(eContractEntity);
				
				//3.将当前法人架构加入签署者
				try {
					
					signatureManager.contractAddSigner(contractId, account);
					
				} catch (Exception e) {
					e.printStackTrace();
					
					return this.getReturnInfo(Global.ICE_UNKNOW, "合同名称为 " + title + " 增加签署者操作失败");
				}
			}
			
			return this.getReturnInfo(Global.ICE_OK, "合同 "+ title +" 创建成功!");
		}

		
//		
//		@Override
//		public Tip initContract(String ecid, String orgUuid) {
//			
//			//获取合同实体
//			EContractEntity eContractEntity = eContractDao.eContract(ecid);
//			
//			String contractId = eContractEntity.getContractId();
//			String account = orgUuid;
//			
//			//如果签署的合同不存在则创建合同
//			if (contractId == null || signatureManager.contractDownload(contractId) == null) {
//				
//			
//				String name = eContractEntity.getName();
//				String file = Global.CONTRACT_FILE_PATH + "/" +  name;
//				
//				final String path = Global.getBasePath() + file;
//				
//				logger.info("文件保存路径："+path);
//	 			
//				byte[] pdfData = ImageConvert.readFile(path);
//				
//				//1.创建合同节点,返回合同文件编号
//				String fid = signatureManager.contractCreateFileNode(account , pdfData);
//				if (fid ==null) {
//					return new Tip("创建合同节点失败！", Global.FAILD);
//				}
//				
//				
//				eContractEntity.setfId(fid);
//				
//				eContractEntity.setState(1);
//				//2.创建合同
//				JSONObject jsonObject = signatureManager.contractCreate(account, fid, Long.toString(eContractEntity.getDeadline().getTime()/1000), eContractEntity.getTitle(), eContractEntity.getComment());
//				contractId = jsonObject.getString("contractId");
//				if (contractId ==null) {
//					return new Tip("创建合同失败！", Global.FAILD);
//				}
//				eContractEntity.setContractId(contractId);
//				eContractDao.edit(eContractEntity);
//				
//				//3.将当前法人架构加入签署者
//				try {
//					signatureManager.contractAddSigner(contractId, account);
//				} catch (Exception e) {
//					e.printStackTrace();
//					return new Tip("添加签署者失败！",Global.SUCCESS);
//				}
//			}
//			
//			return new Tip("合同初始化成功！",Global.SUCCESS);
//		}
//		
//		
		
//		
//		@Override
//		public String initContract(String ecid, String orgUuid) {
//			
//			//获取合同实体
//			EContractEntity eContractEntity = eContractDao.eContract(ecid);
//			
//			String contractId = eContractEntity.getContractId();
//			String account = orgUuid;
//			
//			//如果签署的合同不存在则创建合同
//			if (contractId == null || signatureManager.contractDownload(contractId) == null) {
//				
//			
//				String name = eContractEntity.getName();
//				String file = Global.CONTRACT_FILE_PATH + "/" +  name;
//				
//				final String path = Global.getBasePath() + file;
//				
//				logger.info("文件保存路径："+path);
//	 			
//				byte[] pdfData = ImageConvert.readFile(path);
//				
//				//1.创建合同节点,返回合同文件编号
//				String fid = signatureManager.contractCreateFileNode(account , pdfData);
//				if (fid ==null) {
//					
//					//return new Tip("创建合同节点失败！", Global.FAILD);
//					
//					String message = "创建合同节点失败！";
//					String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
//					
//					return jsonString;
//					
//				}
//				
//				
//				eContractEntity.setfId(fid);
//				
//				eContractEntity.setState(1);
//				//2.创建合同
//				JSONObject jsonObject = signatureManager.contractCreate(account, fid, Long.toString(eContractEntity.getDeadline().getTime()/1000), eContractEntity.getTitle(), eContractEntity.getComment());
//				contractId = jsonObject.getString("contractId");
//				if (contractId ==null) {
//					
//					//return new Tip("创建合同失败！", Global.FAILD);
//				
//					String message = "创建合同失败！";
//					String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
//					
//					return jsonString;
//					
//				}
//				
//				
//				eContractEntity.setContractId(contractId);
//				eContractDao.edit(eContractEntity);
//				
//				//3.将当前法人架构加入签署者
//				try {
//					signatureManager.contractAddSigner(contractId, account);
//				} catch (Exception e) {
//					e.printStackTrace();
//					
//					
//					String message = "添加签署者失败！";
//					String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
//					
//					return jsonString;
//					
//					// return new Tip("添加签署者失败！",Global.SUCCESS);
//				}
//			}
//			
//			String message = "合同初始化成功！";
//			String jsonString = Global.getProtocol(Global.ICE_OK, message, false);
//
//			return jsonString;
//			
//			// return new Tip("合同初始化成功！",Global.SUCCESS);
//		}
//		
		
		
		@Override
		public String initContract(String ecid) {
			
			//获取合同实体
			EContractEntity eContractEntity = eContractDao.eContract(ecid);
			
			String contractId = eContractEntity.getContractId();
			String account = eContractEntity.getOrgUuid();
			
			String info = "";
			//如果签署的合同不存在则创建合同
			if (contractId == null || signatureManager.contractDownload(contractId) == null) {
				
			
				String title = eContractEntity.getTitle();
				String name = eContractEntity.getName();
				String fileName = eContractEntity.getFileName();
				String file = Global.CONTRACT_FILE_PATH + "/" +  name;
				
				final String path = Global.getBasePath() + file;
				
				logger.info("文件保存路径："+path);
	 			
				byte[] pdfData = ImageConvert.readFile(path);
				
				info = "合同文件信息" + "\r\naccount" + account +  "\r\n标题:" + title + "\r\n文件名称:" + fileName + " 文件路径:" + path;
				
				//1.创建合同节点,返回合同文件编号
				String fid = signatureManager.contractCreateFileNode(account , pdfData);
				if (fid == null) {
				
					String message = info + "\r\n创建合同节点失败！";
					
					String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
					
					Global.myLogger.add("initContract", "false", message);
					
					return jsonString;
				}
				eContractEntity.setfId(fid);
				
				eContractEntity.setState(1);
				//2.创建合同
				JSONObject jsonObject = signatureManager.contractCreate(account, fid, Long.toString(eContractEntity.getDeadline().getTime()/1000), eContractEntity.getTitle(), eContractEntity.getComment());
				contractId = jsonObject.getString("contractId");
				if (contractId ==null) {
				
					String message = info + "\r\n创建合同失败！";
					
					String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
					
					Global.myLogger.add("initContract", "false", message);
					
					return jsonString;
				}
				
				eContractEntity.setContractId(contractId);
				eContractDao.edit(eContractEntity);
				
				//3.将当前法人架构加入签署者
				try {
					
					signatureManager.contractAddSigner(contractId, account);
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
					String message = info + "\r\n添加签署者失败！";
					
					Global.myLogger.add("initContract", "false", message);
					
					String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
					
					return jsonString;
				}
			}
			
			String message = info + "\r\n合同初始化成功！";
			
			Global.myLogger.add("initContract", "true", message);
			
			String jsonString = Global.getProtocol(Global.ICE_OK, message, false);

			return jsonString;
		}
		
		public int reSigning(String id, String ecId, String orgUuid) {
			
			EContractEntity eContractEntity = this.eContract(ecId);
			if(eContractEntity != null) {
				
				// 重签直接设置合同状态为 UNSIGN
				eContractEntity.setState(Global.UNSIGN);
				
				eContractDao.edit(eContractEntity);
			}

			
			LegalPersonEContractEntity legalPersonEContractEntity = this.getLegalPersonEContractEntity(id);
			if(legalPersonEContractEntity != null) {
				
				legalPersonEContractEntity.setState(Global.SIGNER_RESIGN);
				
				legalPersonEContractEntity.setEcId(Integer.valueOf(ecId));
				legalPersonEContractEntity.setLpId(orgUuid);
				
				legalPersonEContractDao.editState(legalPersonEContractEntity);
			}
			
			
			return Global.UNSIGN;
		}


		
		
		/**
		 * 合同初始化
		 */
//		@Override
//		public Tip initContract(String ecid) {
//			//获取合同实体
//			EContractEntity eContractEntity = eContractDao.eContract(ecid);
//			
//			String contractId = eContractEntity.getContractId();
//			String account = eContractEntity.getOrgUuid();
//			
//			//如果签署的合同不存在则创建合同
//			if (contractId==null||signatureManager.contractDownload(contractId) == null) {
//				
//				String name = eContractEntity.getName();
//				String file = Global.CONTRACT_FILE_PATH + "/" +  name;
//				
//				final String path = Global.getBasePath() + file;
//				
//				logger.info("文件保存路径："+path);
//	 			
//				byte[] pdfData = ImageConvert.readFile(path);		
//				
//				//1.创建合同节点,返回合同文件编号
//				String fid = signatureManager.contractCreateFileNode(account , pdfData);
//				if (fid ==null) {
//					return new Tip("创建合同节点失败！", Global.FAILD);
//				}
//				eContractEntity.setfId(fid);
//				eContractEntity.setState(1);
//				//2.创建合同
//				JSONObject jsonObject = signatureManager.contractCreate(account, fid, Long.toString(eContractEntity.getDeadline().getTime()/1000), eContractEntity.getTitle(), eContractEntity.getComment());
//				contractId = jsonObject.getString("contractId");
//				if (contractId ==null) {
//					return new Tip("创建合同失败！", Global.FAILD);
//				}
//				eContractEntity.setContractId(contractId);
//				eContractDao.edit(eContractEntity);
//				/*List<LicenseContractEntity> lcList = licenseContractDao.getLiceseIdsByCuuid(cuuid);
//				//1.查询签署者
//				for (LicenseContractEntity lcEntity : lcList) {
//					//得到证照信息
//					LicenseEntity licenseEntity = licenseDao.getLic_ShaByLicenseId(lcEntity.getLicense_id());
//					
//					
//					String signer = licenseEntity.getOrgUuid();
//					signatureManager.contractAddSigner(contractId, signer);
//				}*/
//				
//				//3.将当前法人架构加入签署者
//				try {
//					signatureManager.contractAddSigner(contractId, account);
//				} catch (Exception e) {
//					e.printStackTrace();
//					return new Tip("添加签署者失败！",Global.SUCCESS);
//				}
//			}
//			return new Tip("合同初始化成功！",Global.SUCCESS);
//		}
		
		/**
		 * 添加签署者
		 */
//		@Override
//		public int addSigner(String orgUuid, String ecid) {
//			
//			LicenseEntity licInfo = licenseDao.selectLicInfo(orgUuid);
//			
//			//获取合同实体
//			EContractEntity eContractEntity = eContractDao.eContract(ecid);
//			
//			// 证照信息为空则不能添加
//			if (licInfo == null) {
//				return 0;// 没有真照信息
//			}
//			Integer id = licInfo.getId();
//			// 此合同里面已经有签署者则不能重复添加
//			List<LicenseContractEntity> list = licenseContractDao.getLiceseIdsByCuuid(ecid);
//			int i = 0;
//			for (LicenseContractEntity licenseContractEntity : list) {
//				if (licenseContractEntity.getLicense_id() == id) {
//					i++;
//				}
//			}
//			if (i == 0) {
//				try {
//					signatureManager.contractAddSigner(eContractEntity.getContractId(), orgUuid);
//				} catch (Exception e) {
//					e.printStackTrace();
//					return -1;//添加失败
//				}
//				LicenseContractEntity licenseContractEntity = new LicenseContractEntity();
//				licenseContractEntity.setContract_uuid(ecid);
//				licenseContractEntity.setLicense_id(id);
//				return licenseContractDao.insertLicenseContract(licenseContractEntity);
//
//			} else {
//				return -1;// 已经有此联系人
//			}
//
//		}
		
		
		@Override
		public String addSigner(int ecId, String orgUuid) {
			
			String eId = String.valueOf(ecId);
			
			String info = "";
			
			if(eId != null && !eId.trim().equals("") && orgUuid != null && !orgUuid.trim().equals("")) {
				
				LicenseEntity licenseInfo = licenseDao.licenseInfo(orgUuid);
				if(licenseInfo != null) {
					
					String cName = licenseInfo.getcName();
					
					info = "签署者信息" + "\r\norgUuid" + orgUuid +"\r\n合同id:" + ecId + "\r\n企业名称:" + cName;
					
					// 获取签署者列表
					List<LegalPersonEContractEntity> orgUuids = legalPersonEContractDao.hasSigner(eId, orgUuid);
					
					if(orgUuids == null || orgUuids.size() == 0) {
						
						LegalPersonEContractEntity legalPersonEContractEntity = new LegalPersonEContractEntity();
						
						legalPersonEContractEntity.setLpId(orgUuid);
						legalPersonEContractEntity.setEcId(ecId);
						legalPersonEContractEntity.setHolder(0);
						
						
						this.addRelate(legalPersonEContractEntity);
						
						logger.info("添加签署者addSigner(int ecId, String orgUuid) -> ecId:" + ecId + " orgUuid:" + orgUuid + "没有被添加，现在已经被添加了!");
						
						String message = info + "\r\n添加签署者成功!";
						
						Global.myLogger.add("addSigner", "true", message);
						
						String jsonString = Global.getProtocol(Global.ICE_OK, message, false);
						
						return jsonString;
						
					} else {
						
						logger.info("添加签署者addSigner(int ecId, String orgUuid) -> ecId:" + ecId + " orgUuid:" + orgUuid + "已经被添加了，不需要再次添加!");
				
						String message = "添加签署者已成功!";
						
						String jsonString = Global.getProtocol(Global.ICE_OK, message, false);
						
						return jsonString;
					}
				} else {
					
					logger.info("添加签署者addSigner(int ecId, String orgUuid) -> ecId:" + ecId + " orgUuid:" + orgUuid + "工商信息不全，不能添加为签署者!");

					info = "签署者信息" + "\r\norgUuid" + orgUuid +"\r\n合同id:" + ecId + "\r\n企业名称:????";
					
					String message = info + "\r\n添加签署者失败:法人架构工商信息不全!";
					
					Global.myLogger.add("addSigner", "false", message);
					
					String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
					
					return jsonString;
				}
				

			} else {
				
				logger.info("添加签署者addSigner(int ecId, String orgUuid) -> ecId:" + ecId + " orgUuid:" + orgUuid + "合同ID或法人架构ID为空!");
				
				info = "签署者信息" + "\r\norgUuid" + orgUuid +"\r\n合同id:" + ecId + "\r\n企业名称:???";
				
				String message = info + "\r\n添加签署者失败:合同ID或法人架构ID为空!";
				
				Global.myLogger.add("addSigner", "false", message);
				
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				return jsonString;
			}
		}

		public void removeSigner(String id) {
			
			this.delRelate(id);
			
		}
		
//		/**
//		 * 签署合同
//		 * 
//		 * @param cuuid
//		 * @param signEntity
//		 */
//		@Transactional
//		public Tip signContract(String ecid, SignEntity signEntity,String orgUuid) {
//		
//			//获取合同实体
//			EContractEntity eContractEntity = eContractDao.eContract(ecid);
//					
//			String contractId = eContractEntity.getContractId();
//			
//			String account = orgUuid;
//			/*// 如果签署的合同不存在则创建合同
//			if (signatureManager.contractDownload(contractId) == null) {
//				byte[] pdfData = ImageConvert.httpImage2byte(contractVo.getFileUrl());
//				// 1.创建合同节点,返回合同文件编号
//				String fid = signatureManager.contractCreateFileNode(account, pdfData);
//				if (fid == null) {
//					return new Tip("创建合同节点失败！", Global.FAILD);
//				}
//				contractVo.setFid(fid);
//
//				// 2.创建合同
//				JSONObject jsonObject = signatureManager.contractCreate(account, fid,
//						Long.toString(contractVo.getDeadline().getTime() / 1000), contractVo.getCname(),
//						contractVo.getSummary());
//				contractId = jsonObject.getString("contractId");
//				if (contractId == null) {
//					return new Tip("创建合同失败！", Global.FAILD);
//				}
//				contractVo.setContractId(contractId);
//				contractDao.updateContract(contractVo);
//				List<LicenseContractEntity> lcList = licenseContractDao.getLiceseIdsByCuuid(cuuid);
//				// 1.查询签署者
//				for (LicenseContractEntity lcEntity : lcList) {
//					// 得到证照信息
//					LicenseEntity licenseEntity = licenseDao.getLic_ShaByLicenseId(lcEntity.getLicense_id());
//
//					String signer = licenseEntity.getOrgUuid();
//					signatureManager.contractAddSigner(contractId, signer);
//				}
//				// 3.添加批量签署者
//
//			}*/
//
//			// 4.签署合同
//			byte[] signatureImageData = signatureManager.getUserSignatureImageData(account, 2);
//
//			String signerCert = signatureManager.userGetCert(account, "ZJCA");
//
//			try {
//				signatureManager.contractSign(contractId, account, signatureImageData, signerCert,
//						signEntity.getSignPageNum(), signEntity.getSignX(), signEntity.getSignY(),
//						signEntity.getSignWidth(), signEntity.getSignHeight());
//				// 修改合同状态为已发送，正在签署中
//				
//				eContractDao.edit(eContractEntity);
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//				return new Tip("签署合同失败！", Global.FAILD);
//			}
//					
//			//将签署的合同转换成图片保存返回给页面
//			byte[] contractData = signatureManager.contractDownload(contractId);
//			
//			if(contractData != null) {
//			
//				// 存放到web根目录下,如果日期目录不存在，则创建,
//				final String path = Global.getBasePath() + Global.CONTRACT_FILE_PATH;
//				String fileName = Timestamp.getNowDateStr("yyyyMMddHHmmss") + ".pdf";
//
//				logger.info("合同保存路径："+path);
//				logger.info("合同名称："+fileName);
//
//				String filePatn = path + "/" + fileName;
//
//				ImageConvert.byte2Pdf(contractData, filePatn);
//								
//				eContractEntity.setContract(fileName);
//				
//				eContractDao.edit(eContractEntity);
//				
// 				
//				return new Tip("签署合同成功，并已经保存！", Global.SUCCESS);
//			}
//			
//			return new Tip("签署合同失败，有可能没有返回合同二进制数据！", Global.FAILD);
//
//		}
//		
		
		private void sendRequest(String returnURL) {

			if(returnURL != null && !returnURL.trim().equals("")) {
				
				String resultString = Https.sendGet(returnURL);
				
				logger.info("Review interface result message:" + resultString);
			}
		}
		
		
		
		/**
		 *  向审批返回签署结果
		 *  
		 */
		public boolean sendSignState(EContractEntity eContractEntity, String message, boolean success) {
			
			boolean ok = false;
			
			String returnURL = eContractEntity.getReturnURL();
			if(returnURL != null && !returnURL.trim().equals("")) {
				
				logger.info("********sendSignState->当前签署合同returnURL: " + returnURL);
				
				ok = true;
				
				String contract = eContractEntity.getContract();
				
				String title = eContractEntity.getTitle();
				String name = eContractEntity.getName();
				String fileName = eContractEntity.getFileName();
				Integer state = eContractEntity.getState();
			 
				Map<String, String> paramMap = new HashMap<String, String> ();
				
				if(contract != null && !contract.trim().equals("")) {
					
					name = contract;
				}
				
				
				paramMap.put("title", title);
				paramMap.put("fileName", fileName);
				paramMap.put("name", name);
				paramMap.put("state", String.valueOf(state));
				paramMap.put("message", message);
				
				String contractInfo = JSON.toJSONString(paramMap);
				
				
				logger.info("********sendSignState->合同信息:" + contractInfo);
				

				
				String serverPath = Global.getConfig(Global.SERVER_PATH_KEY);
				
				logger.info("********sendSignState->本服务器IP:Port:" + serverPath);
				
				String contractFilePath = "";
				
				if(state == Global.SIGNER_FINISH) {
					
					contractFilePath = serverPath + "/" +
							Global.CONTRACT_FILE_PATH + "/" +
							name;
					
				}
				
				logger.info("********sendSignState->签署成功合同下载址" + contractFilePath);
				
				paramMap.put("url", contractFilePath);
				
				String jsonString = JSON.toJSONString(paramMap);
				
				logger.info("********sendSignState->发送审批参数信息：" + jsonString);


				String info = "审批签签署详细:"+ contractInfo;
				
				Global.myLogger.add("signContract", "true", info);
				
				String resultString = IOkHttpUtil.sendPost(returnURL, paramMap);
				
				logger.info("********sendSignState->发送审批通知结果信息：" + resultString);
				
			} else {
			
				logger.info("********sendSignState->当前签署操作的合同信息没有returnURL,应该不是走审批的合同");
			}
			
			return ok;
		}
		

		/**
		 * 签署合同
		 * 
		 * @param cuuid
		 * @param signEntity
		 */
		@Transactional
		public String signContract(String ecId, SignEntity signEntity, String orgUuid) {
		
			String info = "";
			
			//获取合同实体
			EContractEntity eContractEntity = eContractDao.eContract(ecId);
			if(eContractEntity == null) {
				
				String message = "签署合同失败 没有获取到基本信息, 合同id:" + ecId;
				
				Global.myLogger.add("signContract", "false", message);
				
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				return jsonString;
			}
						
			String contractId = eContractEntity.getContractId();
			String account = orgUuid;
			
			String title = eContractEntity.getTitle();
			String name = eContractEntity.getName();
			String fileName = eContractEntity.getFileName();
			
			
			String returnURL = eContractEntity.getReturnURL();
			
			String review = (returnURL == null || returnURL.trim().equals(""))? "非审批合同" : "审批合同";
			
			info = "合同文件详细 \r\n标题:" + title + "\r\n文件名称:" + fileName + "\r\n审批信息:" + review;
			
			if(contractId == null || (contractId.trim().equals("") == true)) {

				this.sendRequest(returnURL);
				
				String message = info + "\r\n签署合同失败 没有获到合同ID！";
				
				Global.myLogger.add("signContract", "false", message);
				
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				this.sendSignState(eContractEntity, message, false);
				
				return jsonString;
			}
			
			if(account == null || (account.trim().equals("") == true)) {
				
				String message = info + "签署合同失败 没有获到帐号信息！";
				
				Global.myLogger.add("signContract", "false", message);
				
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				this.sendSignState(eContractEntity, message, false);
				
				return jsonString;
			}
			
			info += "\r\n注册签署帐号：" + account + "\r\n合同id:" + contractId;
			
			// 4.签署合同
			byte[] signatureImageData = signatureManager.getUserSignatureImageData(account, Global.SIGN_TYPE_COMPANY);
			
			if(signatureImageData == null) {
				
				String message = info + "\r\n签署合同失败 没有获取到 电子印章数据！";
				
				Global.myLogger.add("signContract", "false", message);
				
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				this.sendSignState(eContractEntity, message, false);
				
				return jsonString;
			}
				
			
			String signerCert = signatureManager.userGetCert(account, "ZJCA");
			if(signerCert == null || signerCert.trim().equals("") == true) {
				
				String message = info + "\r\n签署合同失败 没有获取到 CA认证信息！";
				
				Global.myLogger.add("signContract", "false", message);
				
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				this.sendSignState(eContractEntity, message, false);
				
				return jsonString;
			}
			
			
			try {
				signatureManager.contractSign(contractId, account, signatureImageData, signerCert,
						signEntity.getSignPageNum(), signEntity.getSignX(), signEntity.getSignY(),
						signEntity.getSignWidth(), signEntity.getSignHeight());
				
				LegalPersonEContractEntity legalPersonEContractEntity = new LegalPersonEContractEntity();
			
				legalPersonEContractEntity.setEcId(Integer.valueOf(ecId));
				legalPersonEContractEntity.setLpId(orgUuid);
				legalPersonEContractEntity.setState(Global.SIGNER_FINISH);
		
				legalPersonEContractDao.editState(legalPersonEContractEntity);
				
				String message = "当前合同id:" + ecId + " 标题:" + title + "名称:" + fileName + " 签署者：" + orgUuid + " 签署者状态设置为已签署操作 state="+ Global.SIGNER_FINISH;
				
				logger.info(message);

				Global.myLogger.add("signContract", "true", message);
				
				this.sendSignState(eContractEntity, message, false);
				
				boolean isDone = true;
				
				List<LegalPersonEContractEntity> lpEContractList = legalPersonEContractDao.getLegalPersonEContractEntityList(ecId);			
				
				// 支持多方和单方签署
				if(lpEContractList != null) {
					
					for(LegalPersonEContractEntity lpContract : lpEContractList) {
						
						int state = lpContract.getState();
						
						if(state != Global.SIGNER_FINISH) {
							
							isDone = false;
						}
						
						logger.info("法人签署状态 " + lpContract.getLpId() + ":" + state);
					}
					
				}
				
				
				if(isDone == true) {
					
					eContractEntity.setState(Global.FINISH);
					
					int r = eContractDao.edit(eContractEntity);
					if(r == 0) {
						
						logger.info("签署成功状态更新失败，会影响合同结束状态!");
					}
					
					String jsonString = JSON.toJSONString(eContractEntity);
					
					logger.info("朕要看看你这葫芦里(eContractEntity) 装的什么状态:" + jsonString);
					
					boolean ok = this.lockContract(contractId);
					if(ok == true) {
						
						message = "当前合同id:" + ecId + " 标题:" + title + "名称:" + fileName +  " 签署者：" + orgUuid + "合同签署完成并且执行锁定成功 state="+ Global.FINISH;
						
						logger.info(message);
						
						Global.myLogger.add("signContract", "true", message);

						ok = this.finishContract(contractId);
						
						 this.sendSignState(eContractEntity, message, false);

						if(ok == true) {

 							message = "当前合同id:" + ecId + " 标题:" + title + "名称:" + fileName +  " 签署者：" + orgUuid+ "合同签署完成操作并且执行锁定成功-结束成功state="+ Global.FINISH;
							
 							Global.myLogger.add("signContract", "true", message);
 							
							jsonString = Global.getProtocol(Global.ICE_OK, message, false);
							
						    this.sendSignState(eContractEntity, message, false);
							
						   logger.info(message);
							
						} else {
							
							message = "当前合同id:" + ecId + " 标题:" + title + "名称:" + fileName +  " 签署者：" + orgUuid + "合同签署完成操作并且执行锁定成功-结束失败 state="+ Global.ICE_UNKNOW;
							logger.info(message);

							Global.myLogger.add("signContract", "false", message);
							
							 jsonString = Global.getProtocol(Global.ICE_UNKNOW, message, false);
							
							this.sendSignState(eContractEntity, message, false);
							
							return jsonString;
						}
						
					}else {
						
						message = "当前合同id:" + ecId  +" 标题:" + title + "名称:" + fileName +  " 签署者：" + orgUuid + "合同签署完成并且执行锁定失败 state="+ Global.ICE_UNKNOW;
						logger.info(message);
						
						Global.myLogger.add("signContract", "false", message);
						
						jsonString = Global.getProtocol(Global.ICE_UNKNOW, message, false);
						 
						this.sendSignState(eContractEntity, message, false);
						
						return jsonString;
					}
											
				}
				else 
				{
					message = "当前合同id:" + ecId + " 标题:" + title + "名称:" + fileName +  " 签署者：" + orgUuid + " 合同状态仍然为签署中成操作 state="+ Global.SIGNER_SIGNING;
					logger.info(message);
					
					Global.myLogger.add("signContract", "true", message);
					
					this.sendSignState(eContractEntity, message, false);
				}
				
				
				// 签署成功，则保存
				byte[] contractData = signatureManager.contractDownload(contractId);
				if(contractData != null) {
				
					//logger.info("合同二进制数据:" + Arrays.toString(contractData));
					
				    String path = Global.getBasePath() + Global.CONTRACT_FILE_PATH;
//				    
//					String oldContract = eContractEntity.getContract();
//					if(oldContract != null && !oldContract.trim().equals("")) {
//						
//						FileConvert.delFile(path, oldContract);
//					}
				
					fileName = Timestamp.getNowDateStr("yyyyMMddHHmmssSSS") + ".pdf";
					
					// 改进保存方法
					FileConvert.byteToFile(contractData, path, fileName);
									
					eContractEntity.setContract(fileName);
					
					eContractDao.edit(eContractEntity);
					
				    message = "当前签署成功，并已经将信息保存到数据！";
					 
					// String jsonString = Global.getProtocol(Global.ICE_OK, message, false);
					
					message = "当前合同id:" + ecId + " 标题:" + title + "名称:" + fileName +  " 签署者：" + orgUuid + "合同签署成功-并已经保存成功 state="+ Global.FINISH;
					
					Global.myLogger.add("signContract", "true", message);
					
					logger.info(message);
					
					this.sendSignState(eContractEntity, message, true);
					
					String jsonString = Global.getProtocol(Global.ICE_OK, message, false);

					return jsonString;
					
				} else {
						
					message = "当前合同id:" + ecId + " 标题:" + title + "名称:" + fileName +  " 签署者：" + orgUuid + "合同签署成功-但获取合同二进制失败 state="+ Global.FINISH;
					
					logger.info(message);
					
					Global.myLogger.add("signContract", "false", message);
					
					this.sendSignState(eContractEntity, message, true);
					
					String jsonString = Global.getProtocol(Global.ICE_UNKNOW, message, false);

					return jsonString;
				}
				
			} catch (Exception e) {
		
				e.printStackTrace();
				
				String message = info + "\r\n签署合同异常:" +e.getLocalizedMessage();
				
				Global.myLogger.add("signContract", "false", message);
				
				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
				
				this.sendSignState(eContractEntity, message, false);
				
				return jsonString;
			}
	}		

		
//
//		/**
//		 * 签署合同
//		 * 
//		 * @param cuuid
//		 * @param signEntity
//		 */
//		@Transactional
//		public String signContract(String ecId, SignEntity signEntity, String orgUuid) {
//		
//			//获取合同实体
//			EContractEntity eContractEntity = eContractDao.eContract(ecId);
//			if(eContractEntity == null) {
//				
//				String message = "签署合同失败 没有获取到基本信息";
//				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
//				
//				return jsonString;
//			}
//						
//			String contractId = eContractEntity.getContractId();
//			String account = orgUuid;
//			
//			String returnURL = eContractEntity.getReturnURL();
//			
//			
//			if(contractId == null || (contractId.trim().equals("") == true)) {
//
//				this.sendRequest(returnURL);
//				
//				String message = "签署合同失败 没有获到合同ID！";
//				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
//								
//				return jsonString;
//			}
//			
//			if(account == null || (account.trim().equals("") == true)) {
//				
//				String message = "签署合同失败 没有获到帐号信息！";
//				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
//								
//				return jsonString;
//			}
//			
//			// 4.签署合同
//			byte[] signatureImageData = signatureManager.getUserSignatureImageData(account, Global.SIGN_TYPE_COMPANY);
//			
//			if(signatureImageData == null) {
//				
//				String message = "签署合同失败 没有获取到 电子印章数据！";
//				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
//				
//				return jsonString;
//			}
//				
//			
//			String signerCert = signatureManager.userGetCert(account, "ZJCA");
//			if(signerCert == null || signerCert.trim().equals("") == true) {
//				
//				String message = "签署合同失败 没有获取到 CA认证信息！";
//				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
//				
//				return jsonString;
//				
//			}
//			
//			
//			try {
//				signatureManager.contractSign(contractId, account, signatureImageData, signerCert,
//						signEntity.getSignPageNum(), signEntity.getSignX(), signEntity.getSignY(),
//						signEntity.getSignWidth(), signEntity.getSignHeight());
//				
//				
// 				
//				LegalPersonEContractEntity legalPersonEContractEntity = new LegalPersonEContractEntity();
//				if(legalPersonEContractEntity != null) {
//			
//					legalPersonEContractEntity.setEcId(Integer.valueOf(ecId));
//					legalPersonEContractEntity.setLpId(orgUuid);
//					legalPersonEContractEntity.setState(Global.SIGNER_FINISH);
//			
//					legalPersonEContractDao.editState(legalPersonEContractEntity);
//					
//					logger.info("当前合同id:" + ecId + " 名称:" + eContractEntity.getTitle() + " 签署者：" + orgUuid + " 签署者状态设置为签署中操作 state="+ Global.SIGNER_FINISH);
//
//					boolean isDone = true;
//					
//					List<LegalPersonEContractEntity> lpEContractList = legalPersonEContractDao.getLegalPersonEContractEntityList(ecId);
//					if(lpEContractList == null || lpEContractList.size() < 2) {
//					
//						isDone = false;
//					}
//					else  {
//						
//						for(LegalPersonEContractEntity lpContract : lpEContractList) {
//							
//							int state = lpContract.getState();
//							
//							if(state != Global.SIGNER_FINISH) {
//								
//								isDone = false;
//							}
//							
//							logger.info("法人签署状态 " + lpContract.getLpId() + ":" + state);
//						}
//					}
//					
//					
//					if(isDone == true) {
//						
//						eContractEntity.setState(Global.FINISH);
//						eContractDao.edit(eContractEntity);
//						
//						boolean ok = this.lockContract(contractId);
//						if(ok == true) {
//							
//							logger.info("当前合同id:" + ecId + " 名称:" + eContractEntity.getTitle() + "合同签署完成并且执行锁定成功 state="+ Global.FINISH);
//
//							ok = this.finishContract(contractId);
//							
//							if(ok == true) {
//									
//								logger.info("当前合同id:" + ecId + " 名称:" + eContractEntity.getTitle() + "合同签署完成操作并且执行锁定成功-结束成功 state="+ Global.FINISH);
//								
//							
//							
//							} else {
//								logger.info("当前合同id:" + ecId + " 名称:" + eContractEntity.getTitle() + "合同签署完成操作并且执行锁定成功-结束失败 state="+ Global.FINISH);
//
//							}
//							
//						}else {
//							logger.info("当前合同id:" + ecId + " 名称:" + eContractEntity.getTitle() + "合同签署完成并且执行锁定失败 state="+ Global.FINISH);
//						}
//						
//						logger.info("当前合同id:" + ecId + " 名称:" + eContractEntity.getTitle() + " 签署者：" + orgUuid + " 合同状态设置为签署完成操作 state="+ Global.FINISH);
//						
//					}
//					else 
//					{
//						logger.info("当前合同id:" + ecId + " 名称:" + eContractEntity.getTitle() + " 签署者：" + orgUuid + " 合同状态仍然为签署中成操作 state="+ Global.FINISH);
//
//					}
//					
//				}
//				
//			} catch (Exception e) {
//		
//				e.printStackTrace();
//				
//				String message = "签署合同失败！";
//				String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
//				
//				return jsonString;
//			}
//					
//			//将签署的合同转换成图片保存返回给页面
//			byte[] contractData = signatureManager.contractDownload(contractId);
//			
//			if(contractData != null) {
//			
//				// 存放到web根目录下,如果日期目录不存在，则创建,
//				final String path = Global.getBasePath() + Global.CONTRACT_FILE_PATH;
//				String fileName = Timestamp.getNowDateStr("yyyyMMddHHmmss") + ".pdf";
//
//				logger.info("合同保存路径："+path);
//				logger.info("合同名称："+fileName);
//
//				String filePatn = path + "/" + fileName;
//
//				ImageConvert.byte2Pdf(contractData, filePatn);
//								
//				eContractEntity.setContract(fileName);
//				
//				eContractDao.edit(eContractEntity);
//				
//				String message = "签署合同成功，并已经保存！";
//				String jsonString = Global.getProtocol(Global.ICE_OK, message, false);
//				
//				return jsonString;
//			}
//			
//			
//			String message = "签署合同失败，有可能没有返回合同二进制数据！";
//			String jsonString = Global.getProtocol(Global.ICE_UNKNOW,  message);
//			
//			return jsonString;
//
//		}		

		
		
		public boolean lockContract(String contractId) {
			
			boolean ok = false;
			
			//获取合同实体
			try {
				signatureManager.contractLock(contractId);
				
				ok = true;
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return ok;
		}

		public boolean finishContract(String contractId) {
		
			boolean ok = false;
			
			try {
				signatureManager.contractFinish(contractId);
				 
				ok = true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return ok;
		}
		
		
//	
//
//		public Tip lockContract(String ecid) {
//			
//			//获取合同实体
//			EContractEntity eContractEntity = eContractDao.eContract(ecid);
//			try {
//				signatureManager.contractLock(eContractEntity.getContractId());
//				
//				return new Tip("锁定合同成功！", Global.SUCCESS);
//			} catch (Exception e) {
//				e.printStackTrace();
//				return new Tip("锁定合同失败！", Global.FAILD);
//			}
//		}
//
//		public Tip finishContract(String ecid) {
//		
//			//获取合同实体
//			EContractEntity eContractEntity = eContractDao.eContract(ecid);
//			try {
//				signatureManager.contractFinish(eContractEntity.getContractId());
//				// 修改合同状态为已完成
//				eContractEntity.setState(5);
//				eContractDao.edit(eContractEntity);
//				return new Tip("结束合同成功！", Global.SUCCESS);
//			} catch (Exception e) {
//				e.printStackTrace();
//				return new Tip("结束合同失败！", Global.FAILD);
//			}
//		}
		
		

		/**
		 * 下载合同
		 * 
		 * @param cuuid
		 */
//		public Map<String, Object> contractDownload(String ecid) {
//			Map<String, Object> map = new HashMap<>();
//
//			//获取合同实体
//			EContractEntity eContractEntity = eContractDao.eContract(ecid);
//			if(eContractEntity == null) {
//				
//				map.put("message", "没有找到合同信息(EContractEntity==null)！");
//				map.put("code", Global.ICE_UNKNOW);
//				
//				return map;
//			}
//			
//			
//			String contactId = eContractEntity.getContractId();
//			
//			String fileName = eContractEntity.getFileName();
//			
//			String contractFile = eContractEntity.getContract();
//			
//			
//			JSONObject infoJson = signatureManager.contractGetInfo(contactId);
//			
//			logger.info(infoJson.toJSONString()); 
//			
//			Integer status = infoJson.getInteger("status");
//			
//			logger.info("当前合同名称(" + fileName + ") \n id (" + contactId + ") \n 合同 (" + contractFile + ") \n 状态:" + status);
//			
//			if(status != 5) {
//				
//				
//				map.put("message", "合同状态为非完成状态，不能执行下载操作 ！");
//				
//				map.put("code", Global.ICE_UNKNOW);
//				
//				return map;
//			}
//		
//			
//			logger.info("生成签署合同下载数据成功，详细信息为 contactId :" + contactId  + " 合同文件名称:" + fileName + " 实际合同文件:" + contractFile);
//			
//			map.put("code", Global.ICE_OK);
//			map.put("contract", contractFile);
//			map.put("fileName", fileName);
//			
//			return map;
//		}

		

		/**
		 * 下载合同
		 * 
		 * @param cuuid
		 */
		public Map<String, Object> contractDownload(String ecid) {
			Map<String, Object> map = new HashMap<>();

			//获取合同实体
			EContractEntity eContractEntity = eContractDao.eContract(ecid);
			if(eContractEntity == null) {
				
				map.put("message", "没有找到合同信息(EContractEntity==null)！");
				map.put("code", Global.ICE_UNKNOW);
				
				return map;
			}
			
			
			String contactId = eContractEntity.getContractId();
			
			String fileName = eContractEntity.getFileName();
			
			String contractFile = eContractEntity.getContract();
			
			
			JSONObject infoJson = signatureManager.contractGetInfo(contactId);
			
			logger.info(infoJson.toJSONString()); 
			
			Integer status = infoJson.getInteger("status");
			
			logger.info("当前合同名称(" + fileName + ") \n id (" + contactId + ") \n 合同 (" + contractFile + ") \n 状态:" + status);
			
			if(status != 5) {
				
				map.put("message", "合同状态为非完成状态，不能执行下载操作 ！");
				
				map.put("code", Global.ICE_UNKNOW);
				
				return map;
			}
			
			
			byte[] data = signatureManager.contractDownload(contactId);
			if (data == null) {
				map.put("message", "没有返回签署成功的合同二进制数据，合同contactId为：" + contactId);
				
				map.put("code", Global.ICE_UNKNOW);
				
				return map;
			}
			
			logger.info("执行下载操作返回的已经签好的合同二进制数据:" + data);
			
			logger.info("生成签署合同下载数据成功，详细信息为 contactId :" + contactId  + " 合同文件名称:" + fileName + " 实际合同文件:" + contractFile);
			
			map.put("code", Global.ICE_OK);
			
			map.put("contract", data);
			map.put("contractFile", contractFile);
			
			map.put("fileName", fileName);
			
			return map;
		}
		
		
		
//		
//		/**
//		 * 下载合同
//		 * 
//		 * @param cuuid
//		 */
//		public Map<String, Object> contractDownload(String ecid) {
//			Map<String, Object> map = new HashMap<>();
//
//			//获取合同实体
//			EContractEntity eContractEntity = eContractDao.eContract(ecid);
//			if(eContractEntity == null) {
//				
//				map.put("message", "没有找到合同信息！");
//				map.put("code", Global.FAILD);
//				
//				return map;
//			}
//			
//			
//			byte[] contract = signatureManager.contractDownload(eContractEntity.getContractId());
//			if (contract == null) {
//				map.put("message", "没有找到文件！");
//				map.put("code", Global.FAILD);
//				
//				return map;
//			}
//			map.put("contractData", contract);
//			map.put("contractName", eContractEntity.getTitle());
//			
//			return map;
//		}

		
		
		
		
		/**
		 * 获取合同详细信息
		 * 
		 * @param cuuid
		 * @return
		 */
		public JSONObject contractGetInfo(String ecid) {

			//获取合同实体
			EContractEntity eContractEntity = eContractDao.eContract(ecid);
			
			return signatureManager.contractGetInfo(eContractEntity.getContractId());
		}

		@Override
		public Map<String, Object> getSealAndSigner(String cuuid, String orgUuid) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String uploadFile(String token, String length, String fileName, String account, String appName) {
			
			
			return "";
		}
}
