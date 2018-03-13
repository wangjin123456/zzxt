package com.zzxt.dao;


import org.apache.ibatis.annotations.Param;

import com.zzxt.entity.LicenseLegalPersonEntity;

public interface LicenseLegalPersonDao {

	public Integer add(LicenseLegalPersonEntity licenseLegalPersonEntity);
	
	// 只关联一次就有效 不需要更新
	//public Integer edit(LicenseLegalPersonEntity licenseLegalPersonEntity);
	
	
	public Integer del(@Param("id") Integer id);
	
}
