package com.jqh.wxserver.utils;

import com.jqh.wxserver.bean.AccessToken;

import java.util.Map;

public class TokenUtils {

    private static final String TOKEN_UTL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    private static final String APPID = "wxb88c9e0dade34d8b";
    private static final String APPSECRENT = "9675022ead0b9dd248557b99403a48f8";
    private static String token = null ;//"26_kYC5WSFCtW3293eBQZWgDT6moFHzoqxNWyeoxoCIoUjzhndqUCaPNZ6bVlTS9-ZNLcGN34truyv6sN1icX5PZyMkbfFeTZVxyzSmqt5Be8bKlnFZLgvXNIteEBOAN1PksOBMLgHkuEtWaPC1BASfAHAMLD";
    private static AccessToken accessToken = null ;
    /**
     * 根据code换取token，该token和普通接口token不一样，只能用于网页授权
     */
    private static final String CODE_ASSCE_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    private static void getToken(){
        if (accessToken != null){
            return ;
        } else {
            String url = TOKEN_UTL + "&appid="+APPID+"&secret="+APPSECRENT;
            String res = HttpClientUtil.doGet(url);
            System.out.println(res);
            Map<String, Object> resMap = JsonUtils.toMap(res);
            String token = String.valueOf(resMap.get("access_token"));
            Double expires = (Double)resMap.get("expires_in");
            accessToken = new AccessToken(token, expires);
            System.out.println(accessToken.toString());
        }
    }

    public static String getAccessToken(){
        if (accessToken == null || accessToken.isEXpired()) {
            getToken();
        }
        return accessToken.getAccessToken();
    }

    public static void main(String[] args) {
        getAccessToken();
    }

    /**
     * 根据code换取token
     * @param code
     * @return
     */
    public static String codeToToken(String code){
        String url = CODE_ASSCE_TOKEN.replace("APPID","wxb88c9e0dade34d8b")
                .replace("SECRET","9675022ead0b9dd248557b99403a48f8")
                .replace("CODE", code);
        String result = HttpClientUtil.doGet(url);
        System.out.println(result);
        return result;

//        {
//            "access_token":"ACCESS_TOKEN",
//                "expires_in":7200,
//                "refresh_token":"REFRESH_TOKEN",
//                "openid":"OPENID",
//                "scope":"SCOPE"
//        }
    }
}
