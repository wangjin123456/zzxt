package com.zzxt.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;


/**
 * 全局常量
 * @author think
 *
 */

/*
 * 协议包说明 ICE
 * 
 * type:0
 * 
 * code:0,-1  // 0 成功  -1失败 
 *  
 * message:{}  // 返回提示信
 * 
 * content:{}  // 数据包体
 * 
 */

/*
 * 协议包说明 自定义
 * 
 * type:1
 * 
 * code:0,-1  // 0 成功  -1失败 
 *  
 * message:{
 * 		show:true|false    // true显示  false 不显示
 *      info:{}            // 提示信息
 * }  // 返回提示信
 * 
 * content:{}  // 数据包体
 * 
 */




public class Global {
	
	/********自定义状态码*********/
	
	public final static int ICE_UNKNOW = -1;
	public final static int ICE_OK = 0;

	public final static int SUCCESS0=0;//成功
	
	public final static int SUCCESS=200;//成功
	public final static int FAILD=500;//失败
	
	public final static String RELOGIN_HINT = "登录已失效，请重新登录！";

	
	/********时间(单位：秒)*******/
	public final static int TWO_HOUR_SECOND=7200;//2小时
	
	/********缓存KEY************/
	public final static String  ACCOUNT="sessionIdaccount";//ICE返回的account信息(字符串中的sessionId请替换成动态sessionId)
	public final static String TOKEN="sessionIdtoken";//ICE返回的accountToken信息(字符串中的sessionId请替换成动态sessionId)
	public final static String ORGTYPES="sessionIdorgTypes";//ICE返回的orgTypes信息(字符串中的sessionId请替换成动态sessionId)
	
	
	//////////////////////////////////////协议键///////////////////////////////////////
	public final static String PROTOCOL_TYPE_KEY						=      "type";
	public final static String PROTOCOL_CODE_KEY 					=      "code";
	public final static String PROTOCOL_MESSAGE_KEY					= 	   "message";
	public final static String PROTOCOL_CONTENT_KEY					= 	   "content";
	
	public final static String PROTOCOL_MESSAGE_SHOW_KEY				= 		"show";
	public final static String PROTOCOL_MESSAGE_INFO_KEY				= 		"info";
	
	public final static String PROTOCOL_TYPE_ICE_VALUE				= 		"0";
	public final static String PROTOCOL_TYPE_CUSTOMER_VALUE			= 		"1";
	
	public final static String CONTRACT_DOWNLOAD_TYPE_KEY			= 		"download";
	
	//////////////////////////////////////登录 鉴权键///////////////////////////////////////
	public final static String  USER_INFO_NAME						= 			"name";		// 真名姓名
	public final static String  USER_INFO_USER						= 			"user"; 		// 帐号
	public final static String  USER_INFO_PWD						= 			"pwd";
	public final static String  USER_INFO_PHONE						= 			"phone";
	public final static String  USER_INFO_EMAIL						= 			"email";
	public final static String  USER_INFO_ACCOUNT_UUID				= 			"accountUuid";
	public final static String  USER_INFO_CORP_ID					= 			"corpId";
	public final static String  AUTH_INFO_ACCESS_TOKEN				= 			"accessToken";
	
	public final static String  AUTH_INFO_ACCESS_TOKEN_V1				= 			"accessToken_v1";
	
	
	
	public final static  String TIME_REMINDER_TIME					= 			" 11:00:00";
	
	public final static String CONTRACT_FILE_PATH = "contract";
	public final static String SIGNATURE_FILE_PATH = "signature";
	
	
	public final static int SHANG_SHANG_SIGN = 1; //上上签平台代码
	
	public final static int ZHONG_SHUI_SIGN = 2; //中税平台代码
	
	
	public final static Integer CONTRACT_SERACH_ALL = -1;			// 合同查询全部 
	
	
	
//	public final static String AONEA		= "^1^";			//[
//	public final static String ATWOA		= "^2^";			//]
//	public final static String ATHRA		= "^3^";			//{
//	public final static String AFURA		= "^4^";			//}

	
	public final static String AONEA		= "_1_";			//[
	public final static String ATWOA		= "_2_";			//]
	public final static String ATHRA		= "_3_";			//{
	public final static String AFURA		= "_4_";			//}
	
	///////////////////////////0组织架构、1客户架构、2法人架构、3供方架构、4国家架构///////////////////////////////
	public final static String STRUCTURE_TYPE_ORG			=		"0";
	public final static String STRUCTURE_TYPE_CUSTOM			=		"1";
	public final static String STRUCTURE_TYPE_LEGALPERSON 	= 		"2";
	public final static String STRUCTURE_TYPE_PROVIDER	 	=		"3";
	public final static String STRUCTURE_TYPE_STATE	      	=		"4";
	
	public final static Integer SIGN_TYPE_PROSON		=	 	1;   // 个人
	public final static Integer SIGN_TYPE_COMPANY	= 		2;   // 企业
	
	
	
	/////////////////////////////////////////权限常量//////////////////////////////////////////////////////
	
