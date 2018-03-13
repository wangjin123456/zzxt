package com.zzxt.servicehttp;

import com.zzxt.bean.UserAccountBean;

/**
 * 
 * @ClassName: LoginServiceHttp
 * @Description: TODO 向微服务 发起请求，匹配账户信息
 * @author wangdekun
 * @date 2017年8月23日 上午11:09:55
 *
 */
public interface LoginServiceHttp {
	/**
	 * @Description 发起匹配账户请求获取结果
	 * 
	 * @param UserAccountEntity
	 *            账户信息
	 * @return String json 返回处理结果信息
	 */
	String matchAccount(UserAccountBean user);
	
	// boolean checkZZXTSupperUser(String user);
	
	public boolean checkZZXTSupperUser(String accessToken, String user);

}
