package com.zzxt.entity;

/**
 * 
 * @ClassName: LegalPerson
 * @Description: 法人架构 实体类
 * @author wangdekun
 * @date 2017年8月28日 下午3:39:11
 *
 */

public class LegalPersonEntity_bak {

	private String parentId;// 父级id 顶级为0 用户能看不能编辑，根节点默认为0 子节点后台获取数据（或者用隐藏表单）
	private String name; // 组织架构名称
	private String province; // 省
	private String city;// 市
	private String region; // 区
	private double longitude; // 经度
	private double latitude;// 纬度
	private String orgType;// 架构类型 下拉框 （未知、集团、大区、事业部、行政区、部门、小区）
	private String orgTypeId;// 类型id 《我也不知道这个填写什么》

	private String token; // 鉴权参数 登陆->鉴权 ->获取
	private String corpId;// 租户id 登陆->鉴权 ->获取
	private String familyType; // 族谱类型 填写 ： 法人架构
	private int familyTypeId; // 族谱类型， 0组织架构、1客户架构、2法人架构、3供方架构、4国家架构
	private String sign;// 签名 合成加密值
	private String ts;// 时间戳
	private String appID;// 调用者app id 固定值
	
	
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
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	public String getFamilyType() {
		return familyType;
	}
	public void setFamilyType(String familyType) {
		this.familyType = familyType;
	}
	public int getFamilyTypeId() {
		return familyTypeId;
	}
	public void setFamilyTypeId(int familyTypeId) {
		this.familyTypeId = familyTypeId;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	
	

}
