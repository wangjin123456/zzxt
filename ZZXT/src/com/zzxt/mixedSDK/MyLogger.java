package com.zzxt.mixedSDK;

import org.apache.log4j.Logger;

/**
 * 上上签SDK自定义日志
 * @author think
 *
 */
public class MyLogger {
	private static Logger logger = Logger.getLogger(MyLogger.class.getName());
	public void info(Object message){
		logger.info(message);
	}

	public void error(Object message){
		logger.error(message);
	}

	public void debug(Object message){
		logger.debug(message);
	}
}
