package com.zzxt.entity;

import java.io.Serializable;

public class LegalPersonUserEntity implements Serializable {

	Integer id;
	
	String orgUuid;
	
	Integer uid;
	
	Integer sub;
	
	Integer power;
	
	
	private static final long serialVersionUID = 1L;

	
	
	

	public LegalPersonUserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LegalPersonUserEntity(String orgUuid, Integer uid, Integer sub, Integer power) {
		super();
		this.orgUuid = orgUuid;
		this.uid = uid;
		this.sub = sub;
		this.power = power;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getOrgUuid() {
		return orgUuid;
	}


	public void setOrgUuid(String orgUuid) {
		this.orgUuid = orgUuid;
	}


	public Integer getUid() {
		return uid;
	}


	public void setUid(Integer uid) {
		this.uid = uid;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
