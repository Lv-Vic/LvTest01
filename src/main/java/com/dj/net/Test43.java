package com.dj.net;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date: 2018-04-03 16:59
 * Mobile: 13161819553
 * @author: changXuan.lv
 */
public class Test43 {
    public static Map<String,Long> STAGEID = new HashMap<String, Long>(){{
        put("9104102800000000777",9200101600000000582L);
    }};
    public static void main(String[] args) {

        /*String url = "http://ats.dajie.com/YuanQuanAssessmentScoreAction.do?cmd=insertScoreFromAssessment";
        CloseableHttpClient aDefault = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("student_flag","11111");
        jsonObject.put("corpId","11111");
        jsonObject.put("exam_id","11111");
        jsonObject.put("percent","11111");
        jsonObject.put("report_url","11111");
        System.out.println(jsonObject.toJSONString());
        StringEntity stringEntity = new StringEntity(jsonObject.toString(),"UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
        try {
            CloseableHttpResponse execute = aDefault.execute(httpPost);
            HttpEntity entity = execute.getEntity();
            String s = EntityUtils.toString(entity);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        JSONObject jsonObjectStu1 = new JSONObject();
        JSONObject jsonObjectStu = JSONObject.parseObject("{\"student_flag\":\"11111\",\"exam_id\":\"11111\",\"percent\":\"11111\",\"report_url\":\"11111\",\"other\": \"{'sex':'1','class_id':'123456789'}\"}");
       /* JSONObject jsonObjectStuOther = new JSONObject();
        String student_flag = "oxflag13";
        jsonObjectStu.put("student_flag",student_flag);
        jsonObjectStu.put("exam_id","zhaoliu2");
        jsonObjectStu.put("percent","13333333337");
        jsonObjectStu.put("report_url","1234567@qq.com");
        jsonObjectStuOther.put("sex",1);
        jsonObjectStuOther.put("class_id",12234);
        jsonObjectStu.put("other",jsonObjectStuOther.toJSONString());*/
        String wishId = jsonObjectStu.getString("student_flag");
        String tbSubjectId = jsonObjectStu.getString("exam_id");
        String score = jsonObjectStu.getString("percent");
        String report_url = jsonObjectStu.getString("report_url");
        JSONObject other = jsonObjectStu.getJSONObject("other");
        String corpId = other.getString("class_id");
        System.out.println("1");

        JSONObject jsonObject = JSONObject.parseObject("{'class_id':'021231231321321'}");
        String class_id = jsonObject.get("class_id").toString();
        System.out.println(class_id);

    }
}