	public final static Integer SUB_LEVEL_FALSE			= 			0;
	public final static Integer SUB_LEVEL_TRUE			= 			1;
	
	public final static Integer USER_POWER_LICENSE		= 			1;
	public final static Integer USER_POWER_SIGNATURE		= 			2;
	public final static Integer USER_POWER_ALL			= 			3;
	
	private static Logger logger = Logger.getLogger(Global.class.getName());
	

	public static Map<String, String> configMap = new HashMap<String, String>();

	private static  Map<String, Object> loginInfoMap = new HashMap<String, Object> ();
	
	private static Map<String, String> logInfoMap = new HashMap<String, String>();
	
	
	
	
	
//	public final static Integer UNSIGN = 0;			// 未签署 默认状态
//	public final static Integer SIGNING = 1;			// 签署中  
//	public final static Integer REVIEWING = 2;
//	public final static Integer REVIEWED = 3;		
//	public final static Integer REJECTED = 4;
//	public final static Integer FINISH = 5;			// 已完成

	public final static Integer UNSIGN = 0;			// 未签署 默认状态
	public final static Integer SIGNING = 1;			// 签署中  
	public final static Integer FINISH = 2;			// 已完成
	
	public final static Integer SIGNER_UNSIGN = 0;			// 未签署 默认状态
	public final static Integer SIGNER_SIGNING = 1;			// 签署中
	public final static Integer SIGNER_FINISH = 2;			// 已完成
	public final static Integer SIGNER_RESIGN = 3;			// 重签
	
	
	public final static Integer ICE_ORG_INIT							= 				-1;				// 默认，修改操作中不传即采用
	public final static Integer ICE_ORG_ENABLED						= 				0;				// 启用
	public final static Integer ICE_ORG_BAN 							= 				1;			    // 禁用

	/////////////////////////////////////////证照系统配置//////////////////////////////////////////////////////
	public final static String ICE_ZZXT_NUMBER_KEY					= 				"number";
	public final static String ICE_ZZXT_ENABLED_KEY					= 				"enabled";
	public final static String ICE_ZZXT_CODE_KEY						= 				"code";
	public final static String ICE_ZZXT_TOKEN_KEY					= 				"token";
	public final static String ICE_ZZXT_APPID_KEY					= 				"appID";
	public final static String ICE_ZZXT_ZHONG_SHUI_APPID_KEY			=				"zhongShuiAppId";
	
	public final static String ICE_ZZXT_CLIENT_SECRET_KEY				= 				"clientSecret";
	public final static String ICE_ZZXT_ZHONG_SHUI_CLIENT_SECRET_KEY				= 	"zhongShuiClientSecret";
	
	
	public final static String ICE_ZZXT_APP_UUID_KEY					=				"appUuid";
	public final static String ICE_ZZXT_APP_SECRET_KEY				=				"appSecret";
	
	public final static String ICE_ZZXT_API_URL_KEY					= 				 "iceApiURL";
	public final static String ICE_ZZXT_TIMESTAMP_URL_KEY				=				"timestampURL";
	
	public final static String ICE_ZZXT_ZHONG_SHUI_API_URL_KEY		= 					"zhongShuiApiURL";
	
	
	public final static String ICE_ZZXT_V1_APPKEY_KEY					=						"AppKey";
	public final static String ICE_ZZXT_V1_APPSECRET_KEY				=					"AppSecret";
	
	
	public final static String SERVER_PATH_KEY	 	  				 =				 "serverPath";
	
	
	
	
///////////////////////////////////////////上上签SDK配置常量//////////////////////////////////////////////////////
	
	public final static String SIGNATURE_SDK_URL_KEY					=				"signatureSdkURL";
	public final static String SIGNATURE_DEVID_KEY					=				"signatureDevId";
	public final static String SIGNATURE_PEM_KEY						=				"signaturePem";
	public final static String SIGNATURE_IS_FINGERKEY					= 				"isFinger";
	
//////////////////////////////////////////////审批配置/////////////////////////////////////////////
	public final static String REVIEW_CONTRACT_GET_INFO				= 				"getContractInfo";
	
	
	public final static String MAX_PAGE_KEY							=				"maxPage";
	
///////////////////////////////////////////配置文件//////////////////////////////////////////////////////
	public final static String CONFIG_PATH				=	"/resource-provider/src/main/resources/";
	
	//public final static String CONFIG_PATH				=	"";
	
//	public final static String CONFIG_FILE_DEV			= 	"application.dev.properties";			// 开发配置文件  
//	public final static String CONFIG_FILE_PROD			= 	"application.prod.properties";			// 预发配置文件
//	public final static String CONFIG_FILE_PRO			= 	"application.pro.properties";			// 生产配置文件
	
	
///////////////////////////////////////////证照系统在ice身份标识//////////////////////////////////////////

	public final static Integer ZZXT_NUMBER_DEV = 192;
	public final static Integer ZZXT_NUMBER_PRO = 221;
	
	public final static Integer ZZXT_NUMBER = ZZXT_NUMBER_DEV;
	
	
	public final static String CONFIG_FILE = "application.properties";
	

