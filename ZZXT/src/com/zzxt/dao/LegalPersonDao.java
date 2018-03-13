package com.zzxt.dao;

import org.apache.ibatis.annotations.Param;

import com.zzxt.entity.LegalPersonEntity;

public interface LegalPersonDao {
	
	Integer add(LegalPersonEntity licenseEntity);

	LegalPersonEntity legalPersonInfo(@Param("id") Integer id);

	Integer del(Integer id);
	
	Integer edit(LegalPersonEntity LicenseEntity);
	
}
