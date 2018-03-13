package com.zzxt.entity;

import java.io.Serializable;

/**
 * 
 * @ClassName: UserAccount
 * @Description: 登录账户实体
 * @author wangdekun
 * @date 2017年8月21日 下午3:27:51
 *
 */
public class UserAccountEntity implements Serializable {

	
	private static final long serialVersionUID = -588652573662578925L;
	private String username;// 用户名
	private String password; // 用户密码
	private String corpId; // 租户ID
	private String sign; // 签名
	private String timestamp;// 时间戳
	private String appID;// 调用者APPid

	public UserAccountEntity() {
	}

	public UserAccountEntity(String username, String password, String corpId, String sign, String timestamp, String appID) {
		super();
		this.username = username;
		this.password = password;
		this.corpId = corpId;
		this.sign = sign;
		this.timestamp = timestamp;
		this.appID = appID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password.trim();
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	@Override
	public String toString() {
		return "UserAccount [username=" + username + ", password=" + password + ", corpId=" + corpId + ", sign=" + sign
				+ ", timestamp=" + timestamp + ", appID=" + appID + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appID == null) ? 0 : appID.hashCode());
		result = prime * result + ((corpId == null) ? 0 : corpId.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((sign == null) ? 0 : sign.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccountEntity other = (UserAccountEntity) obj;
		if (appID == null) {
			if (other.appID != null)
				return false;
		} else if (!appID.equals(other.appID))
			return false;
		if (corpId == null) {
			if (other.corpId != null)
				return false;
		} else if (!corpId.equals(other.corpId))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (sign == null) {
			if (other.sign != null)
				return false;
		} else if (!sign.equals(other.sign))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
