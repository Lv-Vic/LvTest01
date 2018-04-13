package com.dj.thread_test;

/**
 * @Date: 2018-03-29 15:57
 * Mobile: 13161819553
 * @author: changXuan.lv
 */
public class KeyPersonThead extends Thread{
    public KeyPersonThead(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始了战斗");
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "左突右杀，攻击随军");
        }
        System.out.println(Thread.currentThread().getName() + "结束了战斗！");
    }
}
