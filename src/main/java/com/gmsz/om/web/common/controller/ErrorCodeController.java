/*
 *******************************************************************************
 * All rights Reserved, Copyright (C) www.gm-sz.com 2013
 * FileName: ErrorCodeController.java
 * Modify record:
 * NO. |     Date       |    Version      |      Name         |      Content
 * 1   | 2013-4-28        |      1.0        |  GMSZ)LuHaosheng  | original version
 *******************************************************************************
 */
package com.gmsz.om.web.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gmsz.om.common.beans.Result;
import com.gmsz.om.common.constant.StateDefine;

/**
 * Class name:ErrorCodeController
 * Description: 错误码控制器
 * @author LuHaosheng
 */
@Controller
@RequestMapping(value = "errorcode")
public class ErrorCodeController {

	
	/**
	 * Description: Error Code 401
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "401")
	public String notAuth(HttpServletRequest request) {
		String page = null;
		page = "login";
		return page;
	}
	
	/**
	 * Description: Error Code 404
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "404")
	public String notFound(HttpServletRequest request) {
		String page = null;
		page = "notfound";
		return page;
	}
	
	/**
	 * Description: Error Code 403
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "403")
	public String forbidden(HttpServletRequest request) {
		String page = null;
		page = "nopermission";
		return page;
	}
	
	/**
	 * Description: Error Code 500
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "500")
	@ResponseBody
	public Result error(HttpServletRequest request) {

		return new Result(StateDefine.FLAG_FAIL_ERROR, false, "系统错误");
	}
}
