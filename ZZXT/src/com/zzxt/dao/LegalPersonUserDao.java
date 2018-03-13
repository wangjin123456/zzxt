package com.zzxt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzxt.entity.LegalPersonUserEntity;

public interface LegalPersonUserDao {

	public List<LegalPersonUserEntity> userList(@Param("id")String id);
	
	public LegalPersonUserEntity legalPersonUser (@Param("id") String id);
	
	public LegalPersonUserEntity hasUser(@Param("orgUuid")String orgUuid, @Param("uid")Integer uid);
	
	public List<LegalPersonUserEntity> legalPersonUserList(@Param("accountUuid")String accountUuid);
	
	public Integer add(LegalPersonUserEntity legalPersonUserEntity);
	
	public Integer del(String id);
	
	public Integer delUser(String orgUuid, String uid);

	public Integer edit(LegalPersonUserEntity legalPersonUserEntity);
	
	
	public Integer updatePower(LegalPersonUserEntity legalPersonUserEntity);
	
	public Integer updateSub(LegalPersonUserEntity legalPersonUserEntity);
	
	public Integer getPower(String orgUuid, Integer uid);
	
	public Integer getSubPower(String orgUuid, Integer uid);
	
}
