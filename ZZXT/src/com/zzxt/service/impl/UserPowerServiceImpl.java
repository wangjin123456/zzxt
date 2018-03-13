package com.zzxt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzxt.bean.UserPowerBean;
import com.zzxt.dao.LegalPersonUserDao;
import com.zzxt.dao.UserDao;
//import com.zzxt.dao.UserPowerDao;
import com.zzxt.entity.LegalPersonUserEntity;
import com.zzxt.entity.UserEntity;
//import com.zzxt.entity.UserPowerEntity;
import com.zzxt.service.UserPowerService;
import com.zzxt.servicehttp.OrganizationServiceHttp;
import com.zzxt.util.Global;
import com.zzxt.util.IOkHttpUtil;
import com.zzxt.util.Md5Util;
import com.zzxt.util.TimestampUtil;

@Service
public class UserPowerServiceImpl implements UserPowerService {

	private Logger logger = Logger.getLogger(ContractServiceImpl.class);
	
//	@Autowired
//	UserPowerDao userPowerDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	LegalPersonUserDao legalPersonUserDao;
	
	@Autowired
	OrganizationServiceHttp organizationServiceHttp;
	
	/*
	 * @Auth 休闲
	 * @Date 2017-11-26 12:57
	 * @Param accountUuid
	 * @Desc  获取用户权限信息，分子极权限列表，证照信息：1 电子签章：2  证照信息和电子签章：3 
	 */
	
	@Override
	public Map<String, Object> userPowerInfo(String accessToken, String accountUuid) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		Map<String, Object> powerInfoMap = new HashMap<>();
		
		// 法人架构列表
		List<LegalPersonUserEntity> list = legalPersonUserDao.legalPersonUserList(accountUuid);
 		
		String orgUuids = "";
		for(LegalPersonUserEntity legalPersonUser : list) {
			
			String orgUuid = legalPersonUser.getOrgUuid();
			orgUuids += orgUuid;
			orgUuids += ",";
			
			Map<String, Object> powerValueMap = new HashMap<>();
			int sub = legalPersonUser.getSub();
			int power = legalPersonUser.getPower();
			
			powerValueMap.put("sub", sub);
			powerValueMap.put("power", power);
			
			powerInfoMap.put(orgUuid, powerValueMap);
						
		}
		
		if(orgUuids.trim().equals("") == false) {
			
			orgUuids = orgUuids.substring(0, orgUuids.length() - 1);
		}
		
		String legalPersons = organizationServiceHttp.getUserOrgList(accessToken, orgUuids);
		
		logger.info("\n---------一般用户证照树形列表--------\n" + legalPersons);
		
		logger.info("\n---------结束--------\n");
		
		resultMap.put("legalPersons", legalPersons);
		resultMap.put("powerInfo", powerInfoMap);
		resultMap.put("super", "false");
		
		logger.info("一般用户权限信息返回:" + resultMap.toString());
		
		return resultMap;
	}
	
	@Override
	public Map<String, Object> superUserPowerInfo(String accessToken, String orgUuid) {
		
		Map<String, Object> resultMap = new HashMap<>();
 
		String jsonResult = organizationServiceHttp.getOrgTresJson(accessToken, orgUuid, Global.STRUCTURE_TYPE_LEGALPERSON);
		
		logger.info("\n---------超级管理员证照树形列表--------\n" + jsonResult);
		
		logger.info("\n---------结束--------\n");
		
		
		Map<String, Object> powerInfoMap = new HashMap<>();
		
		Map<String, Object> powerValueMap = new HashMap<>();
		
		powerValueMap.put("sub", "1");
		powerValueMap.put("power", "3");
		powerInfoMap.put("superUser", powerValueMap);
		
		resultMap.put("legalPersons", jsonResult);
		resultMap.put("powerInfo", powerInfoMap);
		resultMap.put("super", "true");
		return resultMap;
	}
	
	
	private void addLegalPersonUser(String orgUuid, Integer uid, Integer sub, Integer power) {
		
		LegalPersonUserEntity legalPersonUser = new LegalPersonUserEntity();
		legalPersonUser.setUid(uid);
		legalPersonUser.setOrgUuid(orgUuid);
		legalPersonUser.setSub(sub);
		legalPersonUser.setPower(power);
		
		legalPersonUserDao.add(legalPersonUser);
	}
	
	
