package com.zzxt.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzxt.entity.LogEntity;

public interface LogService {
    
	/**
	 * 添加日志
	 * @param log
	 * @return
	 */
	public Integer addLog(LogEntity log);
	
	/**
	 * 查询所有日志总记录
	 * @return
	 */
	public Integer findAllCount();
	
	/**
	 * 根据用户名查询所有日志总记录数
	 * 
	 * @param userName
	 *            用户名称
	 * @return
	 */
	public Integer findNameCount(@Param("userName") String userName);
    
	/**
	 * 根据用户查找日志
	 * @param userName
	 * @return
	 */
	public List<LogEntity> findLogByName(@Param("userName") String userName, @Param("start") int start,
			@Param("pageSize") int pageSize);
	
	/**
	 * 删除日志
	 * @param lid
	 * @return
	 */
	public Integer deleteLog(Integer lid);
	
    /**
     * 查询分页的日志
     * @param start
     * @param pageSize
     * @return
     */
	public List<LogEntity> findAllByPage( @Param("start") int start, @Param("pageSize") int pageSize);
	
	
}
