package com.jqh.wxserver.utils;

import com.jqh.wxserver.bean.*;
import com.thoughtworks.xstream.XStream;

import java.util.HashMap;
import java.util.Map;

public class XMLUtils {

    public static String beanToXml(BaseMessage baseMessage) {
        if (baseMessage == null)
            return "";
        XStream stream = new XStream();
        stream.processAnnotations(TextMessage.class);
        stream.processAnnotations(ImageMessage.class);
        stream.processAnnotations(MusicMessage.class);
        stream.processAnnotations(NewsMessage.class);
        stream.processAnnotations(VoiceMessage.class);
        stream.processAnnotations(VideoMessage.class);
        return stream.toXML(baseMessage);
    }

    public static void main(String[] args) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("ToUserName", "to");
        requestMap.put("FromUserName", "from");
        requestMap.put("MsgType", "text");
        TextMessage textMessage = new TextMessage(requestMap,"这是文本消息");
        String xml = beanToXml(textMessage);
        System.out.println(xml);
    }

}
