package com.scnu.chatbot.test;

import com.alibaba.fastjson.JSON;
import com.scnu.chatbot.domain.ai.service.BaiDuAI;
import com.scnu.chatbot.domain.zsxq.IZsxqApi;
import com.scnu.chatbot.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.scnu.chatbot.domain.zsxq.model.vo.Topics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {

    Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;

    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Value("${chatbot-api.accessToken}")
    private String accessToken;

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private BaiDuAI baiduAI;

    @Test
    public void testZsxqApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));

        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for (Topics topic : topics) {
            String topicId = topic.getTopic_id();
            String text = topic.getTalk().getText();

            logger.info("topicId：{} text：{}", topicId, text);
            zsxqApi.answer(groupId, cookie, topicId, text, true);
        }
    }

    @Test
    public void test_openAi() throws IOException {
        String response = baiduAI.doERNIEBot(accessToken, "请告诉我Spring Security有什么用");
        logger.info("测试结果：{}", response);
    }

}
