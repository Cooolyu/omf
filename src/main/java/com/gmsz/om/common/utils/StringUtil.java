/*
 *******************************************************************************
 * All rights Reserved, Copyright (C) www.gmly.com 2015
 * FileName: StringUtil.java
 * Modify record:
 * NO. |     Date       |    Version      |    Name         |      Content
 * 1   | 2015年4月24日        |   1.0           |  GMSZ)LiangYan  | original version
 *******************************************************************************
 */
package com.gmsz.om.common.utils;

/**
 * Class name:StringUtil Description: stringUtil
 * 
 * @author LiangYan
 */
public class StringUtil {
	/**
	 * Description: 把一个string转化为long类型的数组
	 * 
	 * @param str
	 * @return
	 */
	public static long[] strToArray(String str) {
		long[] result = null;
		if (str != null) {
			String[] strs = str.split(",");
			result = new long[strs.length];
			for (int i = 0; i < strs.length; i++) {
				result[i] = Long.parseLong(strs[i]);
			}
		}
		return result;
	}

	/**
	 * byte转string
	 * 
	 * @param paramArrayOfByte
	 * @return
	 */
	public static String byte2Hex(byte[] paramArrayOfByte) {
		StringBuffer localStringBuffer = new StringBuffer();
		String str = "";
		for (int i = 0; i < paramArrayOfByte.length; ++i) {
			str = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
			if (str.length() == 1)
				localStringBuffer.append("0");
			localStringBuffer.append(str);
		}
		return localStringBuffer.toString().toUpperCase();
	}
}
