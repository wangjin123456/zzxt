package com.zzxt.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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

public class LicenseEntity implements Serializable {
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
	
	private String reminder_oa;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField (format="yyyy-MM-dd")
	private Date  reminder_expire;
	
	private Integer reminder_days;
	
	private String reminder_content;   

	public LicenseEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LicenseEntity(String uscCode, String orgCode, String regNum, String province, String city, String region,
			String orgType, String manageOrgUuid, String legalName, String licStatus, String industry, Date eDate,
			String licType, Date startDate, Date endDate, int legalPerson_id, Date aDate, String capital, String ra,
			String address, String licScope, String licPath, String cName, String remark, Date addTime, Date updateTime,
			String orgUuid) {
		super();
		this.uscCode = uscCode;
		this.orgCode = orgCode;
		this.regNum = regNum;
		this.province = province;
		this.city = city;
		this.region = region;
		this.orgType = orgType;
		this.manageOrgUuid = manageOrgUuid;
		this.legalName = legalName;
		this.licStatus = licStatus;
		this.industry = industry;
		this.eDate = eDate;
		this.licType = licType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.legalPerson_id = legalPerson_id;
		this.aDate = aDate;
		this.capital = capital;
		this.ra = ra;
		this.address = address;
		this.licScope = licScope;
		this.licPath = licPath;
		this.cName = cName;
		this.remark = remark;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.orgUuid = orgUuid;
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

	
	
	
	
	public String getReminder_oa() {
		return reminder_oa;
	}

	public void setReminder_oa(String reminder_oa) {
		this.reminder_oa = reminder_oa;
	}

	public Date getReminder_expire() {
		return reminder_expire;
	}

	public void setReminder_expire(Date reminder_expire) {
		this.reminder_expire = reminder_expire;
	}


	public Integer getReminder_days() {
		return reminder_days;
	}


	public void setReminder_days(Integer reminder_days) {
		this.reminder_days = reminder_days;
	}


	public String getReminder_content() {
		return reminder_content;
	}

	public void setReminder_content(String reminder_content) {
		this.reminder_content = reminder_content;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
//
//	public LicenseEntity() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	
//	
//
//	public LicenseEntity(Integer id, String uscCode, String orgCode, String regNum, String province, String city,
//			String region, String orgType, String manageOrgUuid, String legalName, String licStatus, String industry,
//			Date eDate, String licType, Date startDate, Date endDate, int legalPerson_id, Date aDate, String capital,
//			String ra, String address, String licScope, String licPath, String cName, String remark, Date addTime,
//			Date updateTime, String orgUuid) {
//		super();
//		this.id = id;
//		this.uscCode = uscCode;
//		this.orgCode = orgCode;
//		this.regNum = regNum;
//		this.province = province;
//		this.city = city;
//		this.region = region;
//		this.orgType = orgType;
//		this.manageOrgUuid = manageOrgUuid;
//		this.legalName = legalName;
//		this.licStatus = licStatus;
//		this.industry = industry;
//		this.eDate = eDate;
//		this.licType = licType;
//		this.startDate = startDate;
//		this.endDate = endDate;
//		this.legalPerson_id = legalPerson_id;
//		this.aDate = aDate;
//		this.capital = capital;
//		this.ra = ra;
//		this.address = address;
//		this.licScope = licScope;
//		this.licPath = licPath;
//		this.cName = cName;
//		this.remark = remark;
//		this.addTime = addTime;
//		this.updateTime = updateTime;
//		this.orgUuid = orgUuid;
//	}
//
//
	public LicenseEntity getPamValue(HttpServletRequest re) throws ParseException{
		SimpleDateFormat myFmt1=new SimpleDateFormat("yyyy-MM-dd");
		//SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss HH:mm:ss");
		String idStr = re.getParameter("id");
		if (idStr != null && !"".equals(idStr)) {
			this.id = Integer.parseInt(idStr);
		}
		
		this.uscCode=re.getParameter("uscCode");
		this.orgCode=re.getParameter("orgCode");
		this.regNum=re.getParameter("regNum");
		this.province=re.getParameter("province");
		this.city=re.getParameter("city");
		this.region=re.getParameter("region");
		this.orgType=re.getParameter("orgType");
		this.manageOrgUuid=re.getParameter("manageOrgUuid");
		this.legalName = re.getParameter("legalName");
		this.licStatus=re.getParameter("licStatus");
		this.industry=re.getParameter("industry");
		
		String o = re.getParameter("eDate");
		if(o != null && !o.trim().equals("")) {
			this.eDate= myFmt1.parse(o.toString());
		}
		
		this.licType=re.getParameter("licType");
		
		o = re.getParameter("startDate");
		if(o != null && !o.trim().equals("")) {
			
			this.startDate=myFmt1.parse(o.toString());
		}
		
		o = re.getParameter("endDate");
		if(o != null && !o.trim().equals("")) {
			this.endDate=myFmt1.parse(o.toString());
		}
		
		String legalPerson_idStr = re.getParameter("legalPerson_id");
		if (legalPerson_idStr != null && !"".equals(legalPerson_idStr)) {
			this.legalPerson_id = Integer.parseInt(legalPerson_idStr);
		}
		
		o = re.getParameter("aDate");
		if(o != null && !o.trim().equals("")) {
			this.aDate=myFmt1.parse(o.toString());
		}
		
		this.capital=re.getParameter("capital");
		this.address=re.getParameter("address");
		this.licPath=re.getParameter("licPath");
		this.licScope=re.getParameter("licScope");
		this.cName=re.getParameter("cName");
		this.ra=re.getParameter("ra");
		this.remark=re.getParameter("remark");
		this.orgUuid=re.getParameter("orgUuid");
		
		return this;
	}
//	
//	
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getUscCode() {
//		return uscCode;
//	}
//
//	public void setUscCode(String uscCode) {
//		this.uscCode = uscCode;
//	}
//
//	public String getcName() {
//		return cName;
//	}
//
//	public void setcName(String cName) {
//		this.cName = cName;
//	}
//	
//	public String getProvince() {
//		return province;
//	}
//
//
//	public void setProvince(String province) {
//		this.province = province;
//	}
//
//
//	public String getCity() {
//		return city;
//	}
//
//
//	public void setCity(String city) {
//		this.city = city;
//	}
//
//
//	public String getRegion() {
//		return region;
//	}
//
//
//	public void setRegion(String region) {
//		this.region = region;
//	}
//
//
//	public String getManageOrgUuid() {
//		return manageOrgUuid;
//	}
//
//
//	public void setManageOrgUuid(String manageOrgUuid) {
//		this.manageOrgUuid = manageOrgUuid;
//	}
//	
//
//
//	public String getLegalName() {
//		return legalName;
//	}
//
//
//
//	public void setLegalName(String legalName) {
//		this.legalName = legalName;
//	}
//
//
//	public String getOrgType() {
//		return orgType;
//	}
//
//
//	public void setOrgType(String orgType) {
//		this.orgType = orgType;
//	}
//
//	
//	public String getLicType() {
//		return licType;
//	}
//
//	public void setLicType(String licType) {
//		this.licType = licType;
//	}
//
//
//	public String getCapital() {
//		return capital;
//	}
//
//	public void setCapital(String capital) {
//		this.capital = capital;
//	}
//
//	public Date geteDate() {
//		return eDate;
//	}
//
//	public void seteDate(Date eDate) {
//		this.eDate = eDate;
//	}
//
//	public Date getStartDate() {
//		return startDate;
//	}
//
//	public void setStartDate(Date startDate) {
//		this.startDate = startDate;
//	}
//
//	public Date getEndDate() {
//		return endDate;
//	}
//
//	public void setEndDate(Date endDate) {
//		this.endDate = endDate;
//	}
//
//	public int getLegalPerson_id() {
//		return legalPerson_id;
//	}
//
//
//
//
//	public void setLegalPerson_id(int legalPerson_id) {
//		this.legalPerson_id = legalPerson_id;
//	}
//
//
//
//
//	public String getRa() {
//		return ra;
//	}
//
//	public void setRa(String ra) {
//		this.ra = ra;
//	}
//
//	public Date getaDate() {
//		return aDate;
//	}
//
//	public void setaDate(Date aDate) {
//		this.aDate = aDate;
//	}
//
//	public String getLicStatus() {
//		return licStatus;
//	}
//
//	public void setLicStatus(String licStatus) {
//		this.licStatus = licStatus;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public String getLicScope() {
//		return licScope;
//	}
//
//	public void setLicScope(String licScope) {
//		this.licScope = licScope;
//	}
//
//	public String getLicPath() {
//		return licPath;
//	}
//
//
//	public void setLicPath(String licPath) {
//		this.licPath = licPath;
//	}
//
//
//	public String getRemark() {
//		return remark;
//	}
//
//	public void setRemark(String remark) {
//		this.remark = remark;
//	}
//
//	public String getOrgCode() {
//		return orgCode;
//	}
//
//	public void setOrgCode(String orgCode) {
//		this.orgCode = orgCode;
//	}
//
//	public String getRegNum() {
//		return regNum;
//	}
//
//	public void setRegNum(String regNum) {
//		this.regNum = regNum;
//	}
//
//	public String getIndustry() {
//		return industry;
//	}
//
//	public void setIndustry(String industry) {
//		this.industry = industry;
//	}
//
//	public static long getSerialversionuid() {
//		return serialVersionUID;
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
//	public String getOrgUuid() {
//		return orgUuid;
//	}
//
//	public void setOrgUuid(String orgUuid) {
//		this.orgUuid = orgUuid;
//	}
//
//
//
//
//
//
//
//
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((aDate == null) ? 0 : aDate.hashCode());
//		result = prime * result + ((addTime == null) ? 0 : addTime.hashCode());
//		result = prime * result + ((address == null) ? 0 : address.hashCode());
//		result = prime * result + ((cName == null) ? 0 : cName.hashCode());
//		result = prime * result + ((capital == null) ? 0 : capital.hashCode());
//		result = prime * result + ((city == null) ? 0 : city.hashCode());
//		result = prime * result + ((eDate == null) ? 0 : eDate.hashCode());
//		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + ((industry == null) ? 0 : industry.hashCode());
//		result = prime * result + legalPerson_id;
//		result = prime * result + ((licPath == null) ? 0 : licPath.hashCode());
//		result = prime * result + ((licScope == null) ? 0 : licScope.hashCode());
//		result = prime * result + ((licStatus == null) ? 0 : licStatus.hashCode());
//		result = prime * result + ((licType == null) ? 0 : licType.hashCode());
//		result = prime * result + ((manageOrgUuid == null) ? 0 : manageOrgUuid.hashCode());
//		result = prime * result + ((legalName == null) ? 0 : legalName.hashCode());
//		result = prime * result + ((orgCode == null) ? 0 : orgCode.hashCode());
//		result = prime * result + ((orgType == null) ? 0 : orgType.hashCode());
//		result = prime * result + ((orgUuid == null) ? 0 : orgUuid.hashCode());
//		result = prime * result + ((province == null) ? 0 : province.hashCode());
//		result = prime * result + ((ra == null) ? 0 : ra.hashCode());
//		result = prime * result + ((regNum == null) ? 0 : regNum.hashCode());
//		result = prime * result + ((region == null) ? 0 : region.hashCode());
//		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
//		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
//		result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
//		result = prime * result + ((uscCode == null) ? 0 : uscCode.hashCode());
//		return result;
//	}
//
//
//
//
//
//
//
//
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		LicenseEntity other = (LicenseEntity) obj;
//		if (aDate == null) {
//			if (other.aDate != null)
//				return false;
//		} else if (!aDate.equals(other.aDate))
//			return false;
//		if (addTime == null) {
//			if (other.addTime != null)
//				return false;
//		} else if (!addTime.equals(other.addTime))
//			return false;
//		if (address == null) {
//			if (other.address != null)
//				return false;
//		} else if (!address.equals(other.address))
//			return false;
//		if (cName == null) {
//			if (other.cName != null)
//				return false;
//		} else if (!cName.equals(other.cName))
//			return false;
//		if (capital == null) {
//			if (other.capital != null)
//				return false;
//		} else if (!capital.equals(other.capital))
//			return false;
//		if (city == null) {
//			if (other.city != null)
//				return false;
//		} else if (!city.equals(other.city))
//			return false;
//		if (eDate == null) {
//			if (other.eDate != null)
//				return false;
//		} else if (!eDate.equals(other.eDate))
//			return false;
//		if (endDate == null) {
//			if (other.endDate != null)
//				return false;
//		} else if (!endDate.equals(other.endDate))
//			return false;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		if (industry == null) {
//			if (other.industry != null)
//				return false;
//		} else if (!industry.equals(other.industry))
//			return false;
//		if (legalPerson_id != other.legalPerson_id)
//			return false;
//		if (licPath == null) {
//			if (other.licPath != null)
//				return false;
//		} else if (!licPath.equals(other.licPath))
//			return false;
//		if (licScope == null) {
//			if (other.licScope != null)
//				return false;
//		} else if (!licScope.equals(other.licScope))
//			return false;
//		if (licStatus == null) {
//			if (other.licStatus != null)
//				return false;
//		} else if (!licStatus.equals(other.licStatus))
//			return false;
//		if (licType == null) {
//			if (other.licType != null)
//				return false;
//		} else if (!licType.equals(other.licType))
//			return false;
//		if (manageOrgUuid == null) {
//			if (other.manageOrgUuid != null)
//				return false;
//		} else if (!manageOrgUuid.equals(other.manageOrgUuid))
//			return false;
//		if (legalName == null) {
//			if (other.legalName != null)
//				return false;
//		} else if (!legalName.equals(other.legalName))
//			return false;
//		if (orgCode == null) {
//			if (other.orgCode != null)
//				return false;
//		} else if (!orgCode.equals(other.orgCode))
//			return false;
//		if (orgType == null) {
//			if (other.orgType != null)
//				return false;
//		} else if (!orgType.equals(other.orgType))
//			return false;
//		if (orgUuid == null) {
//			if (other.orgUuid != null)
//				return false;
//		} else if (!orgUuid.equals(other.orgUuid))
//			return false;
//		if (province == null) {
//			if (other.province != null)
//				return false;
//		} else if (!province.equals(other.province))
//			return false;
//		if (ra == null) {
//			if (other.ra != null)
//				return false;
//		} else if (!ra.equals(other.ra))
//			return false;
//		if (regNum == null) {
//			if (other.regNum != null)
//				return false;
//		} else if (!regNum.equals(other.regNum))
//			return false;
//		if (region == null) {
//			if (other.region != null)
//				return false;
//		} else if (!region.equals(other.region))
//			return false;
//		if (remark == null) {
//			if (other.remark != null)
//				return false;
//		} else if (!remark.equals(other.remark))
//			return false;
//		if (startDate == null) {
//			if (other.startDate != null)
//				return false;
//		} else if (!startDate.equals(other.startDate))
//			return false;
//		if (updateTime == null) {
//			if (other.updateTime != null)
//				return false;
//		} else if (!updateTime.equals(other.updateTime))
//			return false;
//		if (uscCode == null) {
//			if (other.uscCode != null)
//				return false;
//		} else if (!uscCode.equals(other.uscCode))
//			return false;
//		return true;
//	}
//
//
//	@Override
//	public String toString() {
//		return "LicenseEntity [id=" + id + ", uscCode=" + uscCode + ", orgCode=" + orgCode + ", regNum=" + regNum
//				+ ", province=" + province + ", city=" + city + ", region=" + region + ", orgType=" + orgType
//				+ ", manageOrgUuid=" + manageOrgUuid + ", legalName=" + legalName + ", licStatus=" + licStatus + ", industry="
//				+ industry + ", eDate=" + eDate + ", licType=" + licType + ", startDate=" + startDate + ", endDate="
//				+ endDate + ", legalPerson_id=" + legalPerson_id + ", aDate=" + aDate + ", capital=" + capital + ", ra="
//				+ ra + ", address=" + address + ", licScope=" + licScope + ", licPath=" + licPath + ", cName=" + cName
//				+ ", remark=" + remark + ", addTime=" + addTime + ", updateTime=" + updateTime + ", orgUuid=" + orgUuid
//				+ "]";
//	}

}