//	private void addUserPower(String orgUuid, Integer sub, Integer power) {
//		
//		UserPowerEntity userPowerEntity = new UserPowerEntity();
//		userPowerEntity.setOrgUuid(orgUuid);
//		userPowerEntity.setSub(sub);
//		userPowerEntity.setPower(power);
//				
//		userPowerDao.add(userPowerEntity);
//	}
//	
	
	
	/*
	 * @Auth 休闲
	 * @Date 2017-11-27 10:44
	 * @Param orgUuid 
	 * @Desc  传入法人架构id显示相关的用户列表
	 */
	@Override
	public List<UserPowerBean> showUsers(String orgUuid) {
		
		List<UserPowerBean> resultInfo = userDao.userList(orgUuid);
		
		logger.info("showUsers result:" + resultInfo.toString());
		
		return resultInfo;
		
	}
	
	
	
//	/*
//	 * @Auth 休闲
//	 * @Date 2017-11-27 10:44
//	 * @Param accountUuid orgUuid users
//	 * @Desc  导入用户列表时也添加法人架构和当前用户列表关联；添加用户权限默认值，以后权限部分进行更新（修改）即可
//	 */
//	public void addUsers(String orgUuid, List<UserEntity> users){
//		
//		for(UserEntity user : users) {
//			
//			// 存用户信息
//			Integer uid = userDao.add(user);
//			if(uid > 0) {
//				
//				// 存用户_法人关联表 
//				this.addLegalPersonUser(orgUuid, uid, "");
//				
//				// 存用户_权限表 
//				this.addUserPower(uid, Global.SUB_LEVEL_FALSE, Global.USER_POWER_LICENSE, "");
//			}
//		}
//	}
	
	/*
	 * @Auth 休闲
	 * @Date 2017-11-27 10:44
	 * @Param orgUuid users
	 * @Desc  导入用户列表时也添加法人架构和当前用户列表关联；添加用户权限默认值，以后权限部分进行更新（修改）即可
	 */
	@Override
	public int addUsers(String orgUuid, String users){
		
		if(orgUuid == null || orgUuid.trim().equals("") == true || users == null || users.trim().equals("") == true) {
			
			logger.info("addUsers orgUuid == null or users == null");

			return Global.FAILD;
		}
		
		users = users.replace(Global.AONEA, "[");
		users = users.replace(Global.ATWOA, "]");
		users = users.replace(Global.ATHRA, "{");
		users = users.replace(Global.AFURA, "}");
				
		logger.info("当前要添加的用户信息：orgUuid:" +  orgUuid + "\nuser:" + users);

		JSONArray jsonList = JSONArray.parseArray(users);
		int len = jsonList.size();
		
		logger.info("当前要添加的用户信息：orgUuid:" +  orgUuid + "\n users len:" + len);

		for(int index = 0; index < len; index ++) {
			
		
			JSONObject jsonObject = jsonList.getJSONObject(index);
			if(jsonObject != null) {
						
				// 转换为用户实体类
				UserEntity user=(UserEntity) JSONObject.toJavaObject(jsonObject, UserEntity.class);
				
				UserEntity hasUser = null;
				String accountUuid = user.getAccountUuid();
				
				// 判断用户是否已经存在
				if(accountUuid != null) {
					hasUser = userDao.getUser(accountUuid);
				}
				
				//  用户已经存在，不需要添加新用户，但可能需要关联权限
				if(hasUser != null) {
					
					logger.info("Add users userAccountUuid:" + accountUuid + "已经存在, 不继续添加，但要进行测试是否需要关联");
										
					Integer uid = hasUser.getId();
					LegalPersonUserEntity legalPersonUserEntity = legalPersonUserDao.hasUser(orgUuid, uid);
					if(legalPersonUserEntity != null) {
						
						String hasOrgUuid = legalPersonUserEntity.getOrgUuid();
						if(hasOrgUuid.equals(orgUuid) == true) {
							
							logger.info("AddUsers UserAccountUuid:" + accountUuid + "已经存在, 不继续添加，也不需要进行关联");
						}
						else // 这里处理关联权限
						{
							// 存用户_法人关联表 
							this.addLegalPersonUser(orgUuid, uid, Global.SUB_LEVEL_FALSE, Global.USER_POWER_LICENSE);
							
							logger.info("Add users and connectioned");
						}
					}
					else 
					{
						// 存用户_法人关联表 
						this.addLegalPersonUser(orgUuid, uid, Global.SUB_LEVEL_FALSE, Global.USER_POWER_LICENSE);
						
						logger.info("Add users and connectioned");
					}
				}
				else // 用户不存需要添加新用户并关联权限
				{
					Integer result = userDao.add(user);
					if(result > 0)  {
						logger.info("AddUsers Added");
						
						Integer uid = user.getId();
						if(uid != null) {
							
							// 存用户_法人关联表 
							this.addLegalPersonUser(orgUuid, uid, Global.SUB_LEVEL_FALSE, Global.USER_POWER_LICENSE);
							
							logger.info("Add users and connectioned");
//							
//							    if(uid != null) {
//							    	
//									// 存用户_法人关联表 
//									this.addLegalPersonUser(orgUuid, uid, Global.SUB_LEVEL_FALSE, Global.USER_POWER_LICENSE);
//									
//									logger.info("Add users and connectioned");
//							    }
						}
					}
				}
				
			}

		}
		
		return Global.SUCCESS0;
	}


	/*
	 * @Auth 休闲
	 * @Date 2017-11-27 10:44
	 * @Param orgUuid users
	 * @Desc 从法人架构关联中移除用户列表
	 */
	
	@Override
	public boolean removeUsers(String orgUuid, List<String> ids) {
		
		boolean ok = false; int r = 0;
		for(String uid : ids) {
			
			// 先删除用户信息
			r = userDao.del(uid);
			if(r > 0) {
				ok = true;
			}
			
			// 删除法人与用户关联信息
			r = legalPersonUserDao.delUser(orgUuid, uid);
			if(r > 0) {
				 ok = true;
			}
			
			// 删除用户权限信息
//			r = userPowerDao.del(uid);
//			if(r > 0) {
//				ok = true;
//			}
		}
		
		return ok;
	}
	
	
	
	
	/* @Auth 休闲
	 * @Date 2017-11-26 12:57
	 * @Param accountUuid power
	 * @Desc  设置用户权限信息，证照信息：1 电子签章：2  证照信息和电子签章：3 
	 */
	
	@Override
	public boolean updatePower(String orgUuid, Integer uid, Integer power){
		
		boolean ok = false;
		
		LegalPersonUserEntity userPower = new LegalPersonUserEntity();
		userPower.setOrgUuid(orgUuid);
		userPower.setUid(uid);
		userPower.setPower(power);
		
		int r = legalPersonUserDao.updatePower(userPower);
		if(r > 0) {
		
			ok = true;
		}
		
		return ok;
	}
	
	/*
	 * @Auth 休闲
	 * @Date 2017-11-26 12:57
	 * @Param accountUuid power
	 * @Desc  设置用户级权限信息  子级有权：true 子级无权：false 
	 */
	
	@Override
	public boolean updateSubPower(String orgUuid, Integer uid, Integer sub){
		
		boolean ok = false;
		
		LegalPersonUserEntity userPower = new LegalPersonUserEntity();
		userPower.setOrgUuid(orgUuid);
		userPower.setUid(uid);
		userPower.setSub(sub);
		
		int r =  legalPersonUserDao.updateSub(userPower);
		if(r > 0) {
			
			ok = true;
		}
		
		return ok;
	}


	@Override
	public String doSearchUser(String accessToken, String orgUuid, String user){
		
		String userInfo = "";
		boolean ok = this.isCZYCustomer(user);
		if(ok == true) {
			
			userInfo = this.getCZYUserInfo(user);
			
		} else {
			
			userInfo = this.organizationServiceHttp.getOrgUsers(accessToken, orgUuid, user);
		}
		
		return userInfo;
	}
	

	/**
	   * 验证手机号码
	   * @param mobiles
	   * @return
	   */
	
	public boolean checkMobileNumber(String mobileNumber){
	
		boolean flag = false;
		
		try{

			Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
			Matcher matcher = regex.matcher(mobileNumber);
			
			flag = matcher.matches();
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		return flag;
	}
	
	
	public boolean isCZYCustomer(String customer) {
		
		boolean isCheck = false;
		
		boolean isPhone = this.checkMobileNumber(customer);
		
		if(isPhone == false){
			
			return false;
		}
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		String url = apiURL + "czyprovide/customer/checkRegister";
		

		// 封装 请求参数
		HashMap<String, String> paramMap = new HashMap<>();
		
		String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);

		String ts = TimestampUtil.getTs();
		paramMap.put("ts", ts);
		paramMap.put("sign", Md5Util.signMd5(ts));
		paramMap.put("appID", appId);
		paramMap.put("mobile", customer);
		
		
		String jsonResult = IOkHttpUtil.sendGet(url, paramMap);

		boolean ok = Global.isICESuccessed(jsonResult);
		if(ok == true) {
			
			String content = Global.getICEContent(jsonResult);
			
			logger.info("isCZYCustomer.content" + content);
			
			Integer status = 0;
			if(content != null && !content.trim().equals("")) {
				
				JSONObject contentOjbect = Global.getContentObject(jsonResult);
				
				logger.info("isCZYCustomer.contentOjbect" + contentOjbect.toJSONString());
				
				String OK = contentOjbect.getString("ok");
				
				status = Integer.parseInt(OK);
			}
				
			if(status == 1) {
				
				isCheck = true;
			}
		}
		
		return isCheck;
	}
	
	
	
	public String getCZYUserInfo(String user) {
		

		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		String url = apiURL + "czyprovide/customer/getinfo";
		

		// 封装 请求参数
		HashMap<String, String> paramMap = new HashMap<>();
		
		String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);

		String ts = TimestampUtil.getTs();
		paramMap.put("ts", ts);
		paramMap.put("sign", Md5Util.signMd5(ts));
		paramMap.put("appID", appId);
		paramMap.put("mobile", user);
		
		String jsonResult = IOkHttpUtil.sendGet(url, paramMap);

		logger.info("getCZYUserInfo(" + user +") jsonResult =>" + jsonResult);
		
		return jsonResult;
	}
	
//	/*
//	 * @Auth 休闲
//	 * @Date 2017-11-26 15:37
//	 * @Param accountUuid power
//	 * @Desc  获取用户权限信息，证照信息：1 电子签章：2  证照信息和电子签章：3 
//	 */
//	public Integer getPower(String orgUuid, Integer uid) {
//		
//		Integer power = legalPersonUserDao.getPower(orgUuid, uid);
//		
//		return power;
//
//	}
//	
//	/*
//	 * @Auth 休闲
//	 * @Date 2017-11-26 15:37
//	 * @Param accountUuid power
//	 * @Desc  获取用户级权限信息  子级有权：true 子级无权：false 
//	 */
//	public Integer getSubLevel(String orgUuid, Integer uid) {
//		
//		Integer sub = legalPersonUserDao.getSubPower(orgUuid, uid);
//		
//		return sub;
//	}
}
