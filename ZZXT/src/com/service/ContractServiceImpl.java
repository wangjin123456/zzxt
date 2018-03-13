package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import com.zzxt.bean.SignEntity;
import com.zzxt.dao.EContractDao;
import com.zzxt.dao.ESignatureDao;
import com.zzxt.dao.LegalPersonDao;
import com.zzxt.dao.LegalPersonEContractDao;
import com.zzxt.dao.LegalPersonESignatureDao;
import com.zzxt.dao.LicenseDao;
import com.zzxt.dao.ShareholderDao;
import com.zzxt.entity.EContractEntity;
import com.zzxt.entity.LegalPersonEContractEntity;
import com.zzxt.mixedSDK.SignatureManager;
import com.zzxt.service.ContractService;
import com.zzxt.service.LicenseService;
import com.zzxt.servicehttp.AuthServiceHttp;
import com.zzxt.servicehttp.OrganizationServiceHttp;
import com.zzxt.util.Global;

/**
 * 
 * @author Administrator
 *合同业务
 */
@Service
public class ContractServiceImpl implements ContractService {
private Logger logger =Logger.getLogger(ContractServiceImpl.class);
private SignatureManager signatureManager=SignatureManager.Instance(Global.SHANG_SHANG_SIGN);

@Autowired
private EContractDao eContractDao;
@Autowired
private ESignatureDao eSigntureDao;


@Autowired
private LegalPersonEContractDao  legalPersonEContractDao;

@Autowired
private LegalPersonESignatureDao legalPersonESignatureDao;

@Autowired 
LicenseDao licenseDao;

@Autowired
ShareholderDao shareholderDao;


@Autowired
private LegalPersonDao legalPersonDao;

@Autowired
private LicenseService licenseService;

@Autowired
private OrganizationServiceHttp organizationServiceHttp;

@Autowired
private  AuthServiceHttp authServiceHttp;


	@Override
	public Map<String, Object> doSearch(String orgUuid, String skey, Integer pageIndex) {
     //总记录数
		int max = eContractDao.count(orgUuid);
		String a=Global.getConfig(Global.MAX_PAGE_KEY);
		int pageSize =Integer.valueOf(a);//每页显示的页面条数
		
		
		int allPage=(max+pageSize-1)/pageSize;//总页数
		if(pageIndex >allPage){
			pageIndex=allPage;
		}
		if(pageIndex<1){
			pageIndex=1;
		}
		int startIndex=(pageIndex-1)*pageSize;
		int endIndex =pageIndex *pageSize;
		Map<String,Object>resultMap=new HashMap<String,Object>();
		resultMap.put("allPage", allPage);
		resultMap.put("pageSize",pageSize);
		
		List<EContractEntity> contractList=eContractDao.search(orgUuid, skey, startIndex, endIndex);
		return resultMap;
	
	}

	@Override
	public Map<String, Object> doSearchState(String orgUuid, int state, Integer pageIndex) {
	//总记录数
		int max=eContractDao.count(orgUuid);//当前页
		String a=Global.getConfig(Global.MAX_PAGE_KEY);
		int pageSize =Integer.valueOf(a);//每页数据
		
		int allPage =(max+pageSize-1)/pageSize;//总数据
		if(pageIndex >allPage){
			pageIndex =allPage;
		}
		if(pageIndex<1){
			pageIndex =1;
		}
		int startIndex=(pageIndex-1)*pageSize;
		int endIndex =pageIndex*pageSize;
		
		Map<String,Object>resultMap =new HashMap<String,Object>();
		
		resultMap.put("allPage", allPage);
		resultMap.put("pageSize", pageSize);
		List<EContractEntity> contractList=eContractDao.searchState(orgUuid, state, startIndex, endIndex);
		int len=contractList.size();
		logger.info("Search Contract List Size:"+len);
		resultMap.put("dataList", contractList);
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getContractList(String orgUuid, Integer pageIndex) {
		//总记录数
		int max =eContractDao.count(orgUuid);//总记录数
		String a=Global.getConfig(Global.MAX_PAGE_KEY);
		int pageSize=Integer.valueOf(a);//每页数据
		int allPage=(max+pageSize-1)/pageSize;//总的记录数
		
		
		
		
		
		return null;
	}

	@Override
	public EContractEntity eContract(String cid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer add(EContractEntity contractEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer edit(EContractEntity contractEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer addRelate(LegalPersonEContractEntity legalPersonEContractEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delRelate(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public LegalPersonEContractEntity getLegalPersonEContractEntity(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> showContract(String ecid, String orgUuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> getSignerList(String ecId, String orgUuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> getSignerList(String ecId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String userAsyncApplyCertStatus(String account, String taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String userGetCert(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setUserSignatureImage(String sid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setUserSignatureImage(String sid, String ecId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String userAsyncApplyCertSubmit(String orgUuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getSealAndSigner(String cuuid, String orgUuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delContract(String cuuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addSigner(int ecId, String orgUuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeSigner(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public int reSigning(String id, String ecId, String orgUuid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String signContract(String ecId, SignEntity signEntity, String orgUuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean lockContract(String contractId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean finishContract(String contractId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JSONObject contractGetInfo(String cuuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> contractDownload(String cuuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String initContract(String ecid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String initContract(String ecid, String orgUuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String uploadFile(String token, String length, String fileName, String account, String appName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String userAsyncApplyCertSubmit(String orgUuid, int type) {
		// TODO Auto-generated method stub
		return null;
	}

}
