package com.zzxt.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 类型实体
 * @author think
 *
 */
public class TypeContent {
	private String typeUuid;
	
	private String corpId;
	
	private String type;
	
	private String name;
	
	private String dr;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTs;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTs;
	
	private String familyTypeId;

	public String getTypeUuid() {
		return typeUuid;
	}

	public void setTypeUuid(String typeUuid) {
		this.typeUuid = typeUuid;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
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

	public String getDr() {
		return dr;
	}

	public void setDr(String dr) {
		this.dr = dr;
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

	public String getFamilyTypeId() {
		return familyTypeId;
	}

	public void setFamilyTypeId(String familyTypeId) {
		this.familyTypeId = familyTypeId;
	}

	public TypeContent(String typeUuid, String corpId, String type, String name, String dr, Date updateTs,
			Date createTs, String familyTypeId) {
		super();
		this.typeUuid = typeUuid;
		this.corpId = corpId;
		this.type = type;
		this.name = name;
		this.dr = dr;
		this.updateTs = updateTs;
		this.createTs = createTs;
		this.familyTypeId = familyTypeId;
	}

	public TypeContent() {
	
	}

	@Override
	public String toString() {
		return "typeContent [typeUuid=" + typeUuid + ", corpId=" + corpId + ", type=" + type + ", name=" + name
				+ ", dr=" + dr + ", updateTs=" + updateTs + ", createTs=" + createTs + ", familyTypeId=" + familyTypeId
				+ "]";
	}
	
	
	
}
