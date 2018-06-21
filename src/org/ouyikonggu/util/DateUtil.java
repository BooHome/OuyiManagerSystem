package org.ouyikonggu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	  /**
     * 获取日期字符串。
     * 
     * <pre>
     *  日期字符串格式： yyyyMMdd
     *  其中：
     *      yyyy   表示4位年。
     *      MM     表示2位月。
     *      dd     表示2位日。
     * </pre>
     * 
     * @param date
     *                需要转化的日期。
     * @return String "yyyyMMdd"格式的日期字符串。
     */
    public static String getDate(Date date) {
	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	 return formatter.format(date);
    }
}
