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

        get.addHeader("cookie", "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22581122128112814%22%2C%22first_id%22%3A%2218721ed9651a49-0aba9c0ee3b22e8-7a545471-2073600-18721ed96522b3%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg3MjFlZDk2NTFhNDktMGFiYTljMGVlM2IyMmU4LTdhNTQ1NDcxLTIwNzM2MDAtMTg3MjFlZDk2NTIyYjMiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI1ODExMjIxMjgxMTI4MTQifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22581122128112814%22%7D%2C%22%24device_id%22%3A%2218721ed9651a49-0aba9c0ee3b22e8-7a545471-2073600-18721ed96522b3%22%7D; zsxq_access_token=4832348F-7DE4-7AEE-AD8C-B3449C8E2153_0CF2ACF67DE565D2; abtest_env=product; zsxqsessionid=00e4f444883988f753e0b07cd940c2bf; __cuid=bc3a12a147284735a538861c0d76c34e; amp_fef1e8=6e59f57e-d59d-4ba1-88c0-52d67aecc5abR...1hgsen1vc.1hgseudl2.7.4.b");
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

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/588421844888254/comments");

        post.addHeader("cookie", "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22581122128112814%22%2C%22first_id%22%3A%2218721ed9651a49-0aba9c0ee3b22e8-7a545471-2073600-18721ed96522b3%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg3MjFlZDk2NTFhNDktMGFiYTljMGVlM2IyMmU4LTdhNTQ1NDcxLTIwNzM2MDAtMTg3MjFlZDk2NTIyYjMiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI1ODExMjIxMjgxMTI4MTQifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22581122128112814%22%7D%2C%22%24device_id%22%3A%2218721ed9651a49-0aba9c0ee3b22e8-7a545471-2073600-18721ed96522b3%22%7D; zsxq_access_token=4832348F-7DE4-7AEE-AD8C-B3449C8E2153_0CF2ACF67DE565D2; abtest_env=product; zsxqsessionid=00e4f444883988f753e0b07cd940c2bf; __cuid=bc3a12a147284735a538861c0d76c34e; amp_fef1e8=6e59f57e-d59d-4ba1-88c0-52d67aecc5abR...1hgsen1vc.1hgseudl2.7.4.b");
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
}