	public static MyLogger myLogger = null;
	

	public static void setMyLogger(MyLogger mylogger) {
		
		Global.myLogger = mylogger;
	}

 
	static  public boolean initConfig(String basePath) {
		
		// String configFilePath = basePath + CONFIG_PATH +  CONFIG_FILE;
		
		boolean initSuccess = false;

		String configFilePath = basePath + CONFIG_PATH +  CONFIG_FILE;
		
		logger.info("ConfigFilePath:" + configFilePath);

		initSuccess =  Global.readConfig(configFilePath);
		
		return initSuccess;
	}
	
	
	static public void initLogInfo() {
		
		Global.logInfoMap.put("Login", "登录");
		Global.logInfoMap.put("loginOout", "注销");
		
		Global.logInfoMap.put("getTree", "获取法人架构");
		Global.logInfoMap.put("getCorporationTree", "获取管理组织架构");
		Global.logInfoMap.put("addTreeView", "创建法人架构");
		Global.logInfoMap.put("addTree", "创建管理组织架构");
		Global.logInfoMap.put("updateTreeView", "修改法人架构");
		Global.logInfoMap.put("getLic", "获取工商信息");
		Global.logInfoMap.put("saveLic", "保存工商信息");
		Global.logInfoMap.put("doContractSearch", "查询合同");
		Global.logInfoMap.put("doContractSearchState", "合同状态过滤");
		Global.logInfoMap.put("getContractList", "获取合同列表");
		Global.logInfoMap.put("getContract", "获取合同信息");
		Global.logInfoMap.put("saveContract", "保存合同信息");
		Global.logInfoMap.put("editContract", "编辑合同信息");


		Global.logInfoMap.put("doSignatureSearch", "查询印章 ");
		Global.logInfoMap.put("getSignatureList", "获取印章列表");
		Global.logInfoMap.put("getSignature", "获取印章信息");
		Global.logInfoMap.put("saveSignature", "保存印章信息");
		Global.logInfoMap.put("editSignature", "编辑印章信息");
		Global.logInfoMap.put("delSignature", "删除印章信息");
		
		
		
		Global.logInfoMap.put("addSigner", "添加签署者");
		Global.logInfoMap.put("removeSigner", "移除签署");
		Global.logInfoMap.put("delContract", "删除合同");
		Global.logInfoMap.put("showConDetail", "查看/签署合同");
		Global.logInfoMap.put("applyCert", "申请ca证书");
		Global.logInfoMap.put("certStatus", "查询ca证书状态");
		Global.logInfoMap.put("userGetCert", "查询证书编号");
		Global.logInfoMap.put("setSignImage", "设置签名/印章图片");
		Global.logInfoMap.put("initContract", "初始化合同");
		Global.logInfoMap.put("signContract", "签署合同");
		Global.logInfoMap.put("contractInfo", "查看合同信息");
		Global.logInfoMap.put("downloadCon", "下载合同");
		
		
		
		Global.logInfoMap.put("getOrgStructure", "获取管理组织架构");
		Global.logInfoMap.put("getOrgStructureUsers", "获取员工列表");
		Global.logInfoMap.put("showUsers", "显示用户列表");
		Global.logInfoMap.put("addUsers", "添加用户");
		Global.logInfoMap.put("removeUsers", "移除用户");
		Global.logInfoMap.put("setPower", "设置权限");
		Global.logInfoMap.put("setSubLevel", "设置子级权限");
		
		
		
		
		Global.logInfoMap.put("sharehoderList", "获取股东列表");
		Global.logInfoMap.put("addShareInfo", "添加股东信息");
		Global.logInfoMap.put("delSharInfo", "删除股东信息");
		Global.logInfoMap.put("updateSharInfo", "修改股东信息");
		Global.logInfoMap.put("selectRegion", "查询行政区");
		
		Global.logInfoMap.put("addContractInfo", "审批增加合同");
		
	}
	
	static public String getLogInfo(String key) {
		
		Object o =  Global.logInfoMap.get(key);
		
		String value = "";
		
		if(o != null) {
			
			value = o.toString();
		}
		
		return value;
	}
	
	
	static public String getConfig(String key) {
		
		String value = Global.configMap.get(key);
		
		return value;
	}
	
	private static String basePath = "";
	
	static public void setBasePath(String basePath) {
		Global.basePath = basePath;
	}
	
