package com.zzxt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzxt.bean.ManageOrgVo;
import com.zzxt.dao.LegalPersonDao;
//import com.zzxt.dao.Lic_Sha_MiddleDao;
import com.zzxt.dao.LicenseDao;
import com.zzxt.dao.LicenseLegalPersonDao;
import com.zzxt.dao.LicenseShareholderDao;
import com.zzxt.dao.ShareholderDao;
import com.zzxt.entity.LegalPersonEntity;
//import com.zzxt.entity.Lic_Sha_Middle;
import com.zzxt.entity.LicenseEntity;
import com.zzxt.entity.LicenseLegalPersonEntity;
import com.zzxt.entity.LicenseShareholderEntity;
import com.zzxt.entity.ShareholderEntity;
import com.zzxt.service.LicenseService;
import com.zzxt.servicehttp.OrganizationServiceHttp;
import com.zzxt.util.JsonCast;
import com.zzxt.util.MangerOrgUtil;

import cn.bestsign.mixed.sdk.integration.logger.Logger;

/**
 * 
 * @ClassName: LicenseServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wangdekun
 * @date 2017年9月5日 下午3:11:16
 *
 */
@Service("LicenseService")
public class LicenseServiceImpl implements LicenseService {

	@Autowired
	LicenseDao licenseDao;
	
	@Autowired
	ShareholderDao shareholderDao;
	
	//@Autowired
	//Lic_Sha_MiddleDao lic_Sha_MiddleDao;
	
	@Autowired
	LicenseLegalPersonDao licenseLegalPersonDao;
	
	@Autowired
	LicenseShareholderDao licenseShareholderDao;
	
	
	@Autowired
	private LegalPersonDao legalPersonDao;
	
	@Autowired
	OrganizationServiceHttp organizationServiceHttp;
	

	@Override
	public Map<String,Object> getLicenseMap(String accessToken, String orgUuid) {
		
	LicenseEntity licenseInfo =  licenseDao.licenseInfo(orgUuid);
		
		Map<String,Object>map = new HashMap<String,Object>();
		
		//查询法人架构详细信息返回页面
		String org = organizationServiceHttp.getOrg(accessToken, orgUuid);
		
		JSONObject jsonObject = JSONObject.parseObject(org);
		map.put("legalOrg", jsonObject);
		
		
		if(licenseInfo != null) {
			
			//将组织架构orgUuid放入
			String manageOrgUuid = licenseInfo.getManageOrgUuid();
			map.put("manageOrgUuid", manageOrgUuid);
			
			Integer lid = licenseInfo.getId();
			
			//获取执照的法人代表信息
			LegalPersonEntity legalPerson = legalPersonDao.legalPersonInfo(lid);
			
			//获取执照下的所有股东信息
			List<ShareholderEntity>shareholders = shareholderDao.shareHolderList(lid);

			map.put("licList", licenseInfo);
			map.put("legalPerson", legalPerson);
			map.put("shareholders", shareholders);
			
		}
		
		return map;
		
	}
	
	
//	@Override
//	public Map<String,Object> getLicenseMap(String orgUuid) {
//		
//		LicenseEntity licenseInfo =  licenseDao.licenseInfo(orgUuid);
//		
//		Map<String,Object>map = new HashMap<String,Object>();
//		
//		//查询法人架构详细信息返回页面
//		Map<String, String> param=new HashMap<>();
//		
//		param.put("orgUuid", orgUuid);
//		
//		String org = organizationServiceHttp.getOrg(param);
//		
//		JSONObject jsonObject = JSONObject.parseObject(org);
//		map.put("legalOrg", jsonObject);
//		
//		
//		if(licenseInfo!=null) {
//			
//			//将组织架构orgUuid放入
//			String manageOrgUuid = licenseInfo.getManageOrgUuid();
//			map.put("manageOrgUuid", manageOrgUuid);
//			
//			Integer lid = licenseInfo.getId();
//			
//			//获取执照的法人代表信息
//			LegalPersonEntity legalPerson = legalPersonDao.legalPersonInfo(lid);
//			
//			//获取执照下的所有股东信息
//			List<ShareholderEntity>shareholders = shareholderDao.shareHolderList(lid);
//
//			map.put("licList", licenseInfo);
//			map.put("legalPerson", legalPerson);
//			map.put("shareholders", shareholders);
//			
//		}
//		
//		return map;
//	}


	@Override
	public Integer updateLicense(LicenseEntity licenseEntity) {
		
		licenseEntity.setAddTime(new Date());
		licenseEntity.setUpdateTime(new Date());
		
		Integer id = licenseEntity.getId();
		if(id != null) {
			
			return licenseDao.edit(licenseEntity);
			
		} else {
		
			return licenseDao.add(licenseEntity);
		}
	}
	
	@Override
	public Integer updateReminder(LicenseEntity licenseEntity) {
		
		Integer id = licenseEntity.getId();
		if(id == null) {
			
			return 0;
		}
		
		return licenseDao.updateReminder(licenseEntity);
	}
	
