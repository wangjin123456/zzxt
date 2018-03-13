package com.zzxt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzxt.bean.UserPowerBean;
import com.zzxt.entity.UserEntity;

public interface UserDao {

	public List<UserPowerBean> userList(@Param("orgUuid")String orgUuid);
	
	public UserEntity user (@Param("id") String id);
	
	public UserEntity getUser(@Param("accountUuid") String accountUuid);
	
	public Integer add(UserEntity user);
	
	public Integer add(String accountUuid, UserEntity user);
	
	public Integer del(String id);

	public Integer edit(UserEntity user);
	
}
