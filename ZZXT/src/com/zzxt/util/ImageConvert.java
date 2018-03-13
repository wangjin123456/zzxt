package com.zzxt.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.log4j.Logger;
/**
 * 图片处理
 * @author think
 *
 */
public class ImageConvert {
	private static Logger logger = Logger.getLogger(ImageConvert.class.getName());
	 /**
	   * 本地图片到byte数组
	   * @param path 图片的本地路径
	   * @return 图片的字节数组
	   */
	  public static byte[] image2byte(String path){
	    byte[] data = null;
	    FileImageInputStream input = null;
	    try {
	      input = new FileImageInputStream(new File(path));
	      ByteArrayOutputStream output = new ByteArrayOutputStream();
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
	    return data;
	  }
	  /**
	   * byte数组到本地图片
	   * @param data 图片的Byte字节数组
	   * @param path 保存图片的本地路径
	   * @return 1:失败  0:成功
	   */
	  public static int  byte2image(byte[] data,String path){
	    if(data.length<3||path.equals("")) return 1;
	    try{
	    FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
	    imageOutput.write(data, 0, data.length);
	    imageOutput.close();
	    logger.info("Make Picture success,Please find image in " + path);
	    return 0;
	    } catch(Exception ex) {
	    	logger.info("Exception: " + ex);
	      ex.printStackTrace();
	      return 1;
	    }
	  }
	  
	  /**
	   * byte数组到本地图片
	   * @param data 图片的Byte字节数组
	   * @param path 保存PDF的本地路径
	   * @return 1:失败  0:成功
	   */
	  public static int  byte2Pdf(byte[] data,String path){
		  
	    if(data.length<3||path.equals("")) return 1;
	    
	    try{
		    FileOutputStream imageOutput = new FileOutputStream(new File(path));
		    imageOutput.write(data, 0, data.length);
		    
		    imageOutput.close();
		    
		    logger.info("Make Picture success,Please find image in " + path);
		    
		    return 0;
		    
	    } catch(Exception ex) {
	    		
	    		logger.info("Exception: " + ex);
	    		ex.printStackTrace();
	      
	    		return 1;
	    }
	  }
	  
	  
	  
	  /**
	   * http地址图片到byte数组
	   * @param url 图片地址
	   * @return 图片的字节数组
	   */
	  public static byte[] httpImage2byte(String url){
	    byte[] data = null;
		try {
			//new一个URL对象  
			 URL u = new URL(url);
		      //打开链接  
	        HttpURLConnection conn = (HttpURLConnection)u.openConnection();  
	        //设置请求方式为"GET"  
	        conn.setRequestMethod("GET");  
	        //超时响应时间为5秒  
	        conn.setConnectTimeout(5 * 1000);  
	        //通过输入流获取图片数据  
	        InputStream inStream = conn.getInputStream();  
	        //得到图片的二进制数据，以二进制封装得到数据，具有通用性  
	        data = readInputStream(inStream);  
		} catch (Exception e) {
			 logger.info("转换http地址图片失败！");
			e.printStackTrace();
		}  
	    return data;
	  }
	  
	  /**
	   * byte数组到16进制字符串
	   * @param data 图片的Byte字节数组
	   */
	  
	  public String byte2string(byte[] data){
	    if(data==null||data.length<=1) return "0x";
	    if(data.length>200000) return "0x";
	    StringBuffer sb = new StringBuffer();
	    int buf[] = new int[data.length];
	    //byte数组转化成十进制
	    for(int k=0;k<data.length;k++){
	      buf[k] = data[k]<0?(data[k]+256):(data[k]);
	    }
	    //十进制转化成十六进制
	    for(int k=0;k<buf.length;k++){
	      if(buf[k]<16) sb.append("0"+Integer.toHexString(buf[k]));
	      else sb.append(Integer.toHexString(buf[k]));
	    }
	    return "0x"+sb.toString().toUpperCase();

	  } 
	  /**
	   * 从输入流读取数据到byte数组
	   * @param inStream
	   * @return
	   * @throws Exception
	   */
	  public static byte[] readInputStream(InputStream inStream) throws Exception{  
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
	        //创建一个Buffer字符串  
	        byte[] buffer = new byte[1024];  
	        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
	        int len = 0;  
	        //使用一个输入流从buffer里把数据读取出来  
	        while( (len=inStream.read(buffer)) != -1 ){  
	            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
	            outStream.write(buffer, 0, len);  
	        }  
	        //关闭输入流  
	        inStream.close();  
	        //把outStream里的数据写入内存  
	        return outStream.toByteArray();  
	    }
	  
	  
	 
	  /**
	   * 从输入流读取数据到byte数组
	   * @param inStream
	   * @return
	   * @throws Exception
	   */
	  public static byte[] readFile(String fileName) {  
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
	        //创建一个Buffer字符串  
	        byte[] buffer = new byte[1024];  
	        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
	        int len = 0;
	        
	        try {
	        
		        File file = new File(fileName);
		        InputStream  inStream = new FileInputStream(file);
		                
		         //使用一个输入流从buffer里把数据读取出来  
		        while( (len=inStream.read(buffer)) != -1 ){  
		            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
		            outStream.write(buffer, 0, len);  
		        }  
		        //关闭输入流  
		        inStream.close();  
	        
	        }catch (Exception e) {
	        	
				 logger.info("从输入流读取数据到byte数组失败！");
					
				 e.printStackTrace();
			}  
	        
	        //把outStream里的数据写入内存  
	        return outStream.toByteArray();  
	    }
	  
	  /**
	    * 
	    * @Title: 构造图片
	    * @Description: 生成水印并返回java.awt.image.BufferedImage
	    * @param file
	    *            源文件(图片)
	    * @param waterFile
	    *            水印文件(图片)
	    * @param x
	    *            距离右下角的X偏移量
	    * @param y
	    *            距离右下角的Y偏移量
	    * @param alpha
	    *            透明度, 选择值从0.0~1.0: 完全透明~完全不透明
	    * @return BufferedImage
	    * @throws IOException
	    */
      	static private BufferedImage watermark(File file, File waterFile, int x, int y, float alpha) throws IOException {
           
    	   	   // 获取底图
           BufferedImage buffImg = ImageIO.read(file);
           
           // 获取层图
           BufferedImage waterImg = ImageIO.read(waterFile);
           
           // 创建Graphics2D对象，用在底图对象上绘图
           Graphics2D g2d = buffImg.createGraphics();
           
           int waterImgWidth = waterImg.getWidth();// 获取层图的宽度
           int waterImgHeight = waterImg.getHeight();// 获取层图的高度
           
           // 在图形和图像中实现混合和透明效果
           g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
           
           // 绘制
           g2d.drawImage(waterImg, x, y, waterImgWidth, waterImgHeight, null);
           
           g2d.dispose();// 释放图形上下文使用的系统资源
           
           return buffImg;
       }
	  
       
       static public void waterImage(String srcFilePath, String waterFilePath, int x, int y) {
    	   	
    	   		File srcFile = new File(srcFilePath);
    	   		File waterFile = new File(waterFilePath);
    	   		
    	   		int temp = srcFilePath.lastIndexOf(".") + 1;
    	   
    	   		String savePath = srcFilePath.substring(temp);
    	   		
    	   		try {
    	   			BufferedImage bufferedImage = watermark(srcFile, waterFile, x, y, 1.0f);
    	   			
    	   			ImageIO.write(bufferedImage, savePath, new File(srcFilePath));
    	   			
    	   		}catch (Exception e) {
    	   			
    	   		}
    	   	}
       
       /**
        * 输出水印图片
        * 
        * @param buffImg
        *            图像加水印之后的BufferedImage对象
        * @param savePath
        *            图像加水印之后的保存路径
        */
     
       static public void generateWaterFile(BufferedImage buffImg, String savePath) {
             
	    	   int temp = savePath.lastIndexOf(".") + 1;
	       
	    	   try {
	       
	    		   ImageIO.write(buffImg, savePath.substring(temp), new File(savePath));
	           
	    	   } catch (IOException e1) {
	       
	    		   e1.printStackTrace();
	           
	    	   }
       }
       
     
	  public static void main(String[] args) {
		byte[] b= httpImage2byte("http://192.168.3.134:8080/signature/test.pdf");
		byte2image(b, "E:\\test.pdf");
//		
//		 String sourceFilePath = "D://img//di.png";
//	
//		 String waterFilePath = "D://img//ceng.png";
//
//		 String saveFilePath = "D://img//new.png";

//		 ImageConvert newImageUtils = new ImageConvert();
//
//        // 构建叠加层
//		BufferedImage buffImg = newImageUtils.watermark(new File(sourceFilePath), new File(waterFilePath), 0, 0, 1.0f);
//		         // 输出水印图片
//		newImageUtils.generateWaterFile(buffImg, saveFilePath);
		
	}
}
