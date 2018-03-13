package com.zzxt.service;

import java.util.List;
import java.util.Map;

import com.zzxt.bean.UserPowerBean;

public interface UserPowerService {
	


	
	/*
	 * @Auth 休闲
	 * @Date 2017-11-26 12:57
	 * @Param accountUuid
	 * @Desc  获取用户权限信息，分子极权限列表，证照信息：1 电子签章：2  证照信息和电子签章：3 
	 */
	public Map<String, Object> userPowerInfo(String accessToken, String accountUuid);
	/*
	 * @Auth 休闲
	 * @Date 2017-11-26 12:57
	 * @Param accountUuid
	 * @Desc  获取超级用户权限信息，分子极权限列表，证照信息：1 电子签章：2  证照信息和电子签章：3 
	 */
	//public Map<String, Object> superUserPowerInfo(Map<String,String>map, String orgUuid);
	
	public Map<String, Object> superUserPowerInfo(String accessToken, String orgUuid);
	
	/*
	 * @Auth 休闲
	 * @Date 2017-11-27 10:44
	 * @Param orgUuid 
	 * @Desc  传入法人架构id显示相关的用户列表
	 */
	public List<UserPowerBean> showUsers(String orgUuid);
	
	
	
//	/*
//	 * @Auth 休闲
//	 * @Date 2017-11-27 10:44
//	 * @Param accountUuid orgUuid users
//	 * @Desc  导入用户列表时也添加法人架构和当前用户列表关联；添加用户权限默认值，以后权限部分进行更新（修改）即可
//	 */
//	public void addUsers(String orgUuid, List<UserEntity> users);
	

	/*
	 * @Auth 休闲
	 * @Date 2017-11-27 10:44
	 * @Param accountUuid orgUuid users
	 * @Desc  导入用户列表时也添加法人架构和当前用户列表关联；添加用户权限默认值，以后权限部分进行更新（修改）即可
	 */
	public int addUsers(String orgUuid, String users);
	
	/*
	 * @Auth 休闲
	 * @Date 2017-11-27 10:44
	 * @Param orgUuid users
	 * @Desc 从法人架构关联中移除用户列表
	 */
	public boolean removeUsers(String orgUuid, List<String> ids);
	
	
	
	
	/* @Auth 休闲
	 * @Date 2017-11-26 12:57
	 * @Param accountUuid power
	 * @Desc  设置用户权限信息，证照信息：1 电子签章：2  证照信息和电子签章：3 
	 */
	public boolean updatePower(String orgUuid, Integer uid, Integer power);
	
	/*
	 * @Auth 休闲
	 * @Date 2017-11-26 12:57
	 * @Param accountUuid power
	 * @Desc  设置用户级权限信息  子级有权：true 子级无权：false 
	 */
	public boolean updateSubPower(String orgUuid, Integer uid, Integer sub);


	
	public String doSearchUser(String accessToken, String orgUuid, String user);
//
//	/*
//	 * @Auth 休闲
//	 * @Date 2017-11-26 15:37
//	 * @Param accountUuid power
//	 * @Desc  获取用户权限信息，证照信息：1 电子签章：2  证照信息和电子签章：3 
//	 */
//	public Integer getPower(String orgUuid, Integer uid);
//	
//	/*
//	 * @Auth 休闲
//	 * @Date 2017-11-26 15:37
//	 * @Param accountUuid power
//	 * @Desc  获取用户级权限信息  子级有权：true 子级无权：false 
//	 */
//	public Integer getSubLevel(String orgUuid, Integer uid);
}

