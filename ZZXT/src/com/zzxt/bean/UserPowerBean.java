package com.zzxt.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 应用基本信息实体
 * @author think
 *
 */
public class UserPowerBean {

	Integer id ;
	
	String accountUuid;
	
	String corpId;
	
	String userName;
	
	String name;
	
	String mobile;
	
	String email;
	
	Integer sex;
	
	String jobType;
	
	String jobUuid;
	
	Integer sub;
	
	Integer power;
	
	
	

	public UserPowerBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserPowerBean(Integer id, String accountUuid, String corpId, String userName, String name, String mobile,
			String email, Integer sex, String jobType, String jobUuid, Integer sub, Integer power) {
		super();
		this.id = id;
		this.accountUuid = accountUuid;
		this.corpId = corpId;
		this.userName = userName;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.sex = sex;
		this.jobType = jobType;
		this.jobUuid = jobUuid;
		this.sub = sub;
		this.power = power;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
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

	public Integer getSub() {
		return sub;
	}

	public void setSub(Integer sub) {
		this.sub = sub;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}
	
	
	

}
