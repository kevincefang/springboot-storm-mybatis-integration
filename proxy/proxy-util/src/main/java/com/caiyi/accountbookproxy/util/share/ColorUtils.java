package com.caiyi.accountbookproxy.util.share;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lsh on 2017/5/26.
 */
public class ColorUtils {
    private static String[] memberColors = new String[]{"#fc7a60","#faa94a","#ef6161","#b1c23e","#8bb84a","#5a98de"};

    /**
     *  获取账本不存在的成员颜色
     * @param colors 已存在的成员颜色
     * @return 返回 成员颜色
     */
    public static String generateMemberColor(List<String> colors){
        List<String> la = new ArrayList<>(Arrays.asList(memberColors));
        la.removeAll(colors);
        return la.get(0);
    }
}
