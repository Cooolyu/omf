package com.gmsz.om.common.utils;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	
	public static String getRequestString(HttpServletRequest request){
		String reqString = "";
		try {
			InputStream in = request.getInputStream();
			byte[] read = new byte[1024];  
			byte[] all = new byte[0];
			int num;
			while ((num = in.read(read)) > 0) {  
				byte[] temp = new byte[all.length + num];  
				System.arraycopy(all, 0, temp, 0, all.length);  
				System.arraycopy(read, 0, temp, all.length, num);  
				all = temp;  
			}  
			reqString = new String(all, "UTF-8");  
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reqString;
	}

}
