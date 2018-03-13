package com.zzxt.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.itextpdf.text.pdf.PdfReader;

/**
 * pdf装换成png
 * 
 * @author think
 *
 */

public class PDF2PNGUtil {

	public static void main(String[] args) {
		System.out.println(pdf2Image(new File("D:\\tomcat_Bat\\apache-tomcat-8.5.20-windows-x64\\apache-tomcat-8.5.20\\webapps\\ZZXT\\signature\\test.pdf"), "D:\\tomcat_Bat\\apache-tomcat-8.5.20-windows-x64\\apache-tomcat-8.5.20\\webapps\\ZZXT\\signature\\", 300));
	}

	/***
	 * PDF文件转PNG图片，全部页数
	 * 
	 * @param PdfFilePath
	 *            pdf完整路径
	 * @param imgFilePath
	 *            图片存放的文件夹
	 * @param dpi
	 *            dpi越大转换后越清晰，相对转换速度越慢
	 * @return
	 */
	public static Map<String, Object> pdf2Image(File file, String dstImgFolder, int dpi) {
		
		Map<String, Object> map = new HashMap<>();
		
		PDDocument pdDocument;
		
		PdfReader reader;
		
		try {
		
			String imgPDFPath = file.getParent();
			
			int dot = file.getName().lastIndexOf('.');
			
			String imagePDFName = file.getName().substring(0, dot); // 获取图片文件名
			
			String imgFolderPath = null;
			
			//StringBuffer imgRelativePath = new StringBuffer();//相对路径
			//imgRelativePath.append(imagePDFName);
			
			if (dstImgFolder.equals("")) {
			
				imgFolderPath = imgPDFPath + File.separator + imagePDFName;// 获取图片存放的文件夹路径
			
			} else {
			
				imgFolderPath = dstImgFolder + File.separator + imagePDFName;
			
			}

			if (createDirectory(imgFolderPath)) {

				pdDocument = PDDocument.load(file);
				
				PDFRenderer renderer = new PDFRenderer(pdDocument);
				
				/* dpi越大转换后越清晰，相对转换速度越慢 */
				
				reader = new PdfReader(new FileInputStream(file));  
				
				int pages = reader.getNumberOfPages();
				
				StringBuffer imgFilePath = null;
				
				//存放图片路径
				List<String> imgPath = new ArrayList<>();
				for (int i = 0; i < pages; i++) {
					
					String imgFilePathPrefix = imgFolderPath + File.separator + imagePDFName;
					
					imgFilePath = new StringBuffer();
					
					imgFilePath.append(imgFilePathPrefix);
					
					imgFilePath.append("_");
					
					imgFilePath.append(String.valueOf(i + 1));
					
					imgFilePath.append(".png");
					
					File dstFile = new File(imgFilePath.toString());
					
					BufferedImage image = renderer.renderImageWithDPI(i, dpi);
					
					ImageIO.write(image, "png", dstFile);
					
					//保存图片路径
					imgPath.add(imgFilePath.toString().replace(dstImgFolder, ""));
				}
				
				map.put("message", "PDF文档转PNG图片成功！");
				map.put("imgPath", imgPath);
				map.put("code", Global.ICE_OK);
				
				reader.close();
				
				pdDocument.close();
				
				return map;
			
			} else {
				
				map.put("message", "PDF文档转PNG图片失败：" + "创建" + imgFolderPath + "失败");
				map.put("code", Global.ICE_UNKNOW);
				
				return map;
			}

		} catch (IOException e) {
			
			e.printStackTrace();
			
			map.put("message", "PDF文档转PNG图片失败!");
			map.put("code", Global.ICE_UNKNOW);
			
			return map;
		}
	}
	
	
	

	private static boolean createDirectory(String folder) {
		File dir = new File(folder);
		if (dir.exists()) {
			return true;
		} else {
			return dir.mkdirs();
		}
	}
	
	 /** 
     * 将二进制转换成图片保存 
     * @param imgStr 二进制流转换的字符串 
     * @param imgPath 图片的保存路径 
     * @param imgName 图片的名称 
     * @return  
     *      1：保存正常 
     *      0：保存失败 
     */  
    public static int saveToImgByBytes(byte[] data,String imgPath,String imgName){  
    	
    	
    	
    	
        int stateInt = 1;  
        
        return stateInt;  
    }  

}
