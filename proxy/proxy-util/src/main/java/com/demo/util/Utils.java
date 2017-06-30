package com.demo.util;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author: kevin.
 * @date: 2017/5/23.
 * @description:
 */
public class Utils {

    public static String generateUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-","");
    }


    public static String getCurrentFormatDate(String var0) {
        try {
            SimpleDateFormat var1 = new SimpleDateFormat(var0);
            return var1.format(new Date());
        } catch (Exception var2) {
            return "";
        }
    }

    public static String getFormatDate(Date date){
        try {
            SimpleDateFormat var1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            return var1.format(date);
        } catch (Exception var2) {
            return "";
        }
    }


    public static String getTimeFromNowByHour(int hour) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

    }

    public final static boolean isNullString(String s) {
        if ("null".equalsIgnoreCase(s)) {
            return true;
        }
        if (s != null && s.length() > 0 && s.trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }
}
