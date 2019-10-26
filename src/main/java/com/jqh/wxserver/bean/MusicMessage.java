package com.jqh.wxserver.bean;

import java.util.Map;

public class MusicMessage extends BaseMessage {

    private Music music;

    public MusicMessage(Map<String, String> requestMap, Music music) {
        super(requestMap);
        this.setMsgType("music");
        this.music = music;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

}
