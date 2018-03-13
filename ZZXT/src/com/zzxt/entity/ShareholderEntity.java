package com.zzxt.entity;

/**
 * 股东信息实体
 * @author think
 *
 */
public class ShareholderEntity {
	
	private Integer id;//主键
	
	private String type;//股东类型
	
	private String name;//股东名称
	
	private String fund;//认缴出资（金额/时间）
	
	private String realFund;//实际出资（金额/时间）

	
	
	
	public ShareholderEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShareholderEntity(String type, String name, String fund, String realFund) {
		super();
		this.type = type;
		this.name = name;
		this.fund = fund;
		this.realFund = realFund;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFund() {
		return fund;
	}

	public void setFund(String fund) {
		this.fund = fund;
	}

	public String getRealFund() {
		return realFund;
	}

	public void setRealFund(String realFund) {
		this.realFund = realFund;
	}
	
	
	

//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getShareholderType() {
//		return shareholderType;
//	}
//
//	public void setShareholderType(String shareholderType) {
//		this.shareholderType = shareholderType;
//	}
//
//	public String getShareholderName() {
//		return shareholderName;
//	}
//
//	public void setShareholderName(String shareholderName) {
//		this.shareholderName = shareholderName;
//	}
//
//	public String getProjectedContribution() {
//		return projectedContribution;
//	}
//
//	public void setProjectedContribution(String projectedContribution) {
//		this.projectedContribution = projectedContribution;
//	}
//
//	public String getActualContribution() {
//		return actualContribution;
//	}
//
//	public void setActualContribution(String actualContribution) {
//		this.actualContribution = actualContribution;
//	}
//
//	public Date getAddTime() {
//		return addTime;
//	}
//
//	public void setAddTime(Date addTime) {
//		this.addTime = addTime;
//	}
//
//	public Date getUpdateTime() {
//		return updateTime;
//	}
//
//	public void setUpdateTime(Date updateTime) {
//		this.updateTime = updateTime;
//	}
//
//	public Integer getLicenseId() {
//		return licenseId;
//	}
//
//	public void setLicenseId(Integer licenseId) {
//		this.licenseId = licenseId;
//	}
//
//	public Shareholder(Integer id, String shareholderType, String shareholderName, String projectedContribution,
//			String actualContribution, Date addTime, Date updateTime, Integer licenseId) {
//		super();
//		this.id = id;
//		this.shareholderType = shareholderType;
//		this.shareholderName = shareholderName;
//		this.projectedContribution = projectedContribution;
//		this.actualContribution = actualContribution;
//		this.addTime = addTime;
//		this.updateTime = updateTime;
//		this.licenseId = licenseId;
//	}
//
//	public Shareholder() {
//		super();
//	}
//
//	@Override
//	public String toString() {
//		return "Shareholder [id=" + id + ", shareholderType=" + shareholderType + ", shareholderName=" + shareholderName
//				+ ", projectedContribution=" + projectedContribution + ", actualContribution=" + actualContribution
//				+ ", addTime=" + addTime + ", updateTime=" + updateTime + ", licenseId=" + licenseId + "]";
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((actualContribution == null) ? 0 : actualContribution.hashCode());
//		result = prime * result + ((addTime == null) ? 0 : addTime.hashCode());
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + ((licenseId == null) ? 0 : licenseId.hashCode());
//		result = prime * result + ((projectedContribution == null) ? 0 : projectedContribution.hashCode());
//		result = prime * result + ((shareholderName == null) ? 0 : shareholderName.hashCode());
//		result = prime * result + ((shareholderType == null) ? 0 : shareholderType.hashCode());
//		result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Shareholder other = (Shareholder) obj;
//		if (actualContribution == null) {
//			if (other.actualContribution != null)
//				return false;
//		} else if (!actualContribution.equals(other.actualContribution))
//			return false;
//		if (addTime == null) {
//			if (other.addTime != null)
//				return false;
//		} else if (!addTime.equals(other.addTime))
//			return false;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		if (licenseId == null) {
//			if (other.licenseId != null)
//				return false;
//		} else if (!licenseId.equals(other.licenseId))
//			return false;
//		if (projectedContribution == null) {
//			if (other.projectedContribution != null)
//				return false;
//		} else if (!projectedContribution.equals(other.projectedContribution))
//			return false;
//		if (shareholderName == null) {
//			if (other.shareholderName != null)
//				return false;
//		} else if (!shareholderName.equals(other.shareholderName))
//			return false;
//		if (shareholderType == null) {
//			if (other.shareholderType != null)
//				return false;
//		} else if (!shareholderType.equals(other.shareholderType))
//			return false;
//		if (updateTime == null) {
//			if (other.updateTime != null)
//				return false;
//		} else if (!updateTime.equals(other.updateTime))
//			return false;
//		return true;
//	}
//	
}
