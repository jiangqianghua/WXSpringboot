package com.jqh.wxserver.manager;

import com.jqh.wxserver.utils.HttpClientUtil;
import com.jqh.wxserver.utils.TokenUtils;

/**
 * 设置模板消息
 */
public class TemplateMessageManager {

    public static String SET_INDUSTRY_URL = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=";
    public static String GET_INDUSTRY_URL = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=";
    public static String SEND_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
    /**
     * 设置行业
     */
    public static void setIndustry(String json){
        String token = TokenUtils.getAccessToken();
        String url = SET_INDUSTRY_URL + token;
        String res = HttpClientUtil.doPost(url, json);
        System.out.println(res);
    }

    public static String getIndustry(){
        String token = TokenUtils.getAccessToken();
        String url = GET_INDUSTRY_URL + token;
        String res = HttpClientUtil.doGet(url);
        System.out.println(res);
        return res;
    }

    public static void sendTemplateMessage(String json) {
        String token = TokenUtils.getAccessToken();
        String url = SEND_TEMPLATE_URL + token;
        String res = HttpClientUtil.doPost(url, json);
        System.out.println(res);
    }

    public static void main(String[] args) {
        String json = "{\n" +
                "    \"industry_id1\":\"1\",\n" +
                "    \"industry_id2\":\"4\"\n" +
                "}";
        //setIndustry(json);
        //getIndustry();

        // 发送模板消息
        String sendJson = " {\n" +
                " \"touser\":\"o-iULwCy9Qy44Tiq8ZskKavlaktU\",\n" +
                " \"template_id\":\"tZjfnNKSpEviuu2FIAHa9kSOvMtUtQOU0VDhxvAhX1w\",\n" +
                " \"data\":{\n" +
                " \"first\": {\n" +
                " \"value\":\"待办任务通知！\",\n" +
                " \"color\":\"#173177\"\n" +
                " },\n" +
                " \"keyword1\":{\n" +
                " \"value\":\"中午去纽约开会\",\n" +
                " \"color\":\"#173177\"\n" +
                " },\n" +
                " \"keyword2\": {\n" +
                " \"value\":\"2019-10-27\",\n" +
                " \"color\":\"#173177\"\n" +
                " },\n" +
                " \"keyword3\": {\n" +
                " \"value\":\"纽约市\",\n" +
                " \"color\":\"#173177\"\n" +
                " },\n" +
                " \"remark\":{\n" +
                " \"value\":\"欢迎使用待办任务！\",\n" +
                " \"color\":\"#173177\"\n" +
                " }\n" +
                " }\n" +
                " }";
        sendTemplateMessage(sendJson);
    }

}