/*
 *******************************************************************************
 * All rights Reserved, Copyright (C) www.gm-sz.com 2012
 * FileName: CommonController.java
 * Modify record:
 * NO. |     Date       |    Version      |    Name         |      Content
 * 1   | 2012-10-22        |      1.0        | GMSZ)LuHaosheng | original version
 *******************************************************************************
 */
package com.gmsz.om.web.common.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javassist.NotFoundException;

import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONValue;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gmsz.om.common.beans.Result;
import com.gmsz.om.common.constant.StateDefine;

/**
 * Class name:CommonController
 * Description: Common Controller
 * @author LuHaosheng
 */

public class CommonController {

	/**
	 * Description: 用户未登录
	 * @param request
	 * @return
	 * @throws IOException 
	 */
//	@ExceptionHandler(NotLoginException.class)
//	public void noLogin(HttpServletResponse response) throws IOException {
//		response.sendError(401);
//	}
	
	/**
	 * Description: 发生异常
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@ExceptionHandler(Exception.class)
	public void serverError(HttpServletResponse response, Exception e) throws IOException {
//		response.sendError(500);

		Result result = new Result(StateDefine.FLAG_FAIL_ERROR, false, e.getMessage());
		
		Map<String, Object> obj=new LinkedHashMap<>();
		obj.put("resultId", result.getResultId());
		obj.put("resultValue", result.isResultValue());
		obj.put("message", result.getMessage());
		String jsonText = JSONValue.toJSONString(obj);
		
		response.getWriter().println(jsonText);
	}
	
	/**
	 * Description: 没有权限操作
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@ExceptionHandler(NoPermissionException.class)
	public void handlerNoPermissionException(HttpServletResponse response) throws IOException {
//		response.sendError(403);
		Result result = new Result(StateDefine.FLAG_FAIL_ERROR, false, "没有权限");
		
		Map<String, Object> obj=new LinkedHashMap<>();
		obj.put("resultId", result.getResultId());
		obj.put("resultValue", result.isResultValue());
		obj.put("message", result.getMessage());
		String jsonText = JSONValue.toJSONString(obj);
		
		response.getWriter().println(jsonText);
	}
	
	/**
	 * Description: 资源未找到
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@ExceptionHandler(NotFoundException.class)
	public void handlerNotFoundException(HttpServletResponse response) throws IOException {
//		response.sendError(404);
		Result result = new Result(StateDefine.FLAG_FAIL_ERROR, false, "资源未找到");
		
		Map<String, Object> obj=new LinkedHashMap<>();
		obj.put("resultId", result.getResultId());
		obj.put("resultValue", result.isResultValue());
		obj.put("message", result.getMessage());
		String jsonText = JSONValue.toJSONString(obj);
		
		response.getWriter().println(jsonText);
	}
}
