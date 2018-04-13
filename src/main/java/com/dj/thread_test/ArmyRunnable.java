package com.dj.thread_test;

/**
 * @Date: 2018-03-29 15:38
 * Mobile: 13161819553
 * @author: changXuan.lv
 * 军队线程
 * 模拟作战双方的行为
 */
public class ArmyRunnable implements Runnable {
    public void run() {

    }

    ArmyRunnable armyTaskOfSuiDynasty = new ArmyRunnable();
    ArmyRunnable armyTaskOfRevolt = new ArmyRunnable();

    /**
     * 使用runnable 接口创建线程
     */
    Thread armyOfSuiDynasty = new Thread(armyTaskOfSuiDynasty, "随军");
    Thread armyOfRevoltnew = new Thread(armyTaskOfRevolt, "农民起义军");

}
