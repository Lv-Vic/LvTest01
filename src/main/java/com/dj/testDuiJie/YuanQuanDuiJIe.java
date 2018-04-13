package com.dj.testDuiJie;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * @Date: 2018-04-08 10:05
 * Mobile: 13161819553
 * @author: changXuan.lv
 */
public class YuanQuanDuiJIe {

    Logger logger = LoggerFactory.getLogger(YuanQuanDuiJIe.class);
    private static String yuanQuanUrl = "https://test-open-api.oxcoder.com";
    private static String appid = "72ce8bbf422c96d00656a848760d61e2";
    private static String secret = "23a5bd95d7dda71ca43857eb7c924c28";


    public static void main(String[] args) {
        String accessToken = "";
        try {
            accessToken = getAccessToken(appid, secret);
            System.out.println(accessToken);

            //学生信息
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObjectStu = new JSONObject();
            JSONObject jsonObjectStuOther = new JSONObject();
            String student_flag = "oxflag14";
            jsonObjectStu.put("student_flag",student_flag);
            jsonObjectStu.put("name","zhaoliu4");
            jsonObjectStu.put("phone","13333333334");
            jsonObjectStu.put("email","1234567@qq.com");
            jsonObjectStu.put("callback","http://ats.dajie.com/YuanQuanAssessmentScoreAction.do?cmd=insertScoreFromAssessment");
            jsonObjectStuOther.put("sex",1);
            jsonObjectStuOther.put("class_id",2);
            jsonObjectStu.put("other",jsonObjectStuOther.toJSONString());
            jsonArray.add(jsonObjectStu);
            String projid = "1234";
            String wishId = student_flag;

             // 获取TestUrl
//            String yuanQuanTestUrl = getYuanQuanTestUrl(wishId, projid, jsonArray);

            // 获取该公司全部试卷信息
//            getYuanQuanAllPapers(accessToken);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * @param appid  应⽤用申请唯⼀一id
     * @param secret 密钥
     * @return
     * @throws IOException
     */
    public static String getAccessToken(String appid, String secret) throws IOException {
        String url = yuanQuanUrl + "/auth/access_token?appid=" + appid + "&secret=" + secret;
        String access_token = "";
        CloseableHttpClient aDefault = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse execute = aDefault.execute(httpGet);
        if (execute != null) {
            if (execute.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return access_token;
            }
            HttpEntity entity = execute.getEntity();
            String resStr = EntityUtils.toString(entity);
            JSONObject jsonObject = JSON.parseObject(resStr);
            String status = jsonObject.getString("status");
            String success = "success";
            if (!success.equals(status)) {
                return access_token;
            }
            JSONObject data = jsonObject.getJSONObject("data");
            access_token = data.getString("access_token");
        }
        return access_token;
    }

    /**
     * 获取全部试卷信息
     * @param access_token
     * @return
     * @throws IOException
     */
    public static List getYuanQuanAllPapers(String access_token) throws IOException {
        String url = yuanQuanUrl + "/school/exam/get_exam_list";
        List list = new ArrayList();
        CloseableHttpClient aDefault = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("access_token", access_token);
        StringEntity stringEntity = new StringEntity(jsonObject.toString(), "UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
        CloseableHttpResponse execute = aDefault.execute(httpPost);
        if (execute != null) {
            if (execute.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return list;
            }
        }
        HttpEntity entity = execute.getEntity();
        String resStr = EntityUtils.toString(entity);
        JSONObject resStrJsonObject = JSON.parseObject(resStr);
        String status = resStrJsonObject.getString("status");
        String success = "success";
        if (!success.equals(status)) {
            return list;
        }
        JSONObject data = resStrJsonObject.getJSONObject("data");
        JSONArray paperList = data.getJSONArray("list");
        Iterator<Object> iterator = paperList.iterator();
        while (iterator.hasNext()){
            JSONObject next = (JSONObject)iterator.next();
            System.out.println(next);
        }
        return list;
    }

    /**
     *
     * @param projid
     * @param studentJsonArray
     * @return
     */
    public static String getYuanQuanTestUrl(String wishId, String projid, JSONArray studentJsonArray) throws IOException {

        String url = yuanQuanUrl + "/school/exam/add_student";
        String testUrl = "测评链接未分配成功,请联系管理员!";
        CloseableHttpClient aDefault = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = new JSONObject();
        String accessToken = getAccessToken(appid, secret);
        jsonObject.put("access_token", accessToken);
        jsonObject.put("exam_id", projid);
        jsonObject.put("info", studentJsonArray);
        System.out.println(jsonObject.toString());
        StringEntity stringEntity = new StringEntity(jsonObject.toString(),"UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
        CloseableHttpResponse execute;
        try {
            execute = aDefault.execute(httpPost);
            if (execute != null) {
                if (execute.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                    return testUrl;
                }
            }
            HttpEntity entity = execute.getEntity();
            String resStr = EntityUtils.toString(entity);
            JSONObject resStrJsonObject = JSON.parseObject(resStr);
            String status = resStrJsonObject.getString("status");
            String success = "success";
            if (!success.equals(status)) {
                return testUrl;
            }
            JSONObject data = resStrJsonObject.getJSONObject("data");
            if (data == null) {
                return testUrl;
            }
            JSONArray duplicate_student_flag = data.getJSONArray("duplicate_student_flag");
            if (duplicate_student_flag != null) {
                return testUrl;
            }
            JSONObject urls = data.getJSONObject("urls");
            if (urls == null) {
                return testUrl;
            }
            JSONObject backWishId = urls.getJSONObject(wishId);
            if (backWishId == null) {
                return testUrl;
            }
            testUrl = backWishId.getString("url");
            String code = backWishId.getString("code");

            System.out.println(code);
            System.out.println(resStrJsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testUrl;
    }


}
