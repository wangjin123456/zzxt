package com.zzxt.servicehttp;

import java.util.Map;

/**
 * 
 * @ClassName: AdministrativeDivisionServiceHttp
 * @Description: 调用ICE微服务 对类型进行 CRUD
 * @author 
 * @date 2017年9月8日 下午4:46:31
 *
 */
public interface AdministrativeDivisionServiceHttp {
	/**
	 * 获取管理地区
	 */
	String getAdministrativeDivision(String token,String pid);

}
