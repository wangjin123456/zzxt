package com.zzxt.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 岗位成员
 * @author think
 *
 */

public class JobMember implements Serializable{
	 /*"jobUuid": "000777c0-ca8e-41fc-8ba3-1f922a6cd6f2", 
     "corpId": "a8c58297436f433787725a94f780a3c9", 
     "jobType": "部门经理", 
     "typeId": "52", 
     "classify": "", 
     "establishment": 0, 
     "onJob": 0, 
     "orgUuid": "2d05f354-6e40-4126-b7d3-cf794b209f14", 
     "orgName": "浙江成诚综合管理部", 
     "dr": null, 
     "status": 0, 
     "updateTs": "2017-08-24 08:34:17", 
     "createTs": "2016-06-07 14:28:16"*/
	
	private String jobUuid;
	private String corpId;
	private String jobType;
	private String typeId;
	
	private String classify;
	private Integer establishment;
	private Integer onJob;
	private String orgUuid;
	
	private String orgName;
	private String dr;
	private Integer status;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTs;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTs;
	public String getJobUuid() {
		return jobUuid;
	}
	public void setJobUuid(String jobUuid) {
		this.jobUuid = jobUuid;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public Integer getEstablishment() {
		return establishment;
	}
	public void setEstablishment(Integer establishment) {
		this.establishment = establishment;
	}
	public Integer getOnJob() {
		return onJob;
	}
	public void setOnJob(Integer onJob) {
		this.onJob = onJob;
	}
	public String getOrgUuid() {
		return orgUuid;
	}
	public void setOrgUuid(String orgUuid) {
		this.orgUuid = orgUuid;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getDr() {
		return dr;
	}
	public void setDr(String dr) {
		this.dr = dr;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getUpdateTs() {
		return updateTs;
	}
	public void setUpdateTs(Date updateTs) {
		this.updateTs = updateTs;
	}
	public Date getCreateTs() {
		return createTs;
	}
	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classify == null) ? 0 : classify.hashCode());
		result = prime * result + ((corpId == null) ? 0 : corpId.hashCode());
		result = prime * result + ((createTs == null) ? 0 : createTs.hashCode());
		result = prime * result + ((dr == null) ? 0 : dr.hashCode());
		result = prime * result + ((establishment == null) ? 0 : establishment.hashCode());
		result = prime * result + ((jobType == null) ? 0 : jobType.hashCode());
		result = prime * result + ((jobUuid == null) ? 0 : jobUuid.hashCode());
		result = prime * result + ((onJob == null) ? 0 : onJob.hashCode());
		result = prime * result + ((orgName == null) ? 0 : orgName.hashCode());
		result = prime * result + ((orgUuid == null) ? 0 : orgUuid.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
		result = prime * result + ((updateTs == null) ? 0 : updateTs.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobMember other = (JobMember) obj;
		if (classify == null) {
			if (other.classify != null)
				return false;
		} else if (!classify.equals(other.classify))
			return false;
		if (corpId == null) {
			if (other.corpId != null)
				return false;
		} else if (!corpId.equals(other.corpId))
			return false;
		if (createTs == null) {
			if (other.createTs != null)
				return false;
		} else if (!createTs.equals(other.createTs))
			return false;
		if (dr == null) {
			if (other.dr != null)
				return false;
		} else if (!dr.equals(other.dr))
			return false;
		if (establishment == null) {
			if (other.establishment != null)
				return false;
		} else if (!establishment.equals(other.establishment))
			return false;
		if (jobType == null) {
			if (other.jobType != null)
				return false;
		} else if (!jobType.equals(other.jobType))
			return false;
		if (jobUuid == null) {
			if (other.jobUuid != null)
				return false;
		} else if (!jobUuid.equals(other.jobUuid))
			return false;
		if (onJob == null) {
			if (other.onJob != null)
				return false;
		} else if (!onJob.equals(other.onJob))
			return false;
		if (orgName == null) {
			if (other.orgName != null)
				return false;
		} else if (!orgName.equals(other.orgName))
			return false;
		if (orgUuid == null) {
			if (other.orgUuid != null)
				return false;
		} else if (!orgUuid.equals(other.orgUuid))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (typeId == null) {
			if (other.typeId != null)
				return false;
		} else if (!typeId.equals(other.typeId))
			return false;
		if (updateTs == null) {
			if (other.updateTs != null)
				return false;
		} else if (!updateTs.equals(other.updateTs))
			return false;
		return true;
	}
}
