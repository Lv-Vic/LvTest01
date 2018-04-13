package com.dj.testDuiJie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * User:changxuan.lv
 * TEL:13161819553
 * Email:changxuan.lv@dajie-inc.com
 * Date:2017-09-21
 */
public class NiuKETest {
    static final String apiBaseUrl = "http://api.nowcoder.com";
    static final String addTestUserUrl = "/v1/papers/%d/test_users";    //给试卷分配用户
    static final String selfPath = "/v1/users/self";                    //获取账户信息
    static final String allPapersPath = "/v1/papers/oid-%d";            //获取客户所有试卷

    static final String callback = "http://ats.dajie.com/CrAssessmentDetailAction.do?cmd=getNiuKeAssessMentData";

    static final String keySplitStr = ",,,";


    //每当加一个要对接牛客测评的公司 就要来put这两个Map
    public static final Map<String, String> nowcoderCorpKeyMap = new HashMap<String, String>() {{
        put("9104102800000000615", "wanmei" + keySplitStr + "56a9c47c6294b36c508d4403b7202be5"); //完美世界2018校园招聘
        put("9104102800000000640", "MOBIKE" + keySplitStr + "a9c08fb9e1a60704659baa890ae40506"); //摩拜2018校园招聘
        put("9104102800000000962", "zhao_haiyan@leapmotor.com" + keySplitStr + "414aab26276efba05f06f35c8c33f053"); //摩拜2018校园招聘


    }};

    public static void main(String[] args) {
        String corpId = "9104102800000000962";
        String paperId = "8207674";
        String userKey = "9005100800001094608";
        String userName = "赵羽彤";
        String testUrl = null;
        List<CrAssessmentSubjectVo> allPapers = new ArrayList<CrAssessmentSubjectVo>();
        try {
            //获取对应的测评试卷链接
//            testUrl = getTestUrl(corpId,paperId,userKey,userName);
            //获取该公司下所有的测评试卷
            allPapers = getAllPapers(corpId);
        } catch (IOException e) {
            e.getMessage();
        }
        System.out.println(allPapers);
    }

    public static String getTestUrl(String corpId, String paperId, String userKey, String userName) throws IOException {
        String[] adminKeys = nowcoderCorpKeyMap.get(corpId).split(keySplitStr);
        String apiKey = adminKeys[0];
        String secretKey = adminKeys[1];
        String path = String.format(addTestUserUrl, Long.valueOf(paperId));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(apiBaseUrl + path);

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("key", userKey));
        nvps.add(new BasicNameValuePair("name", userName));
        nvps.add(new BasicNameValuePair("callback", callback));
        nvps = makeSign(nvps, apiKey, secretKey, path);
        //URL Encode
        post.setEntity(new UrlEncodedFormEntity(nvps, Charset.forName("UTF-8")));
        HttpResponse res = httpClient.execute(post);
        String testUrl = "";
        String resStr = "";
        if (res != null) {
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                resStr = EntityUtils.toString(entity);
            }
        }
        if (StringUtils.isEmpty(resStr)) {
            return testUrl;
        }
        JSONObject json = JSON.parseObject(resStr);
        JSONArray data = json.getJSONArray("data");
        JSONObject userTestInfo = (JSONObject) data.get(0);
        testUrl = userTestInfo.getString("testUrl");
        return testUrl;
    }

    public static List<NameValuePair> makeSign(List<NameValuePair> nvps, String apiKey, String secretKey, String path) {
        final long timestamp = System.currentTimeMillis() / 1000;
        nvps.add(new BasicNameValuePair("api_key", apiKey));
        nvps.add(new BasicNameValuePair("timestamp", String.valueOf(timestamp)));
        nvps.add(new BasicNameValuePair("path", path));
        Collections.sort(nvps, new Comparator<NameValuePair>() {
            public int compare(NameValuePair o1, NameValuePair o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        List<String> pairs = new ArrayList<String>();
        for (NameValuePair nvp : nvps) {
            pairs.add(nvp.getName() + "=" + nvp.getValue());
        }

        String unSignString = pairs.toString() + '&' + secretKey;
        String sign = DigestUtils.md5Hex(unSignString);
        nvps.add(new BasicNameValuePair("sign", sign));
        return nvps;
    }

    /**
     * 查处此客户所有测评试卷
     *
     * @param corpId
     * @return
     * @throws IOException
     */
    public static List<CrAssessmentSubjectVo> getAllPapers(String corpId) throws IOException {
        try {
            String[] adminKeys = nowcoderCorpKeyMap.get(corpId).split(keySplitStr);
            String apiKey = adminKeys[0];
            String secretKey = adminKeys[1];
            Long ownerId = getOwnerId(apiKey, secretKey);
            String path = String.format(allPapersPath, ownerId);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps = makeSign(nvps, apiKey, secretKey, path);
            StringBuffer param = new StringBuffer();
            for (NameValuePair nvp : nvps) {
                param.append("&");
                param.append(nvp.getName() + "=" + nvp.getValue());
            }
            String url = apiBaseUrl + path + "?" + param.substring(1, param.length());
            HttpGet get = new HttpGet(url);
            HttpResponse res = httpClient.execute(get);
            String resStr = "";
            if (res != null) {
                //System.out.println(res.getStatusLine().getStatusCode());
                if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = res.getEntity();
                    resStr = EntityUtils.toString(entity);
                }
            }
            if (!StringUtils.isEmpty(resStr)) {
                JSONObject jsonAll = JSON.parseObject(resStr);
                JSONArray jsonArray = jsonAll.getJSONObject("data").getJSONArray("datas");
                List<CrAssessmentSubjectVo> paperList = new ArrayList<CrAssessmentSubjectVo>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject paperJson = (JSONObject) jsonArray.get(i);
                    CrAssessmentSubjectVo paperVo = new CrAssessmentSubjectVo();
                    paperVo.setSubjectId(paperJson.getString("id"));
                    paperVo.setSubjectName(paperJson.getString("paperName"));
                    paperList.add(paperVo);
                }
                return paperList;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    /**
     * 获取客户在牛客的账户id
     *
     * @param apiKey
     * @param secretKey
     * @return
     * @throws IOException
     */
    private static Long getOwnerId(String apiKey, String secretKey) throws IOException {
        String path = selfPath;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps = makeSign(nvps, apiKey, secretKey, path);
        StringBuffer param = new StringBuffer();
        for (NameValuePair nvp : nvps) {
            param.append("&");
            param.append(nvp.getName() + "=" + nvp.getValue());
        }

        String url = apiBaseUrl + path + "?" + param.substring(1, param.length());
        HttpGet get = new HttpGet(url);
        HttpResponse res = httpClient.execute(get);
        Long ownerId = 0l;
        String resStr = "";
        if (res != null) {
            System.out.println(res.getStatusLine().getStatusCode());
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                resStr = EntityUtils.toString(entity);
            }
        }
        if (StringUtils.isEmpty(resStr)) {
            return ownerId;
        }
        JSONObject json = JSON.parseObject(resStr);
        JSONObject ownerInfo = JSON.parseObject(json.getString("data"));
        return ownerInfo.getLong("id");
    }

}
