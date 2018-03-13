package com.zzxt.util;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zzxt.entity.LogEntity;
import com.zzxt.service.LogService;

class LogTask implements Runnable{
	
	private LogEntity logEntity;
	
	private static LogService logService;
	
	private static Logger logger = Logger.getLogger(LogTask.class.getName());

	
	public LogTask() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LogTask(LogEntity logEntity) {
		
		this.logEntity = logEntity;
	}
	
	static public void setLogInfo(LogService logService){
	    	
		LogTask.logService = logService;
	    
	}
	
	
	public LogEntity getLogEntity() {
		
		return logEntity;
	}


	public void setLogEntity(LogEntity logEntity) {
		
		this.logEntity = logEntity;
	}


	public void run() {
		
		synchronized(this.logEntity) {
			
			logService.addLog(this.logEntity);
			 
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>日志数据出队成功并已经保存，等待1秒后继续...");
		}

	}
}

@Component
public class MyLogger extends Thread{
	
	
	private static Logger logger = Logger.getLogger(MyLogger.class.getName());
	
	Queue<LogEntity> logDataQueue = new LinkedList<LogEntity>();
	
	// 私有静态成员变量，存储唯一实例
	private static MyLogger myLogger = new MyLogger();
    
	public static String userName;
	
	private boolean isRun = false;
	
	
	private static boolean logout = true;
	
	 
	private ExecutorService executorService = null;

	public static boolean isLogout() {
		return logout;
	}

	public static void setLogout(boolean logout) {
		MyLogger.logout = logout;
	}
	
	

	public String getUserName() {  
        return userName;  
    }  
  
    public void setUserName(String userName) {   
    		
    		MyLogger.userName = userName;  
    }  
    
    // 私有构造函数
	private MyLogger(){
		
		if(this.executorService == null) {
			
			this.executorService = ThreadPool.getPool();
			
		}
	}
	
    // 公有静态成员方法，返回唯一实例
    public static MyLogger getInstance(){

    		return myLogger;
    }
    
    @Autowired
    public void setLogService(LogService logService){
    	
    		LogTask.setLogInfo(logService);
    
    }
    
    
    
    public void runStart() {
    	
    		this.isRun = true;
    		
    		this.start();
    }
    
    public void runStop() {
    		
    		this.isRun = false;
    }
    
	public void run() {
		
		while(this.isRun) {
			
			// logger.info("---------------------------------保存日志异步线程执行中...");
			
			try {
				
				this.saveLog();
				
				Thread.sleep(1000);
				
			}catch(Exception e) {
				
				e.printStackTrace();
				
				logger.info("异步线程保存日志产生异常:" + e.getLocalizedMessage() );
			}
		}
		
		logger.info("保存日志异步线程成功退出！");
		
		this.executorService.shutdown();
	}
	
	
	
    /**
     * 添加日志
     * @param handle 操作
     * @param state  状态
     */
    public synchronized void add(String handle, String state) {
    		    	 
	    	 LogEntity log = new LogEntity();
	
	    	 log.setUserName(userName);
	    	 log.setHandle(handle);
	    	 log.setDate(new Date());
	    	 log.setState(state);
	    	 log.setComment("");
	
	    	 this.logDataQueue.add(log);
	   }
    
    
   
    /**
     * 添加日志
     * @param handle 操作
     * @param state  状态
     */
    public synchronized void add(String handle, String state, String comment) {
    		    	 
	    	 LogEntity log = new LogEntity();
	
	    	 Date date = new Date();
	    	 
	    	 log.setUserName(userName);
	    	 log.setHandle(handle);
	    	 log.setDate(date);
	    	 log.setState(state);
	    	 log.setComment(comment);
	
	    	 this.logDataQueue.add(log);
	}
    
    
    
    /**
     * 添加日志
     * @param handle 操作
     * @param state  状态
     */
    public synchronized void log(String handle, String state, String comment) {
    		    	 
	    	 LogEntity log = new LogEntity();
	
	    	 Date date = new Date();
	    	 
	    	 log.setUserName(userName);
	    	 log.setHandle(handle);
	    	 log.setDate(date);
	    	 log.setState(state);
	    	 log.setComment(comment);
	
	    	 this.logDataQueue.add(log);
	    	 
	    	 if(MyLogger.isLogout()) {
	    		 
	    		 boolean ok = Boolean.parseBoolean(state); 
	    		 String result = ok ? "成功" : "失败";
	    		 
	    		 String info = "用户:" + userName + " 操作: " + Global.getLogInfo(handle) + " 状态:" + result + "信息:" + comment;
	    		 
	    		 logger.info(info);
	    	 }
	}
    
    
    
    
    
    public synchronized void saveLog() {
    	
    		try {
    			
    			if(this.logDataQueue.isEmpty()) {
    				
    				// logger.info("======================================日志数据队列为空, 等待1秒后继续检测...");
    				
    			} else {
    				
    				LogEntity logEntity = this.logDataQueue.poll();
    				
    				if(logEntity != null) {

    					
    					int active = ((ThreadPoolExecutor)this.executorService).getActiveCount();
    					int pool = ((ThreadPoolExecutor)this.executorService).getPoolSize();
    					long task = ((ThreadPoolExecutor)this.executorService).getTaskCount();
    					long completed = ((ThreadPoolExecutor)this.executorService).getCompletedTaskCount();
     					
    					logger.info("1ActiveCount:" +  active + " Pool:" + pool + " task:" + task + " completed:" + completed);
    					
    					
    					this.executorService.execute(new LogTask(logEntity));
    					
    					 active = ((ThreadPoolExecutor)this.executorService).getActiveCount();
    					 pool = ((ThreadPoolExecutor)this.executorService).getPoolSize();
    					 task = ((ThreadPoolExecutor)this.executorService).getTaskCount();
    					 completed = ((ThreadPoolExecutor)this.executorService).getCompletedTaskCount();
    					 
    					logger.info("2ActiveCount:" +  active + " Pool:" + pool + " task:" + task + " completed:" + completed);
    				}
    			}
    			
    		}catch(Exception e) {
    			
    			e.printStackTrace();
    		}
    }

}



