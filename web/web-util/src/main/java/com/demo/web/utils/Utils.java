package com.demo.web.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author: kevin.
 * @date: 2017/5/24.
 * @description:
 */
public class Utils {

    public static String getCurrentFormatDate(String var0) {
        try {
            SimpleDateFormat var1 = new SimpleDateFormat(var0);
            return var1.format(new Date());
        } catch (Exception var2) {
            return "";
        }
    }


    public static String generateUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-","");
    }

}
