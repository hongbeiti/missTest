package com.imooc.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class LoginDateTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public int doStartTag() throws JspException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  	String dateStr = format.format(new Date());
	  	try {
			pageContext.getOut().print(dateStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}

}
