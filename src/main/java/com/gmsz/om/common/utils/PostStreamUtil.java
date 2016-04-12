/*
 *******************************************************************************
 * All rights Reserved, Copyright (C) www.gmly.com 2016
 * FileName: RequestUrlUtil.java
 * Modify record:
 * NO. |     Date       |    Version      |    Name         |      Content
 * 1   | 2016年1月21日        |   1.0           |  GMSZ)LiangYan  | original version
 *******************************************************************************
 */
package com.gmsz.om.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.gmsz.om.common.constant.StateDefine;

/**
 * Class name:PostStreamUtil
 * Description:请求url
 * @author LiangYan
 */
public class PostStreamUtil {
	private static final Logger sysLogger = Logger.getLogger(StateDefine.SYS_LOG);
	
	private static  Map<String, String> TOKENMAP=new HashMap<String, String>();
	public static int FLAG_FAIL_TOKEN_EXPIRED = 700;
	public static int FLAG_FAIL_TOKEN_CLEANUP = 701;
	
	@SuppressWarnings("unchecked")
	public static JSONObject postStreamToOmd(String url, final JSONObject obj ) throws IOException, NoSuchAlgorithmException {
		url=Constant.OMD_IP+url;
		String token=TOKENMAP.get("token");
		if(token==null){//获取token
			token=postStreamToOmdGettoken();
		}
		JSONObject resultJson=null;
		if(token!=null){
			resultJson=postStream(url+"?_token="+token, obj);
			Long resultId = (Long)resultJson.get("resultId");//判断是否返回token过期
			//token过期，重新获取token,有一种情况也需要重新获取token，就是omd重启过，以前的token都被清掉了，所有此时需要再获取一次token
			if(resultId==FLAG_FAIL_TOKEN_EXPIRED||resultId==FLAG_FAIL_TOKEN_CLEANUP){
				token=postStreamToOmdGettoken();
				if(token!=null){
					resultJson=postStream(url+"?_token="+token, obj);
				}
			}
		}
		if(resultJson==null){
			resultJson=new JSONObject();
			resultJson.put("resultValue", false);
		}
		
		return resultJson;
	}



	/**
	 * 
	 * Description:调用omd，获取token
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private static String postStreamToOmdGettoken() throws NoSuchAlgorithmException, IOException {
		String token =null;
		JSONObject objToken = new JSONObject();
		Long time=System.currentTimeMillis();
		String key = Digest.getInstance().getMd5(Constant.PROJECTCODE + Constant.PROJECTKEY + time);
		objToken.put("_code", Constant.PROJECTCODE);
		objToken.put("_key", key);
		objToken.put("_time", time);
		
		JSONObject resultToken =postStream(Constant.OMD_IP+"/gettoken", objToken);
		boolean resultValue = (boolean)resultToken.get("resultValue");
		if(resultValue){
			token=(String) resultToken.get("token");
			TOKENMAP.remove("token");
			TOKENMAP.put("token", token);
		}
		return token;
	}
	
	
	
	private static JSONObject postStream(String url, final JSONObject obj ) throws IOException {
		final String stream=obj.toJSONString();
		sysLogger.info("### Post url==>" + url + "  Post stream==>" + stream + "###");

		String responseString = null;

		// HttpClient httpClient = new DefaultHttpClient();
		HttpClient httpClient = HttpClients.createDefault();
		// 封装输入流  
		ContentProducer cp = new ContentProducer() {
			public void writeTo(OutputStream outstream) throws IOException {  
				Writer writer = new OutputStreamWriter(outstream, "UTF-8");  
				writer.write(stream);  
				writer.flush();  
			}  
		};
		HttpEntity requestEntity = new EntityTemplate(cp);
		HttpPost httppost = new HttpPost(url);
		InputStream in = null;
		try {
			httppost.setEntity(requestEntity);
			HttpResponse response = httpClient.execute(httppost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {  
					entity = new BufferedHttpEntity(entity);  
					in = entity.getContent();  
					byte[] read = new byte[1024];  
					byte[] all = new byte[0];  
					int num;
					while ((num = in.read(read)) > 0) {  
						byte[] temp = new byte[all.length + num];  
						System.arraycopy(all, 0, temp, 0, all.length);  
						System.arraycopy(read, 0, temp, all.length, num);  
						all = temp;  
					}  
					responseString = new String(all, "UTF-8");  
					sysLogger.info("### Receive response==>" + responseString + "###");
				}  
			}else if(statusCode==FLAG_FAIL_TOKEN_CLEANUP||statusCode==FLAG_FAIL_TOKEN_EXPIRED){
				Header error=response.getFirstHeader("Authentication-Error");
				String errorMsg=null;
				if(error!=null){
					errorMsg=error.getValue();
				}
				responseString="{\"message\":\""+errorMsg+"\",\"resultId\":"+statusCode+"}";
			}
		} finally {
			if (in != null) in.close();
			httppost.abort();
		}
		Object resobj=JSONValue.parse(responseString);
		JSONObject jsonObj = (JSONObject)resobj;
		return jsonObj;
	}
}
