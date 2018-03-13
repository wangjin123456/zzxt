package com.zzxt.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author think
 *类型实体
 */
public class TypeBean implements Serializable{
	
	/**
	 * "typeUuid": "d264be39-fb99-49a6-b4d2-934787210861", 
        "corpId": "a8c58297436f433787725a94f780a3c9", 
        "type": "org_type", 
        "name": "gfdgfdgfd", 
        "dr": null, 
        "updateTs": "2017-10-27 09:58:09", 
        "createTs": "2017-10-26 15:01:32", 
        "familyTypeId": "2"
	 */
	
	private String typeUuid;
    private String corpId;
    private String type;
    private String name;
    private String dr;
    private Date updateTs;
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
	@Override
	public String toString() {
		return "TypeBean [typeUuid=" + typeUuid + ", corpId=" + corpId + ", type=" + type + ", name=" + name + ", dr="
				+ dr + ", updateTs=" + updateTs + ", createTs=" + createTs + ", familyTypeId=" + familyTypeId + "]";
	}
	               

}
