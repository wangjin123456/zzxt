package com.zzxt.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.zzxt.bean.SignEntity;
import com.zzxt.entity.EContractEntity;
import com.zzxt.entity.LegalPersonEContractEntity;
import com.zzxt.util.Tip;


class fileInfoBean {
	
	private String length;
	
	private String fileName;
	
	private String account;
	
	private String appName;
	
	private String password;

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	
}



/**
 * 合同业务
 * @author think
 *
 */

public interface ContractService {
	
	public Map<String, Object>  doSearch(String orgUuid, String skey, Integer pageIndex);
	
	public Map<String, Object>  doSearchState (String orgUuid, int state, Integer pageIndex);
	
	public Map<String, Object> getContractList(String orgUuid, Integer pageIndex);
	
	public EContractEntity eContract(String cid);
	
	public Integer add(EContractEntity contractEntity);
	public Integer edit(EContractEntity contractEntity);
	
	public Integer addRelate(LegalPersonEContractEntity legalPersonEContractEntity);
	
	public void delRelate(String id);
		
	public LegalPersonEContractEntity getLegalPersonEContractEntity(String id);
	
	public Map<String, Object> showContract(String ecid, String orgUuid);
	
	
	//public List<OrgBean> getSignerList(String ecId, String orgUuid);
	
	public List<Map<String, String>> getSignerList(String ecId, String orgUuid);
	
	public List<Map<String, String>> getSignerList(String ecId);
	
	//public Tip userAsyncApplyCertStatus(String account, String taskId);
	public String userAsyncApplyCertStatus(String account, String taskId);
	
	public String userGetCert(String account);
	// public Tip setUserSignatureImage(String sid);
	public String setUserSignatureImage(String sid);
	
	// public Tip setUserSignatureImage(String sid, String ecId);
	public String setUserSignatureImage(String sid, String ecId);
	
	// public Object userAsyncApplyCertSubmit(String orgUuid);
	public String userAsyncApplyCertSubmit(String orgUuid);
	
	public Map<String, Object> getSealAndSigner(String cuuid,String orgUuid);

	public Integer delContract(String cuuid);
	//public void addSigner(int ecId, String orgUuid);
	//public Tip addSigner(int ecId, String orgUuid);
	public String addSigner(int ecId, String orgUuid);
	
	public void removeSigner(String id);
	
	public int reSigning(String id, String ecId, String orgUuid);
	
	
	/**
	 * 签署合同
	 * @param cuuid
	 * @param signEntity
	 */
	// public Tip signContract(String cuuid,SignEntity signEntity,String orgUuid);
	public String signContract(String ecId, SignEntity signEntity, String orgUuid);
	
//	public Tip lockContract(String cuuid);
//	public Tip finishContract(String cuuid);
	
	public boolean lockContract(String contractId);
	public boolean finishContract(String contractId);
	
	public JSONObject contractGetInfo(String cuuid);
	public Map<String, Object> contractDownload(String cuuid);
	// public Tip initContract(String cuuid);
	// public Tip initContract(String ecid, String orgUuid);
	// public Tip initContract(String ecid);
	
	public String initContract(String ecid);

	public String initContract(String ecid, String orgUuid);
	
	
	
	public String uploadFile(String token, String length, String fileName, String account, String appName);
	
	
	public String userAsyncApplyCertSubmit(String orgUuid, int type);
	
}
