/*
 *******************************************************************************
 * All rights Reserved, Copyright (C) www.gm-sz.com 2012
 * FileName: Digest.java
 * Modify record:
 * NO. |     Date       |    Version      |    Name         |      Content
 * 1   | 2012-5-2       |      1.0        | GMSZ)LuHaosheng | original version
 *******************************************************************************
 */
package com.gmsz.om.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class name:Digest
 * Description: Get MD5 or SHA from a string.
 * @author LuHaosheng
 */
public class Digest {
	
	private static Digest digestInstance = null;
	
	private Digest() {
		
	}
	
	public synchronized static Digest getInstance() {
		if (digestInstance == null) digestInstance = new Digest();
		return digestInstance;
	}
	
	/**
	 * Description: Get MD5
	 * @param msg
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public String getMd5(String msg) throws NoSuchAlgorithmException {
		return this.digest(msg, "MD5");
	}
	
	/**
	 * Description: Get SHA-1
	 * @param msg
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public String getSha(String msg) throws NoSuchAlgorithmException {
		return this.digest(msg, "SHA-1");
	}

	private String digest(String msg, String type) throws NoSuchAlgorithmException {
		String result = null;
		MessageDigest alg = MessageDigest.getInstance(type);
		alg.update(msg.getBytes());
		byte[] resultBytes = alg.digest();
		result  = StringUtil.byte2Hex(resultBytes);
		return result;
	}

}
