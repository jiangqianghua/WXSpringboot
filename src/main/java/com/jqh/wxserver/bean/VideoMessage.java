package com.jqh.wxserver.bean;

import java.util.Map;

public class VideoMessage extends BaseMessage {
    private String mediaId ;

    private String description ;

    private String title;

    public VideoMessage(Map<String, String> requestMap, String mediaId, String description, String title) {
        super(requestMap);
        setMsgType("video");
        this.mediaId = mediaId;
        this.description = description;
        this.title = title;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
