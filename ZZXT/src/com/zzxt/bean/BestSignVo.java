package com.zzxt.bean;

import com.zzxt.entity.LicenseEntity;

/**
 * 上上签参数分装
 * @author think
 *
 */

public class BestSignVo extends LicenseEntity{
	  /*account 账号，即用户唯一标识
	  mail 用户邮箱，如果用户仅有手机号，则该参数可以为空
	  mobile 用户手机号，如果用户没有邮箱则必须填手机号
	  name 名称，个人填写姓名，企业填写企业名称
	  userType 签署者用户类型：1表示个人类型 ，2表示企业类型
	  signatureImageData 图片
	  */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String legalPerson;
	private String legalPersonIdentity;
	private String legalPersonIdentityType;
	private String contactMobile;
	private byte[] signatureImageData;
	
	
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getLegalPersonIdentity() {
		return legalPersonIdentity;
	}
	public void setLegalPersonIdentity(String legalPersonIdentity) {
		this.legalPersonIdentity = legalPersonIdentity;
	}
	public String getLegalPersonIdentityType() {
		return legalPersonIdentityType;
	}
	public void setLegalPersonIdentityType(String legalPersonIdentityType) {
		this.legalPersonIdentityType = legalPersonIdentityType;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	public byte[] getSignatureImageData() {
		return signatureImageData;
	}
	public void setSignatureImageData(byte[] signatureImageData) {
		this.signatureImageData = signatureImageData;
	}
	

}
