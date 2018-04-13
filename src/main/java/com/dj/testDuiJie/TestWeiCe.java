package com.dj.testDuiJie;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User:changxuan.lv
 * TEL:13161819553
 * Email:changxuan.lv@dajie-inc.com
 * Date:2017-08-30
 */
public class TestWeiCe {
    public static void main(String[] args) {
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
        DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpGet get = new HttpGet(url);
        System.out.println(url);
            HttpResponse res = null;
            /*String resStr = "";

                res = httpClient.execute(get);
                if (res != null) {
                    System.out.println(res.getStatusLine().getStatusCode());
                    if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        HttpEntity entity = res.getEntity();
                        resStr = EntityUtils.toString(entity);
                        System.out.println(resStr);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
*/
        try {
            HttpPost post = new HttpPost(url);
//            List<Map<String,String>> nvps = new ArrayList<Map<String, String>>();
//            Map<String,String> map = new HashMap<String, String>();
//            map.put("act", " get_projs");
//            map.put("logname", "hnair");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("act", "get_projs"));
            nvps.add(new BasicNameValuePair("logname", "gxkj"));
            Map<String,String> studentMaps = new HashMap<String, String>();
            studentMaps.put("candcode","9200100800000074590");
            studentMaps.put("candname","大神");
            studentMaps.put("candsex","男");

            post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            HttpResponse result = new DefaultHttpClient().execute(post);

            String resStr = "";
            if ( result!= null) {
                if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = result.getEntity();
                    resStr = EntityUtils.toString(entity);
                }
            }
            JSONObject jsonAll = JSON.parseObject(resStr);
            System.out.println(resStr);
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
