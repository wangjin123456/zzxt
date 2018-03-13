package com.zzxt.servicehttp.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzxt.bean.ManageOrgVo;
import com.zzxt.bean.TreeBean;
import com.zzxt.servicehttp.OrganizationServiceHttp;
import com.zzxt.util.Global;
import com.zzxt.util.IOkHttpUtil;
import com.zzxt.util.Md5Util;
import com.zzxt.util.TimestampUtil;


@Service("OrganizationServiceHttp")
public class OrganizationServiceHttpImpl implements OrganizationServiceHttp {

	private Logger logger = Logger.getLogger(OrganizationServiceHttpImpl.class.getName());


//	@Override
//	public String orgSearch(Map<String, String> paramMap) {
//		
//		/*
//		 * http://icetest.colourlife.net:8081/v1/orgms/org/search?
//		 * ts=1504487986& sign= &appID= &token= &familyTypeId=2
//		 */
//		
//		String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);
//		
//		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
//				
//		String result = "";
//		
//		String token = redisCacheUtil.get("token"); // 这里需要改
//		
//		System.out.println(token);
//		
//		if (token != null && !"".equals(token)) {
//			paramMap.put("token", token);
//			String ts = TimestampUtil.getTs();
//			String sign = Md5Util.signMd5(ts);
//			paramMap.put("ts", ts);
//			paramMap.put("sign", sign);
//			paramMap.put("appID", appId);
//		
//			String url = apiURL + "orgms/org/search";
//			
//			String json = IOkHttpUtil.sendGet(url, paramMap);
//			
//			JSONObject jsonObject = JSONObject.parseObject(json);
//			
//			Integer code = jsonObject.getInteger("code");
//			if (code == 0) {
//				logger.info("获取数据成功");
//				return json;
//			} else {
//				logger.warn("获取数据失败");
//				return json;
//			}
//		} else {
//			logger.error("未能获取到token");
//			return result;
//		}
//	}

//	@Override
//	public String getOrgTresJson(Map<String,String>map ,String parentIdParam , String familyTypeId) {
//
//
//		String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);
//		
//		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
//				
//		
//		// 获取所有的数据 totalRecord
//		HashMap<String, String> paramMap = new HashMap<>();
//		//paramMap.put("token", token);
//		paramMap.putAll(map);
//		paramMap.put("familyTypeId", familyTypeId);//0组织架构、1客户架构、2法人架构、3供方架构、4国家架构
//		//paramMap.put("parentId", "0");
//		String ts = TimestampUtil.getTs();
//		String sign = Md5Util.signMd5(ts);
//		paramMap.put("ts", ts);
//		paramMap.put("sign", sign);
//		paramMap.put("appID", appId);
//		paramMap.put("status", "0");
//		String url = apiURL + "orgms/org/search";
//		String json = IOkHttpUtil.sendGet(url, paramMap);
//		JSONObject jsonObject = JSONObject.parseObject(json);
//		// pageSize 页大小
//		// totalPage 总页
//		// currentPage 当前页面
//		// totalRecord 总记录
//		Integer totalPage = jsonObject.getInteger("totalPage");
//		Integer totalRecord = jsonObject.getInteger("totalRecord");
//		// 大于一个页面的数据
//		if (totalPage > 1) {
//			paramMap.put("pageSize", String.valueOf(totalRecord));
//			json = IOkHttpUtil.sendGet(url, paramMap);
//		}
//	
//		logger.info("构架信息 familyTypeId: " + familyTypeId +"\n树结构： "+json);
//		
//		// 所有的数据
//		jsonObject = JSONObject.parseObject(json);
//		// 需要进行树封装的数据 集合
//		JSONArray content = null;
//		try {
//			content = jsonObject.getJSONArray("content");
//		} catch (Exception e) {
//			logger.info("获取数据失败");
//			e.printStackTrace();
//		} 
//		
////		Map<String,OrgBean>parent=new HashMap<String,OrgBean>();
////		//将所有树节点转换成实体
////		List<OrgBean>orgBeanList=JsonCast.jsonToOrgBeanList(content.toJSONString(), OrgBean.class);
////		for(OrgBean org:orgBeanList){
////			org.getOrgBeanList().clear();
////			//获取所有父节点ID
////			parent.put(org.getOrgUuid(), org);
////		}
////		for(OrgBean org:orgBeanList){
////			if(parent.get(org.getParentId())!=null){
////				parent.get(org.getParentId()).getOrgBeanList().add(org);
////			}
////		}
////		
////		String all=JsonCast.listToJson(orgBeanList);
////		logger.info("全部树："+all);
////		return all;
//		
//		
//		/*
//		 * "orgUuid":"fd23e134-8902-4d7c-b678-cd8bce804fb7",
//		 * "parentId":"e273e94e-3349-47a8-85ef-f5b9d4015fae",
//		 * "name":"法人1.6.4,3.2",
//		 */
//
//		// long startTime = System.currentTimeMillis();
//
//		// 获取所有的 父节点 parentId
//		HashSet<String> keys = new HashSet<>();
//		for (Object object : content) {
//			JSONObject j = (JSONObject) object;
//			keys.add(j.getString("parentId"));
//			
//		}
//		// 根绝 parentId进行归类
//		HashMap<String, String> tree = new HashMap<>();
//		for (String string : keys) {
//			ArrayList<TreeBean> list = new ArrayList<>();
//			for (Object object : content) {
//				JSONObject j = (JSONObject) object;
//				String parentId = j.getString("parentId");
//				if (string.equals(parentId)) {
//					TreeBean treeBean = new TreeBean();
//					treeBean.setName(j.getString("name"));
//					treeBean.setOrgUuid(j.getString("orgUuid"));
//					treeBean.setParentId(parentId);
//					treeBean.setIsParent(true);
//					list.add(treeBean);
//				}
//			}
//			tree.put(string, JSON.toJSON(list).toString());
//		}
//		// long endTime = System.currentTimeMillis();
//		// System.out.println("耗时（毫秒）：" + (endTime - startTime));
//		// // Set<String> parentId = tree.keySet();
//		// // for (String string : parentId) {
//		// // System.out.println(string);
//		// // }
//
//		return tree.get(parentIdParam);
//	}
//	
	
