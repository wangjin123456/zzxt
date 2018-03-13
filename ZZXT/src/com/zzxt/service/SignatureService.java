package com.zzxt.service;

import java.util.List;
import java.util.Map;

import com.zzxt.entity.ESignatureEntity;
import com.zzxt.entity.LegalPersonESignatureEntity;

/**
 * 电子签章业务
 * @author think
 *
 */

public interface SignatureService {
	
 
	// public List<ESignatureEntity> doSearch(String orgUuid, String skey, Integer pageIndex);
	
	public Map<String, Object> doSearch(String orgUuid, String skey,  Integer pageIndex);
	
	public List<ESignatureEntity> doSearchState(String orgUuid, int state);
	
	public List<ESignatureEntity> getSignatureList(String orgUuid);
	
	public Map<String, Object> getSignatureList(String orgUuid, Integer pageIndex);
	
	public ESignatureEntity eSignature(String cid);
	
	public Integer add(ESignatureEntity signatureEntity);
	public Integer edit(ESignatureEntity eSignatureEntity);
	
	public Integer addRelate(LegalPersonESignatureEntity legalPersonESignatureEntity);
	
	
	public Integer del(String cuuid);
	
	
	public Map<String, Object> showSignature(String esid, String orgUuid);
	

}
