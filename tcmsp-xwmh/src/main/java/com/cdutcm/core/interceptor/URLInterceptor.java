package com.cdutcm.core.interceptor;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cdutcm.core.util.StringUtil;

public class URLInterceptor implements HandlerInterceptor{
	
	private final static Logger logger = LoggerFactory
			.getLogger(URLInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		String url = request.getRequestURL().toString();
		String param = null;
		param = request.getQueryString();
		if(StringUtil.notEmpty(param)){
			param = URLDecoder.decode(param,"UTF-8");
		}else{
			param = "";
		}
		logger.info("IP：【" + request.getRemoteAddr() + "】" + ",URL:【" + url + "/" + param + "】");
		return true;
	}

}
