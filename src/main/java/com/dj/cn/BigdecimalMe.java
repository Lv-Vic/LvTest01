package com.dj.cn;

import java.math.BigDecimal;

/**
 * User:changxuan.lv
 * TEL:13161819553
 * Email:changxuan.lv@dajie-inc.com
 * Date:2017-09-08
 */
public class BigdecimalMe {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(1.0);
        BigDecimal bigDecimal2 = new BigDecimal("2.0");
        BigDecimal bigDecimal3 = new BigDecimal(3);

        BigDecimal add = bigDecimal.add(bigDecimal2);
        System.out.println(add);
        BigDecimal subtract = add.subtract(bigDecimal3);
        System.out.println(subtract);

        BigDecimal divide = add.divide(bigDecimal3);
        System.out.println(divide);

        BigDecimal bigDecimal1 = new BigDecimal("1.0000");
        BigDecimal multiply = add.multiply(bigDecimal1);
        System.out.println(multiply);

        Runtime runtime = Runtime.getRuntime();

    }
}