	@Override
	public String getOrgTresJson(String accessToken, String parentIdParam , String familyTypeId) {
		
		
		String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		// 获取所有的数据 totalRecord
		HashMap<String, String> paramMap = new HashMap<>();
		
		paramMap.put("token", accessToken);
 		
		paramMap.put("familyTypeId", familyTypeId);//0组织架构、1客户架构、2法人架构、3供方架构、4国家架构
		
		//paramMap.put("parentId", "0");
		
		String ts = TimestampUtil.getTs();
		String sign = Md5Util.signMd5(ts);
		paramMap.put("ts", ts);
		paramMap.put("sign", sign);
		paramMap.put("appID", appId);
		
		paramMap.put("status", "0");
		
		String url = apiURL + "orgms/org/search";
		
		String json = IOkHttpUtil.sendGet(url, paramMap);
		
		JSONObject jsonObject = JSONObject.parseObject(json);
		
		// pageSize 页大小
		// totalPage 总页
		// currentPage 当前页面
		// totalRecord 总记录
		
		Integer totalPage = jsonObject.getInteger("totalPage");
		Integer totalRecord = jsonObject.getInteger("totalRecord");
		
		// 大于一个页面的数据
		if (totalPage > 1) {
		
			paramMap.put("pageSize", String.valueOf(totalRecord));
			
			json = IOkHttpUtil.sendGet(url, paramMap);
		}
	
		logger.info("构架信息 familyTypeId: " + familyTypeId +"\n树结构： "+json);
		
		// 所有的数据
		jsonObject = JSONObject.parseObject(json);
		
		// 需要进行树封装的数据 集合
		JSONArray content = null;
		try {
			content = jsonObject.getJSONArray("content");
			
		} catch (Exception e) {
			logger.info("获取数据失败");
			e.printStackTrace();
		} 
		
//		Map<String,OrgBean>parent=new HashMap<String,OrgBean>();
//		//将所有树节点转换成实体
//		List<OrgBean>orgBeanList=JsonCast.jsonToOrgBeanList(content.toJSONString(), OrgBean.class);
//		for(OrgBean org:orgBeanList){
//			org.getOrgBeanList().clear();
//			//获取所有父节点ID
//			parent.put(org.getOrgUuid(), org);
//		}
//		for(OrgBean org:orgBeanList){
//			if(parent.get(org.getParentId())!=null){
//				parent.get(org.getParentId()).getOrgBeanList().add(org);
//			}
//		}
//		
//		String all=JsonCast.listToJson(orgBeanList);
//		logger.info("全部树："+all);
//		return all;
		
		
		/*
		 * "orgUuid":"fd23e134-8902-4d7c-b678-cd8bce804fb7",
		 * "parentId":"e273e94e-3349-47a8-85ef-f5b9d4015fae",
		 * "name":"法人1.6.4,3.2",
		 */

		// long startTime = System.currentTimeMillis();

		// 获取所有的 父节点 parentId
		HashSet<String> keys = new HashSet<>();
		for (Object object : content) {
			JSONObject j = (JSONObject) object;
			keys.add(j.getString("parentId"));
			
		}
		// 根绝 parentId进行归类
		HashMap<String, String> tree = new HashMap<>();
		for (String string : keys) {
			ArrayList<TreeBean> list = new ArrayList<>();
			for (Object object : content) {
				JSONObject j = (JSONObject) object;
				String parentId = j.getString("parentId");
				if (string.equals(parentId)) {
					TreeBean treeBean = new TreeBean();
					treeBean.setName(j.getString("name"));
					treeBean.setOrgUuid(j.getString("orgUuid"));
					treeBean.setParentId(parentId);
					treeBean.setIsParent(true);
					list.add(treeBean);
				}
			}
			tree.put(string, JSON.toJSON(list).toString());
		}
		// long endTime = System.currentTimeMillis();
		// System.out.println("耗时（毫秒）：" + (endTime - startTime));
		// // Set<String> parentId = tree.keySet();
		// // for (String string : parentId) {
		// // System.out.println(string);
		// // }

		return tree.get(parentIdParam);
	}
	

	

