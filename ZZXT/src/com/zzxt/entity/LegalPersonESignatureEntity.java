package com.zzxt.entity;

import java.io.Serializable;

public class LegalPersonESignatureEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String lpId;
	private Integer esId;
	
	
	public LegalPersonESignatureEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LegalPersonESignatureEntity(String lpId, Integer esId) {
		super();
		this.lpId = lpId;
		this.esId = esId;
	}

	public LegalPersonESignatureEntity(String lpId, Integer esId, boolean holder) {
		super();
		this.lpId = lpId;
		this.esId = esId;
	}

	
	public void setId(Integer id) {
		
		this.id = id;
	}
	
	public Integer getId() {
		
		return this.id;
	}
	
	
	public String getLpId() {
		return lpId;
	}

	public void setLpId(String lpId) {
		this.lpId = lpId;
	}

	public Integer getEsId() {
		return esId;
	}

	public void setEcId(Integer esId) {
		this.esId = esId;
	}	
}
