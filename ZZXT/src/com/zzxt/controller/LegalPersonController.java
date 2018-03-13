package com.zzxt.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzxt.redis.RedisCache;
import com.zzxt.redis.RedisCacheUtil;
import com.zzxt.service.LogService;
import com.zzxt.servicehttp.OrganizationServiceHttp;

/**
 * 
 * @ClassName: LegalPersonController
 * @Description: 法人 处理控制器
 * @author wangdekun
 * @date 2017年8月30日 上午10:18:42
 *
 */
@Controller
public class LegalPersonController extends BaseController {

	private static Logger logger = Logger.getLogger(LegalPersonController.class.getName());

	@Autowired
	private OrganizationServiceHttp organizationServiceHttp;
	
	@Autowired
	private RedisCacheUtil redisCacheUtil;
	
	@RequestMapping("/LegalPerson")
	public String getLegalPerson(HttpServletResponse response) {
		String token = redisCacheUtil.get("token");
		return null;
	}

}
