package com.zzxt.mixedSDK;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzxt.InitData.InitData;
import com.zzxt.util.Global;
import com.zzxt.util.IOkHttpUtil;
import com.zzxt.util.Md5Util;
import com.zzxt.util.TimestampUtil;
import com.zzxt.util.Tip;

import cn.bestsign.mixed.sdk.integration.exceptions.BizException;
/**
 * 上上签-签章流程的相关功能接口
 * @author think
 *
 */
@Component
public class ZhongShuiSigner implements OnlineSigner {
	private static Logger logger = Logger.getLogger(ZhongShuiSigner.class.getName());
	
	
	
	
	
	/**
	 * 注册账号
	 * @param account 账号，即用户唯一标识
	 * @param mail 用户邮箱，如果用户仅有手机号，则该参数可以为空
	 * @param mobile 用户手机号，如果用户没有邮箱则必须填手机号
	 * @param name 名称，个人填写姓名，企业填写企业名称
	 * @param userType 签署者用户类型：1表示个人类型 ，2表示企业类型
	 * @return
	 */
	public boolean userReg(String account, String mail, String mobile, String name, int userType){
		JSONObject json=null;
		boolean isSucc=true;

		return isSucc;
	}
	/**
	 * 设置签名/印章图片
	 * @param account 账号，即用户唯一标识
	 * @param userType 签署者用户类型：1表示个人类型 ，2表示企业类型
	 * @param signatureImageData 图片
	 */
	public boolean setUserSignatureImage(String account, int userType, byte[] signatureImageData){
		boolean isSucc=true;


		return isSucc;
	}
	/**
	 * 查询签名/印章图片
	 * @param account 账号，即用户唯一标识
	 * @param userType 签署者用户类型：1表示个人类型 ，2表示企业类型
	 * @return
	 */
	public byte[] getUserSignatureImageData(String account, int userType){
		byte[] imgByte=null;


		return imgByte;
	}
	/**
	 * 设置个人证件信息
	 * @param account 账号，即用户唯一标识
	 * @param identity  证件号码，对应证件类型  注：当前仅针对大陆18位身份证号会进行身份证号格式校验
	 * @param identityType  个人证书支持的证件类型如下
	 *						  证件类型	证件名称
	 *							0	身份证
	 *							1	护照
	 *							B	港澳居民往来内地通行证
	 *							P	外国人永久居留证
	 * @param name
	 * @param contactMail
	 * @param contactMobile
	 * @param province
	 * @param city
	 * @param address
	 */
	public boolean userSetPersonalCredential(String account, String identity, String identityType, String name, String contactMail, String contactMobile, String province, String city, String address){
		boolean isSucc=true;


		return isSucc;
	}
	
	
	/**
	 * 设置企业证件信息
	 * @param account  账号，即用户唯一标识
	 * @param regCode  企业工商注册号，如三证合一后则为统一社会信用代码
	 * @param taxCode  企业税务登记证号，如三证合一后则为统一社会信用代码
	 * @param orgCode  企业组织机构代码，如三证合一后则为统一社会信用代码
	 * @param name     企业名称
	 * @param legalPerson 法人代表姓名
	 * @param legalPersonIdentity  法人代表证件号码
	 * @param legalPersonIdentityType 法人代表证件类型
	 * @param legalPersonMobile  法人代表手机号码，全数字
	 * @param contact  企业联系人姓名
	 * @param contactMail  企业联系人邮箱
	 * @param contactMobile  企业联系人手机号码
	 * @param province   省
	 * @param city       城市
	 * @param address    企业的普通住址信息
	 */
	public boolean userSetEnterpriseCredential(String account, String regCode, String taxCode, String orgCode, String name, String legalPerson, String legalPersonIdentity, String legalPersonIdentityType, String legalPersonMobile, String contact, String contactMail, String contactMobile, String province, String city, String address){
		boolean isSucc=true;


		return isSucc;
	}

	/**
	 * 异步申请数字证书
	 * @param account 账号，即用户唯一标识
	 * @param certType 个人填CFCA，企业填ZJCA
	 * @return taskId 用于查询证书申请情况
	 * 为用户申请数字证书，用于签署合同，证明签名者的身份。申请证书为一次性操作，每个用户只需申请一次即可。
	 * 申请证书为异步操作，申请提交成功则本次申请会进入到证书申请队列，同时接口返回taskId；客户根据taskId查询本次证书申请的情况。
	 * 重复申请会重复进入到证书申请队列，并返回不同的taskId。因此获得taskId后请尽量避免重复申请。
	 */
	public JSONObject userAsyncApplyCertSubmit(String account, String certType){


			return null;
	}
	
	
	/**
	 * 异步申请数字证书
	 * @param account 账号，即用户唯一标识
	 * @param certType 个人填CFCA，企业填ZJCA
	 * @return taskId 用于查询证书申请情况
	 * 为用户申请数字证书，用于签署合同，证明签名者的身份。申请证书为一次性操作，每个用户只需申请一次即可。
	 * 申请证书为异步操作，申请提交成功则本次申请会进入到证书申请队列，同时接口返回taskId；客户根据taskId查询本次证书申请的情况。
	 * 重复申请会重复进入到证书申请队列，并返回不同的taskId。因此获得taskId后请尽量避免重复申请。
	 */
	public String userAsyncApplyCertSubmit(Map<String, String> paramMap){

		return "";
	}
	
	
	
