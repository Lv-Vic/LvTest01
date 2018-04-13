package com.dj.statepo;

/**
 * User:changxuan.lv
 * TEL:13161819553
 * Email:changxuan.lv@dajie-inc.com
 * Date:2017-12-04
 */
public class RedState implements State {
    public void last(ContextColor context) {
        context.setState(new GreenState());
    }

    public void next(ContextColor context) {
        context.setState(new BlueState());
    }

    public String getState() {
        return "red";
    }
}
