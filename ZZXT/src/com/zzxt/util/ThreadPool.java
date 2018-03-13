package com.zzxt.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

class ThreadPool {
	
	private static Logger logger = Logger.getLogger(ThreadPool.class.getName());

	static private int max = Runtime.getRuntime().availableProcessors();
	
	public ThreadPool() {

	}
	
	
	static public ExecutorService getPool() {
		
		int size = ThreadPool.max;
		
		logger.info("CPU个数:" + size + "预计创建线程池大小为:" + (size / 2 + 1));
		
		return Executors.newFixedThreadPool(size / 2 + 1);
	}
}
