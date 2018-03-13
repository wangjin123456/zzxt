package com.zzxt.entity;

import java.io.Serializable;

public class LegalPersonEContractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String lpId;
	private Integer ecId;
	private Integer state;
	private Integer holder;
	
	
	
	
	
	public LegalPersonEContractEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LegalPersonEContractEntity(String lpId, Integer ecId, Integer state, Integer holder) {
		super();
		this.lpId = lpId;
		this.ecId = ecId;
		this.state = state;
		this.holder = holder;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLpId() {
		return lpId;
	}
	public void setLpId(String lpId) {
		this.lpId = lpId;
	}
	public Integer getEcId() {
		return ecId;
	}
	public void setEcId(Integer ecId) {
		this.ecId = ecId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getHolder() {
		return holder;
	}
	public void setHolder(Integer holder) {
		this.holder = holder;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
 
}
