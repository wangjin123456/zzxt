package com.zzxt.servicehttp.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zzxt.servicehttp.FileUploadServiceHttp;
import com.zzxt.util.Global;
import com.zzxt.util.HttpPostUtil;
import com.zzxt.util.IOkHttpUtil;
import com.zzxt.util.Md5Util;
import com.zzxt.util.TimestampUtil;

import cn.bestsign.mixed.sdk.integration.logger.Logger;


@Service("FileUploadServiceHttp")
public class FileUploadServiceHttpImpl implements FileUploadServiceHttp {
		
	public String downloadFile(String token, String fileId) {
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		String url = apiURL + "newfileup/downloadFile";
		 
		String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);
		
		Map<String, String> dataMap = new HashMap<String, String>();
		
		String ts = TimestampUtil.getTs();
		dataMap.put("ts", ts);
		dataMap.put("sign", Md5Util.signMd5(ts));
		dataMap.put("appID", appId);
		
		dataMap.put("access_token", token);
		dataMap.put("fileId", fileId);
		
		String jsonResult = IOkHttpUtil.sendGet(url, dataMap);
		
		return jsonResult;
	}
	
	
	@Override
	public String uploadFile(String token, CommonsMultipartFile cfile, String account, String appName) {
		
		String jsonResult = "";
		
		String apiURL = Global.getConfig(Global.ICE_ZZXT_API_URL_KEY);
		
		String url = apiURL + "newfileup/pcUploadFile";
		
		String fileName = cfile.getOriginalFilename();
		
		Logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ICE->uploadFile->fileName:" + fileName);
		
		DiskFileItem fileItem = (DiskFileItem)cfile.getFileItem();

		File oldFile = fileItem.getStoreLocation();
		Logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ICE->uploadFile->oldFile:" + oldFile.toString());
		
		Logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ICE->uploadFile->oldFile->getPath:" + oldFile.getPath());

		
		Logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ICE->uploadFile->oldFile->getName:" + oldFile.getName());


		String filePath = oldFile.getAbsolutePath();
		
		Logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ICE->uploadFile->oldFile->getAbsolutePath:" + filePath);

		String path = FilenameUtils.getFullPath(filePath);
		
		Logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ICE->uploadFile->oldFile->FilenameUtils.getFullPath:" + path);

		
		String newPath = path + fileName;
		
		Logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ICE->uploadFile->oldFile->newPath:" + newPath);
		
		File newFile = new File(newPath);
	
		boolean ok = oldFile.renameTo(newFile);
		if(ok == true) {
			
			Logger.info("重命名成功");
		} else  {
			
			Logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ICE->uploadFile->oldFile.renameTo(newFile) 失败");

		}
				
		 String newName = newFile.getName();  
		 
			Logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ICE->uploadFile->newName:" + newName);

		 long size = newFile.length();
	 
		try {
			
			String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);
			
			String ts = TimestampUtil.getTs();
			
			String param = "ts="+ts+"&sign=" + Md5Util.signMd5(ts) + "&appID=" + appId + "&access_token=" + token  + "&fileName=" + fileName + "&fileLength=" + String.valueOf(size) + "&fileUploadAccount=" + account + "&fileUploadAppName=" + appName;

			Logger.info("调用文件微服务参数串:" + param);
			
			url = url + "?" + param;
			
			HttpPostUtil postUtil = new HttpPostUtil(url);
			
			Logger.info("调用文件微服务总地址:" + url);
			
			
//			postUtil.addTextParameter("ts", ts);
//			postUtil.addTextParameter("sign", Md5Util.signMd5(ts));
//			postUtil.addTextParameter("appID", appId);
//			postUtil.addTextParameter("access_token", token);
//			postUtil.addTextParameter("fileName", fileName);
//			postUtil.addTextParameter("fileLength", String.valueOf(size));
//			postUtil.addTextParameter("fileUploadAccount", account);
//			postUtil.addTextParameter("fileUploadAppName", appName);
//			
			postUtil.addFileParameter("file", newFile);
			
			byte[] bytes = postUtil.send();
			
			jsonResult = new String(bytes, "UTF-8");
			
			Logger.info("文件微服务对接返回信息:" + jsonResult);

			return jsonResult;
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
			jsonResult = e.getLocalizedMessage();
			
			return jsonResult;
		}
		
	}
	
}
