package com.zzxt.bean;

import java.io.Serializable;

public class TreeBean implements Serializable {

	// id:'01', name:'n1', isParent:true
	// "orgUuid":"17b42378-e757-4101-be6e-9b5e49f6f13e",
	// "parentId":"ba85a533-b1c3-4c80-a7e6-725cd78b2a60",
	// "name":"法人1.4",

	/**
	 * 
	 */
	private static final long serialVersionUID = -7755169062845191642L;
	private String parentId;
	private String orgUuid;
	private String name;
	private Boolean isParent;
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getOrgUuid() {
		return orgUuid;
	}
	public void setOrgUuid(String orgUuid) {
		this.orgUuid = orgUuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	@Override
	public String toString() {
		return "TreeBean [parentId=" + parentId + ", orgUuid=" + orgUuid + ", name=" + name + ", isParent=" + isParent
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isParent == null) ? 0 : isParent.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orgUuid == null) ? 0 : orgUuid.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
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
		TreeBean other = (TreeBean) obj;
		if (isParent == null) {
			if (other.isParent != null)
				return false;
		} else if (!isParent.equals(other.isParent))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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
		return true;
	}
	
	

}
