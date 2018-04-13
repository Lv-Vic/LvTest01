package com.dj.net;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User:changxuan.lv
 * TEL:13161819553
 * Email:changxuan.lv@dajie-inc.com
 * Date:2017-09-07
 */
public class GuavaTester {
    public static void main(String args[]){
        GuavaTester tester = new GuavaTester();
        tester.testJoiner();
        Joiner joiner = Joiner.on(";").skipNulls();
        String join = joiner.join("Harry", null, "Ron", "Hermione");
        System.out.println(join);
        ArrayList<Object> objects = Lists.newArrayList();
        List<Integer> integers = Lists.newArrayList();
    }

    private void testJoiner(){
        System.out.println(Joiner.on(",")
                .skipNulls()
                .join(Arrays.asList(1,2,3,4,5,null,6)));
    }
}
