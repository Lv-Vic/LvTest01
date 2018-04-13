package com.dj.candystate;

import java.util.Random;

/**
 * User:changxuan.lv
 * TEL:13161819553
 * Email:changxuan.lv@dajie-inc.com
 * Date:2017-12-04
 */
public class HasCoin implements CandyState {

    private CandyMachine mCandyMachine;

    public HasCoin(CandyMachine mCandyMachine) {
        this.mCandyMachine = mCandyMachine;
    }

    public void insertCoin() {
        System.out.println("you can't insert another coin!");
    }

    public void returnCoin() {
        System.out.println("coin return!");
        mCandyMachine.setCandyState(mCandyMachine.mOnReadyState);
    }

    public void turnCrank() {
        System.out.println("crank turn...!");
        Random ranwinner = new Random();
        int winner = ranwinner.nextInt(10);
        if (winner == 0) {
            mCandyMachine.setCandyState(mCandyMachine.mWinnerState);

        } else {
            mCandyMachine.setCandyState(mCandyMachine.mSoldState);

        }
    }

    public void dispense() {

    }

    public void printstate() {
        System.out.println("***HasCoin***");
    }
}
