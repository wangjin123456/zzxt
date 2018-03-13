package com.zzxt.bean;

/**
 * 签署合同数据实体
 * 
 * @author think
 *
 */
public class SignEntity {

	private Integer signPageNum=4;//签署在合同的第几页,默认第一页
	private Float signX=0.3f;//X轴坐标，0到1之间的小数
	private Float signY=0.3f;//Y轴坐标，0到1之间的小数
	private Float signWidth=100f;//pdf上的签名区域显示宽度，像素值
	private Float signHeight=100f;//pdf上的签名区域显示高度，像素值

	public Integer getSignPageNum() {
		return signPageNum;
	}

	public void setSignPageNum(Integer signPageNum) {
		this.signPageNum = signPageNum;
	}

	public Float getSignX() {
		return signX;
	}

	public void setSignX(Float signX) {
		this.signX = signX;
	}

	public Float getSignY() {
		return signY;
	}

	public void setSignY(Float signY) {
		this.signY = signY;
	}

	public Float getSignWidth() {
		return signWidth;
	}

	public void setSignWidth(Float signWidth) {
		this.signWidth = signWidth;
	}

	public Float getSignHeight() {
		return signHeight;
	}

	public void setSignHeight(Float signHeight) {
		this.signHeight = signHeight;
	}

}
