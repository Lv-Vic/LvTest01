package com.dj.cn;

/**
 * Created by dajie on 17-6-2.
 */
public enum MyEnum {
    A1("1"),
    A2("2"),
    A3("3"),
    ;
    private final String value;
    MyEnum(String value) {
        this.value = value;
    }
    public String getValue(){
        return value;
    }


}
