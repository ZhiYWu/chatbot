package com.scnu.chatbot.application.job;

import com.alibaba.fastjson.JSON;
import com.scnu.chatbot.domain.ai.service.BaiDuAI;
import com.scnu.chatbot.domain.zsxq.IZsxqApi;
import com.scnu.chatbot.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.scnu.chatbot.domain.zsxq.model.vo.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * 问题任务
 */
@EnableScheduling
@Configuration
public class ChatbotSchedule {

    Logger logger = LoggerFactory.getLogger(ChatbotSchedule.class);

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

    // 表达式：cron.qqe2.com
    @Scheduled(cron = "0/5 * * * * ?")
    public void run() {
        try {
            // 避免封控
            if (new Random().nextBoolean()) {
                logger.info("随机打烊中...");
                return;
            }

            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour > 22 || hour < 7) {
                logger.info("打烊时间不工作，AI 下班了！");
                return;
            }

            // 1.检索问题
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
            logger.info("检索结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if (null == topics || topics.isEmpty()) {
                logger.info("本次检索未查询到待回答问题");
                return;
            }

            // 2.AI回答
            Topics topic = topics.get(0);
            String answer = baiduAI.doERNIEBot(accessToken, topic.getTalk().getText());
            // 3.问题回复
            boolean status = zsxqApi.answer(groupId, cookie, topic.getTopic_id(), answer, false);
            logger.info("编号：{} 问题：{} 回答：{} 状态：{}", topic.getTopic_id(), topic.getTalk().getText(), answer, status);
        } catch (Exception e) {
            logger.error("自动回答问题异常", e);
        }
    }

}
