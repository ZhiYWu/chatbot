package com.scnu.chatbot.domain.ai;

import java.io.IOException;

/**
 * chatGPT openai 接口
 */
public interface IBaiDuAI {

    String doERNIEBot(String accessToken, String question) throws IOException;
}
