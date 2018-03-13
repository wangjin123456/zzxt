package com.zzxt.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zzxt.util.Global;

/**
 * 日志记录
 * @author Administrator
 *
 */
public class LogEntity implements  Serializable{
    private static final long serialVersionUID = 1L;
    
	private Integer lid;        //日志主键
    private String userName;    //用户名称
    
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;          //记录时间
    private String handle;      //进行操作
    private String state;       //执行状态
    private String comment;     //备注
    
	public Integer getLid() {
		return lid;
	}
	public void setLid(Integer lid) {
		this.lid = lid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getHandle() {
		
		String op =  Global.getLogInfo(handle);
		if(op == null || op.trim().equals("")) {
			
			op = handle;
		}
		
		return op;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public LogEntity() {
		super();
	}
	
	public LogEntity(Integer lid, String userName, Date date, String handle, String state, String comment) {
		super();
		this.lid = lid;
		this.userName = userName;
		this.date = date;
		this.handle = handle;
		this.state = state;
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "Log [lid=" + lid + ", userName=" + userName + ", date=" + date + ", handle=" + handle + ", state="
				+ state + ", comment=" + comment + "]";
	}
	
	
	
	
    
}
