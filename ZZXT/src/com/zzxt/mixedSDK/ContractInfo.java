package com.zzxt.mixedSDK;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zzxt.InitData.InitData;
import com.zzxt.util.Global;
import com.zzxt.util.JsonCast;
import com.zzxt.util.Tip;

import cn.bestsign.mixed.sdk.integration.exceptions.BizException;

/**
 * 上上签-合同相关功能接口
 * @author think
 *
 */
@Component
public class ContractInfo {
	private static Logger logger = Logger.getLogger(ShangShangSigner.class.getName());
	@Autowired
	InitData initData;
	
	/**
	 * 创建合同文件节点 
	 * @param account 用于查找合同文件的唯一标识
	 * @param pdfData 合同文件的数据流
	 * @return fid    合同文件编号
	 */
	public String contractCreateFileNode(String account, byte[] pdfData){
		try {
			return initData.getMixedSdk().contractCreateFileNode(account, pdfData);
		} catch (IOException | BizException e) {
			logger.info("创建合同文件节点失败！");
			return (String) JsonCast.beanToJson(new Tip("创建合同文件节点 失败！",Global.FAILD));
		} 
	}
}
