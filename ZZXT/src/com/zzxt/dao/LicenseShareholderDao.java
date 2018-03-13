package com.zzxt.dao;


import org.apache.ibatis.annotations.Param;

import com.zzxt.entity.LicenseShareholderEntity;

public interface LicenseShareholderDao {

	public Integer add(LicenseShareholderEntity licenseShareholderEntity);
	
	public Integer del(@Param("id") Integer id);
	
	public Integer delShareholer(@Param("sid") Integer sid);
	
	public String shareHolderList(@Param("lid") Integer lid);

}
