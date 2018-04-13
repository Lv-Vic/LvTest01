package com.dj.testDuiJie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * User:changxuan.lv
 * TEL:13161819553
 * Email:changxuan.lv@dajie-inc.com
 * Date:2017-08-31
 */
public class TestWC {
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String weiceurl = "http://api.weicewang.com/v1/api?";
        String uid = "dajie";
        String key = "aim69tde7rqoun6b30nk";
        long time = System.currentTimeMillis() / 1000;
        String sec = uid + key + time;
        String s = "";
        try {
            s = EncoderByMd5(sec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = weiceurl + "uid=" + uid + "&time=" + time + "&sec=" + s;
        //获取测评链接  参数
        /*JSONObject nvps = new JSONObject();
        nvps.put("act", "post_cands");
        nvps.put("logname", "yl");
        nvps.put("projid", "P125601");
        JSONArray jsonArray = new JSONArray();
        JSONObject studentjson = new JSONObject();
        studentjson.put("candcode","9500100800002988119");
        studentjson.put("candname","大大");
        jsonArray.add(studentjson);
        nvps.put("cands", jsonArray);*/

        //获取成绩参数
        JSONObject nvps = new JSONObject();
        nvps.put("act", "get_status");
        nvps.put("projid", "P125768");
        JSONArray jsonArray = new JSONArray();
        JSONObject studentjson = new JSONObject();
        studentjson.put("candid", "1003488773");
        studentjson.put("testcode", "EEBRTM");
        jsonArray.add(studentjson);
        nvps.put("cands", jsonArray);
       //获取全部试卷
        /*JSONObject nvps = new JSONObject();
        nvps.put("act", "get_projs");
        nvps.put("logname", "hnair");*/




        CloseableHttpClient client = HttpClients.createDefault();
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            StringEntity ss = new StringEntity(nvps.toString(),"UTF-8");
            ss.setContentEncoding("UTF-8");
//            ss.setContentType("application/json");//发送json数据需要设置contentType

            post.setEntity(ss);
            post.setHeader("Content-Type", "application/json; charset=UTF-8");
            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                System.out.println(result);
                if (!StringUtils.isEmpty(result)) {
                    JSONObject jsonObject = JSON.parseObject(result);
                    String aTrue = jsonObject.getString("status");
                    if (aTrue.equals("true")) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        Iterator<Object> it = data.iterator();

                        while (it.hasNext()) {
                            JSONObject ob = (JSONObject) it.next();
                            String status = ob.getString("status");
                            if (status.equals("true")) {
                                String candid = ob.getString("candid");
                                String candcode = ob.getString("candcode");
                                JSONArray resultScore = ob.getJSONArray("result");
                                Iterator<Object> iterator = resultScore.iterator();
                                BigDecimal totalScore = new BigDecimal(0);
                                boolean needJudgeFlag = false;
                                while (iterator.hasNext()) {
                                    JSONObject scorejs = (JSONObject) iterator.next();
                                    String score = scorejs.getString("score");
                                    String needJudge = scorejs.getString("need_judge");
                                    if (StringUtils.isEmpty(score)) {
                                        continue;
                                    }
                                    needJudge = "";
                                    if ("1".equals(needJudge)){
                                        needJudgeFlag = true;
                                        break;
                                    }
                                    totalScore = totalScore.add(new BigDecimal(score));
                                }
                                if (needJudgeFlag){
                                    continue;
                                }
                                System.out.println("-------------");
                                System.out.println(totalScore);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
