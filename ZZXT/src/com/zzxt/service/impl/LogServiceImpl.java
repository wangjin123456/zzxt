package com.zzxt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzxt.dao.LogDao;
import com.zzxt.entity.LogEntity;
import com.zzxt.service.LogService;

/**
 * 日志记录
 * @author Administrator
 *
 */
@Service("LogService")
public class LogServiceImpl implements LogService{
	
	@Autowired
	private LogDao logDao;

	@Override
	public Integer addLog(LogEntity log) {
		// TODO Auto-generated method stub
		return logDao.addLog(log);
	}

	@Override
	public List<LogEntity> findLogByName(String userName, int start, int pageSize) {
		// TODO Auto-generated method stub
		return logDao.findLogByName(userName, start, pageSize);
	}

	@Override
	public Integer deleteLog(Integer lid) {
		// TODO Auto-generated method stub
		return logDao.deleteLog(lid);
	}

	@Override
	public List<LogEntity> findAllByPage(int start, int pageSize) {
		// TODO Auto-generated method stub
		return logDao.findAllByPage(start, pageSize);
	}

	@Override
	public Integer findNameCount(String userName) {
		// TODO Auto-generated method stub
		return logDao.findNameCount(userName);
	}

	@Override
	public Integer findAllCount() {
		// TODO Auto-generated method stub
		return logDao.findAllCount();
	}


	
}
