package com.scnu.chatbot.domain.ai.service;

import com.alibaba.fastjson.JSON;
import com.scnu.chatbot.domain.ai.IBaiDuAI;
import com.scnu.chatbot.domain.ai.model.aggregates.AIAnswer;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BaiDuAI implements IBaiDuAI {

    @Override
    public String doERNIEBot(String accessToken, String question) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions?access_token=" + accessToken);
        post.addHeader("Content-Type", "application/json");

        String paramJson = "{\"messages\":[{\"role\":\"user\",\"content\":\"" + question + "\"}],\"disable_search\":false,\"enable_citation\":false}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String paramStr = EntityUtils.toString(response.getEntity());
            AIAnswer aiAnswer = JSON.parseObject(paramStr, AIAnswer.class);
            return aiAnswer.getResult();
        } else {
            throw new RuntimeException("api.baidu.com Err Code is " + response.getStatusLine().getStatusCode());
        }
    }
}
