package com.zzxt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.zzxt.redis.RedisCacheUtil;

/**
 * 
 * @ClassName: BaseController
 * @Description: Controller基类，所有后续开发的Controller都必须继承这个基类
 * @author wangdekun
 * @date 2017年8月18日 下午1:42:46
 *
 */
//@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BaseController {

	private static Logger logger = Logger.getLogger(BaseController.class.getName());

	@Autowired
	RedisCacheUtil redisCacheUtil;
	 
	/**
	 * 
	 * @Description 跳转到WEB-INF/下的某个文件夹下的jsp页面 folder ： 文件夹名 jspPage ：
	 *              目标jsp页面名，不带扩展名
	 *
	 * @param folder
	 *            文件夹名
	 * @param jspPage
	 *            目标jsp页面名，不带扩展名
	 * @return
	 */
	protected String toPage(String folder, String jspPage) {

		return (folder + '/' + jspPage);
	}

	/**
	 * 
	 * @Description 重定向到另一个controller
	 *
	 * @param redirect
	 * @return
	 */
	protected String redirectPage(String redirect) {
		return "redirect:/" + redirect;

	}
	
	
    /**
	 * 
	 * @Description 用于返回json数据
	 *
	 * @param jsonResult
	 *            需要返回的json 字符串数据
	 * @param response
	 */
	protected void jsonToCallback(String callback, String jsonResult, HttpServletResponse response) {

		response.setContentType("application/json;charset=UTF-8");
		
		// 获取jsonResult并发送响应
		
		PrintWriter out;
		
		try {
		
			 out = response.getWriter();
			 
			 String message = callback + "(" + jsonResult + ")";
			 
			 logger.info("<<<<<<<<<<<<<<<<<<<<" +message+">>>>>>>>>>>>>>>>>>>>");
			
			 out.println(message);
			 out.flush();
			 out.close();
			 
		} catch (IOException e) {
			logger.error("输出失败", e);
		}
	}
	

    /**
	 * 
	 * @Description 用于返回json数据
	 *
	 * @param jsonResult
	 *            需要返回的json 字符串数据
	 * @param response
	 */
	protected void jsonToPage(String jsonResult, HttpServletResponse response) {

		response.setContentType("application/json;charset=utf-8");
		
		// 获取jsonResult并发送响应
		PrintWriter out;
		try {
			 
			logger.info("<<<<<<<<<<<<<<<<<<<<" +jsonResult+">>>>>>>>>>>>>>>>>>>>");
			out = response.getWriter();

			out.println(jsonResult);
			
			out.flush();
			out.close();
		
		} catch (IOException e) {
		
			logger.error("输出失败", e);
		}
	}
	
	
	
	/**
	 * 返回list json数据
	 * @param <T>
	 * @param list
	 * @param response
	 */
	public <T> void listJsonToPage(List<T> list, HttpServletResponse response) {
		String json = JSON.toJSONString(list);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.println(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			logger.error("输出失败", e);
			e.printStackTrace();
		}
	}
	
	public void beanJsonToPage(Object bean, HttpServletResponse response) {
		String jsonString = JSON.toJSONString(bean);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = null;
		try {            
			writer = response.getWriter();
			writer.println(jsonString);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			logger.error("输出失败", e);
			e.printStackTrace();
		}
		
	}
}
