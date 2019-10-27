package com.jqh.wxserver.user;

import com.jqh.wxserver.utils.HttpClientUtil;
import com.jqh.wxserver.utils.TokenUtils;

public class UserManager {
    public static final String GET_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    /**
     * 获取已经关注公众号的用户信息
     * @param openid
     * @return
     */
    public static String getUserInfo(String openid){
        String token = TokenUtils.getAccessToken();
        System.out.println("token="+token);
        String url = GET_USER_INFO_URL.replace(
                "ACCESS_TOKEN", token)
                .replace("OPENID", openid);
        System.out.println("url=" + url);
        return HttpClientUtil.doGet(url);
    }

    public static void main(String[] args) {
        System.out.println(getUserInfo("o-iULwF12CdCz17NK2S5Unfpd7QQ"));
    }
}
