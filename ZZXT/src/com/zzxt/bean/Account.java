/**   
* @Title: Account.java 
* @Package com.zzxt.bean 
* @Description: 封装登录的用户名密码
* @author  wangdekun   
* @date 2017年9月11日 下午3:17:35 
* @version V1.0   
*/
package com.zzxt.bean;

/**
 * @ClassName: Account
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wangdekun
 * @date 2017年9月11日 下午3:17:35
 * 
 */
public class Account {
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
