package com.ihbaby.dao.baseDao;

import org.apache.commons.lang3.StringUtils;

public class SqlUtils {
	
	
	public static String fieldFilter(String sql) {
		return sql.trim().replace("'", "''");
	}
	
	public static String fieldFilter(Object obj) {
		return (obj == null) ? "" : fieldFilter(obj.toString());
	}
	
	public static String nameFilter(String sql) {
		return sql.trim().replace("`", "``");
	}
	
	public static String getIntoValueSuffix(String sql) {
		String strColumn = StringUtils.substringBetween(sql, "INTO", "VALUES");
		if (StringUtils.isBlank(strColumn)) {
			strColumn = StringUtils.substringBetween(sql, "into", "VALUES");
		}
		if (StringUtils.isBlank(strColumn)) {
			strColumn = StringUtils.substringBetween(sql, "into", "values");
		}
		if (StringUtils.isBlank(strColumn)) {
			strColumn = StringUtils.substringBetween(sql, "INTO", "values");
		}
		strColumn = StringUtils.substringBetween(strColumn, "(", ")");
		String[] columnAry = StringUtils.split(strColumn, ",");
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (int i = 0; i < columnAry.length; i++) {
			sb.append("?,");
		}
		sb.setCharAt(sb.length() - 1, ')');
		return sb.toString();
	}
}
