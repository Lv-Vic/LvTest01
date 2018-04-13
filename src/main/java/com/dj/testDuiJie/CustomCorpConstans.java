package com.dj.testDuiJie;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 有定制需求的CorpId,以及其可能需要的静态数据放入此类
 *
 * @author: kangkang.lv
 * @date: 16-12-12 下午6:44
 */
public final class CustomCorpConstans {

    public static final String dajieceshiCorpId = "9100102800000000001";     //dajieceshi

    public static final String newh3c2018 ="9104102800000000645";   //新华三




    public static final String shunyu2018 = "9104102800000000611";    //舜宇光学科技集团2018定制corpid
    public static final String sunnygd2018 = "9104102800000000764";    //宁波舜宇光电信息有限公司2018定制corpid


    public static final String junleruye2018 = "9104102800000000749";    //石家庄君乐宝乳业有限公司2018定制corpid







    //test
    public static final Map<String,String> gePassTemplateMap = new HashMap<String, String>();
    static {
        gePassTemplateMap.put("9104101600000001787","9001102400000000072");
        gePassTemplateMap.put("9104101600000001788","9001102400000000075");
        gePassTemplateMap.put("9104101600000001789","9001102400000000074");
        gePassTemplateMap.put("9104101600000001790","9001102400000000076");
    }

    //阿迪达斯Adidas2018的定制评分项
    public static final Map<String,String> ResumeItems_Adidas2018 = new HashMap<String, String>(){{
        put("CombineBrands","Brands组合评分");
        put("CombineMerchandising","Merchandising组合评分");
        put("CombineSales","Sales组合评分");
        put("CombineRetail","Retail组合评分");
        put("CombineRetailE","RetailE组合评分");
        put("CombineDigitalE","DigitalE组合评分");
        put("CombineDigitalIT","DigitalIT组合评分");
        put("CombineHR","HR组合评分");
        put("CombineFinance","Finance组合评分");
        put("CombineBD","BD组合评分");
        put("CombineSCM","SCM组合评分");
        put("CombineReebok","Reebok组合评分");
    }};

}
