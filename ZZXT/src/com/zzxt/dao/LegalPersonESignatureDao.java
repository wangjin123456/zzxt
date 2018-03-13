package com.zzxt.dao;


import org.apache.ibatis.annotations.Param;

import com.zzxt.entity.LegalPersonESignatureEntity;

public interface LegalPersonESignatureDao {

	public Integer add(LegalPersonESignatureEntity legalPersonESignatureEntity);
	
	
	public LegalPersonESignatureEntity getLegalPersonESignatureEntity(@Param("esId") String esId);
	
	
	public Integer del(@Param("esId") String esId);
}
