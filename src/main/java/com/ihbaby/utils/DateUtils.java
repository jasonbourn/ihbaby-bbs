package com.ihbaby.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * 年月日时分秒(无下划线) yyyyMMddHHmmss
     */
    public static final String dtLong = "yyyyMMddHHmmsssss";
    public static final String simple = "yyyy-MM-dd HH:mm:ss";
    public static final String china = "yyyy年MM月dd日";
    public static final String dtSimple = "yyyyMMddHHmmss";

    public static String getGestationalWeeks(Date deliveryTime) {
        int getGestationalDay = (int) ((new Date().getTime() / 1000
                - deliveryTime.getTime() / 1000 + 280 * 24 * 3600) / 3600 / 24);
        int weeks = getGestationalDay / 7;
        int days = getGestationalDay % 7;
        return weeks + "周+" + days;
    }

    // 怀孕天数
    public static int getGestationalDays(Date deliveryTime) {
        return (int) ((new Date().getTime() / 1000 - deliveryTime.getTime()
                / 1000 + 280 * 24 * 3600) / 3600 / 24);

    }

    // 距离预产期天数
    public static int fromDeliveryTime(Date deliveryTime) {
        return (int) ((deliveryTime.getTime() / 1000 - new Date().getTime() / 1000) / 3600 / 24);

    }

    public static int getAge(String birthday) throws ParseException {
        DateFormat df = new SimpleDateFormat(china);
        Date date = df.parse(birthday);
        return (int) ((new Date().getTime() - date.getTime()) / 1000 / 3600 / 24);
    }

    public static void main(String[] args) {
        String s = getGestationalWeeks(new Date());
        int gestationalDays = getGestationalDays(new Date());
        int fromDeliveryTime = fromDeliveryTime(new Date());
        System.out.println(gestationalDays);
        System.out.println(fromDeliveryTime);
        System.out.println(s);
    }

    public static Date parseDateSimple(String dateString) throws ParseException {
        DateFormat df = new SimpleDateFormat(simple);
        return df.parse(dateString);
    }

    public static Date parseDateLong(String dateString) throws ParseException {
        DateFormat df = new SimpleDateFormat(dtLong);
        return df.parse(dateString);
    }

    public static String formateDateSimple(Date date) throws ParseException {
        DateFormat df = new SimpleDateFormat(simple);
        return df.format(date);
    }

    public static String formateDateSimple2(Date date) throws ParseException {
        DateFormat df = new SimpleDateFormat(dtSimple);
        return df.format(date);
    }

    public static Date parseDateChina(String yuchanqi) throws ParseException {
        DateFormat df = new SimpleDateFormat(china);
        return df.parse(yuchanqi);

    }

    // date类型转换为String类型
    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    // string类型转换为date类型
    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
        return isSameDate;
    }

    public static int getAgeByBirthday(Date birthday) {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthday)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                // monthNow>monthBirth
                age--;
            }
        }
        return age;
    }
    public static Date getDayLater(int delay){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,delay);
        return cal.getTime();
    }

}
