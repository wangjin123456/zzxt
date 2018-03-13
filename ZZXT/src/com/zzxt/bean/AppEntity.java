package com.zzxt.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 应用基本信息实体
 * @author think
 *
 */
public class AppEntity {
	 private String id; 
     private String appUuid; 
     private String createBy; 
     private String appName; 
     private String appSecret; 
     private String ip; 
     private String url; 
     private String memo; 
     private String status; 
     private String deleted;
     @DateTimeFormat(pattern="yyyy-MM-dd") 
     private Date creationTime; 
     @DateTimeFormat(pattern="yyyy-MM-dd") 
     private Date modifiedTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAppUuid() {
		return appUuid;
	}
	public void setAppUuid(String appUuid) {
		this.appUuid = appUuid;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	@Override
	public String toString() {
		return "appEntity [id=" + id + ", appUuid=" + appUuid + ", createBy=" + createBy + ", appName=" + appName
				+ ", appSecret=" + appSecret + ", ip=" + ip + ", url=" + url + ", memo=" + memo + ", status=" + status
				+ ", deleted=" + deleted + ", creationTime=" + creationTime + ", modifiedTime=" + modifiedTime + "]";
	}
}
