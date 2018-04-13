package com.dj.candystate;

/**
 * User:changxuan.lv
 * TEL:13161819553
 * Email:changxuan.lv@dajie-inc.com
 * Date:2017-12-04
 */
public class SoldOutState implements CandyState {
    private CandyMachine candyMachine;

    public SoldOutState(CandyMachine candyMachine) {
        this.candyMachine = candyMachine;
    }

    public void insertCoin() {
        System.out.println("you can't insert coin,the machine sold out!");
    }

    public void returnCoin() {
        System.out
                .println("you can't return,you haven't inserted a coin yet!");
    }

    public void turnCrank() {
        System.out.println("you turned,but there are no candies!");
    }

    public void dispense() {

    }

    public void printstate() {
        System.out.println("***SoldOutState***");
    }
}
