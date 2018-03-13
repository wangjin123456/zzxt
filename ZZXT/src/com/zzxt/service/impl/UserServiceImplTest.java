package com.zzxt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzxt.dao.UserDaoTest;
import com.zzxt.entity.UserEntityTest;
import com.zzxt.service.UserServiceTest;

@Service("UserServiceTest")
public class UserServiceImplTest implements UserServiceTest {

	@Autowired
	private UserDaoTest userDao;

	@Override
	public boolean testLogin(UserEntityTest user) {
		// TODO Auto-generated method stub
		System.out.println(user.toString());
		String passWrodStr = userDao.getPassword(user.getUsername());
		System.out.println(passWrodStr);
		if (!"".equals(passWrodStr) && null != passWrodStr && user.getPassword().equals(passWrodStr)) {
			return true;
		}
		return false;

	}

}
