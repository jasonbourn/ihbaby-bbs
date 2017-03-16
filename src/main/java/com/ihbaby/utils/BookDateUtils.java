package com.ihbaby.utils;


import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public class BookDateUtils {

    public static String dateToLongString(Date date) {
        if (date == null) {
            return "";
        }
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String dateToShortString(Date date) {
        if (date == null) {
            return "";
        }
        return DateFormatUtils.format(date, "yyyy-MM-dd");
    }
}
