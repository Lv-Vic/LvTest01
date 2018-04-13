package com.dj.candystate;

/**
 * User:changxuan.lv
 * TEL:13161819553
 * Email:changxuan.lv@dajie-inc.com
 * Date:2017-12-04
 */
public class CandyMachine {
    CandyState mSoldOutState;
    CandyState mOnReadyState;
    CandyState mHasCoin;
    CandyState mSoldState;
    CandyState mWinnerState;

    public CandyState getCandyState() {
        return candyState;
    }

    public void setCandyState(CandyState candyState) {
        this.candyState = candyState;
    }

    private CandyState candyState = null;

    private int count = 0;

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public CandyMachine(int count) {
        this.count = count;
        mSoldOutState = new SoldOutState(this);
        mOnReadyState = new OnReadyState(this);
        mHasCoin = new HasCoin(this);
        mSoldState = new SoldState(this);
        mWinnerState = new WinnerState(this);
        if (count > 0) {
            candyState = mOnReadyState;
        } else {
            candyState = mSoldOutState;
        }
    }

    public void insertCoin() {
        candyState.insertCoin();
    }

    public void returnCoin() {
        candyState.returnCoin();
    }

    public void turnCrank() {
        candyState.turnCrank();
        candyState.dispense();
    }

    void releaseCandy() {
        if (count > 0) {
            count = count - 1;
            System.out.println("a candy rolling out!");
        }

    }

    public void printstate() {
        candyState.printstate();
    }




}
