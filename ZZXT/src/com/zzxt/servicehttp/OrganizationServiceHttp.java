/**   
* @Title: OrganizationServiceHttp.java 
* @Package com.zzxt.servicehttp 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  wangdekun   
* @date 2017年8月30日 下午1:27:55 
* @version V1.0   
*/
package com.zzxt.servicehttp;

import java.util.List;
import java.util.Map;

import com.zzxt.bean.ManageOrgVo;

/**
 * @ClassName: OrganizationServiceHttp
 * @Description: 调取微服务 组织架构 接口 进行法人架构的 CRUD
 * @author wangdekun
 * @date 2017年8月30日 下午1:27:55
 * 
 */
public interface OrganizationServiceHttp {

	//String orgSearch(Map<String, String> paramMap);

	//String getOrgTresJson(Map<String,String>map, String parentIdParam, String familyTypeId);
	public String getAllOrgTresJson(String accessToken, String parentIdParam , String familyTypeId);

	public String getOrgTresJson(String accessToken, String parentIdParam , String familyTypeId);
	
	// String getOrgAgents(Map<String, String> map, String token, String orgUuid);
	
	public String getOrgAgents(String token, String orgUuid);
	
	public String getOrgUsers(String token, String orgUuid, String key);
	
	/**
	 * 
	 * @Description 根据组织架构主键（orgUuid）查询组织架构详情
	 *
	 * @param param
	 *            orgUuid corpId
	 * @return
	 */
	//String getOrg(Map<String, String> param);
	public String getOrg(String accessToken, String orgUuid);
	
	
	// public String getOrgList(String accessToken, String orgUuid);
	
	String deleteOrg(Map<String, String> param);

	String insertOrg(Map<String, String> param);

	String updateOrg(Map<String, String> param);
	
	// public String getUserOrgList(String orgUuids);
	
	public String getUserOrgList(String accessToken, String orgUuids);

	/**
	 * 
	 * @Description 批量查询组织架构
	 *
	 * @return
	 */
	String batchOrg();

	/**
	 * 
	 * @Description 根据父级查询子级组织
	 *
	 * @return
	 */
	String orgsOrg();

	/**
	 * 
	 * @Description根据省市区组织架构列表
	 *
	 * @return
	 */
	String batchListOrg();
	
	/**
	 * 获取组织架构
	 * @param map
	 * @param orgUuid
	 * @param string
	 * @return
	 */
	//String getOrgJson(Map<String, String> map, String orgUuid, String string);
	
	
	/**
	 * 获取组织架构
	 * @param map
	 * @param orgUuid
	 * @param string
	 * @return
	 */
	String getOrgJson(String accessToken, String orgUuid, String string);
	
	
	/**
	 * 获取当前管理组织架已经他的所有父级并标记等级
	 * @param map
	 * @return
	 */

	List<ManageOrgVo> getManageOrgList(String accessToken, String mangeOrgUuid);
	
	
	/**
	 * 获取当前管理组织架已经他的所有父级并标记等级
	 * @param map
	 * @return
	 */

	// List<ManageOrgVo> getManageOrgList(String mangeOrgUuid);
	
}
