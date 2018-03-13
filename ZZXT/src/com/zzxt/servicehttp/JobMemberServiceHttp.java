package com.zzxt.servicehttp;

import java.util.Map;

/**
 * 岗位成员相关
 * @author think
 *
 */
public interface JobMemberServiceHttp {
	/**
	 * 获取岗位成员
	 * @param map
	 * @return
	 */
	String getJobMemberJson(Map<String, String> map);

}
