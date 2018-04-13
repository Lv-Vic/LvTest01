package com.dj.statepo;


/**
 * User:changxuan.lv
 * TEL:13161819553
 * Email:changxuan.lv@dajie-inc.com
 * Date:2017-12-04
 * 蓝色下一个绿色，绿色下一个红色，红色下一个是蓝色，
 * 蓝色上一个红色，红色上一个是绿色，绿色上一个蓝色，
 */
public interface State {

    public void last(ContextColor context);
    public void next(ContextColor context);
    public String getState();
}
