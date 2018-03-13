package com.zzxt.entity;

import javax.servlet.http.HttpServletRequest;

/**
 * 法人代表
 * @author think
 *
 */
public class LegalPersonEntity {

	
	private Integer id;
	private String legalPerson;
	private String identity;
	private String identityType;
	private String phone;
	
	
	
	public LegalPersonEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LegalPersonEntity(String legalPerson, String identity, String identityType, String phone) {
		super();
		this.legalPerson = legalPerson;
		this.identity = identity;
		this.identityType = identityType;
		this.phone = phone;
	}
	
	public LegalPersonEntity getPamValue(HttpServletRequest re) {
		
		String pid = re.getParameter("legalPersonId");
		if(pid != null && pid.trim().equals("") == false) {
	 
		    this.setId(Integer.valueOf(pid));
		
		}
		
		this.legalPerson=re.getParameter("legalPerson");
		this.identity=re.getParameter("identity");
		this.identityType=re.getParameter("identityType");
		this.phone=re.getParameter("phone");
		
		return this;
	}
	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getLegalPerson() {
		return legalPerson;
	}
	
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	
	
	public String getIdentity() {
		return identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}
	
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
//	
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getLegalPerson() {
//		return legalPerson;
//	}
//	public void setLegalPerson(String legalPerson) {
//		this.legalPerson = legalPerson;
//	}
//	public String getLegalPersonIdentity() {
//		return legalPersonIdentity;
//	}
//	public void setLegalPersonIdentity(String legalPersonIdentity) {
//		this.legalPersonIdentity = legalPersonIdentity;
//	}
//	public String getLegalPersonIdentityType() {
//		return legalPersonIdentityType;
//	}
//	public void setLegalPersonIdentityType(String legalPersonIdentityType) {
//		this.legalPersonIdentityType = legalPersonIdentityType;
//	}
//	public String getContactMobile() {
//		return contactMobile;
//	}
//	public void setContactMobile(String contactMobile) {
//		this.contactMobile = contactMobile;
//	}

}
