package com.tsw.repair.utils;


/**
 * Excel版本判断
 *
 * @author Kim Chao
 * 2019年6月9日
 */
public class ExcelUtil {
    public static boolean isExcel2003(String filePath)
    {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath)
    {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

}