	static public String getBasePath() {
		
		return Global.basePath;
	}
	

	
	/**
	  * 获取浏览器版本信息
	  * @Title: getBrowserName
	  * @data:2015-1-12下午05:08:49
	  * @author:wolf
	  *
	  * @param agent
	  * @return
	  */
	static public String getBrowserName(HttpServletRequest request) {
		
		String agent = request.getHeader("User-Agent").toLowerCase();
		
		logger.info("Browser -> Header -> agent:" + agent);
		
	  if(agent.indexOf("msie 7")>0){
	   return "ie7";
	  }else if(agent.indexOf("msie 8")>0){
	   return "ie8";
	  }else if(agent.indexOf("msie 9")>0){
	   return "ie9";
	  }else if(agent.indexOf("msie 10")>0){
	   return "ie10";
	  }else if(agent.indexOf("msie")>0){
	   return "ie";
	  }else if(agent.indexOf("opera")>0){
	   return "opera";
	  }else if(agent.indexOf("opera")>0){
	   return "opera";
	  }else if(agent.indexOf("firefox")>0){
	   return "firefox";
	  }else if(agent.indexOf("webkit")>0){
	   return "webkit";
	  }else if(agent.indexOf("gecko")>0 && agent.indexOf("rv:11")>0){
	   return "ie11";
	  }else{
	   return "Others";
	  }
	 }
	
	
	
	
	
	/**
	 * 说明
	 * 成功时返回协议主体包
	 * 
	 * @param content
	 * @return
	 */
	static public String getProtocol(Object content) {
		
		String code = String.valueOf(Global.ICE_OK);
		
		String message = "success";
		
		boolean show = false;
		
		return Global.getMyProtocol(code, message, show, content);
	}
	

	/**
	 * 说明
	 * 失败时弹窗协议返回
	 * 
	 * @author zhao
	 *
	 * @param code
	 * @param message
	 * @return
	 */
	
	static public String getProtocol(Integer code, String message) {
		
		boolean show = false;
		
		if(code != Global.ICE_OK) {
		
			show = true;
		}
		
		String content = "";
		
		return Global.getMyProtocol(String.valueOf(code), message, show,  content);
	}
	
	
	/**
	 * 说明 
	 * 
	 * 不管成功功失败，自定义是否弹窗
	 * 
	 * @param code
	 * @param message
	 * @param show
	 * @return
	 */
	static public String getProtocol(Integer code, String message, boolean show) {
		
		String content = "";
		
		return Global.getMyProtocol(String.valueOf(code), message, show,  content);
	}
	
 
	/**
	 * 
	 * 说明
	 * 完全定制
	 * 
	 * @param code
	 * @param message
	 * @param show
	 * @param content
	 * @return
	 */

	static public String getProtocol(Integer code, String message, boolean show, Object content) {
		
		return Global.getMyProtocol(String.valueOf(code), message, show, content);
	}
 
	
	
	static public String getMyProtocol(String code, String message, boolean show, Object content)  {
		
		JSONObject jsonObject = new JSONObject();
		
		String jsonString = "";
		
		jsonObject.put(Global.PROTOCOL_TYPE_KEY, Global.PROTOCOL_TYPE_CUSTOMER_VALUE);
		jsonObject.put(Global.PROTOCOL_CODE_KEY, code);
		
		JSONObject messagaJsonObject = new JSONObject();
		messagaJsonObject.put(Global.PROTOCOL_MESSAGE_INFO_KEY, message);
		messagaJsonObject.put(Global.PROTOCOL_MESSAGE_SHOW_KEY, show);
		
		jsonObject.put(Global.PROTOCOL_MESSAGE_KEY, messagaJsonObject);
		
		jsonObject.put(Global.PROTOCOL_CONTENT_KEY, content);
		
		jsonString = jsonObject.toJSONString();
		
		return jsonString;
	}
	
	
	
	static public String addCutmostContent(String protocol, String key, Object value) {

		String jsonString = "";
		
		if(protocol != null && !protocol.trim().equals("") && key != null && !key.trim().equals("") && value != null) {
				
			JSONObject jsonObject = JSONObject.parseObject(protocol);
			if(jsonObject != null) {
					 
				 JSONObject contentObject = jsonObject.getJSONObject(Global.PROTOCOL_CONTENT_KEY);
				 if(contentObject != null) {
					 
					 contentObject.put(key, value);
				 }
			 
			}
				
			jsonString = jsonObject.toJSONString();
		}
		
		return jsonString;
	}
	
	
	
	static public String cutmostMessage(String protocol, String info, boolean show) {
		
		String jsonString = Global.updateCustomerMessage(protocol, "show", show);
		
		jsonString = Global.updateCustomerMessage(jsonString, "info", info);
		
		return jsonString;
	}
	
	
	static public String updateIceMessage(String protocol, String value) {
		
		String jsonString = "";
		
		if(protocol != null && !protocol.trim().equals("") && value != null && !value.trim().equals("")) {
				
			JSONObject jsonObject = JSONObject.parseObject(protocol);
			if(jsonObject != null) {
				 
			   jsonObject.put(Global.PROTOCOL_MESSAGE_KEY, value);

			}
				
			jsonString = jsonObject.toJSONString();
		}
		
		return jsonString;
	}
	

	
	static public String updateCustomerMessage(String protocol, String key, Object value) {
		
		String jsonString = "";
		
		if(protocol != null && !protocol.trim().equals("") && value != null) {
				
			JSONObject jsonObject = JSONObject.parseObject(protocol);
			if(jsonObject != null) {
				
				JSONObject object = new JSONObject();
				object.put(key, value);
				
			   jsonObject.put(Global.PROTOCOL_MESSAGE_KEY, object);

			}
				
			jsonString = jsonObject.toJSONString();
		}
		
		return jsonString;
	}
	
	
	
	
	static public JSONObject getIceProtocolObject(String protocol) {
		
		return Global.getProtocolTypeObject(protocol, Global.PROTOCOL_TYPE_ICE_VALUE);
	}
	

