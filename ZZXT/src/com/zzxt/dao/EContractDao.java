package com.zzxt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzxt.entity.EContractEntity;

public interface EContractDao {

	
	public List<EContractEntity> search(@Param("lpId")String lpId, @Param("skey") String skey, @Param("start") int start, @Param("end") int end);
	
	public List<EContractEntity> searchState(@Param("lpId")String lpId, @Param("state") int state, @Param("start") int start, @Param("end") int end);
	
	public List<EContractEntity> eContractList(@Param("lpId")String lpId);
	
	public List<EContractEntity> eContractList(@Param("lpId")String lpId, @Param("start") int start, @Param("end") int end);
	
	
	public EContractEntity eContract (@Param("ecId") String ecId);
	
	public Integer count(@Param("orgUuid") String orgUuid);
	
	public Integer add(EContractEntity econtract);
	
	public Integer del(String id);

	public Integer edit(EContractEntity econtract);
	

}
