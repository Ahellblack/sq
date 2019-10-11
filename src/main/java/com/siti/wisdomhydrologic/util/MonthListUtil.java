package com.siti.wisdomhydrologic.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2019/9/26.
 */
public class MonthListUtil {

    //月份list
    public static List<String> monthList(Integer dateType, Integer year, Integer quarter, String month) {

        List<String> list = new ArrayList<>();
        if (dateType == 1) {
            list.add("01");
            list.add("02");
            list.add("03");
            list.add("04");
            list.add("05");
            list.add("06");
            list.add("07");
            list.add("08");
            list.add("09");
            list.add("10");
            list.add("11");
            list.add("12");
        } else if (dateType == 2 && quarter != null) {
            if (quarter == 1) {
                list.add("01");
                list.add("02");
                list.add("03");
            } else if (quarter == 2) {
                list.add("04");
                list.add("05");
                list.add("06");
            } else if (quarter == 3) {
                list.add("07");
                list.add("08");
                list.add("09");
            } else if (quarter == 4) {
                list.add("10");
                list.add("11");
                list.add("12");
            }
        } else if (dateType == 3 && month != null) {
            list.add(month);
        }
        return list;
    }

}
