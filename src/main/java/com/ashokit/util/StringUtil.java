package com.ashokit.util;

public class StringUtil {

    public static boolean isNullOrEmpty(String value){

        return value == null || value.trim().length() == 0 ;

    }

    public static boolean isNotNullAndNotEmpty(String value){

        return value != null && value.trim().length() != 0;

    }
}