	static public JSONObject getCustomerProtocolObject(String protocol) {
		
		return Global.getProtocolTypeObject(protocol, Global.PROTOCOL_TYPE_CUSTOMER_VALUE);
	}
	
	
	
	
	static public String getIceProtocolInfo(String protocol) {
		
		return Global.getProtocolTypeInfo(protocol, Global.PROTOCOL_TYPE_ICE_VALUE);
	}
	

	static public String getCustomerProtocolInfo(String protocol) {
		
		return Global.getProtocolTypeInfo(protocol, Global.PROTOCOL_TYPE_CUSTOMER_VALUE);
	}
	
	
	static public String getProtocolTypeInfo(String protocol, String type) {
		
		String resultJson = "";
		
		if(protocol != null && !protocol.trim().equals("") && type != null && !type.trim().equals("")) {
			
			JSONObject jsonObject = JSONObject.parseObject(protocol);
			if(jsonObject != null) {
				
				Integer t = jsonObject.getInteger("type");
				if(t == null) {
					
					jsonObject.put(Global.PROTOCOL_TYPE_KEY, type);
					
					resultJson = jsonObject.toJSONString();
				} else {
					
					resultJson = protocol;
				}
			}
		}
		
		return resultJson;
	}
	
	
	static public JSONObject getProtocolTypeObject(String protocol, String type) {
		
		JSONObject jsonObject = null;
		
		if(protocol != null && !protocol.trim().equals("") && type != null && !type.trim().equals("")) {
			
			jsonObject = JSONObject.parseObject(protocol);
			if(jsonObject != null) {
				
				jsonObject.put(Global.PROTOCOL_TYPE_KEY, type);
				
			}
		}
		
		return jsonObject;
	}
	
	
	
	
	
	static private boolean readConfig(String file) {
		
		Properties properties = new Properties();
		
		boolean ok = true;
		
         try{
             
             InputStream in = new BufferedInputStream (new FileInputStream(file));
             
             properties.load(in);     ///加载属性列表
             
             Iterator<String> it = properties.stringPropertyNames().iterator();
             
             while(it.hasNext()){
                 
            	 String key = it.next();
             String value = properties.getProperty(key);
             
                 if(key.equals(Global.ICE_ZZXT_NUMBER_KEY)			 	== true 		||
                		 key.equals(Global.CONTRACT_DOWNLOAD_TYPE_KEY)   		== true		||
                		key.equals(Global.ICE_ZZXT_CODE_KEY)			 		== true 		||
                		key.equals(Global.ICE_ZZXT_TOKEN_KEY) 				== true 		||
                		key.equals(Global.ICE_ZZXT_APPID_KEY) 				== true 		||
                		key.equals(Global.ICE_ZZXT_ZHONG_SHUI_APPID_KEY) 				== true 		||
                		key.equals(Global.ICE_ZZXT_ZHONG_SHUI_CLIENT_SECRET_KEY) 				== true 		||		
                		key.equals(Global.ICE_ZZXT_CLIENT_SECRET_KEY) 		== true 		||		
                		key.equals(Global.ICE_ZZXT_APP_UUID_KEY) 			== true 		||
                		key.equals(Global.ICE_ZZXT_APP_SECRET_KEY) 			== true 		||
                		key.equals(Global.ICE_ZZXT_API_URL_KEY) 	    			== true 		||
                		key.equals(Global.ICE_ZZXT_ZHONG_SHUI_API_URL_KEY) 	== true 		||
                		key.equals(Global.ICE_ZZXT_TIMESTAMP_URL_KEY) 		== true 		||		
                		key.equals(Global.SIGNATURE_SDK_URL_KEY) 			== true 		||                				
                		key.equals(Global.SIGNATURE_DEVID_KEY) 				== true 		||
                		key.equals(Global.SIGNATURE_PEM_KEY) 				== true		||
                		key.equals(Global.SIGNATURE_IS_FINGERKEY)			== true 		||
                		key.equals(Global.SERVER_PATH_KEY) 	 				== true    	||
                		key.equals(Global.MAX_PAGE_KEY) 						== true		||
                		key.equals(Global.ICE_ZZXT_V1_APPKEY_KEY) 			== true     ||
                		key.equals(Global.ICE_ZZXT_V1_APPSECRET_KEY) 			== true) {
                	 	
                	 	logger.info(key + ":" + value);
                	 
                	 	Global.configMap.put(key, value);
                 }
             }
             
             in.close();
         }

         catch(Exception e){

        	 	ok = false;
        	 	
        	 	logger.info(e.getLocalizedMessage());
         }
		
         return ok;
	}
	 
	

	/*
	 * key sessionId
	 * value accountUuid
	 * 
	 * 用户鉴权成功  将以sessionId为key 保存起来 
	 * 
	 */
	
