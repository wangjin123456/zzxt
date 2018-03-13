package com.zzxt.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrgBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5707721195928374057L;
	private Boolean isParent;
	// "orgUuid": "17b42378-e757-4101-be6e-9b5e49f6f13e",
	private String orgUuid;
	// "corpId": "a8c58297436f433787725a94f780a3c9",
	private String corpId;
	// "parentId": "ba85a533-b1c3-4c80-a7e6-725cd78b2a60",
	private String parentId;
	// "dn": "ou=法人1.4,ou=法人1,dc=a8c58297436f433787725a94f780a3c9",
	private String dn;
	// "name": "法人1.4",
	private String name;
	// "province": null,
	private String province;
	// "city": null,
	private String city;
	// "region": null,
	private String region;
	// "longitude": null,
	private String longitude;
	// "latitude": null,
	private String latitude;
	// "orgType": null,
	private String orgType;
	// "orgTypeId": null,
	private String orgTypeId;
	// "dr": null,
	private String dr;
	// "status": 0,
	private String status;
	// "memo": "0",
	private String memo;
	// "updateTs": "2017-08-30 09:01:06",
	private Date updateTs;
	// "createTs": "2017-08-30 09:01:07",
	private Date createTs;
	// "familyType": "组织架构",
	private String familyType;
	// "familyTypeId": "0",
	private String familyTypeId;
	// "sort": 0
	private String sort;
	
	private List<OrgBean>orgBeanList=new ArrayList<OrgBean>();

	public List<OrgBean> getOrgBeanList() {
		return orgBeanList;
	}

	public void setOrgBeanList(List<OrgBean> orgBeanList) {
		this.orgBeanList = orgBeanList;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public String getOrgUuid() {
		return orgUuid;
	}

	public void setOrgUuid(String orgUuid) {
		this.orgUuid = orgUuid;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getParentId() {
		if(parentId==null||"".equals(parentId)){
			return  String.valueOf(0);
		}
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(String orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public String getDr() {
		return dr;
	}

	public void setDr(String dr) {
		this.dr = dr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getFamilyType() {
		return familyType;
	}

	public void setFamilyType(String familyType) {
		this.familyType = familyType;
	}

	public String getFamilyTypeId() {
		return familyTypeId;
	}

	public void setFamilyTypeId(String familyTypeId) {
		this.familyTypeId = familyTypeId;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((corpId == null) ? 0 : corpId.hashCode());
		result = prime * result + ((createTs == null) ? 0 : createTs.hashCode());
		result = prime * result + ((dn == null) ? 0 : dn.hashCode());
		result = prime * result + ((dr == null) ? 0 : dr.hashCode());
		result = prime * result + ((familyType == null) ? 0 : familyType.hashCode());
		result = prime * result + ((familyTypeId == null) ? 0 : familyTypeId.hashCode());
		result = prime * result + ((isParent == null) ? 0 : isParent.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((memo == null) ? 0 : memo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orgType == null) ? 0 : orgType.hashCode());
		result = prime * result + ((orgTypeId == null) ? 0 : orgTypeId.hashCode());
		result = prime * result + ((orgUuid == null) ? 0 : orgUuid.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((sort == null) ? 0 : sort.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		OrgBean other = (OrgBean) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
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
		if (dn == null) {
			if (other.dn != null)
				return false;
		} else if (!dn.equals(other.dn))
			return false;
		if (dr == null) {
			if (other.dr != null)
				return false;
		} else if (!dr.equals(other.dr))
			return false;
		if (familyType == null) {
			if (other.familyType != null)
				return false;
		} else if (!familyType.equals(other.familyType))
			return false;
		if (familyTypeId == null) {
			if (other.familyTypeId != null)
				return false;
		} else if (!familyTypeId.equals(other.familyTypeId))
			return false;
		if (isParent == null) {
			if (other.isParent != null)
				return false;
		} else if (!isParent.equals(other.isParent))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (memo == null) {
			if (other.memo != null)
				return false;
		} else if (!memo.equals(other.memo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orgType == null) {
			if (other.orgType != null)
				return false;
		} else if (!orgType.equals(other.orgType))
			return false;
		if (orgTypeId == null) {
			if (other.orgTypeId != null)
				return false;
		} else if (!orgTypeId.equals(other.orgTypeId))
			return false;
		if (orgUuid == null) {
			if (other.orgUuid != null)
				return false;
		} else if (!orgUuid.equals(other.orgUuid))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (sort == null) {
			if (other.sort != null)
				return false;
		} else if (!sort.equals(other.sort))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (updateTs == null) {
			if (other.updateTs != null)
				return false;
		} else if (!updateTs.equals(other.updateTs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "orgBean [isParent=" + isParent + ", orgUuid=" + orgUuid + ", corpId=" + corpId + ", parentId="
				+ parentId + ", dn=" + dn + ", name=" + name + ", province=" + province + ", city=" + city + ", region="
				+ region + ", longitude=" + longitude + ", latitude=" + latitude + ", orgType=" + orgType
				+ ", orgTypeId=" + orgTypeId + ", dr=" + dr + ", status=" + status + ", memo=" + memo + ", updateTs="
				+ updateTs + ", createTs=" + createTs + ", familyType=" + familyType + ", familyTypeId=" + familyTypeId
				+ ", sort=" + sort + "]";
	}

}
