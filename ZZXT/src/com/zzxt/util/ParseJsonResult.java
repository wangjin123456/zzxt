package com.zzxt.util;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @ClassName: ParseJsonResult
 * @Description: 解析 Htpp请求的结果是否成功
 * @author wangdekun
 * @date 2017年9月11日 上午10:14:23
 *
 */
public class ParseJsonResult {
	private static Logger logger = Logger.getLogger(ParseJsonResult.class.getName());

	// "code": 0
	// "message": "success"
	/**
	 * 
	 * @Description 根据code判断请求的数据是否合法
	 *
	 * @param json
	 *            （Http请求返回的数据）
	 * @return
	 */
	public static Boolean success(String json) {
		logger.info("传进来的" + json);
		if (json != null && !"".equals(json)) {
			JSONObject jsonObject = JSONObject.parseObject(json);
			logger.info(json);
			Integer code = jsonObject.getInteger("code");
			if (code == 0) {
				logger.info("Http 请求ICE接口 ，返回数据正确");
				return true;
			} else {
				String message = jsonObject.getString("message");
				logger.error("Http请求ICE接口，返回错误的提示信息:" + message);
				return false;
			}
		} else {
			logger.error("返回的结果为 ： \"\" 或 null");
			return false;
		}
	}

}
