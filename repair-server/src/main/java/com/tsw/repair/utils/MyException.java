package com.tsw.repair.utils;


/**
 * 自定义Exception
 *
 * @author Kim Chao
 * 2019年6月9日
 */
public class MyException extends Exception {
    private static final long serialVersionUID = -5519743897907627214L;

    public MyException(String message) {
        super(message);
    }
}
