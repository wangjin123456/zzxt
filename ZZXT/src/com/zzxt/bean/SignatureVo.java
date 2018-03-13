package com.zzxt.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 页面提交的电子签章实体
 * @author think
 *
 */

public class SignatureVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String sid;
	private int cid;
	private String sname;
	@JSONField (format="yyyy-MM-dd HH:mm:ss")  
	private Date date;
	private String picUrl;
	private String orgUuid;
	private String summary;
	

	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getOrgUuid() {
		return orgUuid;
	}
	public void setOrgUuid(String orgUuid) {
		this.orgUuid = orgUuid;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@Override
	public String toString() {
		return "SignatureVo [sid=" + sid + ", cid=" + cid + ", sname=" + sname + ", date=" + date + ", picUrl=" + picUrl
				+ ", orgUuid=" + orgUuid + ", summary=" + summary + "]";
	}

	
	
}
