package com.jqh.wxserver.bean;

import java.util.List;
import java.util.Map;

public class NewsMessage extends BaseMessage {
    private String articleCount ;
    private List<Article> articleList;

    public NewsMessage(Map<String, String> requestMap, List<Article> articleList) {
        super(requestMap);
        this.articleList = articleList;
        this.articleCount = articleList.size() + "";
    }

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}
