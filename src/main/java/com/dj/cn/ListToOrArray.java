package com.dj.cn;

import org.springframework.util.StringUtils;

import java.util.*;

/**
 * User:changxuan.lv
 * TEL:13161819553
 * Email:changxuan.lv@dajie-inc.com
 * Date:2017-08-21
 */
public class ListToOrArray {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("1");
        List<String> list2 = new ArrayList<String>();
        list2.add("2");
        list.addAll(list2);
        String[] s = {"s1","s2"};
        List<String> list1 = Arrays.asList(s);
        String[] a = new String[list1.size()];
        String[] strings = list1.toArray(a);
        for (String ss:strings) {
            System.out.println(ss);
        }
        Collections.addAll(list,"a","b");
        System.out.println(list1);
        Map map = new HashMap();
        System.out.println(map.size());

    }

}