	@Override
	public String getAllOrgTresJson(String accessToken, String parentId , String familyTypeId) {
		
		
		String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		if(parentId == null || parentId.trim().equals("")) {
			
			parentId = "0";
		}
		
		// 获取所有的数据 totalRecord
		HashMap<String, String> paramMap = new HashMap<>();
		
		paramMap.put("token", accessToken);
 		
		paramMap.put("familyTypeId", familyTypeId);//0组织架构、1客户架构、2法人架构、3供方架构、4国家架构
		
		paramMap.put("parentId", parentId);
		
		String ts = TimestampUtil.getTs();
		String sign = Md5Util.signMd5(ts);
		paramMap.put("ts", ts);
		paramMap.put("sign", sign);
		paramMap.put("appID", appId);
		
		String url = apiURL + "orgms/org/search";
		
		String json = IOkHttpUtil.sendGet(url, paramMap);
		
		JSONObject jsonObject = JSONObject.parseObject(json);
		
		// pageSize 页大小
		// totalPage 总页
		// currentPage 当前页面
		// totalRecord 总记录
		
		Integer totalPage = jsonObject.getInteger("totalPage");
		Integer totalRecord = jsonObject.getInteger("totalRecord");
		
		// 大于一个页面的数据
		if (totalPage > 1) {
		
			paramMap.put("pageSize", String.valueOf(totalRecord));
			
			json = IOkHttpUtil.sendGet(url, paramMap);
		}
	
		jsonObject = JSONObject.parseObject(json);
			
		logger.info("构架信息 familyTypeId: " + familyTypeId +"\n树结构： "+json);
		
		String resultString = "";
		
		JSONArray content = jsonObject.getJSONArray("content");
		
		if(content == null) {
			
			return resultString;
		}
		
		int len = content.size();
		
		logger.info("\n返回了" + len + "条子级数据\n");
		
		JSONArray resultList = new JSONArray();
		
		for(int index = 0; index < len; index ++) {
			
			JSONObject contentObject = content.getJSONObject(index);
			
			if(contentObject != null) {
				
				JSONObject newObject = new JSONObject();
				
				String uuid = contentObject.getString("orgUuid");
				String name = contentObject.getString("name");
				String pid = contentObject.getString("parentId");
				String status = contentObject.getString("status");
				
				logger.info("Orguuid:" + uuid + " name:" + name + " parentId:" + pid + "status:" + status);
				
				newObject.put("orgUuid", uuid);
				newObject.put("status", status);
				newObject.put("name", name); 
				newObject.put("parentId", pid);
				newObject.put("isParent", "true");
				
				resultList.add(newObject);
			}
			
		}
		
		  resultString = resultList.toJSONString();
		
		return resultString;
	}
	
	
	@Override
	public String getOrgJson(String accessToken, String orgUuid, String familyTypeId) {
		
		
		String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		HashMap<String, String> paramMap = new HashMap<>();
		
		paramMap.put("token", accessToken);
 		paramMap.put("familyTypeId", familyTypeId);//0组织架构、1客户架构、2法人架构、3供方架构、4国家架构
		paramMap.put("parentId", orgUuid);
		paramMap.put("isAll", "0");
		
		paramMap.put("pageSize", "500");
		
		String ts = TimestampUtil.getTs();
		String sign = Md5Util.signMd5(ts);
		paramMap.put("ts", ts);
		paramMap.put("sign", sign);
		paramMap.put("appID", appId);
		paramMap.put("status", "0");
		
		String url = apiURL + "orgms/org/search";
		
		String json = IOkHttpUtil.sendGet(url, paramMap);
		
		String resultString = "";
		
		if(json == null || json.trim().equals("") == true) {
			
			return resultString;
		}
		
		JSONObject jsonObject = JSONObject.parseObject(json);
		if(jsonObject == null) {
			
			return resultString;
		}
		
		// pageSize 页大小
		// totalPage 总页
		// currentPage 当前页面
		// totalRecord 总记录
//		Integer totalPage = jsonObject.getInteger("totalPage");
//		Integer totalRecord = jsonObject.getInteger("totalRecord");
//		if (totalRecord == 0) {
//			String result = JSONObject.toJSONString(new Tip("没有数据", Global.FAILD));
//			return result;
//		}
//		// 大于一个页面的数据
//		logger.info("树：》》》》"+json);
//		if (totalPage != 1) {
//			paramMap.put("pageSize", String.valueOf(totalRecord));
//			json = IOkHttpUtil.sendGet(url, paramMap);
//		}
//		
//		// 所有的数据
//		jsonObject = JSONObject.parseObject(json);
		// 需要进行树封装的数据 集合
		
		
		JSONArray content = jsonObject.getJSONArray("content");
		
		if(content == null) {
						
			return resultString;
		}
		
		int len = content.size();
		
		logger.info("\n返回了" + len + "条子级数据\n");
		
		JSONArray resultList = new JSONArray();
		
		for(int index = 0; index < len; index ++) {
			
			JSONObject contentObject = content.getJSONObject(index);
			
			if(contentObject != null) {
				
				JSONObject newObject = new JSONObject();
				
				String uuid = contentObject.getString("orgUuid");
				String name = contentObject.getString("name");
				String parentId = contentObject.getString("parentId");
				
				logger.info("Orguuid:" + uuid + " name:" + name + " parentId:" + parentId);
				
				newObject.put("orgUuid", uuid);
				newObject.put("name", name); 
				newObject.put("parentId", parentId);
				newObject.put("isParent", "true");
				
				resultList.add(newObject);
			}
			
		}
		
		resultString = resultList.toJSONString();
		
		return resultString;
		
	}
	
	
	
