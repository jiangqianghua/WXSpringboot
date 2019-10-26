package com.jqh.wxserver.bean;

import java.util.Map;

public class ImageMessage extends BaseMessage {

    public ImageMessage(Map<String, String> requestMap, String mediaId) {
        super(requestMap);
        this.setMsgId("image");
        this.mediaId = mediaId;
    }

    private String mediaId ;


    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
