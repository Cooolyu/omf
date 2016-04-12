package com.gmsz.om.common.utils;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

public class ConfigProperties {
	
	@PostConstruct
	public void readProperties () {
	      ResourceBundle bundle = ResourceBundle.getBundle("com.gmsz.om.common.utils.config");
	      Iterator<?> it=bundle.keySet().iterator();
	      while(it.hasNext()){
	    	  String key = it.next().toString();
	    	  setValue(key, bundle.getObject(key));
	      }
	}
	
	public static void setValue(String name,Object value) {
		Constant df = new Constant();
		try {
			Field f = df.getClass().getDeclaredField(name);
			String type = f.getType().toString();
			f.setAccessible(true);
			if(type.equals("class java.lang.String")) {
				f.set(df, value.toString());
			}
			if(type.equals("class java.lang.Integer") || type.equals("int")) {
				f.set(df, Integer.parseInt(value.toString()));
			}
			if(type.equals("class java.lang.Long") || type.equals("long")) {
				f.set(df, Long.parseLong(value.toString()));
			}
		
		} catch (NoSuchFieldException e) {
			return;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
