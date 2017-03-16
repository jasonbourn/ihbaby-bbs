package com.ihbaby.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {
	
	/**
	 * 
	 * isMobileNO: 验证电话 . <br/>
	 *
	 * @param mobiles
	 * @return
	 * @author wangchunxi
	 * @date 2016年4月13日 上午11:26:45
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	/**
	 * 
	 * isNumber: 验证是否是数字 . <br/>
	 *
	 * @param number
	 * @return
	 * @author wangchunxi
	 * @date 2016年4月13日 上午11:27:00
	 */
	public static boolean isNumber(String number) {
		Pattern p = Pattern.compile("\\d*");
		Matcher m = p.matcher(number);
		return m.matches();
	}
	
	public static boolean isPosNO(String posNo) {
		if (StringUtils.isBlank(posNo)) {
			return false;
		}
		Pattern p = Pattern.compile("^\\d{6}$");
		Matcher m = p.matcher(posNo);
		return m.matches();
	}
	/**
	 * 
	 * isAmount: 判断是否是金钱 . <br/>
	 *
	 * @param amount
	 * @return
	 * @author wangchunxi
	 * @date 2016年4月27日 下午4:22:18
	 */
	public static boolean isAmount(String amount) {
		if (StringUtils.isBlank(amount)) {
			return false;
		}
		Pattern p = Pattern.compile("^\\d+(\\.\\d{0,2})?$");
		Matcher m = p.matcher(amount);
		return m.matches();
	}
}
