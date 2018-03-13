package com.zzxt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzxt.entity.ESignatureEntity;

public interface ESignatureDao {
	
	public Integer count(@Param("orgUuid") String orgUuid);

	public List<ESignatureEntity> search(@Param("lpId")String lpId, @Param("skey") String skey, @Param("start") int start, @Param("end") int end);
	
	public List<ESignatureEntity> searchState(@Param("lpId")String lpId, @Param("state") int state);
	
	public List<ESignatureEntity> eSignatureAllList(@Param("lpId")String lpId);
	
	public List<ESignatureEntity> eSignatureList(@Param("lpId")String lpId, @Param("start") int start, @Param("end") int end);
	
	public ESignatureEntity eSignature (@Param("esId") String esId);
	
	public Integer add(ESignatureEntity esignature);
	
	public Integer del(String id);

	public Integer edit(ESignatureEntity esignature);

}
