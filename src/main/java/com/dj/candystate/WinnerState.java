package com.dj.candystate;

/**
 * User:changxuan.lv
 * TEL:13161819553
 * Email:changxuan.lv@dajie-inc.com
 * Date:2017-12-04
 */
public class WinnerState implements CandyState {

    private CandyMachine candyMachine;

    public WinnerState(CandyMachine candyMachine) {
        this.candyMachine = candyMachine;
    }

    public void insertCoin() {
        System.out.println("please wait!we are giving you a candy!");
    }

    public void returnCoin() {
        System.out.println("you haven't inserted a coin yet!");
    }

    public void turnCrank() {
        System.out
                .println("we are giving you a candy,turning another get nothing,!");
    }

    public void dispense() {
        candyMachine.releaseCandy();
        if (candyMachine.getCount() == 0) {
            candyMachine.setCandyState(candyMachine.mSoldOutState);
        } else {
            System.out.println("you are a winner!you get another candy!");
            candyMachine.releaseCandy();
            if (candyMachine.getCount() > 0) {
                candyMachine.setCandyState(candyMachine.mOnReadyState);
            } else {
                System.out.println("Oo,out of candies");
                candyMachine.setCandyState(candyMachine.mSoldOutState);
            }
        }
    }

    public void printstate() {
        System.out.println("***WinnerState***");

    }
}
