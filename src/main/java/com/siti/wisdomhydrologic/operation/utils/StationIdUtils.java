package com.siti.wisdomhydrologic.operation.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2019/8/13.
 */
public class StationIdUtils {
    /**
     * 获取写入的基本站StationName
     */
    public static List<Integer> getTable7StationList() {
        /*** idList*/
        Integer[] idArray = {18806,
                18404,
                18402,
                18319,
                18315,
                16853,
                16702,
                16401,
                18323,
                16308,
                16101};
        List<Integer> idList = new ArrayList<>();
        for (Integer stationId : idArray) {
            idList.add(stationId);
        }
        return idList;
    }
}
/*   String[] NidArray = {
                "高桥内测亭",
                "大团闸(闸外)",
                "大治河东闸(闸内)",
                "芦潮港",
                "三甲港闸(闸内)",
                "邬家路桥",
                "五号沟闸(闸内)",
                "杨思闸(闸外)",
                "洋泾闸(闸内)",
                "周浦",
                "祝桥"};
        List<String> NidList = new ArrayList<>();
        for (String stationId : NidArray) {
            NidList.add(stationId);
        }*/