	static  public void setUserInfoKey(String key, String accountUuid) {
		
	}

	
	static  public void setUserInfoKey(HttpSession session, String accountUuid) {
		
		if(session != null) {
			
			session.setAttribute("accountUuid", accountUuid);
		}
	}

	static public String getUserInfoKey(HttpSession session) {
		
		String accountUuid = "";
		
		if(session != null) {
			
			Object o = session.getAttribute("accountUuid");
			if(o != null) {
				
				accountUuid = o.toString();
			}
		}
		
		return accountUuid;
	}
	
	/*
	 *  以accountUuid为key为入参，返回对应的用户登录信息 map
	 *  
	 *  
	 *  @authore 赵玺翔
	 *  @param String accountUuid 用户登录信息key
	 *   
	 *
	 *  @Time 2017-11-25 15:30
	 *  
	 *  @return String 返回 name （ice oa用户实际姓名)
	 */
	static public String getName(String key) {
		
		Map<String, Object> map = Global.getUserInfoMap(key);
		
		String name = "";
		
		if(map != null) {
			
			name = map.get("name").toString();
		}
		
		return name;
	}
	

	/*
	 *  以accountUuid为key为入参，返回对应的用户登录信息 map
	 *  
	 *  
	 *  @authore 赵玺翔
	 *  @param String accountUuid 用户登录信息key
	 *   
	 *
	 *  @Time 2017-11-25 15:30
	 *  
	 *  @return String 返回 username （ice oa帐号)
	 */
	static public String getUserName(String key) {
		
		Map<String, Object> map = Global.getUserInfoMap(key);
		
		String name = "";
		
		if(map != null) {
			
			name = map.get("username").toString();
		}
		
		return name;
	}
	

	
	/*
	 *  以accountUuid为key为入参，返回自身，做为统一方法提供
	 *  
	 *  
	 *  @authore 赵玺翔
	 *  @param String key, accountUuid用户登录信息key
	 *   
	 *
	 *  @Time 2017-11-25 15:30
	 *  
	 *  @return String 返回 corpId （多租户id)
	 */
	static public String accountUuid(String key) {
		
		return key;
	}
	
	

	/*
	 *  以accountUuid为key为入参，返回对应的用户登录信息 map
	 *  
	 *  
	 *  @authore 赵玺翔
	 *  @param String accountUuid 用户登录信息key
	 *   
	 *
	 *  @Time 2017-11-25 15:30
	 *  
	 *  @return String 返回 corpId （多租户id)
	 */
	
	static public String getUserCorpId(String key) {
		
		Map<String, Object> map = Global.getUserInfoMap(key);
		
		String name = "";
		
		if(map != null) {
			
			name = map.get("corpId").toString();
		}
		
		return name;
	}
	

	
	/*
	 *  以accountUuid为key为入参，返回对应的用户登录信息 map
	 *  
	 *  
	 *  @authore 赵玺翔
	 *  @param String accountUuid 用户登录信息key
	 *   
	 *
	 *  @Time 2017-11-25 15:30
	 *  
	 *  @return String 返回 orgUuid
	 */

	static public String getUserOrgUuid(String key) {
		
		Map<String, Object> map = Global.getUserInfoMap(key);
		
		String name = "";
		
		if(map != null) {
			
			name = map.get("orgUuid").toString();
		}
		
		return name;
	}
	
	
	
	/*
	 *  以accountUuid为key为入参，返回对应的用户登录信息 map
	 *  
	 *  
	 *  @authore 赵玺翔
	 *  @param String accountUuid 用户登录信息key
	 *   
	 *
	 *  @Time 2017-11-25 15:30
	 *  
	 *  @return Map<String, Object>  用户登录成功实际信息存储
	 */
	static private Map<String, Object> getUserInfoMap(String key) {
		
		if(key == null || key.trim().equals("") == true) {
			
			return null;
		}
		
		return (Map<String, Object>) Global.loginInfoMap.get(key);
	}
	
	
	
	
	
	/*
	 *  在/Login 接口中如果登录成功，则需要取出accountUuid为key, 
	 *  员工信息 map 为value 进行用户信息保存
	 *  
	 *  @authore 赵玺翔
	 *  @param String accountUuid 用户登录信息key，  Map<String, Object> userInfoMap 用户登录成功实际信息存储
	 *   
	 *
	 *  @Time 2017-11-25 15:30
	 *  
	 *  @return
	 */
	static  public void setLoginInfo(String accountUuid, Map<String, Object> userInfoMap) {
		
		Global.loginInfoMap.put(accountUuid, userInfoMap);
	}
	
