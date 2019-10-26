package com.jqh.wxserver.bean;

public class AccessToken {

    private String accessToken;

    private long expireTime ;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public AccessToken(String accessToken, double expireIn) {
        this.accessToken = accessToken;
        this.expireTime = System.currentTimeMillis() + (long)(expireIn * 1000);
    }

    public boolean isEXpired() {
        return System.currentTimeMillis() > this.expireTime;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "accessToken='" + accessToken + '\'' +
                ", expireTime=" + expireTime +
                '}';
    }
}
