package com.marsyoung.learning.web.springmvc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.marsyoung.learning.web.springmvc.utils.JsonUtil;

public abstract class BaseAction {
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	public String timeFormat = "yyyy-MM-dd"; //按年：MM/yyyy ,按日：MM-dd ,按小时：hh:mm
    protected static String storeBasePath;
    //是否为开发环境,用于审核，正式环境中需要opus生成才能审核
  	protected String isDevelopment = "false";
	@ModelAttribute
	public void init(ModelMap model,HttpServletRequest request) {
		model.put("ctx", request.getContextPath());
        model.put("sessionId", request.getSession().getId());
	}
	
	/**
	 * renderJson(json直接输出)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param data
	 * @param response 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	protected void renderJson(final Object data,HttpServletResponse response) {
		try {
			response.setHeader("Content-Type", "application/json");
			JsonUtil.getMapper().writeValue(response.getWriter(), data);
			response.getWriter().flush();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	protected void renderJsonp(final Object data, String callback, HttpServletResponse response) {
        try {
            response.setHeader("Content-Type", "application/json");
            PrintWriter pw = response.getWriter();
            pw.write(callback+"(");
            pw.write(JsonUtil.getMapper().writeValueAsString(data));
            pw.write(")");
            response.getWriter().flush();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
	
	/**
	 * 直接输出文本.
	 * @see #render(String, String, String...)
	 */
	protected void renderText(final String text,HttpServletResponse response) {
		try {
			response.getWriter().write(text);
			response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 输出XML
	 * @see #render(String, String, String...)
	 */
	protected void renderXML(final String xml,HttpServletResponse response) {
		try {
			response.setHeader("Content-Type", "text/xml");
			response.getWriter().write(xml);
			response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
    /**
     * 初始化Date类型，进行数据类型转换，把页面提交的String类型转为Date类型
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
