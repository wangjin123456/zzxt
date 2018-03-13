package com.zzxt.mixedSDK;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zzxt.InitData.InitData;
import com.zzxt.util.FileConvert;
import com.zzxt.util.Global;
import com.zzxt.util.ImageConvert;
import com.zzxt.util.JsonCast;
/**
 *上上签 - 文件存储仓库
 *
 *现在文件全部是存到本地,后续要改成存到ICE中  
 * @author think
 */
@Component
public class MyStorage {
	@Autowired
	InitData initData;
	//set文件
	public String set(byte[] data, String dhash) throws Throwable {
		
		Map<String,Object>returnMap=new HashMap<String,Object>();
		
		int isSucc=1;
		
		String path =null;
		
		if(data != null && data.length > 0 && dhash != null) {
			
			if(dhash.contains("userSignImage")){//印章图片
				path =initData.getProjectPath()+"userSignImage/";
				File f=new File(path);
				if(!f.exists()){
					f.mkdirs();
				}
				path+=dhash+".jpg";
				isSucc=ImageConvert.byte2image(data, path);
			}else if(dhash.contains("contractSignImage")){//合同 
				path =initData.getProjectPath()+"contractSignImage/";
				File f=new File(path);
				if(!f.exists()){
					f.mkdirs();
				}
				path+=dhash+".jpg";
				isSucc=ImageConvert.byte2image(data, path);
			}else{//合同节点 pdf
				path =initData.getProjectPath()+"contractPDF/";
				File f=new File(path);
				if(!f.exists()){
					f.mkdirs();
				}
				path+=dhash+".pdf";
				FileConvert.byte2File(data, path);
			}
			if(isSucc==0){
				returnMap.put("code",Global.SUCCESS);
				returnMap.put("message","success");
				returnMap.put("storagePath",path);//存储路径
			}else{
				returnMap.put("code",Global.FAILD);
				returnMap.put("message","fail");
			}
		}
		return JsonCast.collectToString(returnMap);
	}

	public byte[] get(String dhash) throws Throwable {
		byte[] data=null;
		if(dhash.contains("userSignImage")){//印章图片
			String path =initData.getProjectPath()+"/userSignImage/"+dhash+".jpg";
			data=ImageConvert.image2byte(path);
		}else if(dhash.contains("contractSignImage")){//合同 
			String path =initData.getProjectPath()+"/contractSignImage/"+dhash+".jpg";
			data=ImageConvert.image2byte(path);
		}else{//合同节点 pdf
			String path =initData.getProjectPath()+"/contractPDF/"+dhash+".pdf";
			data=FileConvert.file2byte(path);
		}
		return data;
	}

	public boolean exists(String dhash) throws Throwable {
		String path =null;
		if(dhash.contains("userSignImage")){//印章图片
			 path =initData.getProjectPath()+"/userSignImage/"+dhash+".jpg";
		}else if(dhash.contains("contractSignImage")){//合同 
			 path =initData.getProjectPath()+"/contractSignImage/"+dhash+".jpg";
		}else{//合同节点 pdf
			 path =initData.getProjectPath()+"/contractPDF/"+dhash+".pdf";
		}
		File file=new File(path);
		if(file.exists()){
			return true;
		}
		return false;
	}
	
	public void setDebug(Object debug) {
		initData.getMixedSdk().setDebug(debug);
	}

}
