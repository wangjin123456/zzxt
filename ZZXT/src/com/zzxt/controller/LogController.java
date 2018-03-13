package com.zzxt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.zzxt.util.PageBean;
import com.zzxt.entity.LogEntity;
import com.zzxt.service.LogService;
import com.zzxt.util.Global;
import com.zzxt.util.Tip;



/**
 * 日志
 * @author Administrator
 *
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LogController extends BaseController{
	
	private Logger logger = Logger.getLogger(ContractController.class.getName());
	@Autowired
	private LogService logService;
	
	/**
	 * 根据用户名查询日志
	 * 
	 * @param response
	 * @param userName
	 *            用户名称
	 * @param start
	 *            起始页
	 */
	@RequestMapping("/findLogByName")
	@ResponseBody
	public void findLogByName(HttpServletRequest request, HttpServletResponse response, String userName,  int start) {

		String jsonpCallback = request.getParameter("callback");//客户端请求参数 
		
		if (Global.isNull(userName)||Global.isNull(start)) {
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			
			// jsonToPage(protocol, response);
			
			jsonToCallback(jsonpCallback, protocol, response);
			
			return;
		}
		
		PageBean<LogEntity> page = new PageBean<LogEntity>();
		
		page.setTotalRecord(logService.findNameCount(userName));// 设置总记录数
		page.getTotalPage(); //获取总页数
		page.setCurrentPage(start);// 设置当前页
		List<LogEntity> result = logService.findLogByName(userName,(page.getCurrentPage() - 1) * page.getPageSize(),
				page.getPageSize());
		page.setList(result);
		
		if (page.getList() == null) {
			String message = "没有找到您的项目信息";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			
			// jsonToPage(protocol, response);
			
			jsonToCallback(jsonpCallback, protocol, response);
			
		} else {
			
			String jsonResult = Global.getProtocol(JSON.toJSONString(page));
			
			// jsonToPage(jsonResult, response);
			
			jsonToCallback(jsonpCallback, jsonResult, response);
		}
	}
	
	/**
	 * 删除日志
	 * @param lid
	 * @param response
	 */
	@RequestMapping("/deleteLog")
	public void deleteLog(Integer lid, HttpServletRequest request, HttpServletResponse response) {
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数 
		
		Integer x = logService.deleteLog(lid);
		if (x==1) {
			Global.myLogger.add("deleteLog", "true");
			logger.info("成功删除：" + x + "条数据");
			
			// jsonToPage(JSON.toJSONString(new Tip("成功删除：" + x + "条数据",Global.SUCCESS)), response);
		
			jsonToCallback(jsonpCallback, JSON.toJSONString(new Tip("成功删除：" + x + "条数据",Global.SUCCESS)), response);
			
		} else {
			Global.myLogger.add("deleteLog", "false");
			logger.info("删除数据失败");
			
			jsonToPage(JSON.toJSONString(new Tip("删除数据失败",Global.FAILD)), response);
			
			jsonToCallback(jsonpCallback, JSON.toJSONString(new Tip("删除数据失败",Global.FAILD)), response);
		
		}
		
		 // jsonToPage(JSON.toJSONString(lid), response);
		 
		 jsonToCallback(jsonpCallback, JSON.toJSONString(lid), response);
		 
	}
	
	

	/**
	 * 分页查询所有日志
	 * @param response
	 * @param context
	 * @param start
	 * @param lid
	 */
	@RequestMapping("/findAllByPage")
	public void findAllByPage(HttpServletRequest request, HttpServletResponse response,  int start) {
		
		String jsonpCallback = request.getParameter("callback");//客户端请求参数 
		
		PageBean<LogEntity> page = new PageBean<LogEntity>();
		
		page.setTotalRecord(logService.findAllCount());// 设置总记录数
		page.getTotalPage(); //获取总页数
		page.setCurrentPage(start);// 设置当前页
		
		List<LogEntity> result = logService.findAllByPage((page.getCurrentPage() - 1) * page.getPageSize(),
				page.getPageSize());
		page.setList(result);
		
		if (page.getList() == null) {
			String message = "没有找到您的日志信息";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			
			//jsonToPage(protocol, response);
		
			jsonToCallback(jsonpCallback, protocol, response);
			
		} else {
			String jsonResult = Global.getProtocol(JSON.toJSONString(page));
			
			// jsonToPage(jsonResult, response);
			
			jsonToCallback(jsonpCallback, jsonResult, response);
		}
	}

}
