package com.zzxt.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EContractEntity implements Serializable {

	private static final long serialVersionUID = 1L;


	private Integer id;
	
	private String orgUuid;
	
	private String title;
	
	private String fileName;
	
	private String name;
	
	private String contract;
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date signTime;
	
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date destroyTime;
	
	
	private Integer state;
	
	private String fId;
	
	private String contractId;
	
	private String comment;
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date deadline;

	private String returnURL;
	
	


	public void setId(Integer id) {
		
		this.id = id;
	}
	
	public Integer getId() {
		
		return this.id;
	}
	

	public void setOrgUuid(String orgUuid) {
		this.orgUuid = orgUuid;
	}

	public String getOrgUuid() {
		return orgUuid;
	}
	
	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getFileName() {
		return fileName;
	}




	public void setFileName(String fileName) {
		this.fileName = fileName;
	}




	public String getContract() {
		
		return this.contract;
	}

	
	public void setContract(String contract) {
		
		this.contract = contract;
	}


	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public Date getCreateTime() {
		return createTime;
	}




	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}




	public Date getSignTime() {
		return signTime;
	}




	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}




	public Date getDestroyTime() {
		return destroyTime;
	}




	public void setDestroyTime(Date destroyTime) {
		this.destroyTime = destroyTime;
	}




	public Integer getState() {
		return state;
	}




	public void setState(Integer state) {
		this.state = state;
	}




	public String getfId() {
		return fId;
	}




	public void setfId(String fId) {
		this.fId = fId;
	}




	public String getContractId() {
		return contractId;
	}




	public void setContractId(String contractId) {
		this.contractId = contractId;
	}




	public String getComment() {
		return comment;
	}




	public void setComment(String comment) {
		this.comment = comment;
	}


	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	

	public String getReturnURL() {
		return returnURL;
	}

	public void setReturnURL(String returnURL) {
		this.returnURL = returnURL;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	 
	
	
	

	@Override
	public String toString() {
		return "EContractEntity [id=" + id + ", + orgUuid=" + orgUuid + ",title=" + title + ", name=" + name + ", createTime="
				+ createTime + ",  state=" + state
				+ ", comment=" + comment + ", contractId=" + contractId + ", fId=" + fId
				+ "]";
	}
	
}
