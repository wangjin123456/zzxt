package com.zzxt.servicehttp;


import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface FileUploadServiceHttp {
	
	/**
	 * 保存上传文件文本信息
	 */
	
	public String downloadFile(String token, String fileId);

	public String uploadFile(String token, CommonsMultipartFile file, String account, String appName);
	
}
