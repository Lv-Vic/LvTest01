package com.dj.statepo;

/**
 * User:changxuan.lv
 * TEL:13161819553
 * Email:changxuan.lv@dajie-inc.com
 * Date:2017-12-04
 */
public class TestState {
    public static void main(String[] args) {
        ContextColor contextColor = new ContextColor();
        State redState = new RedState();
        contextColor.setState(redState);
        int i = 0;
        while (i < 9){
            System.out.println("当前状态 ： " + contextColor.getState().getState());
            System.out.println("下一个状态是 ： ");
            contextColor.pull();
            i++;
            System.out.println(i);
        }
    }
}
