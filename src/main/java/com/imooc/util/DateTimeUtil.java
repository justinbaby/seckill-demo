package com.imooc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换类
 */
public class DateTimeUtil {
    public static String getSysDatetimeStr() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        return df.format(date);
    }

}
