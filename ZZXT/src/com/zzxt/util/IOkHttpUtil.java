package com.zzxt.util;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 
 * @ClassName: OkHttpUtil
 * @Description: Http请求工具类
 * @author wangdekun
 * @date 2017年8月21日 上午10:54:36
 *
 */
public class IOkHttpUtil {

	private static Logger logger = Logger.getLogger(IOkHttpUtil.class.getName());
	private static OkHttpClient client = new OkHttpClient();

	/**
	 * 
	 * @Description
	 *
	 * @param url
	 *            请求地址 如有参数有 map封装 第二个参数
	 * @param paramMap
	 *            参数可为null
	 * @return
	 */
	public static String sendGet(String url, Map<String, String> paramAddressBar) {

		String result = "";
		// 地址栏参数拼接
		String urlResult = paramJoin(url, paramAddressBar);
		logger.info(urlResult);
		Request request = new Request.Builder().url(urlResult).get().build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				result = response.body().string();
			} else {
				logger.warn("Get 请求没有获取到数据");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("IOkHttpUtil Get 请求出错");
		} finally {
			client.newCall(request).cancel();
		}
		return result;

	}

	/**
	 * 
	 * @Description 发送Post请求
	 *
	 * @param url
	 *            地址
	 * @param paramAddressBar
	 *            待绑定的地址栏参数 如： ..?type=1&id=2
	 * @param paramMap
	 *            未在地址栏显示参数
	 * @return
	 */
	public static String sendPost(String url, Map<String, String> paramAddressBar, Map<String, String> paramMap) {
		
		String result = "";
		
		String urlResult = paramJoin(url, paramAddressBar);
		
		logger.info("请求地址和参数："+urlResult);
		logger.info("参数："+paramMap);
		
		// 用于封装Form参数
		FormBody.Builder params = getFormParam(paramMap);
		Request request = new Request.Builder().url(urlResult).post(params.build()).build();
	
		try {
			Response response = client.newCall(request).execute();
			
			if (response.isSuccessful()) {

				result = response.body().string();
			} else {

				logger.info(response.toString());
				
			}
		} catch (IOException e) {
			e.printStackTrace();
			
			String errorMessage = e.getLocalizedMessage();
			
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, errorMessage);
			
			logger.info(protocol);
			
			return protocol;
		
		} finally {
			
			client.newCall(request).cancel();
		}
		return result;
	}
	
	
	
	/**
	 * 
	 * @Description 发送Post请求
	 *
	 * @param url
	 *            地址
	 * @param paramAddressBar
	 *            待绑定的地址栏参数 如： ..?type=1&id=2
	 * @param paramMap
	 *            未在地址栏显示参数
	 * @return
	 */
	public static String sendPost(String url, Map<String, String> paramMap) {
		String result = "";
		String urlResult = url;
		
		logger.info("请求地址和参数："+urlResult);
		logger.info("参数："+paramMap);
		
		// 用于封装Form参数
		FormBody.Builder params = getFormParam(paramMap);
		Request request = new Request.Builder().url(urlResult).post(params.build()).build();
	
		try {
			
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				result = response.body().string();
			} else {
				logger.warn("POST 请求没有返回数据");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("POST 请求出错");
		} finally {
			client.newCall(request).cancel();
		}
		return result;
	}

	
	
	/**
	 * 
	 * @Description 发送Post请求
	 *
	 * @param url
	 *            地址
	 * @param paramAddressBar
	 *            待绑定的地址栏参数 如： ..?type=1&id=2
	 * @param paramMap
	 *            未在地址栏显示参数
	 * @return
	 */
	public static String fileUpload(String url, String base64Data) {
		 
	 
		return Https.sendPost(url, base64Data);
	}
	
	
	

	/**
	 * 
	 * @Description 发送PUT请求
	 *
	 * @param url
	 *            地址
	 * @param paramAddressBar
	 *            待绑定的地址栏参数 如： ..?type=1&id=2
	 * @param paramMap
	 *            未在地址栏显示参数
	 * @return
	 */

	public static String sendPut(String url, Map<String, String> paramAddressBar, Map<String, String> paramMap) {
		String result = "";
		String urlResult = paramJoin(url, paramAddressBar);
		// 用于封装Form参数
		FormBody.Builder params = getFormParam(paramMap);
		Request request = new Request.Builder().url(urlResult).put(params.build()).build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				result = response.body().string();
			} else {
				logger.info("PUT 请求没有返回数据");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("PUT 请求出错");
		} finally {
			client.newCall(request).cancel();
		}
		return result;
	}

	/**
	 * 
	 * @Description 发送Delete请求
	 *
	 * @param url
	 *            地址
	 * @param paramMap
	 *            参数(可为空)
	 * @return
	 */
	public static String sendDelete(String url, Map<String, String> paramMap) {
		String result = "";
		String urlResult = paramJoin(url, paramMap);
		logger.info("删除请求地址：" + urlResult);
		Request request = new Request.Builder().url(urlResult).delete().build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				result = response.body().string();
			} else {
				logger.info("DELETE 请求没有获取到数据");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(" DELETE 请求出错");
		} finally {
			client.newCall(request).cancel();
		}
		return result;
	}

	/**
	 * 
	 * @Description
	 *
	 * @param url
	 *            请求地址
	 * @param paramMap
	 *            需要拼接在请求地址后面的参数： ..?name=1&password=2
	 * @return
	 */
	private static String paramJoin(String url, Map<String, String> paramMap) {
		StringBuilder urlSB = new StringBuilder();
		// 判断是否 为空和有键值对
		if (paramMap != null && !paramMap.isEmpty()) {
			Set<String> keys = paramMap.keySet();
			urlSB.append(url);
			urlSB.append("?");
			// 判断是否是第一个参数，是否需要&连接参数
			Boolean first = true;
			for (String key : keys) {
				if (first) {
					urlSB.append(key);
					urlSB.append("=");
					urlSB.append(paramMap.get(key));
					first = false;
				} else {
					urlSB.append("&");
					urlSB.append(key);
					urlSB.append("=");
					urlSB.append(paramMap.get(key));
				}
			}
		} else {
			urlSB.append(url);
		}
		return urlSB.toString();

	}

	/**
	 * 
	 * @Description 用于创建 FormBody.Builder
	 *
	 * @param paramMap
	 * @return
	 */
	private static FormBody.Builder getFormParam(Map<String, String> paramMap) {
		FormBody.Builder params = new FormBody.Builder();
		
		if (null != paramMap && !paramMap.isEmpty()) {
			Set<String> keys = paramMap.keySet();
			for (String key : keys) {
				if (!"".equals(paramMap.get(key)) && null != paramMap.get(key)) {
					params.add(key, paramMap.get(key));
				}
			}
		}
		return params;
	}

}