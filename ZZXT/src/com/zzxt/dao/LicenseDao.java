package com.zzxt.dao;


import org.apache.ibatis.annotations.Param;

import com.zzxt.entity.LicenseEntity;

public interface LicenseDao {

//	Integer insertLic(LicenseEntity lic);
//
//	LicenseEntity selectLicInfo(String orgUuid);
//
//	Integer getCount();
//
//	Integer deleteLic(Integer id);
//	
//	Integer updateInfo(LicenseEntity LicenseEntity);
//	
//	LicenseEntity getLic_ShaByLicenseId(Integer id);
	
	
	Integer add(LicenseEntity licenseEntity);

	LicenseEntity licenseInfo(@Param("orgUuid")String orgUuid);

	Integer getCount();

	Integer del(Integer id);
	
	Integer edit(LicenseEntity LicenseEntity);
	
	
	LicenseEntity reminderInfo(@Param("orgUuid")String orgUuid);
	
	Integer updateReminder(LicenseEntity LicenseEntity);
	
}
