package com.scnu.chatbot.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * 单元测试
 */
public class ApiTest {
    @Test
    public void query_unanswered_questions() throws IOException {
        // 封住数据信息
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20");

        get.addHeader("cookie", "cookie数据");
        get.addHeader("Cotent-Type", "application/json;charset=uft8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }


    @Test
    public void comment() throws IOException {
        // 封住数据信息
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/588428242182244/comments");

        post.addHeader("cookie", "cookie数据");
        post.addHeader("Content-Type", "application/json; charset=UTF-8");

        String paramJson = "{\"req_data\":{\"text\":\"success\\n\",\"image_ids\":[],\"mentioned_user_ids\":[]}}";
        // 封装入参信息
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void test_ERNIEBot() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions?access_token=24.6fe9a5e80e311c0ca46de18417ff1162.2592000.1707303734.282335-46644035");
        post.addHeader("Content-Type", "application/json");

        String paramJson = "{\"messages\":[{\"role\":\"user\",\"content\":\"hello\"}],\"disable_search\":false,\"enable_citation\":false}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("application/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}
