package com.dj.candystate;

/**
 * User:changxuan.lv
 * TEL:13161819553
 * Email:changxuan.lv@dajie-inc.com
 * Date:2017-12-04
 */
public class OnReadyState implements CandyState {
    private CandyMachine candyMachine;

    public OnReadyState(CandyMachine candyMachine) {
        this.candyMachine = candyMachine;
    }

    public void insertCoin() {
        System.out.println("you have inserted a coin,next,please turn crank!");
        candyMachine.setCandyState(candyMachine.mHasCoin);
    }

    public void returnCoin() {
        System.out.println("you haven't inserted a coin yet!");
    }

    public void turnCrank() {
        System.out.println("you turned,but you haven't inserted a coin!");
    }

    public void dispense() {

    }

    public void printstate() {
        System.out.println("***OnReadyState***");
    }
}
