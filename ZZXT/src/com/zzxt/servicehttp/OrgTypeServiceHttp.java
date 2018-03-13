package com.zzxt.servicehttp;

import java.util.List;
import java.util.Map;

import com.zzxt.bean.TypeBean;
import com.zzxt.bean.TypeContent;

/**
 * 
 * @ClassName: OrgTypeServiceHttp
 * @Description: 调用ICE微服务 对类型进行 CRUD
 * @author wangdekun
 * @date 2017年9月8日 下午4:46:31
 *
 */
public interface OrgTypeServiceHttp {
	// 删除类型
	String deleteType(String token ,String typeUuid, String corpId);

	// 获取类型
	String getType(String token, String typeUuid, String corpId);

	String getType(String token, String typeUuid);

	// 增加类型
	/**
	 * @Descriptionname 类型值 token 鉴权参数 type 类型(组织架构类型org_type,岗位类型job_type)
	 *                  corpId 租户id familyTypeId
	 *                  族谱类型ID：0组织架构、1客户架构、2法人架构、3供方架构、4国家架构
	 *
	 * @param token
	 * @param name
	 * @param corpId
	 * @return
	 */
	String addType(String token, String name, String corpId);

	// 修改类型
	/**
	 * 
	 * @Description typeUuid name token type(组织架构类型org_type,岗位类型job_type))
	 *              corpId familyTypeId
	 * @param typeUuid
	 * @param name
	 * @param token
	 * @param corpId
	 * @return
	 */
	String updateType(String typeUuid, String name, String token, String corpId);

	// 批量查询类型
	String selectTypeBath(Map<String, String> param);

	// 根据条件查询类型
	String selectTypeSearch(Map<String, String> param);

	// 根据类型查询
	String selectTypeList(Map<String, String> param);
	
	//动态保存类型
	String saveTypeList(List<TypeContent>typeContent,int typeNum,List<TypeContent>newTypeContent,String token,String typeUuids);
	
	//保存类型
	String saveOrgType(TypeBean typeBean,Map<String, String> param);

	String delOrgType(String typeUuid, Map<String, String> map);

	String updateOrgType(TypeBean typeBean, Map<String, String> map);

}
