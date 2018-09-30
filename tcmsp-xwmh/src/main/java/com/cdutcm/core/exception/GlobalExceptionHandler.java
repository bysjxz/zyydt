package com.cdutcm.core.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdutcm.core.message.Header;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public Header exceptionHandler(RuntimeException re){
		Header header = new Header();
		header.setResultCode("0");
		header.setResultInfo(re.getMessage());
		re.printStackTrace();
		System.out.println("异常-----------------" + re.getMessage());
		return header;
	}
 }
