package com.zzxt.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * @author wdk
 *
 *         下来选择框 自定义标签
 */
public class SelectElementTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8031622447030088720L;
	private String name;
	private String value;

	/**
	 * 输出信息
	 */
	@Override
	public int doStartTag() throws JspException {

		try {
			pageContext.getResponse().getWriter()
					.write("这是我写的一大段信息：ABCDEFGHIJKLMNOPQRSTUVWXYZ"+name+":"+value);
		
		
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return super.doStartTag();
	}
	

	/**
	 * 重写父类的setPageContext方法，用于得到当前jsp页面的pageContext对象。
	 */
	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


}