	/**
	 * 查询证书申请情况
	 * @param account
	 * @param taskId
	 * @return status	1：新申请  2：申请中  3：超时 4：申请失败 5：成功-1：无效的申请（数据库无此值）
	 * 申请证书为异步操作，申请提交成功则返回taskId，本接口根据返回的taskId从证书申请队列中查询该次证书申请的情况。
	 *	因证书申请为异步队列处理，不同的taskId查询的状态可能会不一致，因此同一个用户尽量只用一个taskId即可
	 */
	public JSONObject userAsyncApplyCertStatus(String account, String taskId){


			return null;
	}
	
	/**
	 * 获取证书编号
	 * @param account 账号，即用户唯一标识
	 * @param certType 申请证书提交时的certType
	 * @return cert 证书编号
	 */
	public String userGetCert(String account, String certType){


			return null;
	}
	
	
	
	/**
	 * 创建合同文件节点
	 * @param account用于查找合同文件的唯一标识
	 * @param pdfData合同文件的数据流
	 * @return
	 */
	public String contractCreateFileNode(String account,byte[] pdfData){

		return "";
		
	}
	/**
	 * 根据合同文件编号创建合同，获得合同编号。
	 * @param senderAccount合同的发起者，注册用户接口的
	 * @param fid文件编号
	 * @param expireAt过期时间，Unix时间戳秒数，如希望合同有效期到“2017/06/10 12:13:14”截止，则expireAt=1497067994
	 * @param title合同标题
	 * @param description合同描述
	 * @return合同编号
	 */
	
	public JSONObject contractCreate(String senderAccount, String fid, String expireAt, String title, String description){

		return null;
	}
	
	/**
	 * 添加单个签署者
	 * @param contractId创建合同返回的合同编号
	 * @param signer签署者
	 * @throws Exception 
	 */
	public void contractAddSigner(String contractId, String signer) throws Exception{


	}
	/**
	 * 添加批量签署者
	 * @param contractId创建合同返回的合同编号
	 * @param signers签署者列表
	 */
	public void contractAddSigners(String contractId, List<String> signers){

	
	}
	/**
	 * 签署合同
	 * @param contractId创建合同返回的合同编号
	 * @param signerAccount签署者account
	 * @param signatureImageData签名图片文件，必须提供。有2种方式获得图片：①可以从自定义文件存储中取出已设置过的图片在此提交（dhash为文件的唯一标识）；②临时生成图片后在此提交
	 * @param signerCert签署者使用的数字证书编号，通过“获取证书编号”接口获得
	 * @param signPageNum签署在合同的第几页
	 * @param signX X轴坐标，0到1之间的小数
	 * @param signY Y轴坐标，0到1之间的小数
	 * @param signWidth pdf上的签名区域显示宽度，像素值
	 * @param signHeight pdf上的签名区域显示高度，像素值
	 * @throws Exception 
	 */
	public void contractSign(String contractId, String signerAccount, byte[] signatureImageData, String signerCert, int signPageNum, float signX, float signY, float signWidth, float signHeight) throws Exception{


	}
	/**
	 * 多处位置签署合同
	 * @param contractId创建合同返回的合同编号
	 * @param signerAccount签署者account
	 * @param signatureImageData签名图片文件，必须提供。有2种方式获得图片：①可以从自定义文件存储中取出已设置过的图片在此提交（dhash为文件的唯一标识）；②临时生成图片后在此提交
	 * @param signerCert签署者使用的数字证书编号，通过“获取证书编号”接口获得
	 * @param signaturePositions指定的默认签署位置，json array格式，每一项为一个json对象。
				x,y坐标使用百分比，取值0.0 - 1.0。
				pageNum	Int	签署在合同的第几页
				x	Float	X轴坐标，0到1之间的小数
				y	Float	X轴坐标，0到1之间的小数
	 * @param signWidth pdf上的签名区域显示宽度，像素值
	 * @param signHeight pdf上的签名区域显示高度，像素值
	 */
	public void contractSign(String contractId, String signerAccount, byte[] signatureImageData, String signerCert, JSONArray signaturePositions, float signWidth, float signHeight){


	}
	
	/**
	 * 锁定合同
	 * @param contractId 需要锁定的合同编号
	 * @throws Exception 
	 */
	public void contractLock(String contractId) throws Exception{


	}
	/**
	 * 结束合同
	 * @param contractId需要结束的合同编号
	 * @throws Exception 
	 */
	public void contractFinish(String contractId) throws Exception{


	}
	/**
	 * 下载合同
	 * @param contractId需要下载合同pdf文件的合同编号
	 * @return文件流
	 */
	public byte[] contractDownload(String contractId){

		return null;
	}
	/**
	 * 获取合同信息
	 * @param contractId
	 * @return
	 */
	public JSONObject contractGetInfo(String contractId){

		return null;
	}
	
	
	/**
	 * 获取用户注册信息
	 * @param account需要查询的用户帐号
	 * @return
	 */
	public JSONObject userBaseInfo(String account){
		
		return null;

	}
		
}
