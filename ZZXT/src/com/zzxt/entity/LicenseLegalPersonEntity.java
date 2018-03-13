package com.zzxt.entity;

import java.io.Serializable;

public class LicenseLegalPersonEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer lid;
	private Integer lpId;

	
	public LicenseLegalPersonEntity() {
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


	public Integer getLpId() {
		return lpId;
	}


	public void setLpId(Integer lpId) {
		this.lpId = lpId;
	}



}
