package com.zzxt.bean;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.zzxt.util.Md5Util;

public class UserAccountBean {

	// "accountUuid": "858420ba-28fb-44a8-8997-ed0b9e5475c4",
	// "corpId": "a8c58297436f433787725a94f780a3c9",
	// "createTs": "2013-11-07 17:28:27",
	// "dr": 0,
	// "email": "180207093@163.com",
	// "jobType": "大区总经理测试",
	// "jobUuid": "a83c8d9c-ac28-41da-b44d-7a9fc7b54ab7",
	// "landline": "111",
	// "mobile": "18020709359",
	// "name": "慕容复",
	// "orgName": "测试架构22 ",
	// "orgUuid": "a78820c4-d6db-491b-bcfd-1e476a21a9c5",
	// "password": "",
	// "sex": 1,
	// "status": 0,
	// "updateTs": "2017-07-20 11:42:57",
	// "username": "mytest",
	// "job": [

	private String accountUuid;
	private String corpId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTs;
	private String dr;
	private String email;
	private String jobType;
	private String jobUuid;
	private String landline;
	private String mobile;
	private String name;
	private String orgName;
	private String orgUuid;
	private String password;
	private String sex;
	private String status;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTs;
	private String username;
	private List<JobBean> job;

	public UserAccountBean() {
		super();
	}

	public String getAccountUuid() {
		return accountUuid;
	}

	public void setAccountUuid(String accountUuid) {
		this.accountUuid = accountUuid;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

	public String getDr() {
		return dr;
	}

	public void setDr(String dr) {
		this.dr = dr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobUuid() {
		return jobUuid;
	}

	public void setJobUuid(String jobUuid) {
		this.jobUuid = jobUuid;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgUuid() {
		return orgUuid;
	}

	public void setOrgUuid(String orgUuid) {
		this.orgUuid = orgUuid;
	}

	public String getPassword() {
		
		//return password;
		return Md5Util.md5(password);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateTs() {
		return updateTs;
	}

	public void setUpdateTs(Date updateTs) {
		this.updateTs = updateTs;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<JobBean> getJob() {
		return job;
	}

	public void setJob(List<JobBean> job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "UserAccountBean [accountUuid=" + accountUuid + ", corpId=" + corpId + ", createTs="
				+ createTs + ", dr=" + dr + ", email=" + email + ", jobType=" + jobType
				+ ", jobUuid=" + jobUuid + ", landline=" + landline + ", mobile=" + mobile
				+ ", name=" + name + ", orgName=" + orgName + ", orgUuid=" + orgUuid + ", password="
				+ password + ", sex=" + sex + ", status=" + status + ", updateTs=" + updateTs
				+ ", username=" + username + ", job=" + job + "]";
	}

}
