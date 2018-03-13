package com.zzxt.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;


/**
 * 
 * @ClassName: MD5
 * @Description: MD5加密
 * @author wangdekun
 * @date 2017年8月21日 上午10:14:51
 *
 */
public class Md5Util {
	private static Logger logger = Logger.getLogger(Md5Util.class.getName());

	
	private static String appUuid = Global.getConfig(Global.ICE_ZZXT_APP_UUID_KEY);
	
	private static String appId = Global.getConfig(Global.ICE_ZZXT_APPID_KEY);
	
	private static String token = Global.getConfig(Global.ICE_ZZXT_TOKEN_KEY);
	
	private static String appSecret = Global.getConfig(Global.ICE_ZZXT_APP_SECRET_KEY);
	
	/**
	 *
	 * @param 明文
	 * 
	 * @return 32位MD5小写密文
	 */
	public static String md5(String plainText) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			re_md5 = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.error("md5 加密Exception");
		}
		return re_md5;
	}

	
	
	public static String encode_MD5(String text){  
        
        try {  
            MessageDigest digest = MessageDigest.getInstance("md5");  
            byte[] result = digest.digest(text.getBytes());  
            StringBuilder sb =new StringBuilder();  
            for(byte b:result){  
                int number = b&0xff;  
                String hex = Integer.toHexString(number);  
                if(hex.length() == 1){  
                    sb.append("0"+hex);  
                }else{  
                    sb.append(hex);  
                }  
            }  
            return sb.toString();  
        } catch (NoSuchAlgorithmException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
      
    return "" ;  
}  
	
	
	
	/**
	 * 
	 * @Title: signatureMd5  signature=md5( appUuid+ ts（10位）+ appSecret)
	 * @param timestamp
	 *            时间戳
	 * @return String signature密文
	 */

	public static String signatureMd5(String timestamp) {
		return md5(appUuid + timestamp + appSecret);
	}

	
	public static String signatureMd5(String appKey, String timestamp, String appSecret) {
		return md5(appKey + timestamp + appSecret);
	}
	
	public static String getZhongShuiSign(String appId, String appSecret, String timestamp) {
		
		return md5(appId + appSecret + timestamp);
	}
	
	/**
	 * 
	 * @param appID
	 * @param timestamp 	sign=MD5($appID+$ts+$token+”false”)
	 * @param token
	 * @return 返回密文
	 * 
	 */
	public static String signMd5(String timestamp) {
		
		return md5(appId + timestamp + token + "false");
		
		//测试环境使用
		//return "7116520df651860bff96ccbba3cd5f65 9d3292e4819af8f639e6050e99ef40a0";
	}
	
	
	/**
	 * 
	 * @param appID
	 * @param timestamp 	sign=MD5($appID+$ts+$token+”false”)
	 * @param token
	 * @return 返回密文
	 * 
	 */
	public static String signMd5(String a, String timestamp, String t) {
		
		return md5(a + timestamp + t + "false");
		
		//测试环境使用
		//return "7116520df651860bff96ccbba3cd5f65 9d3292e4819af8f639e6050e99ef40a0";
	}

}
