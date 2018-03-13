package com.zzxt.bean;

import java.io.Serializable;
import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @ClassName: LicenseEntity
 * @Description: 证照实体
 * @author wangdekun
 * @date 2017年8月28日 下午3:45:04
 *
 */

public class LicenseLegalPersonBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 783192074995534632L;
	private Integer id; // id` int(11)
	private String uscCode; // Unified_social_credit_code varchar(500)统一社会信用代码
	private String orgCode;//组织机构代码
	private String regNum;//注册号
	
	private String province;
	// "city": null,
	private String city;
	// "region": null,
	private String region;
	// "longitude": null,
	// "orgType": null,
	private String orgType;
	//管理组织架构orgUUid
	private String manageOrgUuid;
	
	private String legalName;//法人代表

	private String name;
	
	private String identity;
	
	private String identityType;
	
	private String phone;
	
	private String licStatus;// `licStatus` varchar(500) '经营状态',
	private String industry;//所属行业
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	@JSONField (format="yyyy-MM-dd")
	private Date eDate;// `Establishment_date` datetime '成立日期',
	private String licType; // `Type` varchar(500) '公司类型',
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	@JSONField (format="yyyy-MM-dd")
	private Date startDate;// `start_date` datetime '营业期限自',
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	@JSONField (format="yyyy-MM-dd")
	private Date endDate;// `end_date` datetime '营业期限至',
	private int legalPerson_id; // `name` varchar(500) '法定代表人id',
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField (format="yyyy-MM-dd")
	private Date aDate;// `approval_date` datetime '核准日期',
	private String capital;// `Capital` varchar(500) '注册资本',
	private String ra;// `Registration_authority` varchar(500) '登记机关',
	private String address;// `address` varchar(500) '地址',
	private String licScope;// `licScope` varchar(1000) '经营范围',
	private String licPath;// `licPath` varchar(500) '附件存储路径',
	
	private String cName; // `Company_name` varchar(500) '企业名称',
	private String remark;// `remark` varchar(500) '备注',
	private Date addTime;//添加时间
	@JSONField (format="yyyy-MM-dd")
	private Date updateTime;//修改时间
	
	private String orgUuid;//架构ID
	
	


	public LicenseLegalPersonBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	

	public Integer getId() {
		return id;
	}






	public void setId(Integer id) {
		this.id = id;
	}






	public String getUscCode() {
		return uscCode;
	}






	public void setUscCode(String uscCode) {
		this.uscCode = uscCode;
	}






	public String getOrgCode() {
		return orgCode;
	}






	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}






	public String getRegNum() {
		return regNum;
	}






	public void setRegNum(String regNum) {
		this.regNum = regNum;
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






	public String getOrgType() {
		return orgType;
	}






	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}






	public String getManageOrgUuid() {
		return manageOrgUuid;
	}






	public void setManageOrgUuid(String manageOrgUuid) {
		this.manageOrgUuid = manageOrgUuid;
	}






	public String getLegalName() {
		return legalName;
	}






	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}






	public String getName() {
		return name;
	}






	public void setName(String name) {
		this.name = name;
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






	public String getLicStatus() {
		return licStatus;
	}






	public void setLicStatus(String licStatus) {
		this.licStatus = licStatus;
	}






	public String getIndustry() {
		return industry;
	}






	public void setIndustry(String industry) {
		this.industry = industry;
	}






	public Date geteDate() {
		return eDate;
	}






	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}






	public String getLicType() {
		return licType;
	}






	public void setLicType(String licType) {
		this.licType = licType;
	}






	public Date getStartDate() {
		return startDate;
	}






	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}






	public Date getEndDate() {
		return endDate;
	}






	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}






	public int getLegalPerson_id() {
		return legalPerson_id;
	}






	public void setLegalPerson_id(int legalPerson_id) {
		this.legalPerson_id = legalPerson_id;
	}






	public Date getaDate() {
		return aDate;
	}






	public void setaDate(Date aDate) {
		this.aDate = aDate;
	}






	public String getCapital() {
		return capital;
	}






	public void setCapital(String capital) {
		this.capital = capital;
	}






	public String getRa() {
		return ra;
	}






	public void setRa(String ra) {
		this.ra = ra;
	}






	public String getAddress() {
		return address;
	}






	public void setAddress(String address) {
		this.address = address;
	}






	public String getLicScope() {
		return licScope;
	}






	public void setLicScope(String licScope) {
		this.licScope = licScope;
	}






	public String getLicPath() {
		return licPath;
	}






	public void setLicPath(String licPath) {
		this.licPath = licPath;
	}






	public String getcName() {
		return cName;
	}






	public void setcName(String cName) {
		this.cName = cName;
	}






	public String getRemark() {
		return remark;
	}






	public void setRemark(String remark) {
		this.remark = remark;
	}






	public Date getAddTime() {
		return addTime;
	}






	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}






	public Date getUpdateTime() {
		return updateTime;
	}






	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}






	public String getOrgUuid() {
		return orgUuid;
	}






	public void setOrgUuid(String orgUuid) {
		this.orgUuid = orgUuid;
	}






	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	


}
