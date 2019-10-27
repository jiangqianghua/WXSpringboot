package com.jqh.wxserver.qrcode;

import com.jqh.wxserver.utils.HttpClientUtil;
import com.jqh.wxserver.utils.JsonUtils;
import com.jqh.wxserver.utils.TokenUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取带参数的二维码，可以让用户扫码关注，并分析是从哪个场景进入的
 */
public class QRCodeManager {
    //获取带参数的二维码的过程包括两步，首先创建二维码ticket，然后凭借ticket到指定URL换取二维码。

    public static final String GET_TICKET = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
//    public static final String GET_SHORT_URL = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN&action=long2short&long_url=LONG_URL";
public static final String GET_SHORT_URL = "https://api.weixin.qq.com/cgi-bin/shorturl";
    public static String getTicket(String token){
        String url = GET_TICKET.replace("TOKEN", token);
        String json = "{\"expire_seconds\": 604800, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"jqh\"}}}";
        String res = HttpClientUtil.doPost(url,json);
        // System.out.println(res);
        Map<String,String> map = JsonUtils.toMap(res);
        String ticket = map.get("ticket");
        return ticket;
    }

    public static String toShortUrl(String longUrl){
        String token = TokenUtils.getAccessToken();
//        String url = GET_SHORT_URL.replace("ACCESS_TOKEN", token)
//                .replace("LONG_URL", longUrl);
//        Map<String,String> params = new HashMap<>();
//        params.put("access_token", token);
//        params.put("action", "long2short");
//        params.put("long_url", longUrl);
        String json = "{\"access_token\":\""+token+"\",\"action\":\"long2short\",\"long_url\":\""+longUrl+"\"}";
        System.out.println(json);
        String url = GET_SHORT_URL + "?access_token" + token;
        String res = HttpClientUtil.doPost(url,json);
        System.out.println("短链接" + res);
        return "";
    }

//"{\"action\":\"long2short\",\"long_url\":\"http://wap.koudaitong.com/v2/showcase/goods?alias=128wi9shh&spm=h56083&redirect_count=1\"}"
    public static void main(String[] args) {
        String ticket = getTicket(TokenUtils.getAccessToken());

        // 根据ticket换取二维码，直接get请求
        //https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQFE8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyT21EMGQ2V0JjYjAxQmNmLU50Y0oAAgTMFLVdAwSAOgkA
        // 用户扫码后，接收到消息是
        //{Ticket=gQFE8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyT21EMGQ2V0JjYjAxQmNmLU50Y0oAAgTMFLVdAwSAOgkA, CreateTime=1572149167, EventKey=jqh, Event=SCAN, ToUserName=gh_5707e632245d, FromUserName=o-iULwCy9Qy44Tiq8ZskKavlaktU, MsgType=event}

        // 换成短链接
        String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + ticket;
        toShortUrl(url);



    }
}
