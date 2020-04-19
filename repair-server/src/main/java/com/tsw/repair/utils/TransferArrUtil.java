package com.tsw.repair.utils;

public class TransferArrUtil {

    public static Long[] stringToLong(String[] stringArr){
        Long[] longArr = new Long[0];
        longArr = new Long[stringArr.length];
        for (int i = 0; i < stringArr.length; i++) {
            longArr[i] = Long.parseLong(stringArr[i]);
        }
        return longArr;
    }
}
