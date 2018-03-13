package com.zzxt.service.impl;
import java.util.HashMap;
/**
 * 电子签章相关业务
 */
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzxt.dao.ESignatureDao;
import com.zzxt.dao.LegalPersonESignatureDao;
import com.zzxt.entity.EContractEntity;
import com.zzxt.entity.ESignatureEntity;
import com.zzxt.entity.LegalPersonESignatureEntity;
import com.zzxt.service.SignatureService;
import com.zzxt.util.Global;

@Service("SignatureService")
public class SignatureServiceImpl implements SignatureService {
	
	private Logger logger = Logger.getLogger(SignatureServiceImpl.class);

	
	@Autowired
	ESignatureDao signatureDao;
	
	@Autowired
	private LegalPersonESignatureDao legalPersonESignatureDao;
	
//	
//	@Override
//	public List<ESignatureEntity> doSearch(String orgUuid, String skey) {
//		
//		List<ESignatureEntity> signatureList = signatureDao.search(orgUuid, skey);
//		 
//		return signatureList;
//	}
	
	@Override
	public Map<String, Object> doSearch(String orgUuid, String skey,  Integer pageIndex) {
		
		// 总记录数
		int max = signatureDao.count(orgUuid);
		
		String a = Global.getConfig(Global.MAX_PAGE_KEY);
		int pageSize = Integer.valueOf(a);
		
		int  allPage = (max + pageSize - 1) / pageSize;
		
		if(pageIndex > allPage) {
			pageIndex = allPage;
		}
		
		if(pageIndex < 1) {
			
			pageIndex = 1;
		}
		
		int startIndex = (pageIndex - 1) * pageSize;
		
		int endIndex = pageIndex * pageSize;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("allPage", allPage);
		resultMap.put("pageSize", pageSize);
						
		List<ESignatureEntity> contractList = signatureDao.search(orgUuid, skey, startIndex, endIndex);
		
		int len = contractList.size();
		
		logger.info("Search Contract List Size:" + len);
		
		resultMap.put("dataList", contractList);
		
		return resultMap;
		
	}
	
	@Override
	public List<ESignatureEntity> doSearchState(String orgUuid, int state) {
		
		List<ESignatureEntity> signatureList = signatureDao.searchState(orgUuid, state);
		 
		return signatureList;
	}
	
	@Override
	public List<ESignatureEntity> getSignatureList(String orgUuid) {
		
		return signatureDao.eSignatureAllList(orgUuid);
	}

	@Override
	public Map<String, Object> getSignatureList(String orgUuid, Integer pageIndex){
		
		// 总记录数
		int max = signatureDao.count(orgUuid);
		
		String a = Global.getConfig(Global.MAX_PAGE_KEY);
		int pageSize = Integer.valueOf(a);
		
		int  allPage = (max + pageSize - 1) / pageSize;
		
		if(pageIndex > allPage) {
			pageIndex = allPage;
		}
		
		if(pageIndex < 1) {
			
			pageIndex = 1;
		}
		
		int startIndex = (pageIndex - 1) * pageSize;
		
		int endIndex = pageIndex * pageSize;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("allPage", allPage);
		resultMap.put("pageSize", pageSize);
		
		List<ESignatureEntity> contractList = signatureDao.eSignatureList(orgUuid, startIndex, endIndex);
		
		resultMap.put("dataList", contractList);
		
		return  resultMap;
	}

	
	@Override
	public Integer add(ESignatureEntity eSignatureEntity) {

		return signatureDao.add(eSignatureEntity);
	}
	

	@Override
	public Integer edit(ESignatureEntity eSignatureEntity) {
		
		return signatureDao.edit(eSignatureEntity);
	
	}
	
	

	@Override
	public ESignatureEntity eSignature(String sid) {
		// TODO Auto-generated method stub
		return signatureDao.eSignature(sid);
	}

	@Override
	public Integer del(String sid) {
		// TODO Auto-generated method stub
		
		legalPersonESignatureDao.del(sid);
		return signatureDao.del(sid);
	}

	@Override
	public Integer addRelate(LegalPersonESignatureEntity legalPersonESignatureEntity) {

		return legalPersonESignatureDao.add(legalPersonESignatureEntity);
	}

	@Override
	public Map<String, Object> showSignature(String esid, String orgUuid) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public SignatureVo findSignatureByOrgUuid(String orgUuid) {
//		return signatureDao.findSignatureByOrgUuid(orgUuid);
//	}

}