	@Override
	public String getOrgAgents(String token, String orgUuid) {
		
		String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
				
		HashMap<String, String> paramMap = new HashMap<>();
		
		paramMap.put("token", token);
		paramMap.put("orgUuid", orgUuid);
		
		String ts = TimestampUtil.getTs();
		String sign = Md5Util.signMd5(ts);
		
		paramMap.put("ts", ts);
		paramMap.put("sign", sign);
		paramMap.put("appID", appId);
		paramMap.put("pageSize", "300");
		
		String url = apiURL + "orgms/account/searchKey";
		
		String json = IOkHttpUtil.sendGet(url, paramMap);
		
		logger.info("管理组织架构用户列表数据:" + json);
		
		JSONObject jsonObject = JSONObject.parseObject(json);
		
		// pageSize 页大小
		// totalPage 总页
		// currentPage 当前页面
		// totalRecord 总记录
//		Integer totalPage = jsonObject.getInteger("totalPage");
//		Integer totalRecord = jsonObject.getInteger("totalRecord");
//		if (totalRecord == 0) {
//			String result = JSONObject.toJSONString(new Tip("没有数据", Global.FAILD));
//			return result;
//		}
		// 大于一个页面的数据
//		logger.info("管理员组织架构员工信息列表"+json);
//		if (totalPage != 1) {
//			paramMap.put("pageSize", String.valueOf(totalRecord));
//			json = IOkHttpUtil.sendGet(url, paramMap);
//		}
		
		// 所有的数据
		//jsonObject = JSONObject.parseObject(json);
		
		// 需要进行树封装的数据 集合
		JSONArray content = jsonObject.getJSONArray("content");
	
		return content.toJSONString();
	}
	


