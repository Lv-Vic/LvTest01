package com.dj.net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dj.candystate.MsgQueue;
import com.dj.cn.CalendarMYUtil;
import com.dj.cn.MyEnum;
import net.sf.json.JSONArray;

import javax.print.DocFlavor;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: changxuan.lv@dajie-inc.com
 * Date: 2018-02-12 10:00
 * Mobile: 13161819553
 */
public class IIItest {
    public static void main(String[] args) {
        List<MyEnum> list = new ArrayList<MyEnum>();
        MyEnum myEnum = MyEnum.A1;
        MyEnum myEnum1 = MyEnum.A2;
        MyEnum myEnum2 = MyEnum.A3;
        list.add(myEnum);
        list.add(myEnum1);
        list.add(myEnum2);
        StringBuilder stringBuilder = new StringBuilder();
        for (MyEnum anEnum : list) {
            stringBuilder.append(anEnum.getValue());
        }
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aa","aa");
        String s = JSON.toJSONString(list1);
        String aaa = "[]";
        JSONArray jsonArray = JSONArray.fromObject(aaa);
        Iterator iterator = jsonArray.iterator();
        int a = 2;
        int b = 1;
//        String s1 = stringStringMap.get("1");
//        boolean add = list1.add(s1);
//        System.out.println(list1.size());
//        System.out.println(s1);
//        stringStringMap.put("1","0");
//        stringStringMap.put("2","2");
//        net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(stringStringMap);
        CalendarMYUtil.getTest1();
//        System.out.println(MsgQueue.rellyOne);
        int i = 1;
        List<Map<String,String>> campusProjectIdList = new ArrayList<Map<String, String>>();

        System.out.println(list1.size());
        for (String s2 : list1) {
            Map<String,String> stringStringMap = new HashMap<String, String>();
            stringStringMap.put("aa",i++ + "");
            campusProjectIdList.add(stringStringMap);
        }
        System.out.println(campusProjectIdList.toString());
        MyEnum[] values = MyEnum.values();
        for (MyEnum value : values) {
        }
        Map<String,String> map = new HashMap<String, String>();
        map.put("1","a");
        map.put("2","b");
        List<String> list2 = new ArrayList<String>();
        list2.add("11");
        list2.add("222");
        System.out.println(list2.contains("22"));
    }

    public static void get(){
    }
}
