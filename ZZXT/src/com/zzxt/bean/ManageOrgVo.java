package com.zzxt.bean;

public class ManageOrgVo {
	private int level;//等级 如果parentId=0，level=0；
	private String orgUuid;
	private String parentId;
	private String name;
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getOrgUuid() {
		return orgUuid;
	}
	public void setOrgUuid(String orgUuid) {
		this.orgUuid = orgUuid;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ManageOrgVo [level=" + level + ", orgUuid=" + orgUuid + ", parentId=" + parentId + ", name=" + name
				+ "]";
	}
	

}
