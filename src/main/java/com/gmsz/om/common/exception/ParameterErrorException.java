/*
 *******************************************************************************
 * All rights Reserved, Copyright (C) www.gm-sz.com 2014
 * FileName: ParameterErrorException.java
 * Modify record:
 * NO. |     Date       |    Version      |    Name           |      Content
 * 1   | 2014年5月8日        |   $Revision$  |  GMSZ)LuHaosheng  | original version
 *******************************************************************************
 */
package com.gmsz.om.common.exception;

/**
 * Class name:ParameterErrorException
 * Description: please write your description
 * @author LuHaosheng
 */
public class ParameterErrorException extends Exception {

	private static final long serialVersionUID = 6213631788387839209L;
	
	public ParameterErrorException(String msg) {
		super(msg);
	}

}
