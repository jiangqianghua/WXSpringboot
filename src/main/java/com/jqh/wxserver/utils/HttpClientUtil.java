package com.jqh.wxserver.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    public static String doGet(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder(url);
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    builder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                }
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String doGet(String url) {
        return doGet(url,null);
    }

    public static String doPost(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);

        if (params != null) {
            List<NameValuePair> paramList = new ArrayList<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, StandardCharsets.UTF_8);
                httpPost.setEntity(entity);
                try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                    return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String doPost(String url,String json){
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        String response = "";
        try {
            StringEntity s = new StringEntity(json, "utf-8");
            s.setContentEncoding("utf-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = client.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = res.getEntity();
                response = EntityUtils.toString(res.getEntity());// 返回json格式：
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

}