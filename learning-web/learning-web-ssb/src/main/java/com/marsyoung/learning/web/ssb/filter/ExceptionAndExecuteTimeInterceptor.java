package com.marsyoung.learning.web.ssb.filter;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ExceptionAndExecuteTimeInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -6442157043443401725L;

	private static final Log log = LogFactory
			.getLog(ExceptionAndExecuteTimeInterceptor.class);

	private static final String EQUAL_SIGN = "=";
	private static final String PLUS_SIGN = "+";
	private static final String AND = "&";

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		/*
		 * 获取该http请求的一些信息，下面的日志会使用到
		 */
		HttpServletRequest request = ServletActionContext.getRequest(); // 获取客户端发过来的HTTP请求

		String remoteHost = request.getHeader("x-real-ip"); // 获取客户端的主机名
		if (remoteHost == null) {
			remoteHost = "“没有获取到客户端IP”";
		}
		String requestURL = request.getRequestURL().toString(); // 获取客户端请求的URL
		Map<String, String[]> paramsMap = request.getParameterMap(); // 获取所有的请求参数

		/*
		 * 获取所有参数的名值对信息的字符串表示，存储在变量paramsStr中
		 */
		StringBuilder paramsStrSb = new StringBuilder();
		if (paramsMap != null && paramsMap.size() > 0) {
			Set<Entry<String, String[]>> paramsSet = paramsMap.entrySet();
			for (Entry<String, String[]> param : paramsSet) {
				StringBuilder paramStrSb = new StringBuilder();
				String paramName = param.getKey(); // 参数的名字
				String[] paramValues = param.getValue(); // 参数的值
				if (paramValues.length == 1) { // 参数只有一个值，绝大多数情况
					paramStrSb.append(paramName).append(EQUAL_SIGN)
							.append(paramValues[0]);
				} else {
					paramStrSb.append(paramName).append(EQUAL_SIGN);
					for (String paramValue : paramValues) {
						paramStrSb.append(paramValue);
						paramStrSb.append(PLUS_SIGN);
					}
					paramStrSb.deleteCharAt(paramStrSb.length() - 1);
				}
				paramsStrSb.append(paramStrSb).append(AND);
			}
			paramsStrSb.deleteCharAt(paramsStrSb.length() - 1);
		}
		String paramsStr = paramsStrSb.toString();
		log.info("收到来自" + remoteHost + "的请求，URL:" + requestURL + "，参数："
				+ paramsStr);


		/*
		 * 如果Action的执行过程中抛出异常，则记录到日志里； 或者Action执行成功，但执行时间过长，也记录到日志里
		 */
		String result = null;
		long start = System.currentTimeMillis();
		try {
			// 执行该拦截器的下一个拦截器，或者如果没有下一个拦截器，直接执行Action的execute方法
			result = invocation.invoke();
		} catch (Exception e) {
			String msg = "抛出了异常！" + remoteHost + "的请求，URL:" + requestURL
					+ "，参数：" + paramsStr;
			log.error(msg, e);
			return "exception";
		}
		long end = System.currentTimeMillis();
		// 如果该Action的执行时间超过了500毫秒，则日志记录下来
		final int MAX_TIME = 500;
		long executeTimeMillis = end - start;
		if (executeTimeMillis >= MAX_TIME) {
			log.info("Action执行时间过长！执行" + remoteHost + "的请求，URL:" + requestURL
					+ "，参数：" + paramsStr + "，共用时" + executeTimeMillis + "毫秒");
		}
		// 记录返回的JSON字符串
		if (request.getAttribute("resp") != null) {
			String jsonStr = (String) request.getAttribute("resp");
			log.debug("请求的URL为:" + requestURL + "，参数为：" + paramsStr
					+ "，该请求返回的JSON字符串是：" + jsonStr);
		}
		return result;
	}
}
