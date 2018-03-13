package com.zzxt.entity;
/**
 * 电子签章分类实体
 * @author think
 *
 */

public class SignatureCategoryEntity {
	private int cid;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	private String cname;

}
