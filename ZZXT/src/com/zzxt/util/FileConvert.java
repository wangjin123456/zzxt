package com.zzxt.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.itextpdf.text.pdf.codec.Base64.OutputStream;

public class FileConvert {
	private static Logger logger = Logger.getLogger(ImageConvert.class.getName());
	 /**
	   * byte数组到本地文件
	   * @param data 文件的Byte字节数组
	   * @param path 保存文件的本地路径
	   * @return 1:失败  0:成功
	   */
	  public static void  byte2File(byte[] data, String path){
	    
	        BufferedOutputStream bos = null;
	        
	        FileOutputStream fos = null;
	        
	        File file = null;
	        
	        try
	        {
	            
	            file = new File(path);
	            
	            fos = new FileOutputStream(file);
	            
	            bos = new BufferedOutputStream(fos);
	            
	            bos.write(data);
	        
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        finally
	        {
	            if (bos != null)
	            {
	                try
	                {
	                    bos.close();
	                }
	                catch (IOException e)
	                {
	                    e.printStackTrace();
	                }
	            }
	            if (fos != null)
	            {
	                try
	                {
	                    fos.close();
	                }
	                catch (IOException e)
	                {
	                    e.printStackTrace();
	                }
	            }
	        }
	  }
	  
	  
	  
	  
	  /**
	   * 本地文件到byte数组
	   * @param path 文件的本地路径
	   * @return 文件的字节数组
	   */
	  public static byte[] file2byte(String path) {
		  
	    byte[] data = null;
	    
	    FileInputStream input = null;
	    
	    ByteArrayOutputStream output = null;
	    
	    try {
	    		
	    		input = new FileInputStream(new File(path));
	    		
	    		output = new ByteArrayOutputStream();
	    		
	    		byte[] buf = new byte[1024];
	    		
	    		int numBytesRead = 0;
	    		
	    		while ((numBytesRead = input.read(buf)) != -1) {
	    			
	    			output.write(buf, 0, numBytesRead);
	    		}
	    		
	    		data = output.toByteArray();
	    		
	    		output.close();
	    		
	    		input.close();
	    		
	    }
	    catch (FileNotFoundException ex1) {
	    	
	      ex1.printStackTrace();
	    }
	    catch (IOException ex1) {
	    	
	      ex1.printStackTrace();
	      
	    }
        finally
        {
            if (input != null)
            {
                try
                {
                		input.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (output != null)
            {
                try
                {
                		output.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
	    
	    return data;
	  }
	  
	  
	  
	  /**
	     * 辅助方法，byte数组保存为本地文件
	     * @param buf
	     * @param filePath
	     * @param fileName
	     */
	    public static void byteToFile(byte[] buf, String filePath, String fileName)
	    {
	        BufferedOutputStream bos = null;
	        
	        FileOutputStream fos = null;
	        
	        File file = null;
	        
	        try
	        {
	            File dir = new File(filePath);
	            if (!dir.exists())
	            {
	                dir.mkdirs();
	            }
	        
	            file = new File(filePath + File.separator + fileName);
	            
	            fos = new FileOutputStream(file);
	            
	            bos = new BufferedOutputStream(fos);
	            
	            bos.write(buf);
	        
	        }
	        
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        finally
	        {
	            if (bos != null)
	            {
	                try
	                {
	                    bos.close();
	                }
	                catch (IOException e)
	                {
	                    e.printStackTrace();
	                }
	            }
	            if (fos != null)
	            {
	                try
	                {
	                    fos.close();
	                }
	                catch (IOException e)
	                {
	                    e.printStackTrace();
	                }
	            }
	        }
	   }
	    
	    
	    
	    
	    /**
	     * 辅助方法，byte数组保存为本地文件
	     * @param buf
	     * @param filePath
	     * @param fileName
	     */
	    public static void delFile(String filePath, String fileName)
	    {
	       
	        File file = null;
	        
	        try
	        {
	            File dir = new File(filePath);
	            if (!dir.exists())
	            {
	             	return;
	            }
	        
	            file = new File(filePath + File.separator + fileName);
	            
	           file.deleteOnExit();
	           
	           logger.info("签署合同操作 删除了 合同文件 保存路径（" + filePath + ") 下的 " + fileName);
	           
	        }
	        
	        catch (Exception e)
	        {
	            e.printStackTrace();
	            
	            logger.info("删除旧有合同文件失败:" + e.getLocalizedMessage());
	        }
	      
	   }
	    
	    
	    
	    /**
	     * 辅助方法，下载文件
	     * @param response
	     * @param path
	     * @param fileName
	     */
	    
	    public static void downloadFile(HttpServletResponse response, String path, String myfile, String fileName)  
	    {  
	      try  
	      {  
		    		String filePath = Global.getBasePath() + Global.CONTRACT_FILE_PATH + File.separator + myfile;
		    		
		    		String message = "downloadContract->downloadFile->path:" + path;
		    		
		    		logger.info(message);
		    		
		    		File file = new File(filePath);
		    		if(file == null || !file.exists()) {
		    			
		    			message = "downloadContract->downloadFile->要下载的文件:" + filePath + "创建文件文件对象失败，或文件已经不存在，不能继续下载!";
		    			
		    			logger.info(message);
		    			
		    			return;
		    		}
	
		    		fileName = URLEncoder.encode(fileName, "UTF-8");
		    		
			   response.reset();
			   response.setHeader("Access-Control-Allow-Origin", "*");
			   response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			   response.addHeader("Content-Length", "" + file.length());
			   response.setContentType("application/octet-stream;charset=UTF-8");
					   
					   
		    		FileInputStream inputStream = new FileInputStream(file);
		 
		    		ServletOutputStream outputStream = response.getOutputStream();
		    		
		    		int size = 0;
		    		
		    		byte[] buffer = new byte[2048];
		    		
		    		while(size != -1) {
		    			
		    			size = inputStream.read(buffer);
		    			outputStream.write(buffer, 0, size);
		    		}
		    		
		    		outputStream.flush();

		    		outputStream.close();
		    		inputStream.close();
		      
		    		 message = "downloadContract->downloadFile->path:" + path + "下载成功!";
		    		 
		    		 logger.info(message);
	      }  
	      catch (IOException ex)  
	      {  
	        ex.printStackTrace(); 
	        
	        String  message = "downloadContract->downloadFile->path:" + path + "下载产生异常,详细信息:" + ex.getLocalizedMessage();
		 
	        logger.info(message);
	      }  
	      
	    }
}
