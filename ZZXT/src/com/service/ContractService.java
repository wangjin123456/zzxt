package com.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.zzxt.bean.SignEntity;
import com.zzxt.entity.EContractEntity;
import com.zzxt.entity.LegalPersonEContractEntity;

/*
 * 
 * 合同业务
 */
public interface ContractService {
   public Map<String,Object> doSearch(String orgUuid,String skey,Integer pageIndex);
   
   public Map<String,Object> doSearchState(String orgUuid,int state,Integer pageIndex);
   
   public Map<String,Object>getContractList(String orgUuid,Integer pageIndex);
   
   public EContractEntity eContract(String cid);
   
   public Integer add(EContractEntity contractEntity);
   
   public Integer edit(EContractEntity contractEntity);
   
   public Integer addRelate(LegalPersonEContractEntity legalPersonEContractEntity);
   
   public void delRelate(String id);
   
   public LegalPersonEContractEntity geLegalPersonEContractEntity(String id);
   
   public Map<String,Object> showContract(String orgUuid,String ecid);
 
   public String addSignner(int ecid,String orgUuid);
   /*
    * 签署合同
    */
   public String signContract(String ecid,SignEntity signEntity,String orgUuid);
   
   public boolean lockContract(String contractId);
   public boolean finishContract(String contractid);
   
   public JSONObject contractGetInfo(String cuuid);
   public Map<String,Object>contractDownLoad(String cuuid);
   
   public String userAsyncApplyCertSubmit(String orgUuid,int type);
   
 
   
   
   
}
