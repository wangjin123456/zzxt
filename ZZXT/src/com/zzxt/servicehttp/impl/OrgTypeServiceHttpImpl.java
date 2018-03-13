package com.zzxt.servicehttp.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zzxt.bean.TypeBean;
import com.zzxt.bean.TypeContent;
import com.zzxt.servicehttp.OrgTypeServiceHttp;
import com.zzxt.util.Global;
import com.zzxt.util.IOkHttpUtil;
import com.zzxt.util.Md5Util;
import com.zzxt.util.TimestampUtil;
import com.zzxt.util.TsSignAppid;

/**
 * 
 * @ClassName: OrgTypeServiceHttpImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wangdekun
 * @date 2017年9月8日 下午5:02:07
 *
 */
@Service("OrgTypeServiceHttp")
public class OrgTypeServiceHttpImpl implements OrgTypeServiceHttp {
	private static Logger logger = Logger.getLogger(OrgTypeServiceHttpImpl.class.getName());


	
	@Override
	public String addType(String token, String name, String corpId) {

		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		String url = apiURL + "orgms/type";
		Map<String, String> param = new HashMap<>();
		// name 类型值
		param.put("name", name);
		// token 鉴权参数
		param.put("token", token);
		// type 类型(组织架构类型org_type,岗位类型job_type)
		param.put("type", "org_type");
		// corpId 租户id
		param.put("corpId", corpId);
		// familyTypeId 族谱类型ID：0组织架构、1客户架构、2法人架构、3供方架构、4国家架构
		param.put("familyTypeId", Global.STRUCTURE_TYPE_LEGALPERSON);
		Map<String, String> addressBar = new HashMap<>();
		TsSignAppid.get(addressBar);
		return IOkHttpUtil.sendPost(url, addressBar, param);
	}

	@Override
	public String deleteType(String token, String typeUuid, String corpId) {
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		String url = apiURL + "orgms/type";
		Map<String, String> param = new HashMap<>();
		param.put("token", token);
		param.put("typeUuid", typeUuid);
		param.put("corpId", corpId);
		TsSignAppid.get(param);
		return IOkHttpUtil.sendDelete(url, param);
	}

	@Override
	public String getType(String token, String typeUuid, String corpId) {
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		String url = apiURL + "orgms/type";
		Map<String, String> param = new HashMap<>();
		param.put("token", token);
		param.put("typeUuid", typeUuid);
		param.put("corpId", corpId);
		TsSignAppid.get(param);
		return IOkHttpUtil.sendGet(url, param);
	}

	@Override
	public String getType(String token, String typeUuid) {
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		String url = apiURL + "orgms/type";
		Map<String, String> param = new HashMap<>();
		param.put("token", token);
		param.put("typeUuid", typeUuid);
		TsSignAppid.get(param);
		return IOkHttpUtil.sendGet(url, param);
	}
	@Override
	public String updateType(String typeUuid, String name, String token, String corpId) {
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		// typeUuid name token type(组织架构类型org_type,岗位类型job_type))corpId
		// familyTypeId
		String url = apiURL + "orgms/type";
		Map<String, String> param = new HashMap<>();
		param.put("familyTypeId",  Global.STRUCTURE_TYPE_LEGALPERSON);
		param.put("type", "org_type");
		param.put("typeUuid", typeUuid);
		param.put("name", name);
		param.put("token", token);
		param.put("corpId", corpId);
		Map<String, String> paramAddressBar = new HashMap<>();
		TsSignAppid.get(paramAddressBar);
		return IOkHttpUtil.sendPut(url, paramAddressBar, param);
	}

	@Override
	public String selectTypeBath(Map<String, String> param) {
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		String url = apiURL + "orgms/type/batch";
		return null;
	}

	@Override
	public String selectTypeSearch(Map<String, String> param) {
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		String url = apiURL + "orgms/type/search";
		TsSignAppid.get(param);
		return IOkHttpUtil.sendGet(url, param);
	}

	@Override
	public String selectTypeList(Map<String, String> param) {
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		String url = apiURL + "orgms/type/typeList";
		return null;
	}

	@Override
	public String saveTypeList(List<TypeContent> typeContent, int typeNum,List<TypeContent>newTypeContent,String token,String typeUuids) {
		
		logger.info("typeContent:"+typeContent.size()+"---newTypeContent:"+newTypeContent.size());
		
		if(newTypeContent!=null&&typeContent!=null){
		
			if(newTypeContent.size()-typeContent.size()>=0){//修改旧的并且增加新的
			
				logger.info("------修改旧的并且增加新的-------");
				
				int count=0;
				
				for(TypeContent newtc:newTypeContent){
				
					for(TypeContent oldtc:typeContent){
					
						if(newtc.getTypeUuid()!=null&&!"".equals(newtc.getTypeUuid())){
						
							if(newtc.getTypeUuid().equals(oldtc.getTypeUuid())){
							
								count+=1;
								
								String reJson=updateType(oldtc.getTypeUuid(), newtc.getName(), token, newtc.getCorpId());
								
								logger.info("修改旧的："+reJson);
							}
						}
					}
				}
				
				
				if(newTypeContent.size()-count>0){//新增
				
					for(int i=count;i<newTypeContent.size();i++){
					
						String reJson=addType(token, newTypeContent.get(i).getName(), newTypeContent.get(i).getCorpId());
						
						logger.info("新增的："+reJson);
					
					}
				
				}
			
			}else if(newTypeContent.size()-typeContent.size()<0){//删除旧的并且修改旧的
			
				logger.info("------删除旧的并且修改旧的-------");
				
				int count =0;
				
				for(TypeContent oldtc:typeContent){
				
					if(!typeUuids.contains(oldtc.getTypeUuid())){
					
						String reJson=deleteType(token, oldtc.getTypeUuid(),oldtc.getCorpId());
					
					}
				}
				
				for(int i=0;i<newTypeContent.size();i++){
				
					String reJson=updateType(newTypeContent.get(i).getTypeUuid(), newTypeContent.get(i).getName(), token,newTypeContent.get(i).getCorpId());
					
					count+=1;
				
				}
			
			}
		
		}else{//新增
			
			if(newTypeContent!=null&&newTypeContent.size()>0){
			
				logger.info("------新增-------");
				
				for(TypeContent newtc:newTypeContent){
				
					String reJson=addType(token, newtc.getName(), newtc.getCorpId());
				
				}
			}
		}
		
		return null;
	}

	@Override
	public String saveOrgType(TypeBean typeBean,Map<String, String> param) {
		String result = addType(param.get("token"), typeBean.getName(), param.get("corpId"));
		
		return result;
	}

	@Override
	public String delOrgType(String typeUuid, Map<String, String> map) {
		String result = deleteType(map.get("token"), typeUuid, map.get("corpId"));
		
		return result;
	}

	@Override
	public String updateOrgType(TypeBean typeBean, Map<String, String> map) {
		//(String typeUuid, String name, String token, String corpId)
		String result = updateType(typeBean.getTypeUuid(),typeBean.getName(),map.get("token"),map.get("corpId"));
		
		return result;
	}

}
