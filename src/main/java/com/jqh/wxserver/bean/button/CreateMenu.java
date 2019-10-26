package com.jqh.wxserver.bean.button;

import com.jqh.wxserver.utils.HttpClientUtil;
import com.jqh.wxserver.utils.JsonUtils;
import com.jqh.wxserver.utils.TokenUtils;

/**
 * 创建菜单
 */
public class CreateMenu {

    public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
    public static void main(String[] args) {
        Button btn = new Button();
        btn.getButton().add(new ClickButton("一级点击", "1"));
        btn.getButton().add(new ViewButton("一级点跳转", "http://www.baidu.com"));
        SubButton sb = new SubButton("一级sub菜单");
        sb.getSub_button().add(new ClickButton("二级点击","32"));
        btn.getButton().add(sb);

        String json = JsonUtils.toJson(btn);
        System.out.println(json);

        String token = TokenUtils.getAccessToken();
        String url = CREATE_MENU_URL + token;

        String result = HttpClientUtil.doPost(url,json);

        System.out.println(result);

    }
}
