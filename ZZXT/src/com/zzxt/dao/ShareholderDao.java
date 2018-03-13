package com.zzxt.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzxt.entity.ShareholderEntity;

/**
 * 股东信息数据层
 * @author think
 *
 */
public interface ShareholderDao {
	
	
	Integer add(ShareholderEntity shareholderEntity);

	ShareholderEntity shareholderInfo(String orgUuid);
	
	List<ShareholderEntity> shareHolderList(@Param("lid") Integer lid);

	Integer getCount();

	Integer del(Integer id);
	
	Integer edit(ShareholderEntity shareholderEntity);
	
	
	
//	/**
//	 * 添加
//	 * @param shareholder
//	 * @return
//	 */
//	Integer addSharInfo(Shareholder shareholder);
//	/**
//	 * 修改
//	 * @param id
//	 * @return
//	 */
//	Integer updateSharInfo(Shareholder shareholder);
//	/**
//	 * 批量添加
//	 * @param shareholder
//	 * @return
//	 */
//	Integer insertSharInfoList(List<Shareholder> shareholder);
////	/**
////	 *根据执照ID查询
////	 * @param 
////	 * @return
////	 */
////	List<Shareholder> selectSharInfos(Integer licenseId);
////	
////	/**
////	 * 根据执照ID统计数量
////	 */
////	Integer getSharCounts(Integer licenseId);
//	
//	Shareholder selectSharInfo(Integer id);
//	/**
//	 * 删除指定信息
//	 */
//	Integer deleteShar(Integer id);
//	
//	/**
//	 * 删除指定信息
//	 */
//	Integer deleteSharById(List<Integer>id);
//	
//	List<Shareholder>getShareholdersByLicenseId(Integer licenseId);
}
