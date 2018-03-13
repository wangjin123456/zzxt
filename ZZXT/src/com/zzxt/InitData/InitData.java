package com.zzxt.InitData;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.zzxt.mixedSDK.MyStorage;
import com.zzxt.util.Global;
import com.zzxt.util.MyLogger;

import cn.bestsign.mixed.sdk.MixedSdk;
/**
 * ServletContextListener是对ServeltContext的一个监听.
 * servelt容器启动,serveltContextListener就会调用contextInitialized方法.
 * 在方法里面调用event.getServletContext()可以获取ServletContext,ServeltContext是一个上下文对象,
 * 他的数据供所有的应用程序共享,进行一些业务的初始化.servelt容器关闭,serveltContextListener就会调用contextDestroyed.
 * @author think
 *
 */
@Component
public  class InitData implements ServletContextListener{
	private static Logger logger = Logger.getLogger(InitData.class.getName());
	
	
	private static MyLogger myLogger = MyLogger.getInstance();
	
	
	protected static MixedSdk mixedSdk;
	protected static String projectPath;
	public MixedSdk getMixedSdk() {
		return mixedSdk;
	}

	public void setMixedSdk(MixedSdk mixedSdk) {
		this.mixedSdk = mixedSdk;
	}
	
	public static String getProjectPath() {
		return projectPath;
	}

	public static void setProjectPath(String projectPath) {
		InitData.projectPath = projectPath;
	}
/**
 * sdk
 * public MixedSdk(String developerId  , String pem, String host, Object storage, Boolean isFinger);
	developerId    ：开发者编号
	pem  ：私钥
	host ：云端地址
	isFinger: true公证，false不公证；
	Storage ：文件存储对象，
 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		projectPath = sce.getServletContext().getRealPath("") + "/";
		
		logger.info("=======》Init_ProjectPath_Parameter:"+projectPath);
		logger.info("=======》Init_MixedSdk");
		
		boolean isOk = Global.initConfig(projectPath);
		if(isOk == false) {
			
			return;
		}
		
		// 初始化日志翻译字典
		Global.initLogInfo();
		
		String devId = Global.getConfig(Global.SIGNATURE_DEVID_KEY);
		String pem = Global.getConfig(Global.SIGNATURE_PEM_KEY);
		String host = Global.getConfig(Global.SIGNATURE_SDK_URL_KEY);
		boolean isFinger = Boolean.valueOf(Global.SIGNATURE_IS_FINGERKEY);
		
		mixedSdk=new MixedSdk(devId, pem, host,new MyStorage(), isFinger);
		mixedSdk.setDebug(false);//是否开启debug模式
		mixedSdk.setConnectionRequestTimeout(2000);//默认800
		
		logger.info("=======》MixedSdk_Host:"+mixedSdk.host);//https://mixed-dev.bestsign.info/openapi/v2
		
		Global.setBasePath(projectPath);
		
		myLogger.runStart();
		
		Global.setMyLogger(myLogger);
	}
	
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		myLogger.runStop();
	}

}
