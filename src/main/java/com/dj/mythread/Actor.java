package com.dj.mythread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: changxuan.lv@dajie-inc.com
 * Date: 2018-02-05 14:21
 * Mobile: 13161819553
 */
public class Actor extends Thread {
    private int $VAR1$;
    /**
     * $VAR1$
     */
    private String $VAR2$;



    @Override
    public void run(){


        System.out.println(getName() + "是一个演员");
        int count = 0;
        System.out.println(getName() + "登台演出：" + (++count));
        System.out.println(getName() + "的演出结束");
    }


    public static void main(String[] args) {
        Thread thread = new Actor();
        thread.setName("MR.Thread:");
        thread.start();

        List aa = new ArrayList();
    }
}
