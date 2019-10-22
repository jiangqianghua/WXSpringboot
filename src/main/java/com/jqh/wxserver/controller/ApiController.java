package com.jqh.wxserver.controller;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/weixin")
public class ApiController {

    public static final String TOKEN = "jqh";
    @GetMapping("/wx")
    public String wxget(String signature,String timestamp,String nonce,String echostr){
        System.out.println("wx");
        System.out.println("signature=" + signature);
        System.out.println("timestamp=" + timestamp);
        System.out.println("nonce=" + nonce);
        System.out.println("echostr=" + echostr);
        if(check(timestamp, nonce, signature)){
            System.out.println("验证成功");
            return echostr;
        }
        return "wx";
    }

    @PostMapping("/wx")
    public String wxpost(HttpServletRequest request)throws Exception{

//        ServletInputStream is = request.getInputStream();
//        byte[] b = new byte[1024];
//        int len;
//        StringBuilder sb = new StringBuilder();
//        while((len = is.read(b))!= -1){
//            sb.append(new String(b,0,len));
//        }
//        System.out.println("wxpost=" + sb.toString());
        Map<String,String> map = parseRequest(request.getInputStream());
        System.out.println(map.toString());
        return "<xml>\n" +
                "  <ToUserName><![CDATA[o-iULwCy9Qy44Tiq8ZskKavlaktU]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[gh_5707e632245d]]></FromUserName>\n" +
                "  <CreateTime>1571760815</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[你好]]></Content>\n" +
                "</xml>";
    }

    private boolean check(String timestamp, String nonce, String sigature){
        //1）将token、timestamp、nonce三个参数进行字典序排序
        String[] strs = new String[]{TOKEN, timestamp, nonce};
        Arrays.sort(strs);
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        String str = strs[0] + strs[1] + strs[2];
        String mysig = sha1(str);
        //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if(sigature.equals(mysig))
            return true;
        return  false;
    }

    private String sha1(String src){
        try {
            MessageDigest md = MessageDigest.getInstance("sha1");
            byte[] digest = md.digest(src.getBytes());
            char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            StringBuilder sb = new StringBuilder();
            for(byte b:digest){
                sb.append(chars[(b>>4)&15]);
                sb.append(chars[b&15]);
            }
            System.out.println(sb.toString());
            return sb.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return "";
    }

    public Map<String, String> parseRequest(InputStream is){
        SAXReader reader = new SAXReader();
        Map<String,String> map = new HashMap<>();
        try{
            Document document = reader.read(is);
            Element root = document.getRootElement();
            List<Element> elements = root.elements();
            for(Element e:elements){
                map.put(e.getName(), e.getStringValue());
            }
            return map;
        }catch (DocumentException e){
            e.printStackTrace();
        }
        return null;
    }

}