	static public void setAccountUuid(HttpSession session, String accountUuid) {
		
		if(session != null && accountUuid != null && !accountUuid.trim().equals("")) {
			
			session.setAttribute(Global.USER_INFO_ACCOUNT_UUID, accountUuid);
		}
	}
	
	
	
	
	/*
	 *  以accountUuid为key, 返回accessToken
	 *  
	 *  @authore 赵玺翔
	 *  @param String accountUuid 用户登录信息key 
	 *   
	 *  @Time 2017-12-12 00:44
	 *  
	 *  @return accessToken
	 */
	static  public String getAccessToken(String accountUuid) {
		
		String token = "";
		
		Map<String, Object> map = Global.getUserInfoMap(accountUuid);
		
		if(!Global.isNull(map)) {
			
			token = map.get(Global.AUTH_INFO_ACCESS_TOKEN).toString();
		}
		
		return token;	
	}
	
	
	/*
	 *  以accountUuid为key, 返回accessToken
	 *  
	 *  @authore 赵玺翔
	 *  @param String accountUuid 用户登录信息key 
	 *   
	 *  @Time 2017-12-12 00:44
	 *  
	 *  @return accessToken
	 */
	static public String getAccessTokenV1(String accountUuid) {
		
		String token = "";
		
		Map<String, Object> map = Global.getUserInfoMap(accountUuid);
		
		if(!Global.isNull(map)) {
			
			token = map.get(Global.AUTH_INFO_ACCESS_TOKEN_V1).toString();
		}
		
		return token;	
	}
	
	
	
	
//	/*
//	 *  在/Auth 接口中如果鉴权成功，则需要以accountUuid为key, 
//	 *   accessToken 为value 进行accessToken信息保存
//	 *  
//	 *  @authore 赵玺翔
//	 *  @param String accountUuid 用户登录信息key， String accessToken 
//	 *   
//	 *  @Time 2017-12-12 00:44
//	 *  
//	 *  @return
//	 */
//	static  public void setAccessToken(String accountUuid, String accessToken) {
//		
//		if(!Global.isNull(accountUuid) && !Global.isNull(accessToken)) {
//			
//			Global.loginInfoMap.put(accountUuid, accessToken);
//		}
//		
//	}

	
	
	static public JSONObject getJSONObject(String jsonString) {
		
		JSONObject jsonObject = null;
		
		if(jsonString != null && !jsonString.trim().equals("")) {
			
			jsonObject = JSONObject.parseObject(jsonString);
		}
		
		return jsonObject;
	}
	
	/*
	 *  以jsonObject为入参，返回ICE请求的信息码 code = 0 为成功
	 *  
	 *  
	 *  @authore 赵玺翔
	 *  @param JSONObject jsonObject  ICE请求返回信息json解析对象
	 *   
	 *
	 *  @Time 2017-12-9 21:00
	 *  
	 *  @return Integer 返回 code
	 */
	static public Integer getICECode(JSONObject jsonObject) {
		
		int code = Global.ICE_UNKNOW;
		
		if(jsonObject != null) {
			
			String c = jsonObject.getString("code");
			if(c != null && c.trim().equals("") == false) {
				
				code = Integer.valueOf(c);
			}
			
		}
		
		return code;
	}
	
	
	
	/*
	 *  以jsonObject为入参，返回ICE请求的信息 
	 *  
	 *  
	 *  @authore 赵玺翔
	 *  @param JSONObject jsonObject  ICE请求返回信息json解析对象
	 *   
	 *
	 *  @Time 2017-12-9 21:00
	 *  
	 *  @return String 返回 message  主是错误提示信
	 */
	static public String getICEMessage(JSONObject jsonObject) {
		
		String message = "";
		
		if(jsonObject != null) {
			
			message = jsonObject.getString("message");
		}
		
		return message;
	}
	
	
	/*
	 *  以jsonObject为入参，返回ICE请求的实际数据内容 
	 *  
	 *  
	 *  @authore 赵玺翔
	 *  @param JSONObject jsonObject  ICE请求返回信息json解析对象
	 *   
	 *
	 *  @Time 2017-12-9 21:00
	 *  
	 *  @return String 返回 content  主体信息json串
	 */
	static public String getICEContent(JSONObject jsonObject) {
		
		String content = "";
		
		if(jsonObject != null) {
			
			content = jsonObject.getString("content");
		}
		
		return content;
	}
	
	

	
	/*
	 *  以jsonString为入参，返回ICE请求的信息码 code = 0 为成功
	 *  
	 *  
	 *  @authore 赵玺翔
	 *  @param String jsonString ICE请求返回信息jsonString
	 *   
	 *
	 *  @Time 2017-12-9 21:00
	 *  
	 *  @return Integer 返回 code
	 */
	static public Integer getICECode(String jsonString) {
		
		int code = Global.ICE_UNKNOW;
		
		JSONObject jsonObject = Global.getJSONObject(jsonString);
		
		if(jsonObject != null) {
			
			String c = jsonObject.getString("code");
			if(c != null && c.trim().equals("") == false) {
				
				code = Integer.valueOf(c);
			}
		}
		
		return code;
	}
	
	
	/*
	 *  以jsonString为入参，返回ICE请求的错误提示信息 
	 *  
	 *  
	 *  @authore 赵玺翔
	 *  @param String jsonString  ICE请求返回信息json串
	 *   
	 *
	 *  @Time 2017-12-9 21:00
	 *  
	 *  @return String 返回 message  主是错误提示信
	 */
	static public String getICEMessage(String jsonString) {
		
		String message = "";
		
		JSONObject jsonObject = Global.getJSONObject(jsonString);
		
		if(jsonObject != null) {
			
			message = jsonObject.getString("message");
		}
		
		return message;
	}
	
	
	