	@Override
	public String getOrgUsers(String token, String orgUuid, String key) {
		
		String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
				
		HashMap<String, String> paramMap = new HashMap<>();
		
		paramMap.put("token", token);
		paramMap.put("orgUuid", orgUuid);
		paramMap.put("keyword", key);
		
		String ts = TimestampUtil.getTs();
		String sign = Md5Util.signMd5(ts);
		
		paramMap.put("ts", ts);
		paramMap.put("sign", sign);
		paramMap.put("appID", appId);
		paramMap.put("pageSize", "300");
		
		String url = apiURL + "orgms/account/searchKey";
		
		String json = IOkHttpUtil.sendGet(url, paramMap);
		
		logger.info("管理组织架构用户列表数据:" + json);
		
		JSONObject jsonObject = JSONObject.parseObject(json);
		
		// pageSize 页大小
		// totalPage 总页
		// currentPage 当前页面
		// totalRecord 总记录
//		Integer totalPage = jsonObject.getInteger("totalPage");
//		Integer totalRecord = jsonObject.getInteger("totalRecord");
//		if (totalRecord == 0) {
//			String result = JSONObject.toJSONString(new Tip("没有数据", Global.FAILD));
//			return result;
//		}
		// 大于一个页面的数据
//		logger.info("管理员组织架构员工信息列表"+json);
//		if (totalPage != 1) {
//			paramMap.put("pageSize", String.valueOf(totalRecord));
//			json = IOkHttpUtil.sendGet(url, paramMap);
//		}
		
		// 所有的数据
		//jsonObject = JSONObject.parseObject(json);
		
		// 需要进行树封装的数据 集合
		JSONArray content = jsonObject.getJSONArray("content");
	
		return content.toJSONString();
	}
	
	

	/**
	 * 查询组织架构详情
	 */
	@Override
	public String getOrg(String accessToken, String orgUuid) {
		/*
		 * http://icetest.colourlife.net:8081/v1/ orgms/org ? ts= &sign= &appID=
		 * &token= &orgUuid=
		 */
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		String url = apiURL + "orgms/org";
		
		
		Map<String, String> map = new HashMap<>();
		 
		map.put("orgUuid", orgUuid);
		map.put("token", accessToken);
		
		getTsSignAppid(map);
		
		String jsonResult = IOkHttpUtil.sendGet(url, map);
		
		JSONObject jsonObject = JSONObject.parseObject(jsonResult);
		
		String orgStr = jsonObject.getString("content");
		
		if ("".equals(orgStr)&&orgStr.trim().length()==0) {
		
			return "";
		}
		
		return orgStr;
	}
	
	