	public Integer saveLicenseLegalperson(LicenseEntity licenseEntity, LegalPersonEntity legalPersonEntity) {
		
		int result = 0;
		
		if(licenseEntity != null && legalPersonEntity != null) {
			
			licenseEntity.setAddTime(new Date());
			licenseEntity.setUpdateTime(new Date());
			
			// 获取工商信息id
			Integer lid = licenseEntity.getId();
			Integer lpId = 0;
			
			// id不存在需要增加操作
			if(lid == null || lid == 0) {
				
				// 添加工商信息
				result = licenseDao.add(licenseEntity);
				// 添加成功
				if(result > 0) {
					
					// 添加成功取出工商信息id
					lid = licenseEntity.getId();
					if(lid != null || lid > 0) {
						
						// 添加操作，也需要添加法人信息
						result = legalPersonDao.add(legalPersonEntity);
						
						// 添加成功 取 lpid 进行关联操作
						if(result > 0) {
							
							// 添加成功，取出法人信息id
							lpId = legalPersonEntity.getId();
							
							// 添加工商信息与法人关联
							LicenseLegalPersonEntity licenseLegalPersonEntity = new LicenseLegalPersonEntity();
							
							licenseLegalPersonEntity.setLid(lid);
							licenseLegalPersonEntity.setLpId(lpId);
							
							result = licenseLegalPersonDao.add(licenseLegalPersonEntity);
							
						}
					}
					
				}
			}
			else { // 这边统一更新操作
			
				// 更新工商信息
				result = licenseDao.edit(licenseEntity);
				// 更新成功
				if(result > 0) {
					
					// 更新法人信息，但需要提醒法人信息id
					lpId = legalPersonEntity.getId();
					if(lpId != null && lpId> 0) {
						
						// 更新操作，也需要更新法人信息
						result = legalPersonDao.edit(legalPersonEntity);
					}

				}
			}
			
		}
		
		return result;
	}
	
	
	@Override
	public LicenseEntity getLicenseInfo(String orgUuid) {
		
		LicenseEntity licenseInfo =  licenseDao.licenseInfo(orgUuid);
		
		return licenseInfo;
	}
	
	
	@Override
	public LegalPersonEntity getLegalPersonInfo(Integer lid) {
		
		LegalPersonEntity legalPersonInfo =  legalPersonDao.legalPersonInfo(lid);
		
		return legalPersonInfo;
	}
	
	@Override
	public Integer updateLegalPerson(LegalPersonEntity legalPerson) {
		
		Integer id = legalPerson.getId();
		if(id != null) {
			
			return legalPersonDao.edit(legalPerson);
			
		} else {
		
			return legalPersonDao.add(legalPerson);
		}
		
	}
	
	@Override
	public Integer saveLicenseLegalPerson(int lid, int lpId) {
		
		LicenseLegalPersonEntity licenseLegalPersonEntity = new LicenseLegalPersonEntity();
		
		licenseLegalPersonEntity.setLid(lid);
		licenseLegalPersonEntity.setLpId(lpId);
		
		int id = licenseLegalPersonDao.add(licenseLegalPersonEntity);
		
		return id;
	}
	
	@Override
	public Integer updateShareHolderInfo(ShareholderEntity shareholderEntity) {

		Integer sid = shareholderEntity.getId();
		
		if(sid == null) {
			
			return shareholderDao.add(shareholderEntity);
		}
		else {
			
			return shareholderDao.edit(shareholderEntity);
		}
	}
	
	@Override
	public Integer saveLicenseShareHolder(int lid, int sid) {
		
		LicenseShareholderEntity licenseShareholderEntity = new LicenseShareholderEntity();
		
		licenseShareholderEntity.setLid(lid);
		licenseShareholderEntity.setSid(sid);
		
		int id = licenseShareholderDao.add(licenseShareholderEntity);
		
		return id;
	}
	
	@Override
	public String getShareHolderList(Integer lid) {
		
		List<ShareholderEntity> list =  shareholderDao.shareHolderList(lid);
		
		String result = JsonCast.listToJson(list);
		
		return result;
		
	}

	
	// 传入工商信息id返回相关的股东信息列表
	@Override
	public List<ShareholderEntity> shareHolderList(String lid) {
		
		int id = Integer.valueOf(lid);
		
		List<ShareholderEntity> list =  shareholderDao.shareHolderList(id);
		
		return list;
	}
	
	@Override
	public ShareholderEntity shareholderInfo(Integer id) {
		
		return null;
	}
	
	@Override
	public Integer delSharInfo(Integer id) {
		
		// 用股东id先删除股东本身信息
		// 用股东id再删除关联表信息 
		int ok = shareholderDao.del(id);
		
		if(ok > 0) {
			Logger.info("删除股东信息成功id:" + id);
		}
		
		ok = licenseShareholderDao.delShareholer(id);
		if(ok > 0) {
			Logger.info("删除股东法人关联信息成功sid:" + id);
		}
		
		return ok;
	}


	
//	@Override
//	public Integer deleteLic(Integer id) {
//		
//		return licenseDao.del(id);
//	}
//	
//	@Override
//	public Integer deleteLic_Shar(String orgUuid) {
//		LicenseEntity licenseEntity=licenseDao.selectLicInfo(orgUuid);
//		List<Lic_Sha_Middle>ls=lic_Sha_MiddleDao.selectByLicenseId(licenseEntity.getId());
//		List<Integer>id=new ArrayList<Integer>();
//		for(Lic_Sha_Middle l:ls){
//			id.add(l.getId());
//		}
//		shareholderDao.deleteSharById(id);
//		return licenseDao.deleteLic(licenseEntity.getId());
//	}
//	
//	@Override
//	public String getLic_ShaByLicenseId(Integer id) {
//		LicenseEntity licList = licenseDao.getLic_ShaByLicenseId(id);
//		//System.out.println(licList.toString());
//		//获取执照下的所有股东信息
//		List<ShareholderEntity>shareholders=shareholderDao.getShareholdersByLicenseId(id);
//		Map<String,Object>map=new HashMap<String,Object>();
//		map.put("licList", licList);
//		map.put("shareholders", shareholders);
//		return JSON.toJSONString(map);
//	}
//
//
//
//
//	@Override
//	public LicenseEntity getLicinfo(String orgUuid) {
//		return licenseDao.selectLicInfo(orgUuid);
//	}


	
}
