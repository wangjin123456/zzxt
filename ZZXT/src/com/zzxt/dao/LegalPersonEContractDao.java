package com.zzxt.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzxt.entity.LegalPersonEContractEntity;

public interface LegalPersonEContractDao {

	public Integer add(LegalPersonEContractEntity legalPersonEContractEntity);
	
	// 传入合同id 返回关联者id, 但不等于 lpId(本身)
	public List<LegalPersonEContractEntity> orgIdList(@Param("ecId") String ecId, @Param("lpId") String lpId);
	
	// 通过合同id查找所有关联数据
	public List<LegalPersonEContractEntity> getSignerList(@Param("ecId") String ecId);
	
	public List<LegalPersonEContractEntity> getLegalPersonEContractEntityList(@Param("ecId") String edId);
	
	// 返回单条关联
	public LegalPersonEContractEntity getLegalPersonEContractEntity(@Param("id") String id);
	
	// 通过合同id和法人架构id 查找关联信息，用于判断当前联是否已经存在
	public List<LegalPersonEContractEntity> hasSigner(@Param("ecId") String ecId, @Param("lpId") String lpId);
	
	// 传入本身id进行编辑 
	public void edit(@Param("id") String id);
	
	// 传入ecId 和法人架构lpId 进行编辑
	public void editState(LegalPersonEContractEntity legalPersonEContractEntity);
	
	public Integer del(@Param("id") String id);
	
}
