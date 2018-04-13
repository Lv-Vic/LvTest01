package com.dj.statepo;

/**
 * User:changxuan.lv
 * TEL:13161819553
 * Email:changxuan.lv@dajie-inc.com
 * Date:2017-12-04
 */
public class ContextColor {

    private State state = null;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void push(){
        state.last(this);
        System.out.println(state.getState());
    }
    public void pull(){
        state.next(this);
        System.out.println(state.getState());
    }
}
