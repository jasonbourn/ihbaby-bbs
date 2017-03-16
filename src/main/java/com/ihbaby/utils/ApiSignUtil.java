package com.ihbaby.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

/**
 * 通用工具类
 * 
 */
public class ApiSignUtil {
	
	
	private static final Logger log = LoggerFactory.getLogger(ApiSignUtil.class);
	
	public static String createSign(SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		Set<Entry<Object, Object>> es = parameters.entrySet();
		Iterator<Entry<Object, Object>> it = es.iterator();
		while (it.hasNext()) {
			Entry<Object, Object> entry = it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("deviceType=" + "weitaixincw12345");
		log.debug(sb.toString());
		String sign = MD5Util.MD5Encode(sb.toString(), "utf-8").toUpperCase();
		return sign;
	}
}
