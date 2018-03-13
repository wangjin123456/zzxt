/**   
* @Title: LicenseServiceDao.java 
* @Package com.zzxt.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  wangdekun   
* @date 2017年9月5日 下午3:08:08 
* @version V1.0   
*/
package com.zzxt.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzxt.entity.LegalPersonEntity;
import com.zzxt.entity.LicenseEntity;
import com.zzxt.entity.ShareholderEntity;

/**
 * @ClassName: LicenseServiceDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wangdekun
 * @date 2017年9月5日 下午3:08:08
 * 
 */
public interface LicenseService {

	public Map<String,Object> getLicenseMap(String accessToken, String orgUuid);

	// 添加 更新工商信息
	Integer updateLicense(LicenseEntity licenseEntity);
	
	// 更新工商信息
	Integer updateReminder(LicenseEntity licenseEntity);
		
	
	// 传入法人构架 获取一条工商信息
	LicenseEntity getLicenseInfo(String orgUuid);

	// 添加 更新法人信息
	Integer updateLegalPerson(LegalPersonEntity legalPerson);
	
	Integer saveLicenseLegalperson(LicenseEntity licenseEntity, LegalPersonEntity legalPerson);
	
	
	// 通过工商信息id找到法人信息
	LegalPersonEntity getLegalPersonInfo(Integer lid);
	
	// 保存 工商和法人关联信息 
	Integer saveLicenseLegalPerson(int lid, int lpId);
	
	// 添加 更新 股东信息
	Integer updateShareHolderInfo(ShareholderEntity shareholder);
	
	// 保存 工商和股东信息关联信息 
	Integer saveLicenseShareHolder(int lid, int sid);
	
	// 传入工商信息id返回相关的股东信息列表
	String getShareHolderList(Integer lid);
	
	// 传入工商信息id返回相关的股东信息列表
	List<ShareholderEntity> shareHolderList(String lid);
		
	
	// 获取单条股东信息 传入股东信息id
	ShareholderEntity shareholderInfo(Integer id);
	
	// 传入股东信息id 删除一条股东信息
	Integer delSharInfo(Integer id);
}