	/**
	 * 查询组织架构详情
	 */
//	@Override
//	public String getOrgList(String accessToken, String orgUuids) {
//
//		String resultString = "";
//		
//		if(orgUuids == null || orgUuids.trim().equals("") == true) {
//			
//			logger.info("getOrgInfo 失败 orgUuid为空!");
//			
//			return resultString;
//		}
//		
//		logger.info("orgUuids:" + orgUuids);
//		
//		String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);
//		
//		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
//		
//		
//		Map<String, String> map = new HashMap<>();
//		String ts = TimestampUtil.getTs();
//		map.put("ts", ts);
//		map.put("sign", Md5Util.signMd5(ts));
//		map.put("appID", appId);
//		map.put("orgUuid", orgUuids);
//		map.put("token", accessToken);
//
//		String url = apiURL + "orgms/org/batch";
//		
//		String jsonResult = IOkHttpUtil.sendGet(url, map);
//		
//		JSONObject jsonObject = JSONObject.parseObject(jsonResult);
//		
//		resultString = jsonObject.getString("content");
//	
//		return resultString;
//	}
//	
	
	
	/**
	 * 查询组织架构详情
	 */
	@Override
	public String getUserOrgList(String accessToken, String orgUuids) {

		String resultString = "";
		
		if(orgUuids == null || orgUuids.trim().equals("") == true) {
			
			logger.info("getOrgInfo 失败 orgUuid为空!");
			
			return resultString;
		}
		
		logger.info("orgUuids:" + orgUuids);
		
		String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		Map<String, String> map = new HashMap<>();
		
		
 
		String ts = TimestampUtil.getTs();
		map.put("ts", ts);
		map.put("sign", Md5Util.signMd5(ts));
		map.put("appID", appId);
		map.put("orgUuid", orgUuids);
		

		String url = apiURL + "orgms/org/batch";
		
		map.put("token", accessToken);
		
		String jsonResult = IOkHttpUtil.sendGet(url, map);
		
		JSONObject jsonObject = JSONObject.parseObject(jsonResult);
		
		if(jsonObject == null) {
			
			return resultString;
		}
		
		// resultString = jsonObject.getString("content");

		JSONArray content = jsonObject.getJSONArray("content");
		
		if(content == null) {
						
			return resultString;
		}
		
		int len = content.size();
		
		logger.info("\n返回了" + len + "条子级数据\n");
		
		JSONArray resultList = new JSONArray();
		
		for(int index = 0; index < len; index ++) {
			
			JSONObject contentObject = content.getJSONObject(index);
			
			if(contentObject != null) {
				
				JSONObject newObject = new JSONObject();
				
				String uuid = contentObject.getString("orgUuid");
				String name = contentObject.getString("name");
				String parentId = contentObject.getString("parentId");
				
				logger.info("Orguuid:" + uuid + " name:" + name + " parentId:" + parentId);
				
				newObject.put("orgUuid", uuid);
				newObject.put("name", name); 
				newObject.put("parentId", parentId);
				newObject.put("isParent", "true");
				
				resultList.add(newObject);
			}
			
		}
		
		resultString = resultList.toJSONString();
		
		return resultString;
	
	}
	
	
	@Override
	public List<ManageOrgVo> getManageOrgList(String accessToken, String mangeOrgUuid) {
		
		List<ManageOrgVo> manageOrgVoList = new ArrayList<>();
		int level = 1;
	
		getMangeOrgVo(accessToken, mangeOrgUuid,manageOrgVoList,level);
		
		return manageOrgVoList;
		
	}
	 
	public void getMangeOrgVo(String accessToken, String mangeOrgUuid,List<ManageOrgVo> manageOrgVoList,int level){
		
		String mangeOrg = getOrg(accessToken, mangeOrgUuid);
		
		if (!"".equals(mangeOrg)) {
		
			JSONObject object = JSONObject.parseObject(mangeOrg);
			
			ManageOrgVo manageOrgVo = JSONObject.toJavaObject(object, ManageOrgVo.class);
			
			manageOrgVo.setLevel(level);
			
			level++;
			
			manageOrgVoList.add(manageOrgVo);
			
			String parentId = manageOrgVo.getParentId();
			
			if (!"0".equals(parentId)) {
				
				getMangeOrgVo(accessToken, parentId,manageOrgVoList,level);
			}
		}
	}

	
	
	
	/**
	 * @Description 封装数据
	 * 
	 *              sign(签名)、ts(时间戳)、appID(调用者app id)
	 *
	 * @param param
	 */
	private void getTsSignAppid(Map<String, String> param) {
		
		
		String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);

		String ts = TimestampUtil.getTs();
		param.put("ts", ts);
		param.put("sign", Md5Util.signMd5(ts));
		param.put("appID", appId);
	}

	@Override
	public String deleteOrg(Map<String, String> param) {							//这里需要验证
		/*
		 * http://icetest.colourlife.net:8081/v1/orgms/org
		 * token=&orgUuid=17b42378 ;
		 */
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		getTsSignAppid(param);
		String url = apiURL + "orgms/org";
		return IOkHttpUtil.sendDelete(url, param);
	}

	@Override
	public String insertOrg(Map<String, String> param) {							//这里需要验证		
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		String url = apiURL + "orgms/org";
		
		Map<String, String> paramAddressBar = new HashMap<>();
		
		getTsSignAppid(paramAddressBar);
		
		return IOkHttpUtil.sendPost(url, paramAddressBar, param);
	}
	

	@Override
	public String updateOrg(Map<String, String> param) {							//这里需要验证
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		String url = apiURL + "orgms/org";
		
		Map<String, String> paramAddressBar = new HashMap<>();
		
		getTsSignAppid(paramAddressBar);
		
		return IOkHttpUtil.sendPut(url, paramAddressBar, param);
	}

	@Override
	public String batchOrg() {
		return null;
	}

	@Override
	public String orgsOrg() {
		return null;
	}

	@Override
	public String batchListOrg() {
		return null;
	}

}
