package com.coffee.mvc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 常用类型转换工具类
 * @author coffee
 *
 */
public class ConversionUtil {
	private static final String dateFormat = "yyyy-MM-dd";
	
	public static Date string2Date(String dateStr) {
		Date date = null;
		try{
			date = new SimpleDateFormat(dateFormat).parse(dateStr);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
}
