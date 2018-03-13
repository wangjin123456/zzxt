
package com.zzxt.util;

import java.util.List;

/**
 *
 */
public class Tip {
	
	private String message;
	
	private String safecode;
	
	private int stateCode;
	
	private String nowtime;//时间
	
	private String oldurl;
	
	private String status;
	
	private Object pam;
	
	private List<String> urls;

	public Tip() {
		super();
	}
	
	

	public Tip(String message, int stateCode) {
		super();
		this.message = message;
		this.stateCode = stateCode;
	}
	public Tip(String message,String safecode){
		this.message = message;
		this.safecode = safecode;
	}
	
	public Tip(String nowtime,String message,int stateCode) {
		super();
		this.message = message;
		this.nowtime = nowtime;
		this.stateCode = stateCode;
	}
	
	public Tip(String message,int stateCode,Object pam) {
		super();
		this.message = message;
		this.pam = pam;
		this.stateCode = stateCode;
	}
	
	public Tip(String message, int stateCode, Object pam, List<String> urls) {
		super();
		this.message = message;
		this.stateCode = stateCode;
		this.pam = pam;
		this.urls = urls;
	}



	/**
	 * @param i
	 */
	public Tip(int stateCode) {
		// TODO Auto-generated constructor stub
		this.stateCode = stateCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNowtime() {
		return nowtime;
	}

	public void setNowtime(String nowtime) {
		this.nowtime = nowtime;
	}

	public String getSafecode() {
		return safecode;
	}

	public void setSafecode(String safecode) {
		this.safecode = safecode;
	}

	public String getOldurl() {
		return oldurl;
	}

	public void setOldurl(String oldurl) {
		this.oldurl = oldurl;
	}



	public Object getPam() {
		return pam;
	}



	public void setPam(Object pam) {
		this.pam = pam;
	}



	public List<String> getUrls() {
		return urls;
	}



	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	
	
}
