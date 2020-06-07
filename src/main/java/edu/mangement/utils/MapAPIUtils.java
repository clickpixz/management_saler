package edu.mangement.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 6/7/2020
 * TIME : 4:51 PM
 */
public class MapAPIUtils {
    public static Map<String,Long> getMapDay(){
        Map<String,Long> map = new LinkedHashMap<>();
        map.put(DAY.MonthDay,0L);
        map.put(DAY.Tuesday,0L);
        map.put(DAY.Wednesday,0L);
        map.put(DAY.Thursday,0L);
        map.put(DAY.Friday,0L);
        map.put(DAY.Saturday,0L);
        map.put(DAY.Sunday,0L);
        return map;
    }
}
