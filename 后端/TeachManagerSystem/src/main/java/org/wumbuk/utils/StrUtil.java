package org.wumbuk.utils;

import java.math.BigDecimal;

//字符串处理的util
public class StrUtil
{

    public  static  boolean isNum(String strkey){

        try {
            new BigDecimal(strkey);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