	/*
	 *  以jsonString为入参，返回ICE请求的实际数据json串 
	 *  
	 *  
	 *  @authore 赵玺翔
	 *  @param String jsonString  ICE请求返回信息json串
	 *   
	 *
	 *  @Time 2017-12-9 21:00
	 *  
	 *  @return String 返回 content  主体数据json串
	 */
	static public String getICEContent(String jsonString) {
		
		String content = "";
		
		JSONObject jsonObject = Global.getJSONObject(jsonString);
		
		if(jsonObject != null) {
			
			content = jsonObject.getString("content");
		}
		
		return content;
	}
	
	
	/*
	 *  以jsonObject为入参，返回ICE请求的实际数据map
	 *  
	 *  
	 *  @authore 赵玺翔
	 *  @param String jsonString  ICE请求返回信息json串
	 *   
	 *
	 *  @Time 2017-12-9 21:00
	 *  
	 *  @return Map<String, Object>  返回 content  主体数据map
	 */
	static public Map<String, Object> getContentMap(JSONObject jsonObject) {
		
		String content = "";

		if(jsonObject != null) {
			
			content = jsonObject.getString("content");
		}

		Map<String, Object> contentMap = null;
		
		if(Global.isNull(content) == false) {
			
			contentMap = JSONObject.parseObject(content);
		}
		
		return contentMap;
	}
	
	
	/*
	 *  以jsonString为入参，返回ICE请求的实际数据map
	 *  
	 *  
	 *  @authore 赵玺翔
	 *  @param String jsonString  ICE请求返回信息json串
	 *   
	 *
	 *  @Time 2017-12-9 21:00
	 *  
	 *  @return Map<String, Object>  返回 content  主体数据map
	 */
	static public  Map<String, Object> getContentMap(String jsonString) {
		
		String content = "";
		
		JSONObject jsonObject = Global.getJSONObject(jsonString);
		
		if(jsonObject != null) {
			
			content = jsonObject.getString("content");
		}
		
		Map<String, Object> contentMap = null;
		
		if(Global.isNull(content) == false) {
			
			contentMap = JSONObject.parseObject(content);
		}
		
		return contentMap;
	}
	
	
	/*
	 *  以jsonString为入参，返回ICE请求的实际数据json对象
	 *  
	 *  
	 *  @authore 赵玺翔
	 *  @param String jsonString  ICE请求返回信息json串
	 *   
	 *
	 *  @Time 2017-12-9 21:00
	 *  
	 *  @return JSONObject  返回 content  主体数据json对象
	 */
	static public JSONObject getContentObject(String jsonString) {
		
		String content = Global.getICEContent(jsonString);
		
		JSONObject contentObject = null;
		if(content != null && content.trim().equals("") == false) {
			
			contentObject = Global.getJSONObject(content);
		}
		
		return contentObject;
	}
	
	
	
	
	/*
	 *  要求代码严谨， 返回对象要判空，帮尝试写一个统一判空方法
	 *  如果存在问题，请后续同事改，或完善
	 *  
	 *  @authore 赵玺翔
	 *  @param Object object 
	 *   
	 *
	 *  @Time 2017-12-9 21:00
	 *  
	 *  @return boolean true:空 false:非空
	 */
	
	static public boolean isNull(Object object) {
		
		boolean isNull = false;
		if(object == null) {
			
			isNull = true;
		} else {
			
			if(object.getClass().equals(String.class) == true) {
				
				if(object.toString().equals("") == true) {
					
					isNull = true;
				}
			} 
			
		}
		
		return isNull;
	}
	
	
	
	
	/*
	 * ICE数据请求返回验证
	 *  
	 *  @authore 赵玺翔
	 *  
	 *  @param String jsonString
	 *   
	 *
	 *  @Time 2017-12-9 21:00
	 *  
	 *  @return boolean true:成功 （code == 0)  false: 错值值
	 */
	
	static public boolean isICESuccessed(String jsonString) {
		
		boolean isSuccessed = false;
		int code = Global.getICECode(jsonString);
		
		if(code == Global.ICE_OK) {
			
			isSuccessed = true;
		}
		
		return isSuccessed;
	}
	
	
	
	/*
	 * ICE数据请求返回验证
	 *  
	 *  @authore 赵玺翔
	 *  
	 *  @param JSONObject jsonObject
	 *   
	 *
	 *  @Time 2017-12-9 21:00
	 *  
	 *  @return boolean true:成功 （code == 0)  false: 错值值
	 */
	
	static public boolean isICESuccessed(JSONObject jsonObject) {
		
		boolean isSuccessed = false;
		int code = Global.getICECode(jsonObject);
		
		if(code == Global.ICE_OK) {
			
			isSuccessed = true;
		}
		
		return isSuccessed;
	}
	

}
