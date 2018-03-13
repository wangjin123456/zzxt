package com.zzxt.entity;

import java.io.Serializable;

public class LicenseShareholderEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer lid;
	private Integer sid;

	
	public LicenseShareholderEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getLid() {
		return lid;
	}


	public void setLid(Integer lid) {
		this.lid = lid;
	}


	public Integer getSid() {
		return sid;
	}


	public void setSid(Integer sid) {
		this.sid = sid;
	}

}
